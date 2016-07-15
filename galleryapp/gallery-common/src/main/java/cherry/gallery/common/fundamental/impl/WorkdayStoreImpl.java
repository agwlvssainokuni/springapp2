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

package cherry.gallery.common.fundamental.impl;

import static com.querydsl.core.types.dsl.Expressions.ONE;
import static com.querydsl.core.types.dsl.Expressions.constant;
import static com.querydsl.core.types.dsl.Expressions.dateOperation;
import static com.querydsl.core.types.dsl.Expressions.numberPath;
import static com.querydsl.core.types.dsl.Expressions.numberTemplate;
import static com.querydsl.core.types.dsl.Expressions.path;
import static com.querydsl.sql.SQLExpressions.select;
import static com.querydsl.sql.SQLExpressions.selectOne;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cherry.fundamental.bizcal.WorkdayStore;
import cherry.fundamental.querydsl.QuerydslUtil;
import cherry.gallery.db.gen.query.QDayoffMaster;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Ops.DateTimeOps;
import com.querydsl.core.types.dsl.DateOperation;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.Union;

public class WorkdayStoreImpl implements WorkdayStore {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QDayoffMaster h0 = new QDayoffMaster("h0");

	private final NumberPath<Long> a = numberPath(Long.class, "a");
	private final NumberExpression<Long> a0 = numberPath(Long.class, a, String.valueOf(0));
	private final NumberPath<Long> b = numberPath(Long.class, "b");
	private final NumberExpression<Long> b0 = numberPath(Long.class, b, String.valueOf(0));
	private final SimplePath<Tuple> d = path(Tuple.class, "d");
	private final NumberPath<Long> dnm = numberPath(Long.class, d, "nm");
	private final Union<Long> digit = QuerydslUtil.createSequenceByUnion(0, 9, 1, null);
	private final NumberExpression<Long> TEN = numberTemplate(Long.class, String.valueOf(10));
	private final SQLQuery<Long> seq = select(a0.multiply(TEN).add(b0).as(dnm)).from(digit.as(a), digit.as(b));

	@Transactional
	@Override
	public int getNumberOfWorkday(String name, LocalDate from, LocalDate to) {
		SQLQuery<Long> query = queryFactory.select(h0.dt.countDistinct()).from(h0);
		query.where(h0.name.eq(name), h0.dt.between(from, to));
		long count = query.fetchOne();
		return (int) (from.until(to.plusDays(1), ChronoUnit.DAYS) - count);
	}

	@Transactional
	@Override
	public LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday) {
		SQLQuery<LocalDate> query = queryFactory.select(addDays(from, dnm)).from(seq.as(d));
		query.where(selectOne().from(h0).where(h0.name.eq(name), h0.dt.eq(addDays(from, dnm))).notExists());
		query.where(select(h0.dt.countDistinct()).from(h0)
				.where(h0.name.eq(name), h0.dt.between(constant(from), addDays(from, dnm)))
				.eq(dnm.subtract(numberOfWorkday).add(ONE)));
		return query.fetchOne();
	}

	private DateOperation<LocalDate> addDays(LocalDate ldt, NumberExpression<Long> diff) {
		return dateOperation(LocalDate.class, DateTimeOps.ADD_DAYS, constant(ldt), diff);
	}

}
