<#include "/header.ftl" />

package ${packageName(typeDef.fqcn)};

import cherry.foundation.type.ILabelledCodeType;

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
	DUMMY("", "");

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
