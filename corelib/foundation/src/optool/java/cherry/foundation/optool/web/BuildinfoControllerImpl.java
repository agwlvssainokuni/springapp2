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

package cherry.foundation.optool.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

import com.google.common.io.ByteStreams;

public class BuildinfoControllerImpl implements BuildinfoController {

	private final Log log = LogFactory.getLog(getClass());

	private Resource resource;

	private Charset charset;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@Override
	public ResponseEntity<String> buildinfo() {
		if (!resource.exists()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try (InputStream in = resource.getInputStream()) {
			String result = new String(ByteStreams.toByteArray(in), charset);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (IOException ex) {
			if (log.isDebugEnabled()) {
				log.debug(ex, "Failed to load buildinfo");
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
