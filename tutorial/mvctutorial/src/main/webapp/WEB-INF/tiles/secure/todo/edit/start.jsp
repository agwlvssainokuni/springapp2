<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fundamental" uri="urn:cherry:fundamental"%>
<h2>TODO編集</h2>
<c:if test="${updated}">
	<div class="has-success">
		<div class="help-block">TODOを更新しました。</div>
	</div>
</c:if>
<s:hasBindErrors name="todoEditForm">
	<div class="form-group has-error">
		<div class="help-block bg-danger">
			<f:errors path="todoEditForm" element="div" />
			<s:nestedPath path="todoEditForm">
				<f:errors path="dueDate" element="div" />
				<f:errors path="doneFlg" element="div" />
				<f:errors path="description" element="div" />
			</s:nestedPath>
		</div>
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/todo/edit/execute?id=${param.id}" method="POST" modelAttribute="todoEditForm" role="form">
	<div class="form-group">
		<f:label path="dueDate" cssErrorClass="has-error">期日</f:label>
		<f:input path="dueDate" cssClass="form-control" cssErrorClass="form-control has-error" />
	</div>
	<div class="form-group">
		<f:checkbox path="doneFlg" cssErrorClass="has-error" label="完了" />
	</div>
	<div class="form-group">
		<f:label path="doneAt">完了日時</f:label>
		<f:input path="doneAt" cssClass="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<f:label path="description" cssErrorClass="has-error">内容</f:label>
		<f:textarea path="description" cssClass="form-control" cssErrorClass="form-control has-error" />
	</div>
	<f:hidden path="lockVersion" />
	<f:button type="submit" class="btn btn-default">更新</f:button>
	<fundamental:onetimetoken />
</f:form>
