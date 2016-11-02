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
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.NoSuchPaddingException;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import cherry.elemental.crypto.AESCrypto;
import cherry.elemental.crypto.Crypto;
import cherry.elemental.crypto.DefaultVersionStrategy;
import cherry.elemental.crypto.HmacSignature;
import cherry.elemental.crypto.NullCrypto;
import cherry.elemental.crypto.RSACrypto;
import cherry.elemental.crypto.RSASignature;
import cherry.elemental.crypto.VersionStrategy;

import com.google.common.io.ByteStreams;

public abstract class KeyScanFactoryBeanSupport<T> implements InitializingBean, FactoryBean<T>, ApplicationContextAware {

	private String algorithmRsa = "RSA/ECB/PKCS1Padding";

	private String algorithmAes = "AES/CBC/PKCS5Padding";

	private String algorithmRsasig = "SHA256withRSA";

	private String algorithmHmac = "HmacSHA256";

	private int versionLimit = Integer.MAX_VALUE;

	private String keyTop;

	private String publicKeyName = "pubkey.der";

	private String privateKeyName = "privkey.pk8";

	private String secretKeyName = "key.bin";

	private String currentMarkName = "current";

	private Map<Integer, String> privateKeyPasswordMap;

	private String defaultPrivateKeyPassword;

	private ApplicationContext applicationContext;

	private T object;

	private Class<?> objectType;

	public void setAlgorithmRsa(String algorithmRsa) {
		this.algorithmRsa = algorithmRsa;
	}

	public void setAlgorithmAes(String algorithmAes) {
		this.algorithmAes = algorithmAes;
	}

	public void setAlgorithmRsasig(String algorithmRsasig) {
		this.algorithmRsasig = algorithmRsasig;
	}

	public void setAlgorithmHmac(String algorithmHmac) {
		this.algorithmHmac = algorithmHmac;
	}

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
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {
		object = createObject(versionLimit, keyTop, publicKeyName, privateKeyName, secretKeyName, currentMarkName,
				privateKeyPasswordMap, defaultPrivateKeyPassword);
		objectType = determineObjectType();
	}

	@Override
	public T getObject() {
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	protected abstract T createNull();

	protected abstract T createVersioned(VersionStrategy<byte[], Integer> strategy, Integer defaultVersion,
			Map<Integer, T> map);

	protected abstract T createImpl(Crypto keyCrypto, Resource secretKey) throws IOException;

	protected abstract T createImpl(Resource publicKey, Resource privateKey, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException;

	private Class<?> determineObjectType() {
		Type superClass = getClass().getGenericSuperclass();
		return (Class<?>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
	}

	private T createObject(int versionLimit, String keyTop, String publicKeyName, String privateKeyName,
			String secretKeyName, String currentMarkName, Map<Integer, String> privateKeyPasswordMap,
			String defaultPrivateKeyPassword) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {

		Map<Integer, Pair<T, Boolean>> map = doScan(versionLimit, keyTop, publicKeyName, privateKeyName, secretKeyName,
				currentMarkName, privateKeyPasswordMap, defaultPrivateKeyPassword);
		if (map.isEmpty()) {
			return createNull();
		}

		Integer defaultVersion = null;
		int maxVersion = Integer.MIN_VALUE;
		Map<Integer, T> cryptoMap = new LinkedHashMap<>(map.size());
		for (Map.Entry<Integer, Pair<T, Boolean>> entry : map.entrySet()) {
			cryptoMap.put(entry.getKey(), entry.getValue().getLeft());
			if (entry.getValue().getRight().booleanValue()) {
				defaultVersion = entry.getKey();
			}
			maxVersion = Math.max(maxVersion, entry.getKey().intValue());
		}

		if (defaultVersion == null) {
			defaultVersion = maxVersion;
		}

		return createVersioned(new DefaultVersionStrategy(), defaultVersion, cryptoMap);
	}

	private Map<Integer, Pair<T, Boolean>> doScan(int versionLimit, String keyTop, String publicKeyName,
			String privateKeyName, String secretKeyName, String currentMarkName,
			Map<Integer, String> privateKeyPasswordMap, String defaultPrivateKeyPassword)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {

		Map<Integer, Pair<T, Boolean>> map = new LinkedHashMap<>();
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

				T crypto;
				if (secretKey.exists()) {
					crypto = createImpl(rsaCrypto(publicKey, privateKey, password), secretKey);
				} else {
					crypto = createImpl(publicKey, privateKey, password);
				}

				map.put(version, Pair.of(crypto, currentMark.exists()));
			} else if (secretKey.exists()) {
				map.put(version, Pair.of(createImpl(null, secretKey), currentMark.exists()));
			} else {
				break;
			}
		}

		return map;
	}

	protected RSACrypto rsaCrypto(Resource publicKey, Resource privateKey, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {
		RSACrypto impl = applicationContext.getAutowireCapableBeanFactory().createBean(RSACrypto.class);
		impl.setAlgorithm(algorithmRsa);
		impl.setPublicKeyBytes(toByteArray(publicKey));
		if (password == null) {
			impl.setPrivateKeyBytes(toByteArray(privateKey));
		} else {
			impl.setPrivateKeyBytes(toByteArray(privateKey), password.toCharArray());
		}
		return impl;
	}

	protected AESCrypto aesCrypto(Crypto keyCrypto, Resource secretKey, Random random) throws IOException {
		AESCrypto impl = applicationContext.getAutowireCapableBeanFactory().createBean(AESCrypto.class);
		impl.setAlgorithm(algorithmAes);
		impl.setKeyCrypto(keyCrypto == null ? new NullCrypto() : keyCrypto);
		impl.setSecretKeyBytes(toByteArray(secretKey));
		impl.setRandom(random);
		return impl;
	}

	protected RSASignature rsaSignature(Resource publicKey, Resource privateKey, String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, InvalidKeySpecException, IOException {
		RSASignature impl = applicationContext.getAutowireCapableBeanFactory().createBean(RSASignature.class);
		impl.setAlgorithm(algorithmRsasig);
		impl.setPublicKeyBytes(toByteArray(publicKey));
		if (password == null) {
			impl.setPrivateKeyBytes(toByteArray(privateKey));
		} else {
			impl.setPrivateKeyBytes(toByteArray(privateKey), password.toCharArray());
		}
		return impl;
	}

	protected HmacSignature hmacSignature(Crypto keyCrypto, Resource secretKey) throws IOException {
		HmacSignature impl = applicationContext.getAutowireCapableBeanFactory().createBean(HmacSignature.class);
		impl.setAlgorithm(algorithmHmac);
		impl.setKeyCrypto(keyCrypto == null ? new NullCrypto() : keyCrypto);
		impl.setSecretKeyBytes(toByteArray(secretKey), algorithmHmac);
		return impl;
	}

	private Resource buildResource(String keyTop, int version, String name) {
		StringBuilder sb = new StringBuilder(keyTop);
		if (!keyTop.endsWith("/")) {
			sb.append("/");
		}
		sb.append(version).append("/").append(name);
		return applicationContext.getResource(sb.toString());
	}

	private byte[] toByteArray(Resource resource) throws IOException {
		try (InputStream in = resource.getInputStream()) {
			return ByteStreams.toByteArray(in);
		}
	}

}
