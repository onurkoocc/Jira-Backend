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
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `statusid` int DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `creatorid` int DEFAULT NULL,
  `lastUpdate` date DEFAULT NULL,
  `priorityid` int DEFAULT NULL,
  `assignedto` int DEFAULT NULL,
  `assignedby` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `assignedby` (`assignedby`),
  KEY `assignedto` (`assignedto`),
  KEY `creatorid` (`creatorid`),
  KEY `priorityid` (`priorityid`),
  KEY `statusid` (`statusid`),
  CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`statusid`) REFERENCES `status` (`id`),
  CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`creatorid`) REFERENCES `user` (`id`),
  CONSTRAINT `issue_ibfk_5` FOREIGN KEY (`priorityid`) REFERENCES `priority` (`id`),
  CONSTRAINT `issue_ibfk_6` FOREIGN KEY (`assignedby`) REFERENCES `user` (`id`),
  CONSTRAINT `issue_ibfk_7` FOREIGN KEY (`assignedto`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'Jira Frontend Yapılması',25,'Projenin sunumu için frontend tasarımının yapılması gerekmektedir',3,'2021-11-18',5,3,4),(2,'Borsa Uygulaması Frontend',23,'Projenin sunumu için frontend tasarımının yapılması gerekmektedir',3,'2021-11-18',5,4,2),(3,'Kafein web sayfası tasarımı yapılacak',25,'React teknolojileri kullanılarak backend mikroservis...',3,'2021-11-18',5,3,4),(4,'Staj Defteri',25,'Staj defteri kodunun yazılması lazım',3,'2021-12-05',5,3,4);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
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
