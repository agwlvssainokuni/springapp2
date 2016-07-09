<#macro item prop>
# ${prop.label}
${prop.name}=${prop.value}
</#macro>
<#list typeDef.itemDef as prop>
<@item prop />
</#list>
