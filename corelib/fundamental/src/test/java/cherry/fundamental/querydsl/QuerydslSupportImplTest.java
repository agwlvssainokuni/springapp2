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

package cherry.fundamental.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.elemental.paginate.PagedList;
import cherry.fundamental.db.gen.query.BVerifyDatetime;
import cherry.fundamental.db.gen.query.QVerifyDatetime;
import cherry.fundamental.etl.Column;
import cherry.fundamental.etl.Consumer;
import cherry.fundamental.etl.CsvConsumer;
import cherry.fundamental.etl.Limiter;
import cherry.fundamental.etl.LimiterException;
import cherry.fundamental.etl.TimeLimiter;

import com.google.common.base.Joiner;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
@Transactional
public class QuerydslSupportImplTest {

	@Autowired
	private QuerydslSupport querydslSupport;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QVerifyDatetime vd = new QVerifyDatetime("vd");

	private final LocalDate localDate = LocalDate.now();

	private final LocalDateTime localDateTime = LocalDateTime.now();

	private final RowMapper<BVerifyDatetime> rowMapper = new BeanPropertyRowMapper<>(BVerifyDatetime.class);

	@Before
	public void before() {
		SQLInsertClause insert = queryFactory.insert(vd);
		for (int i = -100; i <= 100; i++) {
			insert.set(vd.dt, localDate.plusDays(i));
			insert.set(vd.dtm, localDateTime.plusDays(i));
			insert.addBatch();
		}
		assertEquals(201L, insert.execute());
	}

	@Test
	public void testSearch1_OK() {

		PagedList<BVerifyDatetime> pagedList = querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5,
				rowMapper, vd.id, vd.dt, vd.dtm);

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (BVerifyDatetime item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.getDt());
			assertEquals(localDateTime.plusDays(i), item.getDtm());
			i -= 1;
		}
	}

	@Test(expected = DataAccessException.class)
	public void testSearch1_SQLException() {
		querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5, new RowMapper<BVerifyDatetime>() {
			@Override
			public BVerifyDatetime mapRow(ResultSet rs, int rowNum) throws SQLException {
				rs.getObject(0);
				return null;
			}
		}, vd.id, vd.dt, vd.dtm);
	}

	@Test
	public void testSearch2_OK() {

		Predicate<Long> cancelPredicate = (l -> false);
		PagedList<BVerifyDatetime> pagedList = querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5,
				cancelPredicate, rowMapper, vd.id, vd.dt, vd.dtm);

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (BVerifyDatetime item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.getDt());
			assertEquals(localDateTime.plusDays(i), item.getDtm());
			i -= 1;
		}
	}

	@Test
	public void testSearch2_Cancel() {

		Predicate<Long> cancelPredicate = (l -> true);
		PagedList<BVerifyDatetime> pagedList = querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5,
				cancelPredicate, rowMapper, vd.id, vd.dt, vd.dtm);

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		assertNull(pagedList.getList());
	}

	@Test
	public void testSearch3() {

		PagedList<Tuple> pagedList = querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5,
				Projections.tuple(vd.id, vd.dt, vd.dtm));

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (Tuple item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.get(vd.dt));
			assertEquals(localDateTime.plusDays(i), item.get(vd.dtm));
			i -= 1;
		}
	}

	@Test
	public void testSearch4_OK() {

		Predicate<Long> cancelPredicate = (l -> false);
		PagedList<Tuple> pagedList = querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5,
				cancelPredicate, Projections.tuple(vd.id, vd.dt, vd.dtm));

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (Tuple item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.get(vd.dt));
			assertEquals(localDateTime.plusDays(i), item.get(vd.dtm));
			i -= 1;
		}
	}

	@Test
	public void testSearch4_Cancel() {

		Predicate<Long> cancelPredicate = (l -> true);
		PagedList<Tuple> pagedList = querydslSupport.search(commonClause(vd), orderByClause(vd), 10, 5,
				cancelPredicate, Projections.tuple(vd.id, vd.dt, vd.dtm));

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		assertNull(pagedList.getList());
	}

	@Test
	public void testDownload1_OK() throws IOException {
		try (StringWriter w = new StringWriter()) {

			CsvConsumer consumer = new CsvConsumer(w, false);
			long count = querydslSupport.download(commonClause(vd), orderByClause(vd), consumer, vd.dt);
			assertEquals(101L, count);

			List<String> list = new ArrayList<>();
			for (int i = 100; i >= 0; i--) {
				list.add("\"" + localDate.plusDays(i).toString() + "\"\r\n");
			}
			assertEquals(Joiner.on("").join(list), w.toString());
		}
	}

	@Test(expected = IOException.class)
	public void testDownload1_IOException() throws IOException {
		try (StringWriter w = new StringWriter()) {
			querydslSupport.download(commonClause(vd), orderByClause(vd), new Consumer() {

				@Override
				public void begin(Column[] col) throws IOException {
					throw new IOException();
				}

				@Override
				public void consume(Object[] record) throws IOException {
					// 何もしない
				}

				@Override
				public void end() throws IOException {
					// 何もしない
				}
			}, vd.dt);
		}
	}

	@Test
	public void testDownload2_OK() throws IOException {
		try (StringWriter w = new StringWriter()) {

			Limiter limiter = new TimeLimiter(3600000L);
			CsvConsumer consumer = new CsvConsumer(w, false);
			long count = querydslSupport.download(commonClause(vd), orderByClause(vd), consumer, limiter, vd.dt);
			assertEquals(101L, count);

			List<String> list = new ArrayList<>();
			for (int i = 100; i >= 0; i--) {
				list.add("\"" + localDate.plusDays(i).toString() + "\"\r\n");
			}
			assertEquals(Joiner.on("").join(list), w.toString());
		}
	}

	@Test(expected = LimiterException.class)
	public void testDownload2_LimiterException() throws IOException {
		try (StringWriter w = new StringWriter()) {
			Limiter limiter = new TimeLimiter(-1L);
			CsvConsumer consumer = new CsvConsumer(w, false);
			querydslSupport.download(commonClause(vd), orderByClause(vd), consumer, limiter, vd.dt);
		}
	}

	private Function<SQLQuery<?>, SQLQuery<?>> commonClause(QVerifyDatetime qvd) {
		return (query) -> {
			query.from(qvd);
			query.where(qvd.dt.goe(localDate));
			return query;
		};
	}

	private Function<SQLQuery<?>, SQLQuery<?>> orderByClause(QVerifyDatetime qvd) {
		return (query) -> {
			query.orderBy(qvd.dt.desc());
			return query;
		};
	}

}
