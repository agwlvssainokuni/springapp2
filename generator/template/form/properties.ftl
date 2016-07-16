# cherry.gradle.task.generator.GenerateForm<#if enableTimestamp>, ${.now?iso_local}</#if>
<#list typeDef.itemDef as prop>
${formName(typeDef.fqcn)}.${prop.name}=${prop.label}
</#list>
