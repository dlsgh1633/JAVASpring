<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Login</title>

<!-- Custom fonts for this template-->
<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
	<div class="container">
		<!-- Outer Row -->
		<div class="row justify-content-center">
			<div class="col-xl-10 col-lg-12 col-md-9">
				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
									</div>
									<form class="user" name="memberForm" id="memberForm" action="/member/logincheck" onsubmit="return false;" method="POST">
										<div class="form-group">
											<input type="text" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="Enter Email Address..." name="email" id="email">
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user" placeholder="Password" name="password" id="password">
										</div>

										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input" id="rememberme" name="rememberme">
												<label class="custom-control-label" for="rememberme">Remember Me</label>
											</div>
										</div>
										<button class="btn btn-primary btn-user btn-block" type="submit" id="loginsubmit" name="loginsubmit">로그인</button>
										<hr>
										<div class="text-center">
											<a class="small" href="/member/membership">Create an Account!</a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>

</body>
<script type="text/javascript">
	$(document).ready(function() {

		$("#email").on('input', function(event) {
			const regexp = /[^a-zA-Z0-9_@.]/g;

			const emailvalue = $(this).val();

			if (regexp.test(emailvalue)) {

				alert("영어, 특수문자, 숫자 ( _ , @)만 입력 가능합니다.");

				$(this).val(emailvalue.replace(regexp, ''));

			}

		});

		if (localStorage.getItem("rememberEmail")) {
			console.log("제발 기억해다오");
			$('#email').val(localStorage.getItem("rememberEmail"));
			$('#rememberme').prop('checked', true);
		}

		$('#loginsubmit').on('click', function(e) {

			const email = $('#email').val();
			const password = $('#password').val();

			const rememberMe = $('#rememberme').is(':checked');
			console.log(rememberMe);
			console.log("이메일 입력값 : " + email);

			if (rememberMe) { //true
				localStorage.setItem("rememberEmail", email);
			} else {
				localStorage.removeItem("rememberEmail");
			}

			$.ajax({
				url : "/member/logincheck",
				type : "POST",
				data : {
					email : email,
					password : password
				},
				dataType : "JSON",
				//contentType: "application/json",
				success : function(response) {
					if (response.result === 'success') {
						alert("로그인이 되었습니다.");
						window.location.href = '/main';
						console.log("스프링 ajax 성공 ~~~");
					} else {
						alert("이메일 및 비밀번호를 다시 확인해주세요.");
						window.location.href = '/member/login';

					}

				},
				error : function(xhr, status, error) {
					alert("오류가 발생했습니다.");
					window.location.href = '/member/login';
				}
			});
		});
	});
</script>
</html>