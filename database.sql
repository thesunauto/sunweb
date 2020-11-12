CREATE DATABASE  IF NOT EXISTS `xak2sz2y6k8qfkbb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `xak2sz2y6k8qfkbb`;
-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: xak2sz2y6k8qfkbb
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` varchar(255) NOT NULL,
  `datecreated` datetime NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `idparent` varchar(255) DEFAULT NULL,
  `isdeleted` int(11) NOT NULL,
  `metatitle` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `usercreated` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk__idx` (`usercreated`),
  KEY `fk_idparent_id_idx` (`idparent`),
  CONSTRAINT `fk_idparent_id` FOREIGN KEY (`idparent`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usercreate_username` FOREIGN KEY (`usercreated`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('chungtoi','2020-10-15 17:00:00','Mục lớn',NULL,0,NULL,'Chúng tôi','admin'),('cocautochuc','2020-11-09 10:06:52','sơ đồ tổ chức công ty chúng thôi','chungtoi',0,'','Cơ cấu tổ chức','admin'),('duan','2020-11-09 10:26:51','','hoatdong',0,'','Dự án','admin'),('hoatdong','2020-10-15 17:00:00','Mục lớn',NULL,0,NULL,'Hoạt động','admin'),('hoatdongkinhdoanh','2020-11-09 10:26:44','','hoatdong',0,'','Hoạt động kinh doanh','admin'),('kho','2020-11-09 10:07:35','kho chứa hàng','cocautochuc',1,'','Kho','admin'),('lienhe','2020-11-09 10:05:33','thông tin liên hệ với chúng tôi','chungtoi',0,'','Liên hệ','admin'),('quaemail','2020-11-09 10:09:08','liên hệ qua email','lienhe',0,'','Qua Email','admin'),('test1','2020-11-09 11:06:22','test','quaemail',0,'test','test','admin'),('vitrikho','2020-11-09 10:08:23','Mô tả vị trí kho hàng của chúng tôi','lienhe',0,'','Vị trí kho hàng','admin');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) NOT NULL,
  `datecreated` datetime NOT NULL,
  `datepuliced` datetime DEFAULT NULL,
  `dateupdated` datetime DEFAULT NULL,
  `idcategory` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `isdeleted` int(11) NOT NULL,
  `ispulic` int(11) NOT NULL,
  `isshowindex` int(11) NOT NULL,
  `metatitle` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `usercreated` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usercreated_username_idx` (`usercreated`),
  KEY `fk_category_id_idx` (`idcategory`),
  CONSTRAINT `fk_idcategory_id` FOREIGN KEY (`idcategory`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usercreated_username` FOREIGN KEY (`usercreated`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (43,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,'Vị trí kho hàng vũng tàu','Vị trí kho hàng','admin'),(44,'44.html','2020-11-09 11:46:32','2020-11-09 11:46:32','2020-11-09 11:46:32','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Post 2','admin'),(45,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(46,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(47,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(48,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(49,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(50,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(51,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(52,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(53,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(54,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(55,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(56,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(57,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(58,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(59,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(60,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(61,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(62,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(63,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(64,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(65,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(66,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',1,1,0,NULL,'Vị trí kho hàng','admin'),(67,'43.html','2020-11-09 10:29:51','2020-11-09 10:29:51','2020-11-09 10:29:51','vitrikho','yamaha-r25-do-ngau-loi-voi-dien-mao-cuc-chat-7121-1580894428-5e3a88dcf0b92.jpg',0,1,0,NULL,'Vị trí kho hàng','admin'),(68,'68.html','2020-11-09 15:36:49','2020-11-09 15:36:49','2020-11-09 15:36:49','duan','sss.jfif',0,1,0,NULL,'Bài viết duy nhất','admin'),(69,'69.html','2020-11-09 16:16:32','2020-11-09 16:16:32','2020-11-09 16:16:32','duan','poster_rom_osxp.jpg',1,1,0,NULL,'1 bài viết nữa','admin');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `datecreated` datetime NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `isdelete` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','2020-10-29 14:50:00','thesunautomt@gmail.com',0,'Nhân đẹp trai','123','oifjwoeijf','user'),('admin1111111','2020-10-29 16:59:00','thesunautomt@gmail.com',0,'Nhântest','123','oifjwoeijf','user'),('admin2','2020-10-29 15:10:00','nhanptps09235@fpt.edu.vn',0,'rtyhrtyhtyrety','123123','0987654321','admin'),('adminaq2','2020-10-29 13:45:57','123',0,'213','123','oifjwoeijf','admin'),('employee00000','2020-10-29 15:15:00','thesunautomt@gmail.com',0,'Nhân đẹp trai','123','0987654321','admin'),('employee123','2020-10-29 12:28:00','rtyertye',1,'rtyhrtyhtyrety','qwertyui56u5','rtyretyerty','user'),('Nhan','2020-10-29 13:35:00','nhanptps09235@fpt.edu.vn',1,'Nhân đẹp trai','123','0987654321','user'),('sax1506','2020-10-29 11:48:58','nhanptps09235@gmail2222.com',0,'Phạm Thành Nhân','123','0987654321','admin'),('sax1507','2020-10-15 17:00:00','nhanptps09235@gmai1122l.com',0,'Phạm Thành Nhân Nhân','123','09876543210987654321','user'),('sax1508','2020-10-29 11:49:11','nhanptps09235@gm11ail.com',0,'Phạm Thành Phạm Nhân','123','0987654321','admin'),('sax1509','2020-10-29 11:49:04','nhan11ptps09235@gmail.com',1,'Phạm Thành Thành Nhân','123','0987654321','admin'),('user001','2020-10-29 15:17:00','thesunautomt@gmail.com',1,'Nhân deieieieie','123456','0987654321','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'xak2sz2y6k8qfkbb'
--

--
-- Dumping routines for database 'xak2sz2y6k8qfkbb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-11  9:50:13
