
	$.ajax({
		method: "get",
		url: "http://localhost:9090/test",
		success: function(data, txtStatus, xhr){
			console.log("data:" + data);
			console.log("txtStatus:" + txtStatus);
			console.log("xhr:" + xhr);
			console.log("status:" + xhr.Status);

			if(xhr.Status == 200){
				nocticeAlert(0);
			}
		},
		error: function(data, txtStatus,xhr){
			console.log("data:" + data);
			console.log("txtStatus:" + txtStatus);
			console.log("xhr:" + xhr);
			console.log("status:" + xhr.Status);
			if(xhr.Status == 406){
				noticeAlert(2, "Cá nhân đã làm cho công ty này!");
			}
			if(xhr.Status == 200){
				noticeAlert(1, "xảy ra lỗi khi thêm chứng từ!")
			}
		}
	})


// $(document).ready(function(){
// 	$('#statusField').css("display","block");
// 	setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);
// })

//lúc ấn vào button thêm thì nó sẽ gửi về server. 
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
			if(xhr.Status == 200){
				nocticeAlert(0);
			}
		},
		error: function(){
			if(xhr.Status == 406){
				noticeAlert(2, "Cá nhân đã làm cho công ty này!");
			}
			if(xhr.Status == 200){
				noticeAlert(1, "xảy ra lỗi khi thêm chứng từ!")
			}
		}
	})
})


function noticeAlert(stt, mes){
	if(stt == 0) {
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","green");
		$('#statusField').text("Thêm công ty thành công!");
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