<#include "/header_java.ftl" />

package ${packageName(typeDef.fqcn)};

import cherry.goods.log.ILogId;
import cherry.goods.log.Level;

@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateLog", date = "${.now?iso_local}")
public enum ${className(typeDef.fqcn)} implements ILogId {
<#macro item prop>
	/** ${prop.name} [${prop.level}]: ${prop.value} */
	${prop.name}("${prop.name}", Level.${prop.level}),
</#macro>
<#list typeDef.itemDef as prop>
<@item prop />
</#list>
	/* 生成ツールの都合による定義。 */
	DUMMY("DUMMY", Level.ERROR);

	private String id;

	private Level level;

	private ${className(typeDef.fqcn)}(String id, Level level) {
		this.id = id;
		this.level = level;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Level getLevel() {
		return level;
	}

}
