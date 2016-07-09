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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.bizcal.BizDateTime;
import cherry.foundation.bizerror.BizErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;
import cherry.mvctutorial.Config;
import cherry.mvctutorial.db.gen.query.BTodo;

@Controller
public class TodoCreateControllerImpl implements TodoCreateController {

	@Autowired
	private Config config;

	@Autowired
	private TodoService todoService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request) {
		UriComponents redirTo = fromMethodCall(on(TodoCreateController.class).start(null, null, null, null, null, null))
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(redirTo.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView start(TodoCreateForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		form.setDueDate(bizDateTime.today().plusDays(config.getDefaultOffsetOfDueDate()));
		ModelAndView mav = new ModelAndView("secure/todo/create/start");
		return mav;
	}

	@Override
	public ModelAndView confirm(TodoCreateForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView("secure/todo/create/start");
			return mav;
		}

		ModelAndView mav = new ModelAndView("secure/todo/create/confirm");
		return mav;
	}

	@Override
	public ModelAndView execute(TodoCreateForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView("secure/todo/create/start");
			return mav;
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			BizErrorUtil.rejectOnOneTimeTokenError(binding);
			ModelAndView mav = new ModelAndView("secure/todo/create/start");
			return mav;
		}

		BTodo todo = new BTodo();
		todo.setPostedBy(auth.getName());
		todo.setPostedAt(bizDateTime.now());
		todo.setDueDate(form.getDueDate());
		todo.setDescription(form.getDescription());

		Integer id = todoService.create(todo);
		if (id == null) {
			throw new IllegalStateException("Failed to create todo record: " + todo.toString());
		}

		UriComponents redirTo = fromMethodCall(on(TodoCreateController.class).finish(id, null, null, null, null))
				.replaceQueryParam("id", id).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(redirTo.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(int id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		ModelAndView mav = new ModelAndView("secure/todo/create/finish");
		BTodo todo = todoService.findById(auth.getName(), id);
		if (todo != null) {
			mav.addObject(todo);
		}
		return mav;
	}

}
