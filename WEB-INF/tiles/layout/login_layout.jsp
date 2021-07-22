<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT"> 
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
<link href="<%=request.getContextPath()%>/resources/css/common.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/login.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>

<!-- 알림창 관련 Javascript -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-confirm.css">
<link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery-confirm.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/alarm.js"></script>

<script src="<%=request.getContextPath()%>/resources/js/common.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/login.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var message = gup('msg', document.location.href);
		if (message != null) {
			if (message == 'auth') {
				alertBox("관리자권한이 아닙니다");
			} else {
				alertBox("아이디 또는 비밀번호를 확인해주세요");
			}
		}
	});
</script>
</head>
<body class="login_body">
	<div class="login_con">
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>