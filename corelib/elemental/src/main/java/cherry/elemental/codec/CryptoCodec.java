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

import cherry.elemental.crypto.Crypto;

public class CryptoCodec implements Codec<byte[], byte[]> {

	private Crypto crypto;

	public void setCrypto(Crypto crypto) {
		this.crypto = crypto;
	}

	@Override
	public byte[] encode(byte[] raw) {
		return crypto.encrypt(raw);
	}

	@Override
	public byte[] decode(byte[] encoded) {
		return crypto.decrypt(encoded);
	}

}
