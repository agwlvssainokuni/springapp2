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

package cherry.elemental.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.DefaultedMap;

public class MdMaps {

	@FunctionalInterface
	public interface Creator {
		<K, V> Map<K, V> create();
	}

	public static final Creator HASHMAP = new Creator() {
		@Override
		public <K, V> Map<K, V> create() {
			return new HashMap<K, V>();
		}
	};

	public static final Creator LINKEDHASHMAP = new Creator() {
		@Override
		public <K, V> Map<K, V> create() {
			return new LinkedHashMap<K, V>();
		}
	};

	public static final Creator TREEMAP = new Creator() {
		@Override
		public <K, V> Map<K, V> create() {
			return new TreeMap<K, V>();
		}
	};

	public static <X1, R> Map<X1, R> defaulted1dMap(Creator creator, Transformer<? super X1, ? extends R> transformer) {
		return DefaultedMap.<X1, R> defaultedMap(creator.<X1, R> create(), x1 -> transformer.transform(x1));
	}

	public static <X1, X2, R> Map<X1, Map<X2, R>> defaulted2dMap(Creator creator,
			Transformer<Tuple2<? super X1, ? super X2>, ? extends R> transformer) {
		return defaulted1dMap(
				creator,
				x1 -> DefaultedMap.<X2, R> defaultedMap(creator.<X2, R> create(),
						x2 -> transformer.transform(Tuples.of(x1, x2))));
	}

	public static <X1, X2, X3, R> Map<X1, Map<X2, Map<X3, R>>> defaulted3dMap(Creator creator,
			Transformer<Tuple3<? super X1, ? super X2, ? super X3>, ? extends R> transformer) {
		return defaulted2dMap(
				creator,
				p -> DefaultedMap.<X3, R> defaultedMap(creator.<X3, R> create(),
						x3 -> transformer.transform(Tuples.of(p.getX1(), p.getX2(), x3))));
	}

	public static <X1, X2, X3, X4, R> Map<X1, Map<X2, Map<X3, Map<X4, R>>>> defaulted4dMap(Creator creator,
			Transformer<Tuple4<? super X1, ? super X2, ? super X3, ? super X4>, ? extends R> transformer) {
		return defaulted3dMap(
				creator,
				p -> DefaultedMap.<X4, R> defaultedMap(creator.<X4, R> create(),
						x4 -> transformer.transform(Tuples.of(p.getX1(), p.getX2(), p.getX3(), x4))));
	}

	public static <X1, X2, X3, X4, X5, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, R>>>>> defaulted5dMap(
			Creator creator,
			Transformer<Tuple5<? super X1, ? super X2, ? super X3, ? super X4, ? super X5>, ? extends R> transformer) {
		return defaulted4dMap(
				creator,
				p -> DefaultedMap.<X5, R> defaultedMap(creator.<X5, R> create(),
						x5 -> transformer.transform(Tuples.of(p.getX1(), p.getX2(), p.getX3(), p.getX4(), x5))));
	}

	public static <X1, X2, X3, X4, X5, X6, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, Map<X6, R>>>>>> defaulted6dMap(
			Creator creator,
			Transformer<Tuple6<? super X1, ? super X2, ? super X3, ? super X4, ? super X5, ? super X6>, ? extends R> transformer) {
		return defaulted5dMap(creator, p -> DefaultedMap.<X6, R> defaultedMap(creator.<X6, R> create(),
				x6 -> transformer.transform(Tuples.of(p.getX1(), p.getX2(), p.getX3(), p.getX4(), p.getX5(), x6))));
	}

	public static <X1, R> Map<X1, R> defaulted1dMap(Creator creator, Factory<R> factory) {
		return defaulted1dMap(creator, p -> factory.create());
	}

	public static <X1, X2, R> Map<X1, Map<X2, R>> defaulted2dMap(Creator creator, Factory<R> factory) {
		return defaulted2dMap(creator, p -> factory.create());
	}

	public static <X1, X2, X3, R> Map<X1, Map<X2, Map<X3, R>>> defaulted3dMap(Creator creator, Factory<R> factory) {
		return defaulted3dMap(creator, p -> factory.create());
	}

	public static <X1, X2, X3, X4, R> Map<X1, Map<X2, Map<X3, Map<X4, R>>>> defaulted4dMap(Creator creator,
			Factory<R> factory) {
		return defaulted4dMap(creator, p -> factory.create());
	}

	public static <X1, X2, X3, X4, X5, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, R>>>>> defaulted5dMap(
			Creator creator, Factory<R> factory) {
		return defaulted5dMap(creator, p -> factory.create());
	}

	public static <X1, X2, X3, X4, X5, X6, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, Map<X6, R>>>>>> defaulted6dMap(
			Creator creator, Factory<R> factory) {
		return defaulted6dMap(creator, p -> factory.create());
	}

	public static <X1, R> Map<X1, R> defaulted1dMap(Creator creator, R defaultValue) {
		return defaulted1dMap(creator, p -> defaultValue);
	}

	public static <X1, X2, R> Map<X1, Map<X2, R>> defaulted2dMap(Creator creator, R defaultValue) {
		return defaulted2dMap(creator, p -> defaultValue);
	}

	public static <X1, X2, X3, R> Map<X1, Map<X2, Map<X3, R>>> defaulted3dMap(Creator creator, R defaultValue) {
		return defaulted3dMap(creator, p -> defaultValue);
	}

	public static <X1, X2, X3, X4, R> Map<X1, Map<X2, Map<X3, Map<X4, R>>>> defaulted4dMap(Creator creator,
			R defaultValue) {
		return defaulted4dMap(creator, p -> defaultValue);
	}

	public static <X1, X2, X3, X4, X5, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, R>>>>> defaulted5dMap(
			Creator creator, R defaultValue) {
		return defaulted5dMap(creator, p -> defaultValue);
	}

	public static <X1, X2, X3, X4, X5, X6, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, Map<X6, R>>>>>> defaulted6dMap(
			Creator creator, R defaultValue) {
		return defaulted6dMap(creator, p -> defaultValue);
	}

	public static <X1, R> Optional<R> getOpt(Map<X1, R> map, X1 x1) {
		return Optional.ofNullable(map.get(x1));
	}

	public static <X1, X2, R> Optional<R> getOpt(Map<X1, Map<X2, R>> map, X1 x1, X2 x2) {
		return getOpt(map, x1).flatMap(m -> Optional.ofNullable(m.get(x2)));
	}

	public static <X1, X2, X3, R> Optional<R> getOpt(Map<X1, Map<X2, Map<X3, R>>> map, X1 x1, X2 x2, X3 x3) {
		return getOpt(map, x1, x2).flatMap(m -> Optional.ofNullable(m.get(x3)));
	}

	public static <X1, X2, X3, X4, R> Optional<R> getOpt(Map<X1, Map<X2, Map<X3, Map<X4, R>>>> map, X1 x1, X2 x2,
			X3 x3, X4 x4) {
		return getOpt(map, x1, x2, x3).flatMap(m -> Optional.ofNullable(m.get(x4)));
	}

	public static <X1, X2, X3, X4, X5, R> Optional<R> getOpt(Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, R>>>>> map, X1 x1,
			X2 x2, X3 x3, X4 x4, X5 x5) {
		return getOpt(map, x1, x2, x3, x4).flatMap(m -> Optional.ofNullable(m.get(x5)));
	}

	public static <X1, X2, X3, X4, X5, X6, R> Optional<R> getOpt(
			Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, Map<X6, R>>>>>> map, X1 x1, X2 x2, X3 x3, X4 x4, X5 x5, X6 x6) {
		return getOpt(map, x1, x2, x3, x4, x5).flatMap(m -> Optional.ofNullable(m.get(x6)));
	}

}