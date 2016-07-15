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

package cherry.fundamental.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
public class NumberStyleFormatterEditorTest {

	@Value("#,##0")
	private NumberStyleFormatter numberStyleFormatter;

	@Value("#,##0.0")
	private NumberStyleFormatter numberStyleFormatter1;

	@Value("#,##0.00")
	private NumberStyleFormatter numberStyleFormatter2;

	@Value("#,##0.000")
	private NumberStyleFormatter numberStyleFormatter3;

	@Test
	public void testFormat() {
		Locale locale = LocaleContextHolder.getLocale();
		assertEquals("1,234,567", numberStyleFormatter.getNumberFormat(locale).format(1234567));
		assertEquals("1,234,567.0", numberStyleFormatter1.getNumberFormat(locale).format(1234567));
		assertEquals("1,234,567.00", numberStyleFormatter2.getNumberFormat(locale).format(1234567));
		assertEquals("1,234,567.000", numberStyleFormatter3.getNumberFormat(locale).format(1234567));
	}

	@Test
	public void testForNull() {
		NumberStyleFormatterEditor editor = new NumberStyleFormatterEditor();
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
		assertNull(editor.getValue());
	}

	@Test
	public void testForNNN() {
		NumberStyleFormatterEditor editor = new NumberStyleFormatterEditor();
		editor.setAsText("#,##0");
		assertEquals("#,##0", editor.getAsText());
		assertTrue(editor.getValue() instanceof NumberStyleFormatter);
	}

}
