//jqGrid的配置信息
if ($.jgrid) {
    $.jgrid.defaults.width = 1000;
    $.jgrid.defaults.responsive = true;
    $.jgrid.defaults.styleUI = 'Bootstrap';
}

//工具集合Tools
window.T = {};

//iframe自适应
$(window).on('resize', function () {
    var $content = $('#mainApp');
    $content.height($(this).height());
    $content.find('iframe').each(function () {
        $(this).height($content.height()-150);
    });
    var $rrapp = $('#rrapp').parent();
    $rrapp.height($(this).height());
    $(this).height($content.height());
}).resize();

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false
});

//重写alert
window.alert = function (msg, callback) {
    parent.layer.alert(msg, function (index) {
        parent.layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
};

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
        iview.Message.error("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        iview.Message.error("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
};

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        iview.Message.error("请选择一条记录");
        return;
    }
    return grid.getGridParam("selarrrow");
};

/**
 * 预览图片
 * @param url
 */
function eyeImage(url) {
    if (!url) {
        iview.Message.error('请先上传图片');
        return;
    }
    layer.photos({
        photos: {
            "title": "预览", //相册标题
            "start": 0, //初始显示的图片序号，默认0
            "data": [   //相册包含的图片，数组格式
                {
                    "src": url //原图地址
                }
            ]
        }, anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
    });
};

/**
 * 预览图片
 * @param data
 */
function eyeImages(data) {
    layer.photos({
        photos: {
            "title": "预览", //相册标题
            "start": 0, //初始显示的图片序号，默认0
            "data": data
        }, anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机
    });
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

/**
 * 翻译日期
 * @param date
 * @param fmt
 * @returns {*}
 */
function transDate(date, fmt) {
    if (date) {
        try {
            return new Date(date).dateFormat(fmt);
        } catch (e) {
            return '-';
        }
    } else {
        return '-';
    }
};

/**
 * 翻译图片
 * @param url
 * @returns {*}
 */
function transImg(url) {
    if (url) {
        return '<img width="50px" height="50px" src="' + url + '">';
    } else {
        return '-';
    }
};

/**
 * 翻译性别
 * @param gender
 * @returns {*}
 */
function transGender(gender) {
    if (gender == 1) {
        return '男';
    }
    if (gender == 2) {
        return '女';
    }
    return '未知';
};

function transIsNot(value) {
    if (value == 1) {
        return '<span class="label label-success">是</span>';
    }
    return '<span class="label label-danger">否</span>';
};

function toUrl(href) {
    window.location.href = href;
}

function dialogClose() {
    var index = top.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    top.layer.close(index); //再执行关闭
}

function dialogLoading(flag) {
    if (flag) {
        top.layer.load(0, {
            shade: [0.1, '#fff'],
            time: 2000
        });
    } else {
        top.layer.closeAll('loading');
    }
}

/**
 * 用JS获取地址栏参数的方法
 * @param name
 * @returns {null}
 * @constructor
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}