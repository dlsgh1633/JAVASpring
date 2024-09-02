<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<!-- Custom fonts for this template-->
<link href="/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

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
							<form class="user" method="post" name="memberForm"
								id="memberForm" action="/member/membership">
								<div class="form-group">
									<input type="text" name="NAME" id="name"
										class="form-control form-control-user" placeholder="이름"
										maxlength="10"> <input type="hidden"
										id="nameduplication" name="nameduplication" value="false">
								</div>
								<div id="nameMessage" class="text-center"></div>
								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0">
										<input type="text" name="EMAIL" id="EMAIL"
											class="form-control form-control-user" placeholder="이메일주소">
									</div>
									<div id="emailMessage" class="text-center"></div>

									<div class="col-sm-3">
										<button type="button"
											class="btn btn-primary btn-user btn-block" id="checkEmail"
											name="checkEmail">중복확인</button>
									</div>
									<input type="hidden" id="emailduplication"
										name="emailduplication" value="false">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" name="PASSWORD" id="PASSWORD"
											class="form-control form-control-user" placeholder="비밀번호">
									</div>
									<div class="col-sm-6">
										<input type="password" name="PASSWORD2" id="PASSWORD2"
											class="form-control form-control-user" placeholder="비밀번호 확인">
									</div>
								</div>
								<div class="form-group">
									<input type="text" name="TEL" id="TEL"
										class="form-control form-control-user" placeholder="휴대폰번호" maxlength="13">
								</div>
								<div class="form-group row">
									<div class="col-sm-9 mb-3 mb-sm-0">
										<input type="text" name="ADDR" id="sample6_address"
											class="form-control form-control-user" placeholder="주소" readonly>
									</div>
									<div class="col-sm-3">
										<input type="button" onclick="sample6_execDaumPostcode()"
											class="btn btn-primary btn-user btn-block" value=" 주소찾기" >
									</div>
								</div>
								<div class="form-group">
									<input type="text" name="ADDR_DETAIL"
										id="sample6_detailAddress"
										class="form-control form-control-user" placeholder="상세주소">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" name="STREETNUM" id="sample6_postcode" name="sample6_postcode"
											class="form-control form-control-user" placeholder="우편번호" readonly>
									</div>
									<div class="col-sm-6">
										<input type="text" name="PS" id="sample6_extraAddress"
											class="form-control form-control-user" placeholder="참고사항" readonly>
									</div>
								</div>

								<!--   <a href="login.html" class="btn btn-primary btn-user btn-block"> Register Account</a> -->
								<button type="submit" id="submitCheck" name="submitCheck"
									class="btn btn-primary btn-user btn-block">Register
									Account</button>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="/member/login">Already have an
									account? Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>

	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/js/sb-admin-2.min.js"></script>
	<!--  요건 다음 api -->
	<script
		src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


</body>

<script type="text/javascript">


<!--다음 주소찾기 함수 -->
function sample6_execDaumPostcode() {
       new daum.Postcode({
           oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 각 주소의 노출 규칙에 따라 주소를 조합한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var addr = ''; // 주소 변수
               var extraAddr = ''; // 참고항목 변수

               //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
               if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                   addr = data.roadAddress;
               } else { // 사용자가 지번 주소를 선택했을 경우(J)
                   addr = data.jibunAddress;
               }

               // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
               if(data.userSelectedType === 'R'){
                   // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                   // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                   if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                       extraAddr += data.bname;
                   }
                   // 건물명이 있고, 공동주택일 경우 추가한다.
                   if(data.buildingName !== '' && data.apartment === 'Y'){
                       extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                   }
                   // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                   if(extraAddr !== ''){
                       extraAddr = ' (' + extraAddr + ')';
                   }
                   // 조합된 참고항목을 해당 필드에 넣는다.
                   document.getElementById("sample6_extraAddress").value = extraAddr;
               
               } else {
                   document.getElementById("sample6_extraAddress").value = '';
               }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               document.getElementById('sample6_postcode').value = data.zonecode;
               document.getElementById("sample6_address").value = addr;
               // 커서를 상세주소 필드로 이동한다.
               document.getElementById("sample6_detailAddress").focus();
           }
       }).open();
   }




$(document).ready(function(){
	
	let emailVaule2 = '';
		
	       $("#name").on('input', function (event) {

	    	  const  regexp = /[^a-zA-Z0-9]{1,10}$/;
	          const  nameval = $(this).val();
	            
	            if (regexp.test(nameval)) {
					
	                alert("영문 및 숫자입력만 가능합니다.");

	                $(this).val(nameval.replace(regexp,''));
	            }
	        });
	
	
	       $("#EMAIL").on('input',function () {
	    	   const regexp = /[^a-zA-Z0-9_@.]/g; 

	           const emailvalue = $(this).val();
	           
	   

	         
	           
	            if (regexp.test(emailvalue)) {
					
	            	
	                alert("영어, 특수문자, 숫자 ( _ , @)만 입력 가능합니다.");

	                $(this).val(emailvalue.replace(regexp, ''));
	                
	            }
	            
	          
	           
	           
	        });
	
		

	 $("#name").on('input',function(event){
			var name = $(this).val();
			
			if(name === ''){
				$('#name').focus();
			  return $('#nameMessage').text('이름(닉네임)을 작성해주세요.');
			};
			
			$.ajax({
				url : '/member/checkName',
				type : 'POST',
				<!--Input 아이디는 첫번째 name , 두번째 name 은 변수명 -->
				data : {name : name},
				success : function(response){
					if(response === 'possible'){
						$('#nameuplication').val('true');
						$('#nameMessage').text('사용가능한 이름(닉네임)입니다.');
					}
					else{
						$('#nameuplication').val('false');
						$('#nameMessage').text('이미 사용중인 이름(닉네임) 입니다.');
					}
				}
			});
		 });
		
	 $('#checkEmail').on('click',function(e){
			const email = $('#EMAIL').val();
			const validateCheck = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.(com|net|org|edu|gov|mil|co\.kr|or\.kr|go\.kr|ac\.kr)$/i;
			
			//
			//if(email === ''){
			  //return $('#emailMessage').text('이메일을 입력해주세요.');
			//} 
			
			
			if(!validateCheck.test(email)){
				alert('올바른 이메일 형식이 아닙니다.');
	            $('#EMAIL').focus();
	            return false;
			}
			
			$.ajax({
				url : '/member/checkMail',
				type : 'POST',
				data : { email : email },
				success : function(response){
				  if(response === 'possible'){
					  alert("사용가능한 이메일입니다.");
					  $('#emailduplication').val('true');
					  emailVaule2 = email;
					  console.log("emailVaule2의 값은?????" + emailVaule2);
					 
				  }
				  else{
					  alert("중복된 이메일입니다.");
					  $('#emailduplication').val('false');
				  }
				}
			});
		});
	 	
	 // 'blur' 안되면 'input' 써보자.
	 // 공백 불가능하게 하기.
	 $('#PASSWORD').on('change',function(){
		const password1 = $('#PASSWORD').val();
		
		 console.log("password1은"+password1);
		 
		validateCheck = /^(?!.*\s)(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,15}$/


		
		if(validateCheck.test(password1)){
			alert("사용 가능한 비밀번호입니다.");
			
		}
		else{
			alert("비밀번호는 특수문자,숫자,문자를 포함한 최소 8~15자리만 가능합니다.");	
			  $('#PASSWORD').val('');
			$('#PASSWORD').focus();
		}
	 });

	 $('#PASSWORD , #PASSWORD2').on('change',function(){
		const password1 = $('#PASSWORD').val();
		const password2 = $('#PASSWORD2').val();
		if(password1 != '' && password2 != ''){
			if(password1 !== password2){
				alert("비밀번호를 다시 확인하여 주세요.");
				$('#PASSWORD2').val('');
				$('#PASSWORD2').focus();
			}
			else {
				alert("비밀번호 확인이 완료 되었습니다.");
			}			
		}
	 });
	 
	 
	 $('#TEL').on('input',function(event){
		 
		 const  regexp =  /[^0123456789-]/g;
         const  nameval = $(this).val();
         const key = event.charCode || event.keyCode || 0; 
         $text = $(this);
           if (regexp.test(nameval)) {
               $(this).val(nameval.replace(regexp, ''));
           } 
           
           if(key !== 8 && key !== 9){
        	   if($text.val().length === 3){
        		   $text.val($text.val() + '-');
        	   }
        	   if($text.val().length === 8){
        		   $text.val($text.val() + '-');
        	   }
           }
           return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
           
	 });
	 
	 	
 	
	 
	 $('#TEL').on('change',function(){
		const TEL = $('#TEL').val();
		console.log("TEL은1" + TEL);
		const validateCheck = /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/;
		
		
		
		if(!validateCheck.test(TEL)){
			alert('잘못된 전화번호양식입니다. 하이폰을 포함한 010-xxxx-xxxx로 입력바랍니다. ');
			$('#TEL').val('');
			$('#TEL').focus();
		}
		
	 });
	 
	 
 	  $('#sample6_postcode').on('input',function(event){
		 
 		 const regexp =  /^[0-9]+$/;
		 const postcodeval = $(this).val();
		 
		   if (!regexp.test(postcodeval)) {
           	
               alert("우편번호는 숫자입력만 가능합니다.");
               
              $('#sample6_postcode').val('');
            
           }
		  
	 });
 	  
 	
	 
	 function validateName(name) {
		 
		   return /^[a-zA-Z0-9]{1,10}$/.test(name);
		}

		function validateEmail(email) {
		    return /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.(com|net|org|edu|gov|mil|co\.kr|or\.kr|go\.kr|ac\.kr)$/i.test(email);
		}

		function validatePassword(password) {
			
		    return /^(?!.*\s)(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,15}$/.test(password);
		}

		function validateTel(tel) {
		    return /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/.test(tel);
		}	
	 
		function validatePostcode(postcode){
			return /^[0-9]*$/.test(postcode);
		}
		
	 
	 $('#memberForm').on('submit',function(e){
		 const namecheck = $('#name').val();
		 const emailcheck = $('#EMAIL').val();
		 const pwcheck = $('#PASSWORD').val();
		 const pwcheck2 = $('#PASSWORD2').val();
		 const telcheck = $('#TEL').val();
		 const addrcheck = $('#sample6_address').val();
		// const detailcheck = $('#sample6_detailAddress').val();
		 const stnumcheck = $('#sample6_postcode').val();
		 const emailDuplication = $('#emailduplication').val();
		 console.log('이메일 중복확인여부 테스트' + emailduplication);
		 const nameduplication = $('#nameduplication').val();
		 const postcodecheck = $('#sample6_postcode').val();
		 
		
		 
	
		
		 if (!namecheck || !emailcheck || !pwcheck || !pwcheck2 || !telcheck || !addrcheck || !stnumcheck ){
			 alert("모든 필드를 확인해주세요.");
			 return false;
		 }
		 if (!validateName(namecheck)) {
		        alert('이름은 영문과 숫자로 1~10자 이내로 입력해주세요.');
		        console.log(namecheck);
		        $('#name').focus();
		        return false;
		    }
		 
		 $.ajax({
		        url: '/member/checkName',
		        type: 'POST',
		        data: {name: namecheck}, 
		        async: false, //동기적으로 요청하고싶을때 반드시 해야한다 
		        success: function(response) {
		            if (response !== 'possible') {
		            	$('#nameMessage').text('이미 사용중인 이름(닉네임) 입니다.');
		            	 $('#nameduplication').val('false');
		                
		            } else {
		            	 
		                $('#nameduplication').val('true');
		      
		            }
		        }
		    });
		 if ($('#nameduplication').val() === 'false') {
		        $('#nameMessage').text('이미 사용중인 이름(닉네임) 입니다.');
		        return false;
		    }
		 
		 if (!validateEmail(emailcheck)) {
		        alert('올바른 이메일 형식이 아닙니다.');
		        $('#EMAIL').focus();
		        return false;
		    }
		 if(emailVaule2 !== emailcheck){
			 console.log("원본이메일과 새로운 이메일 체크" +emailVaule2 + emailcheck );
			 alert("이메일 중복체크를 다시 확인하여주세요");
			 $('#emailduplication').val('false');
			 return false;
		 }	
		 
		 $.ajax({
				url : '/member/checkMail',
				type : 'POST',
				data : {email : emailcheck},
				async: false, 
				success : function(response){
					
					if(response === 'possible'){
						$('#emailduplication').val('true');
						
					}
					else{
						
						$('#emailduplication').val('false');
						alert("이메일 중복확인을 다시 해주세요.");
						  $('#EMAIL').focus();
						  return;
					}
				}
			});		
		 
		 	//중복검사니까, 당연히 true가 나온다 .
		 	console.log("ajax 거치고 난 후의 플래그 상태" + emailDuplication);
		 	if ($('#emailduplication').val() === 'false') {
		       	  alert("이메일 중복확인은 필수입니다.");
		        return false;
		    }
		 
		 if (!validatePassword(pwcheck)) {
				alert("비밀번호는 특수문자,숫자,문자를 포함한 최소 8~15자리만 가능합니다.");	
		        $('#PASSWORD').focus();
		        return false;
		    }
		 if (pwcheck !== pwcheck2) {
		        alert('비밀번호와 비밀번호 확인란이 일치하지 않습니다.');
		        $('#PASSWORD2').focus();
		        return false;
		    }
		 if (!validateTel(telcheck)) {
				alert('잘못된 전화번호양식입니다. 하이폰을 포함한 010-xxxx-xxxx로 입력바랍니다. ');
		        $('#TEL').focus();
		        return false;
		    }
		 if (!validatePostcode(postcodecheck)){
			    alert("우편번호는 숫자만 가능합니다");
			    $('#sample6_postcode').focus();
			 	
		     	 return false;
		 }
		  if (confirm("회원가입을 하시겠습니까?")) {
		        alert('회원가입이 완료되었습니다.');
		        window.location.href= "/member/login";
		        this.submit();
		    } else {
		    	alert("취소되었습니다.");
		        return false;
		    }	 
	 });
	 
});

		
		
			
			
			
			

</script>

</html>


