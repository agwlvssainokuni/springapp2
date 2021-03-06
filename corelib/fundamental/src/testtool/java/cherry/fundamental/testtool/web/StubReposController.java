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

package cherry.fundamental.testtool.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/testtool/stubrepos")
public interface StubReposController {

	@RequestMapping({ "", "json" })
	@ResponseBody()
	String alwaysReturnJson(@RequestParam("className") String className, @RequestParam("methodName") String methodName,
			@RequestParam(value = "methodIndex", defaultValue = "0") int methodIndex,
			@RequestParam("value") String value, @RequestParam("valueType") String valueType);

	@RequestMapping({ "yaml" })
	@ResponseBody()
	String alwaysReturnYaml(@RequestParam("className") String className, @RequestParam("methodName") String methodName,
			@RequestParam(value = "methodIndex", defaultValue = "0") int methodIndex,
			@RequestParam("value") String value, @RequestParam("valueType") String valueType);

	@RequestMapping(value = { "", "json" }, params = { "peek" })
	@ResponseBody()
	List<String> peekStubJson(@RequestParam("className") String className,
			@RequestParam("methodName") String methodName,
			@RequestParam(value = "methodIndex", defaultValue = "0") int methodIndex);

	@RequestMapping(value = { "yaml" }, params = { "peek" })
	@ResponseBody()
	List<String> peekStubYaml(@RequestParam("className") String className,
			@RequestParam("methodName") String methodName,
			@RequestParam(value = "methodIndex", defaultValue = "0") int methodIndex);

	@RequestMapping(value = { "", "json", "yaml" }, params = { "bean" })
	@ResponseBody()
	List<String> resolveBeanName(@RequestParam("className") String className);

	@RequestMapping(value = { "", "json", "yaml" }, params = { "method" })
	@ResponseBody()
	List<String> resolveMethod(@RequestParam("className") String className,
			@RequestParam("methodName") String methodName);

	@RequestMapping(value = { "", "json", "yaml" }, params = { "list" })
	@ResponseBody()
	List<String> getStubbedMethod(@RequestParam(value = "className", required = false) String className);

}
