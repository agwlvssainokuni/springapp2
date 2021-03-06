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

package cherry.fundamental.querydsl;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;

import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.types.Type;

public class CustomizingSQLTemplatesFactoryBean implements FactoryBean<SQLTemplates> {

	private SQLTemplates templates;

	private List<Type<?>> customTypes;

	public void setTemplates(SQLTemplates templates) {
		this.templates = templates;
	}

	public void setCustomTypes(List<Type<?>> customTypes) {
		this.customTypes = customTypes;
	}

	public void initialize() throws Exception {
		if (customTypes != null) {
			Method addCustomType = SQLTemplates.class.getDeclaredMethod("addCustomType", Type.class);
			addCustomType.setAccessible(true);
			for (Type<?> t : customTypes) {
				addCustomType.invoke(templates, t);
			}
		}
	}

	@Override
	public SQLTemplates getObject() throws Exception {
		return templates;
	}

	@Override
	public Class<?> getObjectType() {
		return SQLTemplates.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
