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

package cherry.foundation.bizerror;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import cherry.foundation.type.ICodeType;

public class BizErrorUtil {

	public static void reject(BindingResult binding, IBizErrId logicalError, Object... args) {
		binding.reject(logicalError.getId(), args, logicalError.getId());
	}

	public static void rejectValue(BindingResult binding, String name, IBizErrId logicError, Object... args) {
		binding.rejectValue(name, logicError.getId(), args, logicError.getId());
	}

	public static MessageSourceResolvable resolve(IBizErrId code, Object... args) {
		return resolve(code.getId(), args);
	}

	public static MessageSourceResolvable resolve(ICodeType<String> code, Object... args) {
		return resolve(code.getCodeValue(), args);
	}

	public static MessageSourceResolvable resolve(String code, Object... args) {
		return new DefaultMessageSourceResolvable(new String[] { code }, args);
	}

	public static void rejectOnOneTimeTokenError(BindingResult binding) {
		reject(binding, BizErrId.OneTimeTokenError);
	}

	public static void rejectOnOptimisticLockError(BindingResult binding) {
		reject(binding, BizErrId.OptimisticLockError);
	}

	public static void rejectOnSearchResultEmpty(BindingResult binding) {
		reject(binding, BizErrId.SearchResultEmpty);
	}

}
