<#list typeDef.itemDef as prop>
${formName(typeDef.fqcn)}.${prop.name}=${prop.label}
</#list>
