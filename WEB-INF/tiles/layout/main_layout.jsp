<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<tiles:insertAttribute name="title" ignore="true" />
</title>
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT"> 
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="MRES">
<meta name="author" content="">

<link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/common.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.popupoverlay.js"></script>
	
<!-- 알림창 관련 Javascript -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-confirm.css">
<link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" />
<script src="<%=request.getContextPath()%>/resources/js/jquery-confirm.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/alarm.js"></script>

<link href="<%=request.getContextPath()%>/resources/css/bootstrap-datepicker3.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.kr.js"></script>

<link href="<%=request.getContextPath()%>/resources/jquery-ui/jquery-ui.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/jqGrid/css/ui.jqgrid.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/jqGrid/js/i18n/grid.locale-en.js"></script>
<script src="<%=request.getContextPath()%>/resources/jqGrid/js/jquery.jqGrid.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common.js?ver=1"></script>

<link href="<%=request.getContextPath()%>/resources/css/jquery.mCustomScrollbar.css" rel="stylesheet">
 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.mCustomScrollbar.concat.min.js"></script>
  
<script type="text/javascript">
$(function() {
// 	$('.scroll_wrap').mCustomScrollbar({
// 		theme:"minimal"
// 	});	
});
</script>

</head>
<body class="mb_wrap">
	<div class="header_wrap">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div class="scroll_wrap">
		<div class="contents_wrap">
			<tiles:insertAttribute name="subTitle" />
			<tiles:insertAttribute name="body" />
		</div>
	</div>

	<div id="thema_wrap">
		<div class="nd5 nds" data-toggle="tooltip" data-placement="left" onclick="color_change_click(5, '${userId}')">
			<span class="floaty-btn-label">남색</span>
			<p class="thema_icon"><i class="fa fa-desktop"></i></p>
		</div>
		<div class="nd4 nds" data-toggle="tooltip" data-placement="left" onclick="color_change_click(4, '${userId}')">
			<span class="floaty-btn-label">민트</span>
			<p class="thema_icon"><i class="fa fa-desktop"></i></p>
		</div>
		<div class="nd3 nds" data-toggle="tooltip" data-placement="left" onclick="color_change_click(3, '${userId}')">
			<span class="floaty-btn-label">보라</span>
			<p class="thema_icon"><i class="fa fa-desktop"></i></p>
		</div>
		<div class="nd2 nds" data-toggle="tooltip" data-placement="left" onclick="color_change_click(2, '${userId}')">
			<span class="floaty-btn-label">연두</span>
			<p class="thema_icon"><i class="fa fa-desktop"></i></p>
		</div>
		<div class="nd1 nds" data-toggle="tooltip" data-placement="left" onclick="color_change_click(1, '${userId}')">
			<span class="floaty-btn-label">파랑</span>
			<p class="thema_icon"><i class="fa fa-desktop"></i></p>
		</div>

		<div id="thema_btn" data-toggle="tooltip" data-placement="left">
			<p class="plus"><i class="fa fa-paint-brush"></i></p>
			<p class="edit"><i class="fa fa-eraser"></i></p>
		</div>
	</div>

</body>
</html>

