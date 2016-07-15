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

package cherry.foundation.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.query.QVerifyDatetime;

import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
@Transactional
public class JSR310LocalDateTimeTypeTest {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QVerifyDatetime vd = new QVerifyDatetime("vd");

	private final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterFactory("yyyy-MM-dd HH:mm:ss.SSS")
			.createDateTimeFormatter();

	@Test
	public void testSaveAndLoad() {

		LocalDateTime orig = LocalDateTime.now();
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.dtm, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery<?> query = queryFactory.from(vd);
		LocalDateTime result = query.select(vd.dtm).fetchOne();
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE dtm=?", Integer.class,
						orig.format(dateTimeFormatter)));
	}

	@Test
	public void testSaveAndLoad_plus1d() {

		LocalDateTime orig = LocalDateTime.now().plusDays(1);
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.dtm, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery<?> query = queryFactory.from(vd);
		LocalDateTime result = query.select(vd.dtm).fetchOne();
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE dtm=?", Integer.class,
						orig.format(dateTimeFormatter)));
	}

	@Test
	public void testSaveAndLoad_null() {

		SQLInsertClause insert = queryFactory.insert(vd);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery<?> query = queryFactory.from(vd);
		LocalDateTime result = query.select(vd.dtm).fetchOne();
		assertNull(result);
	}

	@Test
	public void testLoad() {
		jdbcOperations.execute("INSERT INTO verify_datetime(dtm) VALUES ('2015-01-23 12:34:56.789')");
		LocalDateTime result = queryFactory.from(vd).select(vd.dtm).fetchOne();
		assertEquals(LocalDateTime.of(2015, 1, 23, 12, 34, 56, 789 * 1_000_000), result);
	}

	@Test
	public void testMisc() {

		JSR310LocalDateTimeType type = new JSR310LocalDateTimeType(Types.TIMESTAMP);
		assertEquals(1, type.getSQLTypes().length);
		assertEquals(Types.TIMESTAMP, type.getSQLTypes()[0]);

		assertEquals("2015-01-23 12:34:56", type.getLiteral(LocalDateTime.of(2015, 1, 23, 12, 34, 56)));
	}

}
