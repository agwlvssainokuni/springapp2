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

package cherry.gallery.web;

@lombok.Getter()
@org.springframework.stereotype.Component("webConfig")
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateConfig", date = "2016-07-16T07:27:49+09:00")
public class Config {

	/** ページネーション「全頁数」: ページネーションの「全頁数」を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.app.paginator.totalCount}")
	private long totalCount;

	/** ページネーション「左側頁数」: ページネーションの「左側頁数」を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.app.paginator.lowerSideHint}")
	private long lowerSideHint;

	/** 初期ページサイズ: 初回表示の時のページサイズを構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.app.defaultPageSize}")
	private long defaultPageSize;

	/** 履歴サイズ: 保持する履歴の数を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.app.historySize}")
	private int historySize;

	/** 事例集「単項目チェックEx40」リスト1: アプリケーション事例集「単項目チェックEx40」のリスト1を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.validation.ex40.validationEx40list1}")
	private org.springframework.core.io.Resource validationEx40list1;

	/** 事例集「単項目チェックEx40」リスト2: アプリケーション事例集「単項目チェックEx40」のリスト2を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.validation.ex40.validationEx40list2}")
	private org.springframework.core.io.Resource validationEx40list2;

	/** 事例集「単項目チェックEx40」マップ1: アプリケーション事例集「単項目チェックEx40」のマップ1を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.validation.ex40.validationEx40map1}")
	private org.springframework.core.io.Resource validationEx40map1;

	/** 事例集「単項目チェックEx40」マップ2: アプリケーション事例集「単項目チェックEx40」のマップ2を構成する。 */
	@org.springframework.beans.factory.annotation.Value("${gallery-web.validation.ex40.validationEx40map2}")
	private org.springframework.core.io.Resource validationEx40map2;

}
