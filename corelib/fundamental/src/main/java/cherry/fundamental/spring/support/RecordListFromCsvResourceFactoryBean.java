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
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import cherry.elemental.csv.CsvParser;

public class RecordListFromCsvResourceFactoryBean implements FactoryBean<List<List<String>>>, InitializingBean {

	private Resource resource;

	private Charset charset;

	private List<List<String>> recordList;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void afterPropertiesSet() throws IOException {
		recordList = new ArrayList<>();
		try (InputStream in = resource.getInputStream();
				Reader reader = new InputStreamReader(in, charset);
				CsvParser csv = new CsvParser(reader)) {
			String[] record;
			while ((record = csv.read()) != null) {
				recordList.add(Arrays.asList(record));
			}
		}
	}

	@Override
	public List<List<String>> getObject() throws Exception {
		return recordList;
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
