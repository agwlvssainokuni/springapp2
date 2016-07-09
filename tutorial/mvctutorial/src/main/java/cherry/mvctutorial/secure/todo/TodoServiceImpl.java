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

package cherry.mvctutorial.secure.todo;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.etl.CsvConsumer;
import cherry.foundation.etl.NoneLimiter;
import cherry.foundation.querydsl.QuerydslSupport;
import cherry.goods.paginate.PagedList;
import cherry.mvctutorial.SortOrder;
import cherry.mvctutorial.db.gen.query.BTodo;
import cherry.mvctutorial.db.gen.query.QTodo;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLUpdateClause;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private QuerydslSupport querydslSupport;

	private final QTodo t0 = new QTodo("t0");

	@Transactional(readOnly = true)
	@Override
	public BTodo findById(String loginId, int id) {
		return queryFactory.select(t0).from(t0).where(t0.id.eq(id), t0.postedBy.eq(loginId)).fetchOne();
	}

	@Transactional()
	@Override
	public Integer create(BTodo todo) {
		return queryFactory.insert(t0).populate(todo).executeWithKey(t0.id);
	}

	@Transactional()
	@Override
	public boolean update(String loginId, int id, BTodo todo) {
		SQLUpdateClause update = queryFactory.update(t0);
		update.set(t0.dueDate, todo.getDueDate());
		update.set(t0.description, todo.getDescription());
		update.set(t0.doneFlg, todo.getDoneFlg());
		update.set(t0.doneAt, todo.getDoneAt());
		update.set(t0.lockVersion, t0.lockVersion.add(Expressions.ONE));
		update.where(t0.id.eq(id), t0.postedBy.eq(loginId), t0.lockVersion.eq(todo.getLockVersion()));
		long count = update.execute();
		return count == 1L;
	}

	@Transactional(readOnly = true)
	@Override
	public PagedList<BTodo> searh(String loginId, SearchCondition cond, long pno, long psz) {
		return querydslSupport.search(commonClause(t0, loginId, cond), orderByClause(t0, loginId, cond), pno, psz, t0);
	}

	@Transactional(readOnly = true)
	@Override
	public long export(Writer writer, String loginId, SearchCondition cond) {
		try {
			return querydslSupport.download(commonClause(t0, loginId, cond), orderByClause(t0, loginId, cond),
					new CsvConsumer(writer, true), new NoneLimiter(), t0.id, t0.postedBy, t0.postedAt, t0.dueDate,
					t0.doneAt, t0.doneFlg, t0.description, t0.updatedAt, t0.createdAt, t0.lockVersion);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Function<SQLQuery<?>, SQLQuery<?>> commonClause(QTodo t, String loginId, SearchCondition cond) {
		return (query) -> {
			query.from(t0).where(t.postedBy.eq(loginId));
			if (cond.getPostedFrom() != null) {
				query.where(t.postedAt.goe(cond.getPostedFrom()));
			}
			if (cond.getPostedTo() != null) {
				query.where(t.postedAt.lt(cond.getPostedTo()));
			}
			if (cond.getDueDateFrom() != null) {
				query.where(t.dueDate.goe(cond.getDueDateFrom()));
			}
			if (cond.getDueDateTo() != null) {
				query.where(t.dueDate.lt(cond.getDueDateTo()));
			}
			if (!cond.getDoneFlg().isEmpty()) {
				query.where(t.doneFlg.in(cond.getDoneFlg()));
			}
			return query;
		};
	}

	private Function<SQLQuery<?>, SQLQuery<?>> orderByClause(QTodo t, String loginId, SearchCondition cond) {
		return (query) -> {
			if (StringUtils.isEmpty(cond.getSort().getBy())) {
				return query;
			} else if ("ID".equals(cond.getSort().getBy())) {
				if (cond.getSort().getOrder() == SortOrder.DESC) {
					return query.orderBy(t.id.desc());
				} else {
					return query.orderBy(t.id.asc());
				}
			} else if ("POSTED_AT".equals(cond.getSort().getBy())) {
				if (cond.getSort().getOrder() == SortOrder.DESC) {
					return query.orderBy(t.postedAt.desc());
				} else {
					return query.orderBy(t.postedAt.asc());
				}
			} else if ("DUE_DATE".equals(cond.getSort().getBy())) {
				if (cond.getSort().getOrder() == SortOrder.DESC) {
					return query.orderBy(t.dueDate.desc());
				} else {
					return query.orderBy(t.dueDate.asc());
				}
			} else {
				return query;
			}
		};
	}

}
