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

package cherry.example.web.applied.ex50;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSourceResolvable;

import cherry.foundation.bizerror.BizErrorUtil;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class AppliedEx50FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.foundation.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.CharTypeAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String text10;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.example.web.FormatPattern.LONG)
	private Long int64From;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.example.web.FormatPattern.LONG)
	private Long int64To;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.NumberScale(1)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.example.web.FormatPattern.DECIMAL_1)
	private java.math.BigDecimal decimal1From;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.NumberScale(1)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.example.web.FormatPattern.DECIMAL_1)
	private java.math.BigDecimal decimal1To;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.NumberScale(3)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.example.web.FormatPattern.DECIMAL_3)
	private java.math.BigDecimal decimal3From;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.NumberScale(3)
	@org.springframework.format.annotation.NumberFormat(pattern = cherry.example.web.FormatPattern.DECIMAL_3)
	private java.math.BigDecimal decimal3To;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.DATE)
	private java.time.LocalDate dtFrom;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.DATE)
	private java.time.LocalDate dtTo;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.TIME)
	private java.time.LocalTime tmFrom;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.TIME)
	private java.time.LocalTime tmTo;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.DATE)
	private java.time.LocalDate dtmFromD;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.TIME)
	private java.time.LocalTime dtmFromT;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.DATE)
	private java.time.LocalDate dtmToD;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.TIME)
	private java.time.LocalTime dtmToT;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@javax.validation.Valid()
	private cherry.example.web.SortParam sort1;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@javax.validation.Valid()
	private cherry.example.web.SortParam sort2;

	private long pno = 0L;

	private long psz = 0L;

	@Getter
	public enum Prop {
		Text10("text10", "appliedEx50Form.text10"), //
		Int64From("int64From", "appliedEx50Form.int64From"), //
		Int64To("int64To", "appliedEx50Form.int64To"), //
		Decimal1From("decimal1From", "appliedEx50Form.decimal1From"), //
		Decimal1To("decimal1To", "appliedEx50Form.decimal1To"), //
		Decimal3From("decimal3From", "appliedEx50Form.decimal3From"), //
		Decimal3To("decimal3To", "appliedEx50Form.decimal3To"), //
		DtFrom("dtFrom", "appliedEx50Form.dtFrom"), //
		DtTo("dtTo", "appliedEx50Form.dtTo"), //
		TmFrom("tmFrom", "appliedEx50Form.tmFrom"), //
		TmTo("tmTo", "appliedEx50Form.tmTo"), //
		DtmFromD("dtmFromD", "appliedEx50Form.dtmFromD"), //
		DtmFromT("dtmFromT", "appliedEx50Form.dtmFromT"), //
		DtmToD("dtmToD", "appliedEx50Form.dtmToD"), //
		DtmToT("dtmToT", "appliedEx50Form.dtmToT"), //
		Sort1("sort1", "appliedEx50Form.sort1"), //
		Sort2("sort2", "appliedEx50Form.sort2"), //
		Pno("pno", "appliedEx50Form.pno"), //
		Psz("psz", "appliedEx50Form.psz"), //
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
