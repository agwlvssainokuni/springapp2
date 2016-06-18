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

import static java.util.Calendar.MILLISECOND;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.icu.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
public class LocalDateConverterTest {

	@Autowired
	private ConversionService cs;

	@Test
	public void testCanConvert() {
		assertThat(cs.canConvert(Date.class, LocalDate.class), is(true));
		assertThat(cs.canConvert(LocalDate.class, Date.class), is(true));
	}

	@Test
	public void testConvert() {
		LocalDate now = LocalDate.now();
		for (int i = -1024; i <= 1024; i++) {
			LocalDate ld = now.plusDays(i);
			Calendar cal = Calendar.getInstance();
			cal.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth(), 0, 0, 0);
			cal.set(MILLISECOND, 0);
			Date date = new Date(cal.getTimeInMillis());
			assertThat(cs.convert(date, LocalDate.class), is(ld));
			assertThat(cs.convert(ld, Date.class), is(date));
		}
	}

}
