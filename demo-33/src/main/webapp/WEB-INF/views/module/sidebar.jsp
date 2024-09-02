<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/module/header.jsp" %> 

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/main">
          <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
          </div>
          <div class="sidebar-brand-text mx-3">게시판</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
          <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
            <i class="fas fa-fw fa-folder"></i>
            <span>Pages</span>
          </a>
          <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
              <h6 class="collapse-header">Login Screens:</h6>
              <a class="collapse-item" id="pageLogin" >Login</a> 
              <a class="collapse-item" id="membershipGo" >membership</a>
            </div>
          </div>
        </li>

        <!-- Nav Item - Tables -->
        <li class="nav-item active">
          <a class="nav-link" href="tables.html">
            <i class="fas fa-fw fa-table"></i>
            <span>Tables</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
          <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
      </ul>
      
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



	
	$('#pageLogin').on('click',function(){
		
		$.ajax({
			url:"/member/userstatus",
			type:"GET",
			success : function(response){
				console.log("response의 상태는?"+response);
				if(response === 'null'){
					if(confirm("로그인 하시겠습니까?")){
						window.location.href = "/member/login";
						
					}else{
						alert("취소 되었습니다");
						return false;
					}
				}
				else{
					alert("이미 로그인 상태입니다");
					return false;
				}
			},
			error: function(xhr, status, error){
				alert("오류가 발생했습니다.");
				window.location.href = "/error/error";
			}
				
		});
		
	});
	$('#membershipGo').on('click',function(){
		$.ajax({
			url:"/member/userstatus",
			type:"GET",
			success: function(response){
				if(response === 'null'){
					if(confirm("회원가입 하시겠습니까?")){
						window.location.href = "/member/membership";
					}else{
						alert("취소 되었습니다");
						return false;
					}
				}
				else{
					alert("이미 로그인상태입니다.");
				}
			},
			error : function(xhr, status, error){
				alert("오류가 발생했습니다.");
				window.location.href = "/error/error";
			}
			
		});
	});
	
	

   
   </script>   