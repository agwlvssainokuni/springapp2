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

package cherry.elemental.encdec;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import cherry.elemental.crypto.AESCrypto;
import cherry.elemental.crypto.HmacSignature;

public class ChainedEncdecTest {

	@Test
	public void testEncodeDecodeForString() {
		Encdec<String, String> impl = createForString();
		for (int i = 0; i < 1000; i++) {
			String raw = RandomStringUtils.randomAlphanumeric(1024);
			assertEquals(raw, impl.decode(impl.encode(raw)));
		}
	}

	@Test
	public void testEncodeDecodeForByteArray() {
		Encdec<byte[], byte[]> impl = createForByteArray();
		for (int i = 0; i < 1000; i++) {
			byte[] raw = RandomUtils.nextBytes(1024);
			assertArrayEquals(raw, impl.decode(impl.encode(raw)));
		}
	}

	protected Encdec<String, String> createForString() {
		return EncdecFactory.createEncdecChain(string(), asList(signature(), crypto(), gzip()), base64(true));
	}

	protected Encdec<byte[], byte[]> createForByteArray() {
		return EncdecFactory.createEncdecChain(asList(signature(), crypto(), gzip()));
	}

	private Encdec<String, byte[]> string() {
		StringEncdec impl = new StringEncdec();
		impl.setCharset(StandardCharsets.UTF_8);
		return impl;
	}

	private Encdec<byte[], byte[]> signature() {
		HmacSignature sig = new HmacSignature();
		sig.setAlgorithm("HmacSHA256");
		sig.setSecretKeyBytes(RandomUtils.nextBytes(32), "HmacSHA256");
		SignatureEncdec impl = new SignatureEncdec();
		impl.setSignature(sig);
		return impl;
	}

	private Encdec<byte[], byte[]> crypto() {
		AESCrypto crpt = new AESCrypto();
		crpt.setAlgorithm("AES/CBC/PKCS5Padding");
		crpt.setSecretKeyBytes(RandomUtils.nextBytes(16));
		CryptoEncdec impl = new CryptoEncdec();
		impl.setCrypto(crpt);
		return impl;
	}

	private Encdec<byte[], byte[]> gzip() {
		return new GzipEncdec();
	}

	private Encdec<byte[], String> base64(boolean base64url) {
		Base64Encdec impl = new Base64Encdec();
		impl.setBase64url(base64url);
		return impl;
	}

}
