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
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.foundation.bizcal.BizDateTime;
import cherry.foundation.bizerror.BizErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;
import cherry.foundation.spring.webmvc.Http4xxChecker;
import cherry.mvctutorial.db.gen.query.BTodo;

@Controller
public class TodoEditControllerImpl implements TodoEditController {

	@Autowired
	private TodoService todoService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Override
	public ModelAndView init(String redirTo, int id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		UriComponents redir;
		if (StringUtils.isNotEmpty(redirTo)) {
			redir = UriComponentsBuilder.fromPath(redirTo).build();
		} else {
			redir = fromMethodCall(on(TodoEditController.class).start(id, null, null, null, null, null, null))
					.replaceQueryParam("id", id).build();
		}
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(redir.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView start(int id, TodoEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		BTodo todo = todoService.findById(auth.getName(), id);
		Http4xxChecker.throwNotFoundIfNull(todo, BTodo.class, auth.getName(), id);

		form.setDueDate(todo.getDueDate());
		form.setDescription(todo.getDescription());
		form.setDoneFlg(todo.getDoneFlg() != 0);
		form.setDoneAt(todo.getDoneAt());
		form.setLockVersion(todo.getLockVersion());

		ModelAndView mav = new ModelAndView("secure/todo/edit/start");
		return mav;
	}

	@Override
	public ModelAndView execute(int id, TodoEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		BTodo todo = todoService.findById(auth.getName(), id);
		Http4xxChecker.throwNotFoundIfNull(todo, BTodo.class, auth.getName(), id);

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView("secure/todo/edit/start");
			return mav;
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			BizErrorUtil.rejectOnOneTimeTokenError(binding);
			ModelAndView mav = new ModelAndView("secure/todo/edit/start");
			return mav;
		}

		BTodo newTodo = new BTodo();
		newTodo.setDueDate(form.getDueDate());
		newTodo.setDescription(form.getDescription());
		newTodo.setDoneFlg(form.isDoneFlg() ? 1 : 0);
		if (form.isDoneFlg() && todo.getDoneFlg() == 0) {
			newTodo.setDoneAt(bizDateTime.now());
		} else if (!form.isDoneFlg() && todo.getDoneFlg() != 0) {
			newTodo.setDoneAt(null);
		} else {
			newTodo.setDoneAt(todo.getDoneAt());
		}
		newTodo.setLockVersion(form.getLockVersion());

		boolean result = todoService.update(auth.getName(), id, newTodo);
		if (!result) {
			BizErrorUtil.rejectOnOptimisticLockError(binding);
			ModelAndView mav = new ModelAndView("secure/todo/edit/start");
			return mav;
		}

		redirAttr.addFlashAttribute("updated", true);

		UriComponents redirTo = fromMethodCall(
				on(TodoEditController.class).start(id, null, null, null, null, null, null)).replaceQueryParam("id", id)
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(redirTo.toUriString(), true));
		return mav;
	}

}
