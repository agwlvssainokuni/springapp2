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

package cherry.elemental.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilePathUtil {

	public static File composeFile(File basedir, List<String> path) {
		File file = getCanonicalFile(basedir);
		for (String p : path) {
			file = new File(file, p);
		}
		return getCanonicalFile(file);
	}

	public static List<String> decomposeFile(File basedir, File file) {
		File cbase = getCanonicalFile(basedir);
		File cfile = getCanonicalFile(file);
		if (!cfile.getPath().startsWith(cbase.getPath())) {
			return null;
		}
		List<String> path = new ArrayList<>();
		for (File f = cfile; !f.equals(cbase); f = f.getParentFile()) {
			path.add(f.getName());
		}
		Collections.reverse(path);
		return path;
	}

	private static File getCanonicalFile(File file) {
		try {
			return file.getCanonicalFile();
		} catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

}
