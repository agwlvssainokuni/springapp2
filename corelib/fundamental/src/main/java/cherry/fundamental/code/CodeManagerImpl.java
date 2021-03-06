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

import static com.google.common.base.Preconditions.checkArgument;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cherry.elemental.code.ICodeType;

/**
 * 区分値管理機能。<br />
 */
public class CodeManagerImpl implements CodeManager {

	private CodeStore codeStore;

	private String valueTemplate;

	public void setCodeStore(CodeStore codeStore) {
		this.codeStore = codeStore;
	}

	public void setValueTemplate(String valueTemplate) {
		this.valueTemplate = valueTemplate;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> boolean isValidValue(T codeEnum, String value) {
		return isValidValue(codeEnum.getCodeValue(), value);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isValidValue(String codeName, String value) {
		return codeStore.findByValue(codeName, value) != null;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> ICodeEntry findByValue(T codeEnum, String value) {
		return findByValue(codeEnum.getCodeValue(), value, false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> ICodeEntry findByValue(T codeEnum, String value, boolean plainLabel) {
		return findByValue(codeEnum.getCodeValue(), value, plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public ICodeEntry findByValue(String codeName, String value) {
		return findByValue(codeName, value, false);
	}

	@Transactional(readOnly = true)
	@Override
	public ICodeEntry findByValue(String codeName, String value, boolean plainLabel) {
		ICodeEntry entry = codeStore.findByValue(codeName, value);
		checkArgument(entry != null, "Not defined for (%s, %s)", codeName, value);
		if (plainLabel) {
			return entry;
		}
		return adjustCodeEntry(entry);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> List<ICodeEntry> getCodeList(T codeEnum) {
		return getCodeList(codeEnum.getCodeValue(), false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> List<ICodeEntry> getCodeList(T codeEnum, boolean plainLabel) {
		return getCodeList(codeEnum.getCodeValue(), plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ICodeEntry> getCodeList(String codeName) {
		return getCodeList(codeName, false);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ICodeEntry> getCodeList(String codeName, boolean plainLabel) {
		List<ICodeEntry> list = codeStore.getCodeList(codeName);
		if (plainLabel) {
			return list;
		}
		List<ICodeEntry> result = new ArrayList<>(list.size());
		for (ICodeEntry entry : list) {
			result.add(adjustCodeEntry(entry));
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> Map<String, String> getCodeMap(T codeEnum) {
		return getCodeMap(codeEnum.getCodeValue(), false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends ICodeType<String>> Map<String, String> getCodeMap(T codeEnum, boolean plainLabel) {
		return getCodeMap(codeEnum.getCodeValue(), plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getCodeMap(String codeName) {
		return getCodeMap(codeName, false);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getCodeMap(String codeName, boolean plainLabel) {
		List<ICodeEntry> list = codeStore.getCodeList(codeName);
		Map<String, String> result = new LinkedHashMap<>();
		for (ICodeEntry entry : list) {
			if (plainLabel) {
				result.put(entry.getCodeValue(), entry.getCodeLabel());
			} else {
				result.put(entry.getCodeValue(), formatLabel(entry.getCodeValue(), entry.getCodeLabel()));
			}
		}
		return result;
	}

	private ICodeEntry adjustCodeEntry(ICodeEntry entry) {
		CodeEntry result = new CodeEntry();
		result.setCodeValue(entry.getCodeValue());
		result.setCodeLabel(formatLabel(entry.getCodeValue(), entry.getCodeLabel()));
		result.setSortOrder(entry.getSortOrder());
		return result;
	}

	private String formatLabel(String value, String label) {
		return MessageFormat.format(valueTemplate, value, label);
	}

}
