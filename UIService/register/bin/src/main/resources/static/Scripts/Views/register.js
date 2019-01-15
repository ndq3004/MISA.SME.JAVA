alert("haha");
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
    			url: "/register",
    			contentType:"application/json",
    			data: JSON.stringify(jsondata),   					
    			success: function(data, textStatus, xhr){

    				if(textStatus=="success") {
    					alert("đăng kí thành công. Click để chuyển tới trang đăng nhập");
    					window.location.href="/login";
    				}
    			},
    			error: function(data, txtStatus, xhr){
    				switch(data.status){
    					case 409: alert("Tài khoản đã tồn tại!");
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
		alert("vui long nhap day du du lieu!" )
		return false;
	}
	else{
		if(!($('#txtPassword').val() === $('#txtRePassword').val())) return false; 
	}
	return true;
}