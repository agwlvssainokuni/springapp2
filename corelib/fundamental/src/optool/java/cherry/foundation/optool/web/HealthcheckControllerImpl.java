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

package cherry.foundation.optool.web;

import static com.querydsl.core.types.dsl.Expressions.constant;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

import com.querydsl.core.types.Expression;
import com.querydsl.sql.SQLQueryFactory;

public class HealthcheckControllerImpl implements HealthcheckController {

	private final Log log = LogFactory.getLog(getClass());

	private SQLQueryFactory queryFactory;

	private final Expression<String> ok = constant("OK");

	public void setQueryFactory(SQLQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Transactional()
	@Override
	public ResponseEntity<String> healthcheck() {
		try {
			String result = queryFactory.select(ok).fetchOne();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (DataAccessException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Failed to exequte query");
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
