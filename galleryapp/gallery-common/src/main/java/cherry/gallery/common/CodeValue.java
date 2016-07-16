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

package cherry.gallery.common;

import cherry.elemental.code.ILabelledCodeType;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateCode", date = "2016-07-16T10:06:23+09:00")
public class CodeValue {

	/** 並び順 */
	public static enum SORT_BY implements ILabelledCodeType<String> {
		/** [並び順] 00: ID */
		SORT_BY_00("00", "ID"),
		/** [並び順] 01: 文字列【10】 */
		SORT_BY_01("01", "文字列【10】"),
		/** [並び順] 02: 整数【64bit】 */
		SORT_BY_02("02", "整数【64bit】"),
		/** [並び順] 03: 小数【1桁】 */
		SORT_BY_03("03", "小数【1桁】"),
		/** [並び順] 04: 小数【3桁】 */
		SORT_BY_04("04", "小数【3桁】"),
		/** [並び順] 05: 日付 */
		SORT_BY_05("05", "日付"),
		/** [並び順] 06: 時刻 */
		SORT_BY_06("06", "時刻"),
		/** [並び順] 07: 日時 */
		SORT_BY_07("07", "日時"),
		/* 生成ツールの都合による定義。 */
		DUMMY("", "");

		private String value;

		private String label;

		private SORT_BY(String value, String label) {
			this.value = value;
			this.label = label;
		}

		@Override
		public String getCodeValue() {
			return value;
		}

		@Override
		public String getCodeLabel() {
			return label;
		}
	}

}
