SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_plat
-- ----------------------------
DROP TABLE IF EXISTS `t_plat`;
CREATE TABLE `t_plat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '平台名称',
  `vendor_type` tinyint(4) unsigned NOT NULL COMMENT '厂家类型',
  `access_type` tinyint(4) unsigned NOT NULL COMMENT '接入类型',
  `access_ip_address` varchar(36) NOT NULL COMMENT '接入IP地址',
  `access_port` int(10) unsigned NOT NULL COMMENT '接入端口',
  `login_user` varchar(128) NOT NULL COMMENT '用户名',
  `login_passwd` varchar(64) NOT NULL COMMENT '用户密码',
  `status` tinyint(4) unsigned NOT NULL COMMENT '接入状态',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '平台描述',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_t_plat` (`access_ip_address`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='平台管理表';
