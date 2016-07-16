<#include "/header_java.ftl" />

package ${packageName(typeDef.fqcn)};

import cherry.elemental.code.EnumCodeUtil;
import cherry.elemental.code.ILabelledCodeType;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateCode", date = "${.now?iso_local}")
public enum ${className(typeDef.fqcn)} implements ILabelledCodeType<String> {
<#macro enumItem prop>
	/** ${prop.description} */
	${upperUnderscore(prop.name)}("${prop.name?j_string}", "${prop.description?j_string}"),
</#macro>
<#list typeDef.itemDef>
<#assign current = typeDef.itemDef?first>
<@enumItem current />
<#items as prop>
<#if current.name != prop.name>
<#assign current = prop>
<@enumItem current />
</#if>
</#items>
</#list>
	/* 生成ツールの都合による定義。 */
	DUMMY("", "");

	private static final java.util.Map<String, ${className(typeDef.fqcn)}> nameMap = EnumCodeUtil.getCodeMap(${className(typeDef.fqcn)}.values());

	public static ${className(typeDef.fqcn)} resolve(String name) {
		return nameMap.get(name);
	}

	private String pname;

	private String lname;

	private ${className(typeDef.fqcn)}(String pname, String lname) {
		this.pname = pname;
		this.lname = lname;
	}

	@Override
	public String getCodeValue() {
		return pname;
	}

	@Override
	public String getCodeLabel() {
		return lname;
	}

}
