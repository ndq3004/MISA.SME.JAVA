$(document).ready(function () {
    $ajax({
        method:"post",
        url:"/getAll",
        // beforeSend{
        //     xhr.setRequestHeader('content-Type', 'application/json');
        //     xhr.setRequestHeader("Accept", "/")
        //     xhr.setRequestHeader("Cache-Control", "no-cache")
        //     xhr.setRequestHeader('authorization',localStorage.getItem("authenCookie"));
        //     xhr.setRequestHeader('path','/api/login');
        //     xhr.setRequestHeader('domain', 'localhost');
        //     xhr.setRequestHeader('Expires', 'Tue, 19 Jan 2038 03:14:07 GMT');
        // },
        success: function(res){
            var fakeData = []
            for (var i = 0; i < 20; i++) {
                fakeData.push({
                    PostedDate: res.data.postedDate,
                    RefDate: res.data.createdDate,
                    RefNo: res.data.refID,
                    JournalMemo: "res.data",
                    RefTypeName: "res.data",
                    TotalAmount: "res.data",
                    AccountObjectName: res.data.accountObjectID,
                    ReasonTypeName: res.data.reason,
                    CashBookPostedDate: "res.data",
                    RefNoFiance: res.data.refNoFinance,
                    DepartmentName: "MISA",
                })
            }
            raeJS.buildDataIntoTable(fakeData);
        }

    })
    
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