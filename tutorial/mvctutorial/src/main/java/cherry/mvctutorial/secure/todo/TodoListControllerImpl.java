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

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.elemental.paginate.PagedList;
import cherry.elemental.util.LocalDateTimeUtil;
import cherry.elemental.util.LocalDateUtil;
import cherry.fundamental.bizcal.BizDateTime;
import cherry.fundamental.download.DownloadOperation;
import cherry.fundamental.spring.webmvc.SortOrder;
import cherry.fundamental.spring.webmvc.SortParam;
import cherry.mvctutorial.CodeValue;
import cherry.mvctutorial.Config;
import cherry.mvctutorial.db.gen.query.BTodo;

@Controller
public class TodoListControllerImpl implements TodoListController {

	@Autowired
	private Config config;

	@Autowired
	private TodoService todoService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private DownloadOperation downloadOperation;

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		UriComponents redir;
		if (StringUtils.isNotEmpty(redirTo)) {
			redir = UriComponentsBuilder.fromPath(redirTo).build();
		} else {
			redir = fromMethodCall(on(TodoListController.class).start(null, null, null, null, null, null)).build();
		}
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(redir.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView start(TodoListForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		form.setDueDateTo(bizDateTime.today().plusDays(config.getDefaultOffsetOfDueDate()));
		form.setNotDone(true);
		SortParam param = new SortParam();
		param.setBy(CodeValue.TODO_LIST_SORT_BY.TODO_LIST_SORT_BY_ID.getCodeValue());
		param.setOrder(SortOrder.DESC);
		form.setSort(param);

		ModelAndView mav = new ModelAndView("secure/todo/list/start");
		return mav;
	}

	@Override
	public ModelAndView execute(TodoListForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView("secure/todo/list/start");
			return mav;
		}

		String loginId = auth.getName();
		SearchCondition cond = createCondition(form);
		long pageNo = form.getPno();
		long pageSz = form.getPsz() <= 0L ? config.getDefaultPageSize() : form.getPsz();

		PagedList<BTodo> result = todoService.searh(loginId, cond, pageNo, pageSz);

		ModelAndView mav = new ModelAndView("secure/todo/list/start");
		mav.addObject(result);
		return mav;
	}

	@Override
	public ModelAndView download(TodoListForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView("secure/todo/list/start");
			return mav;
		}

		downloadOperation.download(response, config.getContentType(), config.getCharset(), config.getFilename(),
				bizDateTime.now(), (stream) -> {
					try (Writer writer = new OutputStreamWriter(stream, config.getCharset())) {
						return todoService.export(writer, auth.getName(), createCondition(form));
					}
				});

		return null;
	}

	private SearchCondition createCondition(TodoListForm form) {

		SearchCondition cond = new SearchCondition();
		cond.setPostedFrom(LocalDateTimeUtil.rangeFrom(form.getPostedFrom()));
		cond.setPostedTo(LocalDateTimeUtil.rangeTo(form.getPostedTo(), ChronoUnit.SECONDS));
		cond.setDueDateFrom(LocalDateUtil.rangeFrom(form.getDueDateFrom()));
		cond.setDueDateTo(LocalDateUtil.rangeTo(form.getDueDateTo()));

		List<Integer> doneFlg = new ArrayList<>();
		if (form.isDone()) {
			doneFlg.add(1);
		}
		if (form.isNotDone()) {
			doneFlg.add(0);
		}
		cond.setDoneFlg(doneFlg);

		cond.setSort(form.getSort());

		return cond;
	}

}
