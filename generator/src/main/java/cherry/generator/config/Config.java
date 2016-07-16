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

package cherry.generator.config;

@lombok.Getter()
@org.springframework.stereotype.Component("generatorConfig")
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateConfig")
public class Config {

	/** 整数設定: 整数値を設定する。 */
	@org.springframework.beans.factory.annotation.Value("${generator.config.number1}")
	private int number1;

	/** 小数設定: 小数値を設定する。 */
	@org.springframework.beans.factory.annotation.Value("${generator.config.number2}")
	private java.math.BigDecimal number2;

	/** 文字列設定: 文字列値を設定する。 */
	@org.springframework.beans.factory.annotation.Value("${generator.config.string1}")
	private String string1;

	/** 日付: 性別 */
	@org.springframework.beans.factory.annotation.Value("${generator.config.date}")
	private java.time.LocalDate date;

	/** 時刻: 性別 */
	@org.springframework.beans.factory.annotation.Value("${generator.config.time}")
	private java.time.LocalTime time;

	/** 日時: 性別 */
	@org.springframework.beans.factory.annotation.Value("${generator.config.datetime}")
	private java.time.LocalDateTime datetime;

}
