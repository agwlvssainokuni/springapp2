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
public class ObjectListFromCsvResourceFactoryBeanTest {

	@Autowired
	@Qualifier("objectList")
	private List<?> list;

	@Test
	public void testObjectList() throws Exception {

		@SuppressWarnings("unchecked")
		List<Dto> objectList = (List<Dto>) list;

		assertNotNull(objectList);
		assertEquals(2, objectList.size());
		for (Dto dto : objectList) {
			assertNotNull(dto);
			assertTrue(dto.getFld1().matches("(あああ|ＢＢＢ)"));
			assertTrue(dto.getFld2() == 123 || dto.getFld2() == 456);
		}
	}

}
