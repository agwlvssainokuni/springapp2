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

package cherry.foundation.spring.support;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import com.google.common.io.ByteStreams;

public class ByteArrayFromResourceFactoryBean implements FactoryBean<byte[]> {

	private Resource resource;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public byte[] getObject() throws IOException {
		try (InputStream in = resource.getInputStream()) {
			return ByteStreams.toByteArray(in);
		}
	}

	@Override
	public Class<?> getObjectType() {
		return byte[].class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
