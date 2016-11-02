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

import javax.crypto.NoSuchPaddingException;

import org.springframework.core.io.Resource;

import cherry.elemental.crypto.Crypto;
import cherry.elemental.crypto.NullSignature;
import cherry.elemental.crypto.Signature;
import cherry.elemental.crypto.VersionStrategy;
import cherry.elemental.crypto.VersionedSignature;

public class KeyScanSignatureFactoryBean extends KeyScanFactoryBeanSupport<Signature> {

	@Override
	protected Signature createNull() {
		return new NullSignature();
	}

	@Override
	protected Signature createVersioned(VersionStrategy<byte[], Integer> strategy, Integer defaultVersion,
			Map<Integer, Signature> map) {
		VersionedSignature impl = new VersionedSignature();
		impl.setVersionStrategy(strategy);
		impl.setDefaultVersion(defaultVersion);
		impl.setSignatureMap(map);
		return impl;
	}

	@Override
	protected Signature createImpl(Crypto keyCrypto, Resource secretKey) throws IOException {
		return hmacSignature(keyCrypto, secretKey);
	}

	@Override
	protected Signature createImpl(Resource publicKey, Resource privateKey, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {
		return rsaSignature(publicKey, privateKey, password);
	}

}
