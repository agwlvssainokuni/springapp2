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

package cherry.foundation.format;

import static cherry.goods.util.JavaTimeUtil.getLocalDate;
import static cherry.goods.util.JavaTimeUtil.getLocalDateTime;
import static cherry.goods.util.JavaTimeUtil.getLocalTime;
import static cherry.goods.util.JavaTimeUtil.getSqlDate;
import static cherry.goods.util.JavaTimeUtil.getSqlTime;
import static cherry.goods.util.JavaTimeUtil.getSqlTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class JavaTimeSqlConverterRegistrar implements FormatterRegistrar {

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addConverter(new LocalDateConverter());
		registry.addConverter(new SqlDateConverter());
		registry.addConverter(new LocalTimeConverter());
		registry.addConverter(new SqlTimeConverter());
		registry.addConverter(new LocalDateTimeConverter());
		registry.addConverter(new SqlTimestampConverter());
	}

	static class LocalDateConverter implements Converter<Date, LocalDate> {
		@Override
		public LocalDate convert(Date source) {
			return getLocalDate(source);
		}
	}

	static class SqlDateConverter implements Converter<LocalDate, Date> {
		@Override
		public Date convert(LocalDate source) {
			return getSqlDate(source);
		}
	}

	static class LocalTimeConverter implements Converter<Time, LocalTime> {
		@Override
		public LocalTime convert(Time source) {
			return getLocalTime(source);
		}
	}

	static class SqlTimeConverter implements Converter<LocalTime, Time> {
		@Override
		public Time convert(LocalTime source) {
			return getSqlTime(source);
		}
	}

	static class LocalDateTimeConverter implements Converter<Timestamp, LocalDateTime> {
		@Override
		public LocalDateTime convert(Timestamp source) {
			return getLocalDateTime(source);
		}
	}

	static class SqlTimestampConverter implements Converter<LocalDateTime, Timestamp> {
		@Override
		public Timestamp convert(LocalDateTime source) {
			return getSqlTimestamp(source);
		}
	}

}
