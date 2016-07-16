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
public abstract class AppliedEx61SubFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.LONG)
	private Long id;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.CharTypeAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String text10;

	@cherry.fundamental.validator.MaxLength(value = 100, groups = { javax.validation.groups.Default.class })
	private String text100;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.LONG)
	private Long int64;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.NumberScale(1)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.DECIMAL_1)
	private java.math.BigDecimal decimal1;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.NumberScale(3)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.DECIMAL_3)
	private java.math.BigDecimal decimal3;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATE)
	private java.time.LocalDate dt;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.TIME)
	private java.time.LocalTime tm;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATETIME)
	private java.time.LocalDateTime dtm;

	private Integer lockVersion;

	@Getter
	public enum Prop {
		Id("id", "appliedEx61SubForm.id"), //
		Text10("text10", "appliedEx61SubForm.text10"), //
		Text100("text100", "appliedEx61SubForm.text100"), //
		Int64("int64", "appliedEx61SubForm.int64"), //
		Decimal1("decimal1", "appliedEx61SubForm.decimal1"), //
		Decimal3("decimal3", "appliedEx61SubForm.decimal3"), //
		Dt("dt", "appliedEx61SubForm.dt"), //
		Tm("tm", "appliedEx61SubForm.tm"), //
		Dtm("dtm", "appliedEx61SubForm.dtm"), //
		LockVersion("lockVersion", "appliedEx61SubForm.lockVersion"), //
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
