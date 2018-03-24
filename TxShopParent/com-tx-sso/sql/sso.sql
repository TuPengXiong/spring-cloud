/*
SQLyog Trial v12.2.5 (32 bit)
MySQL - 5.7.20-log : Database - sso
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sso` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `sso`;

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `access_token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `access_token_expire_time` datetime DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `authorized_grant_types` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `auto_approve_scopes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_scoped` bit(1) NOT NULL,
  `scopes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_secret_required` bit(1) NOT NULL,
  `refresh_access_token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `refresh_access_token_expire_time` datetime DEFAULT NULL,
  `registered_redirect_uri` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `resource_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_clientId` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `client` */

insert  into `client`(`id`,`client_id`,`client_secret`,`access_token`,`access_token_expire_time`,`authorities`,`authorized_grant_types`,`auto_approve_scopes`,`is_scoped`,`scopes`,`is_secret_required`,`refresh_access_token`,`refresh_access_token_expire_time`,`registered_redirect_uri`,`resource_ids`,`create_time`,`update_time`) values 
(1,'test','test',NULL,NULL,NULL,'authorization_code,password,implicit,refresh_token,client_credentials',NULL,'','test,info','\0',NULL,NULL,'https://baidu.com',NULL,'2017-10-25 11:38:40','2017-10-25 11:38:52');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone_time` datetime DEFAULT NULL,
  `id_card` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `id_card_time` datetime DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `photo_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_username` (`username`),
  UNIQUE KEY `Index_phone` (`phone`),
  UNIQUE KEY `Index_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nickname`,`phone`,`email`,`phone_time`,`id_card`,`id_card_time`,`birthday`,`status`,`photo_url`,`sex`,`create_time`,`update_time`) values 
(1,'u1','$2a$10$73KBfz6Z3aEMm7oNlli6E.6wqEmAX0Y4x/ta/XddpIod8wPJQX2vG','u1','17710482932','511980432@qq.com',NULL,NULL,NULL,NULL,NULL,'https://image.lovesher.com/71d1c3b5ada170327fda35e47995b19d',NULL,'2017-10-08 08:35:00','2017-10-08 08:34:57'),
(2,'u2','$2a$10$73KBfz6Z3aEMm7oNlli6E.6wqEmAX0Y4x/ta/XddpIod8wPJQX2vG','u2','18370960877','294118639@qq.com',NULL,NULL,NULL,NULL,NULL,'https://image.lovesher.com/1dba335cdab5b651d64285a3d89120a1',NULL,'2017-10-12 13:09:47','2017-10-12 13:09:59');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
