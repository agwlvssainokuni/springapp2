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

package cherry.mvctutorial.secure.todo;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.foundation.validator.groups.G9;

@RequestMapping("/secure/todo/edit")
public interface TodoEditController {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = "to", required = false) String redirTo, @RequestParam("id") int id,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = "start")
	ModelAndView start(@RequestParam("id") int id, @Validated(G9.class) TodoEditForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = "execute")
	ModelAndView execute(@RequestParam("id") int id, @Validated() TodoEditForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

}
