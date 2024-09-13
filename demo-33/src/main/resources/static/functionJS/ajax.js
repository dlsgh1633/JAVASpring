
const ajax = {
	get: (url, data) => {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: url,
				type: 'GET',
				data: data,
				success: function(response) {
					resolve(response); // 요청이 성공하면 resolve 호출
				},
				error: function(request, error, status) {
					reject(new Error("오류가 발생했습니다.")); // 요청이 실패하면 reject 호출
				}
			});
		});
	},
	post: (url, data) => {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: url,
				type: 'POST',
				data: data,
				success: function(response) {
					resolve(response); // 요청이 성공하면 resolve 호출
				},
				error: function(request, error, status) {
					reject(new Error("오류가 발생했습니다.")); // 요청이 실패하면 reject 호출
				}
			});
		});
	},
	asyncpost: (url, data, callback) => {
		$.ajax({
			url: url,
			type: 'POST',
			data: data,
			async: false,
			success: function(response) {
				callback(response);
			},
			error: function(error, status, request) {
				alertMessage('오류가 발생했습니다');
			}
		});
	},
	filepost: (url, data) => {
		console.log(data);
		return new Promise((resolve, reject) => {
			$.ajax({
				url: url,
				type: 'POST',
				data: data,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				success: function(response) {
					resolve(response); // 요청이 성공하면 resolve 호출
				},
				error: function(request, error, status) {
					reject(new Error("오류가 발생했습니다.")); // 요청이 실패하면 reject 호출
				}
			});
		});
	}
}