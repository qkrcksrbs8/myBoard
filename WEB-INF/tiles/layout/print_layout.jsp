<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
<link href="<%=request.getContextPath()%>/resources/css/print.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>

<!-- 알림창 관련 Javascript -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-confirm.css">
<link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery-confirm.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/alarm.js"></script>

<script src="<%=request.getContextPath()%>/resources/js/common.js"></script>

</head>
<body class="print_body">
	<div class="print_con">
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>