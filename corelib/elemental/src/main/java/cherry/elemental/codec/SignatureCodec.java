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

import cherry.elemental.crypto.Signature;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class SignatureCodec implements Codec<byte[], byte[]> {

	private Signature signature;

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	@Override
	public byte[] encode(byte[] raw) {
		byte[] sig = signature.sign(raw);
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeInt(sig.length);
		out.write(sig);
		out.writeInt(raw.length);
		out.write(raw);
		return out.toByteArray();
	}

	@Override
	public byte[] decode(byte[] encoded) {
		ByteArrayDataInput in = ByteStreams.newDataInput(encoded);
		int siglen = in.readInt();
		byte[] sig = new byte[siglen];
		in.readFully(sig, 0, siglen);
		int datlen = in.readInt();
		byte[] dat = new byte[datlen];
		in.readFully(dat);
		if (signature.verify(dat, sig)) {
			return dat;
		}
		throw new IllegalArgumentException("Verification failed");
	}

}
