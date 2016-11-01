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

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import cherry.elemental.crypto.Crypto;
import cherry.elemental.crypto.DefaultVersionStrategy;
import cherry.elemental.crypto.NullSignature;
import cherry.elemental.crypto.Signature;
import cherry.elemental.crypto.VersionedSignature;

public class KeyScanSignatureFactoryBean implements FactoryBean<Signature>, InitializingBean, ApplicationContextAware {

	private int versionLimit = Integer.MAX_VALUE;

	private String keyTop;

	private String publicKeyName = "pubkey.der";

	private String privateKeyName = "privkey.pk8";

	private String secretKeyName = "key.bin";

	private String currentMarkName = "current";

	private Map<Integer, String> privateKeyPasswordMap;

	private String defaultPrivateKeyPassword;

	private ApplicationContext applicationContext;

	private Signature signature;

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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		signature = build(keyTop, publicKeyName, privateKeyName, secretKeyName, currentMarkName, privateKeyPasswordMap);
	}

	@Override
	public Signature getObject() throws Exception {
		return signature;
	}

	@Override
	public Class<?> getObjectType() {
		return Signature.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	private Signature build(String keyTop, String publicKeyName, String privateKeyName, String secretKeyName,
			String currentMarkName, Map<Integer, String> privateKeyPasswordMap) throws Exception {

		Map<Integer, Pair<? extends Signature, Boolean>> map = doScan(keyTop, publicKeyName, privateKeyName,
				secretKeyName, currentMarkName, privateKeyPasswordMap);
		if (map.isEmpty()) {
			return new NullSignature();
		}

		Integer defaultVersion = null;
		int maxVersion = Integer.MIN_VALUE;
		Map<Integer, Signature> signatureMap = new LinkedHashMap<>(map.size());
		for (Map.Entry<Integer, Pair<? extends Signature, Boolean>> entry : map.entrySet()) {
			signatureMap.put(entry.getKey(), entry.getValue().getLeft());
			if (entry.getValue().getRight().booleanValue()) {
				defaultVersion = entry.getKey();
			}
			maxVersion = Math.max(maxVersion, entry.getKey().intValue());
		}

		if (defaultVersion == null) {
			defaultVersion = maxVersion;
		}

		VersionedSignature versionedSignature = new VersionedSignature();
		versionedSignature.setVersionStrategy(new DefaultVersionStrategy());
		versionedSignature.setDefaultVersion(defaultVersion);
		versionedSignature.setSignatureMap(signatureMap);
		return versionedSignature;
	}

	private Map<Integer, Pair<? extends Signature, Boolean>> doScan(String keyTop, String publicKeyName,
			String privateKeyName, String secretKeyName, String currentMarkName,
			Map<Integer, String> privateKeyPasswordMap) throws Exception {

		Map<Integer, Pair<? extends Signature, Boolean>> map = new LinkedHashMap<>();
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

				Signature signature;
				if (secretKey.exists()) {
					signature = hmacSignature(rsaCrypto(publicKey, privateKey, password), secretKey);
				} else {
					signature = rsaSignature(publicKey, privateKey, password);
				}

				map.put(version, Pair.of(signature, currentMark.exists()));
			} else if (secretKey.exists()) {
				map.put(version, Pair.of(hmacSignature(null, secretKey), currentMark.exists()));
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

	private RSASignatureSupport rsaSignature(Resource publicKey, Resource privateKey, String password) throws Exception {
		RSASignatureSupport impl = new RSASignatureSupport();
		impl.setPublicKeyResource(publicKey);
		impl.setPrivateKeyResource(privateKey);
		impl.setPrivateKeyPassword(password);
		impl.initialize();
		return impl;
	}

	private HmacSignatureSupport hmacSignature(Crypto keyCrypto, Resource secretKey) throws IOException {
		HmacSignatureSupport impl = new HmacSignatureSupport();
		impl.setKeyCrypto(keyCrypto);
		impl.setSecretKeyResource(secretKey);
		impl.initialize();
		return impl;
	}

}
