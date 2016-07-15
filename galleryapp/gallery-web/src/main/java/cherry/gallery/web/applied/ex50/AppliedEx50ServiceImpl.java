/*
 * Copyright 2015,2016 agwlvssainokuni
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

package cherry.gallery.web.applied.ex50;

import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_00;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_01;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_02;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_03;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_04;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_05;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_06;
import static cherry.gallery.common.CodeValue.SORT_BY.SORT_BY_07;
import static java.util.Arrays.asList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.elemental.paginate.PagedList;
import cherry.fundamental.bizcal.BizDateTime;
import cherry.fundamental.download.TableDownloadOperation;
import cherry.fundamental.querydsl.QuerydslSupport;
import cherry.fundamental.spring.webmvc.SortOrder;
import cherry.fundamental.spring.webmvc.SortParam;
import cherry.fundamental.type.EnumCodeUtil;
import cherry.gallery.common.CodeValue.SORT_BY;
import cherry.gallery.db.gen.query.BExTbl1;
import cherry.gallery.db.gen.query.QExTbl1;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.sql.SQLQuery;

@Service
public class AppliedEx50ServiceImpl implements AppliedEx50Service {

	@Autowired
	private QuerydslSupport querydslSupport;

	@Autowired
	private TableDownloadOperation tableDownloadOperation;

	@Autowired
	private BizDateTime bizDateTime;

	private final QExTbl1 et1 = new QExTbl1("et1");

	private final String filename = "ex50_{0}.xlsx";
	private final List<String> header = asList("ID", "文字列【10】", "文字列【100】", "整数【64bit】", "小数【1桁】", "小数【3桁】", "日付",
			"時刻", "日時");

	@Transactional
	@Override
	public PagedList<BExTbl1> search(AppliedEx50Form form) {
		return querydslSupport.search(commonClause(form), orderByClause(form), form.getPno(), form.getPsz(), et1);
	}

	@Transactional
	@Override
	public void downloadXlsx(AppliedEx50Form form, HttpServletResponse response) {
		LocalDateTime now = bizDateTime.now();
		tableDownloadOperation.downloadXlsx(response, filename, now, header, commonClause(form), orderByClause(form),
				et1.id, et1.text10, et1.text100, et1.int64, et1.decimal1, et1.decimal3, et1.dt, et1.tm, et1.dtm);
	}

	private Function<SQLQuery<?>, SQLQuery<?>> commonClause(final AppliedEx50Form form) {
		return (SQLQuery<?> query) -> {
			query.from(et1);
			if (StringUtils.isNotEmpty(form.getText10())) {
				query.where(et1.text10.startsWith(form.getText10()));
			}
			if (form.getInt64From() != null) {
				query.where(et1.int64.goe(form.getInt64From()));
			}
			if (form.getInt64To() != null) {
				query.where(et1.int64.loe(form.getInt64To()));
			}
			if (form.getDecimal1From() != null) {
				query.where(et1.decimal1.goe(form.getDecimal1From()));
			}
			if (form.getDecimal1To() != null) {
				query.where(et1.decimal1.loe(form.getDecimal1To()));
			}
			if (form.getDecimal3From() != null) {
				query.where(et1.decimal3.goe(form.getDecimal3From()));
			}
			if (form.getDecimal3To() != null) {
				query.where(et1.decimal3.loe(form.getDecimal3To()));
			}
			if (form.getDtFrom() != null) {
				query.where(et1.dt.goe(form.getDtFrom()));
			}
			if (form.getDtTo() != null) {
				query.where(et1.dt.loe(form.getDtTo()));
			}
			if (form.getTmFrom() != null) {
				query.where(et1.tm.goe(form.getTmFrom()));
			}
			if (form.getTmTo() != null) {
				query.where(et1.tm.loe(form.getTmTo()));
			}
			if (form.getDtmFromD() != null && form.getDtmFromT() != null) {
				query.where(et1.dtm.goe(form.getDtmFromD().atTime(form.getDtmFromT())));
			}
			if (form.getDtmToD() != null && form.getDtmToT() != null) {
				query.where(et1.dtm.loe(form.getDtmToD().atTime(form.getDtmToT())));
			}
			return query;
		};
	}

	private Function<SQLQuery<?>, SQLQuery<?>> orderByClause(final AppliedEx50Form form) {
		return (SQLQuery<?> query) -> {
			query.orderBy(createOrderSpec(form.getSort1()));
			query.orderBy(createOrderSpec(form.getSort2()));
			return query;
		};
	}

	private OrderSpecifier<?> createOrderSpec(SortParam sort) {

		SORT_BY sortBy = EnumCodeUtil.getCodeMap(SORT_BY.class).get(sort.getBy());

		ComparableExpressionBase<?> sortKey;
		if (sortBy == SORT_BY_00) {
			sortKey = et1.id;
		} else if (sortBy == SORT_BY_01) {
			sortKey = et1.text10;
		} else if (sortBy == SORT_BY_02) {
			sortKey = et1.int64;
		} else if (sortBy == SORT_BY_03) {
			sortKey = et1.decimal1;
		} else if (sortBy == SORT_BY_04) {
			sortKey = et1.decimal3;
		} else if (sortBy == SORT_BY_05) {
			sortKey = et1.dt;
		} else if (sortBy == SORT_BY_06) {
			sortKey = et1.tm;
		} else if (sortBy == SORT_BY_07) {
			sortKey = et1.dtm;
		} else {
			sortKey = et1.id;
		}

		if (sort.getOrder() == SortOrder.ASC) {
			return sortKey.asc();
		} else if (sort.getOrder() == SortOrder.DESC) {
			return sortKey.desc();
		} else {
			return sortKey.asc();
		}
	}

}
