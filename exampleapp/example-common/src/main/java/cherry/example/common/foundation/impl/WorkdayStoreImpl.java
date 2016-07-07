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

package cherry.example.common.foundation.impl;

import static com.querydsl.core.types.dsl.Expressions.constant;
import static com.querydsl.core.types.dsl.Expressions.dateOperation;
import static com.querydsl.core.types.dsl.Expressions.datePath;
import static com.querydsl.core.types.dsl.Expressions.numberPath;
import static com.querydsl.core.types.dsl.Expressions.numberTemplate;
import static com.querydsl.core.types.dsl.Expressions.path;
import static com.querydsl.sql.SQLExpressions.select;
import static com.querydsl.sql.SQLExpressions.selectOne;
import static com.querydsl.sql.SQLExpressions.unionAll;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cherry.example.db.gen.query.QDayoffMaster;
import cherry.foundation.bizcal.WorkdayStore;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Ops.DateTimeOps;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.DatePath;
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
	private final QDayoffMaster h1 = new QDayoffMaster("h1");

	private final NumberPath<Long> a = numberPath(Long.class, "a");
	private final NumberExpression<Long> a0 = numberPath(Long.class, a, String.valueOf(0));
	private final NumberPath<Long> b = numberPath(Long.class, "b");
	private final NumberExpression<Long> b0 = numberPath(Long.class, b, String.valueOf(0));
	private final SimplePath<Tuple> d = path(Tuple.class, "d");
	private final NumberPath<Long> dnm = numberPath(Long.class, d, "nm");
	private final DatePath<LocalDate> ddt = datePath(LocalDate.class, d, "dt");
	private final Union<Long> digit = unionAll(createDigitList(0, 9));

	@Transactional
	@Override
	public int getNumberOfWorkday(String name, LocalDate from, LocalDate to) {
		SQLQuery<?> query = queryFactory.from(h0);
		query.where(h0.name.eq(name), h0.dt.between(from, to));
		long count = query.select(h0.dt.count()).fetchOne();
		return (int) (from.until(to.plusDays(1), ChronoUnit.DAYS) - count);
	}

	@Transactional
	@Override
	public LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday) {

		SQLQuery<Tuple> dtcal = select(a0.multiply(10).add(b0).as(dnm),
				dateOperation(LocalDate.class, DateTimeOps.ADD_DAYS, constant(from), a0.multiply(10).add(b0)).as(ddt))
				.from(digit.as(a), digit.as(b));

		SQLQuery<LocalDate> query = queryFactory.select(ddt.min()).from(dtcal.as(d)).leftJoin(h0)
				.on(h0.name.eq(name), h0.dt.between(constant(from), ddt));
		query.where(selectOne().from(h1).where(h1.name.eq(name), h1.dt.eq(ddt)).notExists());
		query.groupBy(dnm);
		query.having(h0.dt.countDistinct().eq(dnm.subtract(numberOfWorkday).add(1)));
		return query.fetchOne();
	}

	private List<SubQueryExpression<Long>> createDigitList(int fm, int to) {
		List<SubQueryExpression<Long>> list = new ArrayList<>(to - fm + 1);
		for (int i = fm; i <= to; i++) {
			list.add(select(numberTemplate(Long.class, String.valueOf(i))));
		}
		return list;
	}

}
