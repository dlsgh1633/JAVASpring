<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/module/header.jsp" %> 

 <!-- Topbar -->
          <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <!-- Sidebar Toggle (Topbar) -->
            <form class="form-inline">
              <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                <i class="fa fa-bars"></i>
              </button>
            </form>

            <!-- Topbar Navbar -->
            <ul class="navbar-nav ml-auto">
              <div class="topbar-divider d-none d-sm-block"></div>

              <!-- Nav Item - User Information -->
              <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <!--  <span class="mr-2 d-none d-lg-inline text-gray-600 small">닉네임</span> -->  
                  <c:choose>
                    <c:when test="${not empty pageContext.request.userPrincipal}">		
                      <span class="mr-2 d-none d-lg-inline text-gray-600 small"/>
                      	<sec:authentication property="principal.username"/>
                      </span>
                    </c:when>
                    <c:otherwise>
                      <span class="mr-2 d-none d-lg-inline text-gray-600 small"/>Guest</span>
                    </c:otherwise>
                  </c:choose>
                   <img class="img-profile rounded-circle" src="/resources/img/undraw_profile.svg">
                </a>
                <!-- Dropdown - User Information -->
                <c:if test="${not empty pageContext.request.userPrincipal}">
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                  <a class="dropdown-item" href="#">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                  </a>

                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item"  data-toggle="modal" data-target="#logoutModal" id="logout" name="logout">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Logout
                  </a>
                </div>
                </c:if>
              </li>
            </ul>
          </nav>
 <!-- End of Topbar -->
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
          
    
  
          
 <script type="text/javascript">

	$('#logout').on('click', function(e) {
	  
	    if (confirm("로그아웃 하시겠습니까?")) {
	        $.ajax({
	            url: "/member/logout",
	            type: "POST",
	            dataType: "JSON",
	            success: function(response) {
	                if (response.result === 'success') {
	                    alert('로그아웃이 완료되었습니다.');
	                    window.location.href = "/main";
	                } else {
	                    alert("로그아웃에 실패했습니다.");
	                    window.location.href = '/error/error';
	                }
	            },
	            error: function(xhr, status, error) {
	                alert("오류가 발생했습니다.");
	                window.location.href = '/member/login';
	            }
	        });
	    } else {
	        alert("취소되었습니다.");
	    }
	});

 </script>         