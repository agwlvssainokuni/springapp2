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

package cherry.fundamental.bizerror;

public enum BizErrId implements IBizErrId {
	/** 多重ポスト判定NG。 */
	OneTimeTokenError,
	/** 楽観ロック判定NG。 */
	OptimisticLockError,
	/** 検索結果0件。 */
	SearchResultEmpty;

	@Override
	public String getId() {
		return name();
	}

}
