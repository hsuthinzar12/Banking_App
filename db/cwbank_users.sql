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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `age` int NOT NULL,
  `statenumber` int NOT NULL,
  `city` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `idtype` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `idnumber` int NOT NULL,
  `balance` double(12,3) NOT NULL DEFAULT '0.000',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Aung Phyo Lwin','Aungphyo12@','09455040565','male',24,0,'Yangon','NRC',192954,201100.000),(3,'Aung Phyo Lwin2','Aungphyo122@','09455040560','male',24,0,'Yangon','NRC',192955,1500.000),(4,'Ko ko','Kokokk@123$','0987654321','male',22,0,'Yangon','NRC',12345,2822000.500),(5,' Ma Ma ','Mama123%',' 0912345676',' female ',21,0,'Yangon','NRC',98765,0.000),(6,'Aung ph','Aungph7&','09125548284','male',19,0,'Yangon','NRC',123459,0.000),(7,'Hsu','Hsu123$','0912345678','female',19,0,'Yangon','NRC',1500,50500.000),(8,'Aung Phyo','Aungphyo1!','09777555333','male',23,0,'Yangon','NRC',222222,3000.000),(10,'Hsu','Hsu1234$','09255561989','Female',22,3,'TGK','N',123456,0.000),(11,'Hsu','Hsu1234$','09255561988','Female',22,4,'TGK','N',123456,0.000),(12,'HsuHsu','Hsu1234$','09255561987','Female',22,5,'TGK','N',123456,0.000),(13,'Hsu1','Hsu1234#','09255561980','Female',22,14,'TGK','N',123456,95000.000);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
