/*
 * Copyright 2014,2016 agwlvssainokuni
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

package cherry.fundamental.code;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 区分値管理機能。<br />
 * 区分値と表示文字列の対を保持する。
 */
public class CodeEntry implements ICodeEntry, Serializable {

	private static final long serialVersionUID = 1L;

	/** 区分値。 */
	private String codeValue;

	/** 区分値の表示文字列。 */
	private String codeLabel;

	/** 画面に選択肢として表示する際の表示順序。 */
	private int sortOrder;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	@Override
	public String getCodeLabel() {
		return codeLabel;
	}

	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}

	@Override
	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

}
