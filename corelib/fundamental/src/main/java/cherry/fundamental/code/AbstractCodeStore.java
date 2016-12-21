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

import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCodeStore implements CodeStore {

	private String cacheName;

	private CacheManager cacheManager;

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Transactional(readOnly = true)
	@Override
	public ICodeEntry findByValue(String codeName, String value) {
		return getCodeListViaCache(codeName).stream().filter(entry -> value.equals(entry.getCodeValue())).findFirst()
				.orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ICodeEntry> getCodeList(String codeName) {
		return getCodeListViaCache(codeName);
	}

	private List<ICodeEntry> getCodeListViaCache(String codeName) {
		Cache cache = cacheManager.getCache(cacheName);
		return cache.get(codeName, () -> getCodeListInternal(codeName));
	}

	protected abstract List<ICodeEntry> getCodeListInternal(String codeName);

}
