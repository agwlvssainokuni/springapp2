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

import cherry.fundamental.type.ILabelledCodeType;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateCode", date = "2016-07-16T07:09:31+09:00")
public enum CodeName implements ILabelledCodeType<String> {
	/** 性別 */
	GENDER_TYPE("gender_type", "性別"),
	/** 都道府県コード */
	PREF_CD("pref_cd", "都道府県コード"),
	/* 生成ツールの都合による定義。 */
	DUMMY("", "");

	private String pname;

	private String lname;

	private CodeName(String pname, String lname) {
		this.pname = pname;
		this.lname = lname;
	}

	@Override
	public String getCodeValue() {
		return pname;
	}

	@Override
	public String getCodeLabel() {
		return lname;
	}

}
