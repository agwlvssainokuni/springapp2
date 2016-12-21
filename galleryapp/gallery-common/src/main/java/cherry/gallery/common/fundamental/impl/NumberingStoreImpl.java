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

import static com.google.common.base.Preconditions.checkState;
import static com.querydsl.core.types.dsl.Expressions.ONE;

import org.springframework.beans.factory.annotation.Autowired;

import cherry.fundamental.numbering.AbstractNumberingStore;
import cherry.fundamental.numbering.NumberingDefinition;
import cherry.gallery.db.gen.query.QNumberingMaster;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;

public class NumberingStoreImpl extends AbstractNumberingStore {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QNumberingMaster nm = new QNumberingMaster("nm");
	private final QBean<NumberingDefinition> qbean = Projections.bean(NumberingDefinition.class, nm.template,
			nm.minValue, nm.maxValue);

	@Override
	protected NumberingDefinition getDefinitionInternal(String numberName) {
		return baseQuery(numberName).select(qbean).fetch().stream().findFirst().get();
	}

	@Override
	public long loadAndLock(String numberName) {
		return baseQuery(numberName).forUpdate().select(nm.currentValue).fetch().stream().findFirst().get().longValue();
	}

	@Override
	public void saveAndUnlock(final String numberName, final long current) {
		SQLUpdateClause update = queryFactory.update(nm);
		update.set(nm.currentValue, current);
		update.set(nm.lockVersion, nm.lockVersion.add(ONE));
		update.where(nm.name.eq(numberName));
		long count = update.execute();
		checkState(count == 1L, "Failed to update %s: name=%s, currentValue=%s, count=%s", nm.getTableName(),
				numberName, current, count);
	}

	private SQLQuery<?> baseQuery(String numberName) {
		SQLQuery<?> query = queryFactory.from(nm);
		query.where(nm.name.eq(numberName));
		return query;
	}

}
