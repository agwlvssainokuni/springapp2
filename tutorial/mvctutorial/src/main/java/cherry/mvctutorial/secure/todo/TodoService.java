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

package cherry.mvctutorial.secure.todo;

import java.io.Writer;

import cherry.goods.paginate.PagedList;
import cherry.mvctutorial.db.gen.query.BTodo;

public interface TodoService {

	BTodo findById(String loginId, int id);

	Integer create(BTodo todo);

	boolean update(String loginId, int id, BTodo todo);

	PagedList<BTodo> searh(String loginId, SearchCondition cond, long pno, long psz);

	long export(Writer writer, String loginId, SearchCondition cond);

}
