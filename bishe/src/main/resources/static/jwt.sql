/*
 Navicat Premium Data Transfer

 Source Server         :
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           :
 Source Schema         : jwt

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/01/2021 21:43:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `tieId` int(11) NULL DEFAULT NULL COMMENT '帖子id',
  `commentUserId` int(11) NULL DEFAULT NULL COMMENT '评论人id',
  `commentContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `commentTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评论时间',
  `commentLikes` int(255) NULL DEFAULT NULL COMMENT '点赞次数',
  `commentTypes` int(255) NULL DEFAULT NULL COMMENT '评论类型',
  `commentPicture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论图片',
  `replyCommentId` int(255) NULL DEFAULT NULL COMMENT '回复评论目标id',
  PRIMARY KEY (`commentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小区id',
  `communityName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '小区名',
  `communityAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '小区地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '小区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `complaintId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `complaintContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '投诉内容',
  `complaintTime` datetime(0) NULL DEFAULT NULL COMMENT '投诉提交时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前状态',
  `finishTime` datetime(0) NULL DEFAULT NULL COMMENT '整改时间',
  PRIMARY KEY (`complaintId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '投诉表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` int(255) NULL DEFAULT NULL COMMENT '用户id',
  `itemName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物品名',
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物品数量',
  `itemPicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物品图片',
  `isBuy` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否已购买，0已购买，1未购买',
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '价格',
  `addTime` datetime(0) NULL DEFAULT NULL COMMENT '添加/修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '物品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notify
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `notifierId` int(255) NULL DEFAULT NULL COMMENT '发布者id',
  `receiverId` int(255) NULL DEFAULT NULL COMMENT '接收者id',
  `status` int(255) NULL DEFAULT 0 COMMENT '标记是否已读',
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知所携带数据',
  `notifyTime` datetime(0) NULL DEFAULT NULL COMMENT '通知时间',
  `tieId` int(255) NULL DEFAULT NULL COMMENT '帖子Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `repairId` int(11) NOT NULL AUTO_INCREMENT COMMENT '维修主键id',
  `repairUserId` int(255) NULL DEFAULT NULL COMMENT '维修用户id',
  `repairContent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修内容',
  `repairPicture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `repairTime` datetime(0) NULL DEFAULT NULL COMMENT '维修提交时间',
  `okRepairUserId` int(11) NULL DEFAULT NULL COMMENT '维修人id',
  `okRepairTime` datetime(0) NULL DEFAULT NULL COMMENT '维修时间',
  `repairStatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修状态',
  PRIMARY KEY (`repairId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tie
-- ----------------------------
DROP TABLE IF EXISTS `tie`;
CREATE TABLE `tie`  (
  `tieId` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `userId` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '帖子标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '帖子内容',
  `publishTime` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `browse` int(255) NULL DEFAULT 0 COMMENT '浏览次数',
  `tieTypes` int(255) NULL DEFAULT NULL COMMENT '帖子类型',
  `likes` int(255) NULL DEFAULT 0 COMMENT '点赞次数',
  `tieStatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '帖子属性',
  PRIMARY KEY (`tieId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号码',
  `headUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像地址',
  `communityId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '小区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '门牌号码',
  `power` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `publishStatus` int(255) NULL DEFAULT NULL COMMENT '用户状态，0正常，1被禁言',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户邮件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_comment_user
-- ----------------------------
DROP VIEW IF EXISTS `v_comment_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `v_comment_user` AS select `c`.`commentId` AS `commentId`,`c`.`tieId` AS `tieId`,`c`.`commentUserId` AS `commentUserId`,`c`.`commentContent` AS `commentContent`,`c`.`commentTime` AS `commentTime`,`c`.`commentLikes` AS `commentLikes`,`c`.`commentTypes` AS `commentTypes`,`c`.`commentPicture` AS `commentPicture`,`c`.`replyCommentId` AS `replyCommentId`,`u`.`nickname` AS `nickname`,`u`.`headUrl` AS `headUrl` from (`comment` `c` left join `user` `u` on((`u`.`id` = `c`.`commentUserId`)));

-- ----------------------------
-- View structure for v_complaint_user
-- ----------------------------
DROP VIEW IF EXISTS `v_complaint_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `v_complaint_user` AS select `c`.`complaintId` AS `complaintId`,`c`.`userId` AS `userId`,`c`.`complaintContent` AS `complaintContent`,`c`.`complaintTime` AS `complaintTime`,`c`.`status` AS `status`,`c`.`finishTime` AS `finishTime`,`u1`.`nickname` AS `nickName`,`u1`.`communityId` AS `communityId`,`u1`.`email` AS `userEmail`,`u1`.`tel` AS `userPhone` from (`complaint` `c` left join `user` `u1` on((`c`.`userId` = `u1`.`id`)));

-- ----------------------------
-- View structure for v_items_user
-- ----------------------------
DROP VIEW IF EXISTS `v_items_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `v_items_user` AS select `i`.`id` AS `id`,`i`.`userId` AS `userId`,`i`.`itemName` AS `itemName`,`i`.`itemNum` AS `itemNum`,`i`.`itemPicture` AS `itemPicture`,`i`.`isBuy` AS `isBuy`,`i`.`money` AS `money`,`i`.`addTime` AS `addTime`,`u`.`nickname` AS `nickname`,`u`.`communityId` AS `communityId`,`u`.`address` AS `address`,`u`.`headUrl` AS `headUrl` from (`items` `i` left join `user` `u` on((`u`.`id` = `i`.`userId`)));

-- ----------------------------
-- View structure for v_replace_user
-- ----------------------------
DROP VIEW IF EXISTS `v_replace_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `v_replace_user` AS select `r`.`repairId` AS `repairId`,`r`.`repairUserId` AS `repairUserId`,`u1`.`nickname` AS `nickname`,`u1`.`communityId` AS `communityId`,`r`.`repairContent` AS `repairContent`,`r`.`repairPicture` AS `repairPicture`,`u1`.`address` AS `homeId`,`u1`.`tel` AS `repairPhone`,`u1`.`email` AS `repairEmail`,`r`.`repairTime` AS `repairTime`,`r`.`okRepairUserId` AS `okRepairUserId`,`u2`.`nickname` AS `workerName`,`r`.`okRepairTime` AS `okRepairTime`,`r`.`repairStatus` AS `repairStatus` from ((`repair` `r` left join `user` `u1` on((`u1`.`id` = `r`.`repairUserId`))) left join `user` `u2` on((`u2`.`id` = `r`.`okRepairUserId`)));

-- ----------------------------
-- View structure for v_tie_user
-- ----------------------------
DROP VIEW IF EXISTS `v_tie_user`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`%` SQL SECURITY DEFINER VIEW `v_tie_user` AS select `t`.`tieId` AS `id`,`t`.`userId` AS `userId`,`t`.`title` AS `title`,`t`.`content` AS `content`,`t`.`publishTime` AS `publishTime`,`t`.`picture` AS `picture`,`t`.`browse` AS `browse`,`t`.`tieTypes` AS `tieTypes`,`t`.`likes` AS `likes`,`t`.`tieStatus` AS `tieStatus`,`u`.`nickname` AS `nickname`,`u`.`headUrl` AS `headUrl`,`u`.`communityId` AS `communityId` from (`tie` `t` left join `user` `u` on((`t`.`userId` = `u`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
