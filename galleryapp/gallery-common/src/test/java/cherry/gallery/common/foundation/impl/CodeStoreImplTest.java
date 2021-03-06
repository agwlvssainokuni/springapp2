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

package cherry.gallery.common.foundation.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.fundamental.code.CodeStore;
import cherry.fundamental.code.ICodeEntry;
import cherry.gallery.db.gen.query.QCodeMaster;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-gallery-common-test.xml")
@Transactional
public class CodeStoreImplTest {

	@Autowired
	private CodeStore codeStore;

	@Autowired
	private SQLQueryFactory queryFactory;

	private QCodeMaster cm = new QCodeMaster("cm");

	@Before
	public void before() {
		SQLInsertClause clause = queryFactory.insert(cm);
		for (int i = 1; i <= 3; i++) {
			clause.columns(cm.name, cm.value, cm.label, cm.sortOrder).values("CODE0", "0" + i, "LABEL0" + i, i)
					.addBatch();
		}
		assertEquals(3L, clause.execute());
	}

	@Test
	public void testInitialization() {
		assertNotNull(codeStore);
	}

	@Test
	public void testFindByValue() {
		assertNull(codeStore.findByValue("CODE0", "00"));

		ICodeEntry entry;
		entry = codeStore.findByValue("CODE0", "01");
		assertEquals("01", entry.getCodeValue());
		assertEquals("LABEL01", entry.getCodeLabel());
		assertEquals(1, entry.getSortOrder());

		entry = codeStore.findByValue("CODE0", "02");
		assertEquals("02", entry.getCodeValue());
		assertEquals("LABEL02", entry.getCodeLabel());
		assertEquals(2, entry.getSortOrder());

		entry = codeStore.findByValue("CODE0", "03");
		assertEquals("03", entry.getCodeValue());
		assertEquals("LABEL03", entry.getCodeLabel());
		assertEquals(3, entry.getSortOrder());

		assertNull(codeStore.findByValue("CODE0", "04"));
		assertNull(codeStore.findByValue("CODE1", "01"));
	}

	@Test
	public void testGetCodeList() {

		List<ICodeEntry> list = codeStore.getCodeList("CODE0");
		assertEquals(3, list.size());
		for (int i = 1; i <= list.size(); i++) {
			ICodeEntry entry = list.get(i - 1);
			assertEquals("0" + i, entry.getCodeValue());
			assertEquals("LABEL0" + i, entry.getCodeLabel());
			assertEquals(i, entry.getSortOrder());
		}

		assertTrue(codeStore.getCodeList("CODE1").isEmpty());
	}

}
