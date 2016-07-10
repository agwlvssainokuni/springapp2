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

package cherry.example.web.basic.ex30;

import static cherry.example.common.CodeValue.SORT_BY.SORT_BY_00;
import static cherry.example.common.CodeValue.SORT_BY.SORT_BY_01;
import static cherry.foundation.spring.webmvc.ModelAndViewBuilder.redirect;
import static cherry.foundation.spring.webmvc.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

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

import cherry.example.db.gen.query.BExTbl1;
import cherry.example.web.BizErrorId;
import cherry.example.web.Config;
import cherry.example.web.basic.ex30.BasicEx30FormBase.Prop;
import cherry.foundation.bizerror.BizErrorUtil;
import cherry.foundation.spring.webmvc.SortOrder;
import cherry.foundation.spring.webmvc.SortParam;
import cherry.foundation.spring.webmvc.ViewNameUtil;
import cherry.goods.paginate.PagedList;

@Controller
public class BasicEx30ControllerImpl implements BasicEx30Controller {

	@Autowired
	private BasicEx30Service service;

	@Autowired
	private Config config;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(BasicEx30Controller.class).start(null, null,
			null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(BasicEx30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		form.setPno(0L);
		if (form.getPsz() <= 0L) {
			form.setPsz(config.getDefaultPageSize());
		}

		adjustSortCondition(form);

		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(BasicEx30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (form.getPno() <= 0L) {
			form.setPno(0L);
		}
		if (form.getPsz() <= 0L) {
			form.setPsz(config.getDefaultPageSize());
		}

		adjustSortCondition(form);

		PagedList<BExTbl1> pagedList = service.search(form);
		if (pagedList.getPageSet().getTotalCount() <= 0L) {
			BizErrorUtil.rejectOnSearchResultEmpty(binding);
			return withViewname(viewnameOfStart).build();
		}

		return withViewname(viewnameOfStart).addObject(pagedList).build();
	}

	@Override
	public ModelAndView download(BasicEx30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, HttpServletResponse response) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		adjustSortCondition(form);
		service.downloadXlsx(form, response);

		return null;
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(BasicEx30Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private boolean hasErrors(BasicEx30Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDtFrom() != null && form.getDtTo() != null) {
			if (form.getDtFrom().isAfter(form.getDtTo())) {
				BizErrorUtil.rejectValue(binding, Prop.DtFrom.getName(), BizErrorId.RangeFromTo, Prop.DtFrom.resolve(),
						Prop.DtTo.resolve());
			}
		}
		if (form.getTmFrom() != null && form.getTmTo() != null) {
			if (form.getTmFrom().isAfter(form.getTmTo())) {
				BizErrorUtil.rejectValue(binding, Prop.TmFrom.getName(), BizErrorId.RangeFromTo, Prop.TmFrom.resolve(),
						Prop.TmTo.resolve());
			}
		}
		if (form.getDtmFromD() == null && form.getDtmFromT() != null) {
			BizErrorUtil.rejectValue(binding, Prop.DtmFromD.getName(), BizErrorId.RequiredWhen,
					Prop.DtmFromD.resolve(), Prop.DtmFromT.resolve());
		}
		if (form.getDtmToD() == null && form.getDtmToT() != null) {
			BizErrorUtil.rejectValue(binding, Prop.DtmToD.getName(), BizErrorId.RequiredWhen, Prop.DtmToD.resolve(),
					Prop.DtmToT.resolve());
		}
		if (form.getDtmFromD() != null && form.getDtmFromT() != null && form.getDtmToD() != null
				&& form.getDtmToT() != null) {
			LocalDateTime dtmFrom = form.getDtmFromD().atTime(form.getDtmFromT());
			LocalDateTime dtmTo = form.getDtmToD().atTime(form.getDtmToT());
			if (dtmFrom.isAfter(dtmTo)) {
				BizErrorUtil.rejectValue(binding, Prop.DtmFromD.getName(), BizErrorId.RangeFromTo,
						Prop.DtmFromD.resolve(), Prop.DtmToD.resolve());
			}
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック

		return false;
	}

	private void adjustSortCondition(BasicEx30Form form) {

		if (form.getSort1() == null) {
			form.setSort1(new SortParam());
		}
		if (form.getSort1().getBy() == null) {
			form.getSort1().setBy(SORT_BY_00.getCodeValue());
		}
		if (form.getSort1().getOrder() == null) {
			form.getSort1().setOrder(SortOrder.ASC);
		}

		if (form.getSort2() == null) {
			form.setSort2(new SortParam());
		}
		if (form.getSort2().getBy() == null) {
			form.getSort2().setBy(SORT_BY_01.getCodeValue());
		}
		if (form.getSort2().getOrder() == null) {
			form.getSort2().setOrder(SortOrder.ASC);
		}
	}

}
