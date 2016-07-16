<#include "/header_java.ftl" />

package ${packageName(typeDef.fqcn)};

@lombok.Getter()
@org.springframework.stereotype.Component("${typeDef.attr.name}")
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateConfig"<#if enableTimestamp>, date = "${.now?iso_local}"</#if>)
public class ${className(typeDef.fqcn)} {
<#macro item prop>

	/** ${prop.label}: ${prop.description} */
	@org.springframework.beans.factory.annotation.Value("${"${"+prop.name+"}"}")
	private ${prop.vartype} ${prop.varname};
</#macro>
<#list typeDef.itemDef as prop>
<@item prop />
</#list>

}
