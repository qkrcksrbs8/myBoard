<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript">
function page_alert(){
	alertBox("페이지 준비중입니다.");
}

var color = '${userColor}';
if(color != ''){
	color_change(color);
}

</script>

<div class="con_wrap con_wrap_100 header_info_wrap">
	<div class="header_date">
		<p class="header_time">2019년 01월 01일 11:35:26</p>
	</div>
	<div class="header_user">
		<i class="fa fa-user"></i>
		<span>${userName}</span>
	</div>
</div>
<nav class="header_menu">
	<div id="logo" onclick="javascript:location.href='<%=request.getContextPath()%>/main'">
		<p class="logo_icon"><img src="<%=request.getContextPath()%>/resources/img/logo_icon.png" /></p>
		<p class="logo_txt"><img src="<%=request.getContextPath()%>/resources/img/logo_txt.png" /></p>
		<p class="logo_des">POROSTONE</p>
	</div>
	
	<div class="header_logout" onclick="javascript:location.href='<%=request.getContextPath()%>/logout'">
		<i class="fa fa-power-off"></i>
	</div>
	
	<ul class="menu">
		<li>  
			<a href="<%=request.getContextPath()%>/poroBoard">게시판</a>
		</li>
	</ul>
</nav>