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

package cherry.gallery.common.fundamental.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cherry.fundamental.code.AbstractCodeStore;
import cherry.fundamental.code.CodeEntry;
import cherry.fundamental.code.ICodeEntry;
import cherry.gallery.db.gen.query.QCodeMaster;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

public class CodeStoreImpl extends AbstractCodeStore {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QCodeMaster cm = new QCodeMaster("a");
	private final QBean<ICodeEntry> qbean = Projections.<ICodeEntry> bean(CodeEntry.class, cm.value.as("codeValue"),
			cm.label.as("codeLabel"), cm.sortOrder);

	@Override
	protected List<ICodeEntry> getCodeListInternal(String codeName) {
		SQLQuery<?> query = queryFactory.from(cm);
		query.where(cm.name.eq(codeName));
		query.orderBy(cm.sortOrder.asc());
		return query.select(qbean).fetch();
	}

}
