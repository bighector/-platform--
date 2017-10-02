<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../common/pageheader.jsp" %>
<div id="vueapp">
    <div class="grid-btn">
        <div class="form-group col-sm-2">
            <i-input v-model="q.tableName" @on-enter="query" placeholder="表名"/>
        </div>
        <i-button @click="query">查询</i-button>
        <shiro:hasPermission name="sys:sss">

        </shiro:hasPermission>
        <i-button type="primary" @click="generator"><i class="fa fa-file-code-o"></i>&nbsp;生成代码</i-button>
    </div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>

<script>
    $(function () {
        $("#jqGrid").jqGrid({
            url: 'list',
            datatype: "json",
            colModel: [
                {label: '表名', name: 'tableName', width: 100, key: true},
                {label: '表备注', name: 'tableComment', width: 100},
                {
                    label: '创建时间', name: 'createTime', width: 100, formatter: function (value) {
                    if (value) {
                        try {
                            return new Date(value).dateFormat(fmt);
                        } catch (e) {
                            return '-';
                        }
                    } else {
                        return '-';
                    }
                }
                }
            ],
            viewrecords: true,
            height: 385,
            rowNum: 10,
            rowList: [10, 30, 50, 100, 200],
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
        el: '#vueapp',
        data: {
            q: {
                tableName: null
            }
        },
        methods: {
            query: function () {
                $("#jqGrid").jqGrid('setGridParam', {
                    postData: {'tableName': vm.q.tableName},
                    page: 1
                }).trigger("reloadGrid");
            },
            generator: function () {
                var tableNames = getSelectedRows();
                if (tableNames == null) {
                    return;
                }
                location.href = "code?tables=" + JSON.stringify(tableNames);
            }
        }
    });
</script>