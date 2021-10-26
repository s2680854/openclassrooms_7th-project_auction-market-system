-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	8.0.26

-- RESET DATABASE --
DROP DATABASE demo;
CREATE DATABASE demo;
USE demo;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bidlist`
--

DROP TABLE IF EXISTS `bidlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bidlist` (
                           `bidlist_id` int unsigned  AUTO_INCREMENT,
                           `account` varchar(50) ,
                           `type` varchar(35) ,
                           `bid_quantity` float ,
                           `ask_quantity` float DEFAULT NULL,
                           `bid` float DEFAULT NULL,
                           `ask` float DEFAULT NULL,
                           `benchmark` varchar(35) DEFAULT NULL,
                           `bidListDate` timestamp DEFAULT NULL,
                           `commentary` varchar(35) DEFAULT NULL,
                           `security` varchar(35) DEFAULT NULL,
                           `status` varchar(35) DEFAULT NULL,
                           `trader` varchar(35) DEFAULT NULL,
                           `book` varchar(35) DEFAULT NULL,
                           `creationName` varchar(35) DEFAULT NULL,
                           `creationDate` timestamp DEFAULT NULL,
                           `revisionName` varchar(35) DEFAULT NULL,
                           `revisionDate` timestamp DEFAULT NULL,
                           `dealName` varchar(35) DEFAULT NULL,
                           `dealType` varchar(35) DEFAULT NULL,
                           `sourceListId` varchar(35) DEFAULT NULL,
                           `side` varchar(35) DEFAULT NULL,
                           `id` bigint DEFAULT NULL,
                           `bids_list_date` datetime(6) DEFAULT NULL,
                           `creation_date` datetime(6) DEFAULT NULL,
                           `creation_name` varchar(255) DEFAULT NULL,
                           `deal_name` varchar(255) DEFAULT NULL,
                           `deal_type` varchar(255) DEFAULT NULL,
                           `revision_date` datetime(6) DEFAULT NULL,
                           `revision_name` varchar(255) DEFAULT NULL,
                           `source_list_id` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`bidlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidlist`
--

LOCK TABLES `bidlist` WRITE;
/*!40000 ALTER TABLE `bidlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `bidlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curvepoint`
--

DROP TABLE IF EXISTS `curvepoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curvepoint` (
                              `id` int unsigned  AUTO_INCREMENT,
                              `curve_id` int ,
                              `asOfDate` timestamp,
                              `term` float ,
                              `value` float ,
                              `creationDate` timestamp ,
                              `as_of_date` datetime(6) DEFAULT NULL,
                              `creation_date` datetime(6) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curvepoint`
--

LOCK TABLES `curvepoint` WRITE;
/*!40000 ALTER TABLE `curvepoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `curvepoint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
                          `id` int unsigned  AUTO_INCREMENT,
                          `moodysRating` varchar(35) ,
                          `sandPRating` varchar(35) ,
                          `fitchRating` varchar(35) ,
                          `orderNumber` int ,
                          `fitch_rating` varchar(255) DEFAULT NULL,
                          `moodys_rating` varchar(255) DEFAULT NULL,
                          `order_number` int ,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` int unsigned  AUTO_INCREMENT,
                        `name` varchar(35) ,
                        PRIMARY KEY (`role_id`),
                        UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'ADMIN'),(1,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rulename`
--

DROP TABLE IF EXISTS `rulename`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rulename` (
                            `id` int unsigned  AUTO_INCREMENT,
                            `name` varchar(35) ,
                            `description` varchar(100) ,
                            `json` varchar(255) ,
                            `template` varchar(50) ,
                            `sqlStr` varchar(255) ,
                            `sqlPart` varchar(255) ,
                            `sql_part` varchar(255) DEFAULT NULL,
                            `sql_str` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rulename`
--

LOCK TABLES `rulename` WRITE;
/*!40000 ALTER TABLE `rulename` DISABLE KEYS */;
/*!40000 ALTER TABLE `rulename` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
                         `trade_id` int unsigned  AUTO_INCREMENT,
                         `type` varchar(35) ,
                         `buyQuantity` float ,
                         `sellQuantity` float ,
                         `buyPrice` float ,
                         `sellPrice` float ,
                         `benchmark` varchar(35) ,
                         `tradeDate` timestamp ,
                         `security` varchar(35) ,
                         `status` varchar(35) ,
                         `trader` varchar(35) ,
                         `book` varchar(35) ,
                         `creationName` varchar(35) ,
                         `creationDate` timestamp ,
                         `revisionName` varchar(35) ,
                         `revisionDate` timestamp ,
                         `dealName` varchar(35) ,
                         `dealType` varchar(35) ,
                         `sourceListId` varchar(35) ,
                         `side` varchar(35) ,
                         `id` bigint ,
                         `account` varchar(255) DEFAULT NULL,
                         `buy_price` double ,
                         `buy_quantity` double ,
                         `creation_date` datetime(6) DEFAULT NULL,
                         `creation_name` varchar(255) DEFAULT NULL,
                         `deal_name` varchar(255) DEFAULT NULL,
                         `deal_type` varchar(255) DEFAULT NULL,
                         `revision_date` datetime(6) DEFAULT NULL,
                         `revision_name` varchar(255) DEFAULT NULL,
                         `sell_price` double ,
                         `sell_quantity` double ,
                         `source_list_id` varchar(255) DEFAULT NULL,
                         `trade_date` datetime(6) DEFAULT NULL,
                         PRIMARY KEY (`trade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` int unsigned  AUTO_INCREMENT,
                         `username` varchar(50) ,
                         `password` varchar(72) ,
                         `fullname` varchar(100) ,
                         `role` varchar(35) ,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `username` (`username`),
                         UNIQUE KEY `fullname` (`fullname`),
                         KEY `role` (`role`),
                         CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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
