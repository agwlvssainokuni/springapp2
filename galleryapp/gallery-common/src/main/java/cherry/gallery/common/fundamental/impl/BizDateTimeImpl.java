/*
 * Copyright 2014,2018 agwlvssainokuni
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

import static com.querydsl.core.types.dsl.DateTimeExpression.currentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import cherry.fundamental.bizcal.BizDateTime;
import cherry.gallery.db.gen.query.QBizdatetimeMaster;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.sql.SQLQueryFactory;

public class BizDateTimeImpl implements BizDateTime {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QBizdatetimeMaster bm = new QBizdatetimeMaster("bm");

	@Override
	public LocalDate today() {
		LocalDate ldt = queryFactory.from(bm).orderBy(bm.id.desc()).select(bm.bizdate).fetchFirst();
		if (ldt == null) {
			return LocalDate.now();
		}
		return ldt;
	}

	@Override
	public LocalDateTime now() {
		DateTimeExpression<LocalDateTime> curDtm = currentTimestamp(LocalDateTime.class);
		Tuple tuple = queryFactory.from(bm).orderBy(bm.id.desc())
				.select(curDtm, bm.offsetDay, bm.offsetHour, bm.offsetMinute, bm.offsetSecond).fetchFirst();
		if (tuple == null) {
			return LocalDateTime.now();
		}
		return tuple.get(curDtm).plusDays(tuple.get(bm.offsetDay).longValue())
				.plusHours(tuple.get(bm.offsetHour).longValue()).plusMinutes(tuple.get(bm.offsetMinute).longValue())
				.plusSeconds(tuple.get(bm.offsetSecond).longValue());
	}

}
