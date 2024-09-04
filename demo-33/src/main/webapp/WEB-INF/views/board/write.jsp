<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
							<form action="/board/postwrite" method="post" class="h-100" name="insertFrm" enctype="multipart/form-data">
								<div class="card shadow mb-4 h-100">
									<div class="card-header py-3">
										<div class="col-sm-11 float-left">
											<input type="text" id="TITLE" name="TITLE" class="form-control" style="white-space: pre-wrap;"  placeholder="제목" maxlength="20">
										</div>

										<button type="button" id="boardSubmit" class="btn btn-primary btn float-right ml-1">작성완료</button>
									</div>
									<div class="card-body h-100">
										<textarea id="CONTENT" name="CONTENT" cols="30" class="form-control h-100" style="white-space: pre-wrap;" placeholder="내용
                        " style="resize: none" onKeyup="len_chk()"></textarea>
									</div>

									<div class="file_list">
										<div>
											<div class="file_input">
												<input type="text" readonly />

												<input type="file" name="files" id="files" onchange="selectFile(this);" multiple>

											</div>
											<button type="button" onclick="removeFile(this);" class="btns del_btn">
												<span>삭제</span>
											</button>

											<button type="button" onclick="addFile();" class="btns fn_add_btn">
												<span>파일 추가</span>
											</button>
										</div>
									</div>

								</div>
							</form>

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
	<a class="scroll-to-top rounded" href="#page-top">
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

</body>
<script type="text/javascript">


$('#boardSubmit').on('click', function() {
	   
	   var title = $('#TITLE').val().trim(); 
	   var content = $('#CONTENT').val().trim(); 
	   var formdata = new FormData();
	    formdata.append("TITLE", title);
	    formdata.append("CONTENT", content);
		
	    if(title === ''){
	    	 alert("제목은 필수사항입니다.");
	    	return false;
	    }
	    if(content === ''){
	    	alert("내용은 필수사항입니다.");
	    	return false;
	    }

	    $('input[type="file"][name="files"]').each(function() {
	        var files = $(this)[0].files;
	        for (var i = 0; i < files.length; i++) {
	            formdata.append("files", files[i]);
	            
	        }
	    });
	    
	    
    if (confirm("작성을 하시겠습니까?")) {
        $.ajax({
            url: "/board/postwrite",
            type: "POST",
            data: formdata,
            enctype: 'multipart/form-data',
            processData: false,
            contentType : false,
            success: function(response) {
                alert("작성이 완료되었습니다");
                window.location.href = "/main";
            },
            error: function(xhr, status, error) {
                alert("오류가 발생했습니다: " + error);
                return false;
            }
        	
        });
    } else {
        alert("작성을 취소하였습니다");
        return false;
    }
});


function len_chk(){  
	  var frm = document.insertFrm.CONTENT; 
	    
	  if(frm.value.length > 700){  
	       alert("내용의 글자수는 700글자 미만으로 작성하여야합니다.");  
	       frm.value = frm.value.substring(0,700);  
	       return false;  
	  } 

	} 
	

function selectFile(element) {

    const file = element.files[0];
    const filename = element.closest('.file_input').firstElementChild;

   
    if ( !file ) {
        filename.value = '';
        return false;
    }

   
    const fileSize = Math.floor(file.size / 1024 / 1024);
    if (fileSize > 10) {
        alert('10MB 이하의 파일로 업로드해 주세요.');
        filename.value = '';
        element.value = '';
        return false;
    }

   
    filename.value = file.name;
}



function addFile() {
    const fileDiv = document.createElement('div');
    fileDiv.innerHTML =`
        <div class="file_input">
            <input type="text" readonly />
            <label> 첨부파일
                <input type="file" name="files" onchange="selectFile(this);" />
            </label>
        </div>
        <button type="button" onclick="removeFile(this);" class="btns del_btn"><span>삭제</span></button>
    `;
    document.querySelector('.file_list').appendChild(fileDiv);
}



function removeFile(element) {
    const fileAddBtn = element.nextElementSibling;
    if (fileAddBtn) {
        const inputs = element.previousElementSibling.querySelectorAll('input');
        inputs.forEach(input => input.value = '')
        return false;
    }
    element.parentElement.remove();
}





</script>

</html>