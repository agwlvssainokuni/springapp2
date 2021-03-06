<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fundamental" uri="urn:cherry:fundamental"%>
<%@ attribute name="sortBy" required="true" rtexprvalue="true"%>
<%@ attribute name="sortOrder" required="true" rtexprvalue="true"%>
<%@ attribute name="sortByItems" required="true" rtexprvalue="true" type="java.util.List"%>
<%@ attribute name="cssClass" required="true" rtexprvalue="true"%>
<div class="ex-page-sorter form-inline">
	<div class="form-group">
		<f:select path="${sortBy}" id="${sortBy}3" cssClass="${cssClass} form-control input-sm">
			<f:options itemLabel="codeLabel" itemValue="codeValue" items="${sortByItems}" />
		</f:select>
	</div>
	<div class="form-group">
		<c:forEach var="item" items="${fundamental:getEnumList('cherry.fundamental.spring.webmvc.SortOrder')}">
			<label class="radio-inline">
				<f:radiobutton path="${sortOrder}" value="${item.name()}" cssClass="${cssClass}" />
				<s:message code="cherry.fundamental.spring.webmvc.SortOrder.${item.name()}" />
			</label>
		</c:forEach>
	</div>
</div>
