# cherry.gradle.task.generator.GenerateLog<#if enableTimestamp>, ${.now?iso_local}</#if>
<#macro item prop>
${prop.name}=${prop.name} - ${prop.value}
</#macro>
<#list typeDef.itemDef as prop>
<@item prop />
</#list>
