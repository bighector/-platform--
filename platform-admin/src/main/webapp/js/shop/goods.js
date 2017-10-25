$(function () {
    $("#jqGrid").jqGrid({
        url: '../goods/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '商品类型', name: 'categoryName', index: 'category_id', width: 80},
            {label: '商品序列号', name: 'goodsSn', index: 'goods_sn', width: 80, hidden: true},
            {label: '名称', name: 'name', index: 'name', width: 160},
            {label: '品牌', name: 'brandName', index: 'brand_id', width: 120},
            {label: '关键字', name: 'keywords', index: 'keywords', width: 80, hidden: true},
            {label: '简明介绍', name: 'goodsBrief', index: 'goods_brief', width: 80, hidden: true},
            {label: '商品描述', name: 'goodsDesc', index: 'goods_desc', width: 80, hidden: true},
            {
                label: '上架', name: 'isOnSale', index: 'is_on_sale', width: 50,
                formatter: function (value) {
                    return transIsNot(value);
                }
            },
            {
                label: '录入日期', name: 'addTime', index: 'add_time', width: 80, formatter: function (value) {
                return transDate(value, 'yyyy-MM-dd');
            }
            },
            // {label: '排序', name: 'sortOrder', index: 'sort_order', width: 80},
            {label: '删除状态', name: 'isDelete', index: 'is_delete', width: 80, hidden: true},
            {label: '属性类别', name: 'attributeCategoryName', index: 'attribute_category', width: 80},
            // {label: '专柜价格', name: 'counterPrice', index: 'counter_price', width: 80},
            // {label: '附加价格', name: 'extraPrice', index: 'extra_price', width: 80},
            {label: '是否新商品', name: 'isNew', index: 'is_new', width: 80, hidden: true},
            {label: '商品单位', name: 'goodsUnit', index: 'goods_unit', width: 80},
            {label: '商品主图', name: 'primaryPicUrl', index: 'primary_pic_url', width: 80, hidden: true},
            {label: '商品列表图', name: 'listPicUrl', index: 'list_pic_url', width: 80, hidden: true},
            {label: '零售价格', name: 'retailPrice', index: 'retail_price', width: 80},
            {label: '商品库存', name: 'goodsNumber', index: 'goods_number', width: 80},
            {label: '销售量', name: 'sellVolume', index: 'sell_volume', width: 80},
            {label: '主product_id', name: 'primaryProductId', index: 'primary_product_id', width: 80, hidden: true},
            // {label: '单价', name: 'unitPrice', index: 'unit_price', width: 80},
            {label: '市场价', name: 'marketPrice', index: 'market_price', width: 80},
            {label: '推广描述', name: 'promotionDesc', index: 'promotion_desc', width: 80, hidden: true},
            {label: '推广标签', name: 'promotionTag', index: 'promotion_tag', width: 80, hidden: true},
            // {label: 'APP专享价', name: 'appExclusivePrice', index: 'app_exclusive_price', width: 80, hidden: true},
            // {
            //     label: '是否是APP专属',
            //     name: 'isAppExclusive',
            //     index: 'is_app_exclusive',
            //     width: 80,
            //     formatter: function (value) {
            //         return transIsNot(value);
            //     }
            // },
            // {
            //     label: '限购', name: 'isLimited', index: 'is_limited', width: 80, formatter: function (value) {
            //     return transIsNot(value);
            //     }
            // },
            {
                label: '热销', name: 'isHot', index: 'is_hot', width: 80, formatter: function (value) {
                return transIsNot(value);
            }
            }],
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
    $('#goodsDesc').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: '500px', //高度
        minHeight: '200px',
        language: "zh_cn",
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',//上传到本地服务器
        imageUploadParams: {id: "edit"},
        // imageManagerDeleteURL: '../sys/oss/delete',//删除图片(有问题)
        imagesLoadURL: '../sys/oss/queryAll'//管理图片
    })
});

var ztree;

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        goodsImgList: [],
        goods: {
            primaryPicUrl: '',
            listPicUrl: '',
            categoryId: '',
            isOnSale: 1,
            isNew: 1,
            isAppExclusive: 0,
            isLimited: 0,
            isHot: 0,
            categoryName: ''
        },
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        // attribute: [],
        // attributes: [],
        brands: [],//品牌
        macros: [],//商品单位
        attributeCategories: []//属性类别
    },
    methods: {
        fileClick() {
            document.getElementById('upload_file').click()
        },
        fileChange(el) {
            if (!el.target.files[0].size) return;
            this.fileList(el.target);
            el.target.value = ''
        },
        fileList(fileList) {
            let files = fileList.files;
            for (let i = 0; i < files.length; i++) {
                //判断是否为文件夹
                if (files[i].type != '') {
                    this.fileAdd(files[i]);
                }
            }
        },
        fileAdd(file) {
            //判断是否为图片文件
            if (file.type.indexOf('image') == -1) {
                iview.Message.error('请选择图片文件');
            } else {
                let reader = new FileReader();
                reader.vue = this;
                reader.readAsDataURL(file);
                reader.onload = function () {
                    let imgUrl = this.result;
                    vm.goodsImgList.push({
                        imgUrl: imgUrl
                    });
                }
            }
        },
        fileDel(index) {
            vm.goodsImgList.splice(index, 1);
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.goodsImgList = [];
            vm.goods = {
                primaryPicUrl: '',
                listPicUrl: '',
                categoryId: '',
                isOnSale: 1,
                isNew: 1,
                isAppExclusive: 0,
                isLimited: 0,
                isHot: 0,
                categoryName: ''
            };
            $('#goodsDesc').editable('setHTML', '');
            vm.getCategory();
            vm.brands = [];
            vm.macros = [];
            vm.attributeCategories = [];
            // vm.attribute = [];
            vm.getBrands();
            vm.getMacro();
            vm.getAttributeCategories();
            // vm.getAttributes('');
        },
        changeCategory: function (opt) {
            // vm.getAttributes(opt.value);
        },
        // getAttributes: function (attributeCategoryId) {
        //     $.get("../attribute/queryAll?attributeCategoryId=" + attributeCategoryId, function (r) {
        //         vm.attributes = r.list;
        //     });
        // },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
            vm.getBrands();
            vm.getMacro();
            vm.getAttributeCategories();
            vm.getGoodsGallery(id);
        },
        /**
         * 获取品牌
         */
        getBrands: function () {
            $.get("../brand/queryAll", function (r) {
                vm.brands = r.list;
            });
        },
        /**
         * 获取单位
         */
        getMacro: function () {
            $.get("../sys/macro/queryMacrosByValue?value=goodsUnit", function (r) {
                vm.macros = r.list;
            });
        },
        getGoodsGallery: function (id) {//获取商品顶部轮播图
            $.get("../goodsgallery/queryAll?goodsId=" + id, function (r) {
                vm.goodsImgList = r.list;
            });
        },
        getAttributeCategories: function () {
            $.get("../attributecategory/queryAll", function (r) {
                vm.attributeCategories = r.list;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.goods.id == null ? "../goods/save" : "../goods/update";
            vm.goods.goodsDesc = $('#goodsDesc').editable('getHTML');
            vm.goods.goodsImgList = vm.goodsImgList;
            // var arr = $('[name="attributeValue"]');
            // var goodsAttribute = [];
            // for (var i = 0; i < arr.length; i++) {
            //     if (vm.attribute.contains($(arr[i]).parent().attr('data'))) {
            //         goodsAttribute.push({
            //             goodsId: vm.goods.id || '',
            //             attributeId: $(arr[i]).parent().attr('data'),
            //             value: arr[i].value
            //         })
            //     }
            // }
            // vm.goods.attributeEntityList = goodsAttribute;
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.goods),
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
        enSale: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('确定要上架选中的商品？', function () {
                $.ajax({
                    type: "POST",
                    url: "../goods/enSale",
                    contentType: "application/json",
                    data: JSON.stringify(id),
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
        unSale: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('确定要上架选中的商品？', function () {
                $.ajax({
                    type: "POST",
                    url: "../goods/unSale",
                    contentType: "application/json",
                    data: JSON.stringify(id),
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
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../goods/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
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
        getInfo: function (id) {
            $.get("../goods/info/" + id, function (r) {
                vm.goods = r.goods;
                $('#goodsDesc').editable('setHTML', vm.goods.goodsDesc);
                vm.getCategory();
                // vm.getAttributes(vm.goods.attributeCategory);
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        getCategory: function () {
            //加载分类树
            $.get("../category/queryAll", function (r) {
                ztree = $.fn.zTree.init($("#categoryTree"), setting, r.list);
                var node = ztree.getNodeByParam("id", vm.goods.categoryId);
                if (node) {
                    ztree.selectNode(node);
                    vm.goods.categoryName = node.name;
                } else {
                    node = ztree.getNodeByParam("id", 0);
                    ztree.selectNode(node);
                    vm.goods.categoryName = node.name;
                }
            })
        },
        categoryTree: function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择类型",
                area: ['300px', '450px'],
                content: jQuery("#categoryLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.goods.categoryId = node[0].id;
                    vm.goods.categoryName = node[0].name;

                    layer.close(index);
                }
            });
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        handleSuccessPicUrl: function (res, file) {
            vm.goods.primaryPicUrl = file.response.url;
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.goods.listPicUrl = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.goods.primaryPicUrl;
            eyeImage(url);
        },
        eyeImageListPicUrl: function () {
            var url = vm.goods.listPicUrl;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    }
});