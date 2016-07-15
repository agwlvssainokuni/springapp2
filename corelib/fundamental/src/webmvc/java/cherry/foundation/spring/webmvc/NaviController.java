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

package cherry.foundation.spring.webmvc;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/navi")
@SessionAttributes(types = { NaviForm.class })
public interface NaviController {

	@RequestMapping()
	ModelAndView back(@Validated() NaviForm form, BindingResult binding);

	@RequestMapping(params = { "to" })
	ModelAndView save1(@RequestParam("to") String to, @Validated() NaviForm form, BindingResult binding);

	@RequestMapping(params = { "to", "fm" })
	ModelAndView save2(@RequestParam("to") String to, @RequestParam("fm") String fm, @Validated() NaviForm form,
			BindingResult binding);

	@RequestMapping(params = { "to", "clear" })
	ModelAndView clear(@RequestParam("to") String to, @Validated() NaviForm form, BindingResult binding,
			SessionStatus status);

}
