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

import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;

import cherry.elemental.log.Log;
import cherry.elemental.log.LogFactory;
import cherry.fundamental.bizcal.BizDateTime;

import com.google.common.util.concurrent.RateLimiter;

public class SendMailServiceImpl implements SendMailService {

	private final Log log = LogFactory.getLog(getClass());

	private BizDateTime bizDateTime;

	private MailSendHandler mailSendHandler;

	private RateLimiter rateLimiter;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setMailSendHandler(MailSendHandler mailSendHandler) {
		this.mailSendHandler = mailSendHandler;
	}

	public void setRateLimiter(RateLimiter rateLimiter) {
		this.rateLimiter = rateLimiter;
	}

	@Override
	public void sendMail() {
		try {

			LocalDateTime now = bizDateTime.now();
			for (long messageId : mailSendHandler.listMessage(now)) {

				if (rateLimiter != null) {
					rateLimiter.acquire();
				}

				mailSendHandler.sendMessage(messageId);
			}
		} catch (MailException | DataAccessException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "failed to send mail");
			}
		}
	}

}
