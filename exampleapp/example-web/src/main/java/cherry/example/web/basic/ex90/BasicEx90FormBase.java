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

package cherry.example.web.basic.ex90;

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
public abstract class BasicEx90FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	private org.springframework.web.multipart.MultipartFile file;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@javax.validation.Valid()
	private java.nio.charset.Charset charset;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.DATE)
	private java.time.LocalDate dt;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.TIME)
	private java.time.LocalTime tm;

	@org.springframework.format.annotation.DateTimeFormat(pattern = cherry.example.web.FormatPattern.DATETIME)
	private java.time.LocalDateTime dtm;

	@Getter
	public enum Prop {
		File("file", "basicEx90Form.file"), //
		Charset("charset", "basicEx90Form.charset"), //
		Dt("dt", "basicEx90Form.dt"), //
		Tm("tm", "basicEx90Form.tm"), //
		Dtm("dtm", "basicEx90Form.dtm"), //
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
