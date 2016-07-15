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

package cherry.foundation.querydsl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.ExtractorResultSetExtractor;
import cherry.foundation.etl.Limiter;
import cherry.foundation.etl.LimiterException;
import cherry.foundation.etl.NoneLimiter;
import cherry.goods.paginate.PageSet;
import cherry.goods.paginate.PagedList;
import cherry.goods.paginate.Paginator;

import com.querydsl.core.types.Expression;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

public class QuerydslSupportImpl implements QuerydslSupport {

	private SQLQueryFactory queryFactory;

	private SQLExceptionTranslator exceptionTranslator;

	private Paginator paginator;

	public void setQueryFactory(SQLQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public void setExceptionTranslator(SQLExceptionTranslator exceptionTranslator) {
		this.exceptionTranslator = exceptionTranslator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	@Transactional
	@Override
	public <T> PagedList<T> search(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, long pageNo, long pageSz, RowMapper<T> rowMapper,
			Expression<?>... expressions) {
		return search(commonClause, orderByClause, pageNo, pageSz, (l -> false), rowMapper, expressions);
	}

	@Transactional
	@Override
	public <T> PagedList<T> search(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, RowMapper<T> rowMapper, Expression<?>... expressions) {
		return searchMain(commonClause, orderByClause, pageNo, pageSz, cancelPredicate, (query) -> {
			ResultSetExtractor<List<T>> extractor = new RowMapperResultSetExtractor<>(rowMapper);
			return getResults("search", query, extractor, expressions);
		});
	}

	@Transactional
	@Override
	public <T> PagedList<T> search(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, long pageNo, long pageSz, Expression<T> expression) {
		return search(commonClause, orderByClause, pageNo, pageSz, (l -> false), expression);
	}

	@Transactional
	@Override
	public <T> PagedList<T> search(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, Expression<T> expression) {
		return searchMain(commonClause, orderByClause, pageNo, pageSz, cancelPredicate, (query) -> {
			return query.select(expression).fetch();
		});
	}

	@Transactional
	@Override
	public long download(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, Consumer consumer, Expression<?>... expressions)
			throws IOException {
		return downloadMain(commonClause, orderByClause, consumer, new NoneLimiter(), expressions);
	}

	@Transactional
	@Override
	public long download(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, Consumer consumer, Limiter limiter,
			Expression<?>... expressions) throws LimiterException, IOException {
		return downloadMain(commonClause, orderByClause, consumer, limiter, expressions);
	}

	private <T> PagedList<T> searchMain(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, Function<SQLQuery<?>, List<T>> searchFunction) {

		long count = commonClause.apply(queryFactory.query()).fetchCount();

		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);
		if (cancelPredicate.test(count)) {
			PagedList<T> result = new PagedList<>();
			result.setPageSet(pageSet);
			return result;
		}

		SQLQuery<?> query = commonClause.apply(queryFactory.query());
		query.limit(pageSz).offset(pageSet.getCurrent().getFrom());
		query = orderByClause.apply(query);
		List<T> list = searchFunction.apply(query);

		PagedList<T> result = new PagedList<>();
		result.setPageSet(pageSet);
		result.setList(list);
		return result;
	}

	private long downloadMain(Function<SQLQuery<?>, SQLQuery<?>> commonClause,
			Function<SQLQuery<?>, SQLQuery<?>> orderByClause, Consumer consumer, Limiter limiter,
			Expression<?>... expressions) throws LimiterException, IOException {

		limiter.start();
		try {

			SQLQuery<?> query = commonClause.apply(queryFactory.query());
			query = orderByClause.apply(query);

			ResultSetExtractor<Long> extractor = new ExtractorResultSetExtractor(consumer, limiter);
			return getResults("download", query, extractor, expressions);

		} catch (IllegalStateException ex) {
			throw (IOException) ex.getCause();
		} finally {
			limiter.stop();
		}
	}

	private <T> T getResults(String task, SQLQuery<?> query, ResultSetExtractor<T> extractor,
			Expression<?>... expressions) {
		ResultSet rs = query.select(expressions).getResults();
		try {
			return extractor.extractData(rs);
		} catch (SQLException ex) {
			throw exceptionTranslator.translate(task, query.getSQL().getSQL(), ex);
		} finally {
			JdbcUtils.closeResultSet(rs);
		}
	}

}
