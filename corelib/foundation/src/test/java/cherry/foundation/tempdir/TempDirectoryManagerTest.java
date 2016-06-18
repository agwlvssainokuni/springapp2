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

package cherry.foundation.tempdir;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-foundation-test.xml")
public class TempDirectoryManagerTest {

	@Autowired
	private TempDirectoryManager tempDirectoryManager;

	@Test
	public void testPrepareTempDirectory() {
		String name = tempDirectoryManager.prepareTempDirectory();
		try {
			assertNotNull(name);
			assertTrue(name.matches("\\d{8}\\d{6}\\d{3}_[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}"));
			assertTrue(new File("/opt/app/00/temp", name).exists());
		} finally {
			tempDirectoryManager.cleanupTempDirectory(name);
		}
		assertFalse(new File("/opt/app/00/temp", name).exists());
	}

	@Test
	public void testGetTempDirectory() {
		String name = tempDirectoryManager.prepareTempDirectory();
		try {
			File dir = tempDirectoryManager.getTempDirectory(name);
			assertTrue(dir.exists());
			assertEquals(new File("/opt/app/00/temp", name), dir);
		} finally {
			tempDirectoryManager.cleanupTempDirectory(name);
		}
		assertFalse(new File("/opt/app/00/temp", name).exists());
	}

	@Test
	public void testCleanupTempDirectory() {
		String name = tempDirectoryManager.prepareTempDirectory();
		try {
			assertTrue(new File("/opt/app/00/temp", name).exists());
		} finally {
			tempDirectoryManager.cleanupTempDirectory(name);
		}
		assertFalse(new File("/opt/app/00/temp", name).exists());
	}

}
