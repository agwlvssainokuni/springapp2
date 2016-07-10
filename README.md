SpringApp2
==========

プロジェクト概要
----------------
Spring framework、Querydsl、Gradle、SLF4J/logbackを組み合わせてどう使うかを、プロジェクトのテンプレートの形でまとめます。

検証課題
--------
*	Spring
	*	Spring framework
		*	型変換
			*	カスタムPropertyEditor
			*	ConversionService
				*	数値
				*	日付、時刻、日時 (JSR-310 = Java Time)
		*	AOP
			*	トレース (自前) 組込み
		*	トランザクション制御
			*	tx:annotation-driven
		*	JDBC
			*	名前付きパラメタによるSQL記述 (NamedParameterJdbcOperations)
		*	JMS
			*	ConnectionFactory
				*	jee:jndi-lookup
			*	JndiDestinationResolver
			*	TaskExecutor
				*	DefaultManagedTaskExecutor
			*	実行系
				*	キューに入れる
					*	JmsTemplate (JmsOperations)
				*	キューから受け取って実行
					*	MDP (Message-Driven POJOs)
					*	jms:listener-container
					*	jms:listener
			*	非同期実行の状態管理
				*	キュー処理と状態管理のトランザクションを分ける
			*	テストケース向けJmsOperations解決
				*	Mockitoで作成するモックをbeanとして定義
					*	factory-method=mock
					*	constructor-arg=JmsOperations
		*	文言管理 (MessageSource)
		*	キャッシュ制御
			*	cache:annotation-deiven
			*	Guava Cache
	*	Spring MVC
		*	実装パターン
			*	@Controller, @RequestMapping, @ModelAttribute
			*	アクションのメソッドの引数の典型的実装パターン
		*	ビュー
			*	JSTL
			*	Apache Tiles 3
			*	Jackson 2
				*	JSON
				*	XML
				*	YAML
		*	データバインディング
			*	型変換のカスタマイズ
		*	バリデーション
			*	Spring Validator
				*	@InitBinder
			*	Bean Validation (JSR-303)
				*	カスタムバリデーション
				*	@ReportAsSingleViolation
		*	ページネーション
			*	リンクの生成
				*	JSPタグでページネーションリンクを生成。
			*	クリック処理&ON/OFFをJavaScriptで組込み
		*	ファイルアップロード
		*	ファイルダウンロード
		*	ハンドラインタセプタ
			*	操作ログ (自前)
		*	ロケール解決
			*	ロケール切替え
			*	ロケール受渡し
	*	Spring Security
		*	Webアプリへの組込み
		*	認証プロバイダ
		*	パスワードエンコード
		*	Authentication受渡し
	*	Spring Mobile
		*	ビュー切替え (normal, tablet, mobile)
		*	サイトプリファレンス切替え
		*	サイトプリファレンス受渡し
	*	バッチフレームワーク (自前)
*	Gradle
	*	複数プロジェクト管理
		*	Webアプリケーション(-web)、バッチアプリケーション(-batch)のプロジェクトが、共通プロジェクト(-common)を参照。
			*	Eclipseに依存プロジェクトとして認識させる
		*	依存ライブラリの管理
			*	Eclipseに依存ライブラリを認識させる。
	*	SourceSets
		*	ツールで生成したコードを収容するためのSourceSetsを分離
	*	デリバリ向けビルドスクリプト
		*	デリバリ向けリソースファイル生成
		*	バッチ起動スクリプト生成
		*	デリバリ向けアーカイブ作成
*	Querydsl
	*	標準API
		*	SQLQueryFactory
		*	SQLQuery, SQLInsertClause, SQLUpdateClause, SQLDeleteClause
	*	カスタマイズ設定
		*	JSR-310 Java Time API対応
			*	Querydsl標準のJava Time対応はあるが、タイムゾーンの扱いを調整する場合に自前で用意。
	*	Spring framework統合
		*	com.querydsl:querydsl-sql-spring
			*	Provider<Connection>
	*	DBアクセスコード(メタデータ)生成
		*	com.querydsl:querydsl-maven-plugin (Mavenプラグイン)
		*	Flywayと組み合わせて、DBスキーマを作り(flyway:migrate)、DBアクセスコードを生成する(querydsl:export)。
		*	DBアクセスコードの生成についてはMavenを使う (Mavenの方がこなれている)。
*	SLF4J/Logback
	*	コンソール出力
	*	ファイル出力
	*	ファイルのローテーション
		*	サイズ
		*	日次
		*	保持履歴数
		*	圧縮
	*	プロパティファイルでパラメタを外出し
	*	自前Appender
		*	Fluent
*	コード生成
	*	DTO
	*	Form (単項目チェックのアノテーションありJava、項目名定義ファイル)
	*	区分値 (名enum、値enum、区分値マスタ投入SQLファイル)
	*	設定項目 (設定値保持クラス、設定値定義ファイル)
	*	ログ (ログID、ログ文言定義ファイル)
*	その他
	*	CSV入出力
	*	ETLのE(xport)とL(oad)
	*	暗号機能

ライセンス
==========
> Copyright 2014,2016 agwlvssainokuni
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>     http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
