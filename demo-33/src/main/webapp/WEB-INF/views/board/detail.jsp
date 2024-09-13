<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/module/header.jsp"%>
<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<%@ include file="/WEB-INF/views/module/sidebar.jsp"%>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<%@ include file="/WEB-INF/views/module/topbar.jsp"%>

				<!-- Begin Page Content -->
				<div class="container-fluid h-100">
					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">게시판</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4 h-75">
						<div class="card-body">
							<!-- Basic Card Example -->
							<div class="card shadow mb-4 h-100">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary btn float-left" style="white-space: pre-wrap;">${boardcheck.TITLE}</h6>
									<input type="hidden" name="memberId" value="${boardcheck.MEMBERID}">
									<c:choose>
										<c:when test="${not empty pageContext.request.userPrincipal}">
											<sec:authentication var="sessionUser" property="principal.userid" />
											<c:if test="${sessionUser == boardcheck.MEMBERID}">
												<a href='/board/modify?boardId=<c:out value="${boardcheck.BOARDID}"/>'>
													<button type="button" class="btn btn-primary btn float-right ml-1">수정</button>
												</a>
												<button type="button" id="deleteButton" class="btn btn-danger btn float-right">삭제</button>
											</c:if>
										</c:when>
									</c:choose>
								</div>
								<div class="card-body navbar-nav-scroll" style="height: 290px !important; white-space: pre-wrap;">${boardcheck.CONTENT}</div>
								<div class="card-body fileUpLoad">
									<label class="fileUpLoadBtn"></label>
									<div id="fileName" class="fileName">
										<c:forEach var="FileList" items="${fileList}">

											<a href="/board/${boardcheck.BOARDID}/files/${FileList.FILE_NAME}/download">${FileList.ORG_FILE_NAME}</a>

										</c:forEach>

									</div>

								</div>
								<div class="card-footer">
									<form action="#" id="replyForm" name="replyForm">

										<input type="hidden" name="boardNo" value="1">
										<input type="hidden" name="parentCommentNo" value="0">
										<input type="hidden" name="commentNo" value="0">

										<ul id="commentDiv" style="max-height: 500px; overflow-y: scroll; overflow-x: hidden;">

										</ul>
									</form>
									<c:if test="${not empty pageContext.request.userPrincipal}">
										<form class="flex" id="commentForm" name="commentForm">
											<input type="hidden" name="boardNo" value="1">
											<textarea id="a3" cols="30" row="5" class="form-control flex" style="width: 90%" placeholder="내용">   
										</textarea>
											<a class="commentAdd flex" style="width: 9%">
												<button type="button" id="commentbutton" class="btn btn-primary btn ml-1" style="margin-top: 0.75rem; width: 100%" >등록</button>
											</a>
										</form>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<%@ include file="/WEB-INF/views/module/footer.jsp"%>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top" style="display: none;">
		<i class="fas fa-angle-up"></i>
	</a>


	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/resources/js/demo/datatables-demo.js"></script>
	<script src="/resources/functionJS/ajax.js"></script>
	<script src="/resources/functionJS/global.js"></script>
	<script src="/resources/functionJS/validate.js"></script>
	
</body>
<script type="text/javascript">
$(document).ready(function(){
	
	commentListload();
	$('#a3').val('');
		
});

//댓글 및 대댓글 불러오기
function commentListload(){
	
	let boardId = ${boardcheck.BOARDID};
	let memberId = ${boardcheck.MEMBERID};
	console.log(memberId);
	const commentDiv = $("#commentDiv");
	commentDiv.empty();

	ajax.get('/board/detail/comment',{boardId : boardId, memberId : memberId})
	.then(function(response){
		 response.forEach(function(comment) {
		        const padding = comment.level * 12 + 'px';
				const commentOwner = comment.commentid;
				const boardOwner = comment.boardid;
				const deleteCheck = comment.comment_DELETE;
				const commentFormat = dateFunc(comment.reg_DATE);
				
				let commentHTML =
					"<li >" +
					"<div class='commentDiv' style='padding-left:" + padding + " '>" +
					"<input type='hidden' class='comment-id' value='" + comment.commentid + "'>" +
					"<div class='commentHead'>" +
					"<div class='commentHead1'>" +
					"<div class='commentName write-name'>" + comment.name + "</div>" +
					"<div class='commentDate'>" + commentFormat + "</div>" +
					"</div>" +
					"<div class='commentHead2'>";

				if (deleteCheck === 0 && comment.userNullcheck) {
					commentHTML +=
						"<div class='commentReply' id='reply'>답글</div>"
				}


				if (comment.writer && deleteCheck === 0) {
					commentHTML +=
						"<div class='commentModify'>수정</div>" +
						"<div class='commentRemove'>삭제</div>";
				}

				commentHTML +=

					"<div class='commentCancle' style='display:none;'>취소</div>" +
					"</div>" +
					"</div>" +
					"<div class='comment'>";

				if (comment.comment_DELETE != 1) {
					commentHTML +=
						"<p>" + comment.content + "</p>";
				}
				else {
					commentHTML +=
						"<p>삭제되었습니다.</p>";
				}

				commentHTML +=
					"</div>" +
					"</div>" +
					"<hr class='sidebar-divider d-none d-md-block'>" +
					"</li>";  
		     
			                    
					$('#commentDiv').append(commentHTML);  
	            })
	})
	.catch(function(error){
		alertMessage('에러가 발생하였습니다');
		console.log(error);
	})
}

//게시판 삭제
$('#deleteButton').on('click', function() {	
	boardId = ${boardcheck.BOARDID};
	
if (confirm("게시글을 삭제 하시겠습니까?")) {

 ajax.post('/board/delete',{boardId : boardId})
 .then(function(){
	 alertMessage("삭제가 완료되었습니다");
     window.location.href = "/main";
 })
 .catch(function(){
	 alertMessage("에러가 발생했습니다.");
	 window.location.href ="/error/error";
 })
} else {
  alertMessage("게시글 삭제를 취소하였습니다");
  return false;
}
});

//댓글 등록
// onclick 펑션으로 ?
//이벤트위임 
function commentRegister() {
    let boardId = ${boardcheck.BOARDID};
    let parentId = 0;
    let content = '';
    let writeName = $(this).closest('.commentDiv').find('.commentName').text();
    if ($(this).hasClass('replysubmit')) {
        // 대댓글 등록
        parentId = $(this).closest('li').prev('li').find('.comment-id').val();
        content = $(this).closest('.reply-form').find('.replytext').val().trim();
        console.log('대댓글 등록 실행');
    } else {
        // 일반 댓글 등록
        content = $('#a3').val().trim();
    }

    if (content === '') {
    	console.log('실행됨');
        alertMessage('내용을 입력해주세요.');
        return;
    }

    ajax.post('/comment/post', {content: content, boardId: boardId, parentsId: parentId})
        .then(function(response){
            alertMessage('댓글을 등록하였습니다');
            commentListload();
            $('#a3').val('');
            $('.replytext').val('');
        })
        .catch(function(error){
            alertMessage('에러가 발생했습니다');
            window.location.href = "/error/error";
        });
}

$(document).on('click', '#commentbutton', commentRegister);
$(document).on('click', '.replysubmit', commentRegister);

//대댓글 입력폼
$('#commentDiv').on('click',".commentReply", function() {
	let parentId = $(this).closest('.commentDiv').find('.comment-id').val();
    let commentLi = $(this).closest('li');
    let writeName = $(this).closest('.commentDiv').find('.commentName').text();
    let ModifyForm = $('.modify-form');
    
    if (ModifyForm.length > 0) {        
        ModifyForm.closest('.comment').html("<p>" + originalCommentContent + "</p>");
        ModifyForm.remove();         
    }

    if (commentLi.next().hasClass('reply-form')) {
        commentLi.next().remove();
        return;
    }

    
    $('.reply-form').remove();
    
    let replyForm = 
        "<li class='reply-form' style='padding-left: 4rem;'>"+
            "<form class='flex reply-form'>"+
                "<textarea cols='30' rows='3' class='form-control flex replytext' style='width: 90%' placeholder='대댓글을 입력하세요'></textarea>"+
                "<button type='button' class='btn btn-primary btn ml-1 replysubmit' style='margin-top: 0.75rem; width: 9%' >등록</button>"+
           " </form>"+
        "</li>";
    
        commentLi.after(replyForm);
        
        
	lengthtest = "@" + writeName+" ";
	console.log("길이 체크" + lengthtest.length);
  
    $('.replytext').val("@" + writeName+" "); 
    $('.replytext').focus();
    
    $('.replytext').on('keydown',function(){
    	if($('.replytext').val().length < lengthtest.length){
    		$('.replytext').val(lengthtest);
    		return;
    	}  	
    });    
});

let originalCommentContent = "";
// 대댓글 수정
$('#commentDiv').on('click',".commentModify",function(){
	let commentDiv = $(this).closest('.commentDiv');
	let parentId = $(this).closest('.commentDiv').find('.comment-id').val();
    let commentLi = $(this).closest('.li');
    let modifyReply = $(this).closest('.commentDiv').find('.comment p').text();
    let modifyBackUp = $(this).closest('.commentDiv').find('.comment p').text();   
    let ModifyForm = $('.modify-form');
    let ReplyForm = $('.reply-form');
    let clickModifyForm = commentDiv.find('.modify-form');
    
    if (ModifyForm.length > 0) {
      
    	ModifyForm.closest('.comment').html("<p>" + originalCommentContent + "</p>");
    	ModifyForm.remove(); 
        
    }
    if (clickModifyForm.length > 0) {
        
    	clickModifyForm.closest('.comment').html("<p>" + originalCommentContent + "</p>");
    	clickModifyForm.remove(); 
        return;
    }
    
    if(ReplyForm.length > 0){
    	ReplyForm.remove();
    }
    
 
    originalCommentContent = modifyReply;  

    let modifyForm = 
      "<div class='modify-form'>" +
        "<textarea class='form-control edit-textarea modfiyComment' rows='3'>" + modifyReply + "</textarea>" +
        "<button type='button' class='btn btn-primary btn ml-1 modifybutton modfiyComment'>수정</button>" +
      "</div>";
    
 
        commentDiv.find('.comment').empty().append(modifyForm);
 	    $('.modfiyComment').focus();
    
});

// 댓글 수정
$('#commentDiv').on('click',".modifybutton",function(){
	
	let commentId = $(this).closest('.commentDiv').find('.comment-id').val();
	let content = $(this).closest('.commentDiv').find('.modfiyComment').val().trim();
	
	if(content === ''){
		alertMessage("내용을 입력해주세요.");
		return false;
	}
	
	ajax.post('/comment/modify',{commentId : commentId, content : content})
	.then((response)=>{
		 alertMessage("수정이 완료되었습니다");
		 commentListload();
		 $('.modfiyComment').val();
	})
	.catch((error)=>{
		 alertMessage("에러가 발생했습니다.");
		 window.location.href ="/error/error";
	})
});


// 댓글 삭제 
$('#commentDiv').on('click', ".commentRemove", function() {
    let deleteId = $(this).closest('.commentDiv').find('.comment-id').val();

    if (confirm("삭제하시겠습니까?")) {  
     ajax.post('/comment/delete',{commentId : deleteId})
     .then((response)=>{
    	 alertMessage("해당 댓글을 삭제하였습니다.");
         commentListload();
     })
     .catch((error)=>{
    	  alertMessage("에러가 발생했습니다.");
          window.location.href = "/error/error";
     })   
    } else {
        console.log("삭제가 취소되었습니다.");
    }
});

</script>
</html>