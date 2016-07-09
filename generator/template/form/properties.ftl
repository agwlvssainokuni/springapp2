# cherry.gradle.task.generator.GenerateForm, ${.now?iso_local}
<#list typeDef.itemDef as prop>
${formName(typeDef.fqcn)}.${prop.name}=${prop.label}
</#list>
