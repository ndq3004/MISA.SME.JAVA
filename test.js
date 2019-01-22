//test
	$.ajax({
		method: "get",
		url: "http://localhost:9090/test",
		success: function(data, txtStatus, xhr){
			console.log("status:" + xhr.status);

			if(xhr.status == 200){
				noticeAlert(0, "Thêm công ty thành công!");
			}
		},
		error: function(data, txtStatus, xhr){
			console.log("data:" + data.status);
			if(data.status == 406){
				noticeAlert(2, "Cá nhân đã làm cho công ty này!");
			}
			if(data.status == 502){
				noticeAlert(1, "xảy ra lỗi khi thêm chứng từ!")
			}
		}
	})


// $(document).ready(function(){
// 	$('#statusField').css("display","block");
// 	setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);
// })

//Get company user work for

$.ajax({
	method: "get",
	url: "http://localhost:9090/getCompanyUser",
	beforeSend: function(xhr){
		xhr.setRequestHeader("authorization", localStorage.getItem("cookieAuthen"));
	},
	success: function(data, txtStatus, xhr){
		
	},
	error: function(){
		
	}
})


//lúc ấn vào button thêm thì nó sẽ gửi về server. Post request create company
$('#add-btn').click(function(){
	var comData = {"address":$('#company-addr').val()};
	$.ajax({
		method: "post",
		url: "http://localhost:9090/addCompany",
		contentType:"application/json",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("authorization", localStorage.getItem("cookieAuthen"));
		},
		data: comData,
		success: function(data, txtStatus, xhr){
			if(xhr.status == 200){
				nocticeAlert(0);
				//append company

			}
		},
		error: function(){
			if(data.status == 406){
				noticeAlert(2, "Cá nhân đã làm cho công ty này!");
			}
			if(data.status == 400){
				noticeAlert(1, "xảy ra lỗi khi thêm chứng từ!")
			}
		}
	})
})


function noticeAlert(stt, mes){
	if(stt == 0) {
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","green");
		$('#statusField').html(mes);
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);
	}
	else if(stt == 1){
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","red");
		$('#statusField').html("Thêm công ty không thành công!" + "<br>" + " Vui lòng thử lại");
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);	
	}
	else if(stt == 2){
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","red");
		$('#statusField').html(mes);
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);	
	}

}