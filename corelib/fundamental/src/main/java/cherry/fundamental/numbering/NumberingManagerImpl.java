/*
 * Copyright 2015,2018 agwlvssainokuni
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

package cherry.fundamental.numbering;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.text.MessageFormat;

import cherry.elemental.code.ICodeType;

public class NumberingManagerImpl implements NumberingManager {

	private NumberingStore numberingStore;

	public void setNumberingStore(NumberingStore numberingStore) {
		this.numberingStore = numberingStore;
	}

	@Override
	public <T extends ICodeType<String>> String issueAsString(T numberEnum) {
		return issueAsString(numberEnum.getCodeValue());
	}

	@Override
	public <T extends ICodeType<String>> String[] issueAsString(T numberEnum, int count) {
		return issueAsString(numberEnum.getCodeValue(), count);
	}

	@Override
	public String issueAsString(String numberName) {

		checkArgument(numberName != null, "numberName must not be null");

		NumberingDefinition def = numberingStore.getDefinition(numberName);
		MessageFormat fmt = new MessageFormat(def.getTemplate());
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			long v = current + 1;
			checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
			checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());
			String result = fmt.format(new Object[] { Long.valueOf(v) });

			offset = 1;
			return result;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	@Override
	public String[] issueAsString(String numberName, int count) {

		checkArgument(numberName != null, "numberName must not be null");
		checkArgument(count > 0, "count must be > 0");

		NumberingDefinition def = numberingStore.getDefinition(numberName);
		MessageFormat fmt = new MessageFormat(def.getTemplate());
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			String[] result = new String[count];
			for (int i = 1; i <= count; i++) {
				long v = current + i;
				checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
				checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());
				result[i - 1] = fmt.format(new Object[] { Long.valueOf(v) });
			}

			offset = count;
			return result;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	@Override
	public <T extends ICodeType<String>> long issueAsLong(T numberEnum) {
		return issueAsLong(numberEnum.getCodeValue());
	}

	@Override
	public <T extends ICodeType<String>> long[] issueAsLong(T numberEnum, int count) {
		return issueAsLong(numberEnum.getCodeValue(), count);
	}

	@Override
	public long issueAsLong(String numberName) {

		checkArgument(numberName != null, "numberName must not be null");

		NumberingDefinition def = numberingStore.getDefinition(numberName);
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			long v = current + 1;
			checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
			checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());

			offset = 1;
			return v;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

	@Override
	public long[] issueAsLong(String numberName, int count) {

		checkArgument(numberName != null, "numberName must not be null");
		checkArgument(count > 0, "count must be > 0");

		NumberingDefinition def = numberingStore.getDefinition(numberName);
		long current = numberingStore.loadAndLock(numberName);
		int offset = 0;
		try {

			long[] result = new long[count];
			for (int i = 1; i <= count; i++) {
				long v = current + i;
				checkState(v >= def.getMinValue(), "%s must be >= %s", numberName, def.getMinValue());
				checkState(v <= def.getMaxValue(), "%s must be <= %s", numberName, def.getMaxValue());
				result[i - 1] = v;
			}

			offset = count;
			return result;
		} finally {
			numberingStore.saveAndUnlock(numberName, current + offset);
		}
	}

}
