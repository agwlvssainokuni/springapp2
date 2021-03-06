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

package cherry.gallery.web.applied.ex50;

import static cherry.gallery.web.ParamDef.REQ_DOWNLOAD;
import static cherry.gallery.web.ParamDef.REQ_TO;
import static cherry.gallery.web.PathDef.SUBURI_EXECUTE;
import static cherry.gallery.web.PathDef.SUBURI_START;
import static cherry.gallery.web.PathDef.URI_APPLIED_EX50;

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

@RequestMapping(URI_APPLIED_EX50)
@SessionAttributes(types = { AppliedEx50Form.class })
public interface AppliedEx50Controller {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = REQ_TO, required = false) String redirTo, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request, SessionStatus status);

	@RequestMapping(SUBURI_START)
	ModelAndView start(@Validated(G9.class) AppliedEx50Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(SUBURI_EXECUTE)
	ModelAndView execute(@Validated() AppliedEx50Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_EXECUTE, params = REQ_DOWNLOAD)
	ModelAndView download(@Validated() AppliedEx50Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, HttpServletResponse response);

}
