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

package cherry.gallery.web.validation.ex10;

import static cherry.fundamental.spring.webmvc.ModelAndViewBuilder.redirect;
import static cherry.fundamental.spring.webmvc.ModelAndViewBuilder.withViewname;
import static cherry.fundamental.spring.webmvc.ModelAndViewBuilder.withoutView;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.fundamental.spring.webmvc.ViewNameUtil;

@Controller
public class ValidationEx10ControllerImpl implements ValidationEx10Controller {

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(ValidationEx10Controller.class).start(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(ValidationEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView confirm(ValidationEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(ValidationEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(ValidationEx10Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private boolean hasErrors(ValidationEx10Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

}
