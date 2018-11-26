DROP TABLE IF EXISTS `cs_user`;
CREATE TABLE `cs_user` (
  `user_id` bigint(32) AUTO_INCREMENT NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `password` varchar(64) NOT NULL,
  `created_time` varchar(32) NOT NULL COMMENT 'user join time',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `cs_authority`;
CREATE TABLE `cs_authority` (
  `auth_id` bigint(32) NOT NULL,
  `auth_code` varchar(64) NOT NULL,
  `auth_name` varchar(64) NOT NULL,
  `auth_des` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `cs_authority` VALUES ('101', 'AUTH_READ', 'read permission', null);
INSERT INTO `cs_authority` VALUES ('102', 'AUTH_DOWNLOAD', 'download permission', null);
INSERT INTO `cs_authority` VALUES ('103', 'AUTH_UPLOAD', 'upload permission', null);
INSERT INTO `cs_authority` VALUES ('104', 'AUTH_ADMIN', 'admin permission', null);



DROP TABLE IF EXISTS `cs_role`;
CREATE TABLE `cs_role` (
  `role_id` bigint(32) NOT NULL,
  `role_code` varchar(64) NOT NULL,
  `role_name` varchar(64) NOT NULL,
  `role_des` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `cs_role` VALUES ('201', 'ROLE_READ', 'normal people with read permission', null);
INSERT INTO `cs_role` VALUES ('202', 'ROLE_DOWNLOAD', 'people can download', null);
INSERT INTO `cs_role` VALUES ('203', 'ROLE_UPLOAD', 'people can upload pic', null);
INSERT INTO `cs_role` VALUES ('204', 'ROLE_ADMIN', 'administrator', null);


DROP TABLE IF EXISTS `cs_role_auth`;
CREATE TABLE `cs_role_auth` (
  `id` bigint(32) NOT NULL,
  `role_id` bigint(32) NOT NULL,
  `auth_id` bigint(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `cs_role_auth` VALUES ('301', '201', '101');
INSERT INTO `cs_role_auth` VALUES ('302', '202', '102');
INSERT INTO `cs_role_auth` VALUES ('303', '203', '103');
INSERT INTO `cs_role_auth` VALUES ('304', '204', '104');
INSERT INTO `cs_role_auth` VALUES ('305', '204', '103');
INSERT INTO `cs_role_auth` VALUES ('306', '204', '102');
INSERT INTO `cs_role_auth` VALUES ('307', '204', '101');



DROP TABLE IF EXISTS `cs_user_role`;
CREATE TABLE `cs_user_role` (
  `id` bigint(32) AUTO_INCREMENT NOT NULL,
  `user_id` bigint(32) NOT NULL,
  `role_id` bigint(32) NOT NULL,
  `created_time` varchar(32) NOT NULL COMMENT 'user role created time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


