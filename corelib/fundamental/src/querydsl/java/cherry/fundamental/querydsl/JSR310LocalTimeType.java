/*
 * Copyright 2015,2016 agwlvssainokuni
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

package cherry.fundamental.querydsl;

import static cherry.elemental.util.JavaTimeUtil.getLocalTime;
import static cherry.elemental.util.JavaTimeUtil.getSqlTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalTime;

import com.querydsl.sql.types.AbstractJSR310DateTimeType;

public class JSR310LocalTimeType extends AbstractJSR310DateTimeType<LocalTime> {

	public JSR310LocalTimeType() {
		super(Types.TIME);
	}

	public JSR310LocalTimeType(int type) {
		super(type);
	}

	@Override
	public String getLiteral(LocalTime value) {
		return timeFormatter.format(value);
	}

	@Override
	public Class<LocalTime> getReturnedClass() {
		return LocalTime.class;
	}

	@Override
	public LocalTime getValue(ResultSet rs, int startIndex) throws SQLException {
		Time time = rs.getTime(startIndex);
		if (time == null) {
			return null;
		}
		return getLocalTime(time);
	}

	@Override
	public void setValue(PreparedStatement st, int startIndex, LocalTime value) throws SQLException {
		st.setTime(startIndex, getSqlTime(value));
	}

}
