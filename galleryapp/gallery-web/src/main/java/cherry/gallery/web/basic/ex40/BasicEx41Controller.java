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

package cherry.gallery.web.basic.ex40;

import static cherry.gallery.web.ParamDef.REQ_BACK;
import static cherry.gallery.web.ParamDef.REQ_ID;
import static cherry.gallery.web.ParamDef.REQ_TO;
import static cherry.gallery.web.PathDef.SUBURI_CONFIRM;
import static cherry.gallery.web.PathDef.SUBURI_EXECUTE;
import static cherry.gallery.web.PathDef.SUBURI_START;
import static cherry.gallery.web.PathDef.URI_BASIC_EX41;

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

import cherry.fundamental.validator.groups.G9;

@RequestMapping(URI_BASIC_EX41)
public interface BasicEx41Controller {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = REQ_TO, required = false) String redirTo,
			@RequestParam(value = REQ_ID, defaultValue = "0") long id, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(SUBURI_START)
	ModelAndView start(@RequestParam(REQ_ID) long id, @Validated(G9.class) BasicEx41Form form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(SUBURI_CONFIRM)
	ModelAndView confirm(@RequestParam(REQ_ID) long id, @Validated() BasicEx41Form form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_EXECUTE, params = REQ_BACK)
	ModelAndView back(@RequestParam(REQ_ID) long id, @Validated() BasicEx41Form form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(SUBURI_EXECUTE)
	ModelAndView execute(@RequestParam(REQ_ID) long id, @Validated() BasicEx41Form form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

}
