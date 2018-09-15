/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.20-log : Database - dongyv
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dongyv` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dongyv`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(30) DEFAULT NULL,
  `price` decimal(11,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`name`,`price`) values 
(1,'儿童玩具',113),
(2,'婴儿推车',310),
(3,'飞盘',30),
(4,'狗粮',520);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(8) DEFAULT NULL,
  `flag` int(2) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`user`,`flag`,`price`) values 
(1,'dongyv1',0,NULL),
(28,'111',1,NULL),
(44,'1233',1,NULL),
(45,'夏',1,NULL),
(46,'东',1,NULL),
(47,'东1',0,NULL),
(48,'夏东宇',1,NULL),
(49,'夏东宇1',1,NULL),
(50,'111',1,NULL),
(51,'222',1,NULL),
(52,'2223',1,NULL);

/*Table structure for table `users_account` */

DROP TABLE IF EXISTS `users_account`;

CREATE TABLE `users_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `use_money` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users_account` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
