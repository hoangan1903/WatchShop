SET NAMES utf8 ;

DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id_banner` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id_banner`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
INSERT INTO `banner` VALUES (1,'2019-05-02 16:24:42',NULL,'https://donghocasio.vn/wp-content/uploads/2016/04/astron-banner.jpg'),(2,'2019-05-02 16:25:32',NULL,'https://media.licdn.com/dms/image/C5112AQEge5XaAe0-9w/article-inline_image-shrink_400_744/0?e=1561593600&v=beta&t=gYawwUeXL3EPpZroPcK_QOoGUqkP6S5_jBCNNvvHw3Q'),(3,'2019-05-02 16:25:55',NULL,'https://shopdonghothuysy.com/template/uploads/2015/08/Banner_donghoonline_7_1.jpg');

DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` varchar(255) NOT NULL,
  `data` longblob,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES (74,'admin','$2a$10$IvV9blYcBOjirpoghTowPO0h4A/GcmfLtMzCepbkhWHTeiR6ozUYK','admin@gmail.com','2019-04-13 12:35:37','2019-04-13 12:35:37'),(75,'employee','$2a$10$F8qM5Y5DvOmt3hVtOgyRA.C/KnQz6YRAmR0rYJ.gNW7J3xRzxoO0i','employee@gmail.com','2019-04-13 12:40:07','2019-04-13 12:40:07'),(76,'1','$2a$10$rN7Yay76wsgs2v8RQf3kLOidJrAvyb1GamgbHdp9ylVpuK3h4J6g.','sdsdsdsds','2019-04-13 12:52:52','2019-04-13 12:52:52'),(78,'2','$2a$10$YPA3kTvD6w1q.EuG8cPeVemRR7AmRYCizd/nuaR2831Oy1iubK04a','sdsdsdsds','2019-04-13 22:39:29','2019-04-13 22:39:29'),(79,'3','$2a$10$sY8n9pyOxS18bUwcQuKCze3ZCRl6azJ2rKVNt3FF7KcbaKs/TJgey','3@gmail.com','2019-04-13 22:54:46','2019-04-13 22:54:46'),(80,'4','$2a$10$jebaddUxcILSLJZnh7aD8eSvf5.5YRh8WhS6k2GXdjEsNOvTnG.F6','sdsdsdsds','2019-04-13 23:06:22','2019-04-13 23:06:22'),(81,'5','$2a$10$HGmfPdyEjGdpiLEGScYHAeoTdp6WooXRNhw/sVKHq87hgQnu.Smw2','3@gmail.com','2019-04-13 23:11:48','2019-04-13 23:11:48'),(82,'customer','$2a$10$UnjybIC6NCEieUZYjOF9x.5rqGSbgy3vvc8LKPXFHPerm7JkX1zq.','customer','2019-04-13 23:13:41','2019-04-13 23:13:41'),(84,'customer1','$2a$10$B62/Dj6prL4krUaHU52N.u46CU9K23cYUgB1QoSi47b/T7Ntp5YK.','3@gmail.com','2019-04-13 23:19:35','2019-04-13 23:19:35'),(85,'','$2a$10$kNTO7PehPbQjON9fSwOOOe1yq.JsLw.7fEfY7l4A1m6gAxf5oHG/a','','2019-04-13 23:22:05','2019-04-13 23:22:05'),(86,'s','$2a$10$J.yhgNlC7LjqJjIQG2crSuHw5vl/Tn6HCVQBfi4lnBSbEdBeuDiF6','','2019-04-13 23:28:16','2019-04-13 23:28:16'),(87,'sssss','$2a$10$OObufLE/ymraagITTv6w4uFBgBH1MKC7xZJo9g7XTbjx59RxxDo4u','','2019-04-13 23:28:29','2019-04-13 23:28:29'),(96,'ssasasasasa','$2a$10$rnmBgkHhVD8xWpkZTQ05jOi1HYRZT9RzCVRo1jpGFoMQOJYOHVGTe','ssssss','2019-04-13 23:31:23','2019-04-13 23:31:23'),(97,'ssasasasasaddd','$2a$10$CsUKXQRUm.GzXbvVkm0OK.e1QKtMcXIHRbMmYKgcgZ/zuu3rZHhU2','ssssss','2019-04-13 23:31:49','2019-04-13 23:31:49'),(98,'sdsds','$2a$10$TCKDUbl8KTfkkGG4SPRXYeYwSRq/ONeuPRZ8/qtqpgnVPrdKpm1gO','sdsds','2019-04-13 23:43:02','2019-04-13 23:43:02'),(99,'sdsdsdsdsdsds','$2a$10$mcItpkZMp1uzZjs5N8hObeR4ataLKacDFNL6NfBVFzyPKQCW1oRMe','3@gmail.com','2019-04-13 23:43:48','2019-04-13 23:43:48'),(100,'final','$2a$10$J.3vCsdCV4/1MakzrvQUn.IWhwaBa1UFKCFg6Jyeyp/QBYqadK7CK','danhy989@gmail.com','2019-04-14 00:03:11','2019-04-14 00:03:11'),(101,'1213131','$2a$10$47cPkXQNnpUelgldXCmHRO1.FxKWh6.n17/QgGMEOxvS.t6TSkvBK','danhy989@gmail.com','2019-04-14 00:05:30','2019-04-14 00:05:30'),(102,'1313131313','$2a$10$4pUS.We/m5MbedpaS5nnbuWWszd4XOyxB76O4ESTbawyoLMLTswYK','danhy989@gmail.com','2019-04-14 00:05:56','2019-04-14 00:05:56'),(103,'sdadss','$2a$10$qFaZ2aa57mUB1IuIaxMS1u4OfDXDDhRpnNmvvgIVrLz5e/dc4KJp2','danhy989@gmail.com','2019-04-14 00:15:10','2019-04-14 00:15:10'),(104,'sdsdsdsadsa','$2a$10$0L9hK0M7dyWtPMvhOr8c3.TtfQs7/qm7jevrbQqCJ1hxtuWeRcf6e','danhy989@gmail.com','2019-04-14 00:15:39','2019-04-14 00:15:39'),(105,'sdsdsdsdssds','$2a$10$bt0.he/GUE1M1ThmxT93l.zdMNJS4HHYsksqkJQoqBFeSIG3l61k6','danhy989@gmail.com','2019-04-14 00:19:51','2019-04-14 00:19:51'),(108,'manager1212','$2a$10$gVpxnwb8fbk7TaOPnjXU5ueRcTy1USJRfZM9PmCEFLU9dSQKCHV.S','danhy989@gmail.com','2019-04-16 01:35:46','2019-04-16 01:35:46'),(109,'manager','$2a$10$YzY.FzDqmfgNRpF8ZHetgOzPZtGTlkDDV4FJnV52kRp4EC2Z9OGRy','manager@gmail.com','2019-04-19 14:59:40','2019-04-19 14:59:40'),(110,'test','$2a$10$ZznLCPEj2iVtVmgXHTYBgu/4Sq/zz7Rz0g302VyQIG1hH/7E0u2Fu','danhy989@gmail.com','2019-04-20 15:00:18','2019-04-20 15:00:18');


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
INSERT INTO `role` VALUES (1,'admin'),(2,'manager'),(3,'employee'),(4,'customer');


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role`),
  CONSTRAINT `FK2aam9nt2tv8vcfymi3jo9c314` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `FKfhxaael2m459kbk8lv8smr5iv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user_role` VALUES (74,1),(109,2),(75,3),(76,4),(78,4),(79,4),(80,4),(81,4),(82,4),(84,4),(85,4),(86,4),(87,4),(96,4),(97,4),(98,4),(99,4),(100,4),(101,4),(102,4),(103,4),(104,4),(105,4),(108,4),(110,4);


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birthdate` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `FKdtvbvnxc0q7vsp1ev909vrtx6` (`id_user`),
  CONSTRAINT `FKdtvbvnxc0q7vsp1ev909vrtx6` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
INSERT INTO `employee` VALUES (9,'employee','male',1998,75);

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id_customer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_customer`),
  KEY `FKl4ks6xjcbcnsionlv8stck4c1` (`id_user`),
  CONSTRAINT `FKl4ks6xjcbcnsionlv8stck4c1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
INSERT INTO `customer` VALUES (1,'sdsad','0121212','sdsds',76),(2,'sdsad',NULL,NULL,78),(3,'3',NULL,NULL,79),(4,'sdsad',NULL,NULL,80),(5,'5',NULL,NULL,81),(6,'customer',NULL,NULL,82),(8,'3',NULL,NULL,84),(9,'',NULL,NULL,85),(10,'',NULL,NULL,86),(11,'',NULL,NULL,87),(12,'ssss',NULL,NULL,96),(13,'ssss',NULL,NULL,97),(14,'dsds',NULL,NULL,98),(15,'sdsdsdsds',NULL,NULL,99),(16,'duy anh',NULL,NULL,100),(17,'12121',NULL,NULL,101),(18,'13131',NULL,NULL,102),(19,'asdasd',NULL,NULL,103),(20,'3',NULL,NULL,104),(21,'sds',NULL,NULL,105),(22,'sấ',NULL,NULL,108),(23,'test',NULL,NULL,110);

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id_cart` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `id_customer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cart`),
  KEY `FK5k8xccwxat02pgimx7bhmkkme` (`id_customer`),
  CONSTRAINT `FK5k8xccwxat02pgimx7bhmkkme` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `cart` VALUES (1,0,6);

DROP TABLE IF EXISTS `firm`;
CREATE TABLE `firm` (
  `id_firm` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_firm`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
INSERT INTO `firm` VALUES (1,'Citizen'),(2,'Ogival'),(3,'Elixa'),(4,'Bulova'),(5,'OP'),(6,'Orient'),(7,'Seiko');

DROP TABLE IF EXISTS `origin`;
CREATE TABLE `origin` (
  `id_origin` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_origin`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
INSERT INTO `origin` VALUES (1,'Japan'),(2,'South Korea'),(3,'China'),(4,'Viet Nam'),(5,'Thailand'),(6,'Singapore'),(7,'America'),(8,'Swiss');


DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `id_model` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_model`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `model` VALUES (1,'Quartz'),(2,'Eco-Drive'),(3,'Cơ'),(4,'Automatic');


DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE `product_detail` (
  `id_product_detail` int(11) NOT NULL AUTO_INCREMENT,
  `size` int(4) DEFAULT NULL,
  `case_material` varchar(45) DEFAULT NULL,
  `chain_material` varchar(45) DEFAULT NULL,
  `glass_material` varchar(45) DEFAULT NULL,
  `water_resistance` int(4) DEFAULT NULL,
  `other_function` varchar(45) DEFAULT NULL,
  `insurance` int(1) DEFAULT NULL,
  `id_origin` int(11) NOT NULL,
  `id_model` int(11) NOT NULL,
  PRIMARY KEY (`id_product_detail`),
  KEY `FKcridlmc61cwcktec2poptvrup` (`id_model`),
  KEY `FK8hjsfqnk2o75yhv13rvxh2t2b` (`id_origin`),
  CONSTRAINT `FK8hjsfqnk2o75yhv13rvxh2t2b` FOREIGN KEY (`id_origin`) REFERENCES `origin` (`id_origin`),
  CONSTRAINT `FKcridlmc61cwcktec2poptvrup` FOREIGN KEY (`id_model`) REFERENCES `model` (`id_model`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;
INSERT INTO `product_detail` VALUES (149,40,'Thép không gỉ ','Dây da','Kính Sapphire',150,'Giờ,phút,giây',5,1,3),(150,43,'Thép không gỉ ','Thép không gỉ ','Mặt kính cứng',100,'Giờ,phút,giây',5,1,2),(151,43,'Thép không gỉ ','Thép không gỉ ','Mặt kính cứng',100,'Giờ,phút,giây',5,1,2),(152,43,'Thép không gỉ ','Dây da','Mặt kính cứng',100,'Giờ,phút,giây',5,1,2),(153,40,'Thép không gỉ mạ vàng','Dây da','Mặt kính cứng',100,'Giờ,phút,giây',5,1,2),(154,42,'Thép không gỉ mạ vàng','Dây da','Mặt kính cứng',100,'Giờ,phút,giây',5,1,2),(155,39,'Thép không gỉ ','Thép không gỉ ','Mặt kính cứng',50,'Lịch ngày, lịch thứ',5,1,1),(156,41,'Thép không gỉ mạ vàng','Dây da','Mặt kính cứng',50,'Lịch ngày, lịch thứ',5,1,1),(157,40,'Thép không gỉ ','Dây da','Sapphire',100,'Giờ,phút,giây',4,8,4),(158,40,'Thép không gỉ ','Thép không gỉ ','Sapphire',120,'Giờ,phút,giây',4,8,4),(159,40,'Thép không gỉ ','Dây da','Sapphire',100,'Giờ,phút,giây',4,8,4),(160,40,'Thép không gỉ ','Thép không gỉ ','Sapphire',100,'Giờ,phút,giây',4,8,4),(161,40,'Thép không gỉ ','Dây da','Sapphire',100,'Giờ,phút,giây',4,8,4),(162,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Sapphire',100,'Giờ,phút,giây',5,8,4),(163,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Sapphire',100,'Giờ,phút,giây',5,8,4),(164,41,'Thép không gỉ mạ vàng','Dây da','Mặt kính cứng',50,'Lịch ngày, lịch thứ',5,8,4),(165,41,'Thép không gỉ mạ vàng','Dây da','Mặt kính cứng',50,'Lịch ngày, lịch thứ',5,8,4),(166,41,'Thép không gỉ mạ vàng','Dây da','Mặt kính cứng',50,'Lịch ngày, lịch thứ',5,8,4),(167,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Mineral Crystal',100,'Giờ,phút,giây',4,8,1),(168,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Mineral Crystal',100,'Giờ,phút,giây',4,8,1),(169,41,'Thép không gỉ mạ vàng','Dây da','Mineral Crystal',100,'Giờ,phút,giây',4,8,1),(170,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Mineral Crystal',100,'Giờ,phút,giây',4,8,1),(171,41,'Thép không gỉ mạ vàng','Dây da','Mineral Crystal',100,'Giờ,phút,giây',4,8,1),(172,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Mineral Crystal',100,'Giờ,phút,giây',4,8,1),(173,41,'Thép không gỉ mạ vàng','Thép không gỉ mạ vàng','Mineral Crystal',100,'Giờ,phút,giây',4,8,1);


DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id_image` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `id_product_detail` int(11) NOT NULL,
  PRIMARY KEY (`id_image`),
  KEY `FKs4kpgowlj28nnxbw7r2gk3m9r` (`id_product_detail`),
  CONSTRAINT `FKs4kpgowlj28nnxbw7r2gk3m9r` FOREIGN KEY (`id_product_detail`) REFERENCES `product_detail` (`id_product_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=452 DEFAULT CHARSET=utf8;
INSERT INTO `image` VALUES (394,'https://donghoduyanh.com/upload/images/dong-ho-citizen-np1010-01l_6.jpg',149),(395,'https://donghoduyanh.com/upload/images/dong-ho-citizen-np1010-01l_7.jpg',149),(396,'https://donghoduyanh.com/upload/images/dong-ho-citizen-np1010-01l_9.jpg',149),(397,'https://donghoduyanh.com/upload/images/dong-ho-citizen-np1010-01l_6.jpg',149),(398,'https://donghoduyanh.com/upload/images/dong-ho-citizen-bi5000-87a_8.jpg',155),(399,'https://donghoduyanh.com/upload/images/dong-ho-citizen-bi5000-87a_9.jpg',155),(400,'https://donghoduyanh.com/upload/images/dong-ho-citizen-bi5000-87a_7.jpg',155),(401,'https://donghoduyanh.com/upload/images/dong-ho-citizen-bi5000-87a_6.jpg',155),(402,'https://cdn3.dhht.vn/wp-content/uploads/2017/10/Ogival-388-67JAGSR-T-5-399x399.jpg',158),(403,'https://cdn3.dhht.vn/wp-content/uploads/2017/10/7_388-67JAGSR-T-399x399.jpg',158),(404,'https://cdn3.dhht.vn/wp-content/uploads/2017/10/Ogival-388-67JAGSR-T-4-399x399.jpg',158),(405,'https://cdn3.dhht.vn/wp-content/uploads/2017/10/Ogival-388-67JAGSR-T-2-399x399.jpg',158),(406,'https://cdn3.dhht.vn/wp-content/uploads/2017/10/Ogival-388-67JAGSR-T-3-399x399.jpg',158),(407,'https://cdn3.dhht.vn/wp-content/uploads/2016/02/box-ogival-399x399.jpg',159),(408,'https://cdn3.dhht.vn/wp-content/uploads/2018/09/637_358-31AGR-GL-T-1-399x399.jpg',159),(409,'https://cdn3.dhht.vn/wp-content/uploads/2017/12/6_1930AGSR-T-1-399x399.jpg',160),(410,'https://cdn3.dhht.vn/wp-content/uploads/2016/02/box-ogival-399x399.jpg',160),(411,'https://cdn3.dhht.vn/wp-content/uploads/2016/02/box-ogival-399x399.jpg',161),(412,'https://cdn3.dhht.vn/wp-content/uploads/2018/09/676_1550-13AGR-GL-N-1-399x399.jpg',161),(413,'https://cdn3.dhht.vn/wp-content/uploads/2017/12/241_FAG03001W0-399x399.jpg',162),(414,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Box-Orient1-399x399.jpg',162),(415,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Orient-SDE00002W0-2-399x399.jpg',163),(416,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Orient-SDE00002W0-3-399x399.jpg',163),(417,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Orient-SDE00002W0-4-399x399.jpg',163),(418,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Orient-SDE00002W0-1-399x399.jpg',163),(419,'https://cdn3.dhht.vn/wp-content/uploads/2017/12/ORIENT-FAC0000BW0-0-399x399.jpg',164),(420,'https://cdn3.dhht.vn/wp-content/uploads/2017/12/ORIENT-FAC0000BW0-1-399x399.jpg',164),(421,'https://cdn3.dhht.vn/wp-content/uploads/2018/03/ORIENT-FUNG2005D0-2-399x399.jpg',165),(422,'https://cdn3.dhht.vn/wp-content/uploads/2018/03/ORIENT-FUNG2005D0-4-399x399.jpg',165),(423,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Box-Orient1-399x399.jpg',165),(424,'https://cdn3.dhht.vn/wp-content/uploads/2018/03/ORIENT-FUNG2005D0-3-399x399.jpg',165),(425,'https://cdn3.dhht.vn/wp-content/uploads/2019/04/40_RA-AG0004B10B-399x399.jpg',166),(426,'https://cdn3.dhht.vn/wp-content/uploads/2014/11/Box-Orient1-399x399.jpg',166),(427,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/bulova-box-399x399.jpg',167),(428,'https://cdn3.dhht.vn/wp-content/uploads/2018/09/628_96C105-399x399.jpg',167),(429,'https://cdn3.dhht.vn/wp-content/uploads/2017/11/Bulova-98A166-5-399x399.jpg',168),(430,'https://cdn3.dhht.vn/wp-content/uploads/2017/11/Bulova-98A166-2-399x399.jpg',168),(431,'https://cdn3.dhht.vn/wp-content/uploads/2017/11/Bulova-98A166-1-399x399.jpg',168),(432,'https://cdn3.dhht.vn/wp-content/uploads/2017/09/2_98A166-399x399.jpg',168),(433,'https://cdn3.dhht.vn/wp-content/uploads/2017/11/Bulova-98A166-3-399x399.jpg',168),(434,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/bulova-box-399x399.jpg',169),(435,'https://cdn3.dhht.vn/wp-content/uploads/2017/06/10_97A123-399x399.jpg',169),(436,'https://cdn3.dhht.vn/wp-content/uploads/2016/06/39_97A123-399x399.jpg',169),(437,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/bulova-box-399x399.jpg',170),(438,'https://cdn3.dhht.vn/wp-content/uploads/2018/12/28_97D115-399x399.jpg',170),(439,'https://cdn3.dhht.vn/wp-content/uploads/2017/06/16_98A165-1-399x399.jpg',171),(440,'https://cdn3.dhht.vn/wp-content/uploads/2017/08/Bulova-98A165-2-399x399.jpg',171),(441,'https://cdn3.dhht.vn/wp-content/uploads/2017/08/16_98A165-399x399.jpg',171),(442,'https://cdn3.dhht.vn/wp-content/uploads/2017/08/Bulova-98A165-1-399x399.jpg',171),(443,'https://cdn3.dhht.vn/wp-content/uploads/2017/08/Bulova-98A165-6-399x399.jpg',171),(444,'https://cdn3.dhht.vn/wp-content/uploads/2017/07/Bulova-98R227-1-399x399.jpg',172),(445,'https://cdn3.dhht.vn/wp-content/uploads/2017/07/19_98R227-399x399.jpg',172),(446,'https://cdn3.dhht.vn/wp-content/uploads/2017/07/Bulova-98R227-4-399x399.jpg',172),(447,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/13_96B215-399x399.jpg',173),(448,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/BULOVA-96B215-0-399x399.jpg',173),(449,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/Bulova-96B215-3-399x399.jpg',173),(450,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/BULOVA-96B215-2-399x399.jpg',173),(451,'https://cdn3.dhht.vn/wp-content/uploads/2015/09/BULOVA-96B215-1-399x399.jpg',173);


DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `create_at` date DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  `id_product_detail` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `FKkqe57pm12q47yk5b3mnbfi0ek` (`id_customer`),
  KEY `FKfp3xnuvspq4gucjf494urfqi3` (`id_product_detail`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(45) NOT NULL,
  `image` mediumtext NOT NULL,
  `price` double NOT NULL,
  `available` int(3) NOT NULL,
  `id_firm` int(11) NOT NULL,
  `id_product_detail` int(11) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `FK5f4ibcghig5y7cruslir1yum1` (`id_firm`),
  KEY `FK6llohb4k4u6kjoipyofhsat89` (`id_product_detail`),
  CONSTRAINT `FK5f4ibcghig5y7cruslir1yum1` FOREIGN KEY (`id_firm`) REFERENCES `firm` (`id_firm`),
  CONSTRAINT `FK6llohb4k4u6kjoipyofhsat89` FOREIGN KEY (`id_product_detail`) REFERENCES `product_detail` (`id_product_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;
INSERT INTO `product` VALUES (147,'NP1010-01L','https://s3-ap-southeast-1.amazonaws.com/pnj-watch-images/3807/1551586923677dong-ho-citizen-nam-np1010.01l-day-da-40mm_1024x1024.png',12200000,1,1,149),(148,'CA4420-81L','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%93ng%20H%E1%BB%93%20Citizen%20CA4420-81L.jpg',6615000,1,1,150),(149,'CA4420-81E','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%93ng%20H%E1%BB%93%20Citizen%20CA4420-81E.jpg',6615000,1,1,151),(150,'CA4420-13L','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%93ng%20H%E1%BB%93%20Citizen%20CA4420-13L.jpg',6480000,1,1,152),(151,'AT2393-17H','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%93ng%20H%E1%BB%93%20Citizen%20AT2393-17H.jpg',6930000,1,1,153),(152,'CA7008-11E','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%92NG%20H%E1%BB%92%20CITIZEN%20CA7008-11E.4.26.1.jpg',7326000,1,1,154),(153,'BI5000-87A','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%92NG%20H%E1%BB%92%20CITIZEN%20BI5000-87A.4.26.1.jpg',2178000,1,1,155),(154,'BF2003-25A','https://donghoduyanh.com/upload/images/%C4%90%E1%BB%93ng%20H%E1%BB%93%20Citizen%20BF2003-25A.jpg',3195000,1,1,156),(155,'358-612AGR-GL-T','https://cdn3.dhht.vn/wp-content/uploads/2018/09/638_358-612AGR-GL-T-399x399.jpg',18600000,1,2,157),(156,'388-67JAGSR-T','https://cdn3.dhht.vn/wp-content/uploads/2017/10/7_388-67JAGSR-T-1-399x399.jpg',23990000,1,2,158),(157,'358-31AGR-GL-T','https://cdn3.dhht.vn/wp-content/uploads/2018/09/637_358-31AGR-GL-T-399x399.jpg',24000000,1,2,159),(158,'1930AGSR-T','https://cdn3.dhht.vn/wp-content/uploads/2017/12/6_1930AGSR-T-399x399.jpg',19000000,1,2,160),(159,'1550-13AGR-GL-N','https://cdn3.dhht.vn/wp-content/uploads/2018/09/676_1550-13AGR-GL-N-399x399.jpg',31450000,1,2,161),(160,'FAG03001W0','https://cdn3.dhht.vn/wp-content/uploads/2017/12/241_FAG03001W0-399x399.jpg',7500000,1,6,162),(161,'SDE00002W0','https://cdn3.dhht.vn/wp-content/uploads/2014/11/6488-SDE00002W0-399x399.jpg',17500000,1,6,163),(162,'FAC0000BW0','https://cdn3.dhht.vn/wp-content/uploads/2017/12/238_FAC0000BW0-399x399.jpg',5500000,1,6,164),(163,'FUNG2005D0','https://cdn3.dhht.vn/wp-content/uploads/2018/03/FUNG2005D0-399x399.jpg',3195000,1,6,165),(164,'RA-AG0004B10B','https://cdn3.dhht.vn/wp-content/uploads/2019/04/40_RA-AG0004B10B-399x399.jpg',2500000,1,6,166),(165,'96C105','https://cdn3.dhht.vn/wp-content/uploads/2018/09/628_96C105-699x699.jpg',8500000,1,4,167),(166,'98A166','https://cdn3.dhht.vn/wp-content/uploads/2017/09/2_98A166-399x399.jpg',13300000,1,4,168),(167,'97A123','https://cdn3.dhht.vn/wp-content/uploads/2016/06/39_97A123-399x399.jpg',6100000,1,4,169),(168,'97D115','https://cdn3.dhht.vn/wp-content/uploads/2018/12/28_97D115-399x399.jpg',8500000,1,4,170),(169,'98A165','https://cdn3.dhht.vn/wp-content/uploads/2017/06/16_98A165-1-399x399.jpg',12000000,1,4,171),(170,'98R227','https://cdn3.dhht.vn/wp-content/uploads/2017/07/19_98R227-399x399.jpg',15000000,1,4,172),(171,'96B215','https://cdn3.dhht.vn/wp-content/uploads/2015/09/13_96B215-399x399.jpg',5600000,1,4,173);


DROP TABLE IF EXISTS `order_status`;
CREATE TABLE `order_status` (
  `id_order_status` int(11) NOT NULL AUTO_INCREMENT,
  `order_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_order_status`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `order_status` VALUES (1,'unconfirmed'),(2,'confirmed'),(3,'paid'),(4,'unpaid');


DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id_payment` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_payment`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `payment` VALUES (1,'Thanh toán khi nhận hàng'),(2,'Thanh toán tại cửa hàng');


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `price` double DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  `id_order_status` int(11) DEFAULT NULL,
  `id_payment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `FKm3ritehtix5ub7jte0bxu41ik` (`id_customer`),
  KEY `FKbp6k71rsko970ooke8me82k9` (`id_order_status`),
  KEY `FK5phng0rr9yex7v321tef65svq` (`id_payment`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `amount` int(11) DEFAULT NULL,
  `id_product` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  PRIMARY KEY (`id_product`,`id_order`),
  KEY `FKsta0q8hk1lt2vdu92u4e5vr4a` (`id_order`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cart_detail`;
CREATE TABLE `cart_detail` (
  `amount` int(11) DEFAULT NULL,
  `id_product` int(11) NOT NULL,
  `id_cart` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id_product`,`id_cart`),
  KEY `FKh5pbblis7iw2kog1cponwqb9c` (`id_cart`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `id_alert` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `create_at` date NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_alert`),
  KEY `FK16kljslgihg3kjciuq3834w27` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
INSERT INTO `alert` VALUES (1,'abcds','2019-04-11',1,74),(2,'sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss','2019-04-10',1,74),(3,'sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss','2019-04-13',1,74),(4,'sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss','2019-04-10',1,74);