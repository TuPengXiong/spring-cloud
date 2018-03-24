/*
SQLyog Trial v12.2.5 (32 bit)
MySQL - 5.7.20-log : Database - image
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mq` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `mq`;

/*Table structure for table `tb_image` */


DELIMITER $$

USE `mq`$$

DROP PROCEDURE IF EXISTS `message`$$

CREATE PROCEDURE `message`()
BEGIN
 DECLARE i INT;
		 DECLARE table_name VARCHAR(50); 
		 DECLARE table_pre VARCHAR(50); 
		 DECLARE sql_text VARCHAR(2000); 
		 SET i=0;
		 SET table_name='';
		 SET table_pre='message_';
		 SET sql_text='';
		 WHILE i<128 DO
			SET table_name=CONCAT(table_pre,i);
			
			SET sql_text=CONCAT('CREATE TABLE ', table_name, '(
				      id bigint(20) NOT NULL AUTO_INCREMENT COMMENT \'主键ID\',
					  user_id bigint(20) DEFAULT NULL COMMENT \'用户ID\',
					  username varchar(50) DEFAULT NULL COMMENT \'用户名\',
					  type varchar(255) NOT NULL COMMENT \'消息类型\',
					  status int NOT NULL COMMENT \'状态\',
					  content longtext DEFAULT NULL COMMENT \'内容\',
					  plugin_name varchar(255) DEFAULT NULL COMMENT \'插件名称\',
					  create_time datetime NOT NULL COMMENT \'新增日期\',
					  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT \'更新日期\',
					  PRIMARY KEY (`id`)
					 ) ENGINE=INNODB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8');
				
			SELECT sql_text; 
			SET @sql_text=sql_text;
			PREPARE stmt FROM @sql_text;
			EXECUTE stmt;
			DEALLOCATE PREPARE stmt;  
			SET i=i+1;
		END WHILE;
    END$$

DELIMITER ;


call message();