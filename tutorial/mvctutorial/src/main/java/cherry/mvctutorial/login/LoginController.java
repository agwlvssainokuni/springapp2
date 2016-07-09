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

package cherry.mvctutorial.login;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/login")
public interface LoginController {

	@RequestMapping(value = "start")
	ModelAndView start(Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = "start", params = "loginFailed")
	ModelAndView loginFailed(Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

	@RequestMapping(value = "start", params = "loggedOut")
	ModelAndView loggedOut(Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

}
