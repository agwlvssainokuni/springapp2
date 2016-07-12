# cherry.gradle.task.generator.GenerateLog, ${.now?iso_local}
<#macro item prop>
${prop.name}=${prop.name} - ${prop.value}
</#macro>
<#list typeDef.itemDef as prop>
<@item prop />
</#list>
