/*
 * Copyright 2014,2016 agwlvssainokuni
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

package cherry.fundamental.querydsl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.TimePath;

/**
 * Querydslサポート機能。<br />
 * パスに関する操作を提供する。
 */
public class PathExpressions {

	/**
	 * テーブルに相当するパスを取得する。
	 * 
	 * @param variable テーブル別名。
	 * @return テーブルのパス。
	 */
	public static SimplePath<Tuple> simplePath(String variable) {
		return Expressions.path(Tuple.class, variable);
	}

	/**
	 * 真偽値カラムに相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラム別名。
	 * @return カラムのパス。
	 */
	public static BooleanPath booleanPath(Path<?> parent, String property) {
		return Expressions.booleanPath(parent, property);
	}

	/**
	 * 数値カラム ({@link Integer}) に相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラム別名。
	 * @return カラムのパス。
	 */
	public static NumberPath<Integer> intPath(Path<?> parent, String property) {
		return Expressions.numberPath(Integer.class, parent, property);
	}

	/**
	 * 数値カラム ({@link Long}) に相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラム別名。
	 * @return カラムのパス。
	 */
	public static NumberPath<Long> longPath(Path<?> parent, String property) {
		return Expressions.numberPath(Long.class, parent, property);
	}

	/**
	 * 数値カラム ({@link BigDecimal}) に相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラム別名。
	 * @return カラムのパス。
	 */
	public static NumberPath<BigDecimal> decimalPath(Path<?> parent, String property) {
		return Expressions.numberPath(BigDecimal.class, parent, property);
	}

	/**
	 * 文字列カラムに相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラムの別名。
	 * @return カラムのパス。
	 */
	public static StringPath stringPath(Path<?> parent, String property) {
		return Expressions.stringPath(parent, property);
	}

	/**
	 * 日付カラムに相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラムの別名。
	 * @return カラムのパス。
	 */
	public static DatePath<LocalDate> datePath(Path<?> parent, String property) {
		return Expressions.datePath(LocalDate.class, parent, property);
	}

	/**
	 * 時刻カラムに相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラムの別名。
	 * @return カラムのパス。
	 */
	public static TimePath<LocalTime> timePath(Path<?> parent, String property) {
		return Expressions.timePath(LocalTime.class, parent, property);
	}

	/**
	 * 日時カラムに相当するパスを取得する。
	 * 
	 * @param parent テーブルのパス。
	 * @param property カラムの別名。
	 * @return カラムのパス。
	 */
	public static DateTimePath<LocalDateTime> dateTimePath(Path<?> parent, String property) {
		return Expressions.dateTimePath(LocalDateTime.class, parent, property);
	}

}
