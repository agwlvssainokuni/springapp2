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

public interface Encdec<R, E> {

	E encode(R raw);

	R decode(E encoded);

	default <F> Encdec<R, F> andThen(Encdec<E, F> encdec) {
		return new Encdec<R, F>() {

			@Override
			public F encode(R raw) {
				return encdec.encode(Encdec.this.encode(raw));
			}

			@Override
			public R decode(F encoded) {
				return Encdec.this.decode(encdec.decode(encoded));
			}
		};
	}

	default <S> Encdec<S, E> compose(Encdec<S, R> encdec) {
		return new Encdec<S, E>() {

			@Override
			public E encode(S raw) {
				return Encdec.this.encode(encdec.encode(raw));
			}

			@Override
			public S decode(E encoded) {
				return encdec.decode(Encdec.this.decode(encoded));
			}
		};
	}

}
