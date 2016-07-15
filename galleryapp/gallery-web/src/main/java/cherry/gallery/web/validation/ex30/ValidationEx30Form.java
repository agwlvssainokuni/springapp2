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

package cherry.gallery.web.validation.ex30;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import cherry.fundamental.validator.JavaTimeMax;
import cherry.fundamental.validator.JavaTimeMin;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValidationEx30Form {

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@NotNull()
	private LocalDateTime notnull;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime ldtmiso;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime ldtmpat;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate ldtiso;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate ldtpat;

	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime ltmiso;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime ltmpat;

	@JavaTimeMin(value = "1900-01-01T00:00:00", inclusive = true)
	private LocalDateTime ldtmmin19000101in;

	@JavaTimeMax(value = "3000-01-01T00:00:00", inclusive = true)
	private LocalDateTime ldtmmax30000101in;

	@JavaTimeMin(value = "1900-01-01T00:00:00", inclusive = false)
	private LocalDateTime ldtmmin19000101ex;

	@JavaTimeMax(value = "3000-01-01T00:00:00", inclusive = false)
	private LocalDateTime ldtmmax30000101ex;

	@JavaTimeMin(value = "1900-01-01", inclusive = true)
	private LocalDate ldtmin19000101in;

	@JavaTimeMax(value = "3000-01-01", inclusive = true)
	private LocalDate ldtmax30000101in;

	@JavaTimeMin(value = "1900-01-01", inclusive = false)
	private LocalDate ldtmin19000101ex;

	@JavaTimeMax(value = "3000-01-01", inclusive = false)
	private LocalDate ldtmax30000101ex;

}
