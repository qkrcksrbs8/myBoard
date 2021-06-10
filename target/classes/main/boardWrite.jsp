<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
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
	
	$('#boardUpdateBtn').on('click', function(){
		var board_title = $('#insertTitle').val();
		var board_content = $('#insertContent').val();
		
		if(board_title.length < 1){
			
			alert('제목을 입력하세요.');
			return false;
		
		}else if(board_content.length < 1){
		
			alert('내용을 입력하세요.');
			return false;
		
		}else if(getByteB(board_title) > 25){
			
			alert('제목은 25byte 이하로 작성해주세요.');
			return false;
			
		}else if(getByteB(board_content) > 200){
			
			alert('내용은 200byte 이하로 작성해주세요.'	);
			return false;
			
		}//if
		
		var special_pattern = /[\{\}\[\]\/?;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
		if( special_pattern.test(board_title) == true ){
		    alert('제목에 특수문자는 사용할 수 없습니다.');
		    return false;
		}
		if( special_pattern.test(board_content) == true ){
		    alert('내용에 특수문자는 사용할 수 없습니다.');
		    return false;
		}
		
		if(confirm("저장 하시겠습니까?")){ 
			
			var user_name = 'admin'
			
			$.ajax({
			    type : 'POST',
			    url : 'insertBoard.do', 
			    data : {
			    	board_title : board_title
			    	,board_content : board_content
			    	, user_name : user_name
			    },
			    error : function(error) {
			        alert("네트워크가 원활하지 않습니다. 잠시 후 다시 시도해주세요.");
			    },
			    success : function(data) {
			        alert("저장되었습니다.");
			        location.href = 'boardList.do';
			    }
			});
			
		}else{
			return;
		}
		
	})
	
});


function getByteB(str) {//byte 체크

	var byte = 0;

	for (var i=0; i<str.length; ++i) {

	// 기본 한글 2바이트 처리
	(str.charCodeAt(i) > 127) ? byte += 2 : byte++ ;

	}

	return byte;

}

 
</script>
<body>
<!-- 게시글 작성 list header -->
<section id="content" class="board-list-header-wrap">
	<div class="container">
		<h3 class="board-list-header"><i class="icon-clipboard-list" style="margin-right: 10px;"></i>게시글 작성</h3>
	</div>
</section>
	
	<section id="content">
		<div class="container board-view-con">
			<div class="board-view-tit">
				<textarea class="required sm-form-control" id="insertTitle" rows="1" cols="30"></textarea> 
			</div>
		</div>
	</section>		
		
	<section id="content"> 
		<div class="container board-view-con"> 
			<div class="board-view-sub" style="min-height: 200px; text-align: left;">
				<textarea class="required sm-form-control" id="insertContent" rows="10" cols="30" ></textarea> 
			</div>
		</div>
	</section>		
	
	<section id="content">
		<div class="container board-view-con">  
			<div class="board-view-sub board-view-utilbtn">
				<button id="boardUpdateBtn" class="btn btn-success" type="submit">작성 완료</button>
				<a href="boardList.do"><button class="btn btn-success">작성 취소</button></a>
			</div>
		</div>
	</section>		

</body>
</html>