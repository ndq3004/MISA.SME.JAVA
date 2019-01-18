var fakeData = [];
var getPage = function() {

	// code ajax
	fakeData = [];

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
			// var data2 = JSON.parse(result);
			// console.log(data2);
			var payment = result;

			for (var i = 0; i < size; i++) {
				if (payment[i] == null)
					break;
				fakeData.push({
					PostedDate : payment[i].postedDate,
					RefDate : payment[i].createdDate,
					RefNo : payment[i].refID,
					JournalMemo : payment[i].reason.journalMemo,
					RefTypeName : payment[i].ref.refType,
					TotalAmount : payment[i].totalAmount,
					AccountObjectName : payment[i].accountObject,
					ReasonTypeName : payment[i].reason.journalMemo,
					CashBookPostedDate : payment[i].createdDate,
					RefNoFiance : payment[i].refNoFinance,
					DepartmentName : payment[i].accountObject,
				})
			}
			raeJS.buildDataIntoTable(fakeData);
			var endRecord=0;
			var startRecord=size*(page-1)+1;
			if(size*(page)>totalRecord){
				endRecord=totalRecord;
			}else endRecord=size*(page);

			$('#startRecord').html(startRecord);
			$('#endRecord').html(endRecord);
			
			
			$('#tblCustomerList').on('click', '#tbodyRAE tr',
					raeJS.rowRAE_OnClick);

			$('#tblCustomerList').on('dbclick', '#tbodyRAE tr',
					raeJS.btnAdd_OnClick);

			$('#btnAdd').click(raeJS.btnAdd_OnClick);
		}
		
	})
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
			// result = JSON.parse(result);
			// console.log(data2);
			var payment = result.data;
			var totalRecord=result.totalRecord;
			console.log(totalPage);
			for (var i = 0; i < size; i++) {
				if (payment[i] == null)
					break;

				fakeData.push({
					PostedDate : payment[i].postedDate,
					RefDate : payment[i].createdDate,
					RefNo : payment[i].refID,
					JournalMemo : payment[i].reason.journalMemo,
					RefTypeName : payment[i].ref.refType,
					TotalAmount : payment[i].totalAmount,
					AccountObjectName : payment[i].accountObject,
					ReasonTypeName : payment[i].reason.journalMemo,
					CashBookPostedDate : payment[i].createdDate,
					RefNoFiance : payment[i].refNoFinance,
					DepartmentName : payment[i].accountObject,
				})
			}
			
		
			raeJS.buildDataIntoTable(fakeData);
			
			$("#totalRecord").html(totalRecord);
			var tutalPage=0;
			var endRecord=0;
			var startRecord=1;
			if (totalRecord == 0) {
				totalPage=1;
				startRecord=0;
				}
			else if (totalRecord % size == 0) {
					totalPage = totalRecord/size;
					}
				else {
					totalPage = totalRecord/size + 1;
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

			$('#tblCustomerList').on('dbclick', '#tbodyRAE tr',
					raeJS.btnAdd_OnClick);

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

})
// load chi tiet tren tung cot
$('#currentPage').keyup(function(event) {
	event.preventDefault();
	// Number 13 is the "Enter" key on the keyboard
	if (event.keyCode === 13) {
		// Trigger the button element with a click
		getPage();
	}

})

var raeJS = Object.create({
	// Form chi tiết:
	dialogDetail : $("#frmRAEDetail").dialog({
		autoOpen : false,
		width : 800,
		modal : true,
		// buttons: {
		// "Cất": function () {

		// },
		// "Hủy bỏ": function () {
		// raeJS.dialogDetail.dialog("close");
		// }
		// },
		open : function() {
		//	 Thực hiện binding các thông tin:
			 var fakeData = {
			 PostedDate: '21/04/2018',
			 RefDate: '21/04/2018',
			 RefNo: 'UNC00013' + i,
			 JournalMemo: 'Trả lương nhân viên tháng 4 năm 2018',
			 RefTypeName: 'Chi tiền lương cho Nhân viên',
			 TotalAmount: '245041092',
			 AccountObjectName: 'Công ty TNHH Phú Thái',
			 ReasonTypeName: 'Trả lương nhân viên',
			 CashBookPostedDate: '21/04/2018',
			 RefNoFiance: 'CT00001' + i,
			 DepartmentName: 'Công ty Cổ Phần MISA',
			 };
			 var detail = {
			 JournalMemo: "Trả lương cho nhân viên",
			 CreditAmount: '3221',
			 DebitAmount: 1112,
			 TotalAmount: "1243.456.700",
			 AccountObject: "223768569",
			 AccountObjectName: "Công ty Cổ Phần MISA",
			 DepartmentName: "Công ty Cổ Phần MISA",
			 StatisCode: "",
			 }

		},
		close : function() {
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
			htmlItem.push('<tr dataRow class="{0}" dataRefID="{1}">'.format(
					index % 2 === 0 ? '' : 'row-highlight', item["RefNo"]));
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

		console.log(this);

		var refID = this.getAttribute("dataRefID");
		$.ajax({
			method : "GET",
			url : "http://localhost:3000//getInvoiceDetail:" + refID,
			beforeSend : function(xhr) {
				xhr.setRequestHeader('authorization', localStorage
						.getItem("authenCookie"));
			},
			success : function(result, txtStatus) {
				console.log(result);
			

				result.forEach(function(dataDetail) {
					console.log(dataDetail);
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
					
				})
		
				
				
				var tbody = $('#tbodyRAEDetail');
				console.log(tbody)
				tbody.html('');

				// Lấy thông tin các cột dữ liệu:
				var column = $('table#tblRAEDetail .gridHeader th');
				var rowTemplate = [];
				var fieldData = [];
				rowTemplate.push('<tr class="{0}">');
				column.each(function(index, item) {
					fieldData.push($(item).attr('fieldData'));
				})
				console.log("Chay ko");
				console.log(RAEDetail);
				RAEDetail.forEach(function(item, index) {
					
					var htmlItem = [];
					htmlItem.push('<tr class="{0}">'.format(index % 2 === 0 ? ''
							: 'row-highlight'));

					fieldData.forEach(function(valueField, indexField) {
					console.log(item[valueField]);
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
debugger;
		// Buid dữ liệu Detail:
	
		setTimeout(function() {
			commonJS.hideMask($('.frmCustomerDetail .rae-detail-box'));
		}, 500)
	},

	// Nhấn Button thêm:
	btnAdd_OnClick : function() {
		raeJS.dialogDetail.dialog('open');
	}
})
