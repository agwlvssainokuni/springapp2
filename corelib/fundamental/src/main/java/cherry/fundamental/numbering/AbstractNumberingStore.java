/*
 * Copyright 2015,2016 agwlvssainokuni
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

package cherry.fundamental.numbering;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public abstract class AbstractNumberingStore implements NumberingStore {

	private String cacheName;

	private CacheManager cacheManager;

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public NumberingDefinition getDefinition(String numberName) {
		Cache cache = cacheManager.getCache(cacheName);
		return cache.get(numberName, () -> getDefinitionInternal(numberName));
	}

	protected abstract NumberingDefinition getDefinitionInternal(String numberName);

}
