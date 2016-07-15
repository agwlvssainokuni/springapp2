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

package cherry.gallery.web.basic.ex30;

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
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateForm", date = "2016-07-16T07:28:22+09:00")
public abstract class BasicEx30FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.fundamental.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.CharTypeAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String text10;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.LONG)
	private Long int64From;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.LONG)
	private Long int64To;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.NumberScale(1)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.DECIMAL_1)
	private java.math.BigDecimal decimal1From;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.NumberScale(1)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.DECIMAL_1)
	private java.math.BigDecimal decimal1To;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.NumberScale(3)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.DECIMAL_3)
	private java.math.BigDecimal decimal3From;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.fundamental.validator.NumberScale(3)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.gallery.web.FormatPattern.DECIMAL_3)
	private java.math.BigDecimal decimal3To;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATE)
	private java.time.LocalDate dtFrom;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATE)
	private java.time.LocalDate dtTo;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.TIME)
	private java.time.LocalTime tmFrom;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.TIME)
	private java.time.LocalTime tmTo;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATE)
	private java.time.LocalDate dtmFromD;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.TIME)
	private java.time.LocalTime dtmFromT;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.DATE)
	private java.time.LocalDate dtmToD;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.gallery.web.FormatPattern.TIME)
	private java.time.LocalTime dtmToT;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@javax.validation.Valid()
	private cherry.fundamental.spring.webmvc.SortParam sort1;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@javax.validation.Valid()
	private cherry.fundamental.spring.webmvc.SortParam sort2;

	private long pno = 0L;

	private long psz = 0L;

	@Getter
	public enum Prop {
		Text10("text10", "basicEx30Form.text10"), //
		Int64From("int64From", "basicEx30Form.int64From"), //
		Int64To("int64To", "basicEx30Form.int64To"), //
		Decimal1From("decimal1From", "basicEx30Form.decimal1From"), //
		Decimal1To("decimal1To", "basicEx30Form.decimal1To"), //
		Decimal3From("decimal3From", "basicEx30Form.decimal3From"), //
		Decimal3To("decimal3To", "basicEx30Form.decimal3To"), //
		DtFrom("dtFrom", "basicEx30Form.dtFrom"), //
		DtTo("dtTo", "basicEx30Form.dtTo"), //
		TmFrom("tmFrom", "basicEx30Form.tmFrom"), //
		TmTo("tmTo", "basicEx30Form.tmTo"), //
		DtmFromD("dtmFromD", "basicEx30Form.dtmFromD"), //
		DtmFromT("dtmFromT", "basicEx30Form.dtmFromT"), //
		DtmToD("dtmToD", "basicEx30Form.dtmToD"), //
		DtmToT("dtmToT", "basicEx30Form.dtmToT"), //
		Sort1("sort1", "basicEx30Form.sort1"), //
		Sort2("sort2", "basicEx30Form.sort2"), //
		Pno("pno", "basicEx30Form.pno"), //
		Psz("psz", "basicEx30Form.psz"), //
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
