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
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
@Transactional
public class SQLQueryFactoryTest {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Test
	public void testQueryFromDual() {
		SQLQuery<?> query = queryFactory.query();
		SQLQuery<?> subquery = SQLExpressions.selectOne();
		query.where(subquery.exists());
		Object[] object = query.select(Wildcard.all).fetchOne();
		assertEquals(1, object.length);
		assertEquals(Long.valueOf(1L), object[0]);
		assertEquals("select *\nfrom dual\nwhere exists (select 1\nfrom dual)", query.getSQL().getSQL());
	}

	@Test
	public void testInitialize() {
		assertNotNull(queryFactory);
	}

}
