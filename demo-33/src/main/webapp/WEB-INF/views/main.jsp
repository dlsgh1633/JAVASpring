<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
				<div class="container-fluid">
					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">게시판</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="" width="100%" cellspacing="0">
									<colgroup>
										<col width="20%">
										<col width="40%">
										<col width="30%">
										<col width="20%">
									</colgroup>

									<thead>
										<tr>
											<th>닉네임</th>
											<th>제목</th>
											<th>날짜</th>
											<th>댓글</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list">
											<tr>
												<td>
													<c:out value="${list.MEMBER_NAME}" />
												</td>
												<td>
													<a href='/board/detail?boardId=<c:out value="${list.BOARDID}"/>'>

														<c:out value="${fn:replace(list.TITLE, ' ', '&nbsp;')}" escapeXml="false" />


													</a>
												</td>
												<td>
													<fmt:formatDate value="${list.REG_DATE}" pattern="yyyy-MM-dd" />
												</td>
												<td>${list.count}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<button type="button" class="btn btn-primary btn float-right" id="goWrite">게시글 작성</button>
								<div style="display: block; text-align: center;">
									<nav aria-label="Page navigation">
										<ul class="pagination justify-content-center">
											<li class="page-item <c:if test='${paging.nowPage == 1}'>disabled</c:if>"><a class="page-link" href="<c:if test='${paging.nowPage != 1}'>/main?nowPage=${paging.nowPage - 1 }&cntPerPage=${paging.cntPerPage}</c:if>" tabindex="-1">Previous</a></li>
											<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
												<c:choose>
													<c:when test="${p == paging.nowPage}">
														<li class="page-item active" aria-current="page"><a class="page-link" href="#">${p}</a></li>
													</c:when>
													<c:otherwise>
														<li class="page-item"><a class="page-link" href="/main?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a></li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
											<li class="page-item <c:if test='${paging.nowPage == paging.lastPage}'>disabled</c:if>"><a class="page-link" href="<c:if test='${paging.nowPage != paging.lastPage}'>/main?nowPage=${paging.nowPage + 1}&cntPerPage=${paging.cntPerPage}</c:if>">Next</a></li>
										</ul>
									</nav>
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
	$('#goWrite').on('click', function() {

		$.ajax({
			url : "/member/userstatus",
			type : "GET",
			success : function(response) {
				if (response === 'null') {
					if (confirm("로그인 하시겠습니까?")) {
						window.location.href = "/member/login";

					} else {
						alert("취소 되었습니다");
						return false;
					}
				} else {
					window.location.href = "/board/write";
				}
			},
			error : function(xhr, status, error) {
				alert("오류가 발생했습니다.");
				window.location.href = "/error/error";
			}

		});

	});
</script>

</html>