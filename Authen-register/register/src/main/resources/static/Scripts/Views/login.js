//alert("hahaaaa");
//$(document).ready(function () {
//    $('#btnLogin').on('click', loginJS.btnLogin_onClick);
//})
//
///**
// * Object JS phục vụ cho trang Login
// */
//var loginJS = Object.create({
//    /*
//     * Hàm xử lý khi nhấn Button Đăng ký
//     * Created by: NVMANH (28/12/2018) 
//     * */
//    btnLogin_onClick: function () {
//        // thực hiện validate:
//    	alert("hah");
//    	$.ajax({
//    		method:"post",
//    		url:"/api/login",
//    		data:{email:$('#email').val(), password: $('#password')},
//    		success: function(){
//    			
//    		},
//    		error: function(){
//    			
//    		}
//    		
//    	})
//    	
//    },
//    doLogin: function () {
//
//    }
//})

$(document).ready(function(){
	if(localStorage.getItem("authenCookie") != ""){
		$.ajax({
			method:"GET",
			url: "/api/home",
			headers:{
				"cookie": localStorage.getItem("authenCookie")
			},
			success: function(data, txtStatus){
				alert(data);
				if(txtStatus == "success") window.location.href="/home";
			}
		})	
	}
	
//	})
	
	//if there is a token
//	if(localStorage.setItem("authenCookie", data.token) != ""){
//		$.ajax({
//			method:"GET",
//			url: "/api/home",
//			beforeSend: function(xhr) {
//		      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
//		    }
//		})
//		.done(function(data, txtStatus,xhr){
//			if(txtStatus == "success") window.local.href="/home";
//		})
//	}
	//if there no token
	$('#btnLogin').on('click', function(){
		alert("haha");
		var jsonObj = {email:$('#email').val(), password: $('#password').val()};
    	$.ajax({
			method:"POST",
			url:"/api/login",
			contentType: "application/json",
			dataType: 'json',
			data: JSON.stringify(jsonObj),
			success: function(data, txtStatus, xhr){
				alert(data.token);
				if(txtStatus=="success") 
					{
						localStorage.setItem("authenCookie", data.token);
						alert(localStorage.getItem("authenCookie"));
						$.ajax({
							method:"GET",
							url: "/api/home",
							beforeSend: function(xhr) {
						      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
						    }
						})
						.done(function(data, txtStatus,xhr){
							 if(txtStatus == "success") window.location.href="/home";
						})
					}
				
			},
			error: function(){
				
			}
		
	})
	})
})

