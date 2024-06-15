CREATE DATABASE  IF NOT EXISTS `operator` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `operator`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: operator
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `media_data`
--

DROP TABLE IF EXISTS `media_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `poster` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `release_year` int DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `trailerid` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_data`
--

LOCK TABLES `media_data` WRITE;
/*!40000 ALTER TABLE `media_data` DISABLE KEYS */;
INSERT INTO `media_data` VALUES (1,_binary '\0','A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.','Christopher Nolan','169 min','/Movies/4.png',0,'8.6/10',2014,NULL,'Interstellar','KPLWWIOCOOQ','movie'),(3,_binary '','Eight years after the Joker\'s reign of anarchy, Batman, with the help of the enigmatic Catwoman, is forced from his exile to save Gotham City.','Christopher Nolan','164 min','/Movies/4.png',5,'8.9/10',2012,'rent','The Dark Knight Rises','KPLWWIOCOOQ','movie'),(4,_binary '\0','The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.','Jon Favreau','169 min','/Movies/4.png',10,'8.6/10',2014,'rent','The Mandalorian','KPLWWIOCOOQ','serie'),(5,_binary '\0','A man with short-term memory loss attempts to track down his wife\'s murderer.','Christopher Nolan','113 min','/Movies/4.png',13,'8.4/10',2000,'buy','Memento','KPLWWIOCOOQ','movie');
/*!40000 ALTER TABLE `media_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-13 17:00:16
