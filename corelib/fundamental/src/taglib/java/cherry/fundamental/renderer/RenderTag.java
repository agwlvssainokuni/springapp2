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

package cherry.fundamental.renderer;

import java.io.IOException;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import cherry.fundamental.render.ObjectRenderer;

public class RenderTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private Object value;

	private Integer mode;

	public void setValue(Object value) {
		this.value = value;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	@Override
	protected int doStartTagInternal() throws IOException {
		ObjectRenderer renderer = getRequestContext().getWebApplicationContext().getBean(ObjectRenderer.class);
		pageContext.getOut().write(renderer.render(value, mode));
		return SKIP_BODY;
	}

}
