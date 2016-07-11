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

package cherry.goods.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.DefaultedMap;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class MdMaps {

	@FunctionalInterface
	public interface Factory {
		<K, V> Map<K, V> create();
	}

	public static final Factory HASHMAP = new Factory() {
		@Override
		public <K, V> Map<K, V> create() {
			return new HashMap<K, V>();
		}
	};

	public static final Factory LINKEDHASHMAP = new Factory() {
		@Override
		public <K, V> Map<K, V> create() {
			return new LinkedHashMap<K, V>();
		}
	};

	public static final Factory TREEMAP = new Factory() {
		@Override
		public <K, V> Map<K, V> create() {
			return new TreeMap<K, V>();
		}
	};

	public static <X1, R> Map<X1, R> defaulted1dMap(Factory factory, Transformer<? super X1, ? extends R> transformer) {
		return DefaultedMap.<X1, R> defaultedMap(factory.<X1, R> create(), x1 -> transformer.transform(x1));
	}

	public static <X1, X2, R> Map<X1, Map<X2, R>> defaulted2dMap(Factory factory,
			Transformer<Pair<? super X1, ? super X2>, ? extends R> transformer) {
		return defaulted1dMap(
				factory,
				x1 -> DefaultedMap.<X2, R> defaultedMap(factory.<X2, R> create(),
						x2 -> transformer.transform(Pair.of(x1, x2))));
	}

	public static <X1, X2, X3, R> Map<X1, Map<X2, Map<X3, R>>> defaulted3dMap(Factory factory,
			Transformer<Triple<? super X1, ? super X2, ? super X3>, ? extends R> transformer) {
		return defaulted2dMap(
				factory,
				p -> DefaultedMap.<X3, R> defaultedMap(factory.<X3, R> create(),
						x3 -> transformer.transform(Triple.of(p.getLeft(), p.getRight(), x3))));
	}

	public static <X1, X2, X3, X4, R> Map<X1, Map<X2, Map<X3, Map<X4, R>>>> defaulted4dMap(Factory factory,
			Transformer<Pair<Pair<? super X1, ? super X2>, Pair<? super X3, ? super X4>>, ? extends R> transformer) {
		return defaulted3dMap(
				factory,
				p -> DefaultedMap.<X4, R> defaultedMap(
						factory.<X4, R> create(),
						x4 -> transformer.transform(Pair.of(Pair.of(p.getLeft(), p.getMiddle()),
								Pair.of(p.getRight(), x4)))));
	}

	public static <X1, X2, X3, X4, X5, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, R>>>>> defaulted5dMap(
			Factory factory,
			Transformer<Pair<Triple<? super X1, ? super X2, ? super X3>, Pair<? super X4, ? super X5>>, ? extends R> transformer) {
		return defaulted4dMap(
				factory,
				p -> DefaultedMap.<X5, R> defaultedMap(
						factory.<X5, R> create(),
						x5 -> transformer.transform(Pair.of(
								Triple.of(p.getLeft().getLeft(), p.getLeft().getRight(), p.getRight().getLeft()),
								Pair.of(p.getRight().getRight(), x5)))));
	}

	public static <X1, X2, X3, X4, X5, X6, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, Map<X6, R>>>>>> defaulted6dMap(
			Factory factory,
			Transformer<Pair<Triple<? super X1, ? super X2, ? super X3>, Triple<? super X4, ? super X5, ? super X6>>, ? extends R> transformer) {
		return defaulted5dMap(
				factory,
				p -> DefaultedMap.<X6, R> defaultedMap(
						factory.<X6, R> create(),
						x6 -> transformer.transform(Pair.of(p.getLeft(),
								Triple.of(p.getRight().getLeft(), p.getRight().getRight(), x6)))));
	}

	public static <X1, R> Map<X1, R> defaulted1dMap(Factory factory, R defaultValue) {
		return defaulted1dMap(factory, p -> defaultValue);
	}

	public static <X1, X2, R> Map<X1, Map<X2, R>> defaulted2dMap(Factory factory, R defaultValue) {
		return defaulted2dMap(factory, p -> defaultValue);
	}

	public static <X1, X2, X3, R> Map<X1, Map<X2, Map<X3, R>>> defaulted3dMap(Factory factory, R defaultValue) {
		return defaulted3dMap(factory, p -> defaultValue);
	}

	public static <X1, X2, X3, X4, R> Map<X1, Map<X2, Map<X3, Map<X4, R>>>> defaulted4dMap(Factory factory,
			R defaultValue) {
		return defaulted4dMap(factory, p -> defaultValue);
	}

	public static <X1, X2, X3, X4, X5, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, R>>>>> defaulted5dMap(
			Factory factory, R defaultValue) {
		return defaulted5dMap(factory, p -> defaultValue);
	}

	public static <X1, X2, X3, X4, X5, X6, R> Map<X1, Map<X2, Map<X3, Map<X4, Map<X5, Map<X6, R>>>>>> defaulted6dMap(
			Factory factory, R defaultValue) {
		return defaulted6dMap(factory, p -> defaultValue);
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
