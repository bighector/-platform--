$(function () {
    $("#jqGrid").jqGrid({
        url: '../address/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '会员', name: 'shopUserName', index: 'user_id', width: 90},
            {label: '收货人姓名', name: 'userName', index: 'user_name', width: 80},
            {label: '手机', name: 'telNumber', index: 'tel_number', width: 80},
            {label: '收货地址国家码', name: 'nationalCode', index: 'national_Code', width: 80},
            {label: '省', name: 'provinceName', index: 'province_Name', width: 80},
            {label: '市', name: 'cityName', index: 'city_Name', width: 80},
            {label: '区', name: 'countyName', index: 'county_Name', width: 80},
            {label: '详细收货地址信息', name: 'detailInfo', index: 'detail_Info', width: 80},
            {label: '邮编', name: 'postalCode', index: 'postal_Code', width: 80}],
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
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        q: {
            userName: '',
            telNumber: ''
        }
        // title: null,
        // address: {isDefault: '0', cityId: '', districtId: ''},
        // ruleValidate: {
        //     mobile: [
        //         {required: true, message: '联系电话不能为空', trigger: 'blur'}
        //     ],
        //     address: [
        //         {required: true, message: '详细地址不能为空', trigger: 'blur'}
        //     ]
        // },
        // users: [],
        // countryList: [],
        // provinceList: [],
        // cityList: [],
        // districtList: []
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'userName': vm.q.userName,
                    'telNumber': vm.q.telNumber
                },
                page: page
            }).trigger("reloadGrid");
        }
        // add: function () {
        //     vm.showList = false;
        //     vm.title = "新增";
        //     vm.address = {isDefault: '0'};
        //     vm.users = [];
        //
        //     this.getUsers();
        //     this.getCountries();
        // },
        // update: function (event) {
        //     var id = getSelectedRow();
        //     if (id == null) {
        //         return;
        //     }
        //     vm.showList = false;
        //     vm.title = "修改";
        //
        //     vm.getInfo(id)
        //     this.getUsers();
        //     this.getCountries();
        //     this.changeCountry();
        //     this.changeProvince();
        //     this.changeCity();
        // },
        // saveOrUpdate: function (event) {
        //     var url = vm.address.id == null ? "../address/save" : "../address/update";
        //     $.ajax({
        //         type: "POST",
        //         url: url,
        //         contentType: "application/json",
        //         data: JSON.stringify(vm.address),
        //         success: function (r) {
        //             if (r.code === 0) {
        //                 alert('操作成功', function (index) {
        //                     vm.reload();
        //                 });
        //             } else {
        //                 alert(r.msg);
        //             }
        //         }
        //     });
        // },
        // del: function (event) {
        //     var ids = getSelectedRows();
        //     if (ids == null) {
        //         return;
        //     }
        //
        //     confirm('确定要删除选中的记录？', function () {
        //         $.ajax({
        //             type: "POST",
        //             url: "../address/delete",
        //             contentType: "application/json",
        //             data: JSON.stringify(ids),
        //             success: function (r) {
        //                 if (r.code == 0) {
        //                     alert('操作成功', function (index) {
        //                         $("#jqGrid").trigger("reloadGrid");
        //                     });
        //                 } else {
        //                     alert(r.msg);
        //                 }
        //             }
        //         });
        //     });
        // },
        // getInfo: function (id) {
        //     $.get("../address/info/" + id, function (r) {
        //         vm.address = r.address;
        //     });
        // },
        // getUsers: function () {
        //     $.get("../user/queryAll", function (r) {
        //         vm.users = r.list;
        //     });
        // },
        // getCountries: function () {
        //     $.get("../sys/region/getAllCountry", function (r) {
        //         vm.countryList = r.list;
        //     });
        // },
        // //选择国家
        // changeCountry: function () {
        //     $.get("../sys/region/getAllProvice?areaId=" + vm.address.countryId, function (r) {
        //         vm.provinceList = r.list;
        //     });
        // },
        // //选择省份
        // changeProvince: function () {
        //     $.get("../sys/region/getAllCity?areaId=" + vm.address.provinceId, function (r) {
        //         vm.cityList = r.list;
        //     });
        // },
        // //选择城市
        // changeCity: function () {
        //     $.get("../sys/region/getChildrenDistrict?areaId=" + vm.address.cityId, function (r) {
        //         vm.districtList = r.list;
        //     });
        // },
        // handleSubmit: function (name) {
        //     handleSubmitValidate(this, name, function () {
        //         vm.saveOrUpdate()
        //     });
        // },
        // handleReset: function (name) {
        //     handleResetForm(this, name);
        // }
    }
});