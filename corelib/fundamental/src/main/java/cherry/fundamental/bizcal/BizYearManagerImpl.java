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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;

public class BizYearManagerImpl implements BizYearManager {

	private BizDateTime bizDateTime;

	private BizYearStrategy bizYearStrategy;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setBizYearStrategy(BizYearStrategy bizYearStore) {
		this.bizYearStrategy = bizYearStore;
	}

	@Override
	public int getBizYear(LocalDate dt) {
		return bizYearByDate(dt).getLeft().intValue();
	}

	@Override
	public int getBizYear() {
		return getBizYear(bizDateTime.today());
	}

	@Override
	public LocalDate getFirstOfBizYear(int bizYear) {
		return bizYearStrategy.rangeOfBizYear(bizYear).getMinimum();
	}

	@Override
	public LocalDate getFirstOfBizYear(LocalDate dt) {
		return bizYearByDate(dt).getRight().getMinimum();
	}

	@Override
	public LocalDate getFirstOfBizYear() {
		return getFirstOfBizYear(bizDateTime.today());
	}

	@Override
	public LocalDate getLastOfBizYear(int bizYear) {
		return bizYearStrategy.rangeOfBizYear(bizYear).getMaximum();
	}

	@Override
	public LocalDate getLastOfBizYear(LocalDate dt) {
		return bizYearByDate(dt).getRight().getMaximum();
	}

	@Override
	public LocalDate getLastOfBizYear() {
		return getLastOfBizYear(bizDateTime.today());
	}

	@Override
	public int getNthDayOfBizYear(LocalDate dt) {
		Range<LocalDate> range = bizYearByDate(dt).getRight();
		return (int) range.getMinimum().until(dt.plusDays(1), ChronoUnit.DAYS);
	}

	@Override
	public int getNthDayOfBizYear() {
		return getNthDayOfBizYear(bizDateTime.today());
	}

	@Override
	public int getNumberOfDaysOfBizYear(int bizYear) {
		Range<LocalDate> range = bizYearStrategy.rangeOfBizYear(bizYear);
		return (int) range.getMinimum().until(range.getMaximum().plusDays(1), ChronoUnit.DAYS);
	}

	@Override
	public int getNumberOfDaysOfBizYear(LocalDate dt) {
		Range<LocalDate> range = bizYearByDate(dt).getRight();
		return (int) range.getMinimum().until(range.getMaximum().plusDays(1), ChronoUnit.DAYS);
	}

	@Override
	public int getNumberOfDaysOfBizYear() {
		return getNumberOfDaysOfBizYear(bizDateTime.today());
	}

	private Pair<Integer, Range<LocalDate>> bizYearByDate(LocalDate dt) {
		return bizYearByDate(dt.getYear(), dt);
	}

	private Pair<Integer, Range<LocalDate>> bizYearByDate(int year, LocalDate dt) {
		Range<LocalDate> range = bizYearStrategy.rangeOfBizYear(year);
		if (range.isAfter(dt)) {
			return bizYearByDate(year - 1, dt);
		}
		if (range.isBefore(dt)) {
			return bizYearByDate(year + 1, dt);
		}
		return Pair.of(year, range);
	}

}
