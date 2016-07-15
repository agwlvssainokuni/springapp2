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

package cherry.fundamental.format;

import java.io.Serializable;
import java.util.regex.Pattern;

public class PatternCI implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Pattern pattern;

	public PatternCI(Pattern pattern) {
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public String toString() {
		return pattern.toString();
	}

}
