DROP TABLE IF EXISTS `sys_sms_log`;
CREATE TABLE `sys_sms_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` bigint(20) COMMENT '操作人',
  `content` text COMMENT '必填参数。发送内容（1-500 个汉字）UTF-8编码',
  `mobile` text COMMENT '必填参数。手机号码。多个以英文逗号隔开',
  `stime` datetime COMMENT '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送',
  `sign` varchar(32) COMMENT '必填参数。用户签名',
  `type` varchar(32) COMMENT '必填参数。固定值 pt',
  `extno` varchar(255) COMMENT '可选参数。扩展码，用户定义扩展码，只能为数字',
  `send_status` int(11) COMMENT '1成功 0失败',
  `send_id` varchar(32) COMMENT '发送编号',
  `invalid_num` int(11) COMMENT '无效号码数',
  `success_num` int(11) COMMENT '成功提交数',
  `black_num` int(11) COMMENT '黑名单数',
  `return_msg` varchar(50) COMMENT '返回消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO sys_config VALUES ('2', 'SMS_CONFIG_KEY', '{"domain":"http://web.cr6868.com/asmx/smsservice.aspx?","name":"","pwd":"","sign":"","type":1}', 0, '短信配置');

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num, status) VALUES (0, '短信平台', null, null, 0, 'fa fa-television', 9, 0);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num, status) VALUES (375, '短信配置', 'sys/smslog.html', 'sys:smslog:list,sys:smslog:info', 1, 'fa fa-envelope-open', 1, 0);