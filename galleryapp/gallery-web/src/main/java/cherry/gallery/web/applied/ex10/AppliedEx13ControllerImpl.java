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

package cherry.gallery.web.applied.ex10;

import static cherry.fundamental.spring.webmvc.ModelAndViewBuilder.redirect;
import static cherry.fundamental.spring.webmvc.ModelAndViewBuilder.withViewname;
import static cherry.fundamental.spring.webmvc.ModelAndViewBuilder.withoutView;
import static cherry.gallery.web.ParamDef.REQ_ID;
import static cherry.gallery.web.ParamDef.REQ_ROWNUM;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.fundamental.bizerror.BizErrorUtil;
import cherry.fundamental.spring.webmvc.ViewNameUtil;
import cherry.gallery.web.BizErrorId;
import cherry.gallery.web.applied.ex10.AppliedEx10SubFormBase.Prop;

@Controller
public class AppliedEx13ControllerImpl implements AppliedEx13Controller {

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(AppliedEx13Controller.class).start(0L, 0,
			null, null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, long id, int rownum, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo, id, rownum)).build();
	}

	@Override
	public ModelAndView start(long id, int rownum, AppliedEx10Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {

		AppliedEx11Form f = new AppliedEx11Form();
		f.setItem(new HashMap<Integer, AppliedEx10SubForm>());
		if (CollectionUtils.isNotEmpty(form.getItem()) && form.getItem().size() > rownum) {
			f.getItem().put(rownum, form.getItem().get(rownum));
		}

		return withViewname(viewnameOfStart).addObject(f).build();
	}

	@Override
	public ModelAndView confirm(long id, int rownum, AppliedEx11Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(id, rownum, form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(long id, int rownum, AppliedEx11Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(long id, int rownum, AppliedEx10Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		return redirect(redirectOnExecute(id)).build();
	}

	private UriComponents redirectOnInit(String redir, long id, int rownum) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(AppliedEx13Controller.class).start(id, rownum, null, null, null, null, null, null))
					.replaceQueryParam(REQ_ID, id).replaceQueryParam(REQ_ROWNUM, rownum).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(AppliedEx12Controller.class).update(id, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private boolean hasErrors(long id, int rownum, AppliedEx11Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getItem().get(rownum).getDt() == null && form.getItem().get(rownum).getTm() != null) {
			BizErrorUtil.rejectValue(binding, AppliedEx11Form.getItemPropName(rownum, Prop.Dt),
					BizErrorId.RequiredWhen, AppliedEx11Form.resolveItemProp(rownum, Prop.Dt),
					AppliedEx11Form.resolveItemProp(rownum, Prop.Tm));
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック

		return false;
	}

}
