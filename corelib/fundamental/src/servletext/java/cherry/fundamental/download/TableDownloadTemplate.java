/*
 * Copyright 2014,2016 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.fundamental.download;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.Transactional;

import cherry.elemental.excel.ExcelFactory;
import cherry.elemental.excel.ExcelWriter;
import cherry.fundamental.etl.Column;
import cherry.fundamental.etl.Consumer;
import cherry.fundamental.etl.CsvConsumer;
import cherry.fundamental.etl.DelegateConsumer;
import cherry.fundamental.etl.ExcelConsumer;
import cherry.fundamental.querydsl.QuerydslSupport;

import com.querydsl.core.types.Expression;
import com.querydsl.sql.SQLQuery;

public class TableDownloadTemplate implements TableDownloadOperation {

	private DownloadOperation downloadOperation;

	private QuerydslSupport querydslSupport;

	private String csvType;

	private String excelType;

	public void setDownloadOperation(DownloadOperation downloadOperation) {
		this.downloadOperation = downloadOperation;
	}

	public void setQuerydslSupport(QuerydslSupport querydslSupport) {
		this.querydslSupport = querydslSupport;
	}

	public void setCsvType(String csvType) {
		this.csvType = csvType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

	@Transactional
	@Override
	public void downloadCsv(HttpServletResponse response, Charset charset, String filename, LocalDateTime timestamp,
			List<String> header, Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, Expression<?>... expressions) {
		downloadOperation.download(response, csvType, charset, filename, timestamp, (stream) -> {
			try (Writer writer = new OutputStreamWriter(stream, charset)) {
				Consumer consumer = createCsvConsumer(writer, header);
				return querydslSupport.download(commonClause, orderByClause, consumer, expressions);
			}
		});
	}

	@Transactional
	@Override
	public void downloadXls(HttpServletResponse response, String filename, LocalDateTime timestamp,
			List<String> header, Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, Expression<?>... expressions) {
		downloadOperation.download(response, excelType, null, filename, timestamp, (stream) -> {
			try (Workbook workbook = ExcelFactory.createBlankXls(); ExcelWriter writer = new ExcelWriter(workbook)) {
				Consumer consumer = createExcelConsumer(writer, header);
				long count = querydslSupport.download(commonClause, orderByClause, consumer, expressions);
				workbook.write(stream);
				return count;
			}
		});
	}

	@Transactional
	@Override
	public void downloadXlsx(HttpServletResponse response, String filename, LocalDateTime timestamp,
			List<String> header, Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, Expression<?>... expressions) {
		downloadOperation.download(response, excelType, null, filename, timestamp, (stream) -> {
			try (Workbook workbook = ExcelFactory.createBlankXlsx(); ExcelWriter writer = new ExcelWriter(workbook)) {
				Consumer consumer = createExcelConsumer(writer, header);
				long count = querydslSupport.download(commonClause, orderByClause, consumer, expressions);
				workbook.write(stream);
				return count;
			}
		});
	}

	private Consumer createCsvConsumer(Writer writer, List<String> header) {
		if (header == null) {
			return new CsvConsumer(writer, false);
		} else {
			return new WithHeaderConsumer(new CsvConsumer(writer, true), header.toArray(new String[header.size()]));
		}
	}

	private Consumer createExcelConsumer(ExcelWriter writer, List<String> header) {
		if (header == null) {
			return new ExcelConsumer(writer, false);
		} else {
			return new WithHeaderConsumer(new ExcelConsumer(writer, true), header.toArray(new String[header.size()]));
		}
	}

	private static class WithHeaderConsumer extends DelegateConsumer {

		private String[] header;

		public WithHeaderConsumer(Consumer delegate, String[] header) {
			super(delegate);
			this.header = header;
		}

		@Override
		protected Column[] prepareBegin(Column[] col) throws IOException {
			Column[] adjusted = new Column[col.length];
			for (int i = 0; i < col.length; i++) {
				adjusted[i] = new Column();
				adjusted[i].setType(col[i].getType());
				if (i < header.length) {
					adjusted[i].setLabel(header[i]);
				} else {
					adjusted[i].setLabel(col[i].getLabel());
				}
			}
			return adjusted;
		}

		@Override
		protected Object[] prepareConsume(Object[] record) throws IOException {
			return record;
		}

		@Override
		protected void prepareEnd() throws IOException {
			// NOTHING
		}
	}

}
