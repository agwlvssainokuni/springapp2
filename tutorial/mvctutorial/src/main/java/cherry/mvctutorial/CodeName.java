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

import cherry.foundation.type.ICodeType;

public enum CodeName implements ICodeType<String> {
	/** TODO検索並び順列 */
	TODO_LIST_SORT_BY("todo_list_sort_by");

	private String value;

	private CodeName(String value) {
		this.value = value;
	}

	@Override
	public String getCodeValue() {
		return value;
	}

}
