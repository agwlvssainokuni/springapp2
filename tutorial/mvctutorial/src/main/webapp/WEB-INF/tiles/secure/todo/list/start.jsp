<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<h2>TODO検索</h2>
<s:hasBindErrors name="todoListForm">
	<div class="form-group has-error">
		<div class="help-block bg-danger">
			<f:errors path="todoListForm" element="div" />
			<s:nestedPath path="todoListForm">
				<f:errors path="postedFrom" element="div" />
				<f:errors path="postedTo" element="div" />
				<f:errors path="dueDateFrom" element="div" />
				<f:errors path="dueDateTo" element="div" />
				<f:errors path="done" element="div" />
				<f:errors path="notDone" element="div" />
			</s:nestedPath>
		</div>
	</div>
</s:hasBindErrors>
<c:if test="${pagedList != null && pagedList.list.isEmpty()}">
	<div class="has-warning">
		<div class="help-block">条件に該当する項目はありませn。</div>
	</div>
</c:if>
<f:form servletRelativeAction="/secure/todo/list/execute" method="POST" modelAttribute="todoListForm" role="form">
	<div class="form-group">
		<f:label path="postedFrom" cssErrorClass="has-error">登録日時</f:label>
		<f:input path="postedFrom" cssErrorClass="has-error" />
		-
		<f:input path="postedTo" cssErrorClass="has-error" />
	</div>
	<div class="form-group">
		<f:label path="dueDateFrom" cssErrorClass="has-error">期日</f:label>
		<f:input path="dueDateFrom" cssErrorClass="has-error" />
		-
		<f:input path="dueDateTo" cssErrorClass="has-error" />
	</div>
	<div class="form-group">
		<f:checkbox path="notDone" label="未完了" cssErrorClass="has-error" />
		<f:checkbox path="done" label="完了" cssErrorClass="has-error" />
	</div>
	<div class="form-group">
		<f:label path="sort.by" cssErrorClass="has-error">ソート列</f:label>
		<f:select path="sort.by" cssErrorClass="has-error">
			<foundation:codeList var="sortByList" codeName="todo_list_sort_by" />
			<f:option value="NONE" label="指定しない" />
			<f:options itemValue="codeValue" itemLabel="codeLabel" items="${sortByList}" />
		</f:select>
	</div>
	<div class="form-group">
		<f:label path="sort.order" cssErrorClass="has-error">ソート順</f:label>
		<f:radiobutton path="sort.order" value="ASC" label="昇順" cssErrorClass="has-error" />
		<f:radiobutton path="sort.order" value="DESC" label="降順" cssErrorClass="has-error" />
	</div>
	<input type="hidden" name="pno" value="0" />
	<f:hidden path="psz" />
	<f:button type="submit" class="btn btn-default">検索</f:button>
	<f:button type="submit" class="btn btn-default" name="download">ダウンロード</f:button>
</f:form>
<c:if test="${pagedList != null && !pagedList.list.isEmpty()}">
	<f:form servletRelativeAction="/secure/todo/list/execute" method="POST" modelAttribute="todoListForm" id="HtodoListForm">
		<f:hidden path="postedFrom" id="HpostedFrom" />
		<f:hidden path="postedTo" id="HpostedTo" />
		<f:hidden path="dueDateFrom" id="HdueDateFrom" />
		<f:hidden path="dueDateTo" id="HdueDateTo" />
		<f:hidden path="notDone" id="HnotDone" />
		<f:hidden path="done" id="Hdone" />
		<f:hidden path="sort.by" id="HsortBy" />
		<f:hidden path="sort.order" id="HsortOrder" />
		<f:hidden path="pno" id="Hpno" />
		<f:hidden path="pno" id="Hpsz" />
	</f:form>
	<mytag:pagerLink pageSet="${pagedList.pageSet}" form="#HtodoListForm" pno="pno" />
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>ID</th>
				<th>投稿日時</th>
				<th>期日</th>
				<th>状態</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
				<s:nestedPath path="pagedList.list[${count - 1}]">
					<s:bind path="id">
						<c:url var="url" value="/secure/todo/edit?id=${status.value}" />
					</s:bind>
					<tr>
						<td>${pagedList.pageSet.current.from + count}</td>
						<td><a href="${url}"><s:bind path="id">${status.value}</s:bind></a></td>
						<td><a href="${url}"><s:bind path="postedAt">${status.value}</s:bind></a></td>
						<td><s:bind path="dueDate">${status.value}</s:bind></td>
						<td><s:bind path="doneFlg">${status.actualValue == 0 ? "未完了" : "完了" }</s:bind></td>
					</tr>
				</s:nestedPath>
			</c:forEach>
		</tbody>
	</table>
	<mytag:pagerLink pageSet="${pagedList.pageSet}" form="#HtodoListForm" pno="pno" />
</c:if>
