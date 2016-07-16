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

package cherry.fundamental.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cherry.elemental.code.ICodeType;

public class CodeManagerImplTest {

	public enum CodeType implements ICodeType<String> {
		NONE, CODE0, CODE1;
		@Override
		public String getCodeValue() {
			return name();
		}
	}

	@Test
	public void testIsValidValue_byENUM() {
		CodeManagerImpl impl = create();
		assertFalse(impl.isValidValue(CodeType.NONE, "01"));
		assertTrue(impl.isValidValue(CodeType.CODE0, "01"));
		assertTrue(impl.isValidValue(CodeType.CODE0, "02"));
		assertFalse(impl.isValidValue(CodeType.CODE0, "03"));
	}

	@Test
	public void testIsValidValue_btNAME() {
		CodeManagerImpl impl = create();
		assertFalse(impl.isValidValue("NONE", "01"));
		assertTrue(impl.isValidValue("CODE0", "01"));
		assertTrue(impl.isValidValue("CODE0", "02"));
		assertFalse(impl.isValidValue("CODE0", "03"));
	}

	@Test
	public void testFindByValue_byENUM() {

		CodeManagerImpl impl = create();
		try {
			impl.findByValue(CodeType.NONE, "01");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (NONE, 01)", ex.getMessage());
		}

		ICodeEntry entry01 = impl.findByValue(CodeType.CODE0, "01");
		assertEquals("01", entry01.getCodeValue());
		assertEquals("01 - LABEL01", entry01.getCodeLabel());
		assertEquals(0, entry01.getSortOrder());
		ICodeEntry entry01p = impl.findByValue(CodeType.CODE0, "01", true);
		assertEquals("01", entry01p.getCodeValue());
		assertEquals("LABEL01", entry01p.getCodeLabel());
		assertEquals(0, entry01p.getSortOrder());

		ICodeEntry entry02 = impl.findByValue(CodeType.CODE0, "02");
		assertEquals("02", entry02.getCodeValue());
		assertEquals("02 - LABEL02", entry02.getCodeLabel());
		assertEquals(0, entry02.getSortOrder());
		ICodeEntry entry02p = impl.findByValue(CodeType.CODE0, "02", true);
		assertEquals("02", entry02p.getCodeValue());
		assertEquals("LABEL02", entry02p.getCodeLabel());
		assertEquals(0, entry02p.getSortOrder());

		try {
			impl.findByValue(CodeType.CODE0, "03");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (CODE0, 03)", ex.getMessage());
		}
	}

	@Test
	public void testFindByValue_byNAME() {

		CodeManagerImpl impl = create();
		try {
			impl.findByValue("NONE", "01");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (NONE, 01)", ex.getMessage());
		}

		ICodeEntry entry01 = impl.findByValue("CODE0", "01");
		assertEquals("01", entry01.getCodeValue());
		assertEquals("01 - LABEL01", entry01.getCodeLabel());
		assertEquals(0, entry01.getSortOrder());
		ICodeEntry entry01p = impl.findByValue("CODE0", "01", true);
		assertEquals("01", entry01p.getCodeValue());
		assertEquals("LABEL01", entry01p.getCodeLabel());
		assertEquals(0, entry01p.getSortOrder());

		ICodeEntry entry02 = impl.findByValue("CODE0", "02");
		assertEquals("02", entry02.getCodeValue());
		assertEquals("02 - LABEL02", entry02.getCodeLabel());
		assertEquals(0, entry02.getSortOrder());
		ICodeEntry entry02p = impl.findByValue("CODE0", "02", true);
		assertEquals("02", entry02p.getCodeValue());
		assertEquals("LABEL02", entry02p.getCodeLabel());
		assertEquals(0, entry02p.getSortOrder());

		try {
			impl.findByValue("CODE0", "03");
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			assertEquals("Not defined for (CODE0, 03)", ex.getMessage());
		}
	}

	@Test
	public void testGetCodeList_byENUM() {

		CodeManagerImpl impl = create();
		List<ICodeEntry> code0 = impl.getCodeList(CodeType.CODE0);
		List<ICodeEntry> code0p = impl.getCodeList(CodeType.CODE0, true);

		ICodeEntry entry01 = code0.get(0);
		assertEquals("01", entry01.getCodeValue());
		assertEquals("01 - LABEL01", entry01.getCodeLabel());
		assertEquals(0, entry01.getSortOrder());
		ICodeEntry entry01p = code0p.get(0);
		assertEquals("01", entry01p.getCodeValue());
		assertEquals("LABEL01", entry01p.getCodeLabel());
		assertEquals(0, entry01p.getSortOrder());

		ICodeEntry entry02 = code0.get(1);
		assertEquals("02", entry02.getCodeValue());
		assertEquals("02 - LABEL02", entry02.getCodeLabel());
		assertEquals(0, entry02.getSortOrder());
		ICodeEntry entry02p = code0p.get(1);
		assertEquals("02", entry02p.getCodeValue());
		assertEquals("LABEL02", entry02p.getCodeLabel());
		assertEquals(0, entry02p.getSortOrder());
	}

	@Test
	public void testGetCodeList_byNAME() {

		CodeManagerImpl impl = create();
		List<ICodeEntry> code0 = impl.getCodeList("CODE0");
		List<ICodeEntry> code0p = impl.getCodeList("CODE0", true);

		ICodeEntry entry01 = code0.get(0);
		assertEquals("01", entry01.getCodeValue());
		assertEquals("01 - LABEL01", entry01.getCodeLabel());
		assertEquals(0, entry01.getSortOrder());
		ICodeEntry entry01p = code0p.get(0);
		assertEquals("01", entry01p.getCodeValue());
		assertEquals("LABEL01", entry01p.getCodeLabel());
		assertEquals(0, entry01p.getSortOrder());

		ICodeEntry entry02 = code0.get(1);
		assertEquals("02", entry02.getCodeValue());
		assertEquals("02 - LABEL02", entry02.getCodeLabel());
		assertEquals(0, entry02.getSortOrder());
		ICodeEntry entry02p = code0p.get(1);
		assertEquals("02", entry02p.getCodeValue());
		assertEquals("LABEL02", entry02p.getCodeLabel());
		assertEquals(0, entry02p.getSortOrder());
	}

	@Test
	public void testGetCodeMap_byENUM() {

		CodeManagerImpl impl = create();
		Map<String, String> code0 = impl.getCodeMap(CodeType.CODE0);
		Map<String, String> code0p = impl.getCodeMap(CodeType.CODE0, true);

		assertEquals("01 - LABEL01", code0.get("01"));
		assertEquals("LABEL01", code0p.get("01"));
		assertEquals("02 - LABEL02", code0.get("02"));
		assertEquals("LABEL02", code0p.get("02"));
	}

	@Test
	public void testGetCodeMap_byNAME() {

		CodeManagerImpl impl = create();
		Map<String, String> code0 = impl.getCodeMap("CODE0");
		Map<String, String> code0p = impl.getCodeMap("CODE0", true);

		assertEquals("01 - LABEL01", code0.get("01"));
		assertEquals("LABEL01", code0p.get("01"));
		assertEquals("02 - LABEL02", code0.get("02"));
		assertEquals("LABEL02", code0p.get("02"));
	}

	private CodeManagerImpl create() {
		Map<String, String> code0 = new LinkedHashMap<>();
		code0.put("01", "LABEL01");
		code0.put("02", "LABEL02");
		Map<String, String> code1 = new LinkedHashMap<>();
		code1.put("11", "LABEL11");
		code1.put("12", "LABEL12");
		code1.put("13", "LABEL13");
		Map<String, Map<String, String>> map = new LinkedHashMap<>();
		map.put("CODE0", code0);
		map.put("CODE1", code1);
		SimpleCodeStore codeStore = new SimpleCodeStore();
		codeStore.setCodeDefMap(map);
		CodeManagerImpl impl = new CodeManagerImpl();
		impl.setValueTemplate("{0} - {1}");
		impl.setCodeStore(codeStore);
		return impl;
	}
}
