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

package cherry.mvctutorial;

import java.nio.charset.Charset;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component("mvctutorialConfig")
public class Config {

	@Value("${mvctutorial.secure.todo.defaultOffsetOfDueDate}")
	private int defaultOffsetOfDueDate;

	@Value("${mvctutorial.secure.todo.defaultPageSize}")
	private long defaultPageSize;

	@Value("${mvctutorial.secure.todo.contentType}")
	private String contentType;

	@Value("${mvctutorial.secure.todo.filename}")
	private String filename;

	@Value("${mvctutorial.secure.todo.charset}")
	private Charset charset;

}
