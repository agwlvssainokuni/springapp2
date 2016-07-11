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

package cherry.foundation.spring.webmvc;

import static cherry.foundation.spring.webmvc.ModelAndViewBuilder.redirect;

import java.util.LinkedList;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

public class NaviControllerImpl implements NaviController {

	private String home;

	private int historySize;

	public void setHome(String home) {
		this.home = home;
	}

	public void setHistorySize(int historySize) {
		this.historySize = historySize;
	}

	@Override
	public ModelAndView back(NaviForm form, BindingResult binding) {
		String uri = restoreHistory(form);
		if (StringUtils.isBlank(uri)) {
			return redirect(home).build();
		}
		return redirect(uri).build();
	}

	@Override
	public ModelAndView save1(String to, String fm, NaviForm form, BindingResult binding) {
		saveHistory(form, fm);
		return redirect(to).build();
	}

	@Override
	public ModelAndView save2(String to, String referer, NaviForm form, BindingResult binding) {
		saveHistory(form, referer);
		return redirect(to).build();
	}

	@Override
	public ModelAndView clear(String to, NaviForm form, BindingResult binding, SessionStatus status) {
		status.setComplete();
		return redirect(to).build();
	}

	private void saveHistory(NaviForm form, String uri) {
		if (form.getHistory() == null) {
			form.setHistory(new LinkedList<String>());
		}
		form.getHistory().add(uri);
		while (form.getHistory().size() > historySize) {
			form.getHistory().remove(0);
		}
	}

	private String restoreHistory(NaviForm form) {
		if (CollectionUtils.isEmpty(form.getHistory())) {
			return null;
		}
		return form.getHistory().remove(form.getHistory().size() - 1);
	}

}
