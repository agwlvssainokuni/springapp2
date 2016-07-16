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

package cherry.mvctutorial;

import cherry.elemental.code.EnumCodeUtil;
import cherry.elemental.code.ICodeType;

public class CodeValue {

	public static enum TODO_LIST_SORT_BY implements ICodeType<String> {
		/** ID */
		TODO_LIST_SORT_BY_ID("ID"),
		/** 登録日時 */
		TODO_LIST_SORT_BY_POSTED_AT("POSTED_AT"),
		/** 期日 */
		TODO_LIST_SORT_BY_DUE_DATE("DUE_DATE");

		private static final java.util.Map<String, TODO_LIST_SORT_BY> valueMap = EnumCodeUtil
				.getCodeMap(TODO_LIST_SORT_BY.values());

		public static TODO_LIST_SORT_BY resolve(String name) {
			return valueMap.get(name);
		}

		private String value;

		private TODO_LIST_SORT_BY(String value) {
			this.value = value;
		}

		@Override
		public String getCodeValue() {
			return value;
		}
	}

}
