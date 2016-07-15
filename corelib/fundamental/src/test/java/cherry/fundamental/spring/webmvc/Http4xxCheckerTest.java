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

package cherry.fundamental.spring.webmvc;

import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwBadRequestIfNull;
import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwBadRequestIfTrue;
import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwNotFoundIfAbsent;
import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwNotFoundIfNull;
import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwNotFoundIfTrue;
import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwUnauthorizedIfNull;
import static cherry.fundamental.spring.webmvc.Http4xxChecker.throwUnauthorizedIfTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

public class Http4xxCheckerTest {

	@Test
	public void throwBadRequestIfNull_OK() {
		try {
			TestDto dto = new TestDto();
			throwBadRequestIfNull(dto, TestDto.class, 1, 2);
		} catch (BadRequestException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwBadRequestIfNull_NG() {
		try {
			TestDto dto = null;
			throwBadRequestIfNull(dto, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (BadRequestException ex) {
			assertEquals("TestDto bad request: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void throwBadRequestIfTrue_OK() {
		try {
			TestDto dto = new TestDto();
			throwBadRequestIfTrue(dto == null, TestDto.class, 1, 2);
		} catch (BadRequestException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwBadRequestIfTrue_NG() {
		try {
			TestDto dto = null;
			throwBadRequestIfTrue(dto == null, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (BadRequestException ex) {
			assertEquals("TestDto bad request: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void throwUnauthorizedIfNull_OK() {
		try {
			TestDto dto = new TestDto();
			throwUnauthorizedIfNull(dto, TestDto.class, 1, 2);
		} catch (UnauthorizedException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwUnauthorizedIfNull_NG() {
		try {
			TestDto dto = null;
			throwUnauthorizedIfNull(dto, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (UnauthorizedException ex) {
			assertEquals("TestDto unauthorized: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void throwUnauthorizedIfTrue_OK() {
		try {
			TestDto dto = new TestDto();
			throwUnauthorizedIfTrue(dto == null, TestDto.class, 1, 2);
		} catch (UnauthorizedException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwUnauthorizedIfTrue_NG() {
		try {
			TestDto dto = null;
			throwUnauthorizedIfTrue(dto == null, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (UnauthorizedException ex) {
			assertEquals("TestDto unauthorized: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void throwNotFoundIfNull_OK() {
		try {
			TestDto dto = new TestDto();
			throwNotFoundIfNull(dto, TestDto.class, 1, 2);
		} catch (NotFoundException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwNotFoundIfNull_NG() {
		try {
			TestDto dto = null;
			throwNotFoundIfNull(dto, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (NotFoundException ex) {
			assertEquals("TestDto not found: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void throwNotFoundIfAbsent_OK() {
		try {
			TestDto dto = new TestDto();
			throwNotFoundIfAbsent(Optional.ofNullable(dto), TestDto.class, 1, 2);
		} catch (NotFoundException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwNotFoundIfAbsent_NG() {
		try {
			TestDto dto = null;
			throwNotFoundIfAbsent(Optional.ofNullable(dto), TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (NotFoundException ex) {
			assertEquals("TestDto not found: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void throwNotFoundIfTrue_OK() {
		try {
			TestDto dto = new TestDto();
			throwNotFoundIfTrue(dto == null, TestDto.class, 1, 2);
		} catch (NotFoundException ex) {
			fail("Exception must not be thrown");
		}
	}

	@Test
	public void throwNotFoundIfTrue_NG() {
		try {
			TestDto dto = null;
			throwNotFoundIfTrue(dto == null, TestDto.class, 1, 2);
			fail("Exception must be thrown");
		} catch (NotFoundException ex) {
			assertEquals("TestDto not found: 1, 2", ex.getMessage());
		}
	}

	@Test
	public void testInstantiate() {
		try {
			new Http4xxChecker();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

	public static class TestDto {
	}

}
