$(document).ready(function () {
    var fakeData = [];
    for (var i = 0; i < 100; i++) {
        fakeData.push({
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
        })
    }
    raeJS.buildDataIntoTable(fakeData);

    $('#tblCustomerList').on('click', '#tbodyRAE tr', raeJS.rowRAE_OnClick);

    $('#tblCustomerList').on('dbclick', '#tbodyRAE tr', raeJS.btnAdd_OnClick);

    $('#btnAdd').click(raeJS.btnAdd_OnClick);
    raeJS.btnAdd_OnClick();

})


var raeJS = Object.create({
    // Form chi tiết:
    dialogDetail: $("#frmRAEDetail").dialog({
        autoOpen: false,
        width: 800,
        modal: true,
        //buttons: {
        //    "Cất": function () {

        //    },
        //    "Hủy bỏ": function () {
        //        raeJS.dialogDetail.dialog("close");
        //    }
        //},
        open: function () {
           // Thực hiện binding các thông tin:
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
        close: function () {
            raeJS.dialogDetail.dialog("close");
        }
    }),

    //Load dữ liệu
    loadData: function () {
        // Gọi Service lấy dữ liệu trả về:

        // Build dữ liệu lên table (fakeData sẽ là dữ liệu thật lấy từ Service trả về - kiểu mảng):
        raeJS.buildDataIntoTable(fakeData);
    },

    // Build dữ liệu lên bảng:
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
    },

    // Chọn 1 bản ghi trong danh sách:
    rowRAE_OnClick: function () {
        commonJS.showMask($('.frmCustomerDetail .rae-detail-box'));
        // Lấy Detail từ Service:
        var RAEDetail = [];
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
        for (var i = 0; i < 4; i++) {
            (function () {
                detail.CreditAmount += i;
                RAEDetail.push(detail);
            })(i)
        }

        // Buid dữ liệu Detail:
        var tbody = $('#tbodyRAEDetail');
        tbody.html('');

        // Lấy thông tin các cột dữ liệu:
        var column = $('table#tblRAEDetail .gridHeader th');
        var rowTemplate = [];
        var fieldData = [];
        rowTemplate.push('<tr class="{0}">');
        column.each(function (index, item) {
            fieldData.push($(item).attr('fieldData'));
        })
        RAEDetail.forEach(function (item, index) {
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
            tbody.append(htmlItem.join(""));
        });
        setTimeout(function () {
            commonJS.hideMask($('.frmCustomerDetail .rae-detail-box'));
        }, 500)
    },

    // Nhấn Button thêm:
    btnAdd_OnClick: function () {
        raeJS.dialogDetail.dialog('open');
    }
})

