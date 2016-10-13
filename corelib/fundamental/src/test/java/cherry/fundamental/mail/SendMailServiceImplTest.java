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

package cherry.fundamental.mail;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import org.junit.Test;

import cherry.fundamental.bizcal.BizDateTime;

import com.google.common.util.concurrent.RateLimiter;

public class SendMailServiceImplTest {

	private BizDateTime bizDateTime;

	private MailSendHandler mailSendHandler;

	@Test
	public void testSendMail_with_RateLimit() {
		testSendMail(1.0D);
	}

	@Test
	public void testSendMail_without_RateLimit() {
		testSendMail(null);
	}

	private void testSendMail(Double sendPerSecond) {

		SendMailService impl = create(sendPerSecond);

		LocalDateTime now = LocalDateTime.now();
		when(bizDateTime.now()).thenReturn(now);
		when(mailSendHandler.listMessage(any(LocalDateTime.class))).thenReturn(asList(1L, 2L, 3L, 4L, 5L));
		when(mailSendHandler.sendMessage(anyLong())).thenReturn(true);

		long start = System.currentTimeMillis();
		impl.sendMail();
		long end = System.currentTimeMillis();
		System.out.println(MessageFormat.format("Time elapsed {0} ms", end - start));

		verify(bizDateTime, times(1)).now();
		verify(mailSendHandler, times(1)).listMessage(eq(now));
		for (long l = 1L; l <= 5L; l++) {
			verify(mailSendHandler, times(1)).sendMessage(eq(l));
		}
	}

	@Test
	public void testRateLimiter_with_Reinitialize() throws InterruptedException {
		testRateLimiter(true);
	}

	@Test
	public void testRateLimiter_without_Reinitialize() throws InterruptedException {
		testRateLimiter(false);
	}

	private void testRateLimiter(boolean reinitialize) throws InterruptedException {
		RateLimiter rateLimiter = RateLimiter.create(10.0D);
		for (int i = 0; i < 50; i++) {
			if (i > 0 && i % 10 == 0) {
				Thread.sleep(1000L);
				if (reinitialize) {
					rateLimiter = RateLimiter.create(10.0D);
				}
			}
			rateLimiter.acquire();
			System.out.println(MessageFormat.format("{0,number,0000}: {1}", i, LocalDateTime.now()));
		}
	}

	private SendMailServiceImpl create(Double sendPerSecond) {
		bizDateTime = mock(BizDateTime.class);
		mailSendHandler = mock(MailSendHandler.class);
		SendMailServiceImpl impl = new SendMailServiceImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setMailSendHandler(mailSendHandler);
		impl.setSendPerSecond(sendPerSecond);
		return impl;
	}

}
