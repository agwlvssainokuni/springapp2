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

package cherry.foundation.crypto;

public class SecureTypeConfigurer {

	private SecureIntegerEncoder secureIntegerEncoder;
	private SecureLongEncoder secureLongEncoder;
	private SecureBigIntegerEncoder secureBigIntegerEncoder;
	private SecureBigDecimalEncoder secureBigDecimalEncoder;
	private SecureStringEncoder secureStringEncoder;

	public void setSecureIntegerEncoder(SecureIntegerEncoder secureIntegerEncoder) {
		this.secureIntegerEncoder = secureIntegerEncoder;
	}

	public void setSecureLongEncoder(SecureLongEncoder secureLongEncoder) {
		this.secureLongEncoder = secureLongEncoder;
	}

	public void setSecureBigIntegerEncoder(SecureBigIntegerEncoder secureBigIntegerEncoder) {
		this.secureBigIntegerEncoder = secureBigIntegerEncoder;
	}

	public void setSecureBigDecimalEncoder(SecureBigDecimalEncoder secureBigDecimalEncoder) {
		this.secureBigDecimalEncoder = secureBigDecimalEncoder;
	}

	public void setSecureStringEncoder(SecureStringEncoder secureStringEncoder) {
		this.secureStringEncoder = secureStringEncoder;
	}

	public void initialize() {
		SecureInteger.setEncoder(secureIntegerEncoder);
		SecureLong.setEncoder(secureLongEncoder);
		SecureBigInteger.setEncoder(secureBigIntegerEncoder);
		SecureBigDecimal.setEncoder(secureBigDecimalEncoder);
		SecureString.setEncoder(secureStringEncoder);
	}

}
