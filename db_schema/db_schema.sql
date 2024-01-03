-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: abcsystemdb
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `abcsystemdb`
--

/*!40000 DROP DATABASE IF EXISTS `abcsystemdb`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `abcsystemdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `abcsystemdb`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cusID` int(10) NOT NULL AUTO_INCREMENT,
  `cusName` varchar(255) NOT NULL,
  `cusEmail` varchar(100) NOT NULL,
  `cusAddress` varchar(255) NOT NULL,
  `cusMobileNo` varchar(10) NOT NULL,
  `cusDOB` date NOT NULL,
  `cusGender` varchar(10) NOT NULL,
  `cusDateCreated` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`cusID`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1000,'Pasindu','hgpcsagara@gmail.com','Galahitiyawa','0786272616','2001-12-21','Male','2023-11-30 13:15:16'),(1001,'testName','test@mail.com','testAddress','0782165232','2000-10-10','Male','2023-11-30 14:00:45'),(1002,'test1Name','test1@mail.com','test1Address','0123521623','1999-12-12','male','2023-11-30 14:22:42'),(1003,'test2Name','test2@mail.com','test2Adderss','0786525632','2000-11-11','Female','2023-11-30 14:26:29'),(1004,'test3Name','test3@mail.com','test3Address','0756358986','2000-12-12','Male','2023-11-30 14:33:00'),(1005,'test4Name','test4@mail.com','test4Address','0787845213','1999-10-10','Female','2023-11-30 14:35:56'),(1006,'test5Name','test4@mail.com','test$Address','0775241256','2000-10-10','Male','2023-12-01 13:41:19'),(1007,'test6Name','test6@mail.com','test6Address','0725454263','1989-12-21','Female','2023-12-01 14:24:05'),(1008,'text7Name','text7@mail.com','test7Address','0772123653','2023-02-02','Male','2023-12-01 14:25:06'),(1009,'test8Name','test8@mail.com','test8Address','0715426352','2022-11-11','Male','2023-12-01 14:30:05'),(1010,'test9Name','test9@mail.com','test9Address','0715426352','2021-02-02','Male','2023-12-01 15:34:34'),(1011,'test10Name','test10@mail.com','test10Address','0745214789','2000-12-12','Female','2023-12-01 15:49:23'),(1012,'test11Name','test11@mail.com','test11Address','0781111111','2020-11-23','Male','2023-12-01 16:00:30'),(1013,'test12Name','test12@mail.com','test12Address','0781111112','2020-11-24','Male','2023-12-07 11:26:50'),(1014,'test13Name','test13@mail.com','test13Address','0781111113','2020-11-25','Male','2023-12-07 11:51:52'),(1015,'test14Name','test14@mail.com','test14Address','0781111114','2020-11-26','Male','2023-12-07 12:57:39');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-03 12:53:34
