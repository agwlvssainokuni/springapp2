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

package cherry.elemental.code;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumCodeUtil {

	public static <C, E extends ICodeType<C>> List<E> getCodeList(Class<E> type) {
		checkArgument(type.getEnumConstants() != null, "%s does not represent an enum type.", type.getSimpleName());
		List<E> list = new ArrayList<>();
		for (E e : type.getEnumConstants()) {
			list.add(e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <C, E extends ICodeType<C>> List<E> getCodeList(String typeName) {
		try {
			return getCodeList((Class<E>) Class.forName(typeName));
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static <C, E extends ICodeType<C>> Map<C, E> getCodeMap(Class<E> type) {
		checkArgument(type.getEnumConstants() != null, "%s does not represent an enum type.", type.getSimpleName());
		Map<C, E> map = new LinkedHashMap<>();
		for (E e : type.getEnumConstants()) {
			map.put(e.getCodeValue(), e);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <C, E extends ICodeType<C>> Map<C, E> getCodeMap(String typeName) {
		try {
			return getCodeMap((Class<E>) Class.forName(typeName));
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static <C, E extends ICodeType<C>> Map<C, E> getCodeMap(@SuppressWarnings("unchecked") E... es) {
		Map<C, E> map = new LinkedHashMap<>();
		for (E e : es) {
			map.put(e.getCodeValue(), e);
		}
		return map;
	}

}
