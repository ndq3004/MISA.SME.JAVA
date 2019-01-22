$(document).ready(function () {
	$.ajax({
		
	})
    var fakeData = []
    for (var i = 0; i < 20; i++) {
        fakeData.push({
            PostedDate: '',
            RefDate: '11',
            RefNo: 'UNC00013'+i,
            JournalMemo: 'Trả lương nhân viên tháng 4 năm 2018',
            RefTypeName: 'Chi tiền lương cho Nhân viên',
            TotalAmount: '245041092',
            AccountObjectName: 'Công ty TNHH Phú Thái',
            ReasonTypeName: 'Trả lương nhân viên',
            CashBookPostedDate: 'qwwqqw',
            RefNoFiance: 'CT00001' + i,
            DepartmentName: 'Công ty Cổ Phần MISA',
        })
    }
    raeJS.buildDataIntoTable(fakeData);
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