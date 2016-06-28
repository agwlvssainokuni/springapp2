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

package cherry.tutorial.querytutorial;

import static com.querydsl.core.types.dsl.Expressions.FOUR;
import static com.querydsl.core.types.dsl.Expressions.THREE;
import static com.querydsl.core.types.dsl.Expressions.TWO;
import static com.querydsl.core.types.dsl.Expressions.numberPath;
import static com.querydsl.core.types.dsl.Expressions.numberTemplate;
import static com.querydsl.sql.SQLExpressions.select;
import static com.querydsl.sql.SQLExpressions.selectOne;
import static com.querydsl.sql.SQLExpressions.selectZero;
import static java.lang.System.out;
import static java.text.MessageFormat.format;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.querytutorial.db.gen.query.QAuthor;
import cherry.querytutorial.db.gen.query.QTodo;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.Union;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-querytutorial.xml")
@Transactional
public class AdvancedUsageTest {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Test
	public void sec0501_GROUPBY() {

		QTodo a = new QTodo("a");
		SQLQuery<?> query = queryFactory.from(a);
		query.groupBy(a.postedBy);
		List<Tuple> list = query.select(
				Projections.tuple(a.postedBy, a.id.count(), a.id.sum(), a.postedAt.min(), a.postedAt.max())).fetch();

		for (Tuple tuple : list) {
			String valPostedBy = tuple.get(a.postedBy);
			Long valCount = tuple.get(a.id.count());
			Long valSum = tuple.get(a.id.sum());
			LocalDateTime valMinPostedAt = tuple.get(a.postedAt.min());
			LocalDateTime valMaxPostedAt = tuple.get(a.postedAt.max());
			out.println(format("{0}: COUNT(id)={1}, SUM(id)={2}, MIN(postedAt)={3}, MAX(postedAt)={4}", valPostedBy,
					valCount, valSum, valMinPostedAt, valMaxPostedAt));
		}
	}

	@Test
	public void sec0502_HAVING() {

		QTodo a = new QTodo("a");
		SQLQuery<?> query = queryFactory.from(a);
		query.groupBy(a.postedBy).having(a.id.count().gt(1), a.postedAt.max().lt(LocalDateTime.of(2015, 2, 1, 0, 0)));
		List<Tuple> list = query.select(
				Projections.tuple(a.postedBy, a.id.count(), a.id.sum(), a.postedAt.min(), a.postedAt.max())).fetch();

		for (Tuple tuple : list) {
			String valPostedBy = tuple.get(a.postedBy);
			Long valCount = tuple.get(a.id.count());
			Long valSum = tuple.get(a.id.sum());
			LocalDateTime valMinPostedAt = tuple.get(a.postedAt.min());
			LocalDateTime valMaxPostedAt = tuple.get(a.postedAt.max());
			out.println(format("{0}: COUNT(id)={1}, SUM(id)={2}, MIN(postedAt)={3}, MAX(postedAt)={4}", valPostedBy,
					valCount, valSum, valMinPostedAt, valMaxPostedAt));
		}
	}

	@Test
	public void sec0503_ORDERBY() {

		QTodo a = new QTodo("a");
		SQLQuery<?> query = queryFactory.from(a);
		query.groupBy(a.postedBy);
		query.orderBy(a.id.count().asc());
		List<Tuple> list = query.select(
				Projections.tuple(a.postedBy, a.id.count(), a.id.sum(), a.postedAt.min(), a.postedAt.max())).fetch();

		for (Tuple tuple : list) {
			String valPostedBy = tuple.get(a.postedBy);
			Long valCount = tuple.get(a.id.count());
			Long valSum = tuple.get(a.id.sum());
			LocalDateTime valMinPostedAt = tuple.get(a.postedAt.min());
			LocalDateTime valMaxPostedAt = tuple.get(a.postedAt.max());
			out.println(format("{0}: COUNT(id)={1}, SUM(id)={2}, MIN(postedAt)={3}, MAX(postedAt)={4}", valPostedBy,
					valCount, valSum, valMinPostedAt, valMaxPostedAt));
		}
	}

	@Test
	public void sec0504_UNION() {

		/* UNION句でつなげるSELECT文を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery<Tuple> queryA = SQLExpressions.selectFrom(a).select(a.id, a.postedBy.as("name"));
		QAuthor b = new QAuthor("b");
		SQLQuery<Tuple> queryB = SQLExpressions.selectFrom(b).select(b.id, b.loginId.as("name"));

		/* UNIONを組み立てる。 */
		@SuppressWarnings("unchecked")
		Union<Tuple> query = queryFactory.query().union(queryA, queryB);

		/* クエリを発行する。 */
		List<Tuple> list = query.fetch();

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valName = tuple.get(a.postedBy.as("name"));
			out.println(format("{0}: name={1}", valId, valName));
		}
	}

	@Test
	public void test0505_UNION() {

		@SuppressWarnings("unchecked")
		Union<Integer> digit = SQLExpressions.unionAll(selectZero(), selectOne(), select(TWO), select(THREE),
				select(FOUR), select(numberTemplate(Integer.class, "5")), select(numberTemplate(Integer.class, "6")),
				select(numberTemplate(Integer.class, "7")), select(numberTemplate(Integer.class, "8")),
				select(numberTemplate(Integer.class, "9")));

		NumberPath<Integer> a = numberPath(Integer.class, "a");
		NumberPath<Integer> b = numberPath(Integer.class, "b");
		NumberPath<Integer> c = numberPath(Integer.class, "c");
		NumberExpression<Integer> a0 = numberPath(Integer.class, a, "0");
		NumberExpression<Integer> b0 = numberPath(Integer.class, b, "0");
		NumberExpression<Integer> c0 = numberPath(Integer.class, c, "0");

		SQLQuery<Integer> query = queryFactory.query().from(digit.as(a), digit.as(b), digit.as(c))
				.select(a0.multiply(100).add(b0.multiply(10).add(c0)));

		for (Integer i : query.fetch()) {
			System.out.println(i);
		}
	}

}
