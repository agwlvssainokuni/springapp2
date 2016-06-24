/*
 * Copyright 2016 agwlvssainokuni
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

package cherry.foundation.bizcal;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.core.convert.ConversionService;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;
import cherry.goods.util.JavaTimeUtil;

public class BizDateTimeBatch implements IBatch {

	public static final String ARG_SHOW = "show";
	public static final String ARG_SET = "set";
	public static final String ARG_TODAY = "today";
	public static final String ARG_CURRENT = "current";
	public static final String ARG_OFFSET = "offset";

	private final Log log = LogFactory.getLog(getClass());

	private BizDateTime bizDateTime;

	private BizDateTimeService bizDateTimeService;

	private ConversionService conversionService;

	private String showPatternToday = "TODAY: {0,date,yyyy/MM/dd}";

	private String showPatternNow = "  NOW: {0,date,yyyy/MM/dd HH:mm:ss.SSS}";

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setBizDateTimeService(BizDateTimeService bizDateTimeService) {
		this.bizDateTimeService = bizDateTimeService;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void setShowPatternToday(String showPatternToday) {
		this.showPatternToday = showPatternToday;
	}

	public void setShowPatternNow(String showPatternNow) {
		this.showPatternNow = showPatternNow;
	}

	@Override
	public ExitStatus execute(String... args) {
		if (args.length <= 0 || ARG_SHOW.equalsIgnoreCase(args[0])) {
			Calendar today = JavaTimeUtil.getCalendar(bizDateTime.today());
			Calendar now = JavaTimeUtil.getCalendar(bizDateTime.now());
			System.out.println(MessageFormat.format(showPatternToday, today.getTime()));
			System.out.println(MessageFormat.format(showPatternNow, now.getTime()));
			return ExitStatus.NORMAL;
		} else if (ARG_SET.equalsIgnoreCase(args[0]) && args.length >= 2) {
			LocalDate value = conversionService.convert(args[1], LocalDate.class);
			if (bizDateTimeService.doSet(value)) {
				logOk();
				return ExitStatus.NORMAL;
			} else {
				logNg();
				return ExitStatus.WARN;
			}
		} else if (ARG_TODAY.equalsIgnoreCase(args[0])) {
			int diff = (args.length < 2 ? 0 : parseDiff(args[1]));
			if (bizDateTimeService.doToday(diff)) {
				logOk();
				return ExitStatus.NORMAL;
			} else {
				logNg();
				return ExitStatus.WARN;
			}
		} else if (ARG_CURRENT.equalsIgnoreCase(args[0])) {
			int diff = (args.length < 2 ? 0 : parseDiff(args[1]));
			if (bizDateTimeService.doCurrent(diff)) {
				logOk();
				return ExitStatus.NORMAL;
			} else {
				logNg();
				return ExitStatus.WARN;
			}
		} else if (ARG_OFFSET.equalsIgnoreCase(args[0])) {
			int offsetD = (args.length < 2 ? 0 : parseDiff(args[1]));
			int offsetH = (args.length < 3 ? 0 : parseDiff(args[2]));
			int offsetM = (args.length < 4 ? 0 : parseDiff(args[3]));
			int offsetS = (args.length < 5 ? 0 : parseDiff(args[4]));
			if (bizDateTimeService.doOffset(offsetD, offsetH, offsetM, offsetS)) {
				logOk();
				return ExitStatus.NORMAL;
			} else {
				logNg();
				return ExitStatus.WARN;
			}
		} else {
			return ExitStatus.WARN;
		}
	}

	private int parseDiff(String arg) {
		if (arg.isEmpty()) {
			return 0;
		} else if (arg.charAt(0) == '+') {
			return conversionService.convert(arg.substring(1), Integer.class).intValue();
		} else {
			return conversionService.convert(arg, Integer.class).intValue();
		}
	}

	private void logOk() {
		if (log.isDebugEnabled()) {
			LocalDate today = bizDateTime.today();
			LocalDateTime now = bizDateTime.now();
			log.debug("updated. today={0}, now={1}", today.toString(), now.toString());
		}
	}

	private void logNg() {
		if (log.isDebugEnabled()) {
			log.debug("Failed to update");
		}
	}

}
