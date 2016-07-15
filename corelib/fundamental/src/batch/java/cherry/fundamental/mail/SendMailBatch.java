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

import java.io.File;

import cherry.elemental.log.Log;
import cherry.elemental.log.LogFactory;
import cherry.fundamental.batch.ExitStatus;
import cherry.fundamental.batch.IBatch;

public class SendMailBatch implements IBatch {

	private final Log log = LogFactory.getLog(getClass());

	private SendMailService sendMailService;

	private long intervalMillis;

	private File shutdownTrigger;

	public void setSendMailService(SendMailService sendMailService) {
		this.sendMailService = sendMailService;
	}

	public void setIntervalMillis(long intervalMillis) {
		this.intervalMillis = intervalMillis;
	}

	public void setShutdownTrigger(File shutdownTrigger) {
		this.shutdownTrigger = shutdownTrigger;
	}

	@Override
	public ExitStatus execute(String... args) {
		sendMailService.sendMail();
		while (!shutdownTrigger.exists()) {
			sleep();
			sendMailService.sendMail();
		}
		deleteShutdownTrigger();
		return ExitStatus.NORMAL;
	}

	private void sleep() {
		try {
			Thread.sleep(intervalMillis);
		} catch (InterruptedException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Interrupted");
			}
		}
	}

	private void deleteShutdownTrigger() {
		if (log.isDebugEnabled()) {
			log.debug("Deleting shutdownTrigger: {0}", shutdownTrigger.getAbsolutePath());
		}
		boolean result = shutdownTrigger.delete();
		if (log.isDebugEnabled()) {
			if (result) {
				log.debug("Deleted shutdownTrigger");
			} else {
				log.debug("Failed to delete shutdownTrigger");
			}
		}
	}

}
