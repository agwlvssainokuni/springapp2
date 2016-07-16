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

package cherry.gradle.task.generator;

import static java.lang.System.out;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.Setter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import cherry.parser.worksheet.SheetBasedParser;
import cherry.parser.worksheet.TypeDef;
import cherry.parser.worksheet.WorkbookParser;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Setter
public class GenerateCode extends DefaultTask {

	private boolean verbose = true;

	private boolean enableTimestamp = false;

	private List<File> definitionFiles = new ArrayList<>();

	private File templateDir = new File("template");

	private String templateCodeName = "code/codename.ftl";

	private String templateCodeValue = "code/codevalue.ftl";

	private String templateSql = "code/sql.ftl";

	private String templateEncoding = "UTF-8";

	private File baseDir = new File(".");

	private String javaBaseDir = "src/generated/java";

	private String sqlBaseDir = "src/generated/resources/db/migration";

	private String sqlEncoding = "UTF-8";

	private String javaEncoding = "UTF-8";

	private String attrCodeValueSpec = "codevalue";

	private String attrSqlSpec = "sql";

	private String attrDirSpec0 = null;

	private String attrDirSpec1 = null;

	private String attrDirSpec2 = null;

	private String attrDirSpec3 = null;

	private String attrDirSpec4 = null;

	@TaskAction
	public void generate() throws TemplateException, IOException {

		message("Starting to generate code files.");

		List<TypeDef> list = new LinkedList<>();
		for (File file : definitionFiles) {
			message("Parsing a file {0}", file);
			list.addAll(parseDef(file));
		}

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(templateDir);
		cfg.setDefaultEncoding(templateEncoding);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setAutoFlush(true);

		message("Loading templates.");
		Template codeNameTempl = cfg.getTemplate(templateCodeName);
		Template codeValueTempl = cfg.getTemplate(templateCodeValue);
		Template sqlTempl = cfg.getTemplate(templateSql);

		message("Generating.");

		for (TypeDef typeDef : list) {

			message("{0}", typeDef.getFqcn());

			Map<String, Object> context = new HashMap<>();
			context.put("typeDef", typeDef);
			context.put("enableTimestamp", enableTimestamp);
			context.put("upperCamel", new FunctionModel(FunctionFactory.upperCamel()));
			context.put("lowerCamel", new FunctionModel(FunctionFactory.lowerCamel()));
			context.put("lowerUnderscore", new FunctionModel(FunctionFactory.lowerUnderscore()));
			context.put("upperUnderscore", new FunctionModel(FunctionFactory.upperUnderscore()));
			context.put("className", new FunctionModel(FunctionFactory.className()));
			context.put("packageName", new FunctionModel(FunctionFactory.packageName()));
			context.put("formName", new FunctionModel(FunctionFactory.formName()));
			context.put("dirName", new FunctionModel(FunctionFactory.dirName()));

			String codeValueName = typeDef.getAttr().get(attrCodeValueSpec);
			if (codeValueName == null) {
				codeValueName = "CodeValue";
				message("CodeValue class name is not specified. \"{0}\" is used.", codeValueName);
			}
			context.put("codeValueName", codeValueName);

			String sqlName = typeDef.getAttr().get(attrSqlSpec);
			if (sqlName == null) {
				sqlName = "V00000000_000__CodeMaster.sql";
				message("SQL file name is not specified. \"{0}\" is used.", sqlName);
			}

			String dirSpec0 = typeDef.getAttr().get(attrDirSpec0);
			String dirSpec1 = typeDef.getAttr().get(attrDirSpec1);
			String dirSpec2 = typeDef.getAttr().get(attrDirSpec2);
			String dirSpec3 = typeDef.getAttr().get(attrDirSpec3);
			String dirSpec4 = typeDef.getAttr().get(attrDirSpec4);

			File javaDir = new File(baseDir, format(javaBaseDir, dirSpec0, dirSpec1, dirSpec2, dirSpec3, dirSpec4));
			File pkgDir = new File(javaDir, FunctionFactory.dirName().apply(typeDef.getFqcn()));
			pkgDir.mkdirs();

			File sqlDir = new File(baseDir, format(sqlBaseDir, dirSpec0, dirSpec1, dirSpec2, dirSpec3, dirSpec4));
			sqlDir.mkdirs();

			String className = FunctionFactory.className().apply(typeDef.getFqcn());
			File codeNameFile = new File(pkgDir, className + ".java");
			File codeValueFile = new File(pkgDir, codeValueName + ".java");

			File sqlFile = new File(sqlDir, sqlName);

			createFile(context, codeNameTempl, codeNameFile, javaEncoding);
			createFile(context, codeValueTempl, codeValueFile, javaEncoding);

			createFile(context, sqlTempl, sqlFile, sqlEncoding);
		}

		message("Completed.");
	}

	private void createFile(Map<String, Object> context, Template template, File file, String encoding)
			throws TemplateException, IOException {
		try (OutputStream os = new FileOutputStream(file); Writer w = new OutputStreamWriter(os, encoding)) {
			template.process(context, w);
			w.flush();
		}
	}

	private void message(String msg, Object... args) {
		if (verbose) {
			out.println(MessageFormat.format(msg, args));
		}
	}

	private List<TypeDef> parseDef(File file) throws IOException {
		WorkbookParser parser = new SheetBasedParser();
		List<TypeDef> list = new LinkedList<>();
		try (InputStream in = new FileInputStream(file)) {
			Workbook workbook = WorkbookFactory.create(in);
			for (TypeDef typeDef : parser.parse(workbook)) {
				if (typeDef.getFqcn() != null) {
					list.add(typeDef);
				}
			}
		} catch (InvalidFormatException ex) {
			throw new IllegalArgumentException(ex);
		}
		return list;
	}

}
