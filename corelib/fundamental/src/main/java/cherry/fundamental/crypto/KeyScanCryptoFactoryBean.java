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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import cherry.elemental.crypto.Crypto;
import cherry.elemental.crypto.DefaultVersionStrategy;
import cherry.elemental.crypto.NullCrypto;
import cherry.elemental.crypto.VersionedCrypto;

public class KeyScanCryptoFactoryBean implements FactoryBean<Crypto>, InitializingBean, ApplicationContextAware {

	private int versionLimit = Integer.MAX_VALUE;

	private String keyTop;

	private String publicKeyName = "pubkey.der";

	private String privateKeyName = "privkey.pk8";

	private String secretKeyName = "key.bin";

	private String currentMarkName = "current";

	private Map<Integer, String> privateKeyPasswordMap;

	private String defaultPrivateKeyPassword;

	private Random random;

	private ApplicationContext applicationContext;

	private Crypto crypto;

	public void setVersionLimit(int versionLimit) {
		this.versionLimit = versionLimit;
	}

	public void setKeyTop(String keyTop) {
		this.keyTop = keyTop;
	}

	public void setPublicKeyName(String publicKeyName) {
		this.publicKeyName = publicKeyName;
	}

	public void setPrivateKeyName(String privateKeyName) {
		this.privateKeyName = privateKeyName;
	}

	public void setSecretKeyName(String secretKeyName) {
		this.secretKeyName = secretKeyName;
	}

	public void setCurrentMarkName(String currentMarkName) {
		this.currentMarkName = currentMarkName;
	}

	public void setPrivateKeyPasswordMap(Map<Integer, String> privateKeyPasswordMap) {
		this.privateKeyPasswordMap = privateKeyPasswordMap;
	}

	public void setDefaultPrivateKeyPassword(String defaultPrivateKeyPassword) {
		this.defaultPrivateKeyPassword = defaultPrivateKeyPassword;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		crypto = build(keyTop, publicKeyName, privateKeyName, secretKeyName, currentMarkName, privateKeyPasswordMap,
				random);
	}

	@Override
	public Crypto getObject() throws Exception {
		return crypto;
	}

	@Override
	public Class<?> getObjectType() {
		return Crypto.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	private Crypto build(String keyTop, String publicKeyName, String privateKeyName, String secretKeyName,
			String currentMarkName, Map<Integer, String> privateKeyPasswordMap, Random random) throws Exception {

		Map<Integer, Pair<? extends Crypto, Boolean>> map = doScan(keyTop, publicKeyName, privateKeyName,
				secretKeyName, currentMarkName, privateKeyPasswordMap, random);
		if (map.isEmpty()) {
			return new NullCrypto();
		}

		Integer defaultVersion = null;
		int maxVersion = Integer.MIN_VALUE;
		Map<Integer, Crypto> cryptoMap = new LinkedHashMap<>(map.size());
		for (Map.Entry<Integer, Pair<? extends Crypto, Boolean>> entry : map.entrySet()) {
			cryptoMap.put(entry.getKey(), entry.getValue().getLeft());
			if (entry.getValue().getRight().booleanValue()) {
				defaultVersion = entry.getKey();
			}
			maxVersion = Math.max(maxVersion, entry.getKey().intValue());
		}

		if (defaultVersion == null) {
			defaultVersion = maxVersion;
		}

		VersionedCrypto versionedCrypto = new VersionedCrypto();
		versionedCrypto.setVersionStrategy(new DefaultVersionStrategy());
		versionedCrypto.setDefaultVersion(defaultVersion);
		versionedCrypto.setCryptoMap(cryptoMap);
		return versionedCrypto;
	}

	private Map<Integer, Pair<? extends Crypto, Boolean>> doScan(String keyTop, String publicKeyName,
			String privateKeyName, String secretKeyName, String currentMarkName,
			Map<Integer, String> privateKeyPasswordMap, Random random) throws Exception {

		Map<Integer, Pair<? extends Crypto, Boolean>> map = new LinkedHashMap<>();
		for (int version = 0; version < versionLimit; version++) {

			Resource publicKey = buildResource(keyTop, version, publicKeyName);
			Resource privateKey = buildResource(keyTop, version, privateKeyName);
			Resource secretKey = buildResource(keyTop, version, secretKeyName);
			Resource currentMark = buildResource(keyTop, version, currentMarkName);

			if (publicKey.exists() && privateKey.exists()) {

				String password = privateKeyPasswordMap.get(version);
				if (password == null) {
					password = defaultPrivateKeyPassword;
				}

				Crypto crypto;
				if (secretKey.exists()) {
					crypto = aesCrypto(rsaCrypto(publicKey, privateKey, password), secretKey, random);
				} else {
					crypto = rsaCrypto(publicKey, privateKey, password);
				}

				map.put(version, Pair.of(crypto, currentMark.exists()));
			} else if (secretKey.exists()) {
				map.put(version, Pair.of(aesCrypto(null, secretKey, random), currentMark.exists()));
			} else {
				break;
			}
		}

		return map;
	}

	private Resource buildResource(String keyTop, int version, String name) {
		StringBuilder sb = new StringBuilder(keyTop);
		if (!keyTop.endsWith("/")) {
			sb.append("/");
		}
		sb.append(version).append("/").append(name);
		return applicationContext.getResource(sb.toString());
	}

	private RSACryptoSupport rsaCrypto(Resource publicKey, Resource privateKey, String password) throws Exception {
		RSACryptoSupport impl = new RSACryptoSupport();
		impl.setPublicKeyResource(publicKey);
		impl.setPrivateKeyResource(privateKey);
		impl.setPrivateKeyPassword(password);
		impl.initialize();
		return impl;
	}

	private AESCryptoSupport aesCrypto(Crypto keyCrypto, Resource secretKey, Random random) throws IOException {
		AESCryptoSupport impl = new AESCryptoSupport();
		impl.setKeyCrypto(keyCrypto);
		impl.setSecretKeyResource(secretKey);
		impl.setRandom(random);
		impl.initialize();
		return impl;
	}

}
