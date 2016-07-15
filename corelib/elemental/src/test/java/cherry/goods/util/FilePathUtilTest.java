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

package cherry.goods.util;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class FilePathUtilTest {

	@Test
	public void testComposeFile_NORMAL() {
		File basedir = new File("/aaa/bbb");
		assertEquals(new File("/aaa/bbb"), FilePathUtil.composeFile(basedir, new ArrayList<String>()));
		assertEquals(new File("/aaa/bbb/ccc"), FilePathUtil.composeFile(basedir, asList("ccc")));
		assertEquals(new File("/aaa/bbb/ccc/ddd"), FilePathUtil.composeFile(basedir, asList("ccc", "ddd")));
		assertEquals(new File("/aaa/bbb/ddd"), FilePathUtil.composeFile(basedir, asList("ccc", "..", "ddd")));
	}

	@Test
	public void testComposeFile_RELPATH() throws IOException {
		File basedir = new File(".");
		assertEquals(new File(".").getCanonicalFile(), FilePathUtil.composeFile(basedir, new ArrayList<String>()));
		assertEquals(new File("ccc").getCanonicalFile(), FilePathUtil.composeFile(basedir, asList("ccc")));
		assertEquals(new File("ccc/ddd").getCanonicalFile(), FilePathUtil.composeFile(basedir, asList("ccc", "ddd")));
		assertEquals(new File("ddd").getCanonicalFile(), FilePathUtil.composeFile(basedir, asList("ccc", "..", "ddd")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testComposeFile_ILLEGALARGUMENT() {
		FilePathUtil.composeFile(new File("/aaa\u0000"), asList("bbb"));
	}

	@Test
	public void testDecomposeFile_NORMAL() {
		File basedir = new File("/aaa/bbb");
		assertEquals(new ArrayList<String>(), FilePathUtil.decomposeFile(basedir, new File("/aaa/bbb")));
		assertEquals(asList("ccc"), FilePathUtil.decomposeFile(basedir, new File("/aaa/bbb/ccc")));
		assertEquals(asList("ccc", "ddd"), FilePathUtil.decomposeFile(basedir, new File("/aaa/bbb/ccc/ddd")));
		assertEquals(asList("ddd"), FilePathUtil.decomposeFile(basedir, new File("/aaa/bbb/ccc/../ddd")));
	}

	@Test
	public void testDecomposeFile_RELPATH() {
		File basedir = new File(".");
		assertEquals(new ArrayList<String>(), FilePathUtil.decomposeFile(basedir, new File(".")));
		assertEquals(asList("ccc"), FilePathUtil.decomposeFile(basedir, new File("ccc")));
		assertEquals(asList("ccc", "ddd"), FilePathUtil.decomposeFile(basedir, new File("ccc/ddd")));
		assertEquals(asList("ddd"), FilePathUtil.decomposeFile(basedir, new File("ccc/../ddd")));
	}

	@Test
	public void testDecomposeFile_UNMATCH() {
		assertNull(FilePathUtil.decomposeFile(new File("/aaa"), new File("/bbb")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecomposeFile_ILLEGALARGUMENT() {
		FilePathUtil.decomposeFile(new File("/aaa\u0000"), new File("/aaa\u0000/bbb"));
	}

}
