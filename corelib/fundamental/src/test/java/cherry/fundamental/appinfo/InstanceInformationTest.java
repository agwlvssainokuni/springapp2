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

package cherry.fundamental.appinfo;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/appctx-fundamental-test.xml")
public class InstanceInformationTest {

	@Autowired
	@Qualifier("instanceInformation")
	private InstanceInformation instanceInformation;

	@Test
	public void testGetId() {
		assertEquals("00", instanceInformation.getId());
	}

	@Test
	public void testGetEnvironmentName() {
		assertEquals("develop", instanceInformation.getEnvironmentName());
	}

	@Test
	public void testGetBaseDirectory() {
		assertEquals(new File("/opt/app/00"), instanceInformation.getBaseDirectory());
	}

}
