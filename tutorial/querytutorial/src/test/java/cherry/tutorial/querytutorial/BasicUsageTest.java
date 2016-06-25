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

import cherry.querytutorial.db.gen.query.BAuthor;
import cherry.querytutorial.db.gen.query.QAuthor;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-querytutorial.xml")
@Transactional
public class BasicUsageTest {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Test
	public void sec0101_Tupleとして取出す() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery<?> query = queryFactory.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = query.select(Projections.tuple(a.all())).fetch();

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			String valName = tuple.get(a.name);
			LocalDateTime valUpdatedAt = tuple.get(a.updatedAt);
			LocalDateTime valCreatedAt = tuple.get(a.createdAt);
			Integer valLockVersion = tuple.get(a.lockVersion);
			Integer valDeletedFlg = tuple.get(a.deletedFlg);
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}", valId,
					valLoginId, valName, valUpdatedAt, valCreatedAt, valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec0102_Tupleとして取出す() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery<?> query = queryFactory.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = query.select(a.all()).fetch();

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			String valName = tuple.get(a.name);
			LocalDateTime valUpdatedAt = tuple.get(a.updatedAt);
			LocalDateTime valCreatedAt = tuple.get(a.createdAt);
			Integer valLockVersion = tuple.get(a.lockVersion);
			Integer valDeletedFlg = tuple.get(a.deletedFlg);
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}", valId,
					valLoginId, valName, valUpdatedAt, valCreatedAt, valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec0103_Beanとして取出す_QBean() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		SQLQuery<?> query = queryFactory.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<BAuthor> list = query.select(Projections.bean(BAuthor.class, a.all())).fetch();

		/* クエリの結果を表示する。 */
		for (BAuthor entity : list) {
			Long valId = entity.getId();
			String valLoginId = entity.getLoginId();
			String valName = entity.getName();
			LocalDateTime valUpdatedAt = entity.getUpdatedAt();
			LocalDateTime valCreatedAt = entity.getCreatedAt();
			Integer valLockVersion = entity.getLockVersion();
			Integer valDeletedFlg = entity.getDeletedFlg();
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}", valId,
					valLoginId, valName, valUpdatedAt, valCreatedAt, valLockVersion, valDeletedFlg));
		}
	}

}
