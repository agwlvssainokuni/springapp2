# cherry.gradle.task.generator.GenerateConfig<#if enableTimestamp>, ${.now?iso_local}</#if>
<#macro item prop>
# ${prop.label}
${prop.name}=${prop.value}
</#macro>
<#list typeDef.itemDef as prop>
<@item prop />
</#list>
