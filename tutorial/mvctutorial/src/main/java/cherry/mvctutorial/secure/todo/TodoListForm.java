/*
 * Copyright 2014,2016 agwlvssainokuni
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

package cherry.mvctutorial.secure.todo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

import cherry.fundamental.spring.webmvc.FormatPattern;
import cherry.fundamental.spring.webmvc.SortParam;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class TodoListForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = FormatPattern.DATETIME)
	private LocalDateTime postedFrom;

	@DateTimeFormat(pattern = FormatPattern.DATETIME)
	private LocalDateTime postedTo;

	@DateTimeFormat(pattern = FormatPattern.DATE)
	private LocalDate dueDateFrom;

	@DateTimeFormat(pattern = FormatPattern.DATE)
	private LocalDate dueDateTo;

	private boolean done;

	private boolean notDone;

	private long pno;

	private long psz;

	@NotNull()
	@Valid()
	private SortParam sort;

}
