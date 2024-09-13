//파일
const file = {
	selectFile: (element) => {
		console.log('func는 ?' + element);
		const file = element.files[0];
		const filename = element.closest('.file_input').firstElementChild;
		let UUID = [];

		if (element.getAttribute('data-uuid') !== null) {
			console.log("있음");
			UUID = element.getAttribute('data-uuid');
			console.log(UUID);
			UUIDs.push(UUID);
		}

		if (!file) {
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
	},
	addFile: () => {
		const fileDiv = document.createElement('div');
		fileDiv.innerHTML = `
	        <div class="file_input">
	            <input type="text" readonly />
	            <label> 첨부파일
	                <input type="file" name="files" onchange="file.selectFile(this);" />
	            </label>
	        </div>
	        <button type="button" onclick="file.removeFile(this);" class="btns del_btn"><span>삭제</span></button>
	    `;
		document.querySelector('.file_list').appendChild(fileDiv);
	},
	removeFile: (element) => {
		const fileAddBtn = element.nextElementSibling;
		if (element.getAttribute('data-uuid') !== null) {
			console.log("있음");
			UUID = element.getAttribute('data-uuid');
			console.log(UUID);
			UUIDs.push(UUID);
		}
		if (fileAddBtn) {
			const inputs = element.previousElementSibling.querySelectorAll('input');
			inputs.forEach(input => input.value = '')
			return false;
		}
		element.parentElement.remove();
	}

}

//문자 수 제한
function len_chk(element, message, count) {

	var frm = document.getElementById(element);

	if (frm.value.length > count) {
		alertMessage(message);
		frm.value = frm.value.substring(0, count);

	}
}

// alert말고 다른것을 사용할때 이 메서드를 수정하면 전체 안내창이 바뀔수있다 ?
function alertMessage(message) {
	alert(message);
}
//간단한 jQuery 사용 .
function jQueryEvent(selector, event, func) {
	$(selector).on(event, function() {
		func(this);
	});
}
// 공용 날짜 함수 yyyy-mm-dd hh:mm:ss 
function dateFunc(data) {
	let today = new Date(data);
	let year = today.getFullYear();
	let month = ('0' + (today.getMonth() + 1)).slice(-2);
	let day = ('0' + today.getDate()).slice(-2);
	let hours = ('0' + today.getHours()).slice(-2);
	let minutes = ('0' + today.getMinutes()).slice(-2);
	let seconds = ('0' + today.getSeconds()).slice(-2);

	return year + '-' + month + '-' + day + ' ' + hours + ":" + minutes + ':' + seconds;
}


// 다음 주소찾기
function daumPost() {
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
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
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



