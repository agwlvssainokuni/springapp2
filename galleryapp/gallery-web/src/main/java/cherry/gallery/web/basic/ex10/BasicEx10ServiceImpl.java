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

package cherry.gallery.web.basic.ex10;

import static com.querydsl.core.types.dsl.Expressions.ONE;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.gallery.db.gen.query.QExTbl1;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;

@Service
public class BasicEx10ServiceImpl implements BasicEx10Service {

	@Autowired
	private SQLQueryFactory qf;

	private final QExTbl1 et1 = new QExTbl1("et1");

	@Transactional
	@Override
	public boolean exists(String text10) {
		return qf.from(et1).where(et1.text10.eq(text10)).select(ONE).fetchFirst() != null;
	}

	@Transactional
	@Override
	public Long create(BasicEx10Form form) {
		SQLInsertClause insert = qf.insert(et1);
		if (StringUtils.isNotEmpty(form.getText10())) {
			insert.set(et1.text10, form.getText10());
		}
		if (StringUtils.isNotEmpty(form.getText100())) {
			insert.set(et1.text100, form.getText100());
		}
		insert.set(et1.int64, form.getInt64());
		insert.set(et1.decimal1, form.getDecimal1());
		insert.set(et1.decimal3, form.getDecimal3());
		insert.set(et1.dt, form.getDt());
		insert.set(et1.tm, form.getTm());
		insert.set(et1.dtm, form.getDtm());
		return insert.executeWithKey(et1.id);
	}

	@Transactional
	@Override
	public BasicEx10Form findById(long id) {
		QBean<BasicEx10Form> qb = Projections.bean(BasicEx10Form.class, et1.text10, et1.text100, et1.int64,
				et1.decimal1, et1.decimal3, et1.dt, et1.tm, et1.dtm, et1.lockVersion);
		return qf.from(et1).where(et1.id.eq(id)).select(qb).fetchOne();
	}

	@Transactional
	@Override
	public boolean exists(long id, String text10) {
		return qf.from(et1).where(et1.id.ne(id), et1.text10.eq(text10)).select(ONE).fetchFirst() != null;
	}

	@Transactional
	@Override
	public long update(long id, BasicEx10Form form) {
		SQLUpdateClause update = qf.update(et1).where(et1.id.eq(id));
		update.where(et1.lockVersion.eq(form.getLockVersion())).set(et1.lockVersion, et1.lockVersion.add(1));
		if (StringUtils.isNotEmpty(form.getText10())) {
			update.set(et1.text10, form.getText10());
		}
		if (StringUtils.isNotEmpty(form.getText100())) {
			update.set(et1.text100, form.getText100());
		}
		update.set(et1.int64, form.getInt64());
		update.set(et1.decimal1, form.getDecimal1());
		update.set(et1.decimal3, form.getDecimal3());
		update.set(et1.dt, form.getDt());
		update.set(et1.tm, form.getTm());
		update.set(et1.dtm, form.getDtm());
		return update.execute();
	}

}
