<#include "/header.ftl" />

package ${packageName(typeDef.fqcn)};

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ${className(typeDef.fqcn)}Base implements Serializable {

	private static final long serialVersionUID = 1L;
<#list typeDef.itemDef as prop>
<#if prop.type?? && prop.name??>

<#if prop.purpose??>
	/** ${prop.purpose} */
</#if>
	private ${prop.type} ${prop.name};
</#if>
</#list>

}
