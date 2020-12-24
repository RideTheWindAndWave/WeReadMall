/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - we_read_mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`we_read_mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `we_read_mall`;

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  `author` varchar(12) NOT NULL,
  `pubTime` varchar(12) NOT NULL,
  `inventory` varchar(12) NOT NULL,
  `price` double(8,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90753 DEFAULT CHARSET=utf8;

/*Data for the table `books` */

insert  into `books`(`id`,`name`,`author`,`pubTime`,`inventory`,`price`) values (90701,'活着','余华','2017/07','92',28.00),(90702,'袭击面包店','村上春树','2020/06','90',45.00),(90703,'人间失格','太宰治','2013/07','100',29.80),(90704,'了凡四训','袁黄','2017/02','100',15.00),(90705,'人间词话','王国维','2019/09','80',25.00),(90706,'局外人','阿尔贝·加缪','2016/07','89',32.00),(90707,'浮生六记','沈复','2017/04','99',45.00),(90708,'围炉夜话','王永彬','2018/05','90',15.00),(90709,'傲慢与偏见','简·奥斯丁','2018/05','100',49.00),(90710,'故都的秋','郁达夫','2018/06','97',32.00),(90711,'巴山鬼话','魏明伦','2012/05','90',29.00),(90712,'资治通鉴','司马光','2017/09','100',45.00),(90713,'柑橘与柠檬啊','麦克·莫波格','2014/10','100',28.00),(90714,'夜莺与玫瑰','奥斯卡·王尔德','2015/08','60',18.80),(90715,'一看就停不下来的大清','郑云鹏','2020/07','100',52.80),(90716,'文化不苦旅','马伯庸','2015/07','90',42.80),(90717,'大荒纪事','张鸣','2018/08','100',39.80),(90718,'亲爱的阿基米德','玖月晞','2017/04','100',59.80),(90719,'城南旧事','林海音','2014/06','80',18.00),(90720,'茶花女','小仲马','2017/01','187',20.00),(90721,'十一种孤独','理查德耶茨','2012/03','80',35.00),(90722,'浮躁','贾平凹','2018/08','90',48.00),(90723,'复活','尼古拉耶维奇·托尔斯泰','2016/11','200',60.00),(90724,'天人五衰','三岛由纪夫','2014/08','90',45.00),(90725,'奔马','三岛由纪夫','2016/09','100',36.00),(90726,'光与暗的故事','劳伦斯·布洛克','2018/01','100',58.00),(90727,'一个陌生女人的来信','斯茨威格','2006/06','150',37.00),(90728,'济南的冬天','老舍','2018/10','120',26.00),(90729,'瓦尔登湖','亨利·戴维·梭罗','2019/04','200',36.80),(90730,'四世同堂','老舍','2017/01','148',22.80),(90731,'远山在呼唤','植村直己','2014/09','170',32.00),(90732,'上锁的房子','三岛由纪夫','2017/07','190',45.00),(90733,'察势观风','谭徐峰','2020/05','100',55.00),(90734,'青色时代','三岛由纪夫','2018/01','170',26.00),(90735,'戏梦巴黎','吉尔伯特·阿代尔','2018/04','140',38.00),(90736,'复仇小姐','罗纳德·弗雷姆','2015/03','80',38.00),(90737,'卖花女','萧伯纳','2018/01','100',49.00),(90738,'疾病的隐喻','苏珊·桑塔格','2020/07','90',57.00),(90739,'雪日','永井荷风','2015/03','160',22.80),(90740,'飞越疯人院','肯·克西','2015/01','90',39.80),(90741,'死魂灵','果戈里','2016/12','90',22.00),(90742,'赶路人','李小晓','2019/06','80',49.00),(90743,'流动的盛宴','欧内斯特·海明威','2019/07','80',35.00),(90744,'槐园梦忆','梁实秋','2017/12','120',28.00),(90745,'幻灭','巴尔扎克','1978/03','200',69.00),(90746,'在轮下','赫尔曼·黑塞','2019/05','90',42.00),(90747,'荒原狼','赫尔曼·黑塞','2019/05','120',42.00),(90748,'少年维特的烦恼','约翰·沃尔夫冈·冯歌德','2019/04','140',39.80),(90749,'欢喜在人间','汪曾祺','2018/04','170',42.00),(90750,'彩虹','吉本芭娜娜','2018/12','80',38.00),(90751,'计算机网络','谢希仁','2020/06','52',49.00),(90752,'丰乳肥臀','莫言','2020/11','2',29.00);

/*Table structure for table `orderlist` */

DROP TABLE IF EXISTS `orderlist`;

CREATE TABLE `orderlist` (
  `id` varchar(12) NOT NULL DEFAULT '',
  `userId` varchar(12) DEFAULT NULL,
  `orderState` varchar(4) DEFAULT NULL,
  `orderTime` varchar(24) DEFAULT NULL,
  `successfulTime` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderlist_users_fk` (`userId`),
  CONSTRAINT `orderlist_users_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderlist` */

insert  into `orderlist`(`id`,`userId`,`orderState`,`orderTime`,`successfulTime`) values ('214565760912','000004','已支付','2020-12-23 03:36:00',NULL),('598562180381','000004','已完成','2020-12-21 22:49:40','2020-12-22 00:06:21'),('880623850193','000006','已完成','2020-12-22 15:57:30','2020-12-22 16:05:54'),('902466162194','000004','已发货','2020-12-21 23:56:02',NULL);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderlistId` varchar(12) DEFAULT NULL,
  `prodId` int(11) DEFAULT NULL,
  `prodName` varchar(12) DEFAULT NULL,
  `prodPrice` double(8,2) DEFAULT NULL,
  `prodNumber` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`orderlistId`,`prodId`,`prodName`,`prodPrice`,`prodNumber`) values ('598562180381',90701,'活着',28.00,5),('902466162194',90730,'四世同堂',22.80,6),('880623850193',90730,'四世同堂',22.80,6),('880623850193',90701,'活着',28.00,10),('214565760912',90701,'活着',28.00,20);

/*Table structure for table `shoppingcart` */

DROP TABLE IF EXISTS `shoppingcart`;

CREATE TABLE `shoppingcart` (
  `userId` varchar(12) NOT NULL,
  `prodId` int(11) NOT NULL,
  `name` varchar(12) NOT NULL,
  `price` double(8,2) NOT NULL,
  `buyNumber` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`prodId`),
  KEY `shoppingcart_books_fk` (`prodId`),
  CONSTRAINT `shoppingcart_books_fk` FOREIGN KEY (`prodId`) REFERENCES `books` (`id`),
  CONSTRAINT `shoppingcart_users_fk` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shoppingcart` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` varchar(12) NOT NULL,
  `username` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `isAdministrator` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userId`,`username`,`password`,`isAdministrator`) values ('000001','刘恒嘉','123456',1),('000002','刘宝袁','123456',1),('000003','姚舜宇','123456',1),('000004','黄龙龙','123456',0),('000005','王文倩','123456',0),('000006','钱淼淼','123456',0),('000007','杜飞翔','123456',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
