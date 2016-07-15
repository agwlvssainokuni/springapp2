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

package cherry.gallery.web.applied.ex40;

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
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateForm", date = "2016-07-10T09:06:41+09:00")
public abstract class AppliedEx41FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@javax.validation.Valid()
	private java.util.List<AppliedEx41SubForm> item;

	@Getter
	public enum Prop {
		Text10("text10", "appliedEx41Form.text10"), //
		Text100("text100", "appliedEx41Form.text100"), //
		Int64("int64", "appliedEx41Form.int64"), //
		Decimal1("decimal1", "appliedEx41Form.decimal1"), //
		Decimal3("decimal3", "appliedEx41Form.decimal3"), //
		Dt("dt", "appliedEx41Form.dt"), //
		Tm("tm", "appliedEx41Form.tm"), //
		Dtm("dtm", "appliedEx41Form.dtm"), //
		LockVersion("lockVersion", "appliedEx41Form.lockVersion"), //
		Item("item", "appliedEx41Form.item"), //
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
