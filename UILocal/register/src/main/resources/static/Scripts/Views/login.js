﻿alert(localStorage.getItem("authenCookie"));
$(document).ready(function(){
	if(localStorage.getItem("authenCookie") != ""){
		$.ajax({
			method:"GET",
			url: "http:/localhost:9090/api/home",
			beforeSend: function(xhr) {
			      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
			},
			success: function(data, txtStatus){
				console.log(data.status + data.email);
				if(txtStatus == "success") {
					//window.location.href="/home";
					 $.ajax({
						 method: "GET",
						 url:"http://localhost:8080/home",
						 success: function(res){
							 $('body').html("");
							 $('body').html(res);
							 console.log(data);
						 }
					 })
				}
			}
		})	
	}
})
	
	//if there no token
	$('#btnLogin').on('click', function(){
		var jsonObj = {email:$('#email').val(), password: $('#password').val()};
    	$.ajax({
			method:"POST",
			url:"http://localhost:9090/api/login",
			contentType: "application/json",
			data: JSON.stringify(jsonObj),
			success: function(data, txtStatus, xhr){
				alert(data.token);
				if(txtStatus=="success") 
					{
						localStorage.setItem("authenCookie", data.token);
						alert(localStorage.getItem("authenCookie"));
						$.ajax({
							method:"GET",
							url: "http://localhost:9090/api/home",
							beforeSend: function(xhr) {
						      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
						    }
						})
						.done(function(data, txtStatus,xhr){
							 if(txtStatus == "success") {
								 //window.location.href="http://localhost:8080/home";
								 $.ajax({
									 method: "GET",
									 url:"http://localhost:8080/home",
									 success: function(res){
										 $('body').html("");
										 $('body').html(res);
										 
									 }
								 })
							 }
						})
					}
				
			},
			error: function(){
				
			}
		
	})
	})


