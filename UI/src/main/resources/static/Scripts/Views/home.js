/**
 * 
 */

$(document).ready(function(){
	if(localStorage.getItem("authenCookie") != ""){
		$.ajax({
			method: "GET",
			url:"http://localhost:9090/api/home",
			beforeSend: function(xhr) {
			      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
			},
			success: function(data, status, xhr){
				console.log("status");
				console.log(xhr);
				//console.log(status);
				//console.log("xhr" + xhr);
				$('#view-username').css("display","inline-flex");
				$('#username').text((data.email).split("@")[0]);
			},
			error: function(err, stt, xhr){
				console.log("err");
				console.log(err);
				console.log("stt");
				console.log(stt);
				console.log("xhr");
				console.log(xhr);
			}
		})	
	}
	else{
		window.location.href="http://localhost:8080/login";
	}
	
})

$('#logout-btn').click(function(){
	localStorage.setItem("authenCookie","");
	window.location.href="http://localhost:8080/login";
})

$('#dashboard').click(function(){
	window.location.href="/rae";
})