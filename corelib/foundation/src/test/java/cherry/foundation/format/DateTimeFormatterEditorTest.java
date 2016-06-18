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

package cherry.foundation.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.format.DateTimeFormatterEditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
public class DateTimeFormatterEditorTest {

	@Value("yyyy/MM/dd")
	private DateTimeFormatter dateTimeFormatter;

	@Test
	public void testFormat() {
		assertEquals("2015/01/23", dateTimeFormatter.format(LocalDate.of(2015, 1, 23)));
	}

	@Test
	public void testForNull() {
		DateTimeFormatterEditor editor = new DateTimeFormatterEditor();
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
		assertNull(editor.getValue());
	}

	@Test
	public void testForYYYYMMDD() {
		DateTimeFormatterEditor editor = new DateTimeFormatterEditor();
		editor.setAsText("yyyy/MM/dd");
		assertEquals("yyyy/MM/dd", editor.getAsText());
		assertTrue(editor.getValue() instanceof DateTimeFormatter);
	}

}
