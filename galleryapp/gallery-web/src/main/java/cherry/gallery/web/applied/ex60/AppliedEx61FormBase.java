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

package cherry.gallery.web.applied.ex60;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSourceResolvable;

import cherry.fundamental.bizerror.BizErrorUtil;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateForm")
public abstract class AppliedEx61FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATE)
	private java.time.LocalDate dt;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.TIME)
	private java.time.LocalTime tm;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATETIME)
	private java.time.LocalDateTime dtm;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@javax.validation.Valid()
	private java.util.List<AppliedEx61SubForm> item;

	@Getter
	public enum Prop {
		Dt("dt", "appliedEx61Form.dt"), //
		Tm("tm", "appliedEx61Form.tm"), //
		Dtm("dtm", "appliedEx61Form.dtm"), //
		Item("item", "appliedEx61Form.item"), //
		DUMMY("dummy", "dummy");

		private final String name;
		private final String nameWithForm;

		private Prop(String name, String nameWithForm) {
			this.name = name;
			this.nameWithForm = nameWithForm;
		}

		public MessageSourceResolvable resolve() {
			return BizErrorUtil.resolve(nameWithForm);
		}
	}

}
