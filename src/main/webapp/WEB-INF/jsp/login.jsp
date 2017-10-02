<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>统一登录页面</title>
    <%@include file="common/pageheader.jsp" %>
</head>
<body class="signin hold-transition login-page">
<div class="loginscreen animated fadeInDown signinpanel" id="loginBox" v-cloak>
    <div class="row" style="color: rgba(255,255,255,.95);">
        <div class="col-md-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>大平台研发公司</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>综合管理平台</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 一个轻量级的Java快速开发平台，能快速开发项目</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 使用当下最流行的Spring+SpringMVC+Mybatis+Shiro技术</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 友好的代码结构及注释，便于阅读及二次开发</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 完善的XSS防范及脚本过滤，彻底杜绝XSS攻击</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 前端使用Vue+iView，上手容易</li>
                </ul>
            </div>
        </div>
        <div class="col-md-5">
            <Card class="m-t text-center" style="background: rgba(109, 109, 109, 0.23);border: 0px solid #dddee1;">
                <p style="padding: 0 20px 20px 20px;">登录到综合管理平台</p>
                <div class="form-group has-feedback">
                    <i-input v-model="userName" @on-enter="login" placeholder="账号" style="width: 200px;" autofocus/>
                </div>
                <div class="form-group has-feedback">
                    <i-input type="password" v-model="passWord" @on-enter="login" style="width: 200px;"
                             placeholder="密码"/>
                </div>
                <div class="row">
                    <i-button type="primary" @click="login" style="width: 200px;">登录</i-button>
                </div>
            </Card>
        </div>
    </div>
    <div class="signup-footer" style="color: rgba(255,255,255,.95);">
        <div class="pull-left">
            2017~${fns:getDate('yyyy')} &copy; 大平台 版权所有. All Rights Reserved.
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var vm = new Vue({
        el: '#loginBox',
        data: {
            userName: '',
            passWord: ''
        },
        methods: {
            login: function (event) {
                var data = "userName=" + vm.userName + "&passWord=" + vm.passWord;
                $.ajax({
                    type: "POST",
                    url: "sys/login",
                    data: data,
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {//登录成功
                            parent.location.href = 'index';
                        } else {
                            iview.Message.error(result.msg);
                        }
                    }
                });
            }
        }
    });
</script>
</html>
