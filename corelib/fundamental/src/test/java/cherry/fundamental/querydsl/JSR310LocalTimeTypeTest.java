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

package cherry.fundamental.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Types;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.fundamental.db.gen.query.QVerifyDatetime;

import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
@Transactional
public class JSR310LocalTimeTypeTest {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QVerifyDatetime vd = new QVerifyDatetime("vd");

	private final DateTimeFormatter timeFormatter = new DateTimeFormatterFactory("HH:mm:ss.SSS")
			.createDateTimeFormatter();

	@Test
	public void testSaveAndLoad() {

		LocalTime orig = LocalTime.now();
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.tm, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery<?> query = queryFactory.from(vd);
		LocalTime result = query.select(vd.tm).fetchOne();
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE tm=?", Integer.class,
						orig.format(timeFormatter)));
	}

	@Test
	public void testSaveAndLoad_plus1h() {

		LocalTime orig = LocalTime.now().plusHours(1);
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.tm, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery<?> query = queryFactory.from(vd);
		LocalTime result = query.select(vd.tm).fetchOne();
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE tm=?", Integer.class,
						orig.format(timeFormatter)));
	}

	@Test
	public void testSaveAndLoad_null() {

		SQLInsertClause insert = queryFactory.insert(vd);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery<?> query = queryFactory.from(vd);
		LocalTime result = query.select(vd.tm).fetchOne();
		assertNull(result);
	}

	@Test
	public void testLoad() {
		jdbcOperations.execute("INSERT INTO verify_datetime(tm) VALUES ('12:34:56.789')");
		LocalTime result = queryFactory.from(vd).select(vd.tm).fetchOne();
		assertEquals(LocalTime.of(12, 34, 56, 789 * 1_000_000), result);
	}

	@Test
	public void testMisc() {

		JSR310LocalTimeType type = new JSR310LocalTimeType(Types.TIMESTAMP);
		assertEquals(1, type.getSQLTypes().length);
		assertEquals(Types.TIMESTAMP, type.getSQLTypes()[0]);

		assertEquals("12:34:56", type.getLiteral(LocalTime.of(12, 34, 56)));
	}

}
