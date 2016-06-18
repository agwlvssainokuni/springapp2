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

package cherry.foundation.batch.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ResourceBundleMessageSource;

import cherry.foundation.batch.ExitStatus;
import cherry.foundation.batch.IBatch;

import com.google.common.base.Optional;

/**
 * バッチプログラムを起動する機能を提供する。
 */
public class Launcher {

	/** アプリケーションコンテキスト定義ファイルのパスを指定するシステムプロパティ名。 */
	public static final String APPCTX_SYSPROP = "batch.appCtx";

	/** アプリケーションコンテキスト定義ファイルのパスのデフォルト値。 */
	public static final String APPCTX_DEFAULT = "classpath:spring/appctx.xml";

	/** ログ出力。 */
	private final Logger log = LoggerFactory.getLogger(getClass());

	/** アプリケーションコンテキスト定義ファイルのパス。 */
	private final String APPCTX = System.getProperty(APPCTX_SYSPROP, APPCTX_DEFAULT);

	/** 起動すべきバッチプログラムの識別名を保持する。 */
	private String batchId;

	/**
	 * バッチプログラム起動機能を生成する。
	 * 
	 * @param batchId 起動すべきバッチプログラムの識別名。
	 */
	public Launcher(String batchId) {
		this.batchId = batchId;
		MDC.put("batchId", batchId);
	}

	/**
	 * バッチプログラムを起動する。
	 * 
	 * @param args 起動時にコマンドラインに指定された引数。
	 * @return バッチプログラムの終了ステータス。
	 */
	public ExitStatus launch(String... args) {
		Msg msg = new Msg();
		try {

			log.info(msg.resolve("BATCH {0} STARTING", batchId));
			for (String arg : args) {
				log.info(msg.resolve("{0}", arg));
			}

			@SuppressWarnings("resource")
			ApplicationContext appCtx = new ClassPathXmlApplicationContext(APPCTX);
			IBatch batch = appCtx.getBean(batchId, IBatch.class);

			log.info(msg.resolve("BATCH {0} STARTED", batchId));

			try {

				ExitStatus status = batch.execute(args);

				switch (status) {
				case NORMAL:
					log.info(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status));
					break;
				case WARN:
					log.warn(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status));
					break;
				case ERROR:
					log.error(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status));
					break;
				default:
					log.error(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status));
					break;
				}

				return status;

			} catch (Exception ex) {

				Optional<ExitStatus> status = translateExceptionToExitStatus(appCtx, ex);
				if (!status.isPresent()) {
					throw ex;
				}

				switch (status.get()) {
				case NORMAL:
					log.info(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status.get()), ex);
					break;
				case WARN:
					log.warn(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status.get()), ex);
					break;
				case ERROR:
					log.error(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status.get()), ex);
					break;
				default:
					log.error(msg.resolve("BATCH {0} ENDED WITH {1}", batchId, status.get()), ex);
					break;
				}

				return status.get();
			}
		} catch (Exception ex) {
			log.error(msg.resolve("BATCH {0} ENDED WITH EXCEPTION", batchId), ex);
			return ExitStatus.FATAL;
		}
	}

	private Optional<ExitStatus> translateExceptionToExitStatus(ApplicationContext appCtx, Exception ex) {
		for (ExceptionExitStatusTranslator translator : appCtx.getBeansOfType(ExceptionExitStatusTranslator.class)
				.values()) {
			Optional<ExitStatus> status = translator.translate(ex);
			if (status.isPresent()) {
				return status;
			}
		}
		return Optional.absent();
	}

	/**
	 * バッチプログラムを起動する際に出力するログの文言を解決する機能を提供する。
	 */
	private static class Msg {

		/** 文言定義を保持する。 */
		private MessageSource msgSrc = createMessageSource();

		/**
		 * ログの文言を解決する。
		 * 
		 * @param code ログの文言の識別名。
		 * @param batchId バッチプログラムの識別名。
		 * @return ログの文言。
		 */
		public String resolve(String code, String batchId) {
			MessageSourceResolvable name = getResolvable(batchId);
			MessageSourceResolvable msg = getResolvable(code, name);
			return msgSrc.getMessage(msg, null);
		}

		/**
		 * ログの文言を解決する。
		 * 
		 * @param code ログの文言の識別名。
		 * @param batchId バッチプログラムの識別名。
		 * @param status 終了ステータス。
		 * @return ログの文言。
		 */
		public String resolve(String code, String batchId, ExitStatus status) {
			MessageSourceResolvable name = getResolvable(batchId, batchId);
			MessageSourceResolvable msg = getResolvable(code, name, status.name());
			return msgSrc.getMessage(msg, null);
		}

		/**
		 * 文言を解決するためのデータ構造 ({@link MessageSourceResolvable}) を生成する。
		 * 
		 * @param code 文言の識別名。
		 * @param args 文言に埋込むデータ。
		 * @return 文言。
		 */
		private MessageSourceResolvable getResolvable(String code, Object... args) {
			return new DefaultMessageSourceResolvable(new String[] { code }, args);
		}

		/**
		 * 文言定義を生成する。
		 * 
		 * @return 文言定義。
		 */
		private MessageSource createMessageSource() {
			ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
			msgSrc.setBasenames("message/launcher", "message/batchId");
			msgSrc.setUseCodeAsDefaultMessage(true);
			return msgSrc;
		}
	}

}
