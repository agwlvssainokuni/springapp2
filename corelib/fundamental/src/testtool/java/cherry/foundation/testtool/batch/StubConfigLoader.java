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

package cherry.foundation.testtool.batch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import cherry.foundation.testtool.stub.StubConfigurer;
import cherry.foundation.testtool.stub.StubRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.PatternFilenameFilter;

public class StubConfigLoader {

	private File definitionDirectory;

	private ObjectMapper jsonObjectMapper;

	private ObjectMapper yamlObjectMapper;

	private StubRepository stubRepository;

	public void setDefinitionDirectory(File definitionDirectory) {
		this.definitionDirectory = definitionDirectory;
	}

	public void setJsonObjectMapper(ObjectMapper jsonObjectMapper) {
		this.jsonObjectMapper = jsonObjectMapper;
	}

	public void setYamlObjectMapper(ObjectMapper yamlObjectMapper) {
		this.yamlObjectMapper = yamlObjectMapper;
	}

	public void setStubRepository(StubRepository stubRepository) {
		this.stubRepository = stubRepository;
	}

	public void loadDefinition() throws IOException {
		File[] jsonfiles = definitionDirectory.listFiles(new PatternFilenameFilter(".*\\.json$"));
		configure(jsonfiles, jsonObjectMapper);
		File[] yamlfiles = definitionDirectory.listFiles(new PatternFilenameFilter(".*\\.yaml$"));
		configure(yamlfiles, yamlObjectMapper);
	}

	private void configure(File[] files, ObjectMapper mapper) throws IOException {
		List<Resource> list = new ArrayList<>();
		if (files != null) {
			for (File f : files) {
				list.add(new FileSystemResource(f));
			}
		}
		StubConfigurer cfg = new StubConfigurer();
		cfg.setObjectMapper(mapper);
		cfg.setResources(list);
		cfg.setRepository(stubRepository);
		cfg.configure();
	}

}
