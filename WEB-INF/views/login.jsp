<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<script>

</script>

<div class="con">
	<div>
		<div class="title center"><img src="<%=request.getContextPath()%>/resources/img/login_logo.png" /></div>
		<div class="title_des center">PORO_STONE</div>
	</div>
	<div>
		<div id="loginbox_con">
			<div id="loginbox">
				<form id="loginform" action="login" method="post">
					<div class="input_with_icon">
						<input type="text" placeholder="ID" name="memberId" onkeyup="enterkey();"> <i class="fa fa-user" aria-hidden="true"></i>
					</div>
					<div class="input_with_icon">
						<input type="password" placeholder="PW" name="memberPw" onkeyup="enterkey();"> <i class="fa fa-lock" aria-hidden="true"></i>
					</div>
					<!--<div class="login_des">
						<div class="left">
							<input id='one' type='checkbox' class="basic_checkbox mb_chkbox" />
							<label for='one'> <span></span> 로그인 상태 유지
							</label>
						</div>
						<div class="right login_des_pw">
							<a href="#;">비밀번호찾기</a>
						</div>
					</div>
					-->
					<div>
						<a href="#;" class="loginbox_btn mb_back_color"
							onClick="javascript:check_onclick();">LOGIN</a>
					</div>
<!-- 					<div> -->
<!-- 						<a href="#;" class="loginbox_btn mb_back_color" -->
<!-- 							onClick="javascript:notice_test();">테스트</a> -->
<!-- 					</div> -->
				</form>
			</div>
		</div>
	</div>
</div>

