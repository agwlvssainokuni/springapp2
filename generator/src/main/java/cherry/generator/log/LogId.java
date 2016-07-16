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

package cherry.generator.log;

import cherry.elemental.log.ILogId;
import cherry.elemental.log.Level;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateLog")
public enum LogId implements ILogId {
	/** LOG00001 [INFO]: お知らせレベルのログです。 */
	LOG00001("LOG00001", Level.INFO),
	/** LOG00002 [WARN]: 警告レベルのログです。 */
	LOG00002("LOG00002", Level.WARN),
	/** LOG00003 [ERROR]: 異常レベルのログです。 */
	LOG00003("LOG00003", Level.ERROR),
	/** LOG00004 [INFO]: お知らせレベルのログ、{0} ←埋め込み。 */
	LOG00004("LOG00004", Level.INFO),
	/** LOG00005 [WARN]: 警告レベルのログ、{0} ←埋め込み。 */
	LOG00005("LOG00005", Level.WARN),
	/** LOG00006 [ERROR]: 異常レベルのログ、{0} ←埋め込み。 */
	LOG00006("LOG00006", Level.ERROR),
	/* 生成ツールの都合による定義。 */
	DUMMY("DUMMY", Level.ERROR);

	private String id;

	private Level level;

	private LogId(String id, Level level) {
		this.id = id;
		this.level = level;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Level getLevel() {
		return level;
	}

}
