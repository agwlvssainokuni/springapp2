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

package cherry.foundation.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleCodeStore implements CodeStore {

	private Map<String, Map<String, String>> codeDefMap;

	public void setCodeDefMap(Map<String, Map<String, String>> codeDefMap) {
		this.codeDefMap = codeDefMap;
	}

	@Override
	public ICodeEntry findByValue(String codeName, String value) {
		Map<String, String> map = codeDefMap.get(codeName);
		if (map == null) {
			return null;
		}
		String label = map.get(value);
		if (label == null) {
			return null;
		}
		CodeEntry entry = new CodeEntry();
		entry.setCodeValue(value);
		entry.setCodeLabel(label);
		entry.setSortOrder(0);
		return entry;
	}

	@Override
	public List<ICodeEntry> getCodeList(String codeName) {
		Map<String, String> map = codeDefMap.get(codeName);
		if (map == null) {
			return null;
		}
		List<ICodeEntry> list = new ArrayList<>(map.entrySet().size());
		for (Map.Entry<String, String> e : map.entrySet()) {
			CodeEntry entry = new CodeEntry();
			entry.setCodeValue(e.getKey());
			entry.setCodeLabel(e.getValue());
			entry.setSortOrder(0);
			list.add(entry);
		}
		return list;
	}

}
