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

import cherry.elemental.code.EnumCodeUtil;
import cherry.elemental.code.ILabelledCodeType;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateCode")
public enum CodeName implements ILabelledCodeType<String> {
	/** 並び順 */
	SORT_BY("sort_by", "並び順"),
	/* 生成ツールの都合による定義。 */
	DUMMY("", "");

	private static final java.util.Map<String, CodeName> nameMap = EnumCodeUtil.getCodeMap(CodeName.values());

	public static CodeName resolve(String name) {
		return nameMap.get(name);
	}

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
