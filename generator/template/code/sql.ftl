<#include "/header_sql.ftl" />

-- cherry.gradle.task.generator.GenerateCode<#if enableTimestamp>, ${.now?iso_local}</#if>
<#macro enumBegin prop>

-- ${prop.description} (${prop.name})
</#macro>
<#macro enumItem prop count>
INSERT INTO code_master (name, value, label, sort_order) VALUES ('${prop.name}', '${prop.value}', '${prop.label}', ${count});
</#macro>
<#list typeDef.itemDef>
<#assign current = typeDef.itemDef?first>
<#assign baseIndex = 0>
<@enumBegin current />
<#items as prop>
<#if current.name != prop.name>
<#assign current = prop>
<#assign baseIndex = prop?index>
<@enumBegin current />
</#if>
<@enumItem prop, (prop?index - baseIndex) />
</#items>
</#list>
