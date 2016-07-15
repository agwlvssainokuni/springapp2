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

package cherry.foundation.bizcal;

import java.time.LocalDate;

import cherry.foundation.type.ICodeType;

/**
 * 営業日管理機能。<br />
 */
public class WorkdayManagerImpl implements WorkdayManager {

	private WorkdayStore workdayStore;

	private String defaultName;

	private BizDateTime bizDateTime;

	public void setWorkdayStore(WorkdayStore workdayStore) {
		this.workdayStore = workdayStore;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	@Override
	public int getNumberOfWorkday(LocalDate to) {
		return getNumberOfWorkday(defaultName, to);
	}

	@Override
	public <T extends ICodeType<String>> int getNumberOfWorkday(T code, LocalDate to) {
		return getNumberOfWorkday(code.getCodeValue(), to);
	}

	@Override
	public int getNumberOfWorkday(String name, LocalDate to) {
		return getNumberOfWorkday(name, bizDateTime.today(), to);
	}

	@Override
	public int getNumberOfWorkday(LocalDate from, LocalDate to) {
		return getNumberOfWorkday(defaultName, from, to);
	}

	@Override
	public <T extends ICodeType<String>> int getNumberOfWorkday(T code, LocalDate from, LocalDate to) {
		return getNumberOfWorkday(code.getCodeValue(), from, to);
	}

	@Override
	public int getNumberOfWorkday(String name, LocalDate from, LocalDate to) {
		return workdayStore.getNumberOfWorkday(name, from, to);
	}

	@Override
	public LocalDate getNextWorkday(int numberOfWorkday) {
		return getNextWorkday(defaultName, numberOfWorkday);
	}

	@Override
	public <T extends ICodeType<String>> LocalDate getNextWorkday(T code, int numberOfWorkday) {
		return getNextWorkday(code.getCodeValue(), numberOfWorkday);
	}

	@Override
	public LocalDate getNextWorkday(String name, int numberOfWorkday) {
		return getNextWorkday(name, bizDateTime.today(), numberOfWorkday);
	}

	@Override
	public LocalDate getNextWorkday(LocalDate from, int numberOfWorkday) {
		return getNextWorkday(defaultName, from, numberOfWorkday);
	}

	@Override
	public <T extends ICodeType<String>> LocalDate getNextWorkday(T code, LocalDate from, int numberOfWorkday) {
		return getNextWorkday(code.getCodeValue(), from, numberOfWorkday);
	}

	@Override
	public LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday) {
		return workdayStore.getNextWorkday(name, from, numberOfWorkday);
	}

}
