
$(document).ready(function () {
    $('#btnRegister').on('click', loginJS.btnRegister_onClick);
})

/**
 * Object JS phục vụ cho trang Login
 */
var loginJS = Object.create({
    /*
     * Hàm xử lý khi nhấn Button Đăng ký
     * Created by: NVMANH (28/12/2018) 
     * */
    btnRegister_onClick: function () {
        // thực hiện validate:

    	if(isNotEmpty())
    	{
    		var jsondata = {contactMobile: $('#txtContactMobile').val(), 
					contactEmail: $('#txtContactEmail').val(), 
					password: $('#txtPassword').val()};
    		$.ajax({
    			method:"POST",
    			url: MISA.Config.loginUrl+"/api/register",
    			contentType:"application/json",
    			data: JSON.stringify(jsondata),   					
    			success: function(data, textStatus, xhr){

    				if(textStatus=="success") {
    					noticeAlert(1,"Đăng kí thành công. Đang để chuyển tới trang đăng nhập!");
    					setTimeout(function(){
    						window.location.href="/login.html";
    					}, 2000)
    					
    				}
    			},
    			error: function(data, txtStatus, xhr){
                    if(data.status == 409){
                    	$('#register-error').text("Tài khoản đã tồn tại!");
                    }    
                    else {
                    	$('#register-error').text("Lỗi đăng kí. Vui lòng thử lại");
                    }
                    	
    			}
    					
    		})
    		
    	}
    },
    doRegister: function () {

    }
})
function isNotEmpty(){
	if($('#txtContactMobile').val()==='' || $('#txtContactEmail').val()==="" || $('#txtPassword').val()==="" || $('#txtRePassword').val()==="")
	{
		$('#register-error').text("vui long nhap day du du lieu!");
		return false;
	}
	else{
		if(!($('#txtPassword').val() === $('#txtRePassword').val())) 
		{
			$('#register-error').text("Password không trùng khớp");
			return false; 
		}
	}
	return true;
}


function noticeAlert(stt, mes){
	//1 = success, 2 = error
	if(stt == 0) {
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","red");
		$('#statusField').text(mes);
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);
	}
	else if(stt == 1){
		$('#statusField').css("display","block");
		$('#statusField').css("background-color","green");
		$('#statusField').html(mes);
		setTimeout(function(){$('#statusField').fadeOut(2000)}, 1000);	
	}


}