/*
 * Copyright 2012,2016 agwlvssainokuni
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

package cherry.fundamental.etl;

import java.io.IOException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * データ抽出機能。
 */
public class ExtractorImpl implements Extractor {

	/**
	 * データを抽出する。
	 * 
	 * @param dataSource データソース。
	 * @param sql SQL。
	 * @param paramMap データ抽出時のパラメタ。
	 * @param consumer データの格納先。
	 * @param limiter データ抽出制限。
	 * @return 格納したデータの件数。
	 * @throws LimiterException データ抽出制限超過。
	 * @throws IOException データ格納エラー。
	 */
	@Override
	public long extract(DataSource dataSource, String sql, Map<String, ?> paramMap, Consumer consumer, Limiter limiter)
			throws LimiterException, IOException {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		ResultSetExtractor<Long> extractor = new ExtractorResultSetExtractor(consumer, limiter);

		limiter.start();
		try {
			return template.query(sql, paramMap, extractor);
		} catch (IllegalStateException ex) {
			throw (IOException) ex.getCause();
		} finally {
			limiter.stop();
		}
	}

}
