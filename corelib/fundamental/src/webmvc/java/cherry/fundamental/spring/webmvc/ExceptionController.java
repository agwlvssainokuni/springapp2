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

package cherry.fundamental.spring.webmvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

}
