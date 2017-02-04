/*
 * Copyright 2017 agwlvssainokuni
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

package cherry.fundamental.spring.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring/appctx-fundamental-test.xml",
		"classpath:cherry/fundamental/spring/support/appctx-fundamental-test-springsupport.xml" })
public class MapListFromCsvResourceFactoryBeanTest {

	@Autowired
	@Qualifier("mapList")
	private List<?> list;

	@Test
	public void testMapList() throws Exception {

		@SuppressWarnings("unchecked")
		List<Map<String, String>> mapList = (List<Map<String, String>>) list;

		assertNotNull(mapList);
		assertEquals(2, mapList.size());
		for (Map<String, String> m : mapList) {
			assertNotNull(m);
			assertEquals(2, m.size());
			assertTrue(m.containsKey("fld1"));
			assertTrue(m.containsKey("fld2"));
			assertTrue(m.get("fld1").matches("(あああ|ＢＢＢ)"));
			assertTrue(m.get("fld2").matches("(123|456)"));
		}
	}

}
