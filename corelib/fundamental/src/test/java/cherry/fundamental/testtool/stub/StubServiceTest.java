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

package cherry.fundamental.testtool.stub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.fundamental.testtool.ToolTester;
import cherry.fundamental.testtool.reflect.ReflectionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
public class StubServiceTest {

	@Autowired
	@Qualifier("jsonStubService")
	private StubService stubService;

	@Autowired
	@Qualifier("reflectionResolver")
	private ReflectionResolver resolver;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Autowired
	private StubRepository repository;

	private Method method;

	@Before
	public void before() throws NoSuchMethodException {
		method = ToolTester.class.getDeclaredMethod("toBeStubbed1", Long.class, Long.class);
	}

	@After
	public void after() {
		for (Method m : repository.getStubbedMethod()) {
			repository.clear(m);
		}
	}

	@Test
	public void testResolveBeanName() {
		List<String> list = stubService.resolveBeanName(ToolTester.class.getName());
		assertEquals(1, list.size());
		assertEquals("toolTesterImpl", list.get(0));
	}

	@Test
	public void testResolveBeanName_ClassNotFound() {
		List<String> list = stubService.resolveBeanName(ToolTester.class.getName() + "NotExist");
		assertEquals(0, list.size());
	}

	@Test
	public void testResolveMethod() {
		List<Method> list = stubService.resolveMethod(ToolTester.class.getName(), "toBeStubbed1");
		assertEquals(2, list.size());
		Set<Class<?>> returnType = new HashSet<>();
		returnType.add(Long.class);
		returnType.add(BigDecimal.class);
		for (Method m : list) {
			assertEquals(2, m.getParameterTypes().length);
			returnType.remove(m.getReturnType());
		}
		assertTrue(returnType.isEmpty());
	}

	@Test
	public void testResolveMethod_ClassNotFound() {
		List<Method> list = stubService.resolveMethod(ToolTester.class.getName() + "NotExist", "toBeStubbed1");
		assertEquals(0, list.size());
	}

	@Test
	public void testGetStubbedMethod_NOCOND() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertEquals("true", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", null));
		List<Method> list = stubService.getStubbedMethod(null);
		assertEquals(1, list.size());
		assertEquals(method, list.get(0));
	}

	@Test
	public void testGetStubbedMethod_WITHCOND() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertEquals("true", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", null));
		List<Method> list = stubService.getStubbedMethod(ToolTester.class.getName());
		assertEquals(1, list.size());
		assertEquals(method, list.get(0));
	}

	@Test
	public void testGetStubbedMethod_EMPTY() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertEquals("true", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", null));
		List<Method> list = stubService.getStubbedMethod(getClass().getName());
		assertTrue(list.isEmpty());
	}

	@Test
	public void testGetStubbedMethod_ClassNotFound() {
		List<Method> list = stubService.getStubbedMethod(ToolTester.class.getName() + "NotExist");
		assertTrue(list.isEmpty());
	}

	@Test
	public void testPeek() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", null));
		assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertFalse(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("123", stubService.peek(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("java.lang.Long", stubService.peekType(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testPeekThrowable() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.alwaysThrows(ToolTester.class.getName(), "toBeStubbed1", index,
				IllegalArgumentException.class.getName()));
		assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertTrue(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals(IllegalArgumentException.class.getName(),
				stubService.peekThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testAlwaysReturn1() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", null));
		for (int i = 0; i < 100; i++) {
			assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
			assertFalse(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals(Long.class.getName(), stubService.peekType(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals("123", stubService.peek(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals(Long.valueOf(123L), repository.get(method).next());
		}
		assertEquals("true", stubService.clear(ToolTester.class.getName(), "toBeStubbed1", index));
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testAlwaysReturn2() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", "long"));
		for (int i = 0; i < 100; i++) {
			assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
			assertFalse(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals("long", stubService.peekType(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals("123", stubService.peek(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals(Long.valueOf(123L), repository.get(method).next());
		}
		assertEquals("true", stubService.clear(ToolTester.class.getName(), "toBeStubbed1", index));
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testThenReturn1() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.thenReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", null));

		assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertFalse(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals(Long.class.getName(), stubService.peekType(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("123", stubService.peek(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals(Long.valueOf(123L), repository.get(method).next());

		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testThenReturn2() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.thenReturn(ToolTester.class.getName(), "toBeStubbed1", index, "123", "long"));

		assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertFalse(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("long", stubService.peekType(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("123", stubService.peek(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals(Long.valueOf(123L), repository.get(method).next());

		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testAlwaysThrows() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.alwaysThrows(ToolTester.class.getName(), "toBeStubbed1", index,
				IllegalArgumentException.class.getName()));
		for (int i = 0; i < 100; i++) {
			assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
			assertTrue(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals(IllegalArgumentException.class.getName(),
					stubService.peekThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
			assertEquals(IllegalArgumentException.class, repository.get(method).nextThrowable());
		}
		assertEquals("true", stubService.clear(ToolTester.class.getName(), "toBeStubbed1", index));
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testThenThrows() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals("true", stubService.thenThrows(ToolTester.class.getName(), "toBeStubbed1", index,
				IllegalArgumentException.class.getName()));

		assertTrue(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
		assertTrue(stubService.isThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals(IllegalArgumentException.class.getName(),
				stubService.peekThrowable(ToolTester.class.getName(), "toBeStubbed1", index));
		assertEquals(IllegalArgumentException.class, repository.get(method).nextThrowable());

		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", index));
	}

	@Test
	public void testExecutePredicate_methodIndex() {
		assertFalse(stubService.hasNext(ToolTester.class.getName(), "toBeStubbed1", 4));
	}

	@Test
	public void testExecutePredicate_ClassNotFound() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertFalse(stubService.hasNext(ToolTester.class.getName() + "NotExist", "toBeStubbed1", index));
	}

	@Test
	public void testExecuteFunction_methodIndex() {
		assertEquals("false", stubService.peekThrowable(ToolTester.class.getName(), "toBeStubbed1", 3));
	}

	@Test
	public void testExecuteFunction_ClassNotFound() {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		assertEquals(ToolTester.class.getName() + "NotExist",
				stubService.peekThrowable(ToolTester.class.getName() + "NotExist", "toBeStubbed1", index));
	}

	@Test
	public void testExecuteWithMapping_methodIndex() throws IOException {
		assertEquals("false", stubService.alwaysReturn(ToolTester.class.getName(), "toBeStubbed1", 3, "1", null));
	}

	@Test
	public void testExecuteWithMapping_ClassNotFound() throws IOException {
		int index = getMethodIndex(ToolTester.class, "toBeStubbed1", Long.class);
		String result = stubService.alwaysReturn(ToolTester.class.getName() + "NotExist", "toBeStubbed1", index, "1",
				null);
		Map<?, ?> map = objectMapper.readValue(result, Map.class);
		assertEquals(ClassNotFoundException.class.getName(), map.get("type"));
		assertEquals(ToolTester.class.getName() + "NotExist", map.get("message"));
	}

	private int getMethodIndex(Class<?> beanClass, String methodName, Class<?> returnType) {
		int i = 0;
		for (Method m : resolver.resolveMethod(ToolTester.class, methodName)) {
			if (m.getReturnType() == returnType) {
				return i;
			}
			i++;
		}
		return i;
	}

}
