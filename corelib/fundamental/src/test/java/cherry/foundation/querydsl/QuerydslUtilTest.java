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

import static com.querydsl.core.types.dsl.Expressions.as;
import static com.querydsl.core.types.dsl.Expressions.constant;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.query.QVerifySecure;
import cherry.goods.paginate.PageSet;
import cherry.goods.paginate.PagedList;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.TimeExpression;
import com.querydsl.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
@Transactional
public class QuerydslUtilTest {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Test
	public void currentDate() {
		DateExpression<LocalDate> expr = QuerydslUtil.currentDate();
		String query = queryFactory.query().select(expr).getSQL().getSQL();
		assertEquals("select current_date\nfrom dual", query);
	}

	@Test
	public void currentTime() {
		TimeExpression<LocalTime> expr = QuerydslUtil.currentTime();
		String query = queryFactory.query().select(expr).getSQL().getSQL();
		assertEquals("select current_time\nfrom dual", query);
	}

	@Test
	public void currentDateTime() {
		DateTimeExpression<LocalDateTime> expr = QuerydslUtil.currentTimestamp();
		String query = queryFactory.query().select(expr).getSQL().getSQL();
		assertEquals("select current_timestamp\nfrom dual", query);
	}

	@Test
	public void adjustSize() {
		assertNull(QuerydslUtil.adjustSize(null, QVerifySecure.verifySecure.int32));
		assertEquals("", QuerydslUtil.adjustSize("", QVerifySecure.verifySecure.int32));
		assertEquals("123456789012345678901234567890123456789012345678901234567890123456789012",
				QuerydslUtil.adjustSize("123456789012345678901234567890123456789012345678901234567890123456789012",
						QVerifySecure.verifySecure.int32));
		assertEquals("123456789012345678901234567890123456789012345678901234567890123456789012",
				QuerydslUtil.adjustSize("1234567890123456789012345678901234567890123456789012345678901234567890123",
						QVerifySecure.verifySecure.int32));
	}

	@Test
	public void getExpressionLabel() {
		assertEquals("INT32", QuerydslUtil.getExpressionLabel(QVerifySecure.verifySecure.int32));
		assertEquals("aaa", QuerydslUtil.getExpressionLabel(QVerifySecure.verifySecure.int32.as("aaa")));
		assertNull(QuerydslUtil.getExpressionLabel(QuerydslUtil.currentDate()));
	}

	@Test
	public void tupleToMap() {
		Expression<String> string = constant("string");
		Expression<String> stringWithName = as(string, "aaa");
		List<Tuple> list = queryFactory.query().select(string, stringWithName).fetch();

		PagedList<Tuple> pagedList = new PagedList<>();
		pagedList.setPageSet(new PageSet());
		pagedList.setList(list);

		PagedList<Map<String, ?>> plist = QuerydslUtil.tupleToMap(pagedList, string, stringWithName);
		assertEquals(pagedList.getPageSet(), plist.getPageSet());
		assertEquals(1, plist.getList().size());
		assertEquals(1, plist.getList().get(0).entrySet().size());
		assertEquals("string", plist.getList().get(0).get("aaa"));
	}

	@Test
	public void misc() {
		assertNotNull(new QuerydslUtil());
	}

}
