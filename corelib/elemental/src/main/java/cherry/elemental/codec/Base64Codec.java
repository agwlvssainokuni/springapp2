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

package cherry.elemental.codec;

import java.util.Base64;

public class Base64Codec implements Codec<byte[], String> {

	@Override
	public String encode(byte[] raw) {
		return Base64.getEncoder().encodeToString(raw);
	}

	@Override
	public byte[] decode(String encoded) {
		return Base64.getDecoder().decode(encoded);
	}

}
