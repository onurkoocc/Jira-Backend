-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: jira-db
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  `issueid` int DEFAULT NULL,
  `activitydate` date DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `statusupdate` varchar(255) DEFAULT NULL,
  `updater` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `issueid` (`issueid`),
  KEY `updater` (`updater`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`issueid`) REFERENCES `issue` (`id`),
  CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`updater`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Issue Created',1,'2021-11-18','Projenin sunumu için frontend tasarımının yapılması gerekmektedir','com.kafein.jirabackend.model.Status@16a314ac',3),(2,'Uygulama Oluşturuldu',1,'2021-11-18','Uygulamanın React kütüphaneleri sebeiyle çıkardığı sıkıntılar bla bla','Fixed',2),(3,'Uygulama Test Edildi',1,'2021-11-18','Uygulamamız sıkıntısız çalışmaktadır','Tested',4),(4,'Issue Created',2,'2021-11-18','Projenin sunumu için frontend tasarımının yapılması gerekmektedir','com.kafein.jirabackend.model.Status@74ad5c8',3),(5,'Uygulama Oluşturuldu',2,'2021-11-18','Uygulamanın React kütüphaneleri sebeiyle çıkardığı sıkıntılar bla bla','Fixed',2),(6,'Uygulama Test Edildi',2,'2021-11-18','Uygulamamızın frontend kısmındaki issue listeleme kısımları çalışmamaktadır ','Reopened',4),(7,'Uygulama Hata Yoktur',2,'2021-11-18','Uygulamamız düzgün çalışmaktadır. Local kütüphanelerinizi güncelleyiniz','NoError',2),(8,'Uygulama Test Edildi',2,'2021-11-18','Uygulamamızın frontend kısmındaki issue listeleme kısımları çalışmamaktadır ','Reopened',4),(9,'Uygulama Hata Yoktur',2,'2021-11-18','Uygulamamız düzgün çalışmaktadır. Local kütüphanelerinizi güncelleyiniz','Fixed',2),(10,'Issue Created',3,'2021-11-18','React teknolojileri kullanılarak backend mikroservis...','com.kafein.jirabackend.model.Status@1a66959f',3),(11,'Frontend tasarımı yapıldı',3,'2021-11-18','Tasarımı beklenen teknolojileri kullanarak tamamladım','Fixed',2),(12,'Test yapıldı',3,'2021-11-18','Uygulama hatasız çalışmaktadır','Tested',4),(13,'Issue Created',4,'2021-12-05','Staj defteri kodunun yazılması lazım','com.kafein.jirabackend.model.Status@393b04cd',3),(14,'Staj Defteri Solution',4,'2021-12-05','Staj defteri kodu yazılmış ve çözülmüştür','Fixed',2),(15,'Staj Defteri Test',4,'2021-12-05','Staj defteri test edilmiştir ve hatasız çalışmaktadır.','Tested',4);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-06  0:07:54
