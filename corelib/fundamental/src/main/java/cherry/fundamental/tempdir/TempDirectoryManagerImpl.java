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

package cherry.fundamental.tempdir;

import java.io.File;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.UUID;

import cherry.elemental.util.JavaTimeUtil;
import cherry.fundamental.bizcal.BizDateTime;

public class TempDirectoryManagerImpl implements TempDirectoryManager {

	private File baseTempDirectory;

	private String directoryNamePattern;

	private BizDateTime bizDateTime;

	public void setBaseTempDirectory(File baseTempDirectory) {
		this.baseTempDirectory = baseTempDirectory;
	}

	public void setDirectoryNamePattern(String directoryNamePattern) {
		this.directoryNamePattern = directoryNamePattern;
	}

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void initializeBaseTempDirectory() throws IllegalStateException {
		if (baseTempDirectory.exists()) {
			return;
		}
		if (baseTempDirectory.mkdirs()) {
			return;
		}
		throw new IllegalStateException("Failed to mkdirs " + baseTempDirectory.getPath());
	}

	@Override
	public String prepareTempDirectory() {
		Calendar timestamp = JavaTimeUtil.getCalendar(bizDateTime.now());
		UUID uuid = UUID.randomUUID();
		String name = MessageFormat.format(directoryNamePattern, timestamp.getTime(), uuid.toString());
		File dir = new File(baseTempDirectory, name);
		if (dir.exists()) {
			throw new IllegalStateException("TempDirectory already exists " + dir.getPath());
		}
		if (!dir.mkdir()) {
			throw new IllegalStateException("Failed to mkdir " + dir.getPath());
		}
		return name;
	}

	@Override
	public File getTempDirectory(String name) throws IllegalArgumentException {
		File dir = getDir(name);
		if (!dir.exists()) {
			throw new IllegalArgumentException("Directory does not exists " + dir.getPath());
		}
		return dir;
	}

	@Override
	public void cleanupTempDirectory(String name) throws IllegalArgumentException, IllegalStateException {
		File dir = getDir(name);
		if (!dir.exists()) {
			return;
		}
		deleteDir(dir);
	}

	private File getDir(String name) throws IllegalArgumentException {
		File dir = new File(baseTempDirectory, name);
		if (!dir.getName().equals(name)) {
			throw new IllegalArgumentException("Name is not valid " + name);
		}
		return dir;
	}

	private void deleteDir(File d) throws IllegalStateException {
		for (File f : d.listFiles()) {
			if (f.isDirectory()) {
				deleteDir(f);
			} else {
				if (!f.delete()) {
					throw new IllegalStateException("Failed to delete " + f.getPath());
				}
			}
		}
		if (!d.delete()) {
			throw new IllegalStateException("Failed to delete " + d.getPath());
		}
	}

}
