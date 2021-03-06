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

package cherry.fundamental.crypto;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;

public class AESDeterministicCryptoSupportTest {

	@Test
	public void testDefault() throws Exception {

		AESDeterministicCryptoSupport crypto = new AESDeterministicCryptoSupport();
		crypto.setSecretKeyResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		crypto.setInitVectorResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		crypto.initialize();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testCBC() throws Exception {

		AESDeterministicCryptoSupport crypto = new AESDeterministicCryptoSupport();
		crypto.setAlgorithm("AES/CBC/PKCS5Padding");
		crypto.setSecretKeyResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		crypto.setInitVectorResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		crypto.initialize();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testECB() throws Exception {

		AESDeterministicCryptoSupport crypto = new AESDeterministicCryptoSupport();
		crypto.setAlgorithm("AES/ECB/PKCS5Padding");
		crypto.setSecretKeyResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		crypto.initialize();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc = crypto.encrypt(plain);
			byte[] dec = crypto.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testUsingKeyCipherHelper() throws Exception {

		byte[] key = RandomUtils.nextBytes(16);
		byte[] iv = RandomUtils.nextBytes(16);

		AESDeterministicCryptoSupport crypto0 = new AESDeterministicCryptoSupport();
		crypto0.setSecretKeyResource(new ByteArrayResource(key));
		crypto0.setInitVectorResource(new ByteArrayResource(iv));
		crypto0.initialize();

		AESDeterministicCryptoSupport keyCrypto = new AESDeterministicCryptoSupport();
		keyCrypto.setSecretKeyResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		keyCrypto.setInitVectorResource(new ByteArrayResource(RandomUtils.nextBytes(16)));
		keyCrypto.initialize();

		AESDeterministicCryptoSupport crypto1 = new AESDeterministicCryptoSupport();
		crypto1.setKeyCrypto(keyCrypto);
		crypto1.setSecretKeyResource(new ByteArrayResource(keyCrypto.encrypt(key)));
		crypto1.setInitVectorResource(new ByteArrayResource(keyCrypto.encrypt(iv)));
		crypto1.initialize();

		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(1024);
			byte[] enc0 = crypto0.encrypt(plain);
			byte[] enc1 = crypto1.encrypt(plain);
			assertThat(enc1, is(enc0));
			byte[] dec0 = crypto0.decrypt(enc0);
			byte[] dec1 = crypto1.decrypt(enc1);
			assertThat(dec0, is(plain));
			assertThat(dec1, is(plain));
		}
	}

}
