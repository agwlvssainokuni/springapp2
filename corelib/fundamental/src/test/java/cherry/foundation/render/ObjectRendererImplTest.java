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

package cherry.foundation.render;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
public class ObjectRendererImplTest {

	@Autowired
	private ObjectRenderer objectRenderer;

	@Test
	public void testNull() {
		assertEquals("", objectRenderer.render(null, null));
	}

	@Test
	public void testNumberWithoutFormat() {
		ObjectRenderer renderer = new ObjectRendererImpl();
		assertEquals("1,234,567", renderer.render(1234567L, null));
		assertEquals("1,234,567", renderer.render(1234567.0, null));
		assertEquals("1,234,567.1", renderer.render(1234567.1, null));
		assertEquals("1,234,567.12", renderer.render(1234567.12, null));
		assertEquals("1,234,567.123", renderer.render(1234567.123, null));
		assertEquals("1,234,567.123", renderer.render(1234567.1234, null));
	}

	@Test
	public void testNumberWithNull() {
		assertEquals("1,234,567", objectRenderer.render(1234567L, null));
		assertEquals("1,234,567", objectRenderer.render(1234567.0, null));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, null));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, null));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, null));
		assertEquals("1,234,567.1234", objectRenderer.render(1234567.1234, null));
		assertEquals("1,234,567.12345", objectRenderer.render(1234567.12345, null));
		assertEquals("1,234,567.123456", objectRenderer.render(1234567.123456, null));
		assertEquals("1,234,567.1234567", objectRenderer.render(1234567.1234567, null));
		assertEquals("1,234,567.12345678", objectRenderer.render(1234567.12345678, null));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.123456789, null));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.1234567891, null));
	}

	@Test
	public void testNumberWithMinus1() {
		assertEquals("1,234,567", objectRenderer.render(1234567L, -1));
		assertEquals("1,234,567", objectRenderer.render(1234567.0, -1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, -1));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, -1));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, -1));
		assertEquals("1,234,567.1234", objectRenderer.render(1234567.1234, -1));
		assertEquals("1,234,567.12345", objectRenderer.render(1234567.12345, -1));
		assertEquals("1,234,567.123456", objectRenderer.render(1234567.123456, -1));
		assertEquals("1,234,567.1234567", objectRenderer.render(1234567.1234567, -1));
		assertEquals("1,234,567.12345678", objectRenderer.render(1234567.12345678, -1));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.123456789, -1));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.1234567891, -1));
	}

	@Test
	public void testNumberWith0() {
		assertEquals("1,234,567", objectRenderer.render(1234567L, 0));
		assertEquals("1,234,567", objectRenderer.render(1234567.0, 0));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, 0));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, 0));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, 0));
		assertEquals("1,234,567.1234", objectRenderer.render(1234567.1234, 0));
		assertEquals("1,234,567.12345", objectRenderer.render(1234567.12345, 0));
		assertEquals("1,234,567.123456", objectRenderer.render(1234567.123456, 0));
		assertEquals("1,234,567.1234567", objectRenderer.render(1234567.1234567, 0));
		assertEquals("1,234,567.12345678", objectRenderer.render(1234567.12345678, 0));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.123456789, 0));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.1234567891, 0));
	}

	@Test
	public void testNumberWith1() {
		assertEquals("1,234,567.0", objectRenderer.render(1234567L, 1));
		assertEquals("1,234,567.0", objectRenderer.render(1234567.0, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.12, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.123, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1234, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.12345, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.123456, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1234567, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.12345678, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.123456789, 1));
		assertEquals("1,234,567.1", objectRenderer.render(1234567.1234567891, 1));
	}

	@Test
	public void testNumberWith2() {
		assertEquals("1,234,567.00", objectRenderer.render(1234567L, 2));
		assertEquals("1,234,567.00", objectRenderer.render(1234567.0, 2));
		assertEquals("1,234,567.10", objectRenderer.render(1234567.1, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.123, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.1234, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12345, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.123456, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.1234567, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.12345678, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.123456789, 2));
		assertEquals("1,234,567.12", objectRenderer.render(1234567.1234567891, 2));
	}

	@Test
	public void testNumberWith3() {
		assertEquals("1,234,567.000", objectRenderer.render(1234567L, 3));
		assertEquals("1,234,567.000", objectRenderer.render(1234567.0, 3));
		assertEquals("1,234,567.100", objectRenderer.render(1234567.1, 3));
		assertEquals("1,234,567.120", objectRenderer.render(1234567.12, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.12345, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123456, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234567, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.12345678, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.123456789, 3));
		assertEquals("1,234,567.123", objectRenderer.render(1234567.1234567891, 3));
	}

	@Test
	public void testNumberWith4() {
		assertEquals("1,234,567.0000", objectRenderer.render(1234567L, 4));
		assertEquals("1,234,567.0000", objectRenderer.render(1234567.0, 4));
		assertEquals("1,234,567.1000", objectRenderer.render(1234567.1, 4));
		assertEquals("1,234,567.1200", objectRenderer.render(1234567.12, 4));
		assertEquals("1,234,567.1230", objectRenderer.render(1234567.123, 4));
		assertEquals("1,234,567.1234", objectRenderer.render(1234567.1234, 4));
		assertEquals("1,234,567.1235", objectRenderer.render(1234567.12345, 4));
		assertEquals("1,234,567.1235", objectRenderer.render(1234567.123456, 4));
		assertEquals("1,234,567.1235", objectRenderer.render(1234567.1234567, 4));
		assertEquals("1,234,567.1235", objectRenderer.render(1234567.12345678, 4));
		assertEquals("1,234,567.1235", objectRenderer.render(1234567.123456789, 4));
		assertEquals("1,234,567.1235", objectRenderer.render(1234567.1234567891, 4));
	}

	@Test
	public void testNumberWith5() {
		assertEquals("1,234,567.00000", objectRenderer.render(1234567L, 5));
		assertEquals("1,234,567.00000", objectRenderer.render(1234567.0, 5));
		assertEquals("1,234,567.10000", objectRenderer.render(1234567.1, 5));
		assertEquals("1,234,567.12000", objectRenderer.render(1234567.12, 5));
		assertEquals("1,234,567.12300", objectRenderer.render(1234567.123, 5));
		assertEquals("1,234,567.12340", objectRenderer.render(1234567.1234, 5));
		assertEquals("1,234,567.12345", objectRenderer.render(1234567.12345, 5));
		assertEquals("1,234,567.12346", objectRenderer.render(1234567.123456, 5));
		assertEquals("1,234,567.12346", objectRenderer.render(1234567.1234567, 5));
		assertEquals("1,234,567.12346", objectRenderer.render(1234567.12345678, 5));
		assertEquals("1,234,567.12346", objectRenderer.render(1234567.123456789, 5));
		assertEquals("1,234,567.12346", objectRenderer.render(1234567.1234567891, 5));
	}

	@Test
	public void testNumberWith6() {
		assertEquals("1,234,567.000000", objectRenderer.render(1234567L, 6));
		assertEquals("1,234,567.000000", objectRenderer.render(1234567.0, 6));
		assertEquals("1,234,567.100000", objectRenderer.render(1234567.1, 6));
		assertEquals("1,234,567.120000", objectRenderer.render(1234567.12, 6));
		assertEquals("1,234,567.123000", objectRenderer.render(1234567.123, 6));
		assertEquals("1,234,567.123400", objectRenderer.render(1234567.1234, 6));
		assertEquals("1,234,567.123450", objectRenderer.render(1234567.12345, 6));
		assertEquals("1,234,567.123456", objectRenderer.render(1234567.123456, 6));
		assertEquals("1,234,567.123457", objectRenderer.render(1234567.1234567, 6));
		assertEquals("1,234,567.123457", objectRenderer.render(1234567.12345678, 6));
		assertEquals("1,234,567.123457", objectRenderer.render(1234567.123456789, 6));
		assertEquals("1,234,567.123457", objectRenderer.render(1234567.1234567891, 6));
	}

	@Test
	public void testNumberWith7() {
		assertEquals("1,234,567.0000000", objectRenderer.render(1234567L, 7));
		assertEquals("1,234,567.0000000", objectRenderer.render(1234567.0, 7));
		assertEquals("1,234,567.1000000", objectRenderer.render(1234567.1, 7));
		assertEquals("1,234,567.1200000", objectRenderer.render(1234567.12, 7));
		assertEquals("1,234,567.1230000", objectRenderer.render(1234567.123, 7));
		assertEquals("1,234,567.1234000", objectRenderer.render(1234567.1234, 7));
		assertEquals("1,234,567.1234500", objectRenderer.render(1234567.12345, 7));
		assertEquals("1,234,567.1234560", objectRenderer.render(1234567.123456, 7));
		assertEquals("1,234,567.1234567", objectRenderer.render(1234567.1234567, 7));
		assertEquals("1,234,567.1234568", objectRenderer.render(1234567.12345678, 7));
		assertEquals("1,234,567.1234568", objectRenderer.render(1234567.123456789, 7));
		assertEquals("1,234,567.1234568", objectRenderer.render(1234567.1234567891, 7));
	}

	@Test
	public void testNumberWith8() {
		assertEquals("1,234,567.00000000", objectRenderer.render(1234567L, 8));
		assertEquals("1,234,567.00000000", objectRenderer.render(1234567.0, 8));
		assertEquals("1,234,567.10000000", objectRenderer.render(1234567.1, 8));
		assertEquals("1,234,567.12000000", objectRenderer.render(1234567.12, 8));
		assertEquals("1,234,567.12300000", objectRenderer.render(1234567.123, 8));
		assertEquals("1,234,567.12340000", objectRenderer.render(1234567.1234, 8));
		assertEquals("1,234,567.12345000", objectRenderer.render(1234567.12345, 8));
		assertEquals("1,234,567.12345600", objectRenderer.render(1234567.123456, 8));
		assertEquals("1,234,567.12345670", objectRenderer.render(1234567.1234567, 8));
		assertEquals("1,234,567.12345678", objectRenderer.render(1234567.12345678, 8));
		assertEquals("1,234,567.12345679", objectRenderer.render(1234567.123456789, 8));
		assertEquals("1,234,567.12345679", objectRenderer.render(1234567.1234567891, 8));
	}

	@Test
	public void testNumberWith9() {
		assertEquals("1,234,567.000000000", objectRenderer.render(1234567L, 9));
		assertEquals("1,234,567.000000000", objectRenderer.render(1234567.0, 9));
		assertEquals("1,234,567.100000000", objectRenderer.render(1234567.1, 9));
		assertEquals("1,234,567.120000000", objectRenderer.render(1234567.12, 9));
		assertEquals("1,234,567.123000000", objectRenderer.render(1234567.123, 9));
		assertEquals("1,234,567.123400000", objectRenderer.render(1234567.1234, 9));
		assertEquals("1,234,567.123450000", objectRenderer.render(1234567.12345, 9));
		assertEquals("1,234,567.123456000", objectRenderer.render(1234567.123456, 9));
		assertEquals("1,234,567.123456700", objectRenderer.render(1234567.1234567, 9));
		assertEquals("1,234,567.123456780", objectRenderer.render(1234567.12345678, 9));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.123456789, 9));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.1234567891, 9));
	}

	@Test
	public void testNumberWith10() {
		assertEquals("1,234,567.000000000", objectRenderer.render(1234567L, 10));
		assertEquals("1,234,567.000000000", objectRenderer.render(1234567.0, 10));
		assertEquals("1,234,567.100000000", objectRenderer.render(1234567.1, 10));
		assertEquals("1,234,567.120000000", objectRenderer.render(1234567.12, 10));
		assertEquals("1,234,567.123000000", objectRenderer.render(1234567.123, 10));
		assertEquals("1,234,567.123400000", objectRenderer.render(1234567.1234, 10));
		assertEquals("1,234,567.123450000", objectRenderer.render(1234567.12345, 10));
		assertEquals("1,234,567.123456000", objectRenderer.render(1234567.123456, 10));
		assertEquals("1,234,567.123456700", objectRenderer.render(1234567.1234567, 10));
		assertEquals("1,234,567.123456780", objectRenderer.render(1234567.12345678, 10));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.123456789, 10));
		assertEquals("1,234,567.123456789", objectRenderer.render(1234567.1234567891, 10));
	}

	@Test
	public void testLocalDate() {
		assertEquals("2015/01/23", objectRenderer.render(LocalDate.of(2015, 1, 23), null));
	}

	@Test
	public void testLocalTime() {
		assertEquals("12:34:56", objectRenderer.render(LocalTime.of(12, 34, 56), null));
	}

	@Test
	public void testLocalDateTime() {
		assertEquals("2015/01/23 12:34:56", objectRenderer.render(LocalDateTime.of(2015, 1, 23, 12, 34, 56), null));
	}

	@Test
	public void testString() {
		assertEquals("TEST", objectRenderer.render("TEST", null));
	}

	@Test
	public void testDto() {
		Dto dto = new Dto();
		dto.setKey("KEY");
		dto.setValue("VALUE");
		assertEquals("ObjectRendererImplTest.Dto[key=KEY,value=VALUE]", objectRenderer.render(dto, null));
	}

	public static class Dto {

		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

}
