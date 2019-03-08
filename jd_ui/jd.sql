-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: jd
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `login_info`
--

DROP TABLE IF EXISTS `login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `login_time` timestamp(6) NOT NULL,
  `login_ip` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_info`
--

LOCK TABLES `login_info` WRITE;
/*!40000 ALTER TABLE `login_info` DISABLE KEYS */;
INSERT INTO `login_info` VALUES (1,1,'2018-12-25 18:43:57.841000','0:0:0:0:0:0:0:1'),(2,1,'2018-12-31 04:55:58.525000','127.0.0.1'),(3,1,'2018-12-31 04:57:45.507000','0:0:0:0:0:0:0:1'),(4,1,'2018-12-31 05:24:23.086000','0:0:0:0:0:0:0:1'),(5,1,'2018-12-31 05:26:50.178000','0:0:0:0:0:0:0:1'),(6,1,'2018-12-31 05:36:44.057000','0:0:0:0:0:0:0:1'),(7,1,'2018-12-31 05:56:23.515000','0:0:0:0:0:0:0:1'),(8,1,'2018-12-31 06:07:01.053000','0:0:0:0:0:0:0:1'),(9,1,'2018-12-31 06:07:19.414000','0:0:0:0:0:0:0:1'),(10,1,'2018-12-31 06:08:24.089000','0:0:0:0:0:0:0:1'),(11,1,'2018-12-31 07:29:28.367000','0:0:0:0:0:0:0:1'),(12,1,'2019-01-12 20:15:20.607000','0:0:0:0:0:0:0:1'),(13,2,'2019-03-02 03:36:08.129000','0:0:0:0:0:0:0:1'),(14,5,'2019-03-02 03:38:33.749000','0:0:0:0:0:0:0:1'),(15,10,'2019-03-02 03:40:52.113000','0:0:0:0:0:0:0:1'),(16,2,'2019-03-02 09:39:05.586000','0:0:0:0:0:0:0:1'),(17,10,'2019-03-02 09:43:51.370000','0:0:0:0:0:0:0:1'),(18,10,'2019-03-02 09:44:45.941000','0:0:0:0:0:0:0:1'),(19,1,'2019-03-02 21:33:49.144000','0:0:0:0:0:0:0:1'),(20,1,'2019-03-04 03:27:26.760000','0:0:0:0:0:0:0:1'),(21,1,'2019-03-04 22:23:19.203000','0:0:0:0:0:0:0:1'),(22,1,'2019-03-05 00:59:50.676000','0:0:0:0:0:0:0:1'),(23,1,'2019-03-06 00:32:49.829000','0:0:0:0:0:0:0:1'),(24,1,'2019-03-06 01:06:59.846000','0:0:0:0:0:0:0:1'),(25,1,'2019-03-06 01:09:04.380000','0:0:0:0:0:0:0:1'),(26,1,'2019-03-06 01:10:07.165000','0:0:0:0:0:0:0:1'),(27,1,'2019-03-06 01:23:07.528000','0:0:0:0:0:0:0:1'),(28,1,'2019-03-06 01:23:49.215000','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `login_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(45) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `weight` float NOT NULL,
  `place` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `picture` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_code_UNIQUE` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'hhhh','hhh',222,222,'22','32r2','1551861225253.png'),(3,'2222','22',222,222,'22','22','1551861763308.pdf'),(4,'e\'e\'e\'e','eee',222,2,'ee','d','1551861894012.md');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` blob NOT NULL,
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'test',_binary '\Ïí!∫O\'{˜\r¡Æ¨7˙Òf\÷?°orª\‡àˇñ\n','13143378602'),(2,'pomo',_binary 'r6\€{,\rHFñ\ÈeFL∑ûO,\\∫\Î¡F\Èqñ\'','13143378602'),(3,'Pomo1',_binary '\ÕVwÄC±çêˆñª:\'3¸E\œ9–Ä6†å\⁄H\Ê','13143378602'),(4,'Pomo2',_binary 'ıK\À\’\≈sêtsª(ı\Ÿ\Íä5).eï	˝\ \«','13143378602'),(5,'Pomo3',_binary '\÷◊∑\Œ\–\ÔT‹©^UZ`1 DÉo+\Œ\–q\·òt\ﬁ','13143378602'),(6,'Pomo4',_binary '\·í\‹b¯¸é\ÁõkøÆ‰ú∫3XX=\Èd⁄éÆ','13143378602'),(7,'Pomo5',_binary 'R±°Ê©ûΩ7∫ﬂæñ•æ)9oj#©M<','13143378502'),(8,'Pomo6',_binary '∞{rí+±R˙0\›;±ï\ÎQ£\‡\Îë','13143378502'),(9,'Pomo7',_binary 'e\∆\Í?ê#)8ô/Yêî)\\Ü\Ã\·,Mòçã\ﬁ|z\“','13143378502'),(10,'Pomo8',_binary 'üˆ(˘:Hpò˛\·ùGX≤^¿,2\‹}\‰ß\◊ˇ','13143378502');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'jd'
--

--
-- Dumping routines for database 'jd'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-08 13:25:45
