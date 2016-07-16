<#include "/header_java.ftl" />

package ${packageName(typeDef.fqcn)};

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSourceResolvable;

import cherry.fundamental.bizerror.BizErrorUtil;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@javax.annotation.Generated(value = "cherry.gradle.task.generator.GenerateForm"<#if enableTimestamp>, date = "${.now?iso_local}"</#if>)
public abstract class ${className(typeDef.fqcn)}Base implements Serializable {

	private static final long serialVersionUID = 1L;
<#macro groups prop>{ javax.validation.groups.Default.class${""
}<#if prop.g1?? && prop.g1 == "○">, cherry.fundamental.validator.groups.G1.class</#if>${""
}<#if prop.g2?? && prop.g2 == "○">, cherry.fundamental.validator.groups.G2.class</#if>${""
}<#if prop.g3?? && prop.g3 == "○">, cherry.fundamental.validator.groups.G3.class</#if>${""
}<#if prop.g4?? && prop.g4 == "○">, cherry.fundamental.validator.groups.G4.class</#if>${""
}<#if prop.g5?? && prop.g5 == "○">, cherry.fundamental.validator.groups.G5.class</#if>${""
}<#if prop.g6?? && prop.g6 == "○">, cherry.fundamental.validator.groups.G6.class</#if>${""
}<#if prop.g7?? && prop.g7 == "○">, cherry.fundamental.validator.groups.G7.class</#if>${""
}<#if prop.g8?? && prop.g8 == "○">, cherry.fundamental.validator.groups.G8.class</#if>${""
}<#if prop.g9?? && prop.g9 == "○">, cherry.fundamental.validator.groups.G9.class</#if>${""
} }</#macro>
<#macro notnull prop>
<#if prop.required?? && prop.required == "○">
	@javax.validation.constraints.NotNull(groups = <@groups prop/>)
</#if>
</#macro>
<#macro notempty prop>
<#if prop.required?? && prop.required == "○">
	@org.hibernate.validator.constraints.NotEmpty(groups = <@groups prop/>)
</#if>
</#macro>

<#list typeDef.itemDef as prop>
<#switch prop.type>
<#case "数値">
<@notnull prop />
<#if prop.min??>
<#if prop.subtype == "整数" || prop.subtype == "整数(Long)">
	@javax.validation.constraints.Min(value = ${prop.min}, groups = <@groups prop/>)
<#else>
	@javax.validation.constraints.DecimalMin(value = "${prop.min}", groups = <@groups prop/>)
</#if>
</#if>
<#if prop.max??>
<#if prop.subtype == "整数" || prop.subtype == "整数(Long)">
	@javax.validation.constraints.Max(value = ${prop.max}, groups = <@groups prop/>)
<#else>
	@javax.validation.constraints.DecimalMax(value = "${prop.max}", groups = <@groups prop/>)
</#if>
</#if>
<#if !prop.subtype??>
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_X)
	private java.math.BigDecimal ${prop.name};
<#elseif prop.subtype == "整数">
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.INTEGER)
	private Integer ${prop.name};
<#elseif prop.subtype == "整数(Long)">
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.LONG)
	private Long ${prop.name};
<#elseif prop.subtype == "小数1桁">
	@cherry.fundamental.validator.NumberScale(1)
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_1)
	private java.math.BigDecimal ${prop.name};
<#elseif prop.subtype == "小数2桁">
	@cherry.fundamental.validator.NumberScale(2)
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_2)
	private java.math.BigDecimal ${prop.name};
<#elseif prop.subtype == "小数3桁">
	@cherry.fundamental.validator.NumberScale(3)
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_3)
	private java.math.BigDecimal ${prop.name};
<#elseif prop.subtype == "小数4桁">
	@cherry.fundamental.validator.NumberScale(4)
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_4)
	private java.math.BigDecimal ${prop.name};
<#elseif prop.subtype == "小数5桁">
	@cherry.fundamental.validator.NumberScale(5)
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_5)
	private java.math.BigDecimal ${prop.name};
<#else>
	@org.springframework.format.annotation.NumberFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DECIMAL_X)
	private java.math.BigDecimal ${prop.name};
</#if>

<#break>
<#case "日付">
<@notnull prop />
	@org.springframework.format.annotation.DateTimeFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DATE)
	private java.time.LocalDate ${prop.name};

<#break>
<#case "時刻">
<@notnull prop />
	@org.springframework.format.annotation.DateTimeFormat(pattern = ${typeDef.attr.FORMATPATTERN}.TIME)
	private java.time.LocalTime ${prop.name};

<#break>
<#case "日時">
<@notnull prop />
	@org.springframework.format.annotation.DateTimeFormat(pattern = ${typeDef.attr.FORMATPATTERN}.DATETIME)
	private java.time.LocalDateTime ${prop.name};

<#break>
<#case "ファイル">
<@notnull prop />
	private org.springframework.web.multipart.MultipartFile ${prop.name};

<#break>
<#case "フラグ">
	private boolean ${prop.name};

<#break>
<#case "ロックバージョン">
	private Integer ${prop.name};

<#break>
<#case "ページネーション">
	private long ${prop.name} = 0L;

<#break>
<#case "サブフォーム">
<@notnull prop />
	@javax.validation.Valid()
	private ${prop.subformType} ${prop.name};

<#break>
<#case "文字列">
<@notempty prop />
<#if prop.minlen??>
	@cherry.fundamental.validator.MinLength(value = ${prop.minlen}, groups = <@groups prop/>)
</#if>
<#if prop.maxlen??>
	@cherry.fundamental.validator.MaxLength(value = ${prop.maxlen}, groups = <@groups prop/>)
</#if>
<#if !prop.subtype??>
<#elseif prop.subtype == "メール">
	@org.hibernate.validator.constraints.Email(groups = <@groups prop/>)
<#elseif prop.subtype == "電話番号">
	@cherry.fundamental.validator.TelNo(groups = <@groups prop/>)
<#elseif prop.subtype == "郵便番号">
	@cherry.fundamental.validator.ZipCode(groups = <@groups prop/>)
<#elseif prop.subtype == "半角">
	@cherry.fundamental.validator.CharTypeBasicLatin(groups = <@groups prop/>)
<#elseif prop.subtype == "半角(カナ含む)">
	@cherry.fundamental.validator.CharTypeHalfWidth(groups = <@groups prop/>)
<#elseif prop.subtype == "半角数字">
	@cherry.fundamental.validator.CharTypeNumeric(groups = <@groups prop/>)
<#elseif prop.subtype == "半角英字">
	@cherry.fundamental.validator.CharTypeAlpha(groups = <@groups prop/>)
<#elseif prop.subtype == "半角英数字">
	@cherry.fundamental.validator.CharTypeAlphaNumeric(groups = <@groups prop/>)
<#elseif prop.subtype == "半角カナ">
	@cherry.fundamental.validator.CharTypeHalfKatakana(groups = <@groups prop/>)
<#elseif prop.subtype == "全角">
	@cherry.fundamental.validator.CharTypeFullWidth(groups = <@groups prop/>)
<#elseif prop.subtype == "全角数字">
	@cherry.fundamental.validator.CharTypeFullNumeric(groups = <@groups prop/>)
<#elseif prop.subtype == "全角英字">
	@cherry.fundamental.validator.CharTypeFullAlpha(groups = <@groups prop/>)
<#elseif prop.subtype == "全角英数字">
	@cherry.fundamental.validator.CharTypeFullAlphaNumeric(groups = <@groups prop/>)
<#elseif prop.subtype == "全角ひらがな">
	@cherry.fundamental.validator.CharTypeFullHiragana(groups = <@groups prop/>)
<#elseif prop.subtype == "全角カタカナ">
	@cherry.fundamental.validator.CharTypeFullKatakana(groups = <@groups prop/>)
<#else>
</#if>
	private String ${prop.name};

<#break>
</#switch>
</#list>
	@Getter
	public enum Prop {
<#list typeDef.itemDef as prop>
<#if prop.name??>
		${upperCamel(prop.name)}("${prop.name}", "${formName(typeDef.fqcn)}.${prop.name}"), //
</#if>
</#list>
		DUMMY("dummy", "dummy");

		private final String name;
		private final String nameWithForm;

		private Prop(String name, String nameWithForm) {
			this.name = name;
			this.nameWithForm = nameWithForm;
		}

		public MessageSourceResolvable resolve() {
			return BizErrorUtil.resolve(nameWithForm);
		}
	}

}
