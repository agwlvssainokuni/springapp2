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

package cherry.example.web.basic.ex40;

import static cherry.example.web.ParamDef.FLASH_UPDATED;
import static cherry.example.web.ParamDef.REQ_ID;
import static cherry.foundation.spring.webmvc.Http4xxChecker.throwNotFoundIfNull;
import static cherry.foundation.spring.webmvc.ModelAndViewBuilder.redirect;
import static cherry.foundation.spring.webmvc.ModelAndViewBuilder.withViewname;
import static cherry.foundation.spring.webmvc.ModelAndViewBuilder.withoutView;
import static com.google.common.base.Preconditions.checkState;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.BizErrorId;
import cherry.example.web.basic.ex40.BasicEx41FormBase.Prop;
import cherry.foundation.bizerror.BizErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;
import cherry.foundation.spring.webmvc.ViewNameUtil;

@Controller
public class BasicEx41ControllerImpl implements BasicEx41Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private BasicEx41Service service;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(BasicEx41Controller.class).start(0L, null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo, id)).build();
	}

	@Override
	public ModelAndView start(long id, BasicEx41Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		BasicEx41Form f = service.findById(id);
		throwNotFoundIfNull(f, BasicEx41Form.class, id);
		return withViewname(viewnameOfStart).addObject(f).build();
	}

	@Override
	public ModelAndView confirm(long id, BasicEx41Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(id, form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(long id, BasicEx41Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(long id, BasicEx41Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(id, form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			BizErrorUtil.rejectOnOneTimeTokenError(binding);
			return withViewname(viewnameOfStart).build();
		}

		long count = service.update(id, form);
		checkState(count == 1L, "failed to update: id=%s, form=%s", id, form);

		redirAttr.addFlashAttribute(FLASH_UPDATED, Boolean.TRUE);
		return redirect(redirectOnExecute(id)).build();
	}

	private UriComponents redirectOnInit(String redir, long id) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(BasicEx41Controller.class).start(id, null, null, null, null, null, null))
					.replaceQueryParam(REQ_ID, id).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(BasicEx41Controller.class).start(id, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private boolean hasErrors(long id, BasicEx41Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDt() == null && form.getTm() != null) {
			BizErrorUtil.rejectValue(binding, Prop.Dt.getName(), BizErrorId.RequiredWhen, Prop.Dt.resolve(),
					Prop.Tm.resolve());
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック
		if (service.exists(id, form.getText10())) {
			BizErrorUtil.rejectValue(binding, Prop.Text10.getName(), BizErrorId.AlreadyExists, Prop.Text10.resolve());
		}

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
