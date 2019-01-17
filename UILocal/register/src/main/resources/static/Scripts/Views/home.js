/**
 * 
 */
alert("home");
$(document).ready(function () {
    $('button').on('click', loginJS.btnRegister_onClick);
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
    	alert($('#txtContactMobile').val())
//       $.ajax({
//    	   method: "post",
//    	   url: "/test",
//    	   data: {contactEmail : $('input').val()}
//       })
//       .done(function(msg){
//    	   alert(msg)
    	//})
    },
    doRegister: function () {

    }
})