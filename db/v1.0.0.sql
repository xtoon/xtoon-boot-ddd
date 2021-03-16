/*
 Source Server Type    : MySQL
 Source Schema         : xtoon-boot
 Date: 01/03/2021 11:27:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `mobile` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `salt` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '盐',
  `password` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `token` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='帐号 ';

-- ----------------------------
-- Records of sys_account
-- ----------------------------
BEGIN;
INSERT INTO `sys_account` VALUES ('1', '18555555555', '525899665@qq.com', 'puJ5DOjCSoP5XiwQwPcq', '443c8b10f626f034240de540655be650635e88c5582d858f0762a42e503b24f4', '562131dd9b337481ac08dd5bbf844a37', '2021-02-28 11:42:34', NULL, '0', '超级管理员', '2021-02-24 13:34:58', '超级管理员', '2021-02-27 23:42:34');
COMMIT;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT 'uuid',
  `code` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统验证码 ';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `user_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `operation` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作',
  `method` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '方法',
  `params` text COLLATE utf8mb4_bin COMMENT '参数',
  `time` bigint(20) DEFAULT NULL COMMENT '执行时长（毫秒）',
  `ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip地址',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属租户',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='日志 ';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `parent_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父级权限',
  `permission_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名称',
  `permission_type` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限类型',
  `permission_level` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限级别',
  `permission_codes` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '授权编码',
  `menu_icon` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `menu_url` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单URL',
  `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态 0启用，1禁用',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单 ';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('0', '-1', '所有菜单', '0', '1', NULL, NULL, NULL, NULL, '0', NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362320253140361218', '0', '系统管理', '0', '1', NULL, 'system', 0, NULL, '0', NULL, '0', 'system', '2021-02-18 16:37:14', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362320893308592129', '1362320253140361218', '用户管理', '1', '1', NULL, 'admin', 0, 'sys/user', '0', NULL, '0', 'system', '2021-02-18 16:39:46', 'system', '2021-02-18 17:07:47');
INSERT INTO `sys_permission` VALUES ('1362321213669531650', '1362320253140361218', '角色管理', '1', '1', '', 'role', 1, 'sys/role', '0', NULL, '0', 'system', '2021-02-18 16:41:03', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362321550283399170', '1362320253140361218', '权限管理', '1', '0', '', 'menu', 2, 'sys/menu', '0', NULL, '0', 'system', '2021-02-18 16:42:23', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362321865267240961', '1362320253140361218', '租户管理', '1', '0', NULL, 'shouye', 3, 'sys/tenant', '0', NULL, '0', 'system', '2021-02-18 16:43:38', '超级管理员', '2021-02-27 14:45:44');
INSERT INTO `sys_permission` VALUES ('1362323350755500033', '1362320253140361218', '日志管理', '1', '1', NULL, 'log', 4, 'sys/log', '0', NULL, '0', 'system', '2021-02-18 16:49:32', '超级管理员', '2021-02-27 15:19:39');
INSERT INTO `sys_permission` VALUES ('1362324153734029314', '1362321550283399170', '所有菜单列表', '2', '0', 'sys:permission:list', NULL, 1, NULL, '0', NULL, '0', 'system', '2021-02-18 16:52:44', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362324379337252865', '1362321550283399170', '菜单选择', '2', '0', 'sys:permission:select', NULL, 2, NULL, '0', NULL, '0', 'system', '2021-02-18 16:53:37', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362324608593715201', '1362321550283399170', '权限详情', '2', '0', 'sys:permission:info', NULL, 3, NULL, '0', NULL, '0', 'system', '2021-02-18 16:54:32', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362324808573935617', '1362321550283399170', '新增', '2', '0', 'sys:permission:save', NULL, 4, NULL, '0', NULL, '0', 'system', '2021-02-18 16:55:20', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362324991982460929', '1362321550283399170', '更新', '2', '0', 'sys:permission:update', NULL, 5, NULL, '0', NULL, '0', 'system', '2021-02-18 16:56:04', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362325121653563393', '1362321550283399170', '删除', '2', '0', 'sys:permission:delete', NULL, 6, NULL, '0', NULL, '0', 'system', '2021-02-18 16:56:34', 'system', '2021-02-18 19:36:42');
INSERT INTO `sys_permission` VALUES ('1362377009644195842', '1362321550283399170', '禁用', '2', '0', 'sys:permission:disable', '22', 0, NULL, '0', NULL, '0', 'system', '2021-02-18 20:22:45', 'system', '2021-02-18 20:24:30');
INSERT INTO `sys_permission` VALUES ('1362755127202639873', '1362321213669531650', '角色分页查询', '2', '1', 'sys:role:list', NULL, 0, NULL, '0', NULL, '0', 'system', '2021-02-19 21:25:16', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362755335118483457', '1362321213669531650', '角色列表', '2', '1', 'sys:role:select', NULL, 1, NULL, '0', NULL, '0', 'system', '2021-02-19 21:26:05', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362755413975592961', '1362321213669531650', '角色信息', '2', '1', 'sys:role:info', NULL, 1, NULL, '0', NULL, '0', 'system', '2021-02-19 21:26:24', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362755490018324481', '1362321213669531650', '保存角色', '2', '1', 'sys:role:save', NULL, 2, NULL, '0', NULL, '0', 'system', '2021-02-19 21:26:42', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362755572402843650', '1362321213669531650', '修改角色', '2', '1', 'sys:role:update', NULL, 3, NULL, '0', NULL, '0', 'system', '2021-02-19 21:27:02', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362755671384223746', '1362321213669531650', '删除角色', '2', '1', 'sys:role:delete', NULL, 4, NULL, '0', NULL, '0', 'system', '2021-02-19 21:27:25', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1362755739877208065', '1362321213669531650', '禁用角色', '2', '1', 'sys:role:disable', NULL, 5, NULL, '0', NULL, '0', 'system', '2021-02-19 21:27:42', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1364445600617373697', '1362323350755500033', '分页查询日志', '2', '1', 'sys:log:list', NULL, 0, NULL, '0', NULL, '0', '超级管理员', '2021-02-24 13:22:36', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1364445923025133570', '1362320893308592129', '用户分页查询', '2', '1', 'sys:user:list', NULL, 0, NULL, '0', NULL, '0', '超级管理员', '2021-02-24 13:23:53', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1364446030516756481', '1362320893308592129', '用户信息', '2', '1', 'sys:user:info', NULL, 1, NULL, '0', NULL, '0', '超级管理员', '2021-02-24 13:24:19', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1364446380040691714', '1362320893308592129', '修改用户', '2', '1', 'sys:user:update', NULL, 2, NULL, '0', NULL, '0', '超级管理员', '2021-02-24 13:25:42', '超级管理员', '2021-02-24 13:31:17');
INSERT INTO `sys_permission` VALUES ('1364446467147997186', '1362320893308592129', '删除用户', '2', '1', 'sys:user:delete', NULL, 4, NULL, '0', NULL, '0', '超级管理员', '2021-02-24 13:26:03', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1364446617924837377', '1362320893308592129', '禁用用户', '2', '1', 'sys:user:disable', NULL, 5, NULL, '0', NULL, '0', '超级管理员', '2021-02-24 13:26:39', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1365315749635231746', '1362320893308592129', '用户保存', '2', '1', 'sys:user:save', '', 0, NULL, '0', NULL, '0', '超级管理员', '2021-02-26 23:00:16', '超级管理员', '2021-02-26 23:01:07');
INSERT INTO `sys_permission` VALUES ('1365562507946668034', '1362321865267240961', '租户分页查询', '2', '0', 'sys:tenant:list', '', 0, NULL, '0', NULL, '0', '超级管理员', '2021-02-27 15:20:47', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('1365670028631916546', '1362321865267240961', '租户禁用', '2', '0', 'sys:tenant:disable', '', 0, NULL, '0', NULL, '0', '测试用户', '2021-02-27 22:28:02', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `role_code` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态 0启用，1禁用',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属租户',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色 ';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'sysAdmin', '系统管理员', '0', '测试', '1', '0', '超级管理员', '2021-02-24 13:34:58', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `role_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色ID',
  `permission_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限ID',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属租户',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色权限';

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `tenant_code` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编码',
  `tenant_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户名称',
  `creator_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者ID',
  `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='租户 ';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_tenant` VALUES ('1', 'xtoon', '享同科技', '1', '0', NULL, '0', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `account_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '帐号ID',
  `user_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `user_type` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户类型',
  `link_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联id',
  `status` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '状态',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属租户',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户 ';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', '1', '超级管理员', NULL, NULL, '0', NULL, '1', '0', '超级管理员', '2021-02-24 13:34:58', '超级管理员1', '2021-02-26 17:11:29');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `user_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色ID',
  `remarks` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属租户',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色 ';

BEGIN;
INSERT INTO `sys_user_role`(`id`, `user_id`, `role_id`, `remarks`, `tenant_id`, `del_flag`, `created_by`, `created_time`, `updated_by`, `updated_time`) VALUES ('1', '1', '1', NULL, '1', '0', '超级管理员', '2021-02-26 17:11:29', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;