$(function () {
    $("#jqGrid").jqGrid({
        url: '../feedback/list',
        datatype: "json",
        colModel: [
            {label: 'msgId', name: 'msgId', index: 'msg_id', key: true, hidden: true},
            {label: '父节点', name: 'parentId', index: 'parent_id', width: 80},
            {label: '会员名称', name: 'userName', index: 'user_name', width: 80},
            {label: '邮件', name: 'userEmail', index: 'user_email', width: 80},
            {label: '标题', name: 'msgTitle', index: 'msg_title', width: 80},
            {label: '类型', name: 'msgType', index: 'msg_type', width: 80},
            {label: '状态', name: 'msgStatus', index: 'msg_status', width: 80},
            {label: '详细内容', name: 'msgContent', index: 'msg_content', width: 80},
            {label: '反馈时间', name: 'msgTime', index: 'msg_time', width: 80},
            {label: '图片', name: 'messageImg', index: 'message_img', width: 80},
            {label: '订单Id', name: 'orderId', index: 'order_id', width: 80},
            {label: 'msgArea', name: 'msgArea', index: 'msg_area', width: 80}],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        feedback: {},
        q: {
            userName: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.feedback = {};
        },
        update: function (event) {
            var msgId = getSelectedRow();
            if (msgId == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(msgId)
        },
        saveOrUpdate: function (event) {
            var url = vm.feedback.msgId == null ? "../feedback/save" : "../feedback/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.feedback),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var msgIds = getSelectedRows();
            if (msgIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../feedback/delete",
                    contentType: "application/json",
                    data: JSON.stringify(msgIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (msgId) {
            $.get("../feedback/info/" + msgId, function (r) {
                vm.feedback = r.feedback;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'userName': vm.q.userName},
                page: page
            }).trigger("reloadGrid");
        }
    }
});