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

package cherry.fundamental.bizcal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.core.convert.ConversionService;

import cherry.fundamental.batch.ExitStatus;

public class BizDateTimeBatchTest {

	private BizDateTime bizDateTime = mock(BizDateTime.class);
	private BizDateTimeService bizDateTimeService = mock(BizDateTimeService.class);
	private ConversionService conversionService = mock(ConversionService.class);

	@Test
	public void testNoArgs() {
		// 準備
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute());
		// 検証
		verify(bizDateTime, times(1)).today();
		verify(bizDateTime, times(1)).now();
	}

	@Test
	public void testShow() {
		// 準備
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("show"));
		// 検証
		verify(bizDateTime, times(1)).today();
		verify(bizDateTime, times(1)).now();
	}

	@Test
	public void testSet_NORMAL_1() {
		// 準備
		LocalDate today = LocalDate.now();
		when(conversionService.convert(eq("yyyy/mm/dd"), eq(LocalDate.class))).thenReturn(today);
		when(bizDateTimeService.doSet(any(LocalDate.class))).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("set", "yyyy/mm/dd"));
		// 検証
		verify(conversionService, times(1)).convert(eq("yyyy/mm/dd"), eq(LocalDate.class));
		verify(bizDateTimeService, times(1)).doSet(eq(today));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testSet_WARN_1() {
		// 準備
		LocalDate today = LocalDate.now();
		when(conversionService.convert(eq("yyyy/mm/dd"), eq(LocalDate.class))).thenReturn(today);
		when(bizDateTimeService.doSet(any(LocalDate.class))).thenReturn(false); // NG
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.WARN, impl.execute("set", "yyyy/mm/dd"));
		// 検証
		verify(conversionService, times(1)).convert(eq("yyyy/mm/dd"), eq(LocalDate.class));
		verify(bizDateTimeService, times(1)).doSet(eq(today));
		verify(bizDateTime, never()).today(); // when NG
		verify(bizDateTime, never()).now(); // when NG
	}

	@Test
	public void testSet_WARN_2() {
		// 準備
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.WARN, impl.execute("set"));
		// 検証
		verify(conversionService, never()).convert(anyString(), any());
		verify(bizDateTimeService, never()).doSet(any());
		verify(bizDateTime, never()).today();
		verify(bizDateTime, never()).now();
	}

	@Test
	public void testToday_NORMAL_1() {
		// 準備
		when(conversionService.convert(eq("diff"), eq(Integer.class))).thenReturn(123);
		when(bizDateTimeService.doToday(anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("today", "diff"));
		// 検証
		verify(conversionService, times(1)).convert(eq("diff"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doToday(eq(123));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testToday_NORMAL_2() {
		// 準備
		when(bizDateTimeService.doToday(anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("today"));
		// 検証
		verify(conversionService, never()).convert(anyString(), any()); // when "diff" not specified
		verify(bizDateTimeService, times(1)).doToday(eq(0)); // when "diff" not specified
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testToday_WARN_1() {
		// 準備
		when(conversionService.convert(eq("diff"), eq(Integer.class))).thenReturn(123);
		when(bizDateTimeService.doToday(anyInt())).thenReturn(false); // NG
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.WARN, impl.execute("today", "diff"));
		// 検証
		verify(conversionService, times(1)).convert(eq("diff"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doToday(eq(123));
		verify(bizDateTime, never()).today(); // when NG
		verify(bizDateTime, never()).now(); // when NG
	}

	@Test
	public void testCurrent_NORMAL_1() {
		// 準備
		when(conversionService.convert(eq("diff"), eq(Integer.class))).thenReturn(123);
		when(bizDateTimeService.doCurrent(anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("current", "diff"));
		// 検証
		verify(conversionService, times(1)).convert(eq("diff"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doCurrent(eq(123));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testCurrent_NORMAL_2() {
		// 準備
		when(bizDateTimeService.doCurrent(anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("current"));
		// 検証
		verify(conversionService, never()).convert(anyString(), any()); // when "diff" not specified
		verify(bizDateTimeService, times(1)).doCurrent(eq(0)); // when "diff" not specified
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testCurrent_WARN_1() {
		// 準備
		when(conversionService.convert(eq("diff"), eq(Integer.class))).thenReturn(123);
		when(bizDateTimeService.doCurrent(anyInt())).thenReturn(false); // NG
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.WARN, impl.execute("current", "diff"));
		// 検証
		verify(conversionService, times(1)).convert(eq("diff"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doCurrent(eq(123));
		verify(bizDateTime, never()).today(); // when NG
		verify(bizDateTime, never()).now(); // when NG
	}

	@Test
	public void testOffset_NORMAL_1() {
		// 準備
		when(conversionService.convert(eq("d"), eq(Integer.class))).thenReturn(12);
		when(conversionService.convert(eq("h"), eq(Integer.class))).thenReturn(34);
		when(conversionService.convert(eq("m"), eq(Integer.class))).thenReturn(56);
		when(conversionService.convert(eq("s"), eq(Integer.class))).thenReturn(78);
		when(bizDateTimeService.doOffset(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("offset", "d", "h", "m", "s"));
		// 検証
		verify(conversionService, times(1)).convert(eq("d"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("h"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("m"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("s"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doOffset(eq(12), eq(34), eq(56), eq(78));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testOffset_NORMAL_2() {
		// 準備
		when(conversionService.convert(eq("d"), eq(Integer.class))).thenReturn(12);
		when(conversionService.convert(eq("h"), eq(Integer.class))).thenReturn(34);
		when(conversionService.convert(eq("m"), eq(Integer.class))).thenReturn(56);
		when(conversionService.convert(eq("s"), eq(Integer.class))).thenReturn(78);
		when(bizDateTimeService.doOffset(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("offset", "d", "h", "m"));
		// 検証
		verify(conversionService, times(1)).convert(eq("d"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("h"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("m"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("s"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doOffset(eq(12), eq(34), eq(56), eq(0));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testOffset_NORMAL_3() {
		// 準備
		when(conversionService.convert(eq("d"), eq(Integer.class))).thenReturn(12);
		when(conversionService.convert(eq("h"), eq(Integer.class))).thenReturn(34);
		when(conversionService.convert(eq("m"), eq(Integer.class))).thenReturn(56);
		when(conversionService.convert(eq("s"), eq(Integer.class))).thenReturn(78);
		when(bizDateTimeService.doOffset(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("offset", "d", "h"));
		// 検証
		verify(conversionService, times(1)).convert(eq("d"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("h"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("m"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("s"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doOffset(eq(12), eq(34), eq(0), eq(0));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testOffset_NORMAL_4() {
		// 準備
		when(conversionService.convert(eq("d"), eq(Integer.class))).thenReturn(12);
		when(conversionService.convert(eq("h"), eq(Integer.class))).thenReturn(34);
		when(conversionService.convert(eq("m"), eq(Integer.class))).thenReturn(56);
		when(conversionService.convert(eq("s"), eq(Integer.class))).thenReturn(78);
		when(bizDateTimeService.doOffset(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("offset", "d"));
		// 検証
		verify(conversionService, times(1)).convert(eq("d"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("h"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("m"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("s"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doOffset(eq(12), eq(0), eq(0), eq(0));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testOffset_NORMAL_5() {
		// 準備
		when(conversionService.convert(eq("d"), eq(Integer.class))).thenReturn(12);
		when(conversionService.convert(eq("h"), eq(Integer.class))).thenReturn(34);
		when(conversionService.convert(eq("m"), eq(Integer.class))).thenReturn(56);
		when(conversionService.convert(eq("s"), eq(Integer.class))).thenReturn(78);
		when(bizDateTimeService.doOffset(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true); // OK
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.NORMAL, impl.execute("offset"));
		// 検証
		verify(conversionService, never()).convert(eq("d"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("h"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("m"), eq(Integer.class));
		verify(conversionService, never()).convert(eq("s"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doOffset(eq(0), eq(0), eq(0), eq(0));
		verify(bizDateTime, times(1)).today(); // when OK
		verify(bizDateTime, times(1)).now(); // when OK
	}

	@Test
	public void testOffset_WARN_1() {
		// 準備
		when(conversionService.convert(eq("d"), eq(Integer.class))).thenReturn(12);
		when(conversionService.convert(eq("h"), eq(Integer.class))).thenReturn(34);
		when(conversionService.convert(eq("m"), eq(Integer.class))).thenReturn(56);
		when(conversionService.convert(eq("s"), eq(Integer.class))).thenReturn(78);
		when(bizDateTimeService.doOffset(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false); // NG
		when(bizDateTime.today()).thenReturn(LocalDate.now());
		when(bizDateTime.now()).thenReturn(LocalDateTime.now());
		// 実行
		BizDateTimeBatch impl = create();
		assertEquals(ExitStatus.WARN, impl.execute("offset", "d", "h", "m", "s"));
		// 検証
		verify(conversionService, times(1)).convert(eq("d"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("h"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("m"), eq(Integer.class));
		verify(conversionService, times(1)).convert(eq("s"), eq(Integer.class));
		verify(bizDateTimeService, times(1)).doOffset(eq(12), eq(34), eq(56), eq(78));
		verify(bizDateTime, never()).today(); // when NG
		verify(bizDateTime, never()).now(); // when NG
	}

	private BizDateTimeBatch create() {
		BizDateTimeBatch impl = new BizDateTimeBatch();
		impl.setBizDateTime(bizDateTime);
		impl.setBizDateTimeService(bizDateTimeService);
		impl.setConversionService(conversionService);
		impl.setShowPatternToday("TODAY: {0,date,yyyy/MM/dd}");
		impl.setShowPatternNow("  NOW: {0,date,yyyy/MM/dd HH:mm:ss.SSS}");
		return impl;
	}

}
