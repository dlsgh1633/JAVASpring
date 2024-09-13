<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<!-- Custom fonts for this template-->
<link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">
	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
							</div>
							<form class="user" method="post" name="memberForm" id="memberForm" action="/member/membership">
								<div class="form-group">
									<input type="text" name="NAME" id="name" class="form-control form-control-user" placeholder="이름" maxlength="10">
									<input type="hidden" id="nameduplication" name="nameduplication" value="false">
								</div>
								<div id="nameMessage" class="text-center"></div>
								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0">
										<input type="text" name="EMAIL" id="EMAIL" class="form-control form-control-user" placeholder="이메일주소">
									</div>
									<div id="emailMessage" class="text-center"></div>

									<div class="col-sm-3">
										<button type="button" class="btn btn-primary btn-user btn-block" id="checkEmail" name="checkEmail">중복확인</button>
									</div>
									<input type="hidden" id="emailduplication" name="emailduplication" value="false">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" name="PASSWORD" id="PASSWORD" class="form-control form-control-user" placeholder="비밀번호">
									</div>
									<div class="col-sm-6">
										<input type="password" name="PASSWORD2" id="PASSWORD2" class="form-control form-control-user" placeholder="비밀번호 확인">
									</div>
								</div>
								<div class="form-group">
									<input type="text" name="TEL" id="TEL" class="form-control form-control-user" placeholder="휴대폰번호" maxlength="13">
								</div>
								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0">
										<input type="text" name="ADDR" id="sample6_address" class="form-control form-control-user" placeholder="주소" readonly>
									</div>
									<div class="col-sm-3">
										<input type="button" onclick="daumPost()" class="btn btn-primary btn-user btn-block" value=" 주소찾기">
									</div>
								</div>
								<div class="form-group">
									<input type="text" name="ADDR_DETAIL" id="sample6_detailAddress" class="form-control form-control-user" placeholder="상세주소">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" name="STREETNUM" id="sample6_postcode" name="sample6_postcode" class="form-control form-control-user" placeholder="우편번호" readonly>
									</div>
									<div class="col-sm-6">
										<input type="text" name="PS" id="sample6_extraAddress" class="form-control form-control-user" placeholder="참고사항" readonly>
									</div>
								</div>

								<!--   <a href="login.html" class="btn btn-primary btn-user btn-block"> Register Account</a> -->
								<button type="submit" id="submitCheck" name="submitCheck" class="btn btn-primary btn-user btn-block">Register Account</button>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="/member/login">Already have an account? Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->

	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>

	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
	<!--  요건 다음 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="/resources/functionJS/ajax.js"></script>
	<script src="/resources/functionJS/global.js"></script>
	<script src="/resources/functionJS/validate.js"></script>

</body>

<script type="text/javascript">
$(document).ready(function() {

	let emailVaule2 = '';

	validate.validateInput('#name', 'input', validate.regex.name, "영문 및 숫자입력만 가능합니다.");
	validate.validateInput('#EMAIL', 'input', validate.regex.emailInput, "영어, 특수문자, 숫자 ( _ , @)만 입력 가능합니다.");
	validate.oppositeInput('#PASSWORD', 'change', validate.regex.password, "비밀번호는 특수문자,숫자,문자를 포함한 최소 8~15자리만 가능합니다.", "사용가능한 비밀번호입니다.");
	validate.autoHyphen('#TEL', 'input');
	validate.oppositeInput('#TEL', 'change', validate.regex.tel, '잘못된 전화번호양식입니다. 하이픈을 포함한 010-xxxx-xxxx로 입력바랍니다.');


	$("#name").on('change', function(event) {
		let name = $(this).val();
		if (name === '') {
			$('#name').focus();
			return $('#nameMessage').text('이름(닉네임)을 작성해주세요.');
		};

		ajax.post('/member/checkName', { name: name })
			.then((res) => {
				if (res === 'possible') {
					$('#nameduplication').val('true');
					$('#nameMessage').text('사용가능한 이름(닉네임)입니다.');

				} else {
					$('#nameduplication').val('false');
					$('#nameMessage').text('이미 사용중인 이름(닉네임) 입니다.');
				}
			}).catch((err) => {
				alertMessage("에러가 발생했습니다");
			});
	});

	$('#checkEmail').on('click', async function(e) {
		const email = $('#EMAIL').val();
		if (!validate.regex.email.test(email)) {
			alertMessage('올바른 이메일 형식이 아닙니다.');
			$('#EMAIL').focus();
			return false;
		}

		ajax.post('/member/checkMail', { email: email })
			.then((res) => {
				if (res === 'possible') {
					alertMessage("사용가능한 이메일입니다.");
					$('#emailduplication').val('true');
					emailVaule2 = email;
				} else {
					alertMessage("중복된 이메일입니다.");
					$('#emailduplication').val('false');
				}
			}).catch((err) => {
				alertMessage("에러가 발생했습니다");
			});
	});


	$('#PASSWORD , #PASSWORD2').on('change', function() {
		const password1 = $('#PASSWORD').val();
		const password2 = $('#PASSWORD2').val();
		if (password1 != '' && password2 != '') {
			if (password1 !== password2) {
				alertMessage("비밀번호를 다시 확인하여 주세요.");
				$('#PASSWORD2').val('');
				$('#PASSWORD2').focus();
			}
			else {
				alert("비밀번호 확인이 완료 되었습니다.");
			}
		}
	});


	$('#memberForm').on('submit', function(e) {
		e.preventDefault();
		
		 const namecheck = $('#name').val();
		 const emailcheck = $('#EMAIL').val();
		 const pwcheck = $('#PASSWORD').val();
		 const pwcheck2 = $('#PASSWORD2').val();
		 const telcheck = $('#TEL').val();
		 const addrcheck = $('#sample6_address').val();
		 const stnumcheck = $('#sample6_postcode').val();
		 const emailDuplication = $('#emailduplication').val();
		 const nameduplication = $('#nameduplication').val();
		 const postcodecheck = $('#sample6_postcode').val();
		
		if (!checkValidateInput('#name', validate.regex.name2, '이름은 영문과 숫자로 1~10자 이내로 입력해주세요.')) return false;
		if (!checkValidateInput('#EMAIL', validate.regex.email, '올바른 이메일 형식이 아닙니다.')) return false;
		if (!checkValidateInput('#PASSWORD', validate.regex.password, '비밀번호는 특수문자, 숫자, 문자를 포함한 최소 8~15자리만 가능합니다.')) return false;
		if (!checkValidateInput('#TEL', validate.regex.tel, '잘못된 전화번호 양식입니다. 하이폰을 포함한 010-xxxx-xxxx로 입력바랍니다.')) return false;
		if (!checkValidateInput('#sample6_postcode', validate.regex.postcode, '우편번호는 숫자만 가능합니다')) return false;

		// 이름 중복 확인
		ajax.asyncpost('/member/checkName', { name: namecheck }, function(response) {
			if (response !== 'possible') {
				$('#nameduplication').val('false');
				$('#nameMessage').text('이미 사용중인 이름(닉네임) 입니다.');
				return false;
			} else {
				$('#nameduplication').val('true');


				if (emailVaule2 !== emailcheck) {
					alertMessage("이메일 중복체크를 다시 확인하여주세요");
					$('#emailduplication').val('false');
					return false;
				}

				ajax.post('/member/checkMail', { email: emailcheck })
					.then(function(res) {
						if (res === 'possible') {
							$('#emailduplication').val('true');
						} else {
							$('#emailduplication').val('false');
							alert("이메일 중복확인을 다시 해주세요.");
							$('#EMAIL').focus();
							return;
						}

						if ($('#emailduplication').val() === 'false') {
							alertMessage("이메일 중복확인은 필수입니다.");
							return false;
						}

						if (pwcheck !== pwcheck2) {
							alertMessage('비밀번호와 비밀번호 확인란이 일치하지 않습니다.');
							$('#PASSWORD2').focus();
							return false;
						}
						if (addrcheck === '' || stnumcheck === '' || postcodecheck === '') {
							alertMessage("주소는 필수 사항입니다.");
							return false;
						}

						if (confirm("회원가입을 하시겠습니까?")) {
							alertMessage('회원가입이 완료되었습니다.');
							window.location.href = "/member/login";
							this.submit();
						} else {
							alertMessage("취소되었습니다.");
							return false;
						}
					})
					.catch(function(err) {
						alertMessage('오류가 발생했습니다');
					});
			}
		});
	});
});
</script>
</html>


