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

package cherry.fundamental.crypto;

import static cherry.fundamental.crypto.SecureString.cryptoValueOf;
import static cherry.fundamental.crypto.SecureString.plainValueOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.fundamental.crypto.SecureType.Encoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
public class SecureStringTest {

	@Test
	public void testRandomTest() {
		for (int i = 0; i < 100; i++) {
			String plain = RandomStringUtils.randomAlphanumeric(1024);
			SecureString sec0 = plainValueOf(plain);
			SecureString sec1 = cryptoValueOf(sec0.crypto());
			assertThat(sec1.plain(), is(plain));
		}
	}

	@Test
	public void testToString() {
		SecureString sec = plainValueOf("ABC");
		assertThat(sec.toString(), is("SecureString[ABC]"));
	}

	@Test
	public void testNoneEncoder() {
		Encoder<String> encoder = new SecureString.NoneEncoder();
		assertThat(encoder.encode(null), is(nullValue()));
		assertThat(encoder.encode("ABC"), is("ABC"));
		assertThat(encoder.decode(null), is(nullValue()));
		assertThat(encoder.decode("ABC"), is("ABC"));
	}

}
