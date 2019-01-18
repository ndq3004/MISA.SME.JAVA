

$('#currentPage').change(function(){
	var fakeData = []
	//code ajax
	
	var page=$('#currentPage').val();
	var size=$('#inputTotalRecord').val();
	$.ajax({
		method:"GET",
		url: "http://localhost:9000//getPage/page:"+page+"_size:"+size,
		beforeSend: function(xhr) {
		      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
		},
		success: function(result, txtStatus){
			//var data2 = JSON.parse(result);
			//console.log(data2);
			var payment=result;
			console.log(payment);

			for (var i = 0; i < size; i++) {
				if(payment[i] == null) break;
				console.log(payment[i]);
		        fakeData.push({
		            PostedDate: payment[i].postedDate,
		            RefDate: payment[i].createdDate,
		            RefNo: payment[i].refID,
		            JournalMemo: payment[i].reason.journalMemo,
		            RefTypeName: payment[i].ref.refType,
		            TotalAmount: payment[i].totalAmount,
		            AccountObjectName: payment[i].accountObject,
		            ReasonTypeName: payment[i].reason.journalMemo,
		            CashBookPostedDate: payment[i].createdDate,
		            RefNoFiance: payment[i].refNoFinance,
		            DepartmentName: payment[i].accountObject,
		        })
		    }
		    raeJS.buildDataIntoTable(fakeData);
		}
	})
})

$('#inputTotalRecord').change(function(){
	var fakeData = []
	//code ajax
	var size=$('#inputTotalRecord').val();
	$.ajax({
		method:"GET",
		url: "http://localhost:9000/getAllPage_Size:"+size,
		beforeSend: function(xhr) {
		      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
		},
		success: function(result, txtStatus){
			//var data2 = JSON.parse(result);
			//console.log(data2);
			var payment=result.data;
			console.log(payment);

			for (var i = 0; i < size; i++) {
				if(payment[i] == null) break;
				console.log(payment[i]);
		        fakeData.push({
		            PostedDate: payment[i].postedDate,
		            RefDate: payment[i].createdDate,
		            RefNo: payment[i].refID,
		            JournalMemo: payment[i].reason.journalMemo,
		            RefTypeName: payment[i].ref.refType,
		            TotalAmount: payment[i].totalAmount,
		            AccountObjectName: payment[i].accountObject,
		            ReasonTypeName: payment[i].reason.journalMemo,
		            CashBookPostedDate: payment[i].createdDate,
		            RefNoFiance: payment[i].refNoFinance,
		            DepartmentName: payment[i].accountObject,
		        })
		    }
		    raeJS.buildDataIntoTable(fakeData);
		}
	})
})

$(document).ready(function () {
	
	var fakeData = []
	//code ajax
	var size=$('#inputTotalRecord').val();
	console.log(size);
	$.ajax({
		method:"GET",
		url: "http://localhost:9000/getAllPage_Size:"+size,
		beforeSend: function(xhr) {
		      xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
		},
		success: function(result, txtStatus){
			//var data2 = JSON.parse(result);
			//console.log(data2);
			$('#totalRecord').text(result.totalRecord)
			var payment=result.data;
			console.log(payment);

			for (var i = 0; i < size; i++) {
				if(payment[i] == null) break;
				console.log(payment[i]);
		        fakeData.push({
		            PostedDate: payment[i].postedDate,
		            RefDate: payment[i].createdDate,
		            RefNo: payment[i].refID,
		            JournalMemo: payment[i].reason.journalMemo,
		            RefTypeName: payment[i].ref.refType,
		            TotalAmount: payment[i].totalAmount,
		            AccountObjectName: payment[i].accountObject,
		            ReasonTypeName: payment[i].reason.journalMemo,
		            CashBookPostedDate: payment[i].createdDate,
		            RefNoFiance: payment[i].refNoFinance,
		            DepartmentName: payment[i].accountObject,
		        })
		    }
		    raeJS.buildDataIntoTable(fakeData);
		}
	})	
    
//    for (var i = 0; i < 20; i++) {
//        fakeData.push({
//            PostedDate: '',
//            RefDate: '11',
//            RefNo: 'UNC00013'+i,
//            JournalMemo: 'Trả lương nhân viên tháng 4 năm 2018',
//            RefTypeName: 'Chi tiền lương cho Nhân viên',
//            TotalAmount: '245041092',
//            AccountObjectName: 'Công ty TNHH Phú Thái',
//            ReasonTypeName: 'Trả lương nhân viên',
//            CashBookPostedDate: 'qwwqqw',
//            RefNoFiance: 'CT00001' + i,
//            DepartmentName: 'Công ty Cổ Phần MISA',
//        })
//    }
//    raeJS.buildDataIntoTable(fakeData);
})


var raeJS = Object.create({
    loadData: function () {
        // Gọi Service lấy dữ liệu trả về:

        // Build dữ liệu lên table (fakeData sẽ là dữ liệu thật lấy từ Service trả về - kiểu mảng):
        raeJS.buildDataIntoTable(fakeData);
    },
    buildDataIntoTable: function (data) {
        var table = $('#tbodyRAE');
        table.html('');
        // Lấy thông tin các cột dữ liệu:
        var column = $('#tblCustomerList .gridHeader th');
        var rowTemplate = [];
        var fieldData = [];
        rowTemplate.push('<tr class="{0}">');
        column.each(function (index, item) {
            fieldData.push($(item).attr('fieldData'));
        })
        data.forEach(function (item, index) {
            var htmlItem = [];
            htmlItem.push('<tr class="{0}">'.format(index % 2 === 0 ? '' : 'row-highlight'));
            fieldData.forEach(function (valueField, indexField) {
                if (indexField === 0) {
                    htmlItem.push('<td class="no-border-left" >{0}</td>'.format(item[valueField]));
                } else {
                    htmlItem.push('<td>{0}</td>'.format(item[valueField]));
                }
            })
            htmlItem.push('</tr>');
            table.append(htmlItem.join(""));
        });
    }
})