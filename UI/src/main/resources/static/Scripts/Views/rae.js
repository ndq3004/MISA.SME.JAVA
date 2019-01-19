////get name
$(document).ready(function(){
	if(localStorage.getItem("authenCookie") != "" && localStorage.getItem("authenCookie") != null){
		$.ajax({
			method: "GET",
			url: "http://localhost:9090" + "/api/home",
			beforeSend: function(xhr) {
			      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
			},
			success: function(data, status, xhr){
				$('#user-info').text(data.email);
			},
			error: function(err, stt, xhr){
				window.location.href="http://localhost:8080" + "/login";			
			}
		})	
	}
	else{
		window.location.href="http://localhost:8080" + "/login";
	}
	
})

$('#tblCustomerList').on('click', '#tbodyRAE tr',function(){
	localStorage.setItem("idFind", $(this).find("td:eq(2)").text());
	console.log(localStorage.getItem("idFind"));
});
$('#btnDelete').click(function(){
	var id = localStorage.getItem("idFind");
	$.ajax({
		method:"post",
		url: "http://localhost:3000/deleteRef",
		beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', localStorage
					.getItem("authenCookie"));
		},
		data: {"refID": id},
		success: function(){
			alert("da xoa thanh cong");
		}
	})
	
})
function convertDate(date){
date= new Date(date);	
day = date.getDate();
  month = date.getMonth() + 1;
  year = date.getFullYear();
  return day+"/"+month+"/"+year;
};

var fakeData = [];
var dataRAE=[];
var totalRecord=0;
var totalPage=0;
var getPage = function() {
	 totalPage=0;
	// code ajax
	fakeData = [];
	dataRAE=[];
	var page = $('#currentPage').val();
	var size = $('#inputTotalRecord').val();
	$.ajax({
		method : "GET",
		url : "http://localhost:3000/getPage/page:" + page + "_size:" + size,
		beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', localStorage
					.getItem("authenCookie"));
		},
		success : function(result, txtStatus) {
			
			var payment = result;
			
			
			for (var i = 0; i < size; i++) {
				if (payment[i] == null)
				break;
				dataRAE.push(payment[i]);
				fakeData.push({
					ID:payment[i].refID,
					PostedDate :convertDate(payment[i].postedDate),
					RefDate :convertDate(payment[i].createdDate),
					RefNo : payment[i].refNoFinance,
					JournalMemo : payment[i].reason.journalMemo,
					RefTypeName : payment[i].ref.refType,
					TotalAmount : payment[i].totalAmount,
					AccountObjectName : payment[i].accountObject,
					ReasonTypeName : payment[i].reason.journalMemo,
					CashBookPostedDate :convertDate(payment[i].createdDate),
					RefNoFiance : payment[i].refNoFinance,
					DepartmentName : payment[i].accountObject,
				})
			}
			raeJS.buildDataIntoTable(fakeData);
			var endRecord=0;
			var startRecord=size*(page-1)+1;
			if(size*page>=totalRecord){
				endRecord=totalRecord;
			}else endRecord=size*(page);

			$('#startRecord').html(startRecord);
			$('#endRecord').html(endRecord);
			
			
			$('#tblCustomerList').on('click', '#tbodyRAE tr',
					raeJS.rowRAE_OnClick);

			$('#tblCustomerList').on('dblclick', '#tbodyRAE tr',
					raeJS.btnAdd_OnClick);

			$('#btnAdd').click(raeJS.btnAdd_OnClick);
		}
		
	})
}


function loadDataPopup(){
	var trHTML=$('.rowSelected').attr("indexRef");
	var index=parseInt(trHTML);
	var ref=dataRAE[index];
	raeJS.dialogDetail.dialog('open');
	$("input[PopUpField='refID']").val(ref.refID);
	$("input[PopUpField='objectID']").val(ref.accountObjectID);
	$("input[PopUpField='objectName']").val(ref.accountObject);
	$("input[PopUpField='address']").val("");
	$("input[PopUpField='accountTo1']").val("");
	$("input[PopUpField='accountTo2']").val("");
	$("input[PopUpField='reasonTypeID']").val(ref.reason.journalMemo);
	$("input[PopUpField='journalMeno']").val(ref.reason.journalMemo);
	$("input[PopUpField='StaffName']").val("Misa");
	$("input[PopUpField='dateRecord']").val(ref.postedDate);
	$("input[PopUpField='dateFinance']").val(ref.createdDate);
	$("input[PopUpField='financeNo']").val(ref.refNoFinance);	
	var result=[];
		result=JSON.parse(localStorage.getItem("detailRef"));
	var raeDetail=[];
		result.forEach(function(dataDetail) {
			 				var detail = {
	
				JournalMemo : "Trả lương cho nhân viên",
				CreditAmount : "TK No",
				DebitAmount : "TK co",
				TotalAmount : dataDetail.amount,
				AccountObject : dataDetail.account_object_id,
				AccountObjectName : dataDetail.account_object_id,
				DepartmentName : dataDetail.account_object_id,
				StatisCode : "",			
			}
			raeDetail.push(detail);
		})
	var tbody = $('#tbodyRAEDetail-popup');
	
	tbody.html('');

	// Lấy thông tin các cột dữ liệu:
	var column = $('table#tblDetail-popup #gridHeader_popup th');
	var rowTemplate = [];
	var fieldData = [];
	rowTemplate.push('<tr class="{0}">');
	column.each(function(index, item) {
		fieldData.push($(item).attr('fieldData'));
	})

	raeDetail.forEach(function(item, index) {
		
		var htmlItem = [];
		htmlItem.push('<tr class="{0}">'.format(''));

		fieldData.forEach(function(valueField, indexField) {
			if (indexField === 0) {
				htmlItem.push('<td class="no-border-left" >{0}</td>'
						.format(item[valueField]));
			} else {
				htmlItem.push('<td>{0}</td>'.format(item[valueField]));
			}
		})
		htmlItem.push('</tr>');
		tbody.append(htmlItem.join(""));
	});
	

}


var getPageHome = function() {
	// code ajax
	fakeData = [];
	var page = $('#currentPage').val();
	var size = $('#inputTotalRecord').val();
	$.ajax({
		method : "GET",
		url : "http://localhost:3000/getAllPage_Size:" + size,
		beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', localStorage
					.getItem("authenCookie"));
		},
		success : function(result, txtStatus) {
			
			var payment = result.data;
			 totalRecord=result.totalRecord;
			for (var i = 0; i < size; i++) {
				if (payment[i] == null)
					break;
				dataRAE.push(payment[i]);
				fakeData.push({
					ID:payment[i].refID,
					PostedDate :convertDate(payment[i].postedDate),
					RefDate :convertDate(payment[i].createdDate),
					RefNo : payment[i].refNoFinance,
					JournalMemo : payment[i].reason.journalMemo,
					RefTypeName : payment[i].ref.refType,
					TotalAmount : payment[i].totalAmount,
					AccountObjectName : payment[i].accountObject,
					ReasonTypeName : payment[i].reason.journalMemo,
					CashBookPostedDate :convertDate(payment[i].createdDate),
					RefNoFiance : payment[i].refNoFinance,
					DepartmentName : payment[i].accountObject,
				})
			}
			
		
			raeJS.buildDataIntoTable(fakeData);
			
			$("#totalRecord").html(totalRecord);
			
			var endRecord=0;
			var startRecord=1;
			if (totalRecord == 0) {
				totalPage=1;
				startRecord=0;
				}
			else if (totalRecord % size == 0) {
					totalPage = parseInt(totalRecord/size);
					}
				else {
					totalPage = parseInt(totalRecord/size) + 1;
					}
			
			$("#totalPage").html(totalPage);
			
			if(size>totalRecord){
				endRecord=totalRecord;
			}else endRecord=size;

			$('#startRecord').html(startRecord);
			$('#endRecord').html(endRecord);
			$("#totalPage").html(totalPage);
			$('#tblCustomerList').on('click', '#tbodyRAE tr',
					raeJS.rowRAE_OnClick);

			$('#tblCustomerList').on('dblclick', '#tbodyRAE tr',
					
					function(){
				loadDataPopup()
				});

			$('#btnAdd').click(raeJS.btnAdd_OnClick);
		}
	})
}

$(document).ready(function() {
	getPageHome();
	$('#arrow-combo-trigger').click(function(){
        $('#numberRecordSelection').show();
        event.stopPropagation() ;
    })
    $(document).on('click','.record-select-item',function(){
        debugger;
        var number = $(this).html();
       $("#inputTotalRecord").val(number);
       getPageHome();
    })

	$('#currentPage').keyup(function(event) {
		event.preventDefault();
		// Number 13 is the "Enter" key on the keyboard
		if (event.keyCode === 13) {
			// Trigger the button element with a click
			getPage();
		}
	
	})
	$(document).on('click','tr',function(){
        $('tr').removeClass('rowSelected');
        $(this).addClass('rowSelected');
    })
	$('#btnEdit').click(function(){
		
		if($('.rowSelected').html()==null){
			alert("Bạn chưa chọn hàng dữ liệu");
		}else{
			
			//load dữ liệu Ref ra
			loadDataPopup()
		}
	});
})
// load chi tiet tren tung cot

var raeJS = Object.create({
	// Form chi tiết:
	dialogDetail : $("#frmRAEDetail").dialog({
		autoOpen : false,
		width : 800,
		modal : true,
		 buttons: {
		 "Cất": function () {
			 alert("cat");
		 },
		 "Hủy bỏ": function () {
		 raeJS.dialogDetail.dialog("close");
		 }
		 },
		open : function(status) {
		// //	 Thực hiện binding các thông tin:
		},
		close : function() {
			$('.text-required').removeClass('required-border');	
			$('.text-required').next('.box-required-after').remove();
			reset();
			raeJS.dialogDetail.dialog("close");
		}
	}),

	// Load dữ liệu
	loadData : function() {
		// Gọi Service lấy dữ liệu trả về:

		// Build dữ liệu lên table (fakeData sẽ là dữ liệu thật lấy từ Service
		// trả về - kiểu mảng):
		raeJS.buildDataIntoTable(fakeData);
	},

	// Build dữ liệu lên bảng:
	buildDataIntoTable : function(data) {
		var table = $('#tbodyRAE');
		table.html('');
		// Lấy thông tin các cột dữ liệu:
		var column = $('#tblCustomerList .gridHeader th');
		var rowTemplate = [];
		var fieldData = [];
		rowTemplate.push('<tr class="{0}">');
		column.each(function(index, item) {
			fieldData.push($(item).attr('fieldData'));
		})
		data.forEach(function(item, index) {
			var htmlItem = [];
			htmlItem.push('<tr dataRow class="{0}" dataRefID="{1}" indexRef={2}">'.format(
					index % 2 === 0 ? '' : 'row-highlight',item["ID"],index));
			fieldData.forEach(function(valueField, indexField) {
				if (indexField === 0) {
					htmlItem.push('<td class="no-border-left" >{0}</td>'
							.format(item[valueField]));
				} else {
					htmlItem.push('<td>{0}</td>'.format(item[valueField]));
				}
			})
			htmlItem.push('</tr>');
			table.append(htmlItem.join(""));
		});
	},

	// Chọn 1 bản ghi trong danh sách:
	rowRAE_OnClick : function() {
		commonJS.showMask($('.frmCustomerDetail .rae-detail-box'));
		// Lấy Detail từ Service:
		var RAEDetail = [];
		var refID = this.getAttribute("dataRefID");
		
		// Buid dữ liệu Detail:
		$.ajax({
			method : "GET",
			url : "http://localhost:3000//getInvoiceDetail:" + refID,
			beforeSend : function(xhr) {
				xhr.setRequestHeader('authorization', localStorage
						.getItem("authenCookie"));
			},
			success : function(result, txtStatus) {
				localStorage.setItem("detailRef", JSON.stringify(result));
			
				result.forEach(function(dataDetail) {	
					var detail = {
						JournalMemo : "Trả lương cho nhân viên",
						CreditAmount : "TK No",
						DebitAmount : "TK co",
						TotalAmount : dataDetail.amount,
						AccountObject : dataDetail.account_object_id,
						AccountObjectName : dataDetail.account_object_id,
						DepartmentName : dataDetail.account_object_id,
						StatisCode : "",	
					}
					RAEDetail.push(detail);
				});
				var tbody = $('#tbodyRAEDetail');
			
				
				tbody.html('');

				// Lấy thông tin các cột dữ liệu:
				var column = $('table#tblRAEDetail .gridHeader th');
				var rowTemplate = [];
				var fieldData = [];
				rowTemplate.push('<tr class="{0}">');
				column.each(function(index, item) {
					fieldData.push($(item).attr('fieldData'));
				})
			
				RAEDetail.forEach(function(item, index) {
					
					var htmlItem = [];
					htmlItem.push('<tr class="{0}">'.format(index % 2 === 0 ? ''
							: 'row-highlight'));

					fieldData.forEach(function(valueField, indexField) {
					
						if (indexField === 0) {
							htmlItem.push('<td class="no-border-left" >{0}</td>'
									.format(item[valueField]));
						} else {
							htmlItem.push('<td>{0}</td>'.format(item[valueField]));
						}
					})

					htmlItem.push('</tr>');
					tbody.append(htmlItem.join(""));
				});
			}
		})
	
		setTimeout(function() {
			commonJS.hideMask($('.frmCustomerDetail .rae-detail-box'));
		}, 500)
	},

	// Nhấn Button thêm:
	btnAdd_OnClick : function() {
		raeJS.dialogDetail.dialog('open');
	}
})

$('#btnSave-popup').click(function(){
	var dataRef={
			"accountObjectID":$("input[PopUpField='objectID']").val(),
			"accountObject":$("input[PopUpField='objectName']").val(),
			"refNoFinance":$("input[PopUpField='financeNo']").val(),
			"createdDate": new Date($("input[popupfield='dateRecord']").val()),
			"postedDate": new Date($("input[popupfield='dateFinance']").val()),
			"reason":{"reasonTypeID":"45","journalMemo":"ko biet"},
			"ref":{"refTypeID":2, "refType":"know"}
		};
var validationInput={
		"accountObjectID":$("input[PopUpField='objectID']").val(),
		"accountObject":$("input[PopUpField='objectName']").val(),
		"refNoFinance":$("input[PopUpField='financeNo']").val(),
		"createdDate": getTime("input[popupfield='dateRecord']").toString(),
		"postedDate": getTime("input[popupfield='dateFinance']").toString(),
		"reasonTypeID":"45","journalMemo":"ko biet",
		"refTypeID":2,
		"refType":"know"
}
	if(checkProperties(validationInput)) { 
		$.ajax({
			method:"POST",
			url:"http://localhost:3000/addRef",
			contentType: "application/json; charset=utf-8",
			beforeSend : function(xhr) {
				xhr.setRequestHeader('authorization', localStorage.getItem("authenCookie"));
			},
			data: JSON.stringify(dataRef),
			success: function(data){
				console.log(data);
				$('#overlay').show();
				if(data.message === "success!")
				{//alert("thêm chứng từ thành công!");
					$('#overlay').text("thêm chứng từ thành công!")
					$('#overlay').show();
					setTimeout(function(){
						$("#overlay").hide();
					}, 2000);
					
				 	raeJS.dialogDetail.dialog("close");
				 	getPageHome();
				}
				else {
					$('#overlay').text("thêm chứng từ thành công!")
					$('#overlay').show();
					setTimeout(function(){
						$("#overlay").hide();
					}, 2000);
					alert("có lỗi khi thêm chứng từ. Vui lòng kiểm tra nhâp liệu!");
				}
					
			}
		})
	} else {
		for (var key in dataRef) {
	        if (dataRef[key] !== null && dataRef[key] != "") {
	        	$('.text-required').addClass('required-border');
            	$('.text-required').parent().attr('title', "Thông tin này không được để trống");
            	$('.text-required').parent().addClass('wrap-control');
            	var nextElement = $('.text-required').next();
                if (!$(nextElement).hasClass('box-required-after')) {
                	$('.text-required').after('<div class="box-required-after">*</div>');
                } 
	        } else {
                 $(this).removeClass('required-border');
                 $(this).next('.box-required-after').remove();
                 $(this).parent().removeAttr('title');
            }	    
		}
	}
})

$('#btnAdd-popup').click(function(){
	reset();
})



function reset(){
	$("input[PopUpField='refID']").val("");
	$("input[PopUpField='objectID']").val("");
	$("input[PopUpField='objectName']").val("");
	$("input[PopUpField='address']").val("");
	$("input[PopUpField='accountTo1']").val("");
	$("input[PopUpField='accountTo2']").val("");
	$("input[PopUpField='reasonTypeID']").val("");
	$("input[PopUpField='journalMeno']").val("");
	$("input[PopUpField='StaffName']").val("");
	$("input[PopUpField='dateRecord']").val("");
	$("input[PopUpField='dateFinance']").val("");
	$("input[PopUpField='financeNo']").val("");
}
//Get day month year

function getTime(element){
  var date = new Date($(element).val());
  var months;
  var days;
  day = date.getDate();
  if(day < 10) days = "0" + day;
  else days = day;
  month = date.getMonth() + 1;
  if(month < 10) months = "0" + month;
  else months = month; 
  year = date.getFullYear();
  return year + "-" + months + "-" + days;
};



// check dien het du lieu moi cho luu
function checkProperties(obj) {
    for (var key in obj) {
        if (obj[key] == null && obj[key] == "")
            return false;
    }
    return true;
}
function checkTbar(){
	if ($('#currentpage').val() == totalPage) {
		$('.tbar-page-next').addClass('tbar-item-disabled');
		$('.tbar-page-last').addClass('tbar-item-disabled');
		$('.tbar-page-next').parent().removeClass('tbar-item-control-active');
		$('.tbar-page-last').parent().removeClass('tbar-item-control-active');
	}  else {
		$('.tbar-page-next').removeClass('tbar-item-disabled');
		$('.tbar-page-last').removeClass('tbar-item-disabled');
		$('.tbar-page-next').parent().addClass('tbar-item-control-active');
		$('.tbar-page-last').parent().addClass('tbar-item-control-active');
	}
	
	if ($('#currentpage').val() == 1) {
		$('.tbar-page-first').addClass('tbar-item-disabled');
		$('.tbar-page-prev').addClass('tbar-item-disabled');
		$('.tbar-page-first').parent().removeClass('tbar-item-control-active');
		$('.tbar-page-prev').parent().removeClass('tbar-item-control-active');
					
	}  else {
		$('.tbar-page-first').removeClass('tbar-item-disabled');
		$('.tbar-page-prev').removeClass('tbar-item-disabled');
		$('.tbar-page-first').parent().addClass('tbar-item-control-active');
		$('.tbar-page-prev').parent().addClass('tbar-item-control-active');
	}
}
checkTbar();
//click sang trang tiep theo
$('.tbar-page-next').click(function(){
	var recentPage = $('#currentPage').val();
	if(recentPage <= totalPage){
		$('#currentPage').val(Number(recentPage)+Number(1));
		getPage();
		checkTbar();
	}
})

//click ve trang truoc
$('.tbar-page-prev').click(function(){
	var recentPage = $('#currentPage').val();
	if(recentPage > 1){
		$('#currentPage').val(Number(recentPage)-Number(1));
		getPage();
		checkTbar();
	}
})

//click chuyen den trang cuoi cung
$('.tbar-page-last').click(function(){
	if(recentPage < totalPage){
		$('#currentPage').val(Number(totalPage));
		getPage();
		checkTbar();
	}
})

//click chuyen den trang dau tien
$('.tbar-page-first').click(function(){
	if(recentPage > 1){
		$('#currentPage').val(Number(1));
		getPage();
		checkTbar();
	}
})

//click hien hieu ung dong dang chon
$(document).on('click','tr',function(){
    $('tr').removeClass('rowSelected');
    $(this).addClass('rowSelected');
})