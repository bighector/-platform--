<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/statics"/>

<link rel="shortcut icon" href="favicon.ico">
<!--bootstrap-->
<link rel="stylesheet" href="${ctxStatic}/css/main.css">
<link rel="stylesheet" href="${ctxStatic}/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctxStatic}/css/font-awesome.min.css">
<!--jqgrid-->
<link rel="stylesheet" href="${ctxStatic}/js/plugins/jqgrid/ui.jqgrid-bootstrap.css">
<link rel="stylesheet" href="${ctxStatic}/js/plugins/ztree/css/metroStyle/metroStyle.css">
<!--main-->
<link rel="stylesheet" href="${ctxStatic}/css/iview.css">
<!--treegrid-->
<link rel="stylesheet" href="${ctxStatic}/js/plugins/treegrid/jquery.treegrid.css">
<link rel="stylesheet" href="${ctxStatic}/css/login.css" rel="stylesheet">

<link href="${ctxStatic}/css/animate.css" rel="stylesheet">
<link href="${ctxStatic}/css/style.css?v=4.1.0" rel="stylesheet">
<!--jquery-->
<script src="${ctxStatic}/js/jquery.min.js"></script>
<!--layer-->
<script src="${ctxStatic}/js/plugins/layer/layer.min.js"></script>
<!--bootstrap-->
<script src="${ctxStatic}/js/bootstrap.min.js"></script>
<!--vue-->
<script src="${ctxStatic}/js/vue.min.js"></script>
<script src="${ctxStatic}/js/iview.min.js"></script>
<!--jqgrid-->
<script src="${ctxStatic}/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="${ctxStatic}/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<!--ztree-->
<script src="${ctxStatic}/js/plugins/ztree/jquery.ztree.all.min.js"></script>
<!--treegrid-->
<script src="${ctxStatic}/js/plugins/treegrid/jquery.treegrid.extension.js"></script>
<script src="${ctxStatic}/js/plugins/treegrid/jquery.treegrid.min.js"></script>
<script src="${ctxStatic}/js/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script src="${ctxStatic}/js/plugins/treegrid/tree.table.js"></script>

<script src="${ctxStatic}/js/jquery-extend.js"></script>

<!-- 全局js -->
<script src="${ctxStatic}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctxStatic}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- 第三方插件 -->
<script src="${ctxStatic}/js/plugins/pace/pace.min.js"></script>

<!-- 自定义js -->
<script src="${ctxStatic}/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="${ctxStatic}/js/contabs.js"></script>

<script>
    jQuery(document).ready(function () {

        //jqGrid的配置信息
        if ($.jgrid) {
            $.jgrid.defaults.width = 1000;
            $.jgrid.defaults.responsive = true;
            $.jgrid.defaults.styleUI = 'Bootstrap';
        }
    });

    //重写confirm式样框
    window.confirm = function (msg, callback) {
        parent.layer.confirm(msg, {
                skin: 'layui-layer-molv', btn: ['确定', '取消']
            },
            function () {//确定事件
                if (typeof(callback) === "function") {
                    callback("ok");
                }
            });
    };


    //重写alert
    window.alert = function (msg, callback) {
        parent.layer.alert(msg, function (index) {
            parent.layer.close(index);
            if (typeof(callback) === "function") {
                callback("ok");
            }
        });
    };

    function toUrl(href) {
        window.location.href = href;
    }

    function getJson(form) {
        var o = {};
        var $form = $(form).find('input,textarea,select');
        $.each($form, function (i, item) {
            var $this = $(item);

            if ($this.attr("type") == 'radio') {
                o[$this.attr("name")] = $("input[name='" + $this.attr("name") + "']:checked").val();
                return true;
            }
            if ($this.hasClass("rate")) {
                o[$this.attr("name")] = parseFloat($this.val().toString().replace(/\$|\,/g, '')) * parseFloat($this.attr("unit"));
            } else {
                o[$this.attr("name")] = $this.val();
            }
        })
        return o;
    }

    //获取选中的数据
    function getSelectedRowData() {
        var id = getSelectedRow();
        return $("#jqGrid").jqGrid('getRowData', id);
    }

    //选择一条记录
    function getSelectedRow() {
        var grid = $("#jqGrid");
        var rowKey = grid.getGridParam("selrow");
        if (!rowKey) {
            alert("请选择一条记录");
            return;
        }

        var selectedIDs = grid.getGridParam("selarrrow");
        if (selectedIDs.length > 1) {
            alert("只能选择一条记录");
            return;
        }

        return selectedIDs[0];
    };

    //选择多条记录
    function getSelectedRows() {
        var grid = $("#jqGrid");
        var rowKey = grid.getGridParam("selrow");
        if (!rowKey) {
            alert("请选择一条记录");
            return;
        }
        return grid.getGridParam("selarrrow");
    };

    /**
     * 重置验证
     * @param vue vue对象
     * @param name
     */
    function handleResetForm(vue, name) {
        vue.$refs[name].resetFields();
    };

    /**
     * 表单验证
     * @param vue vue对象
     * @param name 验证规则
     * @param callback 验证通过回调函数
     */
    function handleSubmitValidate(vue, name, callback) {
        vue.$refs[name].validate(function (valid) {
            if (valid) {
                callback();
            } else {
                iview.Message.error('请填写完整信息!');
                return false;
            }
        })
    };
</script>