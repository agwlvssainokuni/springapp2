<#include "/header.ftl" />

package ${packageName(typeDef.fqcn)};

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ${className(typeDef.fqcn)} extends ${className(typeDef.fqcn)}Base {

	private static final long serialVersionUID = 1L;

}
