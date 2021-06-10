<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/dark.css" type="text/css" />
<link rel="stylesheet" href="css/font-icons.css" type="text/css" />
<link rel="stylesheet" href="css/animate.css" type="text/css" />
<link rel="stylesheet" href="css/magnific-popup.css" type="text/css" />
<link rel="stylesheet" href="css/responsive.css" type="text/css" />
<link rel="stylesheet" href="css/tdl.css" type="text/css" />
<link rel="stylesheet" href="css/imports/shortcodes/misc.css" type="text/css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>

$(function(){
	
	$('#boardInsertBtn').on('click', function(){//글 작성 버튼		
		location.href='writeBoard.do';//글 작성 페이지로 이동		
	});
	
	$('#searchBtn').on('click', function(){//조회버튼		 
		
		var keyWord = $('input[name=keyWord]').val();//검색어 value
		var special_pattern = /[\{\}\[\]\/?;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
		
		if( special_pattern.test(keyWord) == true ){//특수문자 사용 불가
		    alert('특수문자는 사용할 수 없습니다.');
		    return false;
		}
		
	});
	
});

</script>

<style>
ul {
	position:relative;
}
button {
	position : absolute;
	top:50%;  
	right:0;
	transform:translate(0, -50%);
	-webkit-transform:translate(0, -50%);
} 

.form-row > .col, .form-row > [class*="col-"] {
    padding-right: 5px;
    padding-left: 5px;
    padding-bottom: 40px;
}
</style>

<body>

<div class="container">

	<!-- 게시판 헤더 -->
	<section id="content" class="board-list-header-wrap">
		<div class="container">
			<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>게시판 목록</h3>
		</div>
	</section>
	
	<!-- 게시글 없을 때. --> 
	<c:if test="${count==0}"> 
		<section id="content">
			<div class="container">
				<div class="board-list-wrap board-list-header">
					<ul class="row">	
						<li>게시판에 저장된 글이 없습니다.</li>
					</ul>
				</div>
			</div>
		</section>
	</c:if>
	
	<!-- 게시글 있을 때. --> 
	<section id="content">
		<div class="container">
			<div class="board-list-wrap board-list-header">
				<ul class="row">	
					<li class="col-xs-1 col-md-1">번호</li>
					<li class="col-xs-6 col-md-6">제목</li>
					<li class="col-xs-2 col-md-2">작성자</li>
					<li class="col-xs-2 col-md-2">작성일</li>
				</ul> 
			</div>
			<div class="board-list-wrap borard-list-con">
				<c:set var="number" value="${count}" />
				<c:forEach var="boardList" items="${boardList}" varStatus="boardNum">
					<ul class="row">
						<li class="col-md-1 col-xs-1">${boardList.rnum} </li> 
						<li class="col-md-6 col-xs-6"><a href="boardDetail.do?board_seq=${boardList.board_seq}">${boardList.board_title}</a></li>
						<li class="col-md-2 col-xs-2">${boardList.created_by }</li>
						<li class="col-md-2 col-xs-2">${boardList.created_date.substring(2,4)}월 ${boardList.created_date.substring(4)}일</li>	
					</ul>
				</c:forEach>
			</div>
		</div>
	</section>	
	
	<!-- 게시판 페이징  -->
	<section id="content" class="board-list-paging">
		<div class="container">
			<div class="row position:relative;">
				<div class="col-md-12">
					<ul class="pagination">
						<li>
							${pagingHtml}
						</li> 						
					</ul>		 									 
					<button id="boardInsertBtn" class="btn btn-success" style="margin-top: 50px">글작성</button>  			
				</div>
			</div>
		</div>
	</section>		
	
	<section id="content" class="board-list-search-wrap">
		<div class="container">
			<div class="row">
				<div class="col-12 col-md-10  nopadding">
					<form action="boardList.do" name="search" method="POST">
						<div class="form-row">
							<div class="form-group  col-4 col-md-3 offset-md-4">
								<div class="white-section">
									<select id="inputState" name="keyField" class="form-control selectpicker">					
										<option value="onlyTitle">제목</option>
										<option value="titleContent">내용</option>
										<option value="onlyName">작성자</option>
									</select> 
								</div>
							</div>
							<div class="form-group col-md-4">
								<input type="text" name="keyWord" class="form-control">
							</div>
							<div class="form-group col-md-1"   style=" position: relative; margin-bottom: 100px;">
								<button type="submit" class="btn btn-success form-control"><i class="icon-line-search"></i></button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
</div>

</body>
</html>