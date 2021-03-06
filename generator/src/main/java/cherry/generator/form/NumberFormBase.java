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

package cherry.generator.form;

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
public abstract class NumberFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.INTEGER)
	private Integer int1;

	@javax.validation.constraints.Min(value = 10, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.INTEGER)
	private Integer int2;

	@javax.validation.constraints.Max(value = 10, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.INTEGER)
	private Integer int3;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.LONG)
	private Long long1;

	@javax.validation.constraints.Min(value = 10, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.LONG)
	private Long long2;

	@javax.validation.constraints.Max(value = 10, groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.LONG)
	private Long long3;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.DECIMAL_X)
	private java.math.BigDecimal decimal1;

	@javax.validation.constraints.DecimalMin(value = "10", groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.DECIMAL_X)
	private java.math.BigDecimal decimal2;

	@javax.validation.constraints.DecimalMax(value = "10", groups = { javax.validation.groups.Default.class })
	@org.springframework.format.annotation.NumberFormat(pattern = FormatPattern.DECIMAL_X)
	private java.math.BigDecimal decimal3;

	@Getter
	public enum Prop {
		Int1("int1", "numberForm.int1"), //
		Int2("int2", "numberForm.int2"), //
		Int3("int3", "numberForm.int3"), //
		Long1("long1", "numberForm.long1"), //
		Long2("long2", "numberForm.long2"), //
		Long3("long3", "numberForm.long3"), //
		Decimal1("decimal1", "numberForm.decimal1"), //
		Decimal2("decimal2", "numberForm.decimal2"), //
		Decimal3("decimal3", "numberForm.decimal3"), //
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
