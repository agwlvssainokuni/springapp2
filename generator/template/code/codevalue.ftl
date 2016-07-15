<#include "/header_java.ftl" />

package ${packageName(typeDef.fqcn)};

import cherry.fundamental.type.ILabelledCodeType;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateCode", date = "${.now?iso_local}")
public class ${codeValueName} {
<#macro enumBegin prop>

	/** ${prop.description} */
	public static enum ${upperUnderscore(prop.name)} implements ILabelledCodeType<String> {
</#macro>
<#macro enumItem current prop>
		/** [${current.description}] ${prop.value}: ${prop.label} */
		${upperUnderscore(current.name)}_${upperUnderscore(prop.value)}("${prop.value?j_string}", "${prop.label?j_string}"),
</#macro>
<#macro enumEnd prop>
		/* 生成ツールの都合による定義。 */
		DUMMY("", "");

		private String value;

		private String label;

		private ${upperUnderscore(prop.name)}(String value, String label) {
			this.value = value;
			this.label = label;
		}

		@Override
		public String getCodeValue() {
			return value;
		}

		@Override
		public String getCodeLabel() {
			return label;
		}
	}
</#macro>
<#list typeDef.itemDef>
<#assign current = typeDef.itemDef?first>
<@enumBegin current />
<#items as prop>
<#if current.name != prop.name>
<@enumEnd current />
<#assign current = prop>
<@enumBegin current />
</#if>
<@enumItem current prop />
</#items>
<@enumEnd current />
</#list>

}
