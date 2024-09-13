const validate = {
	regex: {
		name: /[^a-zA-Z0-9]/g,  // 이름: 영문자와 숫자, 1~10자
		name2: /^[a-zA-Z0-9]{1,10}$/,
		email: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.(com|net|org|edu|gov|mil|co\.kr|or\.kr|go\.kr|ac\.kr)$/i, // 이메일
		password: /^(?!.*\s)(?=.*[0-9])(?=.*[#?!@$%^&*-]).{8,15}$/, // 비밀번호: 8~15자, 숫자, 특수문자 포함
		telhyphen: /[^0123456789-]/g,  // 전화번호: 010-XXXX-XXXX 형식
		tel: /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/,
		postcode: /^[0-9]*$/,  // 우편번호: 숫자만 허용
		emailInput: /[^a-zA-Z0-9_@.]/g
	},
	validateInput: function(selector, event, regex, message) {
		$(selector).on(event, function() {
			const value = $(selector).val();
			if (regex.test(value)) {
				alertMessage(message);
				$(selector).val(value.replace(regex, ''));
			}
		});
	},
	oppositeInput: function(selector, event, regex, message, message2) {
		$(selector).on(event, function() {
			const value = $(selector).val();
			if (!regex.test(value)) {
				alertMessage(message);
				$(selector).val('');
				$(selector).focus();
			}
			else {
				if (message2 != null)
					alertMessage(message2);
			}
		});
	},
	autoHyphen: function(selector, event) {
		$(selector).on(event, function() {
			const key = event.charCode || event.keyCode || 0;
			$text = $(this);

			if (key !== 8 && key !== 9) {
				if ($text.val().length === 3) {
					$text.val($text.val() + '-');
				}
				if ($text.val().length === 8) {
					$text.val($text.val() + '-');
				}
			}
			return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
		});
	}
}

function checkValidateInput(selector, regex, message) {
	const value = $(selector).val();
	if (!regex.test(value)) {
		alertMessage(message);
		$(selector).focus();
	}
	return regex.test(value);
}

function truecheckValidateInput(selector, regex, message) {
	const value = $(selector).val();
	if (regex.test(value)) {
		alertMessage(message);
		$(selector).focus();
	}
	return regex.test(value);
}

