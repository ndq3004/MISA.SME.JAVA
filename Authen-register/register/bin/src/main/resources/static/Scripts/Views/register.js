alert($('#txtContactMobile').val());
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
    	alert($('#txtContactMobile').val());
//    	if(isNotEmpty())
//    	{
//    		alert("name");
//    		$.ajax({
//    			method:"post",
//    			url: "/register",
//    			data: {contactMobile: $('#txtContactMobile').val(), contactEmail: $('#txtContactEmail').val(), password: $('#txtPassword').val()}
//    		})
//    	}
    },
    doRegister: function () {

    }
})
//function isNotEmpty(){
//	if($('#txtContactMobile').val()==='' || $('#txtContactEmail').val()==="" || $('#txtPassword').val()==="" || $('#txtRePassword').val()==="")
//	{
//		alert("vui long nhap day du du lieu!" )
//		return false;
//	}
//	else{
//		if(!($('#txtPassword').val() === $('#txtRePassword').val())) return false; 
//	}
//	return true;
//}