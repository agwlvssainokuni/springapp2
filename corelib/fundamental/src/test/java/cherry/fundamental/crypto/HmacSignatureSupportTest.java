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

package cherry.fundamental.crypto;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;

import cherry.elemental.crypto.NullCrypto;

public class HmacSignatureSupportTest {

	@Test
	public void testSignVerify() throws Exception {
		HmacSignatureSupport impl = createCrypto();
		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(245);
			byte[] signed = impl.sign(plain);
			assertNotEquals(plain, signed);
			assertTrue(impl.verify(plain, signed));
		}
	}

	private HmacSignatureSupport createCrypto() throws Exception {
		HmacSignatureSupport crypto = new HmacSignatureSupport();
		crypto.setAlgorithm("HmacSHA256");
		crypto.setKeyCrypto(new NullCrypto());
		crypto.setSecretKeyResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		crypto.initialize();
		return crypto;
	}

}
