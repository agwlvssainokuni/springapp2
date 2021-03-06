<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title">スタブ設定ツール</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>${title}</title>
<link rel="stylesheet" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" media="screen" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

		$("#registerBtn").click(function(event) {
			$.ajax($("#toolUri").val(), {
				method : "POST",
				data : {
					className : $("#className").val(),
					methodName : $("#methodName").val(),
					methodIndex : $("#methodIndex").val(),
					value : $("#value").val(),
					valueType : $("#valueType").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#result").val(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

		$("#peekBtn").click(function(event) {
			$.ajax($("#toolUri").val() + "?peek", {
				method : "POST",
				data : {
					className : $("#className").val(),
					methodName : $("#methodName").val(),
					methodIndex : $("#methodIndex").val()
				},
				success : function(data, textStatus, jqXHR) {
					if (data == null) {
						return;
					}
					$("#value").val(data[0]);
					$("#valueType").val(data[1]);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

		$("#className").blur(function(event) {
			var old = $(this).data("old");
			if (old == $(this).val()) {
				return;
			}
			$(this).data("old", $(this).val());
			if ("" == $(this).val()) {
				return;
			}
			$.ajax($("#toolUri").val() + "?bean", {
				method : "POST",
				data : {
					className : $("#className").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#beanName option.withValue").remove();
					for (var i = 0; i < data.length; i++) {
						var opt = $("<option/>").addClass("withValue");
						opt.attr("value", data[i]).attr("label", data[i]).text(data[i]);
						$("#beanName").append(opt);
					}
					$("#beanName option.withValue:first").attr("selected", "selected");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

		$("#methodName").blur(function(event) {
			var old = $(this).data("old");
			if (old == $(this).val()) {
				return;
			}
			$(this).data("old", $(this).val());
			if ("" == $(this).val() || "" == $("#className").val()) {
				return;
			}
			$.ajax($("#toolUri").val() + "?method", {
				method : "POST",
				data : {
					className : $("#className").val(),
					methodName : $("#methodName").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#methodIndex option.withValue").remove();
					for (var i = 0; i < data.length; i++) {
						var opt = $("<option/>").addClass("withValue");
						opt.attr("value", i).attr("label", data[i]).text(data[i]);
						$("#methodIndex").append(opt);
					}
					$("#methodIndex option.withValue:first").attr("selected", "selected");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

		$("#clearBtn").click(function(event) {
			$("#value").val("");
			$("#valueType").val("");
		});

		$("#listBtn").click(function(event) {
			$.ajax($("#toolUri").val() + "?list", {
				method : "POST",
				data : {
					className : $("#className").val()
				},
				success : function(data, textStatus, jqXHR) {
					$("#result").val(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

	});
</script>
</head>
<body role="document">
	<div class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="nav navbar-header">
				<div class="navbar-brand">${title}</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<h2 class="page-header">${title}</h2>
		<div role="form" class="form-horizontal">
			<div class="form-group">
				<label for="toolUri" class="col-sm-1 control-label">ツールURI</label>
				<div class="col-sm-11">
					<input type="text" id="toolUri" name="toolUri" class="form-control" placeholder="ツールのURIを指定してください"
						value="<c:url value="/testtool/stubrepos" />" />
				</div>
			</div>
			<div class="form-group">
				<label for="className" class="col-sm-1 control-label">クラス</label>
				<div class="col-sm-7">
					<input type="text" id="className" name="className" class="form-control" placeholder="BeanのFQCNを指定してください" />
				</div>
				<div class="col-sm-4">
					<select id="beanName" name="beanName" class="form-control">
						<option value="0" label="(参考)">(参考)</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="methodName" class="col-sm-1 control-label">メソッド</label>
				<div class="col-sm-7">
					<input type="text" id="methodName" name="methodName" class="form-control" placeholder="メソッドの名称を指定してください" />
				</div>
				<div class="col-sm-4">
					<select id="methodIndex" name="methodIndex" class="form-control">
						<option value="0" label="メソッドを引数のパターンで指定">メソッドを引数のパターンで指定</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="value" class="col-sm-1 control-label">返却値
					<button class="btn btn-default btn-xs" type="button" id="clearBtn">クリア</button>
					<button class="btn btn-default btn-xs" type="button" id="peekBtn">現在値</button>
				</label>
				<div class="col-sm-7">
					<textarea rows="3" cols="1" id="value" name="value" class="form-control" placeholder="返却値をJSON形式で指定"></textarea>
				</div>
				<div class="col-sm-4">
					<textarea rows="3" cols="1" id="valueType" name="valueType" class="form-control" placeholder="返却値の型を指定(非必須)"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-1">
					<button class="btn btn-default btn-block" type="button" id="listBtn">一覧</button>
				</div>
				<div class="col-sm-11">
					<button class="btn btn-default btn-block" type="button" id="registerBtn">登録</button>
				</div>
			</div>
		</div>
		<div class="form-horizontal" role="form">
			<div class="form-group">
				<label for="result" class="col-sm-1 control-label">登録結果</label>
				<div class="col-sm-11">
					<textarea rows="5" cols="1" id="result" name="result" class="form-control"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<address class="text-center">Copyright &copy;, 2015, agwlvssainokuni</address>
	</div>
</body>
</html>
