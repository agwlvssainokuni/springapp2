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

package cherry.fundamental.bizcal;

import static cherry.fundamental.bizcal.BizYearUtil.between;

import java.time.LocalDate;

import org.apache.commons.lang3.Range;

public class SimpleBizYearStrategy implements BizYearStrategy {

	private int yearOfFirstOffset;

	private int monthOfFirst;

	private int dayOfFirst;

	public void setYearOfFirstOffset(int yearOfFirstOffset) {
		this.yearOfFirstOffset = yearOfFirstOffset;
	}

	public void setMonthOfFirst(int monthOfFirst) {
		this.monthOfFirst = monthOfFirst;
	}

	public void setDayOfFirst(int dayOfFirst) {
		this.dayOfFirst = dayOfFirst;
	}

	@Override
	public Range<LocalDate> rangeOfBizYear(int bizYear) {
		LocalDate firstDate = LocalDate.of(bizYear + yearOfFirstOffset, monthOfFirst, dayOfFirst);
		LocalDate lastDate = firstDate.plusYears(1).minusDays(1);
		return between(firstDate, lastDate);
	}

}
