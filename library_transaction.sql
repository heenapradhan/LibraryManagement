-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `library_transaction`
--

DROP TABLE IF EXISTS `library_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentId` int NOT NULL,
  `studentName` varchar(50) NOT NULL,
  `studentContact` varchar(20) NOT NULL,
  `bookName` varchar(70) NOT NULL,
  `bookAuthor` varchar(70) NOT NULL,
  `borrowingReturn` varchar(50) NOT NULL,
  `issuedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dueDate` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_transaction`
--

LOCK TABLES `library_transaction` WRITE;
/*!40000 ALTER TABLE `library_transaction` DISABLE KEYS */;
INSERT INTO `library_transaction` VALUES (4,23,'kk','932992932','','','','2016-07-19 18:43:16','2022-01-02 12:56:14'),(6,335,'Sumedh','95676565756','','','','2016-07-19 18:44:34','2022-01-02 12:56:14'),(7,87,'abhishek','9329882382','','','','2016-07-19 18:46:12','2022-01-02 12:56:14'),(8,7,'hina','7470617558','','','','2022-01-02 06:46:57','2022-01-02 12:56:14'),(9,7,'hina','7470617558','C++','Balaguru','borrow','2022-01-02 12:31:19','2022-01-02 18:01:19'),(10,7,'hina','7470617558','C++','Balaguru','borrow','2022-01-02 12:35:27','2022-01-02 18:05:27'),(11,7,'hina','7470617558','C++','Balaguru','borrow','2022-01-03 09:24:18','2022-01-10 02:54:18');
/*!40000 ALTER TABLE `library_transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-04 11:37:33
