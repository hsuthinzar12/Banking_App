-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cwbank
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `histories`
--

DROP TABLE IF EXISTS `histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `histories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `senderName` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `receiverName` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `senderPhone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `receiverPhone` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `transfertime` datetime DEFAULT NULL,
  `amount` double(10,3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sendPhone` (`senderPhone`),
  KEY `fk_receiverPhone` (`receiverPhone`),
  CONSTRAINT `fk_receiverPhone` FOREIGN KEY (`receiverPhone`) REFERENCES `users` (`phone`),
  CONSTRAINT `fk_sendPhone` FOREIGN KEY (`senderPhone`) REFERENCES `users` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `histories`
--

LOCK TABLES `histories` WRITE;
/*!40000 ALTER TABLE `histories` DISABLE KEYS */;
INSERT INTO `histories` VALUES (1,'Aung Aung','Phyo','09455040565','0987654321','0000-00-00 00:00:00',0.000),(2,'Aung Aung','Phyo','09455040565','0987654321','0000-00-00 00:00:00',0.000),(3,'Aung Aung','Phyo','09455040565','0987654321','2023-09-22 12:23:10',1000.000),(4,'Ko ko','Aung Phyo Lwin','0987654321','09455040565','0000-00-00 00:00:00',30000.000),(5,'Ko ko','Aung Phyo Lwin','0987654321','09455040565','2023-09-22 00:09:35',5000.000),(6,'Ko ko','Aung Phyo Lwin','0987654321','09455040565','2023-09-22 15:09:34',1000.000),(7,'Hsu','Aung Phyo Lwin','0912345678','09455040565','2023-09-22 15:09:17',1500.000),(8,'Aung Phyo','Ko ko','09777555333','0987654321','2023-09-23 13:09:39',5000.000),(9,'Aung Phyo','Aung Phyo Lwin','09777555333','09455040565','2023-09-23 13:09:11',2000.000),(10,'Hsu','Aung Phyo Lwin2','0912345678','09455040560','2024-02-23 10:02:16',1500.000),(11,'Hsu','Aung Phyo Lwin','0912345678','09455040565','2024-02-23 22:02:18',5000.000),(12,'Hsu1','Aung Phyo Lwin','09255561980','09455040565','2024-02-24 08:02:52',5000.000);
/*!40000 ALTER TABLE `histories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-24 10:30:55
