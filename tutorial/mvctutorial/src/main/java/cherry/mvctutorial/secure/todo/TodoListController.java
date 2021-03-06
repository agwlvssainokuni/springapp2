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

import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import cherry.fundamental.validator.groups.G9;

@SessionAttributes(types = { TodoListForm.class })
@RequestMapping("/secure/todo/list")
public interface TodoListController {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = "to", required = false) String redirTo, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, SessionStatus status);

	@RequestMapping(value = "start")
	ModelAndView start(@Validated(G9.class) TodoListForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = "execute")
	ModelAndView execute(@Validated() TodoListForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = "execute", params = "download")
	ModelAndView download(@Validated TodoListForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, HttpServletResponse response);

}
