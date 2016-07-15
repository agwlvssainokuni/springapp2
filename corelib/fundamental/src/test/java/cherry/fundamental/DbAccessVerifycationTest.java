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

package cherry.fundamental;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.fundamental.db.gen.query.QVerifyConstraints;

import com.querydsl.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
@Transactional
public class DbAccessVerifycationTest {

	@Autowired
	private SQLQueryFactory qf;

	private final QVerifyConstraints vc = new QVerifyConstraints("vc");

	@Test
	public void testQueryDslUnique_OK() {
		assertEquals(1L, qf.insert(vc).set(vc.name, "name0").execute());
		assertEquals(1L, qf.insert(vc).set(vc.name, "name1").execute());
	}

	@Test(expected = DuplicateKeyException.class)
	public void testQueryDslUnique_NG() {
		assertEquals(1L, qf.insert(vc).set(vc.name, "name0").execute());
		qf.insert(vc).set(vc.name, "name0").execute();
	}

	@Test
	public void testQueryDslRef_OK() {
		Long id = qf.insert(vc).set(vc.name, "name0").executeWithKey(vc.id);
		assertNotNull(id);
		assertEquals(1L, qf.insert(vc).set(vc.parentId, id).execute());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testQueryDslRef_NG() {
		Long id = qf.insert(vc).set(vc.name, "name0").executeWithKey(vc.id);
		assertNotNull(id);
		qf.insert(vc).set(vc.parentId, 0L).execute();
	}

}
