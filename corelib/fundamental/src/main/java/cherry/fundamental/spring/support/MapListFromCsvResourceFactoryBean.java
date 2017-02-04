/*
 * Copyright 2017 agwlvssainokuni
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

package cherry.fundamental.spring.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import cherry.elemental.csv.CsvParser;

public class MapListFromCsvResourceFactoryBean implements FactoryBean<List<Map<String, String>>>, InitializingBean {

	private Resource resource;

	private Charset charset;

	private List<Map<String, String>> mapList;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		mapList = new ArrayList<>();
		try (InputStream in = resource.getInputStream();
				Reader reader = new InputStreamReader(in, charset);
				CsvParser csv = new CsvParser(reader)) {

			String[] header = csv.read();
			if (header == null) {
				return;
			}

			String[] record;
			for (int rownum = 1; (record = csv.read()) != null; rownum++) {
				Map<String, String> map = new HashMap<>();
				for (int i = 0; i < header.length && i < record.length; i++) {
					map.put(header[i], record[i]);
				}
				mapList.add(map);
			}
		}
	}

	@Override
	public List<Map<String, String>> getObject() throws Exception {
		return mapList;
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
