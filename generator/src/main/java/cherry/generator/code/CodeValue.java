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

package cherry.generator.code;

import cherry.elemental.code.EnumCodeUtil;
import cherry.elemental.code.ILabelledCodeType;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateCode", date = "2016-07-16T10:19:46+09:00")
public class CodeValue {

	/** 性別 */
	public static enum GENDER_TYPE implements ILabelledCodeType<String> {
		/** [性別] 1: 男性 */
		GENDER_TYPE_1("1", "男性"),
		/** [性別] 2: 女性 */
		GENDER_TYPE_2("2", "女性"),
		/** [性別] 9: なし */
		GENDER_TYPE_9("9", "なし"),
		/* 生成ツールの都合による定義。 */
		DUMMY("", "");

		private static final java.util.Map<String, GENDER_TYPE> valueMap = EnumCodeUtil.getCodeMap(GENDER_TYPE.values());

		public static GENDER_TYPE resolve(String name) {
			return valueMap.get(name);
		}

		private String value;

		private String label;

		private GENDER_TYPE(String value, String label) {
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

	/** 都道府県コード */
	public static enum PREF_CD implements ILabelledCodeType<String> {
		/** [都道府県コード] 01: 北海道 */
		PREF_CD_01("01", "北海道"),
		/** [都道府県コード] 02: 青森県 */
		PREF_CD_02("02", "青森県"),
		/** [都道府県コード] 03: 岩手県 */
		PREF_CD_03("03", "岩手県"),
		/** [都道府県コード] 04: 宮城県 */
		PREF_CD_04("04", "宮城県"),
		/** [都道府県コード] 05: 秋田県 */
		PREF_CD_05("05", "秋田県"),
		/** [都道府県コード] 06: 山形県 */
		PREF_CD_06("06", "山形県"),
		/** [都道府県コード] 07: 福島県 */
		PREF_CD_07("07", "福島県"),
		/** [都道府県コード] 08: 茨城県 */
		PREF_CD_08("08", "茨城県"),
		/** [都道府県コード] 09: 栃木県 */
		PREF_CD_09("09", "栃木県"),
		/** [都道府県コード] 10: 群馬県 */
		PREF_CD_10("10", "群馬県"),
		/** [都道府県コード] 11: 埼玉県 */
		PREF_CD_11("11", "埼玉県"),
		/** [都道府県コード] 12: 千葉県 */
		PREF_CD_12("12", "千葉県"),
		/** [都道府県コード] 13: 東京都 */
		PREF_CD_13("13", "東京都"),
		/** [都道府県コード] 14: 神奈川県 */
		PREF_CD_14("14", "神奈川県"),
		/** [都道府県コード] 15: 新潟県 */
		PREF_CD_15("15", "新潟県"),
		/** [都道府県コード] 16: 富山県 */
		PREF_CD_16("16", "富山県"),
		/** [都道府県コード] 17: 石川県 */
		PREF_CD_17("17", "石川県"),
		/** [都道府県コード] 18: 福井県 */
		PREF_CD_18("18", "福井県"),
		/** [都道府県コード] 19: 山梨県 */
		PREF_CD_19("19", "山梨県"),
		/** [都道府県コード] 20: 長野県 */
		PREF_CD_20("20", "長野県"),
		/** [都道府県コード] 21: 岐阜県 */
		PREF_CD_21("21", "岐阜県"),
		/** [都道府県コード] 22: 静岡県 */
		PREF_CD_22("22", "静岡県"),
		/** [都道府県コード] 23: 愛知県 */
		PREF_CD_23("23", "愛知県"),
		/** [都道府県コード] 24: 三重県 */
		PREF_CD_24("24", "三重県"),
		/** [都道府県コード] 25: 滋賀県 */
		PREF_CD_25("25", "滋賀県"),
		/** [都道府県コード] 26: 京都府 */
		PREF_CD_26("26", "京都府"),
		/** [都道府県コード] 27: 大阪府 */
		PREF_CD_27("27", "大阪府"),
		/** [都道府県コード] 28: 兵庫県 */
		PREF_CD_28("28", "兵庫県"),
		/** [都道府県コード] 29: 奈良県 */
		PREF_CD_29("29", "奈良県"),
		/** [都道府県コード] 30: 和歌山県 */
		PREF_CD_30("30", "和歌山県"),
		/** [都道府県コード] 31: 鳥取県 */
		PREF_CD_31("31", "鳥取県"),
		/** [都道府県コード] 32: 島根県 */
		PREF_CD_32("32", "島根県"),
		/** [都道府県コード] 33: 岡山県 */
		PREF_CD_33("33", "岡山県"),
		/** [都道府県コード] 34: 広島県 */
		PREF_CD_34("34", "広島県"),
		/** [都道府県コード] 35: 山口県 */
		PREF_CD_35("35", "山口県"),
		/** [都道府県コード] 36: 徳島県 */
		PREF_CD_36("36", "徳島県"),
		/** [都道府県コード] 37: 香川県 */
		PREF_CD_37("37", "香川県"),
		/** [都道府県コード] 38: 愛媛県 */
		PREF_CD_38("38", "愛媛県"),
		/** [都道府県コード] 39: 高知県 */
		PREF_CD_39("39", "高知県"),
		/** [都道府県コード] 40: 福岡県 */
		PREF_CD_40("40", "福岡県"),
		/** [都道府県コード] 41: 佐賀県 */
		PREF_CD_41("41", "佐賀県"),
		/** [都道府県コード] 42: 長崎県 */
		PREF_CD_42("42", "長崎県"),
		/** [都道府県コード] 43: 熊本県 */
		PREF_CD_43("43", "熊本県"),
		/** [都道府県コード] 44: 大分県 */
		PREF_CD_44("44", "大分県"),
		/** [都道府県コード] 45: 宮崎県 */
		PREF_CD_45("45", "宮崎県"),
		/** [都道府県コード] 46: 鹿児島県 */
		PREF_CD_46("46", "鹿児島県"),
		/** [都道府県コード] 47: 沖縄県 */
		PREF_CD_47("47", "沖縄県"),
		/* 生成ツールの都合による定義。 */
		DUMMY("", "");

		private static final java.util.Map<String, PREF_CD> valueMap = EnumCodeUtil.getCodeMap(PREF_CD.values());

		public static PREF_CD resolve(String name) {
			return valueMap.get(name);
		}

		private String value;

		private String label;

		private PREF_CD(String value, String label) {
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
