<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../common/pageheader.jsp" %>
<div id="vueapp" v-cloak>
    <div>
        <div class="grid-btn">
            <i-button type="ghost" @click="reload"><i class="fa fa-refresh"></i>&nbsp;刷新</i-button>
            <shiro:hasPermission name="sys:menu:save">
                <i-button type="info" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</i-button>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:menu:update">
                <i-button type="warning" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</i-button>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:menu:delete">
                <i-button type="error" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</i-button>
            </shiro:hasPermission>
        </div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>

    <Card v-show="!showList" id="formObj">
        <p slot="title">{{title}}</p>
        <i-form ref="formValidate" :model="menu" :rules="ruleValidate" :label-width="80">
            <Form-item label="类型" prop="type">
                <Radio-group v-model="menu.type">
                    <Radio label="0">
                        <span>目录</span>
                    </Radio>
                    <Radio label="1">
                        <span>菜单</span>
                    </Radio>
                    <Radio label="2">
                        <span>按钮</span>
                    </Radio>
                </Radio-group>
            </Form-item>
            <Form-item label="菜单名称" prop="name">
                <i-input v-model="menu.name" placeholder="菜单名称或按钮名称"/>
            </Form-item>

            <Form-item label="上级菜单" v-if="menu.type == 2" prop="parentName">
                <i-input v-model="menu.parentName" @on-click="menuTree" icon="eye" readonly="readonly"
                         placeholder="一级菜单"/>
            </Form-item>
            <Form-item label="上级菜单" v-if="menu.type != 2">
                <i-input v-model="menu.parentName" @on-click="menuTree" icon="eye" readonly="readonly"
                         placeholder="一级菜单"/>
            </Form-item>

            <Form-item v-if="menu.type == 1" label="菜单URL" prop="url">
                <i-input v-model="menu.url" placeholder="菜单URL"/>
            </Form-item>
            <Form-item v-if="menu.type == 1 || menu.type == 2" label="授权标识" prop="perms">
                <i-input v-model="menu.perms" placeholder="多个用逗号分隔，如：user:list,user:create"/>
            </Form-item>
            <Form-item v-if="menu.type != 2" label="排序号" prop="orderNum">
                <Input-number :min="0" :step="1" v-model="menu.orderNum" placeholder="排序号" style="width: 188px;"/>
            </Form-item>
            <Form-item v-if="menu.type != 2" label="图标" prop="icon">
                <i-input v-model="menu.icon" placeholder="图标，点击右边按钮选取图标" @on-click="selectIcon" icon="eye"/>
            </Form-item>
            <Form-item label="状态" prop="status">
                <Radio-group v-model="menu.status">
                    <Radio label="0">
                        <span>有效</span>
                    </Radio>
                    <Radio label="1">
                        <span>无效</span>
                    </Radio>
                </Radio-group>
            </Form-item>
            <Form-item>
                <i-button type="primary" @click="handleSubmit('formValidate')">提交</i-button>
                <i-button type="warning" @click="reload" style="margin-left: 8px">返回</i-button>
                <i-button type="ghost" @click="handleReset('formValidate')" style="margin-left: 8px">重置</i-button>
            </Form-item>
        </i-form>
    </Card>
</div>

<!-- 选择菜单 -->
<div id="menuLayer" style="display: none;padding:10px;">
    <ul id="menuTree" class="ztree"></ul>
</div>

<script type="text/javascript">
    $(function () {
        initialPage();
        getGrid();
    });

    function initialPage() {
        $(window).resize(function () {
            TreeGrid.table.resetHeight({height: $(window).height() - 100});
        });
    }

    function getGrid() {
        var colunms = TreeGrid.initColumn();
        var table = new TreeTable(TreeGrid.id, 'queryAll', colunms);
        table.setExpandColumn(2);
        table.setIdField("menuId");
        table.setCodeField("menuId");
        table.setParentCodeField("parentId");
        table.setExpandAll(false);
        table.setHeight($(window).height() - 100);
        table.init();
        TreeGrid.table = table;
    }

    var TreeGrid = {
        id: "jqGrid",
        table: null,
        layerIndex: -1
    };

    /**
     * 初始化表格的列
     */
    TreeGrid.initColumn = function () {
        var columns = [
            {field: 'selectItem', radio: true},
            {title: '编号', field: 'menuId', visitable: false, align: 'center', valign: 'middle', width: '80px'},
            {title: '名称', field: 'name', align: 'center', valign: 'middle', width: '180px'},
            {title: '上级菜单', field: 'parentName', align: 'center', valign: 'middle', width: '100px'},
            {
                title: '图标',
                field: 'icon',
                align: 'center',
                valign: 'middle',
                width: '80px',
                formatter: function (item, index) {
                    return item.icon == null ? '' : '<i class="' + item.icon + ' fa-lg"></i>';
                }
            },
            {
                title: '类型',
                field: 'type',
                align: 'center',
                valign: 'middle',
                width: '80px',
                formatter: function (item) {
                    if (item.type === 0) {
                        return '<span class="label label-primary">目录</span>';
                    }
                    if (item.type === 1) {
                        return '<span class="label label-success">菜单</span>';
                    }
                    if (item.type === 2) {
                        return '<span class="label label-warning">按钮</span>';
                    }
                }
            },
            {title: '排序', field: 'orderNum', align: 'center', valign: 'middle', width: '80px'},
            {title: '菜单URL', field: 'url', align: 'center', valign: 'middle', width: '150px'},
            {title: '授权标识', field: 'perms', align: 'center', valign: 'middle'},
            {
                title: '状态', field: 'status', align: 'center', valign: 'middle', width: '80px',
                formatter: function (item) {
                    if (item.status === 1) {
                        return '<span class="label label-danger">无效</span>';
                    }
                    return '<span class="label label-success">有效</span>';
                }
            }];
        return columns;
    };

    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "menuId",
                pIdKey: "parentId",
                rootPId: -1
            },
            key: {
                url: "nourl"
            }
        }
    };
    var ztree;

    var vm = new Vue({
        el: '#vueapp',
        data: {
            showList: true,
            title: null,
            menu: {
                parentName: null,
                parentId: 0,
                type: 1,
                orderNum: 0,
                status: 0
            },
            q: {
                menuName: '',
                parentName: ''
            },
            ruleValidate: {
                name: [
                    {required: true, message: '菜单名称不能为空', trigger: 'blur'}
                ],
                url: [
                    {required: true, message: '菜单url不能为空', trigger: 'blur'}
                ],
                parentName: [
                    {required: true, message: '上级菜单不能为空', trigger: 'blur'}
                ]
            }
        },
        methods: {
            selectIcon: function () {
                layer.open({
                    type: 2,
                    title: '选取图标',
                    closeBtn: 1,
                    anim: -1,
                    isOutAnim: false,
                    shadeClose: false,
                    shade: 0.3,
                    area: ['1030px', '500px'],
                    content: ['icon'],
                    btn: false
                });
            },
            getMenu: function (menuId) {
                //加载菜单树
                $.get("select", function (r) {
                    ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
                    var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
                    if (node) {
                        ztree.selectNode(node);
                        vm.menu.parentName = node.name;
                    } else {
                        node = ztree.getNodeByParam("menuId", 0);
                        ztree.selectNode(node);
                        vm.menu.parentName = node.name;
                    }
                })
            },
            add: function () {
                vm.showList = false;
                vm.title = "新增";
                var menuId = TreeGrid.table.getSelectedRow();
                var parentId = 0;
                if (menuId.length != 0) {
                    parentId = menuId[0].id;
                }
                vm.menu = {parentName: null, parentId: parentId, type: 1, orderNum: 0, status: 0};
                vm.getMenu();

                layer.open({
                    type: 1,
                    title: '新增',
                    closeBtn: 1,
                    anim: -1,
                    isOutAnim: false,
                    shadeClose: false,
                    shade: 0.3,
                    area: ['1030px', '500px'],
                    content: $('#formObj'),
                    btn: false
                });
            },
            update: function (event) {
                var menuId = TreeGrid.table.getSelectedRow();
                if (menuId.length == 0) {
                    iview.Message.error("请选择一条记录");
                    return;
                }

                $.get("info/" + menuId[0].id, function (r) {
                    vm.showList = false;
                    vm.title = "修改";
                    vm.menu = r.menu;

                    vm.getMenu();
                });
                layer.open({
                    type: 1,
                    title: '修改',
                    closeBtn: 1,
                    anim: -1,
                    isOutAnim: false,
                    shadeClose: false,
                    shade: 0.3,
                    area: ['1030px', '500px'],
                    content: $('#formObj'),
                    btn: false
                });
            },
            del: function (event) {
                var menuIds = TreeGrid.table.getSelectedRow(), ids = [];
                if (menuIds.length == 0) {
                    iview.Message.error("请选择一条记录");
                    return;
                }

                confirm('确定要删除选中的记录？', function () {
                    $.each(menuIds, function (idx, item) {
                        ids[idx] = item.id;
                    });
                    $.ajax({
                        type: "POST",
                        url: "delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
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
                });
            },
            saveOrUpdate: function (event) {
                var url = vm.menu.menuId == null ? "save" : "update";
                $.ajax({
                    type: "POST",
                    url: url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.menu),
                    success: function (r) {
                        if (r.code === 0) {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                            layer.closeAll();
                        } else {
                            iview.Message.error(r.msg);
                        }
                    }
                });
            },
            menuTree: function () {
                layer.open({
                    type: 1,
                    offset: '50px',
                    skin: 'layui-layer-molv',
                    title: "选择菜单",
                    area: ['300px', '450px'],
                    content: jQuery("#menuLayer"),
                    btn: ['确定', '取消'],
                    btn1: function (index) {
                        var node = ztree.getSelectedNodes();
                        //选择上级菜单
                        vm.menu.parentId = node[0].menuId;
                        vm.menu.parentName = node[0].name;

                        layer.close(index);
                    }
                });
            },
            query: function () {
                vm.reload();
            },
            reload: function (event) {
                vm.showList = true;
                layer.closeAll();
                TreeGrid.table.refresh();
            },
            handleSubmit: function (name) {
                handleSubmitValidate(this, name, function () {
                    vm.saveOrUpdate()
                });
            },
            handleReset: function (name) {
                handleResetForm(this, name);
            }
        }
    });
</script>