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

import static java.util.Calendar.MILLISECOND;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
public class LocalDateTimeConverterTest {

	@Autowired
	private ConversionService cs;

	@Test
	public void testCanConvert() {
		assertThat(cs.canConvert(Timestamp.class, LocalDateTime.class), is(true));
		assertThat(cs.canConvert(LocalDateTime.class, Timestamp.class), is(true));
	}

	@Test
	public void testConvert() {
		LocalDateTime now = LocalDateTime.now();
		for (int i = -86400 * 16; i <= 86400 * 16; i += 10) {
			LocalDateTime ldt = now.plusSeconds(i);
			Calendar cal = Calendar.getInstance();
			cal.set(ldt.getYear(), ldt.getMonthValue() - 1, ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(),
					ldt.getSecond());
			cal.set(MILLISECOND, ldt.getNano() / 1_000_000);
			Timestamp ts = new Timestamp(cal.getTimeInMillis());
			assertThat(cs.convert(ts, LocalDateTime.class), is(ldt));
			assertThat(cs.convert(ldt, Timestamp.class), is(ts));
		}
	}

}
