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

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;
import static com.querydsl.core.types.dsl.Expressions.numberTemplate;
import static com.querydsl.sql.ColumnMetadata.getColumnMetadata;
import static com.querydsl.sql.SQLExpressions.select;
import static com.querydsl.sql.SQLExpressions.unionAll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cherry.elemental.paginate.PagedList;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.TimeExpression;
import com.querydsl.sql.ColumnMetadata;
import com.querydsl.sql.Union;

/**
 * Querydslサポート機能。<br />
 */
public class QuerydslUtil {

	/**
	 * SQL関数「CURRENT_DATE」。
	 */
	public static DateExpression<LocalDate> currentDate() {
		return DateExpression.currentDate(LocalDate.class);
	}

	/**
	 * SQL関数「CURRENT_TIME」。
	 */
	public static TimeExpression<LocalTime> currentTime() {
		return TimeExpression.currentTime(LocalTime.class);
	}

	/**
	 * SQL関数「CURRENT_TIMESTAMP」。
	 */
	public static DateTimeExpression<LocalDateTime> currentTimestamp() {
		return DateTimeExpression.currentTimestamp(LocalDateTime.class);
	}

	/**
	 * 指定された範囲の数列を形成するクエリをUNION ALLクエリの形態で作成する。
	 * 
	 * @param fm 数列の開始値。
	 * @param to 数列の終了値
	 * @param step 数列の増分。
	 * @param as 形成するクエリの列名。
	 * @return 数列を形成するUNION ALLクエリ。
	 */
	public static Union<Long> createSequenceByUnion(int fm, int to, int step, String as) {
		List<SubQueryExpression<Long>> list = new ArrayList<>((to - fm + 1) / step);
		for (int i = fm; i <= to; i += step) {
			if (StringUtils.isEmpty(as)) {
				list.add(select(numberTemplate(Long.class, String.valueOf(i))));
			} else {
				list.add(select(numberTemplate(Long.class, String.valueOf(i)).as(as)));
			}
		}
		return unionAll(list);
	}

	/**
	 * カラムメタデータに基づいて文字列の長さを調整する。<br />
	 * 具体的にはSQL型の文字数に収まるよう切り詰める。
	 * 
	 * @param value 対象文字列。
	 * @param path 調整先のカラム。
	 * @return 長さを調整した文字列。
	 */
	public static String adjustSize(String value, Path<?> path) {
		if (value == null) {
			return value;
		}
		ColumnMetadata metadata = getColumnMetadata(path);
		if (metadata.getSize() < 0) {
			return value;
		} else if (value.length() <= metadata.getSize()) {
			return value;
		} else {
			return value.substring(0, metadata.getSize());
		}
	}

	/**
	 * カラムメタデータに基づいて当該カラムの表記名を取得する。<br />
	 * 「AS」で別名が指定された場合は別名、そうでなければ、元のカラム名が得られる。カラム名を特定できない場合はnullが返却される。
	 * 
	 * @param expression 対象の式。
	 * @return 表記名。
	 */
	public static String getExpressionLabel(Expression<?> expression) {
		if (expression instanceof Operation) {
			Operation<?> op = (Operation<?>) expression;
			if (op.getOperator() == Ops.ALIAS) {
				return ((Path<?>) op.getArg(1)).getMetadata().getName();
			}
		}
		if (expression instanceof Path) {
			return getColumnMetadata((Path<?>) expression).getName();
		}
		return null;
	}

	/**
	 * {@link Tuple}の内容を{@link Map}の形態で取得する。<br />
	 * {@link Map}には、キーとしてカラム名をcamelCaseに変換した文字列、値として当該カラムの値そのままが格納される。
	 * 
	 * @param tuple 元データを保持する{@link Tuple}を指定する。
	 * @param expressions 元データのカラムを指定する。
	 * @return {@link Tuple}の内容を格納した{@link Map}。
	 */
	public static Map<String, ?> tupleToMap(Tuple tuple, Expression<?>... expressions) {
		Map<String, Object> map = new LinkedHashMap<>();
		for (Expression<?> expr : expressions) {
			String label = getExpressionLabel(expr);
			if (label != null) {
				map.put(UPPER_UNDERSCORE.to(LOWER_CAMEL, label), tuple.get(expr));
			}
		}
		return map;
	}

	/**
	 * {@link Tuple}のリストを{@link Map}のリストの形態で取得する。<br />
	 * {@link Tuple}の内容を格納した{@link Map}は、{@link #tupleToMap(Tuple, Expression...)}により取得する。
	 * 
	 * @param tupleList 元データを保持する{@link Tuple}のリストを指定する。
	 * @param expressions 元データのカラムを指定する。
	 * @return {@link Tuple}の内容を格納した{@link Map}のリスト。
	 */
	public static List<Map<String, ?>> tupleToMap(List<Tuple> tupleList, Expression<?>... expressions) {
		List<Map<String, ?>> list = new ArrayList<>(tupleList.size());
		for (Tuple tuple : tupleList) {
			list.add(tupleToMap(tuple, expressions));
		}
		return list;
	}

	/**
	 * {@link Tuple}のリストを{@link Map}のリストの形態で取得する。<br />
	 * {@link Tuple}の内容を格納した{@link Map}のリストは、{@link #tupleToMap(PagedList, Expression...)}により取得する。
	 * 
	 * @param pagedList 元データを保持する{@link Tuple}のリストを指定する。
	 * @param expressions 元データのカラムを指定する。
	 * @return {@link Tuple}の内容を格納した{@link Map}のリスト。
	 */
	public static PagedList<Map<String, ?>> tupleToMap(PagedList<Tuple> pagedList, Expression<?>... expressions) {
		PagedList<Map<String, ?>> list = new PagedList<>();
		list.setPageSet(pagedList.getPageSet());
		list.setList(tupleToMap(pagedList.getList(), expressions));
		return list;
	}

}
