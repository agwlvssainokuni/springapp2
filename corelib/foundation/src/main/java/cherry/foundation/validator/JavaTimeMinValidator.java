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

package cherry.foundation.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JavaTimeMinValidator implements ConstraintValidator<JavaTimeMin, Temporal> {

	private String minValue;

	private boolean inclusive;

	@Override
	public void initialize(JavaTimeMin annotation) {
		minValue = annotation.value();
		inclusive = annotation.inclusive();
	}

	@Override
	public boolean isValid(Temporal value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else if (value instanceof LocalDate) {
			LocalDate min = LocalDate.parse(minValue);
			if (inclusive) {
				return min.isBefore((LocalDate) value) || min.isEqual((LocalDate) value);
			} else {
				return min.isBefore((LocalDate) value);
			}
		} else if (value instanceof LocalDateTime) {
			LocalDateTime min = LocalDateTime.parse(minValue);
			if (inclusive) {
				return min.isBefore((LocalDateTime) value) || min.isEqual((LocalDateTime) value);
			} else {
				return min.isBefore((LocalDateTime) value);
			}
		} else {
			return true;
		}
	}

}
