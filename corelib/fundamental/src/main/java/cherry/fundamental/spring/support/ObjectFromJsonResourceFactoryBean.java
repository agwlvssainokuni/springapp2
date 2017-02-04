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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectFromJsonResourceFactoryBean<T> implements FactoryBean<T>, InitializingBean {

	private Resource resource;

	private String typeName;

	private ObjectMapper objectMapper;

	private T object;

	private Class<T> objectType;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws IOException {
		JavaType javaType = objectMapper.getTypeFactory().constructFromCanonical(typeName);
		try (InputStream in = resource.getInputStream()) {
			object = objectMapper.<T> readValue(in, javaType);
		}
		objectType = (Class<T>) javaType.getRawClass();
	}

	@Override
	public T getObject() throws Exception {
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
