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
//	$.ajax({
//		method:"GET",
//		url: "/api/home",
//		headers:{
//			"cookie": localStorage.getItem("authenCookie")
//		}
//	})
//	.done(function(data){
//		alert(data);
//	})
	
	
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
							headers:{
								
								'cookie':'content-Type=application/json; Accept=/; Cache-Control=no-cache;authorization='+ 
								localStorage.getItem("authenCookie") + 
								'; path=/api/login; domain=localhost; HttpOnly; Expires=Tue, 19 Jan 2038 03:14:07 GMT;'
							}
//							beforeSend: function(xhr) {
//								xhr.setRequestHeader('content-Type', 'application/json');
//								xhr.setRequestHeader("Accept", "/")
//								xhr.setRequestHeader("Cache-Control", "no-cache")
//						      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
//						      xhr.setRequestHeader('path','/api/login');
//						      xhr.setRequestHeader('domain', 'localhost');
//						      xhr.setRequestHeader('Expires', 'Tue, 19 Jan 2038 03:14:07 GMT');
//						      
//
//						    }
						})
						.done(function(data, txtStatus){
							alert(data.status);
						})
					}
				
			},
			error: function(){
				
			}
		
	})
	})
})

//function setAuthen(t_name, )