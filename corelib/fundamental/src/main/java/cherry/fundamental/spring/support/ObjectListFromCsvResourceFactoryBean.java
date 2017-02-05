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

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindingResult;

import cherry.elemental.csv.CsvParser;
import cherry.fundamental.validator.DataBinderHelper;

public class ObjectListFromCsvResourceFactoryBean<T> implements FactoryBean<List<T>> {

	private Resource resource;

	private Charset charset;

	private Class<T> objectClass;

	private DataBinderHelper dataBinderHelper;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setObjectClass(Class<T> objectClass) {
		this.objectClass = objectClass;
	}

	public void setDataBinderHelper(DataBinderHelper dataBinderHelper) {
		this.dataBinderHelper = dataBinderHelper;
	}

	@Override
	public List<T> getObject() throws Exception {
		try (InputStream in = resource.getInputStream();
				Reader reader = new InputStreamReader(in, charset);
				CsvParser csv = new CsvParser(reader)) {

			String[] header = csv.read();
			if (header == null) {
				return Collections.emptyList();
			}

			List<T> objectList = new ArrayList<>();
			Map<Integer, List<String>> errmsg = new LinkedHashMap<>();
			String[] record;
			for (int rownum = 1; (record = csv.read()) != null; rownum++) {

				MutablePropertyValues pvs = new MutablePropertyValues();
				for (int i = 0; i < header.length && i < record.length; i++) {
					pvs.add(header[i], record[i]);
				}

				T target = objectClass.newInstance();
				BindingResult binding = dataBinderHelper.bindAndValidate(target, pvs);
				if (binding.hasErrors()) {
					errmsg.put(rownum, dataBinderHelper.resolveAllMessage(binding, LocaleContextHolder.getLocale()));
					continue;
				}

				objectList.add(target);
			}

			if (!errmsg.isEmpty()) {
				String prefix0 = format("Invalid record in {0}: ", resource.toString());
				String m = errmsg.entrySet().stream().map(e -> {
					String prefix1 = format("row {0,number,#0} ", e.getKey());
					return e.getValue().stream().collect(joining(", ", prefix1, ""));
				}).collect(joining("; ", prefix0, ""));
				throw new IllegalStateException(m);
			}

			return objectList;
		}
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
