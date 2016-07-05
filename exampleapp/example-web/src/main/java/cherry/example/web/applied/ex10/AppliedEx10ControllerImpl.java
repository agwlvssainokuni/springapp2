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

package cherry.example.web.applied.ex10;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.BizErrorId;
import cherry.example.web.applied.ex10.AppliedEx10FormBase.Prop;
import cherry.foundation.bizerror.BizErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;
import cherry.foundation.spring.webmvc.ViewNameUtil;

@Controller
public class AppliedEx10ControllerImpl implements AppliedEx10Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private AppliedEx10Service service;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(AppliedEx10Controller.class).start(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView update(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView confirm(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			BizErrorUtil.rejectOnOneTimeTokenError(binding);
			return withViewname(viewnameOfStart).build();
		}

		Long id = service.create(form);
		checkState(id != null, "failed to create: form=%s", form);

		return redirect(redirectOnExecute(id.longValue())).build();
	}

	@Override
	public ModelAndView completed(long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		AppliedEx10Form f = service.findById(id);
		throwNotFoundIfNull(f, AppliedEx10Form.class, id);
		return withoutView().addObject(f).build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(AppliedEx10Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(AppliedEx10Controller.class).completed(id, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private boolean hasErrors(AppliedEx10Form form, BindingResult binding) {

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
		if (service.exists(form.getText10())) {
			BizErrorUtil.rejectValue(binding, Prop.Text10.getName(), BizErrorId.AlreadyExists, Prop.Text10.resolve());
		}

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
