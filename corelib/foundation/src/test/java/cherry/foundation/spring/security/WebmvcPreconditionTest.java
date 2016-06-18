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

package cherry.foundation.spring.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cherry.foundation.spring.webmvc.NotFoundException;
import cherry.foundation.spring.webmvc.WebmvcPrecondition;

public class WebmvcPreconditionTest {

	@Test
	public void testCheckNotNull_OK() {
		try {
			TestDto dto = new TestDto();
			WebmvcPrecondition.checkNotNull(dto, TestDto.class, 1, 2);
		} catch (NotFoundException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void testCheckNotNull_NG() {
		try {
			TestDto dto = null;
			WebmvcPrecondition.checkNotNull(dto, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (NotFoundException ex) {
			assertEquals("TestDto not found: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void testInstantiate() {
		try {
			new WebmvcPrecondition();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

	public static class TestDto {
	}

}
