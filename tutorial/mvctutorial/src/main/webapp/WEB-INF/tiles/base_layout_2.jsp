<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Sprint MVCチュートリアル</title>
<link rel="stylesheet" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<link rel="stylesheet" media="screen" href="<c:url value="/style/mvctutorial.css"/>" />
<script type="text/javascript" src="//code.jquery.com/jquery-3.0.0.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value="/script/mvctutorial.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="nav navbar-header">
			<div class="navbar-brand">Spring MVCチュートリアル</div>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/home/"/>">ホーム</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/logout" />" onclick="JavaScript:$('#logout').submit(); return false;"><span
						class="glyphicon glyphicon-off"></span> ログアウト</a>
					<form action="<c:url value="/logout" />" method="POST" id="logout">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					</form></li>
			</ul>
		</div>
	</div>
	<div class="container" role="main">
		<tiles:insertAttribute name="content" />
	</div>
</body>
</html>
