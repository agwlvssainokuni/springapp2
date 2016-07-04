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

package cherry.gradle.task.generator;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import java.util.function.Function;

public class FunctionFactory {

	public static Function<String, String> upperCamel() {
		return (value -> LOWER_CAMEL.to(UPPER_CAMEL, value));
	}

	public static Function<String, String> lowerCamel() {
		return (value -> LOWER_CAMEL.to(LOWER_CAMEL, value));
	}

	public static Function<String, String> lowerUnderscore() {
		return (value -> LOWER_CAMEL.to(LOWER_UNDERSCORE, value));
	}

	public static Function<String, String> upperUnderscore() {
		return (value -> LOWER_CAMEL.to(UPPER_UNDERSCORE, value));
	}

	public static Function<String, String> className() {
		return (fqcn) -> {
			int index = fqcn.lastIndexOf(".");
			if (index < 0) {
				return fqcn;
			} else {
				return fqcn.substring(index + 1);
			}
		};
	}

	public static Function<String, String> packageName() {
		return (fqcn) -> {
			int index = fqcn.lastIndexOf(".");
			if (index < 0) {
				return "";
			} else {
				return fqcn.substring(0, index);
			}
		};
	}

	public static Function<String, String> formName() {
		return className().andThen(cn -> UPPER_CAMEL.to(LOWER_CAMEL, cn));
	}

	public static Function<String, String> dirName() {
		return packageName().andThen(pn -> pn.replaceAll("\\.", "/"));
	}

}
