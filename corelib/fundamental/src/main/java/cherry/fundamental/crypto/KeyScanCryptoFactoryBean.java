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

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Random;

import javax.crypto.NoSuchPaddingException;

import org.springframework.core.io.Resource;

import cherry.elemental.crypto.Crypto;
import cherry.elemental.crypto.NullCrypto;
import cherry.elemental.crypto.VersionStrategy;
import cherry.elemental.crypto.VersionedCrypto;

public class KeyScanCryptoFactoryBean extends KeyScanFactoryBeanSupport<Crypto> {

	private Random random;

	public void setRandom(Random random) {
		this.random = random;
	}

	@Override
	protected Crypto createNull() {
		return new NullCrypto();
	}

	@Override
	protected Crypto createVersioned(VersionStrategy<byte[], Integer> strategy, Integer defaultVersion,
			Map<Integer, Crypto> map) {
		VersionedCrypto impl = new VersionedCrypto();
		impl.setVersionStrategy(strategy);
		impl.setDefaultVersion(defaultVersion);
		impl.setCryptoMap(map);
		return impl;
	}

	@Override
	protected Crypto createImpl(Crypto keyCrypto, Resource secretKey) throws IOException {
		return aesCrypto(keyCrypto, secretKey, random);
	}

	@Override
	protected Crypto createImpl(Resource publicKey, Resource privateKey, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {
		return rsaCrypto(publicKey, privateKey, password);
	}

}
