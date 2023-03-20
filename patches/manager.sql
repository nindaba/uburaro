-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (arm64)
--
-- Host: localhost    Database: uburaro
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `country` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `house_number` varchar(255) DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `customer_date_created` bigint DEFAULT '0',
  `customer_item_type` varchar(255) DEFAULT 'item',
  `customer_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKcq2ltiqfa01794mlea91ookgk` (`customer_date_created`,`customer_item_type`,`customer_t_key`),
  CONSTRAINT `FKcq2ltiqfa01794mlea91ookgk` FOREIGN KEY (`customer_date_created`, `customer_item_type`, `customer_t_key`) REFERENCES `customer` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKsyn4cpsb02fuadp3vx1v53aiv` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_group`
--

DROP TABLE IF EXISTS `branch_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_group` (
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK5o2e7tv5njpcrp8gwrw4ucm16` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_group`
--

LOCK TABLES `branch_group` WRITE;
/*!40000 ALTER TABLE `branch_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_group_members`
--

DROP TABLE IF EXISTS `branch_group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_group_members` (
  `branch_group_date_created` bigint NOT NULL DEFAULT '0',
  `branch_group_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `branch_group_t_key` bigint NOT NULL DEFAULT '0',
  `members_date_created` bigint NOT NULL DEFAULT '0',
  `members_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `members_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`branch_group_date_created`,`branch_group_item_type`,`branch_group_t_key`,`members_date_created`,`members_item_type`,`members_t_key`),
  UNIQUE KEY `UK_1uddhky08b2s3w7y1v1wniuy` (`members_date_created`,`members_item_type`,`members_t_key`),
  CONSTRAINT `FKfg2pcpun02gm6mevrp6xfcx57` FOREIGN KEY (`members_date_created`, `members_item_type`, `members_t_key`) REFERENCES `hotel` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKsmemlho2ugeaymr5dkyvfxjee` FOREIGN KEY (`branch_group_date_created`, `branch_group_item_type`, `branch_group_t_key`) REFERENCES `branch_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_group_members`
--

LOCK TABLES `branch_group_members` WRITE;
/*!40000 ALTER TABLE `branch_group_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `branch_group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `company_name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK4sp56kmt2jqgj3g8jmfgn6xfi` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company2discount_group`
--

DROP TABLE IF EXISTS `company2discount_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company2discount_group` (
  `discounted_companies_date_created` bigint NOT NULL DEFAULT '0',
  `discounted_companies_t_key` varchar(255) NOT NULL DEFAULT 'item',
  `discounted_companies_item_type` bigint NOT NULL DEFAULT '0',
  `discount_groups_date_created` bigint NOT NULL DEFAULT '0',
  `discount_groups_t_key` varchar(255) NOT NULL DEFAULT 'item',
  `discount_groups_item_type` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`discounted_companies_date_created`,`discounted_companies_t_key`,`discounted_companies_item_type`,`discount_groups_date_created`,`discount_groups_t_key`,`discount_groups_item_type`),
  KEY `FKh9cakd8vd9jt5s9hkcb4wbj4b` (`discount_groups_date_created`,`discount_groups_t_key`,`discount_groups_item_type`),
  CONSTRAINT `FKh9cakd8vd9jt5s9hkcb4wbj4b` FOREIGN KEY (`discount_groups_date_created`, `discount_groups_t_key`, `discount_groups_item_type`) REFERENCES `discount_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKtppenus1sj3y9kk0khp1w5pk3` FOREIGN KEY (`discounted_companies_date_created`, `discounted_companies_t_key`, `discounted_companies_item_type`) REFERENCES `company` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company2discount_group`
--

LOCK TABLES `company2discount_group` WRITE;
/*!40000 ALTER TABLE `company2discount_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `company2discount_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_group`
--

DROP TABLE IF EXISTS `company_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_group` (
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKp0tdevbsor8ih6oh8d0gc6ecv` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_group`
--

LOCK TABLES `company_group` WRITE;
/*!40000 ALTER TABLE `company_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuration` (
  `property_key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK63aadlstb27rduwbqt3xs4005` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `age` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK1pqu9mfqpd1xp8795efin2xxa` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `principal` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_address`
--

DROP TABLE IF EXISTS `customer_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_address` (
  `customer_date_created` bigint NOT NULL DEFAULT '0',
  `customer_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `customer_t_key` bigint NOT NULL DEFAULT '0',
  `address_date_created` bigint NOT NULL DEFAULT '0',
  `address_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `address_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`customer_date_created`,`customer_item_type`,`customer_t_key`,`address_date_created`,`address_item_type`,`address_t_key`),
  UNIQUE KEY `UK_guhudn9at1qyfh4ywcj3ofu7a` (`address_date_created`,`address_item_type`,`address_t_key`),
  CONSTRAINT `FK28jiw1bhufr9e4ga1asymindu` FOREIGN KEY (`address_date_created`, `address_item_type`, `address_t_key`) REFERENCES `address` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK9u1fkvyr9t8e38ao0dytx5ag1` FOREIGN KEY (`customer_date_created`, `customer_item_type`, `customer_t_key`) REFERENCES `customer` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_address`
--

LOCK TABLES `customer_address` WRITE;
/*!40000 ALTER TABLE `customer_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers2companies`
--

DROP TABLE IF EXISTS `customers2companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers2companies` (
  `companies_date_created` bigint NOT NULL DEFAULT '0',
  `companies_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `companies_t_key` bigint NOT NULL DEFAULT '0',
  `customers_date_created` bigint NOT NULL DEFAULT '0',
  `customers_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `customers_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`companies_date_created`,`companies_item_type`,`companies_t_key`,`customers_date_created`,`customers_item_type`,`customers_t_key`),
  KEY `FK8sg2600kqoeacs78e9sx22r8b` (`customers_date_created`,`customers_item_type`,`customers_t_key`),
  CONSTRAINT `FK8sg2600kqoeacs78e9sx22r8b` FOREIGN KEY (`customers_date_created`, `customers_item_type`, `customers_t_key`) REFERENCES `customer` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKdf6880gwn6x25lvh46fkotg9d` FOREIGN KEY (`companies_date_created`, `companies_item_type`, `companies_t_key`) REFERENCES `company` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers2companies`
--

LOCK TABLES `customers2companies` WRITE;
/*!40000 ALTER TABLE `customers2companies` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers2companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_group`
--

DROP TABLE IF EXISTS `discount_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount_group` (
  `discount_value` double DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKkl0gl8u4ono5p6nbtnws67xrw` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_group`
--

LOCK TABLES `discount_group` WRITE;
/*!40000 ALTER TABLE `discount_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK1wnlof60vultrfgmaiusk9b7r` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `principal` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee2employee_group`
--

DROP TABLE IF EXISTS `employee2employee_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee2employee_group` (
  `employee_groups_date_created` bigint NOT NULL DEFAULT '0',
  `employee_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `employee_groups_t_key` bigint NOT NULL DEFAULT '0',
  `employee_date_created` bigint NOT NULL DEFAULT '0',
  `employee_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `employee_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`employee_groups_date_created`,`employee_groups_item_type`,`employee_groups_t_key`,`employee_date_created`,`employee_item_type`,`employee_t_key`),
  KEY `FKmk24ip0qia53krj2xr0oydg37` (`employee_date_created`,`employee_item_type`,`employee_t_key`),
  CONSTRAINT `FKmk24ip0qia53krj2xr0oydg37` FOREIGN KEY (`employee_date_created`, `employee_item_type`, `employee_t_key`) REFERENCES `employee_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKpoemlfqn0bnrklk2dmhxo6cd1` FOREIGN KEY (`employee_groups_date_created`, `employee_groups_item_type`, `employee_groups_t_key`) REFERENCES `employee` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee2employee_group`
--

LOCK TABLES `employee2employee_group` WRITE;
/*!40000 ALTER TABLE `employee2employee_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee2employee_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_group`
--

DROP TABLE IF EXISTS `employee_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_group` (
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKn7b1cl4au2uve0jh5tmi7qw6p` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_group`
--

LOCK TABLES `employee_group` WRITE;
/*!40000 ALTER TABLE `employee_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generated_key`
--

DROP TABLE IF EXISTS `generated_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generated_key` (
  `generated_value` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`generated_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generated_key`
--

LOCK TABLES `generated_key` WRITE;
/*!40000 ALTER TABLE `generated_key` DISABLE KEYS */;
INSERT INTO `generated_key` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34),(35),(36),(37),(38),(39),(40),(41),(42),(43),(44),(45),(46),(47),(48),(49),(50),(51),(52),(53),(54),(55),(56),(57),(58),(59),(60),(61),(62),(63),(64),(65),(66),(67),(68),(69),(70),(71),(72),(73),(74),(75),(76),(77),(78),(79),(80),(81),(82),(83),(84),(85),(86),(87),(88),(89),(90),(91),(92),(93),(94),(95),(96),(97),(98),(99),(100),(101),(102),(103),(104),(105),(106),(107),(108),(109),(110),(111),(112),(113),(114),(115),(116),(117),(118),(119),(120),(121),(122),(123),(124),(125),(126),(127),(128),(129),(130),(131),(132),(133),(134),(135),(136),(137),(138),(139),(140),(141),(142),(143),(144),(145),(146),(147),(148),(149),(150),(151),(152),(153),(154),(155),(156),(157),(158),(159),(160),(161),(162),(163),(164),(165),(166),(167),(168),(169),(170),(171),(172),(173),(174),(175),(176),(177),(178),(179),(180),(181),(182),(183),(184),(185),(186),(187),(188),(189),(190),(191),(192),(193),(194),(195),(196),(197),(198),(199),(200),(201),(202),(203),(204),(205),(206),(207),(208),(209),(210),(211),(212),(213),(214),(215),(216),(217),(218),(219),(220),(221),(222),(223),(224),(225),(226),(227),(228),(229),(230),(231),(232),(233),(234),(235),(236),(237),(238),(239),(240),(241),(242),(243),(244),(245),(246),(247),(248),(249),(250),(251),(252),(253),(254),(255),(256),(257),(258),(259),(260),(261),(262),(263),(264),(265),(266),(267),(268),(269),(270),(271),(272),(273),(274),(275),(276),(277),(278),(279),(280),(281),(282),(283),(284),(285),(286),(287),(288),(289),(290),(291),(292),(293),(294),(295),(296),(297),(298),(299),(300),(301),(302),(303),(304),(305),(306),(307),(308),(309),(310),(311),(312),(313),(314),(315),(316),(317),(318),(319),(320),(321),(322),(323),(324),(325),(326),(327),(328),(329),(330),(331),(332),(333),(334),(335),(336),(337),(338),(339),(340),(341),(342),(343),(344),(345),(346),(347),(348),(349),(350),(351),(352),(353),(354),(355),(356),(357),(358),(359),(360),(361),(362),(363),(364),(365),(366),(367),(368),(369),(370),(371),(372),(373),(374),(375),(376),(377),(378),(379),(380),(381),(382),(383),(384),(385),(386),(387),(388),(389),(390),(391),(392),(393),(394),(395),(396),(397),(398),(399),(400),(401),(402),(403),(404),(405),(406),(407),(408),(409),(410),(411),(412),(413),(414),(415),(416),(417),(418),(419),(420),(421),(422),(423),(424),(425),(426),(427),(428),(429),(430),(431),(432),(433),(434),(435),(436),(437),(438),(439),(440),(441),(442),(443),(444),(445),(446),(447),(448),(449),(450),(451),(452),(453),(454),(455),(456),(457),(458),(459),(460),(461),(462),(463),(464),(465),(466),(467),(468),(469),(470),(471),(472),(473),(474),(475),(476),(477),(478),(479),(480),(481),(482),(483),(484),(485),(486),(487),(488),(489),(490),(491),(492),(493),(494),(495),(496),(497),(498),(499),(500),(501),(502),(503),(504),(505),(506),(507),(508),(509),(510),(511),(512),(513),(514),(515),(516),(517),(518),(519),(520),(521),(522),(523),(524),(525),(526),(527),(528),(529),(530),(531),(532),(533),(534),(535),(536),(537),(538),(539),(540),(541),(542),(543),(544),(545),(546),(547),(548),(549),(550),(551),(552),(553),(554),(555),(556),(557),(558),(559),(560),(561),(562),(563),(564),(565),(566),(567),(568),(569),(570),(571),(572),(573),(574),(575),(576),(577),(578),(579),(580),(581),(582),(583),(584),(585),(586),(587),(588),(589),(590),(591),(592),(593),(594),(595),(596),(597),(598),(599),(600),(601),(602),(603),(604),(605),(606),(607),(608),(609),(610),(611),(612),(613),(614),(615),(616),(617),(618),(619),(620),(621),(622),(623),(624),(625),(626),(627),(628),(629),(630),(631),(632),(633),(634),(635),(636),(637),(638),(639),(640),(641),(642),(643),(644),(645),(646),(647),(648),(649),(650),(651),(652),(653),(654),(655),(656),(657),(658),(659),(660),(661),(662),(663),(664),(665),(666),(667),(668),(669),(670),(671),(672),(673),(674),(675),(676),(677),(678),(679),(680),(681),(682),(683),(684),(685),(686),(687),(688),(689),(690),(691),(692),(693),(694),(695),(696),(697),(698),(699),(700),(701),(702),(703),(704),(705),(706),(707),(708),(709),(710),(711),(712),(713),(714),(715),(716),(717),(718),(719),(720),(721),(722),(723),(724),(725),(726),(727),(728),(729),(730),(731),(732),(733),(734),(735),(736),(737),(738),(739),(740),(741),(742),(743),(744),(745),(746),(747),(748),(749),(750),(751),(752),(753),(754),(755),(756),(757),(758),(759),(760),(761),(762),(763),(764),(765),(766),(767),(768),(769),(770),(771),(772),(773),(774),(775),(776),(777),(778),(779),(780),(781),(782),(783),(784),(785),(786),(787),(788),(789),(790),(791),(792),(793),(794),(795),(796),(797),(798),(799),(800),(801),(802),(803),(804),(805),(806),(807),(808),(809),(810),(811),(812),(813),(814),(815),(816),(817),(818),(819),(820),(821),(822),(823),(824),(825),(826),(827),(828),(829),(830),(831),(832),(833),(834),(835),(836),(837),(838),(839),(840),(841),(842),(843),(844),(845),(846),(847),(848),(849),(850),(851),(852),(853),(854),(855),(856),(857),(858),(859),(860),(861),(862),(863),(864),(865),(866),(867),(868),(869),(870),(871),(872),(873),(874),(875),(876),(877),(878),(879),(880),(881),(882),(883),(884),(885),(886),(887),(888),(889),(890),(891),(892),(893),(894),(895),(896),(897),(898),(899),(900),(901),(902),(903),(904),(905),(906),(907),(908),(909),(910),(911),(912),(913),(914),(915),(916),(917),(918),(919),(920),(921),(922),(923),(924),(925),(926),(927),(928),(929),(930),(931),(932),(933),(934),(935),(936),(937),(938),(939),(940),(941),(942),(943),(944),(945),(946),(947),(948),(949),(950),(951),(952),(953),(954),(955),(956),(957),(958),(959),(960),(961),(962),(963),(964),(965),(966),(967),(968),(969),(970),(971),(972),(973),(974),(975),(976),(977),(978),(979),(980),(981),(982),(983),(984),(985),(986),(987),(988),(989),(990),(991),(992),(993),(994),(995),(996),(997),(998),(999),(1000),(1001),(1002),(1003),(1004),(1005),(1006),(1007),(1008),(1009),(1010),(1011),(1012),(1013),(1014),(1015),(1016),(1017),(1018),(1019),(1020),(1021),(1022),(1023),(1024),(1025),(1026),(1027),(1028),(1029),(1030),(1031),(1032),(1033),(1034),(1035),(1036),(1037),(1038),(1039),(1040),(1041);
/*!40000 ALTER TABLE `generated_key` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequence` VALUES (1042);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `branch_group_date_created` bigint DEFAULT '0',
  `branch_group_item_type` varchar(255) DEFAULT 'item',
  `branch_group_t_key` bigint DEFAULT '0',
  `default_language_date_created` bigint DEFAULT '0',
  `default_language_item_type` varchar(255) DEFAULT 'item',
  `default_language_t_key` bigint DEFAULT '0',
  `default_tax_group_date_created` bigint DEFAULT '0',
  `default_tax_group_item_type` varchar(255) DEFAULT 'item',
  `default_tax_group_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKr6v2r0kh96ecbaqbs5fbmkw4c` (`branch_group_date_created`,`branch_group_item_type`,`branch_group_t_key`),
  KEY `FKrh3i853vse3bha0ajnqx7cpdr` (`default_language_date_created`,`default_language_item_type`,`default_language_t_key`),
  KEY `FKigu0xxql0jk256uu2j8fl68rm` (`default_tax_group_date_created`,`default_tax_group_item_type`,`default_tax_group_t_key`),
  CONSTRAINT `FKb1doog2mnnt6g2rd25wk1qmsl` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKigu0xxql0jk256uu2j8fl68rm` FOREIGN KEY (`default_tax_group_date_created`, `default_tax_group_item_type`, `default_tax_group_t_key`) REFERENCES `tax_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKr6v2r0kh96ecbaqbs5fbmkw4c` FOREIGN KEY (`branch_group_date_created`, `branch_group_item_type`, `branch_group_t_key`) REFERENCES `branch_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKrh3i853vse3bha0ajnqx7cpdr` FOREIGN KEY (`default_language_date_created`, `default_language_item_type`, `default_language_t_key`) REFERENCES `language` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_addresses`
--

DROP TABLE IF EXISTS `hotel_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_addresses` (
  `hotel_date_created` bigint NOT NULL DEFAULT '0',
  `hotel_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `hotel_t_key` bigint NOT NULL DEFAULT '0',
  `addresses_date_created` bigint NOT NULL DEFAULT '0',
  `addresses_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `addresses_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`hotel_date_created`,`hotel_item_type`,`hotel_t_key`,`addresses_date_created`,`addresses_item_type`,`addresses_t_key`),
  UNIQUE KEY `UK_q6ubn0f1r9l2crht8valmxcuq` (`addresses_date_created`,`addresses_item_type`,`addresses_t_key`),
  CONSTRAINT `FK7sw9shkdwkh5c67s3nsp2j5ws` FOREIGN KEY (`hotel_date_created`, `hotel_item_type`, `hotel_t_key`) REFERENCES `hotel` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKn914lqcer5a2selrhbkvwfe5j` FOREIGN KEY (`addresses_date_created`, `addresses_item_type`, `addresses_t_key`) REFERENCES `address` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_addresses`
--

LOCK TABLES `hotel_addresses` WRITE;
/*!40000 ALTER TABLE `hotel_addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_languages`
--

DROP TABLE IF EXISTS `hotel_languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_languages` (
  `hotel_date_created` bigint NOT NULL DEFAULT '0',
  `hotel_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `hotel_t_key` bigint NOT NULL DEFAULT '0',
  `languages_date_created` bigint NOT NULL DEFAULT '0',
  `languages_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `languages_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`hotel_date_created`,`hotel_item_type`,`hotel_t_key`,`languages_date_created`,`languages_item_type`,`languages_t_key`),
  UNIQUE KEY `UK_7j3edbhbny4qewrtbci85n1b0` (`languages_date_created`,`languages_item_type`,`languages_t_key`),
  CONSTRAINT `FKi6tojtx5v3elt55khmvbubrsd` FOREIGN KEY (`hotel_date_created`, `hotel_item_type`, `hotel_t_key`) REFERENCES `hotel` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKio950r90ehwnmjice1ajng4n6` FOREIGN KEY (`languages_date_created`, `languages_item_type`, `languages_t_key`) REFERENCES `language` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_languages`
--

LOCK TABLES `hotel_languages` WRITE;
/*!40000 ALTER TABLE `hotel_languages` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_rooms`
--

DROP TABLE IF EXISTS `hotel_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_rooms` (
  `hotel_date_created` bigint NOT NULL DEFAULT '0',
  `hotel_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `hotel_t_key` bigint NOT NULL DEFAULT '0',
  `rooms_date_created` bigint NOT NULL DEFAULT '0',
  `rooms_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `rooms_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`hotel_date_created`,`hotel_item_type`,`hotel_t_key`,`rooms_date_created`,`rooms_item_type`,`rooms_t_key`),
  UNIQUE KEY `UK_fmi178h29lol23ecm4lpy4k9v` (`rooms_date_created`,`rooms_item_type`,`rooms_t_key`),
  CONSTRAINT `FK3dlw48f9lh369mtslruyq5ry0` FOREIGN KEY (`rooms_date_created`, `rooms_item_type`, `rooms_t_key`) REFERENCES `room` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKeooiy4fuuqc4rx7n6hejiov72` FOREIGN KEY (`hotel_date_created`, `hotel_item_type`, `hotel_t_key`) REFERENCES `hotel` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_rooms`
--

LOCK TABLES `hotel_rooms` WRITE;
/*!40000 ALTER TABLE `hotel_rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel_tax_groups`
--

DROP TABLE IF EXISTS `hotel_tax_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel_tax_groups` (
  `hotel_date_created` bigint NOT NULL DEFAULT '0',
  `hotel_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `hotel_t_key` bigint NOT NULL DEFAULT '0',
  `tax_groups_date_created` bigint NOT NULL DEFAULT '0',
  `tax_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `tax_groups_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`hotel_date_created`,`hotel_item_type`,`hotel_t_key`,`tax_groups_date_created`,`tax_groups_item_type`,`tax_groups_t_key`),
  UNIQUE KEY `UK_4mtf7jcbtqc8y5esptuhsiwrs` (`tax_groups_date_created`,`tax_groups_item_type`,`tax_groups_t_key`),
  CONSTRAINT `FK941modmn92dva83lk5dyjnclu` FOREIGN KEY (`tax_groups_date_created`, `tax_groups_item_type`, `tax_groups_t_key`) REFERENCES `tax_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKfhhthfp99xq9cmqusvyg7b69h` FOREIGN KEY (`hotel_date_created`, `hotel_item_type`, `hotel_t_key`) REFERENCES `hotel` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel_tax_groups`
--

LOCK TABLES `hotel_tax_groups` WRITE;
/*!40000 ALTER TABLE `hotel_tax_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_tax_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `active` bit(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `date_modified` datetime(6) DEFAULT NULL,
  `visible` bit(1) NOT NULL,
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `code_index` (`code`),
  KEY `FKc4p5uvkflfbi192i2grf4x265` (`t_key`),
  CONSTRAINT `FKc4p5uvkflfbi192i2grf4x265` FOREIGN KEY (`t_key`) REFERENCES `generated_key` (`generated_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1679147735693,'mBCategory',_binary '','categorie-icizanye-cuisine','2023-03-18 15:55:35.693000',_binary '',1),(1679147735700,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:35.693000',_binary '\0',2),(1679147735910,'mBCategory',_binary '','categorie-icizanye-bar','2023-03-18 15:55:35.910000',_binary '',3),(1679147735919,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:35.910000',_binary '\0',4),(1679147735964,'mBCategory',_binary '','categorie-icizanye-salle','2023-03-18 15:55:35.964000',_binary '',5),(1679147735975,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:35.964000',_binary '\0',6),(1679147736010,'mBCategory',_binary '','categorie-icizanye-hebergement','2023-03-18 15:55:36.010000',_binary '',7),(1679147736016,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.010000',_binary '\0',8),(1679147736048,'mBCategory',_binary '','categorie-icizanye-gymnase','2023-03-18 15:55:36.048000',_binary '',9),(1679147736053,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.048000',_binary '\0',10),(1679147736083,'mBCategory',_binary '','categorie-icizanye-automobile','2023-03-18 15:55:36.083000',_binary '',11),(1679147736088,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.083000',_binary '\0',12),(1679147736120,'mBCategory',_binary '','categorie-icizanye-batiment','2023-03-18 15:55:36.120000',_binary '',13),(1679147736125,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.120000',_binary '\0',14),(1679147736278,'mBCategory',_binary '','categorie-kugatumba-cuisine','2023-03-18 15:55:36.278000',_binary '',15),(1679147736284,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.278000',_binary '\0',16),(1679147736315,'mBCategory',_binary '','categorie-kugatumba-bar','2023-03-18 15:55:36.315000',_binary '',17),(1679147736322,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.315000',_binary '\0',18),(1679147736356,'mBCategory',_binary '','categorie-kugatumba-salle','2023-03-18 15:55:36.356000',_binary '',19),(1679147736362,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.356000',_binary '\0',20),(1679147736394,'mBCategory',_binary '','categorie-kugatumba-automobile','2023-03-18 15:55:36.394000',_binary '',21),(1679147736399,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.394000',_binary '\0',22),(1679147736427,'mBCategory',_binary '','categorie-kugatumba-batiment','2023-03-18 15:55:36.427000',_binary '',23),(1679147736433,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.427000',_binary '\0',24),(1679147736525,'mBCategory',_binary '','categorie-sptpa-matière-première','2023-03-18 15:55:36.525000',_binary '',25),(1679147736531,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.525000',_binary '\0',26),(1679147736564,'mBCategory',_binary '','categorie-sptpa-produit-finie','2023-03-18 15:55:36.564000',_binary '',27),(1679147736570,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.564000',_binary '\0',28),(1679147736601,'mBCategory',_binary '','categorie-sptpa-son-maïs','2023-03-18 15:55:36.601000',_binary '',29),(1679147736606,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.601000',_binary '\0',30),(1679147736642,'mBCategory',_binary '','categorie-sptpa-automobile','2023-03-18 15:55:36.642000',_binary '',31),(1679147736647,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.642000',_binary '\0',32),(1679147736677,'mBCategory',_binary '','categorie-sptpa-amballage','2023-03-18 15:55:36.677000',_binary '',33),(1679147736686,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.677000',_binary '\0',34),(1679147736711,'mBCategory',_binary '','categorie-sptpa-electrique','2023-03-18 15:55:36.711000',_binary '',35),(1679147736716,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.711000',_binary '\0',36),(1679147736746,'mBCategory',_binary '','categorie-sptpa-batiment','2023-03-18 15:55:36.746000',_binary '',37),(1679147736752,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.746000',_binary '\0',38),(1679147736779,'mBCategory',_binary '','categorie-sptpa-machine','2023-03-18 15:55:36.779000',_binary '',39),(1679147736785,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.779000',_binary '\0',40),(1679147736812,'mBCategory',_binary '','categorie-sptpa-matériel','2023-03-18 15:55:36.812000',_binary '',41),(1679147736817,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.812000',_binary '\0',42),(1679147736930,'mBClient',_binary '','CAMPANY-WV','2023-03-18 15:55:36.930000',_binary '',43),(1679147736936,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.930000',_binary '\0',44),(1679147736985,'mBClient',_binary '','CAMPANY-CAFOB','2023-03-18 15:55:36.985000',_binary '',45),(1679147736991,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:36.985000',_binary '\0',46),(1679147737022,'mBClient',_binary '','CAMPANY-UGADAS','2023-03-18 15:55:37.022000',_binary '',47),(1679147737027,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.022000',_binary '\0',48),(1679147737057,'mBClient',_binary '','CAMPANY-PADANE','2023-03-18 15:55:37.057000',_binary '',49),(1679147737063,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.057000',_binary '\0',50),(1679147737095,'mBClient',_binary '','CAMPANY-RESEAU_2000','2023-03-18 15:55:37.095000',_binary '',51),(1679147737100,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.095000',_binary '\0',52),(1679147737131,'mBClient',_binary '','CAMPANY-SAVE_THE-CHILDREN','2023-03-18 15:55:37.131000',_binary '',53),(1679147737136,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.131000',_binary '\0',54),(1679147737160,'mBClient',_binary '','CAMPANY-BBIN','2023-03-18 15:55:37.160000',_binary '',55),(1679147737166,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.160000',_binary '\0',56),(1679147737194,'mBClient',_binary '','CAMPANY-CNDH','2023-03-18 15:55:37.194000',_binary '',57),(1679147737199,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.194000',_binary '\0',58),(1679147737233,'mBClient',_binary '','CAMPANY-PNLP','2023-03-18 15:55:37.233000',_binary '',59),(1679147737239,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.233000',_binary '\0',60),(1679147737270,'mBClient',_binary '','CAMPANY-SOTB','2023-03-18 15:55:37.270000',_binary '',61),(1679147737275,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.270000',_binary '\0',62),(1679147737305,'mBClient',_binary '','CAMPANY-IFDC','2023-03-18 15:55:37.305000',_binary '',63),(1679147737311,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.305000',_binary '\0',64),(1679147737342,'mBClient',_binary '','CAMPANY-CICR','2023-03-18 15:55:37.342000',_binary '',65),(1679147737346,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.342000',_binary '\0',66),(1679147737371,'mBClient',_binary '','CLIENT-PROVINCE-MUYINGA','2023-03-18 15:55:37.371000',_binary '',67),(1679147737376,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.371000',_binary '\0',68),(1679147737407,'mBClient',_binary '','CLIENT-CNDD-FDD','2023-03-18 15:55:37.407000',_binary '',69),(1679147737412,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.407000',_binary '\0',70),(1679147737442,'mBClient',_binary '','CLIENT-ADCOM-MUYINGA','2023-03-18 15:55:37.442000',_binary '',71),(1679147737447,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.442000',_binary '\0',72),(1679147737479,'mBClient',_binary '','CAMPANY-BCB','2023-03-18 15:55:37.479000',_binary '',73),(1679147737484,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.479000',_binary '\0',74),(1679147737512,'mBClient',_binary '','CLIENT-VESOS','2023-03-18 15:55:37.512000',_binary '',75),(1679147737517,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.512000',_binary '\0',76),(1679147737547,'mBClient',_binary '','CAMPANY-PACT-BURUNDI','2023-03-18 15:55:37.547000',_binary '',77),(1679147737552,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.547000',_binary '\0',78),(1679147737577,'mBClient',_binary '','CAMPANY-PSI','2023-03-18 15:55:37.577000',_binary '',79),(1679147737582,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.577000',_binary '\0',80),(1679147737608,'mBClient',_binary '','CAMPANY-CARE','2023-03-18 15:55:37.608000',_binary '',81),(1679147737614,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.608000',_binary '\0',82),(1679147737637,'mBClient',_binary '','CAMPANY-SFCG','2023-03-18 15:55:37.637000',_binary '',83),(1679147737642,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.637000',_binary '\0',84),(1679147737666,'mBClient',_binary '','CLIENT-THIERRY','2023-03-18 15:55:37.666000',_binary '',85),(1679147737670,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.666000',_binary '\0',86),(1679147737864,'mBFacility',_binary '','ICIZANYE-HOTEL','2023-03-18 15:55:37.864000',_binary '',87),(1679147737869,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.864000',_binary '\0',88),(1679147737888,'mBCapital',_binary '\0',NULL,'2023-03-18 15:55:37.888000',_binary '\0',89),(1679147737893,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.888000',_binary '\0',90),(1679147737920,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 15:55:37.920000',_binary '\0',91),(1679147737926,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.920000',_binary '\0',92),(1679147737984,'mBFacility',_binary '','SNACK-BAR-KUGATUMBA','2023-03-18 15:55:37.984000',_binary '',93),(1679147737989,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.984000',_binary '\0',94),(1679147737998,'mBCapital',_binary '\0',NULL,'2023-03-18 15:55:37.998000',_binary '\0',95),(1679147738003,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:37.998000',_binary '\0',96),(1679147738016,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 15:55:38.016000',_binary '\0',97),(1679147738020,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.016000',_binary '\0',98),(1679147738076,'mBFacility',_binary '','SPTPA-TERINTAMBWE','2023-03-18 15:55:38.076000',_binary '',99),(1679147738081,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.076000',_binary '\0',100),(1679147738092,'mBCapital',_binary '\0',NULL,'2023-03-18 15:55:38.092000',_binary '\0',101),(1679147738098,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.092000',_binary '\0',102),(1679147738108,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 15:55:38.108000',_binary '\0',103),(1679147738113,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.108000',_binary '\0',104),(1679147738176,'mBInventory',_binary '','INVENTORY-CONGERATEUR','2023-03-18 15:55:38.176000',_binary '',105),(1679147738181,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.176000',_binary '\0',106),(1679147738231,'mBInventory',_binary '','INVENTORY-FRIGOT','2023-03-18 15:55:38.231000',_binary '',107),(1679147738237,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.231000',_binary '\0',108),(1679147738263,'mBInventory',_binary '','INVENTORY-MICRO_ONDE','2023-03-18 15:55:38.263000',_binary '',109),(1679147738266,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.263000',_binary '\0',110),(1679147738294,'mBInventory',_binary '','INVENTORY-FRITEUSE','2023-03-18 15:55:38.294000',_binary '',111),(1679147738299,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.294000',_binary '\0',112),(1679147738329,'mBInventory',_binary '','INVENTORY-CAFETIERE','2023-03-18 15:55:38.329000',_binary '',113),(1679147738334,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.329000',_binary '\0',114),(1679147738363,'mBInventory',_binary '','INVENTORY-CHAUFFE_EAU','2023-03-18 15:55:38.363000',_binary '',115),(1679147738368,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.363000',_binary '\0',116),(1679147738394,'mBInventory',_binary '','INVENTORY-TOOSTER','2023-03-18 15:55:38.394000',_binary '',117),(1679147738399,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.394000',_binary '\0',118),(1679147738423,'mBInventory',_binary '','INVENTORY-MIXER_A_JUS','2023-03-18 15:55:38.423000',_binary '',119),(1679147738428,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.423000',_binary '\0',120),(1679147738451,'mBInventory',_binary '','INVENTORY-TABLE_GRAND','2023-03-18 15:55:38.451000',_binary '',121),(1679147738455,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.451000',_binary '\0',122),(1679147738507,'mBInventory',_binary '','INVENTORY-BALANCE_GRAND','2023-03-18 15:55:38.507000',_binary '',123),(1679147738513,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.507000',_binary '\0',124),(1679147738539,'mBInventory',_binary '','INVENTORY-BALANCE_PETIT','2023-03-18 15:55:38.539000',_binary '',125),(1679147738544,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.539000',_binary '\0',126),(1679147738571,'mBInventory',_binary '','INVENTORY-TELEMOS_GRAND','2023-03-18 15:55:38.571000',_binary '',127),(1679147738577,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.571000',_binary '\0',128),(1679147738606,'mBInventory',_binary '','INVENTORY-TELEMOS_PETIT','2023-03-18 15:55:38.606000',_binary '',129),(1679147738611,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.606000',_binary '\0',130),(1679147738637,'mBInventory',_binary '','INVENTORY-ISAFUNIYA_NINI','2023-03-18 15:55:38.637000',_binary '',131),(1679147738641,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.637000',_binary '\0',132),(1679147738672,'mBInventory',_binary '','INVENTORY-ISAFUNIYA_NTOYA','2023-03-18 15:55:38.672000',_binary '',133),(1679147738677,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.672000',_binary '\0',134),(1679147738707,'mBInventory',_binary '','INVENTORY-PANNEAU_OMLETTE','2023-03-18 15:55:38.707000',_binary '',135),(1679147738713,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.707000',_binary '\0',136),(1679147738737,'mBInventory',_binary '','INVENTORY-PANNEAU_DE_THE','2023-03-18 15:55:38.737000',_binary '',137),(1679147738742,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.737000',_binary '\0',138),(1679147738763,'mBInventory',_binary '','INVENTORY-PANNEAU_SIMPLE','2023-03-18 15:55:38.763000',_binary '',139),(1679147738768,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.763000',_binary '\0',140),(1679147738798,'mBInventory',_binary '','INVENTORY-PANNEAU_DES_FRITTES','2023-03-18 15:55:38.798000',_binary '',141),(1679147738803,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.798000',_binary '\0',142),(1679147738828,'mBInventory',_binary '','INVENTORY-MARMITE_DE_PATTE_P','2023-03-18 15:55:38.828000',_binary '',143),(1679147738833,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.828000',_binary '\0',144),(1679147738860,'mBInventory',_binary '','INVENTORY-GRAND_BASSIN','2023-03-18 15:55:38.860000',_binary '',145),(1679147738864,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.860000',_binary '\0',146),(1679147738891,'mBInventory',_binary '','INVENTORY-PETIT__BASSIN','2023-03-18 15:55:38.891000',_binary '',147),(1679147738896,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.891000',_binary '\0',148),(1679147738924,'mBInventory',_binary '','INVENTORY-IMBABURA','2023-03-18 15:55:38.924000',_binary '',149),(1679147738929,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.924000',_binary '\0',150),(1679147738959,'mBInventory',_binary '','INVENTORY-SEAUX','2023-03-18 15:55:38.959000',_binary '',151),(1679147738964,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.959000',_binary '\0',152),(1679147738995,'mBInventory',_binary '','INVENTORY-BAIN_MALIN','2023-03-18 15:55:38.995000',_binary '',153),(1679147739001,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:38.995000',_binary '\0',154),(1679147739031,'mBInventory',_binary '','INVENTORY-VERRE_DE_LAIT','2023-03-18 15:55:39.031000',_binary '',155),(1679147739036,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.031000',_binary '\0',156),(1679147739065,'mBInventory',_binary '','INVENTORY-CHINWA','2023-03-18 15:55:39.065000',_binary '',157),(1679147739070,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.065000',_binary '\0',158),(1679147739098,'mBInventory',_binary '','INVENTORY-FOUE','2023-03-18 15:55:39.098000',_binary '',159),(1679147739105,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.098000',_binary '\0',160),(1679147739137,'mBInventory',_binary '','INVENTORY-SOUS_TASSE','2023-03-18 15:55:39.137000',_binary '',161),(1679147739142,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.137000',_binary '\0',162),(1679147739168,'mBInventory',_binary '','INVENTORY-JAGUE','2023-03-18 15:55:39.168000',_binary '',163),(1679147739173,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.168000',_binary '\0',164),(1679147739196,'mBInventory',_binary '','INVENTORY-BOLLE_DE_FRUIT','2023-03-18 15:55:39.196000',_binary '',165),(1679147739201,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.196000',_binary '\0',166),(1679147739224,'mBInventory',_binary '','INVENTORY-BOLLE_DE_SOUPE','2023-03-18 15:55:39.224000',_binary '',167),(1679147739230,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.224000',_binary '\0',168),(1679147739256,'mBInventory',_binary '','INVENTORY-BOLLE_DE_PUMA','2023-03-18 15:55:39.256000',_binary '',169),(1679147739261,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.256000',_binary '\0',170),(1679147739283,'mBInventory',_binary '','INVENTORY-B0LLE_DE_SUCRE','2023-03-18 15:55:39.283000',_binary '',171),(1679147739287,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.283000',_binary '\0',172),(1679147739313,'mBInventory',_binary '','INVENTORY-VENTULATEUR','2023-03-18 15:55:39.313000',_binary '',173),(1679147739318,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.313000',_binary '\0',174),(1679147739341,'mBInventory',_binary '','INVENTORY-MARMITE_DE_BUFFET','2023-03-18 15:55:39.341000',_binary '',175),(1679147739346,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.341000',_binary '\0',176),(1679147739373,'mBInventory',_binary '','INVENTORY-PLATEAU_SALADE_G','2023-03-18 15:55:39.373000',_binary '',177),(1679147739377,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.373000',_binary '\0',178),(1679147739396,'mBInventory',_binary '','INVENTORY-PLATEAU_SALADE_P','2023-03-18 15:55:39.396000',_binary '',179),(1679147739401,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.396000',_binary '\0',180),(1679147739424,'mBInventory',_binary '','INVENTORY-ASSIETTE_DE_P_ENTIER','2023-03-18 15:55:39.424000',_binary '',181),(1679147739429,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.424000',_binary '\0',182),(1679147739453,'mBInventory',_binary '','INVENTORY-ASSIETTE_DE_MUKEKE','2023-03-18 15:55:39.453000',_binary '',183),(1679147739460,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.453000',_binary '\0',184),(1679147739488,'mBInventory',_binary '','INVENTORY-ASSIETTE_DE_MELANGE','2023-03-18 15:55:39.488000',_binary '',185),(1679147739493,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.488000',_binary '\0',186),(1679147739524,'mBInventory',_binary '','INVENTORY-ASSIETTE_DE_COM_SIMPLE','2023-03-18 15:55:39.524000',_binary '',187),(1679147739529,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.524000',_binary '\0',188),(1679147739558,'mBInventory',_binary '','INVENTORY-FOURCHETTE','2023-03-18 15:55:39.558000',_binary '',189),(1679147739564,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.558000',_binary '\0',190),(1679147739591,'mBInventory',_binary '','INVENTORY-LOUCHE','2023-03-18 15:55:39.591000',_binary '',191),(1679147739597,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.591000',_binary '\0',192),(1679147739629,'mBInventory',_binary '','INVENTORY-COUTEAU','2023-03-18 15:55:39.629000',_binary '',193),(1679147739635,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.629000',_binary '\0',194),(1679147739661,'mBInventory',_binary '','INVENTORY-PETIT_CUIERRE','2023-03-18 15:55:39.661000',_binary '',195),(1679147739666,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.661000',_binary '\0',196),(1679147739695,'mBInventory',_binary '','INVENTORY-CUIERRE_DE_SOUPE','2023-03-18 15:55:39.695000',_binary '',197),(1679147739700,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.695000',_binary '\0',198),(1679147739723,'mBInventory',_binary '','INVENTORY-ASSIETTE_IGISAFURIYA','2023-03-18 15:55:39.723000',_binary '',199),(1679147739728,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.723000',_binary '\0',200),(1679147739747,'mBInventory',_binary '','INVENTORY-PLANCHE','2023-03-18 15:55:39.747000',_binary '',201),(1679147739751,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.747000',_binary '\0',202),(1679147739771,'mBInventory',_binary '','INVENTORY-ETAGERE','2023-03-18 15:55:39.771000',_binary '',203),(1679147739776,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.771000',_binary '\0',204),(1679147739802,'mBInventory',_binary '','INVENTORY-CALCULATRICE','2023-03-18 15:55:39.802000',_binary '',205),(1679147739806,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.802000',_binary '\0',206),(1679147739826,'mBInventory',_binary '','INVENTORY-HOT_POT','2023-03-18 15:55:39.826000',_binary '',207),(1679147739831,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.826000',_binary '\0',208),(1679147739850,'mBInventory',_binary '','INVENTORY-MACHINE_VIANDE','2023-03-18 15:55:39.850000',_binary '',209),(1679147739855,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.850000',_binary '\0',210),(1679147739878,'mBInventory',_binary '','INVENTORY-MIXEUR_A_SOUPE','2023-03-18 15:55:39.878000',_binary '',211),(1679147739883,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.878000',_binary '\0',212),(1679147739909,'mBInventory',_binary '','INVENTORY-PORTE-CUILLEUR','2023-03-18 15:55:39.909000',_binary '',213),(1679147739913,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.909000',_binary '\0',214),(1679147739940,'mBInventory',_binary '','INVENTORY-IFURU','2023-03-18 15:55:39.940000',_binary '',215),(1679147739945,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.940000',_binary '\0',216),(1679147739970,'mBInventory',_binary '','INVENTORY-MACHINEPOMM_DE_TERRE','2023-03-18 15:55:39.970000',_binary '',217),(1679147739975,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.970000',_binary '\0',218),(1679147739996,'mBInventory',_binary '','INVENTORY-PLATINE','2023-03-18 15:55:39.996000',_binary '',219),(1679147739999,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:39.996000',_binary '\0',220),(1679147740019,'mBInventory',_binary '','INVENTORY-KETIA','2023-03-18 15:55:40.019000',_binary '',221),(1679147740024,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.019000',_binary '\0',222),(1679147740045,'mBInventory',_binary '','INVENTORY-GRIADE','2023-03-18 15:55:40.045000',_binary '',223),(1679147740049,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.045000',_binary '\0',224),(1679147740071,'mBInventory',_binary '','INVENTORY-LAPPE_CHOUX','2023-03-18 15:55:40.071000',_binary '',225),(1679147740077,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.071000',_binary '\0',226),(1679147740103,'mBInventory',_binary '','INVENTORY-LAPPE_CAROTTE','2023-03-18 15:55:40.103000',_binary '',227),(1679147740109,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.103000',_binary '\0',228),(1679147740137,'mBInventory',_binary '','INVENTORY-COUTEAU_STEACK','2023-03-18 15:55:40.137000',_binary '',229),(1679147740142,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.137000',_binary '\0',230),(1679147740165,'mBInventory',_binary '','INVENTORY-CUISINIERE_A_GAZ','2023-03-18 15:55:40.165000',_binary '',231),(1679147740169,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.165000',_binary '\0',232),(1679147740194,'mBInventory',_binary '','INVENTORY-COUTEAU_SIMPLE','2023-03-18 15:55:40.194000',_binary '',233),(1679147740199,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.194000',_binary '\0',234),(1679147740219,'mBInventory',_binary '','INVENTORY-MARTEAU_A_STEACK','2023-03-18 15:55:40.219000',_binary '',235),(1679147740224,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:40.219000',_binary '\0',236),(1679147741247,'mBInventory',_binary '','INVENTORY-CIBOIRE','2023-03-18 15:55:41.247000',_binary '',237),(1679147741253,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.247000',_binary '\0',238),(1679147741293,'mBInventory',_binary '','INVENTORY-ORDINATEUR_DE_BUREAU','2023-03-18 15:55:41.293000',_binary '',239),(1679147741299,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.293000',_binary '\0',240),(1679147741326,'mBInventory',_binary '','INVENTORY-ORDINATEUR_PORTABLE','2023-03-18 15:55:41.326000',_binary '',241),(1679147741333,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.326000',_binary '\0',242),(1679147741373,'mBInventory',_binary '','INVENTORY-RADIO','2023-03-18 15:55:41.373000',_binary '',243),(1679147741380,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.373000',_binary '\0',244),(1679147741423,'mBInventory',_binary '','INVENTORY-TELEVISION','2023-03-18 15:55:41.423000',_binary '',245),(1679147741431,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.423000',_binary '\0',246),(1679147741469,'mBInventory',_binary '','INVENTORY-IMPRIMANTE','2023-03-18 15:55:41.469000',_binary '',247),(1679147741477,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.469000',_binary '\0',248),(1679147741521,'mBInventory',_binary '','INVENTORY-CHAISE_PLASTIQUE','2023-03-18 15:55:41.521000',_binary '',249),(1679147741529,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.521000',_binary '\0',250),(1679147741564,'mBInventory',_binary '','INVENTORY-CHAISE_RESTO_BAR','2023-03-18 15:55:41.564000',_binary '',251),(1679147741570,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.564000',_binary '\0',252),(1679147741608,'mBInventory',_binary '','INVENTORY-CHAISE_ET_TABLE_PALLAUTE','2023-03-18 15:55:41.608000',_binary '',253),(1679147741615,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.608000',_binary '\0',254),(1679147741650,'mBInventory',_binary '','INVENTORY-TABLE_PLASTIQUE','2023-03-18 15:55:41.650000',_binary '',255),(1679147741658,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.650000',_binary '\0',256),(1679147741721,'mBInventory',_binary '','INVENTORY-TABLE_METALLIQUE','2023-03-18 15:55:41.721000',_binary '',257),(1679147741731,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.721000',_binary '\0',258),(1679147741767,'mBInventory',_binary '','INVENTORY-TABLE_EN_BOIS','2023-03-18 15:55:41.767000',_binary '',259),(1679147741774,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.767000',_binary '\0',260),(1679147741810,'mBInventory',_binary '','INVENTORY-TABLE_ZIBIYO_NINI','2023-03-18 15:55:41.810000',_binary '',261),(1679147741817,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.810000',_binary '\0',262),(1679147741851,'mBInventory',_binary '','INVENTORY-TABLE_ZIBIYO_NTOYA','2023-03-18 15:55:41.851000',_binary '',263),(1679147741858,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.851000',_binary '\0',264),(1679147741891,'mBInventory',_binary '','INVENTORY-VERRE_DE_LIMONADE','2023-03-18 15:55:41.891000',_binary '',265),(1679147741897,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.891000',_binary '\0',266),(1679147741934,'mBInventory',_binary '','INVENTORY-VERRE_DE_VIN','2023-03-18 15:55:41.934000',_binary '',267),(1679147741941,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.934000',_binary '\0',268),(1679147741987,'mBInventory',_binary '','INVENTORY-VERRE_DE_PRIMUS','2023-03-18 15:55:41.987000',_binary '',269),(1679147741993,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:41.987000',_binary '\0',270),(1679147742018,'mBInventory',_binary '','INVENTORY-VERRE_DE_AMSTEL','2023-03-18 15:55:42.018000',_binary '',271),(1679147742023,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.018000',_binary '\0',272),(1679147742050,'mBInventory',_binary '','INVENTORY-CARTON_DE_G_AMSTEL','2023-03-18 15:55:42.050000',_binary '',273),(1679147742055,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.050000',_binary '\0',274),(1679147742076,'mBInventory',_binary '','INVENTORY-CARTON_DE_G_PRIMUS','2023-03-18 15:55:42.076000',_binary '',275),(1679147742080,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.076000',_binary '\0',276),(1679147742107,'mBInventory',_binary '','INVENTORY-CARTON_DE_P_AMSTEL','2023-03-18 15:55:42.107000',_binary '',277),(1679147742112,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.107000',_binary '\0',278),(1679147742132,'mBInventory',_binary '','INVENTORY-CARTON_DE_P_PRIMUS','2023-03-18 15:55:42.132000',_binary '',279),(1679147742136,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.132000',_binary '\0',280),(1679147742154,'mBInventory',_binary '','INVENTORY-CARTON_SKOOL','2023-03-18 15:55:42.154000',_binary '',281),(1679147742158,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.154000',_binary '\0',282),(1679147742194,'mBInventory',_binary '','INVENTORY-CARTON_SOMA','2023-03-18 15:55:42.194000',_binary '',283),(1679147742201,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.194000',_binary '\0',284),(1679147742244,'mBInventory',_binary '','INVENTORY-CARTON_FANTA','2023-03-18 15:55:42.244000',_binary '',285),(1679147742251,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.244000',_binary '\0',286),(1679147742298,'mBInventory',_binary '','INVENTORY-CARTON_ROYAL','2023-03-18 15:55:42.298000',_binary '',287),(1679147742306,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.298000',_binary '\0',288),(1679147742362,'mBInventory',_binary '','INVENTORY-CARTON_VIVA','2023-03-18 15:55:42.362000',_binary '',289),(1679147742369,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.362000',_binary '\0',290),(1679147742410,'mBInventory',_binary '','INVENTORY-VIDE_G_AMSTEL','2023-03-18 15:55:42.410000',_binary '',291),(1679147742415,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.410000',_binary '\0',292),(1679147742438,'mBInventory',_binary '','INVENTORY-VIDE_G_PRIMUS','2023-03-18 15:55:42.438000',_binary '',293),(1679147742445,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.438000',_binary '\0',294),(1679147742488,'mBInventory',_binary '','INVENTORY-VIDE_ROYAL','2023-03-18 15:55:42.488000',_binary '',295),(1679147742494,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.488000',_binary '\0',296),(1679147742536,'mBInventory',_binary '','INVENTORY-VIDE_FANTA','2023-03-18 15:55:42.536000',_binary '',297),(1679147742544,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.536000',_binary '\0',298),(1679147742587,'mBInventory',_binary '','INVENTORY-TABLE_COMPTOIRE','2023-03-18 15:55:42.587000',_binary '',299),(1679147742594,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.587000',_binary '\0',300),(1679147742641,'mBInventory',_binary '','INVENTORY-CHAISE_COMPTOIRE','2023-03-18 15:55:42.641000',_binary '',301),(1679147742648,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.641000',_binary '\0',302),(1679147742700,'mBInventory',_binary '','INVENTORY-CHAISE_BLANC','2023-03-18 15:55:42.700000',_binary '',303),(1679147742707,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.700000',_binary '\0',304),(1679147742749,'mBInventory',_binary '','INVENTORY-CHAISE_METTALIQUE','2023-03-18 15:55:42.749000',_binary '',305),(1679147742757,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.749000',_binary '\0',306),(1679147742803,'mBInventory',_binary '','INVENTORY-PLATEAU_EN_BOIS','2023-03-18 15:55:42.803000',_binary '',307),(1679147742810,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.803000',_binary '\0',308),(1679147742853,'mBInventory',_binary '','INVENTORY-PLTEAU_BRARUDI','2023-03-18 15:55:42.853000',_binary '',309),(1679147742860,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.853000',_binary '\0',310),(1679147742924,'mBInventory',_binary '','INVENTORY-ETAGERE-METALIQUE','2023-03-18 15:55:42.924000',_binary '',311),(1679147742932,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.924000',_binary '\0',312),(1679147742974,'mBInventory',_binary '','INVENTORY-COFRE-FORT','2023-03-18 15:55:42.974000',_binary '',313),(1679147742981,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:42.974000',_binary '\0',314),(1679147743649,'mBInventory',_binary '','INVENTORY-PISCINE_','2023-03-18 15:55:43.649000',_binary '',315),(1679147743655,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.649000',_binary '\0',316),(1679147743678,'mBInventory',_binary '','INVENTORY-FILTRE_DE_SABLE','2023-03-18 15:55:43.678000',_binary '',317),(1679147743683,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.678000',_binary '\0',318),(1679147743705,'mBInventory',_binary '','INVENTORY-ASPIRATEUR_ET_TUYAUX','2023-03-18 15:55:43.705000',_binary '',319),(1679147743710,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.705000',_binary '\0',320),(1679147743731,'mBInventory',_binary '','INVENTORY-SCEAU','2023-03-18 15:55:43.731000',_binary '',321),(1679147743737,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.731000',_binary '\0',322),(1679147743760,'mBInventory',_binary '','INVENTORY-PORTE_MENTAUX','2023-03-18 15:55:43.760000',_binary '',323),(1679147743765,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.760000',_binary '\0',324),(1679147743793,'mBInventory',_binary '','INVENTORY-RACLETTE','2023-03-18 15:55:43.793000',_binary '',325),(1679147743798,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.793000',_binary '\0',326),(1679147743829,'mBInventory',_binary '','INVENTORY-TUYAUX_ALIMANTA','2023-03-18 15:55:43.829000',_binary '',327),(1679147743835,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.829000',_binary '\0',328),(1679147743860,'mBInventory',_binary '','INVENTORY-TAPIS_AU_SOL','2023-03-18 15:55:43.860000',_binary '',329),(1679147743865,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.860000',_binary '\0',330),(1679147743893,'mBInventory',_binary '','INVENTORY-PETIT_TAPIS_','2023-03-18 15:55:43.893000',_binary '',331),(1679147743898,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.893000',_binary '\0',332),(1679147743925,'mBInventory',_binary '','INVENTORY-POUILIE_MULTICOMBINE','2023-03-18 15:55:43.925000',_binary '',333),(1679147743930,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.925000',_binary '\0',334),(1679147743956,'mBInventory',_binary '','INVENTORY-APAREIL_DE_MUSCULATION','2023-03-18 15:55:43.956000',_binary '',335),(1679147743962,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.956000',_binary '\0',336),(1679147743991,'mBInventory',_binary '','INVENTORY-ALTHERE_AVEC_SES_DISQUES','2023-03-18 15:55:43.991000',_binary '',337),(1679147743996,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:43.991000',_binary '\0',338),(1679147744022,'mBInventory',_binary '','INVENTORY-SUPPORT_ALTHERE','2023-03-18 15:55:44.022000',_binary '',339),(1679147744028,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.022000',_binary '\0',340),(1679147744052,'mBInventory',_binary '','INVENTORY-BANC_DE_MUSCULATION','2023-03-18 15:55:44.052000',_binary '',341),(1679147744057,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.052000',_binary '\0',342),(1679147744086,'mBInventory',_binary '','INVENTORY-APPAREIL_VIBRO-MASSER','2023-03-18 15:55:44.086000',_binary '',343),(1679147744092,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.086000',_binary '\0',344),(1679147744116,'mBInventory',_binary '','INVENTORY-PESEPERSONNE','2023-03-18 15:55:44.116000',_binary '',345),(1679147744122,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.116000',_binary '\0',346),(1679147744149,'mBInventory',_binary '','INVENTORY-BALLONS_SUISSE','2023-03-18 15:55:44.149000',_binary '',347),(1679147744156,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.149000',_binary '\0',348),(1679147744180,'mBInventory',_binary '','INVENTORY-VELO_CARDIOLOGIQUE','2023-03-18 15:55:44.180000',_binary '',349),(1679147744187,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.180000',_binary '\0',350),(1679147744212,'mBInventory',_binary '','INVENTORY-ASPIRATEUR_DES_TAPIS','2023-03-18 15:55:44.212000',_binary '',351),(1679147744217,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.212000',_binary '\0',352),(1679147744244,'mBInventory',_binary '','INVENTORY-POSTE_RADIO_ALIPU','2023-03-18 15:55:44.244000',_binary '',353),(1679147744249,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.244000',_binary '\0',354),(1679147744281,'mBInventory',_binary '','INVENTORY-STABILISATEUR','2023-03-18 15:55:44.281000',_binary '',355),(1679147744287,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.281000',_binary '\0',356),(1679147744311,'mBInventory',_binary '','INVENTORY-TABLETTE','2023-03-18 15:55:44.311000',_binary '',357),(1679147744317,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.311000',_binary '\0',358),(1679147744341,'mBInventory',_binary '','INVENTORY-PAIRE_DE_POIDS_DE_MUSCL','2023-03-18 15:55:44.341000',_binary '',359),(1679147744346,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.341000',_binary '\0',360),(1679147744373,'mBInventory',_binary '','INVENTORY-SUPPORT_DES_POIDS','2023-03-18 15:55:44.373000',_binary '',361),(1679147744379,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.373000',_binary '\0',362),(1679147744417,'mBInventory',_binary '','INVENTORY-ROULETTE_DE_MUSCULTION','2023-03-18 15:55:44.417000',_binary '',363),(1679147744424,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.417000',_binary '\0',364),(1679147744449,'mBInventory',_binary '','INVENTORY-APPAR_ASSOUPLESSEMENT','2023-03-18 15:55:44.449000',_binary '',365),(1679147744453,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.449000',_binary '\0',366),(1679147744510,'mBInventory',_binary '','INVENTORY-APPAR_DE_MUSCULATION','2023-03-18 15:55:44.510000',_binary '',367),(1679147744517,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.510000',_binary '\0',368),(1679147744541,'mBInventory',_binary '','INVENTORY-PAIRE_DE_GROS_DISQUE','2023-03-18 15:55:44.541000',_binary '',369),(1679147744547,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.541000',_binary '\0',370),(1679147744571,'mBInventory',_binary '','INVENTORY-MIROIRE_DE_VESTIAIRE','2023-03-18 15:55:44.571000',_binary '',371),(1679147744578,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.571000',_binary '\0',372),(1679147744602,'mBInventory',_binary '','INVENTORY-PETIT_PORTE_MENTEAU','2023-03-18 15:55:44.602000',_binary '',373),(1679147744607,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:44.602000',_binary '\0',374),(1679147745074,'mBInventory',_binary '','INVENTORY-DRAPS','2023-03-18 15:55:45.074000',_binary '',375),(1679147745079,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.074000',_binary '\0',376),(1679147745107,'mBInventory',_binary '','INVENTORY-ESSUIE-MAIN','2023-03-18 15:55:45.107000',_binary '',377),(1679147745112,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.107000',_binary '\0',378),(1679147745142,'mBInventory',_binary '','INVENTORY-LITS','2023-03-18 15:55:45.142000',_binary '',379),(1679147745147,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.142000',_binary '\0',380),(1679147745177,'mBInventory',_binary '','INVENTORY-MATELAS','2023-03-18 15:55:45.177000',_binary '',381),(1679147745182,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.177000',_binary '\0',382),(1679147745208,'mBInventory',_binary '','INVENTORY-CAMERA','2023-03-18 15:55:45.208000',_binary '',383),(1679147745214,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.208000',_binary '\0',384),(1679147745250,'mBInventory',_binary '','INVENTORY-CHAUFFE-EAU','2023-03-18 15:55:45.250000',_binary '',385),(1679147745256,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.250000',_binary '\0',386),(1679147745280,'mBInventory',_binary '','INVENTORY-SALLON_MINISTRE','2023-03-18 15:55:45.280000',_binary '',387),(1679147745286,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.280000',_binary '\0',388),(1679147745312,'mBInventory',_binary '','INVENTORY-MACHINE_A_LAVER','2023-03-18 15:55:45.312000',_binary '',389),(1679147745318,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.312000',_binary '\0',390),(1679147745347,'mBInventory',_binary '','INVENTORY-MIROIRES','2023-03-18 15:55:45.347000',_binary '',391),(1679147745352,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.347000',_binary '\0',392),(1679147745397,'mBInventory',_binary '','INVENTORY-ROUTEUR','2023-03-18 15:55:45.397000',_binary '',393),(1679147745403,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.397000',_binary '\0',394),(1679147745431,'mBInventory',_binary '','INVENTORY-MOUSTIQUAIRE','2023-03-18 15:55:45.431000',_binary '',395),(1679147745436,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.431000',_binary '\0',396),(1679147745459,'mBInventory',_binary '','INVENTORY-TELEPHONE_FIXE','2023-03-18 15:55:45.459000',_binary '',397),(1679147745465,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.459000',_binary '\0',398),(1679147745490,'mBInventory',_binary '','INVENTORY-CHAISE','2023-03-18 15:55:45.490000',_binary '',399),(1679147745495,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.490000',_binary '\0',400),(1679147745519,'mBInventory',_binary '','INVENTORY-TAPIS','2023-03-18 15:55:45.519000',_binary '',401),(1679147745524,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.519000',_binary '\0',402),(1679147745551,'mBInventory',_binary '','INVENTORY-BABOUCHE','2023-03-18 15:55:45.551000',_binary '',403),(1679147745555,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.551000',_binary '\0',404),(1679147745579,'mBInventory',_binary '','INVENTORY-ALLONGES','2023-03-18 15:55:45.579000',_binary '',405),(1679147745583,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.579000',_binary '\0',406),(1679147745601,'mBInventory',_binary '','INVENTORY-CONTRE_FAUTEUIL','2023-03-18 15:55:45.601000',_binary '',407),(1679147745607,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.601000',_binary '\0',408),(1679147745646,'mBInventory',_binary '','INVENTORY-FAIRE_A_REPASSER','2023-03-18 15:55:45.646000',_binary '',409),(1679147745651,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.646000',_binary '\0',410),(1679147745675,'mBInventory',_binary '','INVENTORY-POUBELLES','2023-03-18 15:55:45.675000',_binary '',411),(1679147745680,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.675000',_binary '\0',412),(1679147745719,'mBInventory',_binary '','INVENTORY-TE_OREILLE','2023-03-18 15:55:45.719000',_binary '',413),(1679147745725,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.719000',_binary '\0',414),(1679147745748,'mBInventory',_binary '','INVENTORY-DECODEUR','2023-03-18 15:55:45.748000',_binary '',415),(1679147745753,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.748000',_binary '\0',416),(1679147745776,'mBInventory',_binary '','INVENTORY-RIDEAUX','2023-03-18 15:55:45.776000',_binary '',417),(1679147745781,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.776000',_binary '\0',418),(1679147745806,'mBInventory',_binary '','INVENTORY-RIDEAUX_DU_JOUR','2023-03-18 15:55:45.806000',_binary '',419),(1679147745810,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.806000',_binary '\0',420),(1679147745838,'mBInventory',_binary '','INVENTORY-PORTE_RIDEAU','2023-03-18 15:55:45.838000',_binary '',421),(1679147745843,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.838000',_binary '\0',422),(1679147745863,'mBInventory',_binary '','INVENTORY-MALES','2023-03-18 15:55:45.863000',_binary '',423),(1679147745867,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.863000',_binary '\0',424),(1679147745887,'mBInventory',_binary '','INVENTORY-EXTINCTEUR','2023-03-18 15:55:45.887000',_binary '',425),(1679147745892,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.887000',_binary '\0',426),(1679147745920,'mBInventory',_binary '','INVENTORY-PHOTOCOPIEUSE','2023-03-18 15:55:45.920000',_binary '',427),(1679147745924,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.920000',_binary '\0',428),(1679147745945,'mBInventory',_binary '','INVENTORY-CENTRAL_TELEPHONIQUE','2023-03-18 15:55:45.945000',_binary '',429),(1679147745949,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.945000',_binary '\0',430),(1679147745969,'mBInventory',_binary '','INVENTORY-BAGAGE_EAU','2023-03-18 15:55:45.969000',_binary '',431),(1679147745973,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.969000',_binary '\0',432),(1679147745993,'mBInventory',_binary '','INVENTORY-COUVRE-LITS','2023-03-18 15:55:45.993000',_binary '',433),(1679147745998,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:45.993000',_binary '\0',434),(1679147746030,'mBInventory',_binary '','INVENTORY-TABLE','2023-03-18 15:55:46.030000',_binary '',435),(1679147746034,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.030000',_binary '\0',436),(1679147746056,'mBInventory',_binary '','INVENTORY-TELECOMMANDE','2023-03-18 15:55:46.056000',_binary '',437),(1679147746062,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.056000',_binary '\0',438),(1679147746081,'mBInventory',_binary '','INVENTORY-CONTOIRE','2023-03-18 15:55:46.081000',_binary '',439),(1679147746085,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.081000',_binary '\0',440),(1679147746811,'mBInventory',_binary '','INVENTORY-ASSIETTE','2023-03-18 15:55:46.811000',_binary '',441),(1679147746818,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.811000',_binary '\0',442),(1679147746857,'mBInventory',_binary '','INVENTORY-FLUTEUSES','2023-03-18 15:55:46.857000',_binary '',443),(1679147746863,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.857000',_binary '\0',444),(1679147746896,'mBInventory',_binary '','INVENTORY-BASSINS-G','2023-03-18 15:55:46.896000',_binary '',445),(1679147746903,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.896000',_binary '\0',446),(1679147746937,'mBInventory',_binary '','INVENTORY-TAMIS','2023-03-18 15:55:46.937000',_binary '',447),(1679147746943,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.937000',_binary '\0',448),(1679147746992,'mBInventory',_binary '','INVENTORY-ISAFURIYA','2023-03-18 15:55:46.992000',_binary '',449),(1679147746998,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:46.992000',_binary '\0',450),(1679147747032,'mBInventory',_binary '','INVENTORY-IKARAYI','2023-03-18 15:55:47.032000',_binary '',451),(1679147747039,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.032000',_binary '\0',452),(1679147747071,'mBInventory',_binary '','INVENTORY-MIXEUR','2023-03-18 15:55:47.071000',_binary '',453),(1679147747079,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.071000',_binary '\0',454),(1679147747113,'mBInventory',_binary '','INVENTORY-CONGELATEUR','2023-03-18 15:55:47.113000',_binary '',455),(1679147747120,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.113000',_binary '\0',456),(1679147747154,'mBInventory',_binary '','INVENTORY-CUISINIERE','2023-03-18 15:55:47.154000',_binary '',457),(1679147747161,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.154000',_binary '\0',458),(1679147747196,'mBInventory',_binary '','INVENTORY-BALANCE','2023-03-18 15:55:47.196000',_binary '',459),(1679147747202,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.196000',_binary '\0',460),(1679147747235,'mBInventory',_binary '','INVENTORY-PLANCHES','2023-03-18 15:55:47.235000',_binary '',461),(1679147747242,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.235000',_binary '\0',462),(1679147747281,'mBInventory',_binary '','INVENTORY-PALLETTE','2023-03-18 15:55:47.281000',_binary '',463),(1679147747288,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.281000',_binary '\0',464),(1679147747324,'mBInventory',_binary '','INVENTORY-IPANU','2023-03-18 15:55:47.324000',_binary '',465),(1679147747331,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.324000',_binary '\0',466),(1679147747365,'mBInventory',_binary '','INVENTORY-BASSIN_P','2023-03-18 15:55:47.365000',_binary '',467),(1679147747372,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.365000',_binary '\0',468),(1679147747405,'mBInventory',_binary '','INVENTORY-IBIRIKA','2023-03-18 15:55:47.405000',_binary '',469),(1679147747411,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.405000',_binary '\0',470),(1679147747463,'mBInventory',_binary '','INVENTORY-COUTEAU_DE_TABLE','2023-03-18 15:55:47.463000',_binary '',471),(1679147747468,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.463000',_binary '\0',472),(1679147747491,'mBInventory',_binary '','INVENTORY-UTUYIKO_DUTOYA','2023-03-18 15:55:47.491000',_binary '',473),(1679147747497,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.491000',_binary '\0',474),(1679147747519,'mBInventory',_binary '','INVENTORY-BOLLE','2023-03-18 15:55:47.519000',_binary '',475),(1679147747523,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.519000',_binary '\0',476),(1679147747550,'mBInventory',_binary '','INVENTORY-TABLE_DE_CUISINE','2023-03-18 15:55:47.550000',_binary '',477),(1679147747556,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.550000',_binary '\0',478),(1679147747577,'mBInventory',_binary '','INVENTORY-CABELAS','2023-03-18 15:55:47.577000',_binary '',479),(1679147747581,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.577000',_binary '\0',480),(1679147747602,'mBInventory',_binary '','INVENTORY-ROUCHE','2023-03-18 15:55:47.602000',_binary '',481),(1679147747607,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.602000',_binary '\0',482),(1679147747629,'mBInventory',_binary '','INVENTORY-MACHETTE_DE_CUISINE','2023-03-18 15:55:47.629000',_binary '',483),(1679147747633,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.629000',_binary '\0',484),(1679147747659,'mBInventory',_binary '','INVENTORY-FOUR_AKABENZI','2023-03-18 15:55:47.659000',_binary '',485),(1679147747664,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.659000',_binary '\0',486),(1679147747687,'mBInventory',_binary '','INVENTORY-VANTILATEUR','2023-03-18 15:55:47.687000',_binary '',487),(1679147747692,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:47.687000',_binary '\0',488),(1679147748133,'mBInventory',_binary '','INVENTORY-CARTON_AMSTEL_G','2023-03-18 15:55:48.133000',_binary '',489),(1679147748139,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.133000',_binary '\0',490),(1679147748170,'mBInventory',_binary '','INVENTORY-CARTON_P_AMSTEL','2023-03-18 15:55:48.170000',_binary '',491),(1679147748175,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.170000',_binary '\0',492),(1679147748196,'mBInventory',_binary '','INVENTORY-CARTON_PRIMUS_G','2023-03-18 15:55:48.196000',_binary '',493),(1679147748201,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.196000',_binary '\0',494),(1679147748223,'mBInventory',_binary '','INVENTORY-CARTON_PRIMUS_P','2023-03-18 15:55:48.223000',_binary '',495),(1679147748229,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.223000',_binary '\0',496),(1679147748272,'mBInventory',_binary '','INVENTORY-CARTON_ROYALE','2023-03-18 15:55:48.272000',_binary '',497),(1679147748277,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.272000',_binary '\0',498),(1679147748302,'mBInventory',_binary '','INVENTORY-CARTON_SKOL','2023-03-18 15:55:48.302000',_binary '',499),(1679147748307,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.302000',_binary '\0',500),(1679147748336,'mBInventory',_binary '','INVENTORY-CARTON_TUSKER','2023-03-18 15:55:48.336000',_binary '',501),(1679147748342,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.336000',_binary '\0',502),(1679147748367,'mBInventory',_binary '','INVENTORY-CARTON_SMINOFF','2023-03-18 15:55:48.367000',_binary '',503),(1679147748373,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.367000',_binary '\0',504),(1679147748400,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_SOMA','2023-03-18 15:55:48.400000',_binary '',505),(1679147748406,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.400000',_binary '\0',506),(1679147748431,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_AMSTEL_G','2023-03-18 15:55:48.431000',_binary '',507),(1679147748438,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.431000',_binary '\0',508),(1679147748463,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_AMSTEL_P','2023-03-18 15:55:48.463000',_binary '',509),(1679147748469,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.463000',_binary '\0',510),(1679147748492,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_PRIMUS_G ','2023-03-18 15:55:48.492000',_binary '',511),(1679147748499,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.492000',_binary '\0',512),(1679147748529,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_PRIMUS_P ','2023-03-18 15:55:48.529000',_binary '',513),(1679147748535,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.529000',_binary '\0',514),(1679147748564,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_FANTA','2023-03-18 15:55:48.564000',_binary '',515),(1679147748571,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.564000',_binary '\0',516),(1679147748596,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_VIVA','2023-03-18 15:55:48.596000',_binary '',517),(1679147748601,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.596000',_binary '\0',518),(1679147748625,'mBInventory',_binary '','INVENTORY-BOUTEUILLE_ROYALE','2023-03-18 15:55:48.625000',_binary '',519),(1679147748630,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.625000',_binary '\0',520),(1679147748656,'mBInventory',_binary '','INVENTORY-CARTON_VIDE_AMSTEL_G','2023-03-18 15:55:48.656000',_binary '',521),(1679147748663,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.656000',_binary '\0',522),(1679147748688,'mBInventory',_binary '','INVENTORY-CARTON_VIDE_AMSTEL_P','2023-03-18 15:55:48.688000',_binary '',523),(1679147748694,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.688000',_binary '\0',524),(1679147748720,'mBInventory',_binary '','INVENTORY-CARTON_VIDE_PRIMUS_G','2023-03-18 15:55:48.720000',_binary '',525),(1679147748726,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.720000',_binary '\0',526),(1679147748752,'mBInventory',_binary '','INVENTORY-CARTON_VIDE_VIVA','2023-03-18 15:55:48.752000',_binary '',527),(1679147748759,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.752000',_binary '\0',528),(1679147748786,'mBInventory',_binary '','INVENTORY-CHAISES','2023-03-18 15:55:48.786000',_binary '',529),(1679147748791,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.786000',_binary '\0',530),(1679147748838,'mBInventory',_binary '','INVENTORY-TABLES','2023-03-18 15:55:48.838000',_binary '',531),(1679147748843,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.838000',_binary '\0',532),(1679147748869,'mBInventory',_binary '','INVENTORY-CHAISE_DE_COMPTOIR','2023-03-18 15:55:48.869000',_binary '',533),(1679147748875,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.869000',_binary '\0',534),(1679147748899,'mBInventory',_binary '','INVENTORY-TABLES_DE_COMPTOIR','2023-03-18 15:55:48.899000',_binary '',535),(1679147748904,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.899000',_binary '\0',536),(1679147748929,'mBInventory',_binary '','INVENTORY-CHAISE_FAUTEUILS','2023-03-18 15:55:48.929000',_binary '',537),(1679147748935,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.929000',_binary '\0',538),(1679147748961,'mBInventory',_binary '','INVENTORY-CHAISES_BRARUDI','2023-03-18 15:55:48.961000',_binary '',539),(1679147748967,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.961000',_binary '\0',540),(1679147748990,'mBInventory',_binary '','INVENTORY-TABLES_BRARUDI','2023-03-18 15:55:48.990000',_binary '',541),(1679147748996,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:48.990000',_binary '\0',542),(1679147749020,'mBInventory',_binary '','INVENTORY-IMITAKA_YA_BRARUDI','2023-03-18 15:55:49.020000',_binary '',543),(1679147749027,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.020000',_binary '\0',544),(1679147749051,'mBInventory',_binary '','INVENTORY-BILLARD','2023-03-18 15:55:49.051000',_binary '',545),(1679147749057,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.051000',_binary '\0',546),(1679147749084,'mBInventory',_binary '','INVENTORY-FRIGO','2023-03-18 15:55:49.084000',_binary '',547),(1679147749090,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.084000',_binary '\0',548),(1679147749116,'mBInventory',_binary '','INVENTORY-FRIGO_BRARUDI','2023-03-18 15:55:49.116000',_binary '',549),(1679147749122,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.116000',_binary '\0',550),(1679147749147,'mBInventory',_binary '','INVENTORY-ORDINATEUR','2023-03-18 15:55:49.147000',_binary '',551),(1679147749152,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.147000',_binary '\0',552),(1679147749195,'mBInventory',_binary '','INVENTORY-PARAPLUIE','2023-03-18 15:55:49.195000',_binary '',553),(1679147749200,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.195000',_binary '\0',554),(1679147749228,'mBInventory',_binary '','INVENTORY-CARCULATRICE','2023-03-18 15:55:49.228000',_binary '',555),(1679147749233,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.228000',_binary '\0',556),(1679147749256,'mBInventory',_binary '','INVENTORY-POUBELLE','2023-03-18 15:55:49.256000',_binary '',557),(1679147749261,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.256000',_binary '\0',558),(1679147749304,'mBInventory',_binary '','INVENROTY-CONGELATEUR','2023-03-18 15:55:49.304000',_binary '',559),(1679147749310,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.304000',_binary '\0',560),(1679147749344,'mBInventory',_binary '','INVENTORY-VERRE-SIMPLE','2023-03-18 15:55:49.344000',_binary '',561),(1679147749349,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.344000',_binary '\0',562),(1679147749373,'mBInventory',_binary '','INVENTORY-CONGELATEUR-BRARUDI','2023-03-18 15:55:49.373000',_binary '',563),(1679147749379,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.373000',_binary '\0',564),(1679147749404,'mBInventory',_binary '','INVENTORY-PLATEAUX','2023-03-18 15:55:49.404000',_binary '',565),(1679147749411,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.404000',_binary '\0',566),(1679147749436,'mBInventory',_binary '','INVENTORY-COUPE','2023-03-18 15:55:49.436000',_binary '',567),(1679147749442,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.436000',_binary '\0',568),(1679147749467,'mBInventory',_binary '','INVENTORY-VERRE-DE-VIN','2023-03-18 15:55:49.467000',_binary '',569),(1679147749472,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.467000',_binary '\0',570),(1679147749496,'mBInventory',_binary '','INVENTORY-VERRE-BRARUDI','2023-03-18 15:55:49.496000',_binary '',571),(1679147749502,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:49.496000',_binary '\0',572),(1679147750335,'mBInventory',_binary '','INVENTORY-TISSU','2023-03-18 15:55:50.335000',_binary '',573),(1679147750341,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.335000',_binary '\0',574),(1679147750377,'mBInventory',_binary '','INVENTORY-RETRO_PROJECTEUR','2023-03-18 15:55:50.377000',_binary '',575),(1679147750384,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.377000',_binary '\0',576),(1679147750411,'mBInventory',_binary '','INVENTORY-ALLONGE','2023-03-18 15:55:50.411000',_binary '',577),(1679147750417,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.411000',_binary '\0',578),(1679147750536,'mBInventory',_binary '','INVENTORY-HAUT_PARLEUR','2023-03-18 15:55:50.536000',_binary '',579),(1679147750541,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.536000',_binary '\0',580),(1679147750579,'mBInventory',_binary '','INVENTORY-VENTILLATEUR','2023-03-18 15:55:50.579000',_binary '',581),(1679147750585,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.579000',_binary '\0',582),(1679147750627,'mBInventory',_binary '','INVENTORY-RADIO-SIMPLE','2023-03-18 15:55:50.627000',_binary '',583),(1679147750633,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.627000',_binary '\0',584),(1679147750789,'mBInventory',_binary '','INVENTORY-CAMIONNETTE','2023-03-18 15:55:50.789000',_binary '',585),(1679147750793,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.789000',_binary '\0',586),(1679147750821,'mBInventory',_binary '','INVENTORY-GROUPE_ELECTOGENE','2023-03-18 15:55:50.821000',_binary '',587),(1679147750827,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.821000',_binary '\0',588),(1679147750902,'mBInventory',_binary '','INVENTORY-GROUPE_ELECTOGENE-1','2023-03-18 15:55:50.902000',_binary '',589),(1679147750906,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.902000',_binary '\0',590),(1679147750926,'mBInventory',_binary '','INVENTORY-GROUPE_ELECTOGENE-2','2023-03-18 15:55:50.926000',_binary '',591),(1679147750932,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.926000',_binary '\0',592),(1679147750996,'mBInventory',_binary '','INVENTORY-AMBALLAGE-DE-25KG','2023-03-18 15:55:50.996000',_binary '',593),(1679147751003,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:50.996000',_binary '\0',594),(1679147751023,'mBInventory',_binary '','INVENTORY-AMBALLAGE-DE-10KG','2023-03-18 15:55:51.023000',_binary '',595),(1679147751028,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.023000',_binary '\0',596),(1679147751055,'mBInventory',_binary '','INVENTORY-AMBALLAGE-DE-5KG','2023-03-18 15:55:51.055000',_binary '',597),(1679147751059,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.055000',_binary '\0',598),(1679147751081,'mBInventory',_binary '','INVENTORY-AMBALLAGE-DE-1KG','2023-03-18 15:55:51.081000',_binary '',599),(1679147751086,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.081000',_binary '\0',600),(1679147751111,'mBInventory',_binary '','INVENTORY-AMBALLAGE-DE-500G','2023-03-18 15:55:51.111000',_binary '',601),(1679147751116,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.111000',_binary '\0',602),(1679147751269,'mBInventory',_binary '','INVENTORY-MAïS','2023-03-18 15:55:51.269000',_binary '',603),(1679147751276,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.269000',_binary '\0',604),(1679147751314,'mBInventory',_binary '','INVENTORY-SORGHO','2023-03-18 15:55:51.314000',_binary '',605),(1679147751321,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.314000',_binary '\0',606),(1679147751359,'mBInventory',_binary '','INVENTORY-SOJA','2023-03-18 15:55:51.359000',_binary '',607),(1679147751366,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.359000',_binary '\0',608),(1679147751403,'mBInventory',_binary '','INVENTORY-BLE','2023-03-18 15:55:51.403000',_binary '',609),(1679147751411,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.403000',_binary '\0',610),(1679147751595,'mBInventory',_binary '','INVENTORY-LIGNE-ELECTRIQUE','2023-03-18 15:55:51.595000',_binary '',611),(1679147751601,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.595000',_binary '\0',612),(1679147751630,'mBInventory',_binary '','INVENTORY-GROUPE-ELECTROGENE','2023-03-18 15:55:51.630000',_binary '',613),(1679147751636,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.630000',_binary '\0',614),(1679147751697,'mBInventory',_binary '','INVENTORY-DROYER','2023-03-18 15:55:51.697000',_binary '',615),(1679147751702,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.697000',_binary '\0',616),(1679147751724,'mBInventory',_binary '','INVENTORY-GRADER','2023-03-18 15:55:51.724000',_binary '',617),(1679147751729,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.724000',_binary '\0',618),(1679147751759,'mBInventory',_binary '','INVENTORY-MACHINE-CYNCLONE','2023-03-18 15:55:51.759000',_binary '',619),(1679147751763,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.759000',_binary '\0',620),(1679147751789,'mBInventory',_binary '','INVENTORY-MELANGEUR','2023-03-18 15:55:51.789000',_binary '',621),(1679147751792,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.789000',_binary '\0',622),(1679147751821,'mBInventory',_binary '','INVENTORY-TORREFACTEUR','2023-03-18 15:55:51.821000',_binary '',623),(1679147751825,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.821000',_binary '\0',624),(1679147751855,'mBInventory',_binary '','INVENTORY-CHAINE-DE-PRODUCTION-MAΪS','2023-03-18 15:55:51.855000',_binary '',625),(1679147751860,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.855000',_binary '\0',626),(1679147751887,'mBInventory',_binary '','INVENTORY-CYNCLONE-INOXYDABLE','2023-03-18 15:55:51.887000',_binary '',627),(1679147751892,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.887000',_binary '\0',628),(1679147751915,'mBInventory',_binary '','INVENTORY-CHAINE-DE-PRODUCTION-RIZ','2023-03-18 15:55:51.915000',_binary '',629),(1679147751920,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:51.915000',_binary '\0',630),(1679147752089,'mBInventory',_binary '','INVENTORY-FARINE-DE-MAΪS-DE-25KG','2023-03-18 15:55:52.089000',_binary '',631),(1679147752094,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.089000',_binary '\0',632),(1679147752115,'mBInventory',_binary '','INVENTORY-FARINE-DE-MAΪS-10KG','2023-03-18 15:55:52.115000',_binary '',633),(1679147752118,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.115000',_binary '\0',634),(1679147752146,'mBInventory',_binary '','INVENTORY-FARINE-DE-MAΪS-5KG','2023-03-18 15:55:52.146000',_binary '',635),(1679147752151,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.146000',_binary '\0',636),(1679147752177,'mBInventory',_binary '','INVENTORY-FARINE-DE-BOULLIE-DE-1KG','2023-03-18 15:55:52.177000',_binary '',637),(1679147752181,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.177000',_binary '\0',638),(1679147752208,'mBInventory',_binary '','INVENTORY-FARINE-DE-BOULLIE-DE-500G','2023-03-18 15:55:52.208000',_binary '',639),(1679147752211,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.208000',_binary '\0',640),(1679147752317,'mBInventory',_binary '','INVENTORY-SON-DE-MAΪS-QUALITE-1','2023-03-18 15:55:52.317000',_binary '',641),(1679147752321,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.317000',_binary '\0',642),(1679147752344,'mBInventory',_binary '','INVENTORY-SON-DE-MAΪS-QUALITE-2','2023-03-18 15:55:52.344000',_binary '',643),(1679147752349,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.344000',_binary '\0',644),(1679147752415,'mBInventory',_binary '','INVENTORY-MACHINE-A-COUDRE','2023-03-18 15:55:52.415000',_binary '',645),(1679147752422,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.415000',_binary '\0',646),(1679147752462,'mBInventory',_binary '','INVENTORY-BALANCE-ELECTRONIQUE','2023-03-18 15:55:52.462000',_binary '',647),(1679147752468,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.462000',_binary '\0',648),(1679147752513,'mBInventory',_binary '','INVENTORY-BALANCE-ZHUSHENG','2023-03-18 15:55:52.513000',_binary '',649),(1679147752519,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.513000',_binary '\0',650),(1679147752557,'mBInventory',_binary '','IMVENTORY-ALLONGE','2023-03-18 15:55:52.557000',_binary '',651),(1679147752563,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.557000',_binary '\0',652),(1679147752603,'mBInventory',_binary '','INVENTORY-TABLE-DE-BUREAU','2023-03-18 15:55:52.603000',_binary '',653),(1679147752610,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.603000',_binary '\0',654),(1679147752656,'mBInventory',_binary '','INVENTORY-UNIFORME','2023-03-18 15:55:52.656000',_binary '',655),(1679147752661,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.656000',_binary '\0',656),(1679147752680,'mBInventory',_binary '','INVENTORY-BOTUNE','2023-03-18 15:55:52.680000',_binary '',657),(1679147752685,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.680000',_binary '\0',658),(1679147752711,'mBInventory',_binary '','INVENTORY-MARMITE','2023-03-18 15:55:52.711000',_binary '',659),(1679147752716,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.711000',_binary '\0',660),(1679147752735,'mBInventory',_binary '','INVENTORY-BECHE','2023-03-18 15:55:52.735000',_binary '',661),(1679147752741,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.735000',_binary '\0',662),(1679147752912,'mBInventory',_binary '','INVENTORY-MERCEDES-BENZ-3236','2023-03-18 15:55:52.912000',_binary '',663),(1679147752916,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.912000',_binary '\0',664),(1679147752954,'mBInventory',_binary '','INVENTORY-BATIMENT','2023-03-18 15:55:52.954000',_binary '',665),(1679147752958,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:52.954000',_binary '\0',666),(1679147753005,'mBRentProperty',_binary '','CAMPANY-YOUNG-TEAM','2023-03-18 15:55:53.005000',_binary '',667),(1679147753010,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:53.005000',_binary '\0',668),(1679147753042,'mBRentProperty',_binary '','CAMPANY-MUTUELLE','2023-03-18 15:55:53.042000',_binary '',669),(1679147753047,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:53.042000',_binary '\0',670),(1679147753067,'mBRentProperty',_binary '','CAMPANY-SHAMDAAR','2023-03-18 15:55:53.067000',_binary '',671),(1679147753073,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:53.067000',_binary '\0',672),(1679147753095,'mBRentProperty',_binary '','CAMPANY-HONEST','2023-03-18 15:55:53.095000',_binary '',673),(1679147753100,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:53.095000',_binary '\0',674),(1679147753123,'mBRentProperty',_binary '','CAMPANY-LA-TECH','2023-03-18 15:55:53.123000',_binary '',675),(1679147753127,'modificationLog',_binary '\0',NULL,'2023-03-18 15:55:53.123000',_binary '\0',676),(1679148939019,'mBClient',_binary '','CAMPANY-YOUNG-TEAM','2023-03-18 16:15:39.019000',_binary '',677),(1679148939022,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.019000',_binary '\0',678),(1679148939050,'mBClient',_binary '','CAMPANY-SHAMDAAR','2023-03-18 16:15:39.050000',_binary '',679),(1679148939053,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.050000',_binary '\0',680),(1679148939079,'mBClient',_binary '','CAMPANY-LATECH','2023-03-18 16:15:39.079000',_binary '',681),(1679148939082,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.079000',_binary '\0',682),(1679148939107,'mBClient',_binary '','CAMPAMY-MUTUELLE','2023-03-18 16:15:39.107000',_binary '',683),(1679148939111,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.107000',_binary '\0',684),(1679148939134,'mBClient',_binary '','CAMPANY-HONEST','2023-03-18 16:15:39.134000',_binary '',685),(1679148939138,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.134000',_binary '\0',686),(1679148939164,'mBClient',_binary '','CAMPANY-SPTPA','2023-03-18 16:15:39.164000',_binary '',687),(1679148939166,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.164000',_binary '\0',688),(1679148939280,'mBClient',_binary '','CLIENT-STANY','2023-03-18 16:15:39.280000',_binary '',689),(1679148939282,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.280000',_binary '\0',690),(1679148939303,'mBClient',_binary '','CLIENT-JACQUES','2023-03-18 16:15:39.303000',_binary '',691),(1679148939306,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.303000',_binary '\0',692),(1679148939326,'mBClient',_binary '','CLIENT-BOSCO','2023-03-18 16:15:39.326000',_binary '',693),(1679148939329,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.326000',_binary '\0',694),(1679148939351,'mBClient',_binary '','CLIENT-PATRICK','2023-03-18 16:15:39.351000',_binary '',695),(1679148939353,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.351000',_binary '\0',696),(1679148939372,'mBClient',_binary '','CLIENT-DANIEL','2023-03-18 16:15:39.372000',_binary '',697),(1679148939375,'modificationLog',_binary '\0',NULL,'2023-03-18 16:15:39.372000',_binary '\0',698),(1679149581844,'mBRentProperty',_binary '','CAMPANY-SPTPA','2023-03-18 16:26:21.844000',_binary '',699),(1679149581847,'modificationLog',_binary '\0',NULL,'2023-03-18 16:26:21.844000',_binary '\0',700),(1679149781560,'mBRentContract',_binary '','CC-703','2023-03-18 16:29:41.560000',_binary '\0',701),(1679149781564,'modificationLog',_binary '\0',NULL,'2023-03-18 16:29:41.560000',_binary '\0',702),(1679149928293,'mBRentContract',_binary '','CC-706','2023-03-18 16:32:08.293000',_binary '\0',704),(1679149928303,'modificationLog',_binary '\0',NULL,'2023-03-18 16:32:08.293000',_binary '\0',705),(1679150375137,'mBRentContract',_binary '','CC-709','2023-03-18 16:39:35.137000',_binary '\0',707),(1679150375149,'modificationLog',_binary '\0',NULL,'2023-03-18 16:39:35.137000',_binary '\0',708),(1679150437482,'mBRentContract',_binary '','CC-712','2023-03-18 16:40:37.482000',_binary '\0',710),(1679150437493,'modificationLog',_binary '\0',NULL,'2023-03-18 16:40:37.482000',_binary '\0',711),(1679150478822,'mBRentContract',_binary '','CC-715','2023-03-18 16:41:18.822000',_binary '\0',713),(1679150478827,'modificationLog',_binary '\0',NULL,'2023-03-18 16:41:18.822000',_binary '\0',714),(1679150541107,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.107000',_binary '\0',716),(1679150541118,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.107000',_binary '\0',717),(1679150541201,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.201000',_binary '\0',719),(1679150541212,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.201000',_binary '\0',720),(1679150541285,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.285000',_binary '\0',722),(1679150541311,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.285000',_binary '\0',723),(1679150541388,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.388000',_binary '\0',725),(1679150541399,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.388000',_binary '\0',726),(1679150541457,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.457000',_binary '\0',728),(1679150541466,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.457000',_binary '\0',729),(1679150541524,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.524000',_binary '\0',731),(1679150541533,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.524000',_binary '\0',732),(1679150541592,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.592000',_binary '\0',734),(1679150541604,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.592000',_binary '\0',735),(1679150541653,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.653000',_binary '\0',737),(1679150541662,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.653000',_binary '\0',738),(1679150541723,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.723000',_binary '\0',740),(1679150541732,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.723000',_binary '\0',741),(1679150541785,'mBRentOrder',_binary '\0',NULL,'2023-03-18 16:42:21.785000',_binary '\0',743),(1679150541794,'modificationLog',_binary '\0',NULL,'2023-03-18 16:42:21.785000',_binary '\0',744),(1679150764052,'mBInvoice',_binary '\0','IN-748','2023-01-01 02:00:00.000000',_binary '\0',746),(1679150764066,'modificationLog',_binary '\0',NULL,'2023-03-18 16:46:04.052000',_binary '\0',747),(1679150764203,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:46:04.203000',_binary '\0',749),(1679150764216,'modificationLog',_binary '\0',NULL,'2023-03-18 16:46:04.203000',_binary '\0',750),(1679150882869,'mBInvoice',_binary '\0','IN-753','2023-01-01 02:00:00.000000',_binary '\0',751),(1679150882884,'modificationLog',_binary '\0',NULL,'2023-03-18 16:48:02.869000',_binary '\0',752),(1679150882986,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:48:02.986000',_binary '\0',754),(1679150883004,'modificationLog',_binary '\0',NULL,'2023-03-18 16:48:02.986000',_binary '\0',755),(1679151000771,'mBInvoice',_binary '\0','IN-758','2023-01-01 02:00:00.000000',_binary '\0',756),(1679151000786,'modificationLog',_binary '\0',NULL,'2023-03-18 16:50:00.771000',_binary '\0',757),(1679151000899,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:50:00.899000',_binary '\0',759),(1679151000916,'modificationLog',_binary '\0',NULL,'2023-03-18 16:50:00.899000',_binary '\0',760),(1679151042448,'mBInvoice',_binary '\0','IN-763','2023-01-01 02:00:00.000000',_binary '\0',761),(1679151042463,'modificationLog',_binary '\0',NULL,'2023-03-18 16:50:42.448000',_binary '\0',762),(1679151042553,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:50:42.553000',_binary '\0',764),(1679151042572,'modificationLog',_binary '\0',NULL,'2023-03-18 16:50:42.553000',_binary '\0',765),(1679151129812,'mBInvoice',_binary '\0','IN-768','2023-01-01 02:00:00.000000',_binary '\0',766),(1679151129824,'modificationLog',_binary '\0',NULL,'2023-03-18 16:52:09.812000',_binary '\0',767),(1679151129909,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:52:09.909000',_binary '\0',769),(1679151129920,'modificationLog',_binary '\0',NULL,'2023-03-18 16:52:09.909000',_binary '\0',770),(1679151266186,'mBInvoice',_binary '\0','IN-773','2023-01-01 02:00:00.000000',_binary '\0',771),(1679151266197,'modificationLog',_binary '\0',NULL,'2023-03-18 16:54:26.186000',_binary '\0',772),(1679151266279,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:54:26.279000',_binary '\0',774),(1679151266290,'modificationLog',_binary '\0',NULL,'2023-03-18 16:54:26.279000',_binary '\0',775),(1679151361094,'mBInvoice',_binary '\0','IN-783','2023-01-01 02:00:00.000000',_binary '\0',781),(1679151361107,'modificationLog',_binary '\0',NULL,'2023-03-18 16:56:01.094000',_binary '\0',782),(1679151361205,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 16:56:01.205000',_binary '\0',784),(1679151361216,'modificationLog',_binary '\0',NULL,'2023-03-18 16:56:01.205000',_binary '\0',785),(1679151624483,'mBInvoice',_binary '\0','IN-793','2023-01-01 02:00:00.000000',_binary '\0',791),(1679151624495,'modificationLog',_binary '\0',NULL,'2023-03-18 17:00:24.483000',_binary '\0',792),(1679151624581,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:00:24.581000',_binary '\0',794),(1679151624594,'modificationLog',_binary '\0',NULL,'2023-03-18 17:00:24.581000',_binary '\0',795),(1679151728990,'mBInvoice',_binary '\0','IN-798','2023-01-01 02:00:00.000000',_binary '\0',796),(1679151729001,'modificationLog',_binary '\0',NULL,'2023-03-18 17:02:08.990000',_binary '\0',797),(1679151729096,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:02:09.096000',_binary '\0',799),(1679151729109,'modificationLog',_binary '\0',NULL,'2023-03-18 17:02:09.096000',_binary '\0',800),(1679151838355,'mBInvoice',_binary '\0','IN-803','2023-01-01 02:00:00.000000',_binary '\0',801),(1679151838364,'modificationLog',_binary '\0',NULL,'2023-03-18 17:03:58.355000',_binary '\0',802),(1679151838430,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:03:58.430000',_binary '\0',804),(1679151838440,'modificationLog',_binary '\0',NULL,'2023-03-18 17:03:58.430000',_binary '\0',805),(1679151926045,'mBInvoice',_binary '\0','IN-808','2023-01-02 02:00:00.000000',_binary '\0',806),(1679151926061,'modificationLog',_binary '\0',NULL,'2023-03-18 17:05:26.045000',_binary '\0',807),(1679151926182,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:05:26.182000',_binary '\0',809),(1679151926195,'modificationLog',_binary '\0',NULL,'2023-03-18 17:05:26.182000',_binary '\0',810),(1679152049788,'mBInvoice',_binary '\0','IN-813','2023-01-06 02:00:00.000000',_binary '\0',811),(1679152049800,'modificationLog',_binary '\0',NULL,'2023-03-18 17:07:29.788000',_binary '\0',812),(1679152049883,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:07:29.883000',_binary '\0',814),(1679152049894,'modificationLog',_binary '\0',NULL,'2023-03-18 17:07:29.883000',_binary '\0',815),(1679152108711,'mBInvoice',_binary '\0','IN-818','2023-01-07 02:00:00.000000',_binary '\0',816),(1679152108723,'modificationLog',_binary '\0',NULL,'2023-03-18 17:08:28.711000',_binary '\0',817),(1679152108813,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:08:28.813000',_binary '\0',819),(1679152108825,'modificationLog',_binary '\0',NULL,'2023-03-18 17:08:28.813000',_binary '\0',820),(1679152151437,'mBInvoice',_binary '\0','IN-823','2023-01-20 02:00:00.000000',_binary '\0',821),(1679152151449,'modificationLog',_binary '\0',NULL,'2023-03-18 17:09:11.437000',_binary '\0',822),(1679152151534,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:09:11.534000',_binary '\0',824),(1679152151544,'modificationLog',_binary '\0',NULL,'2023-03-18 17:09:11.534000',_binary '\0',825),(1679152197347,'mBInvoice',_binary '\0','IN-828','2023-01-12 02:00:00.000000',_binary '\0',826),(1679152197361,'modificationLog',_binary '\0',NULL,'2023-03-18 17:09:57.347000',_binary '\0',827),(1679152197449,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:09:57.449000',_binary '\0',829),(1679152197461,'modificationLog',_binary '\0',NULL,'2023-03-18 17:09:57.449000',_binary '\0',830),(1679152252914,'mBInvoice',_binary '\0','IN-833','2023-01-06 02:00:00.000000',_binary '\0',831),(1679152252924,'modificationLog',_binary '\0',NULL,'2023-03-18 17:10:52.914000',_binary '\0',832),(1679152253012,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:10:53.012000',_binary '\0',834),(1679152253023,'modificationLog',_binary '\0',NULL,'2023-03-18 17:10:53.012000',_binary '\0',835),(1679152292972,'mBInvoice',_binary '\0','IN-838','2023-01-14 02:00:00.000000',_binary '\0',836),(1679152292979,'modificationLog',_binary '\0',NULL,'2023-03-18 17:11:32.972000',_binary '\0',837),(1679152293033,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:11:33.033000',_binary '\0',839),(1679152293040,'modificationLog',_binary '\0',NULL,'2023-03-18 17:11:33.033000',_binary '\0',840),(1679152351966,'mBInvoice',_binary '\0','IN-843','2023-01-22 02:00:00.000000',_binary '\0',841),(1679152351977,'modificationLog',_binary '\0',NULL,'2023-03-18 17:12:31.966000',_binary '\0',842),(1679152352064,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:12:32.064000',_binary '\0',844),(1679152352078,'modificationLog',_binary '\0',NULL,'2023-03-18 17:12:32.064000',_binary '\0',845),(1679152426977,'mBInvoice',_binary '\0','IN-848','2023-01-20 02:00:00.000000',_binary '\0',846),(1679152426987,'modificationLog',_binary '\0',NULL,'2023-03-18 17:13:46.977000',_binary '\0',847),(1679152427096,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:13:47.096000',_binary '\0',849),(1679152427110,'modificationLog',_binary '\0',NULL,'2023-03-18 17:13:47.096000',_binary '\0',850),(1679152544046,'mBInvoice',_binary '\0','IN-853','2023-01-21 02:00:00.000000',_binary '\0',851),(1679152544056,'modificationLog',_binary '\0',NULL,'2023-03-18 17:15:44.046000',_binary '\0',852),(1679152544161,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:15:44.161000',_binary '\0',854),(1679152544172,'modificationLog',_binary '\0',NULL,'2023-03-18 17:15:44.161000',_binary '\0',855),(1679152648839,'mBInvoice',_binary '\0','IN-863','2023-01-02 02:00:00.000000',_binary '\0',861),(1679152648853,'modificationLog',_binary '\0',NULL,'2023-03-18 17:17:28.839000',_binary '\0',862),(1679152648936,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:17:28.936000',_binary '\0',864),(1679152648948,'modificationLog',_binary '\0',NULL,'2023-03-18 17:17:28.936000',_binary '\0',865),(1679152726806,'mBInvoice',_binary '\0','IN-868','2023-02-01 02:00:00.000000',_binary '\0',866),(1679152726818,'modificationLog',_binary '\0',NULL,'2023-03-18 17:18:46.806000',_binary '\0',867),(1679152726901,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:18:46.901000',_binary '\0',869),(1679152726911,'modificationLog',_binary '\0',NULL,'2023-03-18 17:18:46.901000',_binary '\0',870),(1679152810458,'mBClient',_binary '','CLIENT-PATRON','2023-03-18 17:20:10.458000',_binary '\0',871),(1679152810471,'modificationLog',_binary '\0',NULL,'2023-03-18 17:20:10.458000',_binary '\0',872),(1679152881000,'mBClient',_binary '','CLIENT-PATRONNE','2023-03-18 17:21:21.000000',_binary '\0',878),(1679152881011,'modificationLog',_binary '\0',NULL,'2023-03-18 17:21:21.000000',_binary '\0',879),(1679152987192,'mBClient',_binary '','CLIENT-BARTHAZAR','2023-03-18 17:23:07.192000',_binary '\0',885),(1679152987203,'modificationLog',_binary '\0',NULL,'2023-03-18 17:23:07.192000',_binary '\0',886),(1679153056579,'mBInvoice',_binary '\0','IN-889','2023-01-01 02:00:00.000000',_binary '\0',887),(1679153056586,'modificationLog',_binary '\0',NULL,'2023-03-18 17:24:16.579000',_binary '\0',888),(1679153056625,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:24:16.625000',_binary '\0',890),(1679153056629,'modificationLog',_binary '\0',NULL,'2023-03-18 17:24:16.625000',_binary '\0',891),(1679153112347,'mBInvoice',_binary '\0','IN-894','2023-01-06 02:00:00.000000',_binary '\0',892),(1679153112357,'modificationLog',_binary '\0',NULL,'2023-03-18 17:25:12.347000',_binary '\0',893),(1679153112454,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:25:12.454000',_binary '\0',895),(1679153112466,'modificationLog',_binary '\0',NULL,'2023-03-18 17:25:12.454000',_binary '\0',896),(1679153193643,'mBInvoice',_binary '\0','IN-899','2023-01-07 02:00:00.000000',_binary '\0',897),(1679153193654,'modificationLog',_binary '\0',NULL,'2023-03-18 17:26:33.643000',_binary '\0',898),(1679153193743,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:26:33.743000',_binary '\0',900),(1679153193753,'modificationLog',_binary '\0',NULL,'2023-03-18 17:26:33.743000',_binary '\0',901),(1679153453632,'mBInvoice',_binary '\0','IN-904','2023-02-10 02:00:00.000000',_binary '\0',902),(1679153453644,'modificationLog',_binary '\0',NULL,'2023-03-18 17:30:53.632000',_binary '\0',903),(1679153453724,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:30:53.724000',_binary '\0',905),(1679153453736,'modificationLog',_binary '\0',NULL,'2023-03-18 17:30:53.724000',_binary '\0',906),(1679153570784,'mBInvoice',_binary '\0','IN-909','2023-02-16 02:00:00.000000',_binary '\0',907),(1679153570795,'modificationLog',_binary '\0',NULL,'2023-03-18 17:32:50.784000',_binary '\0',908),(1679153570872,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:32:50.872000',_binary '\0',910),(1679153570883,'modificationLog',_binary '\0',NULL,'2023-03-18 17:32:50.872000',_binary '\0',911),(1679153736570,'mBInvoice',_binary '\0','IN-914','2023-02-17 02:00:00.000000',_binary '\0',912),(1679153736580,'modificationLog',_binary '\0',NULL,'2023-03-18 17:35:36.570000',_binary '\0',913),(1679153736664,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:35:36.664000',_binary '\0',915),(1679153736676,'modificationLog',_binary '\0',NULL,'2023-03-18 17:35:36.664000',_binary '\0',916),(1679153780614,'mBClient',_binary '','CAMPANY-FPP','2023-03-18 17:36:20.614000',_binary '\0',917),(1679153780625,'modificationLog',_binary '\0',NULL,'2023-03-18 17:36:20.614000',_binary '\0',918),(1679153839190,'mBInvoice',_binary '\0','IN-921','2023-02-10 02:00:00.000000',_binary '\0',919),(1679153839200,'modificationLog',_binary '\0',NULL,'2023-03-18 17:37:19.190000',_binary '\0',920),(1679153839280,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:37:19.280000',_binary '\0',922),(1679153839293,'modificationLog',_binary '\0',NULL,'2023-03-18 17:37:19.280000',_binary '\0',923),(1679153882789,'mBClient',_binary '','CAMPANY-ODEDIM','2023-03-18 17:38:02.789000',_binary '\0',924),(1679153882800,'modificationLog',_binary '\0',NULL,'2023-03-18 17:38:02.789000',_binary '\0',925),(1679153952153,'mBInvoice',_binary '\0','IN-928','2023-02-11 02:00:00.000000',_binary '\0',926),(1679153952162,'modificationLog',_binary '\0',NULL,'2023-03-18 17:39:12.153000',_binary '\0',927),(1679153952240,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:39:12.240000',_binary '\0',929),(1679153952252,'modificationLog',_binary '\0',NULL,'2023-03-18 17:39:12.240000',_binary '\0',930),(1679154031874,'mBInvoice',_binary '\0','IN-933','2023-02-10 02:00:00.000000',_binary '\0',931),(1679154031885,'modificationLog',_binary '\0',NULL,'2023-03-18 17:40:31.874000',_binary '\0',932),(1679154031982,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:40:31.982000',_binary '\0',934),(1679154031992,'modificationLog',_binary '\0',NULL,'2023-03-18 17:40:31.982000',_binary '\0',935),(1679154077320,'mBClient',_binary '','CAMPANY-PROJET-NKURIZA','2023-03-18 17:41:17.320000',_binary '\0',936),(1679154077332,'modificationLog',_binary '\0',NULL,'2023-03-18 17:41:17.320000',_binary '\0',937),(1679154172691,'mBInvoice',_binary '\0','IN-940','2023-02-03 02:00:00.000000',_binary '\0',938),(1679154172703,'modificationLog',_binary '\0',NULL,'2023-03-18 17:42:52.691000',_binary '\0',939),(1679154172799,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:42:52.799000',_binary '\0',941),(1679154172811,'modificationLog',_binary '\0',NULL,'2023-03-18 17:42:52.799000',_binary '\0',942),(1679154215342,'mBClient',_binary '','CAMPANY-MFPTE','2023-03-18 17:43:35.342000',_binary '\0',943),(1679154215352,'modificationLog',_binary '\0',NULL,'2023-03-18 17:43:35.342000',_binary '\0',944),(1679154284759,'mBInvoice',_binary '\0','IN-947','2023-02-18 02:00:00.000000',_binary '\0',945),(1679154284770,'modificationLog',_binary '\0',NULL,'2023-03-18 17:44:44.759000',_binary '\0',946),(1679154284870,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:44:44.870000',_binary '\0',948),(1679154284881,'modificationLog',_binary '\0',NULL,'2023-03-18 17:44:44.870000',_binary '\0',949),(1679154358650,'mBInvoice',_binary '\0','IN-952','2023-02-25 02:00:00.000000',_binary '\0',950),(1679154358663,'modificationLog',_binary '\0',NULL,'2023-03-18 17:45:58.650000',_binary '\0',951),(1679154358743,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:45:58.743000',_binary '\0',953),(1679154358753,'modificationLog',_binary '\0',NULL,'2023-03-18 17:45:58.743000',_binary '\0',954),(1679154435578,'mBInvoice',_binary '\0','IN-957','2023-02-04 02:00:00.000000',_binary '\0',955),(1679154435588,'modificationLog',_binary '\0',NULL,'2023-03-18 17:47:15.578000',_binary '\0',956),(1679154435721,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:47:15.721000',_binary '\0',958),(1679154435730,'modificationLog',_binary '\0',NULL,'2023-03-18 17:47:15.721000',_binary '\0',959),(1679154513222,'mBClient',_binary '','CAMPANY-OIM','2023-03-18 17:48:33.222000',_binary '\0',965),(1679154513231,'modificationLog',_binary '\0',NULL,'2023-03-18 17:48:33.222000',_binary '\0',966),(1679154585030,'mBInvoice',_binary '\0','IN-969','2023-02-19 02:00:00.000000',_binary '\0',967),(1679154585039,'modificationLog',_binary '\0',NULL,'2023-03-18 17:49:45.030000',_binary '\0',968),(1679154585119,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:49:45.119000',_binary '\0',970),(1679154585131,'modificationLog',_binary '\0',NULL,'2023-03-18 17:49:45.119000',_binary '\0',971),(1679154638320,'mBInvoice',_binary '\0','IN-974','2023-02-13 02:00:00.000000',_binary '\0',972),(1679154638330,'modificationLog',_binary '\0',NULL,'2023-03-18 17:50:38.320000',_binary '\0',973),(1679154638412,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:50:38.412000',_binary '\0',975),(1679154638423,'modificationLog',_binary '\0',NULL,'2023-03-18 17:50:38.412000',_binary '\0',976),(1679154683848,'mBInvoice',_binary '\0','IN-979','2023-02-24 02:00:00.000000',_binary '\0',977),(1679154683858,'modificationLog',_binary '\0',NULL,'2023-03-18 17:51:23.848000',_binary '\0',978),(1679154683938,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:51:23.938000',_binary '\0',980),(1679154683950,'modificationLog',_binary '\0',NULL,'2023-03-18 17:51:23.938000',_binary '\0',981),(1679154721973,'mBClient',_binary '','CAMPANY-SOS','2023-03-18 17:52:01.973000',_binary '\0',982),(1679154721984,'modificationLog',_binary '\0',NULL,'2023-03-18 17:52:01.973000',_binary '\0',983),(1679154747632,'mBInvoice',_binary '\0','IN-986','2023-02-19 02:00:00.000000',_binary '\0',984),(1679154747643,'modificationLog',_binary '\0',NULL,'2023-03-18 17:52:27.632000',_binary '\0',985),(1679154747725,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:52:27.725000',_binary '\0',987),(1679154747737,'modificationLog',_binary '\0',NULL,'2023-03-18 17:52:27.725000',_binary '\0',988),(1679154793149,'mBInvoice',_binary '\0','IN-991','2023-02-03 02:00:00.000000',_binary '\0',989),(1679154793159,'modificationLog',_binary '\0',NULL,'2023-03-18 17:53:13.149000',_binary '\0',990),(1679154793250,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:53:13.250000',_binary '\0',992),(1679154793260,'modificationLog',_binary '\0',NULL,'2023-03-18 17:53:13.250000',_binary '\0',993),(1679154997882,'mBInvoice',_binary '\0','IN-996','2023-02-24 02:00:00.000000',_binary '\0',994),(1679154997888,'modificationLog',_binary '\0',NULL,'2023-03-18 17:56:37.882000',_binary '\0',995),(1679154997912,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:56:37.912000',_binary '\0',997),(1679154997912,'modificationLog',_binary '\0',NULL,'2023-03-18 17:56:37.912000',_binary '\0',998),(1679155047432,'mBClient',_binary '','CLIENT-ADCOM-GASORWE','2023-03-18 17:57:27.432000',_binary '\0',999),(1679155047435,'modificationLog',_binary '\0',NULL,'2023-03-18 17:57:27.432000',_binary '\0',1000),(1679155127390,'mBInvoice',_binary '\0','IN-1003','2023-02-12 02:00:00.000000',_binary '\0',1001),(1679155127396,'modificationLog',_binary '\0',NULL,'2023-03-18 17:58:47.390000',_binary '\0',1002),(1679155127430,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 17:58:47.430000',_binary '\0',1004),(1679155127433,'modificationLog',_binary '\0',NULL,'2023-03-18 17:58:47.430000',_binary '\0',1005),(1679155182558,'mBClient',_binary '','CLIENT-COMMUNE-MUYINGA','2023-03-18 17:59:42.558000',_binary '\0',1006),(1679155182567,'modificationLog',_binary '\0',NULL,'2023-03-18 17:59:42.558000',_binary '\0',1007),(1679155231140,'mBInvoice',_binary '\0','IN-1010','2023-02-25 02:00:00.000000',_binary '\0',1008),(1679155231152,'modificationLog',_binary '\0',NULL,'2023-03-18 18:00:31.140000',_binary '\0',1009),(1679155231226,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 18:00:31.227000',_binary '\0',1011),(1679155231237,'modificationLog',_binary '\0',NULL,'2023-03-18 18:00:31.227000',_binary '\0',1012),(1679155297685,'mBClient',_binary '','CLIENT-SHABANI-CUMBA','2023-03-18 18:01:37.685000',_binary '\0',1013),(1679155297699,'modificationLog',_binary '\0',NULL,'2023-03-18 18:01:37.685000',_binary '\0',1014),(1679155325791,'mBInvoice',_binary '\0','IN-1017','2023-02-19 02:00:00.000000',_binary '\0',1015),(1679155325801,'modificationLog',_binary '\0',NULL,'2023-03-18 18:02:05.791000',_binary '\0',1016),(1679155325876,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 18:02:05.876000',_binary '\0',1018),(1679155325887,'modificationLog',_binary '\0',NULL,'2023-03-18 18:02:05.876000',_binary '\0',1019),(1679155366433,'mBInvoice',_binary '\0','IN-1022','2023-02-26 02:00:00.000000',_binary '\0',1020),(1679155366443,'modificationLog',_binary '\0',NULL,'2023-03-18 18:02:46.433000',_binary '\0',1021),(1679155366527,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 18:02:46.527000',_binary '\0',1023),(1679155366540,'modificationLog',_binary '\0',NULL,'2023-03-18 18:02:46.527000',_binary '\0',1024),(1679155445883,'mBInvoice',_binary '\0','IN-1027','2023-02-28 02:00:00.000000',_binary '\0',1025),(1679155445894,'modificationLog',_binary '\0',NULL,'2023-03-18 18:04:05.883000',_binary '\0',1026),(1679155445969,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 18:04:05.969000',_binary '\0',1028),(1679155445980,'modificationLog',_binary '\0',NULL,'2023-03-18 18:04:05.969000',_binary '\0',1029),(1679155485815,'mBClient',_binary '','CLIENT-NEILLA','2023-03-18 18:04:45.815000',_binary '\0',1030),(1679155485825,'modificationLog',_binary '\0',NULL,'2023-03-18 18:04:45.815000',_binary '\0',1031),(1679155521346,'mBInvoice',_binary '\0','IN-1034','2023-02-28 02:00:00.000000',_binary '\0',1032),(1679155521357,'modificationLog',_binary '\0',NULL,'2023-03-18 18:05:21.346000',_binary '\0',1033),(1679155521434,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 18:05:21.434000',_binary '\0',1035),(1679155521447,'modificationLog',_binary '\0',NULL,'2023-03-18 18:05:21.434000',_binary '\0',1036),(1679157697891,'mBInvoice',_binary '\0','IN-1039','2023-02-17 02:00:00.000000',_binary '\0',1037),(1679157697892,'modificationLog',_binary '\0',NULL,'2023-03-18 18:41:37.891000',_binary '\0',1038),(1679157697926,'mBCapitalEntry',_binary '\0',NULL,'2023-03-18 18:41:37.926000',_binary '\0',1040),(1679157697926,'modificationLog',_binary '\0',NULL,'2023-03-18 18:41:37.926000',_binary '\0',1041);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_modification_logs`
--

DROP TABLE IF EXISTS `item_modification_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_modification_logs` (
  `item_date_created` bigint NOT NULL DEFAULT '0',
  `item_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `item_t_key` bigint NOT NULL DEFAULT '0',
  `modification_logs_date_created` bigint NOT NULL DEFAULT '0',
  `modification_logs_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `modification_logs_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_date_created`,`item_item_type`,`item_t_key`,`modification_logs_date_created`,`modification_logs_item_type`,`modification_logs_t_key`),
  UNIQUE KEY `UK_no8pm65h5034dwqrgcp6s74nj` (`modification_logs_date_created`,`modification_logs_item_type`,`modification_logs_t_key`),
  CONSTRAINT `FKq72s4mggd67qp81kq1v4m23fb` FOREIGN KEY (`item_date_created`, `item_item_type`, `item_t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKsv93w5thfrslfax3qtd6tan5e` FOREIGN KEY (`modification_logs_date_created`, `modification_logs_item_type`, `modification_logs_t_key`) REFERENCES `modification_log` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_modification_logs`
--

LOCK TABLES `item_modification_logs` WRITE;
/*!40000 ALTER TABLE `item_modification_logs` DISABLE KEYS */;
INSERT INTO `item_modification_logs` VALUES (1679147735693,'mBCategory',1,1679147735700,'modificationLog',2),(1679147735910,'mBCategory',3,1679147735919,'modificationLog',4),(1679147735964,'mBCategory',5,1679147735975,'modificationLog',6),(1679147736010,'mBCategory',7,1679147736016,'modificationLog',8),(1679147736048,'mBCategory',9,1679147736053,'modificationLog',10),(1679147736083,'mBCategory',11,1679147736088,'modificationLog',12),(1679147736120,'mBCategory',13,1679147736125,'modificationLog',14),(1679147736278,'mBCategory',15,1679147736284,'modificationLog',16),(1679147736315,'mBCategory',17,1679147736322,'modificationLog',18),(1679147736356,'mBCategory',19,1679147736362,'modificationLog',20),(1679147736394,'mBCategory',21,1679147736399,'modificationLog',22),(1679147736427,'mBCategory',23,1679147736433,'modificationLog',24),(1679147736525,'mBCategory',25,1679147736531,'modificationLog',26),(1679147736564,'mBCategory',27,1679147736570,'modificationLog',28),(1679147736601,'mBCategory',29,1679147736606,'modificationLog',30),(1679147736642,'mBCategory',31,1679147736647,'modificationLog',32),(1679147736677,'mBCategory',33,1679147736686,'modificationLog',34),(1679147736711,'mBCategory',35,1679147736716,'modificationLog',36),(1679147736746,'mBCategory',37,1679147736752,'modificationLog',38),(1679147736779,'mBCategory',39,1679147736785,'modificationLog',40),(1679147736812,'mBCategory',41,1679147736817,'modificationLog',42),(1679147736930,'mBClient',43,1679147736936,'modificationLog',44),(1679147736985,'mBClient',45,1679147736991,'modificationLog',46),(1679147737022,'mBClient',47,1679147737027,'modificationLog',48),(1679147737057,'mBClient',49,1679147737063,'modificationLog',50),(1679147737095,'mBClient',51,1679147737100,'modificationLog',52),(1679147737131,'mBClient',53,1679147737136,'modificationLog',54),(1679147737160,'mBClient',55,1679147737166,'modificationLog',56),(1679147737194,'mBClient',57,1679147737199,'modificationLog',58),(1679147737233,'mBClient',59,1679147737239,'modificationLog',60),(1679147737270,'mBClient',61,1679147737275,'modificationLog',62),(1679147737305,'mBClient',63,1679147737311,'modificationLog',64),(1679147737342,'mBClient',65,1679147737346,'modificationLog',66),(1679147737371,'mBClient',67,1679147737376,'modificationLog',68),(1679147737407,'mBClient',69,1679147737412,'modificationLog',70),(1679147737442,'mBClient',71,1679147737447,'modificationLog',72),(1679147737479,'mBClient',73,1679147737484,'modificationLog',74),(1679147737512,'mBClient',75,1679147737517,'modificationLog',76),(1679147737547,'mBClient',77,1679147737552,'modificationLog',78),(1679147737577,'mBClient',79,1679147737582,'modificationLog',80),(1679147737608,'mBClient',81,1679147737614,'modificationLog',82),(1679147737637,'mBClient',83,1679147737642,'modificationLog',84),(1679147737666,'mBClient',85,1679147737670,'modificationLog',86),(1679147737864,'mBFacility',87,1679147737869,'modificationLog',88),(1679147737888,'mBCapital',89,1679147737893,'modificationLog',90),(1679147737920,'mBCapitalEntry',91,1679147737926,'modificationLog',92),(1679147737984,'mBFacility',93,1679147737989,'modificationLog',94),(1679147737998,'mBCapital',95,1679147738003,'modificationLog',96),(1679147738016,'mBCapitalEntry',97,1679147738020,'modificationLog',98),(1679147738076,'mBFacility',99,1679147738081,'modificationLog',100),(1679147738092,'mBCapital',101,1679147738098,'modificationLog',102),(1679147738108,'mBCapitalEntry',103,1679147738113,'modificationLog',104),(1679147738176,'mBInventory',105,1679147738181,'modificationLog',106),(1679147738231,'mBInventory',107,1679147738237,'modificationLog',108),(1679147738263,'mBInventory',109,1679147738266,'modificationLog',110),(1679147738294,'mBInventory',111,1679147738299,'modificationLog',112),(1679147738329,'mBInventory',113,1679147738334,'modificationLog',114),(1679147738363,'mBInventory',115,1679147738368,'modificationLog',116),(1679147738394,'mBInventory',117,1679147738399,'modificationLog',118),(1679147738423,'mBInventory',119,1679147738428,'modificationLog',120),(1679147738451,'mBInventory',121,1679147738455,'modificationLog',122),(1679147738507,'mBInventory',123,1679147738513,'modificationLog',124),(1679147738539,'mBInventory',125,1679147738544,'modificationLog',126),(1679147738571,'mBInventory',127,1679147738577,'modificationLog',128),(1679147738606,'mBInventory',129,1679147738611,'modificationLog',130),(1679147738637,'mBInventory',131,1679147738641,'modificationLog',132),(1679147738672,'mBInventory',133,1679147738677,'modificationLog',134),(1679147738707,'mBInventory',135,1679147738713,'modificationLog',136),(1679147738737,'mBInventory',137,1679147738742,'modificationLog',138),(1679147738763,'mBInventory',139,1679147738768,'modificationLog',140),(1679147738798,'mBInventory',141,1679147738803,'modificationLog',142),(1679147738828,'mBInventory',143,1679147738833,'modificationLog',144),(1679147738860,'mBInventory',145,1679147738864,'modificationLog',146),(1679147738891,'mBInventory',147,1679147738896,'modificationLog',148),(1679147738924,'mBInventory',149,1679147738929,'modificationLog',150),(1679147738959,'mBInventory',151,1679147738964,'modificationLog',152),(1679147738995,'mBInventory',153,1679147739001,'modificationLog',154),(1679147739031,'mBInventory',155,1679147739036,'modificationLog',156),(1679147739065,'mBInventory',157,1679147739070,'modificationLog',158),(1679147739098,'mBInventory',159,1679147739105,'modificationLog',160),(1679147739137,'mBInventory',161,1679147739142,'modificationLog',162),(1679147739168,'mBInventory',163,1679147739173,'modificationLog',164),(1679147739196,'mBInventory',165,1679147739201,'modificationLog',166),(1679147739224,'mBInventory',167,1679147739230,'modificationLog',168),(1679147739256,'mBInventory',169,1679147739261,'modificationLog',170),(1679147739283,'mBInventory',171,1679147739287,'modificationLog',172),(1679147739313,'mBInventory',173,1679147739318,'modificationLog',174),(1679147739341,'mBInventory',175,1679147739346,'modificationLog',176),(1679147739373,'mBInventory',177,1679147739377,'modificationLog',178),(1679147739396,'mBInventory',179,1679147739401,'modificationLog',180),(1679147739424,'mBInventory',181,1679147739429,'modificationLog',182),(1679147739453,'mBInventory',183,1679147739460,'modificationLog',184),(1679147739488,'mBInventory',185,1679147739493,'modificationLog',186),(1679147739524,'mBInventory',187,1679147739529,'modificationLog',188),(1679147739558,'mBInventory',189,1679147739564,'modificationLog',190),(1679147739591,'mBInventory',191,1679147739597,'modificationLog',192),(1679147739629,'mBInventory',193,1679147739635,'modificationLog',194),(1679147739661,'mBInventory',195,1679147739666,'modificationLog',196),(1679147739695,'mBInventory',197,1679147739700,'modificationLog',198),(1679147739723,'mBInventory',199,1679147739728,'modificationLog',200),(1679147739747,'mBInventory',201,1679147739751,'modificationLog',202),(1679147739771,'mBInventory',203,1679147739776,'modificationLog',204),(1679147739802,'mBInventory',205,1679147739806,'modificationLog',206),(1679147739826,'mBInventory',207,1679147739831,'modificationLog',208),(1679147739850,'mBInventory',209,1679147739855,'modificationLog',210),(1679147739878,'mBInventory',211,1679147739883,'modificationLog',212),(1679147739909,'mBInventory',213,1679147739913,'modificationLog',214),(1679147739940,'mBInventory',215,1679147739945,'modificationLog',216),(1679147739970,'mBInventory',217,1679147739975,'modificationLog',218),(1679147739996,'mBInventory',219,1679147739999,'modificationLog',220),(1679147740019,'mBInventory',221,1679147740024,'modificationLog',222),(1679147740045,'mBInventory',223,1679147740049,'modificationLog',224),(1679147740071,'mBInventory',225,1679147740077,'modificationLog',226),(1679147740103,'mBInventory',227,1679147740109,'modificationLog',228),(1679147740137,'mBInventory',229,1679147740142,'modificationLog',230),(1679147740165,'mBInventory',231,1679147740169,'modificationLog',232),(1679147740194,'mBInventory',233,1679147740199,'modificationLog',234),(1679147740219,'mBInventory',235,1679147740224,'modificationLog',236),(1679147741247,'mBInventory',237,1679147741253,'modificationLog',238),(1679147741293,'mBInventory',239,1679147741299,'modificationLog',240),(1679147741326,'mBInventory',241,1679147741333,'modificationLog',242),(1679147741373,'mBInventory',243,1679147741380,'modificationLog',244),(1679147741423,'mBInventory',245,1679147741431,'modificationLog',246),(1679147741469,'mBInventory',247,1679147741477,'modificationLog',248),(1679147741521,'mBInventory',249,1679147741529,'modificationLog',250),(1679147741564,'mBInventory',251,1679147741570,'modificationLog',252),(1679147741608,'mBInventory',253,1679147741615,'modificationLog',254),(1679147741650,'mBInventory',255,1679147741658,'modificationLog',256),(1679147741721,'mBInventory',257,1679147741731,'modificationLog',258),(1679147741767,'mBInventory',259,1679147741774,'modificationLog',260),(1679147741810,'mBInventory',261,1679147741817,'modificationLog',262),(1679147741851,'mBInventory',263,1679147741858,'modificationLog',264),(1679147741891,'mBInventory',265,1679147741897,'modificationLog',266),(1679147741934,'mBInventory',267,1679147741941,'modificationLog',268),(1679147741987,'mBInventory',269,1679147741993,'modificationLog',270),(1679147742018,'mBInventory',271,1679147742023,'modificationLog',272),(1679147742050,'mBInventory',273,1679147742055,'modificationLog',274),(1679147742076,'mBInventory',275,1679147742080,'modificationLog',276),(1679147742107,'mBInventory',277,1679147742112,'modificationLog',278),(1679147742132,'mBInventory',279,1679147742136,'modificationLog',280),(1679147742154,'mBInventory',281,1679147742158,'modificationLog',282),(1679147742194,'mBInventory',283,1679147742201,'modificationLog',284),(1679147742244,'mBInventory',285,1679147742251,'modificationLog',286),(1679147742298,'mBInventory',287,1679147742306,'modificationLog',288),(1679147742362,'mBInventory',289,1679147742369,'modificationLog',290),(1679147742410,'mBInventory',291,1679147742415,'modificationLog',292),(1679147742438,'mBInventory',293,1679147742445,'modificationLog',294),(1679147742488,'mBInventory',295,1679147742494,'modificationLog',296),(1679147742536,'mBInventory',297,1679147742544,'modificationLog',298),(1679147742587,'mBInventory',299,1679147742594,'modificationLog',300),(1679147742641,'mBInventory',301,1679147742648,'modificationLog',302),(1679147742700,'mBInventory',303,1679147742707,'modificationLog',304),(1679147742749,'mBInventory',305,1679147742757,'modificationLog',306),(1679147742803,'mBInventory',307,1679147742810,'modificationLog',308),(1679147742853,'mBInventory',309,1679147742860,'modificationLog',310),(1679147742924,'mBInventory',311,1679147742932,'modificationLog',312),(1679147742974,'mBInventory',313,1679147742981,'modificationLog',314),(1679147743649,'mBInventory',315,1679147743655,'modificationLog',316),(1679147743678,'mBInventory',317,1679147743683,'modificationLog',318),(1679147743705,'mBInventory',319,1679147743710,'modificationLog',320),(1679147743731,'mBInventory',321,1679147743737,'modificationLog',322),(1679147743760,'mBInventory',323,1679147743765,'modificationLog',324),(1679147743793,'mBInventory',325,1679147743798,'modificationLog',326),(1679147743829,'mBInventory',327,1679147743835,'modificationLog',328),(1679147743860,'mBInventory',329,1679147743865,'modificationLog',330),(1679147743893,'mBInventory',331,1679147743898,'modificationLog',332),(1679147743925,'mBInventory',333,1679147743930,'modificationLog',334),(1679147743956,'mBInventory',335,1679147743962,'modificationLog',336),(1679147743991,'mBInventory',337,1679147743996,'modificationLog',338),(1679147744022,'mBInventory',339,1679147744028,'modificationLog',340),(1679147744052,'mBInventory',341,1679147744057,'modificationLog',342),(1679147744086,'mBInventory',343,1679147744092,'modificationLog',344),(1679147744116,'mBInventory',345,1679147744122,'modificationLog',346),(1679147744149,'mBInventory',347,1679147744156,'modificationLog',348),(1679147744180,'mBInventory',349,1679147744187,'modificationLog',350),(1679147744212,'mBInventory',351,1679147744217,'modificationLog',352),(1679147744244,'mBInventory',353,1679147744249,'modificationLog',354),(1679147744281,'mBInventory',355,1679147744287,'modificationLog',356),(1679147744311,'mBInventory',357,1679147744317,'modificationLog',358),(1679147744341,'mBInventory',359,1679147744346,'modificationLog',360),(1679147744373,'mBInventory',361,1679147744379,'modificationLog',362),(1679147744417,'mBInventory',363,1679147744424,'modificationLog',364),(1679147744449,'mBInventory',365,1679147744453,'modificationLog',366),(1679147744510,'mBInventory',367,1679147744517,'modificationLog',368),(1679147744541,'mBInventory',369,1679147744547,'modificationLog',370),(1679147744571,'mBInventory',371,1679147744578,'modificationLog',372),(1679147744602,'mBInventory',373,1679147744607,'modificationLog',374),(1679147745074,'mBInventory',375,1679147745079,'modificationLog',376),(1679147745107,'mBInventory',377,1679147745112,'modificationLog',378),(1679147745142,'mBInventory',379,1679147745147,'modificationLog',380),(1679147745177,'mBInventory',381,1679147745182,'modificationLog',382),(1679147745208,'mBInventory',383,1679147745214,'modificationLog',384),(1679147745250,'mBInventory',385,1679147745256,'modificationLog',386),(1679147745280,'mBInventory',387,1679147745286,'modificationLog',388),(1679147745312,'mBInventory',389,1679147745318,'modificationLog',390),(1679147745347,'mBInventory',391,1679147745352,'modificationLog',392),(1679147745397,'mBInventory',393,1679147745403,'modificationLog',394),(1679147745431,'mBInventory',395,1679147745436,'modificationLog',396),(1679147745459,'mBInventory',397,1679147745465,'modificationLog',398),(1679147745490,'mBInventory',399,1679147745495,'modificationLog',400),(1679147745519,'mBInventory',401,1679147745524,'modificationLog',402),(1679147745551,'mBInventory',403,1679147745555,'modificationLog',404),(1679147745579,'mBInventory',405,1679147745583,'modificationLog',406),(1679147745601,'mBInventory',407,1679147745607,'modificationLog',408),(1679147745646,'mBInventory',409,1679147745651,'modificationLog',410),(1679147745675,'mBInventory',411,1679147745680,'modificationLog',412),(1679147745719,'mBInventory',413,1679147745725,'modificationLog',414),(1679147745748,'mBInventory',415,1679147745753,'modificationLog',416),(1679147745776,'mBInventory',417,1679147745781,'modificationLog',418),(1679147745806,'mBInventory',419,1679147745810,'modificationLog',420),(1679147745838,'mBInventory',421,1679147745843,'modificationLog',422),(1679147745863,'mBInventory',423,1679147745867,'modificationLog',424),(1679147745887,'mBInventory',425,1679147745892,'modificationLog',426),(1679147745920,'mBInventory',427,1679147745924,'modificationLog',428),(1679147745945,'mBInventory',429,1679147745949,'modificationLog',430),(1679147745969,'mBInventory',431,1679147745973,'modificationLog',432),(1679147745993,'mBInventory',433,1679147745998,'modificationLog',434),(1679147746030,'mBInventory',435,1679147746034,'modificationLog',436),(1679147746056,'mBInventory',437,1679147746062,'modificationLog',438),(1679147746081,'mBInventory',439,1679147746085,'modificationLog',440),(1679147746811,'mBInventory',441,1679147746818,'modificationLog',442),(1679147746857,'mBInventory',443,1679147746863,'modificationLog',444),(1679147746896,'mBInventory',445,1679147746903,'modificationLog',446),(1679147746937,'mBInventory',447,1679147746943,'modificationLog',448),(1679147746992,'mBInventory',449,1679147746998,'modificationLog',450),(1679147747032,'mBInventory',451,1679147747039,'modificationLog',452),(1679147747071,'mBInventory',453,1679147747079,'modificationLog',454),(1679147747113,'mBInventory',455,1679147747120,'modificationLog',456),(1679147747154,'mBInventory',457,1679147747161,'modificationLog',458),(1679147747196,'mBInventory',459,1679147747202,'modificationLog',460),(1679147747235,'mBInventory',461,1679147747242,'modificationLog',462),(1679147747281,'mBInventory',463,1679147747288,'modificationLog',464),(1679147747324,'mBInventory',465,1679147747331,'modificationLog',466),(1679147747365,'mBInventory',467,1679147747372,'modificationLog',468),(1679147747405,'mBInventory',469,1679147747411,'modificationLog',470),(1679147747463,'mBInventory',471,1679147747468,'modificationLog',472),(1679147747491,'mBInventory',473,1679147747497,'modificationLog',474),(1679147747519,'mBInventory',475,1679147747523,'modificationLog',476),(1679147747550,'mBInventory',477,1679147747556,'modificationLog',478),(1679147747577,'mBInventory',479,1679147747581,'modificationLog',480),(1679147747602,'mBInventory',481,1679147747607,'modificationLog',482),(1679147747629,'mBInventory',483,1679147747633,'modificationLog',484),(1679147747659,'mBInventory',485,1679147747664,'modificationLog',486),(1679147747687,'mBInventory',487,1679147747692,'modificationLog',488),(1679147748133,'mBInventory',489,1679147748139,'modificationLog',490),(1679147748170,'mBInventory',491,1679147748175,'modificationLog',492),(1679147748196,'mBInventory',493,1679147748201,'modificationLog',494),(1679147748223,'mBInventory',495,1679147748229,'modificationLog',496),(1679147748272,'mBInventory',497,1679147748277,'modificationLog',498),(1679147748302,'mBInventory',499,1679147748307,'modificationLog',500),(1679147748336,'mBInventory',501,1679147748342,'modificationLog',502),(1679147748367,'mBInventory',503,1679147748373,'modificationLog',504),(1679147748400,'mBInventory',505,1679147748406,'modificationLog',506),(1679147748431,'mBInventory',507,1679147748438,'modificationLog',508),(1679147748463,'mBInventory',509,1679147748469,'modificationLog',510),(1679147748492,'mBInventory',511,1679147748499,'modificationLog',512),(1679147748529,'mBInventory',513,1679147748535,'modificationLog',514),(1679147748564,'mBInventory',515,1679147748571,'modificationLog',516),(1679147748596,'mBInventory',517,1679147748601,'modificationLog',518),(1679147748625,'mBInventory',519,1679147748630,'modificationLog',520),(1679147748656,'mBInventory',521,1679147748663,'modificationLog',522),(1679147748688,'mBInventory',523,1679147748694,'modificationLog',524),(1679147748720,'mBInventory',525,1679147748726,'modificationLog',526),(1679147748752,'mBInventory',527,1679147748759,'modificationLog',528),(1679147748786,'mBInventory',529,1679147748791,'modificationLog',530),(1679147748838,'mBInventory',531,1679147748843,'modificationLog',532),(1679147748869,'mBInventory',533,1679147748875,'modificationLog',534),(1679147748899,'mBInventory',535,1679147748904,'modificationLog',536),(1679147748929,'mBInventory',537,1679147748935,'modificationLog',538),(1679147748961,'mBInventory',539,1679147748967,'modificationLog',540),(1679147748990,'mBInventory',541,1679147748996,'modificationLog',542),(1679147749020,'mBInventory',543,1679147749027,'modificationLog',544),(1679147749051,'mBInventory',545,1679147749057,'modificationLog',546),(1679147749084,'mBInventory',547,1679147749090,'modificationLog',548),(1679147749116,'mBInventory',549,1679147749122,'modificationLog',550),(1679147749147,'mBInventory',551,1679147749152,'modificationLog',552),(1679147749195,'mBInventory',553,1679147749200,'modificationLog',554),(1679147749228,'mBInventory',555,1679147749233,'modificationLog',556),(1679147749256,'mBInventory',557,1679147749261,'modificationLog',558),(1679147749304,'mBInventory',559,1679147749310,'modificationLog',560),(1679147749344,'mBInventory',561,1679147749349,'modificationLog',562),(1679147749373,'mBInventory',563,1679147749379,'modificationLog',564),(1679147749404,'mBInventory',565,1679147749411,'modificationLog',566),(1679147749436,'mBInventory',567,1679147749442,'modificationLog',568),(1679147749467,'mBInventory',569,1679147749472,'modificationLog',570),(1679147749496,'mBInventory',571,1679147749502,'modificationLog',572),(1679147750335,'mBInventory',573,1679147750341,'modificationLog',574),(1679147750377,'mBInventory',575,1679147750384,'modificationLog',576),(1679147750411,'mBInventory',577,1679147750417,'modificationLog',578),(1679147750536,'mBInventory',579,1679147750541,'modificationLog',580),(1679147750579,'mBInventory',581,1679147750585,'modificationLog',582),(1679147750627,'mBInventory',583,1679147750633,'modificationLog',584),(1679147750789,'mBInventory',585,1679147750793,'modificationLog',586),(1679147750821,'mBInventory',587,1679147750827,'modificationLog',588),(1679147750902,'mBInventory',589,1679147750906,'modificationLog',590),(1679147750926,'mBInventory',591,1679147750932,'modificationLog',592),(1679147750996,'mBInventory',593,1679147751003,'modificationLog',594),(1679147751023,'mBInventory',595,1679147751028,'modificationLog',596),(1679147751055,'mBInventory',597,1679147751059,'modificationLog',598),(1679147751081,'mBInventory',599,1679147751086,'modificationLog',600),(1679147751111,'mBInventory',601,1679147751116,'modificationLog',602),(1679147751269,'mBInventory',603,1679147751276,'modificationLog',604),(1679147751314,'mBInventory',605,1679147751321,'modificationLog',606),(1679147751359,'mBInventory',607,1679147751366,'modificationLog',608),(1679147751403,'mBInventory',609,1679147751411,'modificationLog',610),(1679147751595,'mBInventory',611,1679147751601,'modificationLog',612),(1679147751630,'mBInventory',613,1679147751636,'modificationLog',614),(1679147751697,'mBInventory',615,1679147751702,'modificationLog',616),(1679147751724,'mBInventory',617,1679147751729,'modificationLog',618),(1679147751759,'mBInventory',619,1679147751763,'modificationLog',620),(1679147751789,'mBInventory',621,1679147751792,'modificationLog',622),(1679147751821,'mBInventory',623,1679147751825,'modificationLog',624),(1679147751855,'mBInventory',625,1679147751860,'modificationLog',626),(1679147751887,'mBInventory',627,1679147751892,'modificationLog',628),(1679147751915,'mBInventory',629,1679147751920,'modificationLog',630),(1679147752089,'mBInventory',631,1679147752094,'modificationLog',632),(1679147752115,'mBInventory',633,1679147752118,'modificationLog',634),(1679147752146,'mBInventory',635,1679147752151,'modificationLog',636),(1679147752177,'mBInventory',637,1679147752181,'modificationLog',638),(1679147752208,'mBInventory',639,1679147752211,'modificationLog',640),(1679147752317,'mBInventory',641,1679147752321,'modificationLog',642),(1679147752344,'mBInventory',643,1679147752349,'modificationLog',644),(1679147752415,'mBInventory',645,1679147752422,'modificationLog',646),(1679147752462,'mBInventory',647,1679147752468,'modificationLog',648),(1679147752513,'mBInventory',649,1679147752519,'modificationLog',650),(1679147752557,'mBInventory',651,1679147752563,'modificationLog',652),(1679147752603,'mBInventory',653,1679147752610,'modificationLog',654),(1679147752656,'mBInventory',655,1679147752661,'modificationLog',656),(1679147752680,'mBInventory',657,1679147752685,'modificationLog',658),(1679147752711,'mBInventory',659,1679147752716,'modificationLog',660),(1679147752735,'mBInventory',661,1679147752741,'modificationLog',662),(1679147752912,'mBInventory',663,1679147752916,'modificationLog',664),(1679147752954,'mBInventory',665,1679147752958,'modificationLog',666),(1679147753005,'mBRentProperty',667,1679147753010,'modificationLog',668),(1679147753042,'mBRentProperty',669,1679147753047,'modificationLog',670),(1679147753067,'mBRentProperty',671,1679147753073,'modificationLog',672),(1679147753095,'mBRentProperty',673,1679147753100,'modificationLog',674),(1679147753123,'mBRentProperty',675,1679147753127,'modificationLog',676),(1679148939019,'mBClient',677,1679148939022,'modificationLog',678),(1679148939050,'mBClient',679,1679148939053,'modificationLog',680),(1679148939079,'mBClient',681,1679148939082,'modificationLog',682),(1679148939107,'mBClient',683,1679148939111,'modificationLog',684),(1679148939134,'mBClient',685,1679148939138,'modificationLog',686),(1679148939164,'mBClient',687,1679148939166,'modificationLog',688),(1679148939280,'mBClient',689,1679148939282,'modificationLog',690),(1679148939303,'mBClient',691,1679148939306,'modificationLog',692),(1679148939326,'mBClient',693,1679148939329,'modificationLog',694),(1679148939351,'mBClient',695,1679148939353,'modificationLog',696),(1679148939372,'mBClient',697,1679148939375,'modificationLog',698),(1679149581844,'mBRentProperty',699,1679149581847,'modificationLog',700),(1679149781560,'mBRentContract',701,1679149781564,'modificationLog',702),(1679149928293,'mBRentContract',704,1679149928303,'modificationLog',705),(1679150375137,'mBRentContract',707,1679150375149,'modificationLog',708),(1679150437482,'mBRentContract',710,1679150437493,'modificationLog',711),(1679150478822,'mBRentContract',713,1679150478827,'modificationLog',714),(1679150541107,'mBRentOrder',716,1679150541118,'modificationLog',717),(1679150541201,'mBRentOrder',719,1679150541212,'modificationLog',720),(1679150541285,'mBRentOrder',722,1679150541311,'modificationLog',723),(1679150541388,'mBRentOrder',725,1679150541399,'modificationLog',726),(1679150541457,'mBRentOrder',728,1679150541466,'modificationLog',729),(1679150541524,'mBRentOrder',731,1679150541533,'modificationLog',732),(1679150541592,'mBRentOrder',734,1679150541604,'modificationLog',735),(1679150541653,'mBRentOrder',737,1679150541662,'modificationLog',738),(1679150541723,'mBRentOrder',740,1679150541732,'modificationLog',741),(1679150541785,'mBRentOrder',743,1679150541794,'modificationLog',744),(1679150764052,'mBInvoice',746,1679150764066,'modificationLog',747),(1679150764203,'mBCapitalEntry',749,1679150764216,'modificationLog',750),(1679150882869,'mBInvoice',751,1679150882884,'modificationLog',752),(1679150882986,'mBCapitalEntry',754,1679150883004,'modificationLog',755),(1679151000771,'mBInvoice',756,1679151000786,'modificationLog',757),(1679151000899,'mBCapitalEntry',759,1679151000916,'modificationLog',760),(1679151042448,'mBInvoice',761,1679151042463,'modificationLog',762),(1679151042553,'mBCapitalEntry',764,1679151042572,'modificationLog',765),(1679151129812,'mBInvoice',766,1679151129824,'modificationLog',767),(1679151129909,'mBCapitalEntry',769,1679151129920,'modificationLog',770),(1679151266186,'mBInvoice',771,1679151266197,'modificationLog',772),(1679151266279,'mBCapitalEntry',774,1679151266290,'modificationLog',775),(1679151361094,'mBInvoice',781,1679151361107,'modificationLog',782),(1679151361205,'mBCapitalEntry',784,1679151361216,'modificationLog',785),(1679151624483,'mBInvoice',791,1679151624495,'modificationLog',792),(1679151624581,'mBCapitalEntry',794,1679151624594,'modificationLog',795),(1679151728990,'mBInvoice',796,1679151729001,'modificationLog',797),(1679151729096,'mBCapitalEntry',799,1679151729109,'modificationLog',800),(1679151838355,'mBInvoice',801,1679151838364,'modificationLog',802),(1679151838430,'mBCapitalEntry',804,1679151838440,'modificationLog',805),(1679151926045,'mBInvoice',806,1679151926061,'modificationLog',807),(1679151926182,'mBCapitalEntry',809,1679151926195,'modificationLog',810),(1679152049788,'mBInvoice',811,1679152049800,'modificationLog',812),(1679152049883,'mBCapitalEntry',814,1679152049894,'modificationLog',815),(1679152108711,'mBInvoice',816,1679152108723,'modificationLog',817),(1679152108813,'mBCapitalEntry',819,1679152108825,'modificationLog',820),(1679152151437,'mBInvoice',821,1679152151449,'modificationLog',822),(1679152151534,'mBCapitalEntry',824,1679152151544,'modificationLog',825),(1679152197347,'mBInvoice',826,1679152197361,'modificationLog',827),(1679152197449,'mBCapitalEntry',829,1679152197461,'modificationLog',830),(1679152252914,'mBInvoice',831,1679152252924,'modificationLog',832),(1679152253012,'mBCapitalEntry',834,1679152253023,'modificationLog',835),(1679152292972,'mBInvoice',836,1679152292979,'modificationLog',837),(1679152293033,'mBCapitalEntry',839,1679152293040,'modificationLog',840),(1679152351966,'mBInvoice',841,1679152351977,'modificationLog',842),(1679152352064,'mBCapitalEntry',844,1679152352078,'modificationLog',845),(1679152426977,'mBInvoice',846,1679152426987,'modificationLog',847),(1679152427096,'mBCapitalEntry',849,1679152427110,'modificationLog',850),(1679152544046,'mBInvoice',851,1679152544056,'modificationLog',852),(1679152544161,'mBCapitalEntry',854,1679152544172,'modificationLog',855),(1679152648839,'mBInvoice',861,1679152648853,'modificationLog',862),(1679152648936,'mBCapitalEntry',864,1679152648948,'modificationLog',865),(1679152726806,'mBInvoice',866,1679152726818,'modificationLog',867),(1679152726901,'mBCapitalEntry',869,1679152726911,'modificationLog',870),(1679152810458,'mBClient',871,1679152810471,'modificationLog',872),(1679152881000,'mBClient',878,1679152881011,'modificationLog',879),(1679152987192,'mBClient',885,1679152987203,'modificationLog',886),(1679153056579,'mBInvoice',887,1679153056586,'modificationLog',888),(1679153056625,'mBCapitalEntry',890,1679153056629,'modificationLog',891),(1679153112347,'mBInvoice',892,1679153112357,'modificationLog',893),(1679153112454,'mBCapitalEntry',895,1679153112466,'modificationLog',896),(1679153193643,'mBInvoice',897,1679153193654,'modificationLog',898),(1679153193743,'mBCapitalEntry',900,1679153193753,'modificationLog',901),(1679153453632,'mBInvoice',902,1679153453644,'modificationLog',903),(1679153453724,'mBCapitalEntry',905,1679153453736,'modificationLog',906),(1679153570784,'mBInvoice',907,1679153570795,'modificationLog',908),(1679153570872,'mBCapitalEntry',910,1679153570883,'modificationLog',911),(1679153736570,'mBInvoice',912,1679153736580,'modificationLog',913),(1679153736664,'mBCapitalEntry',915,1679153736676,'modificationLog',916),(1679153780614,'mBClient',917,1679153780625,'modificationLog',918),(1679153839190,'mBInvoice',919,1679153839200,'modificationLog',920),(1679153839280,'mBCapitalEntry',922,1679153839293,'modificationLog',923),(1679153882789,'mBClient',924,1679153882800,'modificationLog',925),(1679153952153,'mBInvoice',926,1679153952162,'modificationLog',927),(1679153952240,'mBCapitalEntry',929,1679153952252,'modificationLog',930),(1679154031874,'mBInvoice',931,1679154031885,'modificationLog',932),(1679154031982,'mBCapitalEntry',934,1679154031992,'modificationLog',935),(1679154077320,'mBClient',936,1679154077332,'modificationLog',937),(1679154172691,'mBInvoice',938,1679154172703,'modificationLog',939),(1679154172799,'mBCapitalEntry',941,1679154172811,'modificationLog',942),(1679154215342,'mBClient',943,1679154215352,'modificationLog',944),(1679154284759,'mBInvoice',945,1679154284770,'modificationLog',946),(1679154284870,'mBCapitalEntry',948,1679154284881,'modificationLog',949),(1679154358650,'mBInvoice',950,1679154358663,'modificationLog',951),(1679154358743,'mBCapitalEntry',953,1679154358753,'modificationLog',954),(1679154435578,'mBInvoice',955,1679154435588,'modificationLog',956),(1679154435721,'mBCapitalEntry',958,1679154435730,'modificationLog',959),(1679154513222,'mBClient',965,1679154513231,'modificationLog',966),(1679154585030,'mBInvoice',967,1679154585039,'modificationLog',968),(1679154585119,'mBCapitalEntry',970,1679154585131,'modificationLog',971),(1679154638320,'mBInvoice',972,1679154638330,'modificationLog',973),(1679154638412,'mBCapitalEntry',975,1679154638423,'modificationLog',976),(1679154683848,'mBInvoice',977,1679154683858,'modificationLog',978),(1679154683938,'mBCapitalEntry',980,1679154683950,'modificationLog',981),(1679154721973,'mBClient',982,1679154721984,'modificationLog',983),(1679154747632,'mBInvoice',984,1679154747643,'modificationLog',985),(1679154747725,'mBCapitalEntry',987,1679154747737,'modificationLog',988),(1679154793149,'mBInvoice',989,1679154793159,'modificationLog',990),(1679154793250,'mBCapitalEntry',992,1679154793260,'modificationLog',993),(1679154997882,'mBInvoice',994,1679154997888,'modificationLog',995),(1679154997912,'mBCapitalEntry',997,1679154997912,'modificationLog',998),(1679155047432,'mBClient',999,1679155047435,'modificationLog',1000),(1679155127390,'mBInvoice',1001,1679155127396,'modificationLog',1002),(1679155127430,'mBCapitalEntry',1004,1679155127433,'modificationLog',1005),(1679155182558,'mBClient',1006,1679155182567,'modificationLog',1007),(1679155231140,'mBInvoice',1008,1679155231152,'modificationLog',1009),(1679155231226,'mBCapitalEntry',1011,1679155231237,'modificationLog',1012),(1679155297685,'mBClient',1013,1679155297699,'modificationLog',1014),(1679155325791,'mBInvoice',1015,1679155325801,'modificationLog',1016),(1679155325876,'mBCapitalEntry',1018,1679155325887,'modificationLog',1019),(1679155366433,'mBInvoice',1020,1679155366443,'modificationLog',1021),(1679155366527,'mBCapitalEntry',1023,1679155366540,'modificationLog',1024),(1679155445883,'mBInvoice',1025,1679155445894,'modificationLog',1026),(1679155445969,'mBCapitalEntry',1028,1679155445980,'modificationLog',1029),(1679155485815,'mBClient',1030,1679155485825,'modificationLog',1031),(1679155521346,'mBInvoice',1032,1679155521357,'modificationLog',1033),(1679155521434,'mBCapitalEntry',1035,1679155521447,'modificationLog',1036),(1679157697891,'mBInvoice',1037,1679157697892,'modificationLog',1038),(1679157697926,'mBCapitalEntry',1040,1679157697926,'modificationLog',1041);
/*!40000 ALTER TABLE `item_modification_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `iso_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKj0f7nfmg7f83qinoj2loou4ij` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_value`
--

DROP TABLE IF EXISTS `language_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language_value` (
  `value` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `language_date_created` bigint DEFAULT '0',
  `language_item_type` varchar(255) DEFAULT 'item',
  `language_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK8b59pg38uxppbf40it4nrth` (`language_date_created`,`language_item_type`,`language_t_key`),
  CONSTRAINT `FK8b59pg38uxppbf40it4nrth` FOREIGN KEY (`language_date_created`, `language_item_type`, `language_t_key`) REFERENCES `language` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKfsbxkg3kdqhqw4htw61udan91` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_value`
--

LOCK TABLES `language_value` WRITE;
/*!40000 ALTER TABLE `language_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `language_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbcapital`
--

DROP TABLE IF EXISTS `mbcapital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbcapital` (
  `current_value` bigint NOT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKsde4h6kpp6sgud6dyhfy5j50f` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbcapital`
--

LOCK TABLES `mbcapital` WRITE;
/*!40000 ALTER TABLE `mbcapital` DISABLE KEYS */;
INSERT INTO `mbcapital` VALUES (-71028000,1679147737888,'mBCapital',89),(0,1679147737998,'mBCapital',95),(0,1679147738092,'mBCapital',101);
/*!40000 ALTER TABLE `mbcapital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbcapital_entry`
--

DROP TABLE IF EXISTS `mbcapital_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbcapital_entry` (
  `amount` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `entry_type` int DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `capital_date_created` bigint DEFAULT '0',
  `capital_item_type` varchar(255) DEFAULT 'item',
  `capital_t_key` bigint DEFAULT '0',
  `invoice_date_created` bigint DEFAULT '0',
  `invoice_item_type` varchar(255) DEFAULT 'item',
  `invoice_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKmdqhuf5aqwq31xjcammbudmb4` (`capital_date_created`,`capital_item_type`,`capital_t_key`),
  KEY `FK18c0wj95sroibn7vabvwtolap` (`invoice_date_created`,`invoice_item_type`,`invoice_t_key`),
  CONSTRAINT `FK18c0wj95sroibn7vabvwtolap` FOREIGN KEY (`invoice_date_created`, `invoice_item_type`, `invoice_t_key`) REFERENCES `mbinvoice` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKmdqhuf5aqwq31xjcammbudmb4` FOREIGN KEY (`capital_date_created`, `capital_item_type`, `capital_t_key`) REFERENCES `mbcapital` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKq9qf70j4noxh572xghmvut900` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbcapital_entry`
--

LOCK TABLES `mbcapital_entry` WRITE;
/*!40000 ALTER TABLE `mbcapital_entry` DISABLE KEYS */;
INSERT INTO `mbcapital_entry` VALUES (0,NULL,1,1679147737920,'mBCapitalEntry',91,1679147737888,'mBCapital',89,NULL,NULL,NULL),(0,NULL,1,1679147738016,'mBCapitalEntry',97,1679147737998,'mBCapital',95,NULL,NULL,NULL),(0,NULL,1,1679147738108,'mBCapitalEntry',103,1679147738092,'mBCapital',101,NULL,NULL,NULL),(100000,'DEBT-SFCG : HEBERGEMENT:100000',0,1679150764203,'mBCapitalEntry',749,1679147737888,'mBCapital',89,1679150764052,'mBInvoice',746),(558500,'DEBT-PROVINCE MUYINGA : RESTO:275000\nBAR:283500\n',0,1679150882986,'mBCapitalEntry',754,1679147737888,'mBCapital',89,1679150882869,'mBInvoice',751),(350000,'DEBT-IFDC : RESTO:10000\nBAR:60000\nHEBERGEMENT:280000',0,1679151000899,'mBCapitalEntry',759,1679147737888,'mBCapital',89,1679151000771,'mBInvoice',756),(28000,'DEBT-ADCOM MUYINGA : RESTO:28000',0,1679151042553,'mBCapitalEntry',764,1679147737888,'mBCapital',89,1679151042448,'mBInvoice',761),(1040000,'DEBT-PNLP : RESTO:962000\nBAR:78000\n',0,1679151129909,'mBCapitalEntry',769,1679147737888,'mBCapital',89,1679151129812,'mBInvoice',766),(1593000,'DEBT-CAFOB : RESTO:1330000\nBAR:63000\nSALLE:200000',0,1679151266279,'mBCapitalEntry',774,1679147737888,'mBCapital',89,1679151266186,'mBInvoice',771),(645000,'DEBT-VESOS : RESTO:332000\nBAR:313000',0,1679151361205,'mBCapitalEntry',784,1679147737888,'mBCapital',89,1679151361094,'mBInvoice',781),(1729000,'DEBT-BCB : RESTO:584000\n\nBAR:595000\n\nHEBERGEMENT:550000',0,1679151624581,'mBCapitalEntry',794,1679147737888,'mBCapital',89,1679151624483,'mBInvoice',791),(7601000,'DEBT-WV : RESTO:3812500\nBAR:757500\nHEBERGEMENT:2550000\nSALLE:480000',0,1679151729096,'mBCapitalEntry',799,1679147737888,'mBCapital',89,1679151728990,'mBInvoice',796),(745000,'DEBT-SAVE THE CHILDREN : RESTO:528000\nBAR:57000\nHEBERGEMENT:160000',0,1679151838430,'mBCapitalEntry',804,1679147737888,'mBCapital',89,1679151838355,'mBInvoice',801),(3678000,'DEBT-CNDH : RESTO:2242000\nBAR:986000\nSALLE:450000',0,1679151926182,'mBCapitalEntry',809,1679147737888,'mBCapital',89,1679151926045,'mBInvoice',806),(341000,'DEBT-PADANE : BAR:161000\nHEBERGEMENT:180000',0,1679152049883,'mBCapitalEntry',814,1679147737888,'mBCapital',89,1679152049788,'mBInvoice',811),(460000,'DEBT-SOTB : BAR:100000\nHEBERGEMENT:360000',0,1679152108813,'mBCapitalEntry',819,1679147737888,'mBCapital',89,1679152108711,'mBInvoice',816),(600000,'DEBT-PSI  : HEBERGEMENT:600000',0,1679152151534,'mBCapitalEntry',824,1679147737888,'mBCapital',89,1679152151437,'mBInvoice',821),(80000,'DEBT-PACT BURUNDI : HEBERGRMENT:80000',0,1679152197449,'mBCapitalEntry',829,1679147737888,'mBCapital',89,1679152197347,'mBInvoice',826),(137500,'DEBT-CNDD-FDD : RESTO:100000\nBAR:37500',0,1679152253012,'mBCapitalEntry',834,1679147737888,'mBCapital',89,1679152252914,'mBInvoice',831),(60000,'DEBT-CICR : HEBERGEMENT:60000',0,1679152293033,'mBCapitalEntry',839,1679147737888,'mBCapital',89,1679152292972,'mBInvoice',836),(195000,'DEBT-THIERRY : HEBERGEMENT:195000',0,1679152352064,'mBCapitalEntry',844,1679147737888,'mBCapital',89,1679152351966,'mBInvoice',841),(508000,'DEBT-RESEAU_2000 : RESTO:324000\nBAR:84000\nSALLE:100000',0,1679152427096,'mBCapitalEntry',849,1679147737888,'mBCapital',89,1679152426977,'mBInvoice',846),(10832000,'DEBT-UGADAS : RESTO:8140000\nBAR:1692000\nSALLE:1000000',0,1679152544161,'mBCapitalEntry',854,1679147737888,'mBCapital',89,1679152544046,'mBInvoice',851),(1415000,'DEBT-BBIN : RESTO:1072500\nBAR:192500\nSALLE:150000',0,1679152648936,'mBCapitalEntry',864,1679147737888,'mBCapital',89,1679152648839,'mBInvoice',861),(60000,'DEBT-CARE : HEBERGEMENT:60000',0,1679152726901,'mBCapitalEntry',869,1679147737888,'mBCapital',89,1679152726806,'mBInvoice',866),(55000,'DEBT-BARTHAZAR : BAR:25000\nHEBERGEMENT:30000',0,1679153056625,'mBCapitalEntry',890,1679147737888,'mBCapital',89,1679153056579,'mBInvoice',887),(55000,'DEBT-PATRON : BAR:55000',0,1679153112454,'mBCapitalEntry',895,1679147737888,'mBCapital',89,1679153112347,'mBInvoice',892),(354000,'DEBT-PATRONNE : RESTO:239000\nBAR:115000',0,1679153193743,'mBCapitalEntry',900,1679147737888,'mBCapital',89,1679153193643,'mBInvoice',897),(19193500,'DEBT-WV : RESTO:11838000\nBAR:2020500\nSALLE:1640000\nHEBERGEMENT:3695000',0,1679153453724,'mBCapitalEntry',905,1679147737888,'mBCapital',89,1679153453632,'mBInvoice',902),(2104000,'DEBT-SFCG : RESTO:1470000\nBART:294000\nHEBERGEMENT:100000\nSALLE:240000',0,1679153570872,'mBCapitalEntry',910,1679147737888,'mBCapital',89,1679153570784,'mBInvoice',907),(4074000,'DEBT-PNLP : RESTO:3034000\nBAR:240000\nSALLE:800000',0,1679153736664,'mBCapitalEntry',915,1679147737888,'mBCapital',89,1679153736570,'mBInvoice',912),(3945000,'DEBT-FPP : RESTO:1885000\nBAR:1610000\nSALLE:450000',0,1679153839280,'mBCapitalEntry',922,1679147737888,'mBCapital',89,1679153839190,'mBInvoice',919),(1680000,'DEBT-ODEDIM : RESTO:1320000\nBAR:210000\nSALLE:150000',0,1679153952240,'mBCapitalEntry',929,1679147737888,'mBCapital',89,1679153952153,'mBInvoice',926),(410000,'DEBT-RESEAU_2000 : RESTO:274000\nBAR:56000\nSALLE:80000',0,1679154031982,'mBCapitalEntry',934,1679147737888,'mBCapital',89,1679154031874,'mBInvoice',931),(1592500,'DEBT-PROJET-NKURIZA : RESTO:1232500\nBAR:210000\nSALLE:150000',0,1679154172799,'mBCapitalEntry',941,1679147737888,'mBCapital',89,1679154172691,'mBInvoice',938),(1302500,'DEBT-MFPTE : RESTO:990000\nBAR:112500\nSALLE:200000',0,1679154284870,'mBCapitalEntry',948,1679147737888,'mBCapital',89,1679154284759,'mBInvoice',945),(310000,'DEBT-IFDC : RESTO:80000\nHEBERGEMENT:23000',0,1679154358743,'mBCapitalEntry',953,1679147737888,'mBCapital',89,1679154358650,'mBInvoice',950),(80000,'DEBT-SAVE THE CHILDREN : HEBERGEMENT:80000',0,1679154435721,'mBCapitalEntry',958,1679147737888,'mBCapital',89,1679154435578,'mBInvoice',955),(473500,'DEBT-OIM : BAR:173500\nHEBERGEMENT:300000',0,1679154585119,'mBCapitalEntry',970,1679147737888,'mBCapital',89,1679154585030,'mBInvoice',967),(740000,'DEBT-PSI  : HEBERGEMENT:740000',0,1679154638412,'mBCapitalEntry',975,1679147737888,'mBCapital',89,1679154638320,'mBInvoice',972),(40000,'DEBT-SOTB : HEBERGEMENT:40000',0,1679154683938,'mBCapitalEntry',980,1679147737888,'mBCapital',89,1679154683848,'mBInvoice',977),(51500,'DEBT-SOS : RESTO:51500',0,1679154747725,'mBCapitalEntry',987,1679147737888,'mBCapital',89,1679154747632,'mBInvoice',984),(83000,'DEBT-CARE : RESTO:70000\nBAR:13000',0,1679154793250,'mBCapitalEntry',992,1679147737888,'mBCapital',89,1679154793149,'mBInvoice',989),(392500,'DEBT-BCB : RESTO:196000\nBAR:56500\nHEBERGEMENT:140000',0,1679154997912,'mBCapitalEntry',997,1679147737888,'mBCapital',89,1679154997882,'mBInvoice',994),(74000,'DEBT-ADCOM-GASORWE : BAR:74000',0,1679155127430,'mBCapitalEntry',1004,1679147737888,'mBCapital',89,1679155127390,'mBInvoice',1001),(906000,'DEBT-COMMUNE MUYINGA : RESTO:26500\nBAR:879500\n',0,1679155231226,'mBCapitalEntry',1011,1679147737888,'mBCapital',89,1679155231140,'mBInvoice',1008),(63500,'DEBT-SHABANI CUMBA : RESTO:63500',0,1679155325876,'mBCapitalEntry',1018,1679147737888,'mBCapital',89,1679155325791,'mBInvoice',1015),(27000,'DEBT-PATRON : BAR:27000',0,1679155366527,'mBCapitalEntry',1023,1679147737888,'mBCapital',89,1679155366433,'mBInvoice',1020),(210000,'DEBT-PATRONNE : RESTO:103000\nBAR:107000',0,1679155445969,'mBCapitalEntry',1028,1679147737888,'mBCapital',89,1679155445883,'mBInvoice',1025),(38500,'DEBT-NEILLA : BAR:38500',0,1679155521434,'mBCapitalEntry',1035,1679147737888,'mBCapital',89,1679155521346,'mBInvoice',1032),(120000,'DEBT-CICR : HEBERGEMENT:120000',0,1679157697926,'mBCapitalEntry',1040,1679147737888,'mBCapital',89,1679157697891,'mBInvoice',1037);
/*!40000 ALTER TABLE `mbcapital_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbcategory`
--

DROP TABLE IF EXISTS `mbcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbcategory` (
  `name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `facility_date_created` bigint DEFAULT '0',
  `facility_item_type` varchar(255) DEFAULT 'item',
  `facility_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK9e8j98enhm7hk26t6j5acfjbl` (`facility_date_created`,`facility_item_type`,`facility_t_key`),
  CONSTRAINT `FK9e8j98enhm7hk26t6j5acfjbl` FOREIGN KEY (`facility_date_created`, `facility_item_type`, `facility_t_key`) REFERENCES `mbfacility` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKfad48os1dksy3ttjdigjfnwvs` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbcategory`
--

LOCK TABLES `mbcategory` WRITE;
/*!40000 ALTER TABLE `mbcategory` DISABLE KEYS */;
INSERT INTO `mbcategory` VALUES ('CUISINE',1679147735693,'mBCategory',1,1679147737864,'mBFacility',87),('BAR',1679147735910,'mBCategory',3,1679147737864,'mBFacility',87),('SALLE',1679147735964,'mBCategory',5,1679147737864,'mBFacility',87),('HEBERGEMENT',1679147736010,'mBCategory',7,1679147737864,'mBFacility',87),('GYMNASE',1679147736048,'mBCategory',9,1679147737864,'mBFacility',87),('AUTOMOBILE',1679147736083,'mBCategory',11,1679147737864,'mBFacility',87),('BATIMENT',1679147736120,'mBCategory',13,1679147737864,'mBFacility',87),('CUISINE',1679147736278,'mBCategory',15,1679147737984,'mBFacility',93),('BAR',1679147736315,'mBCategory',17,1679147737984,'mBFacility',93),('SALLE',1679147736356,'mBCategory',19,1679147737984,'mBFacility',93),('AUTOMOBILE',1679147736394,'mBCategory',21,1679147737984,'mBFacility',93),('BATIMENT',1679147736427,'mBCategory',23,1679147737984,'mBFacility',93),('MATIERE-PREMIERE',1679147736525,'mBCategory',25,1679147738076,'mBFacility',99),('PRODUIT-FINIE',1679147736564,'mBCategory',27,1679147738076,'mBFacility',99),('SON-DE-MAïS',1679147736601,'mBCategory',29,1679147738076,'mBFacility',99),('AUTOMOBILE',1679147736642,'mBCategory',31,1679147738076,'mBFacility',99),('AMBALLAGE',1679147736677,'mBCategory',33,1679147738076,'mBFacility',99),('ELECTRIQUE',1679147736711,'mBCategory',35,1679147738076,'mBFacility',99),('BATIMENT',1679147736746,'mBCategory',37,1679147738076,'mBFacility',99),('MACHINE',1679147736779,'mBCategory',39,1679147738076,'mBFacility',99),('MATERIEL',1679147736812,'mBCategory',41,1679147738076,'mBFacility',99);
/*!40000 ALTER TABLE `mbcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbclient`
--

DROP TABLE IF EXISTS `mbclient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbclient` (
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_debt` bigint NOT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `facility_date_created` bigint DEFAULT '0',
  `facility_item_type` varchar(255) DEFAULT 'item',
  `facility_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKep80jyo73d14ny43mn2imyxgx` (`facility_date_created`,`facility_item_type`,`facility_t_key`),
  CONSTRAINT `FKa6xaxbq3p40ucitqcr8434yov` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKep80jyo73d14ny43mn2imyxgx` FOREIGN KEY (`facility_date_created`, `facility_item_type`, `facility_t_key`) REFERENCES `mbfacility` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbclient`
--

LOCK TABLES `mbclient` WRITE;
/*!40000 ALTER TABLE `mbclient` DISABLE KEYS */;
INSERT INTO `mbclient` VALUES ('Bujumbura','WV',-26794500,1679147736930,'mBClient',43,1679147737864,'mBFacility',87),('Bujumbura','CAFOB',-1593000,1679147736985,'mBClient',45,1679147737864,'mBFacility',87),('Bujumbura','UGADAS',-10832000,1679147737022,'mBClient',47,1679147737864,'mBFacility',87),('Bujumbura','PADANE',-341000,1679147737057,'mBClient',49,1679147737864,'mBFacility',87),('Muyinga','RESEAU_2000',-918000,1679147737095,'mBClient',51,1679147737864,'mBFacility',87),('Bujumbura','SAVE THE CHILDREN',-825000,1679147737131,'mBClient',53,1679147737864,'mBFacility',87),('Bujumbura','BBIN',-1415000,1679147737160,'mBClient',55,1679147737864,'mBFacility',87),('Bujumbura','CNDH',-3678000,1679147737194,'mBClient',57,1679147737864,'mBFacility',87),('Bujumbura','PNLP',-5114000,1679147737233,'mBClient',59,1679147737864,'mBFacility',87),('Gasorwe','SOTB',-500000,1679147737270,'mBClient',61,1679147737864,'mBFacility',87),('Bujumbura','IFDC',-660000,1679147737305,'mBClient',63,1679147737864,'mBFacility',87),('Bujumbura','CICR',-180000,1679147737342,'mBClient',65,1679147737864,'mBFacility',87),('Muyinga','PROVINCE MUYINGA',-558500,1679147737371,'mBClient',67,1679147737864,'mBFacility',87),('Muyinga','CNDD-FDD',-137500,1679147737407,'mBClient',69,1679147737864,'mBFacility',87),('Muyinga','ADCOM MUYINGA',-28000,1679147737442,'mBClient',71,1679147737864,'mBFacility',87),('Bujumbura','BCB',-2121500,1679147737479,'mBClient',73,1679147737864,'mBFacility',87),('Muyinga','VESOS',-645000,1679147737512,'mBClient',75,1679147737864,'mBFacility',87),('Bujumbura','PACT BURUNDI',-80000,1679147737547,'mBClient',77,1679147737864,'mBFacility',87),('Bujumbura','PSI ',-1340000,1679147737577,'mBClient',79,1679147737864,'mBFacility',87),('Bujumbura','CARE',-143000,1679147737608,'mBClient',81,1679147737864,'mBFacility',87),('Bujumbura','SFCG',-2204000,1679147737637,'mBClient',83,1679147737864,'mBFacility',87),('Muyinga','THIERRY',-195000,1679147737666,'mBClient',85,1679147737864,'mBFacility',87),('Muyinga','YOUNG TEAM',-500000,1679148939019,'mBClient',677,1679147737864,'mBFacility',87),('Muyinga','SHAMDAAR',-240000,1679148939050,'mBClient',679,1679147737864,'mBFacility',87),('Muyinga','LATECH',-140000,1679148939079,'mBClient',681,1679147737864,'mBFacility',87),('Muyinga','MUTUELLE',0,1679148939107,'mBClient',683,1679147737864,'mBFacility',87),('Muyinga','HONEST',-240000,1679148939134,'mBClient',685,1679147737864,'mBFacility',87),('Muyinga','SPTPA',-240000,1679148939164,'mBClient',687,1679147737864,'mBFacility',87),('Masanganzira','STANY',0,1679148939280,'mBClient',689,1679147738076,'mBFacility',99),('Masanganzira','JACQUES',0,1679148939303,'mBClient',691,1679147738076,'mBFacility',99),('Ngozi','BOSCO',0,1679148939326,'mBClient',693,1679147738076,'mBFacility',99),('Muyinga','PATRICK',0,1679148939351,'mBClient',695,1679147738076,'mBFacility',99),('Bujumbura','DANIEL',0,1679148939372,'mBClient',697,1679147738076,'mBFacility',99),('Muyinga','PATRON',-82000,1679152810458,'mBClient',871,1679147737864,'mBFacility',87),('Muyinga','PATRONNE',-564000,1679152881000,'mBClient',878,1679147737864,'mBFacility',87),('Muiyinga','BARTHAZAR',-55000,1679152987192,'mBClient',885,1679147737864,'mBFacility',87),('Bujumbura','FPP',-3945000,1679153780614,'mBClient',917,1679147737864,'mBFacility',87),('Muyinga','ODEDIM',-1680000,1679153882789,'mBClient',924,1679147737864,'mBFacility',87),('Bujumbura','PROJET-NKURIZA',-1592500,1679154077320,'mBClient',936,1679147737864,'mBFacility',87),('Bujumbura','MFPTE',-1302500,1679154215342,'mBClient',943,1679147737864,'mBFacility',87),('Bujumbura','OIM',-473500,1679154513222,'mBClient',965,1679147737864,'mBFacility',87),('Muyinga','SOS',51500,1679154721973,'mBClient',982,1679147737864,'mBFacility',87),('Gasorwe','ADCOM-GASORWE',-74000,1679155047432,'mBClient',999,1679147737864,'mBFacility',87),('Muyinga','COMMUNE MUYINGA',-906000,1679155182558,'mBClient',1006,1679147737864,'mBFacility',87),('Cumba','SHABANI CUMBA',-63500,1679155297685,'mBClient',1013,1679147737864,'mBFacility',87),('Muyinga','NEILLA',-38500,1679155485815,'mBClient',1030,1679147737864,'mBFacility',87);
/*!40000 ALTER TABLE `mbclient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbfacility`
--

DROP TABLE IF EXISTS `mbfacility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbfacility` (
  `address` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `capital_date_created` bigint DEFAULT '0',
  `capital_item_type` varchar(255) DEFAULT 'item',
  `capital_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK13oh5x9b4q00y2ha3qgrndw6i` (`capital_date_created`,`capital_item_type`,`capital_t_key`),
  CONSTRAINT `FK13oh5x9b4q00y2ha3qgrndw6i` FOREIGN KEY (`capital_date_created`, `capital_item_type`, `capital_t_key`) REFERENCES `mbcapital` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKcteuwcaa7dbdud6ssbko07t8n` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbfacility`
--

LOCK TABLES `mbfacility` WRITE;
/*!40000 ALTER TABLE `mbfacility` DISABLE KEYS */;
INSERT INTO `mbfacility` VALUES ('Muyinga',NULL,'Icizanye-hotel',1679147737864,'mBFacility',87,1679147737888,'mBCapital',89),('Bujumbura',NULL,'Kugatumba-bar',1679147737984,'mBFacility',93,1679147737998,'mBCapital',95),('Gashoho-Muyinga',NULL,'Sptpa',1679147738076,'mBFacility',99,1679147738092,'mBCapital',101);
/*!40000 ALTER TABLE `mbfacility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbinventory`
--

DROP TABLE IF EXISTS `mbinventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbinventory` (
  `cost` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `category_date_created` bigint DEFAULT '0',
  `category_item_type` varchar(255) DEFAULT 'item',
  `category_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKi5w0xi04cr1ae0r86vq2r60oe` (`category_date_created`,`category_item_type`,`category_t_key`),
  CONSTRAINT `FKi5w0xi04cr1ae0r86vq2r60oe` FOREIGN KEY (`category_date_created`, `category_item_type`, `category_t_key`) REFERENCES `mbcategory` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKipl3irqnv4pk97ov4f759jo92` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbinventory`
--

LOCK TABLES `mbinventory` WRITE;
/*!40000 ALTER TABLE `mbinventory` DISABLE KEYS */;
INSERT INTO `mbinventory` VALUES (4000000,'Congerateur',4,'piece',1679147738176,'mBInventory',105,1679147735693,'mBCategory',1),(2000000,'Frigot',1,'piece',1679147738231,'mBInventory',107,1679147736010,'mBCategory',7),(1500000,'Micro onde',3,'piece',1679147738263,'mBInventory',109,1679147735693,'mBCategory',1),(1000000,'Friteuse',4,'piece',1679147738294,'mBInventory',111,1679147735693,'mBCategory',1),(800000,'Cafetiere',4,'piece',1679147738329,'mBInventory',113,1679147735693,'mBCategory',1),(90000,'Chauffe eau',2,'piece',1679147738363,'mBInventory',115,1679147736278,'mBCategory',15),(60000,'Tooster',2,'piece',1679147738394,'mBInventory',117,1679147735693,'mBCategory',1),(180000,'Mixer a jus',2,'piece',1679147738423,'mBInventory',119,1679147735693,'mBCategory',1),(1500000,'Table grand',1,'piece',1679147738451,'mBInventory',121,1679147735693,'mBCategory',1),(600000,'Balance grand',4,'piece',1679147738507,'mBInventory',123,1679147735693,'mBCategory',1),(45000,'Balance petit',1,'piece',1679147738539,'mBInventory',125,1679147735693,'mBCategory',1),(225000,'Telemos grand',9,'piece',1679147738571,'mBInventory',127,1679147735693,'mBCategory',1),(270000,'Telemos petit',18,'piece',1679147738606,'mBInventory',129,1679147735693,'mBCategory',1),(360000,'Isafuniya nini',4,'piece',1679147738637,'mBInventory',131,1679147735693,'mBCategory',1),(75000,'Isafuniya ntoya',5,'piece',1679147738672,'mBInventory',133,1679147735693,'mBCategory',1),(240000,'Panneau omlette',4,'piece',1679147738707,'mBInventory',135,1679147735693,'mBCategory',1),(135000,'Panneau de thé',9,'piece',1679147738737,'mBInventory',137,1679147735693,'mBCategory',1),(525000,'Panneau simple',15,'piece',1679147738763,'mBInventory',139,1679147735693,'mBCategory',1),(100000,'Panneau des frittes',5,'piece',1679147738798,'mBInventory',141,1679147735693,'mBCategory',1),(30000,'Marmite de patte p',2,'piece',1679147738828,'mBInventory',143,1679147735693,'mBCategory',1),(175000,'Grand bassin',5,'piece',1679147738860,'mBInventory',145,1679147736010,'mBCategory',7),(32000,'Petit  bassin',8,'piece',1679147738891,'mBInventory',147,1679147735693,'mBCategory',1),(105000,'Imbabura',7,'piece',1679147738924,'mBInventory',149,1679147736278,'mBCategory',15),(444000,'Seaux',37,'piece',1679147738959,'mBInventory',151,1679147736010,'mBCategory',7),(6000000,'Bain malin',10,'piece',1679147738995,'mBInventory',153,1679147735693,'mBCategory',1),(28000,'Verre de lait',7,'piece',1679147739031,'mBInventory',155,1679147735693,'mBCategory',1),(0,'Chinwa',1,'piece',1679147739065,'mBInventory',157,1679147735693,'mBCategory',1),(0,'Foue',4,'piece',1679147739098,'mBInventory',159,1679147735693,'mBCategory',1),(3000000,'Sous tasse ',120,'piece',1679147739137,'mBInventory',161,1679147735693,'mBCategory',1),(5000,'Jague',1,'piece',1679147739168,'mBInventory',163,1679147735693,'mBCategory',1),(10000,'Bolle de Fruit',2,'piece',1679147739196,'mBInventory',165,1679147735693,'mBCategory',1),(30000,'Bolle de soupe',6,'piece',1679147739224,'mBInventory',167,1679147735693,'mBCategory',1),(45000,'Bolle de puma',9,'piece',1679147739256,'mBInventory',169,1679147735693,'mBCategory',1),(15000,'Bolle de sucre',3,'piece',1679147739283,'mBInventory',171,1679147735693,'mBCategory',1),(400000,'Ventulateur',5,'piece',1679147739313,'mBInventory',173,1679147735693,'mBCategory',1),(480000,'Marmite de buffet',16,'piece',1679147739341,'mBInventory',175,1679147735693,'mBCategory',1),(45500,'Plateau salade G',7,'piece',1679147739373,'mBInventory',177,1679147735693,'mBCategory',1),(52000,'Plateau salade P',13,'piece',1679147739396,'mBInventory',179,1679147735693,'mBCategory',1),(180000,'Assiette de P entier',3,'piece',1679147739424,'mBInventory',181,1679147735693,'mBCategory',1),(800000,'Assiette de mukeke',16,'piece',1679147739453,'mBInventory',183,1679147735693,'mBCategory',1),(204000,'Assiette de melange',17,'piece',1679147739488,'mBInventory',185,1679147735693,'mBCategory',1),(1056000,'Assiette de com simple',88,'piece',1679147739524,'mBInventory',187,1679147735693,'mBCategory',1),(202000,'Fourchette',101,'piece',1679147739558,'mBInventory',189,1679147736278,'mBCategory',15),(100000,'Louche',25,'piece',1679147739591,'mBInventory',191,1679147735693,'mBCategory',1),(320000,'Couteau',160,'piece',1679147739629,'mBInventory',193,1679147735693,'mBCategory',1),(93000,'Petit cuierre',62,'piece',1679147739661,'mBInventory',195,1679147735693,'mBCategory',1),(195000,'Cuierre de soupe',65,'piece',1679147739695,'mBInventory',197,1679147735693,'mBCategory',1),(120000,'Assiette igisafuriya',2,'piece',1679147739723,'mBInventory',199,1679147735693,'mBCategory',1),(100000,'Planche',4,'piece',1679147739747,'mBInventory',201,1679147735693,'mBCategory',1),(150000,'Etagere',1,'piece',1679147739771,'mBInventory',203,1679147736010,'mBCategory',7),(10000,'Calculatrice',1,'piece',1679147739802,'mBInventory',205,1679147736278,'mBCategory',15),(1200000,'Hot pot',2,'piece',1679147739826,'mBInventory',207,1679147735693,'mBCategory',1),(400000,'Machine viande',2,'piece',1679147739850,'mBInventory',209,1679147735693,'mBCategory',1),(900000,'Mixeur a soupe',5,'piece',1679147739878,'mBInventory',211,1679147735693,'mBCategory',1),(0,'Porte-cuilleur',2,'piece',1679147739909,'mBInventory',213,1679147735693,'mBCategory',1),(700000,'Ifuru electrique',1,'piece',1679147739940,'mBInventory',215,1679147735693,'mBCategory',1),(550000,'Machine pomm de terre',1,'piece',1679147739970,'mBInventory',217,1679147735693,'mBCategory',1),(0,'Platine',4,'piece',1679147739996,'mBInventory',219,1679147735693,'mBCategory',1),(50000,'Ketia',2,'piece',1679147740019,'mBInventory',221,1679147735693,'mBCategory',1),(175000,'Griade',7,'piece',1679147740045,'mBInventory',223,1679147735693,'mBCategory',1),(15000,'Lappe choux',5,'piece',1679147740071,'mBInventory',225,1679147735693,'mBCategory',1),(12000,'Lappe carotte',4,'piece',1679147740103,'mBInventory',227,1679147735693,'mBCategory',1),(6000,'Couteau steack',4,'piece',1679147740137,'mBInventory',229,1679147735693,'mBCategory',1),(2500000,'Cuisiniere a gaz',1,'piece',1679147740165,'mBInventory',231,1679147735693,'mBCategory',1),(6000,'couteau simple',2,'piece',1679147740194,'mBInventory',233,1679147735693,'mBCategory',1),(30000,'Marteau a steack',2,'piece',1679147740219,'mBInventory',235,1679147735693,'mBCategory',1),(9000000,'Ciboire',9,'piece',1679147741247,'mBInventory',237,1679147735910,'mBCategory',3),(300000,'Ordinateur de bureau',1,'piece',1679147741293,'mBInventory',239,1679147736010,'mBCategory',7),(2000000,'Ordinateur portable',2,'piece',1679147741326,'mBInventory',241,1679147735910,'mBCategory',3),(5100000,'Radio JBL',1,'piece',1679147741373,'mBInventory',243,1679147736356,'mBCategory',19),(13600000,'Television',17,'piece',1679147741423,'mBInventory',245,1679147736010,'mBCategory',7),(1000000,'Imprimante',1,'piece',1679147741469,'mBInventory',247,1679147736315,'mBCategory',17),(7595000,'Chaise plastique',217,'piece',1679147741521,'mBInventory',249,1679147735910,'mBCategory',3),(2940000,'Chaise resto bar',49,'piece',1679147741564,'mBInventory',251,1679147735910,'mBCategory',3),(5000000,'Chaise et table pallaute',10,'piece',1679147741608,'mBInventory',253,1679147735910,'mBCategory',3),(2340000,'Table plastique',39,'piece',1679147741650,'mBInventory',255,1679147735910,'mBCategory',3),(1000000,'Table metallique',5,'piece',1679147741721,'mBInventory',257,1679147735910,'mBCategory',3),(1715000,'Table en bois',49,'piece',1679147741767,'mBInventory',259,1679147735910,'mBCategory',3),(2000000,'Table zibiyo nini',4,'piece',1679147741810,'mBInventory',261,1679147735910,'mBCategory',3),(750000,'Table zibiyo ntoya',3,'piece',1679147741851,'mBInventory',263,1679147735910,'mBCategory',3),(68000,'Verre de limonade',17,'piece',1679147741891,'mBInventory',265,1679147735910,'mBCategory',3),(40000,'Verre de vin',10,'piece',1679147741934,'mBInventory',267,1679147735910,'mBCategory',3),(360000,'Verre de primus',72,'piece',1679147741987,'mBInventory',269,1679147735910,'mBCategory',3),(710000,'Verre de amstel',142,'piece',1679147742018,'mBInventory',271,1679147735910,'mBCategory',3),(0,'Carton de G amstel',44,'caisse',1679147742050,'mBInventory',273,1679147735910,'mBCategory',3),(0,'Carton de G primus',34,'caisse',1679147742076,'mBInventory',275,1679147735910,'mBCategory',3),(0,'Carton de P amstel',18,'caisse',1679147742107,'mBInventory',277,1679147735910,'mBCategory',3),(0,'Carton de P primus',25,'caisse',1679147742132,'mBInventory',279,1679147735910,'mBCategory',3),(0,'Carton skool',28,'caisse',1679147742154,'mBInventory',281,1679147735910,'mBCategory',3),(0,'Carton soma',4,'caisse',1679147742194,'mBInventory',283,1679147736315,'mBCategory',17),(0,'Carton fanta',81,'caisse',1679147742244,'mBInventory',285,1679147736315,'mBCategory',17),(0,'Carton royal',16,'caisse',1679147742298,'mBInventory',287,1679147735910,'mBCategory',3),(160000,'Carton viva',36,'caisse',1679147742362,'mBInventory',289,1679147736315,'mBCategory',17),(100000,'Vide G amstel',20,'caisse',1679147742410,'mBInventory',291,1679147735910,'mBCategory',3),(235000,'Vide G primus',47,'caisse',1679147742438,'mBInventory',293,1679147735910,'mBCategory',3),(355000,'Vide royal',71,'caisse',1679147742488,'mBInventory',295,1679147735910,'mBCategory',3),(105000,'Vide fanta',21,'caisse',1679147742536,'mBInventory',297,1679147735910,'mBCategory',3),(450000,'Table comptoire',3,'piece',1679147742587,'mBInventory',299,1679147735910,'mBCategory',3),(840000,'Chaise comptoire',14,'piece',1679147742641,'mBInventory',301,1679147735910,'mBCategory',3),(0,'Chaise blanc',19,'piece',1679147742700,'mBInventory',303,1679147735910,'mBCategory',3),(1620000,'Chaise mettalique',27,'piece',1679147742749,'mBInventory',305,1679147735910,'mBCategory',3),(0,'Plateau en bois',9,'piece',1679147742803,'mBInventory',307,1679147735910,'mBCategory',3),(0,'Plteau brarudi',20,'piece',1679147742853,'mBInventory',309,1679147735910,'mBCategory',3),(4000000,'Etagere Metalique',2,'piece',1679147742924,'mBInventory',311,1679147735910,'mBCategory',3),(4500000,'Cofre fort',1,'piece',1679147742974,'mBInventory',313,1679147735910,'mBCategory',3),(30000000,'Piscine ',1,'piece',1679147743649,'mBInventory',315,1679147736048,'mBCategory',9),(12000000,'Filtre de sable',1,'piece',1679147743678,'mBInventory',317,1679147736048,'mBCategory',9),(10000000,'Aspirateur et tuyaux',1,'piece',1679147743705,'mBInventory',319,1679147736048,'mBCategory',9),(50000,'Sceau',1,'piece',1679147743731,'mBInventory',321,1679147736048,'mBCategory',9),(300000,'Porte mentaux',51,'piece',1679147743760,'mBInventory',323,1679147736010,'mBCategory',7),(5000000,'Raclette',1,'piece',1679147743793,'mBInventory',325,1679147736048,'mBCategory',9),(5000000,'Tuyaux alimanta',1,'piece',1679147743829,'mBInventory',327,1679147736048,'mBCategory',9),(400000,'Tapis au sol',4,'piece',1679147743860,'mBInventory',329,1679147736048,'mBCategory',9),(800000,'Petit tapis ',8,'piece',1679147743893,'mBInventory',331,1679147736048,'mBCategory',9),(5000000,'Pouilie multicombiné',1,'piece',1679147743925,'mBInventory',333,1679147736048,'mBCategory',9),(15000000,'Apareil de musculation',1,'piece',1679147743956,'mBInventory',335,1679147736048,'mBCategory',9),(5000000,'Althere avec ses disques',1,'piece',1679147743991,'mBInventory',337,1679147736048,'mBCategory',9),(5000000,'Support althere',1,'piece',1679147744022,'mBInventory',339,1679147736048,'mBCategory',9),(12000000,'Banc de musculation',1,'piece',1679147744052,'mBInventory',341,1679147736048,'mBCategory',9),(2000000,'Appareil vibro-masser',1,'piece',1679147744086,'mBInventory',343,1679147736048,'mBCategory',9),(5000000,'Pesepersonne',1,'piece',1679147744116,'mBInventory',345,1679147736048,'mBCategory',9),(20000000,'Ballons suisse',4,'piece',1679147744149,'mBInventory',347,1679147736048,'mBCategory',9),(15000000,'Velo cardiologique',3,'piece',1679147744180,'mBInventory',349,1679147736048,'mBCategory',9),(5000000,'Aspirateur des tapis',1,'piece',1679147744212,'mBInventory',351,1679147736048,'mBCategory',9),(250000,'Poste radio alipu',1,'piece',1679147744244,'mBInventory',353,1679147736048,'mBCategory',9),(300000,'Stabilisateur',2,'piece',1679147744281,'mBInventory',355,1679147736010,'mBCategory',7),(50000,'Tablette',1,'piece',1679147744311,'mBInventory',357,1679147736048,'mBCategory',9),(25000000,'Paire de poids de muscl',11,'piece',1679147744341,'mBInventory',359,1679147736048,'mBCategory',9),(5000000,'Support des poids',1,'piece',1679147744373,'mBInventory',361,1679147736048,'mBCategory',9),(12000000,'Roulette de muscultion',2,'piece',1679147744417,'mBInventory',363,1679147736048,'mBCategory',9),(15000000,'Appar assouplessement',1,'piece',1679147744449,'mBInventory',365,1679147736048,'mBCategory',9),(15000000,'Appar de musculation',1,'piece',1679147744510,'mBInventory',367,1679147736048,'mBCategory',9),(7000000,'Paire de gros disque',3,'piece',1679147744541,'mBInventory',369,1679147736048,'mBCategory',9),(120000,'Miroire de vestiaire',2,'piece',1679147744571,'mBInventory',371,1679147736048,'mBCategory',9),(50000,'Petit porte menteau',2,'piece',1679147744602,'mBInventory',373,1679147736048,'mBCategory',9),(3010000,'Draps',43,'piece',1679147745074,'mBInventory',375,1679147736010,'mBCategory',7),(2560000,'Essuie-main',64,'piece',1679147745107,'mBInventory',377,1679147736010,'mBCategory',7),(18900000,'Lits',27,'piece',1679147745142,'mBInventory',379,1679147736010,'mBCategory',7),(27000000,'Matelas',27,'piece',1679147745177,'mBInventory',381,1679147736010,'mBCategory',7),(15000000,'Camera',1,'piece',1679147745208,'mBInventory',383,1679147736315,'mBCategory',17),(6000000,'Chauffe-eau',12,'piece',1679147745250,'mBInventory',385,1679147736010,'mBCategory',7),(7000000,'Sallon ministre',7,'piece',1679147745280,'mBInventory',387,1679147736010,'mBCategory',7),(4500000,'Machine a laver',3,'piece',1679147745312,'mBInventory',389,1679147736010,'mBCategory',7),(625000,'Miroires',25,'piece',1679147745347,'mBInventory',391,1679147736010,'mBCategory',7),(450000,'Routeur',1,'piece',1679147745397,'mBInventory',393,1679147736315,'mBCategory',17),(1500000,'Moustiquaire',25,'piece',1679147745431,'mBInventory',395,1679147736010,'mBCategory',7),(2700000,'Telephone fixe',15,'piece',1679147745459,'mBInventory',397,1679147736010,'mBCategory',7),(15000,'Chaise',1,'piece',1679147745490,'mBInventory',399,1679147736812,'mBCategory',41),(660000,'Tapis',44,'piece',1679147745519,'mBInventory',401,1679147736010,'mBCategory',7),(100000,'Babouche',25,'piece',1679147745551,'mBInventory',403,1679147736010,'mBCategory',7),(140000,'Allonges',7,'piece',1679147745579,'mBInventory',405,1679147736010,'mBCategory',7),(15000,'Contre fauteuil',3,'piece',1679147745601,'mBInventory',407,1679147736010,'mBCategory',7),(130000,'Faire a repasser',2,'piece',1679147745646,'mBInventory',409,1679147736010,'mBCategory',7),(375000,'Poubelles',25,'piece',1679147745675,'mBInventory',411,1679147736010,'mBCategory',7),(195000,'Té oreille',39,'piece',1679147745719,'mBInventory',413,1679147736010,'mBCategory',7),(420000,'Decodeur',6,'piece',1679147745748,'mBInventory',415,1679147736010,'mBCategory',7),(2512000,'Rideaux ',67,'piece',1679147745776,'mBInventory',417,1679147736010,'mBCategory',7),(360000,'Rideaux du jour',6,'piece',1679147745806,'mBInventory',419,1679147736010,'mBCategory',7),(2160000,'Porte rideau',27,'piece',1679147745838,'mBInventory',421,1679147736010,'mBCategory',7),(180000,'Males',3,'piece',1679147745863,'mBInventory',423,1679147736010,'mBCategory',7),(640000,'Extincteur',2,'piece',1679147745887,'mBInventory',425,1679147736010,'mBCategory',7),(3000000,'Photocopieuse',1,'piece',1679147745920,'mBInventory',427,1679147736010,'mBCategory',7),(11200000,'Central telephonique',1,'piece',1679147745945,'mBInventory',429,1679147736010,'mBCategory',7),(840000,'Bagage eau',6,'piece',1679147745969,'mBInventory',431,1679147736010,'mBCategory',7),(5400000,'Couvre-lits',27,'piece',1679147745993,'mBInventory',433,1679147736010,'mBCategory',7),(800000,'Table',40,'piece',1679147746030,'mBInventory',435,1679147736010,'mBCategory',7),(300000,'Telecommande',15,'piece',1679147746056,'mBInventory',437,1679147736010,'mBCategory',7),(1500000,'Contoire',1,'piece',1679147746081,'mBInventory',439,1679147736010,'mBCategory',7),(1960000,'Assiette',196,'piece',1679147746811,'mBInventory',441,1679147736278,'mBCategory',15),(1000000,'Fluteuses',4,'piece',1679147746857,'mBInventory',443,1679147736278,'mBCategory',15),(280000,'Bassins G',8,'piece',1679147746896,'mBInventory',445,1679147736278,'mBCategory',15),(0,'Tamis',11,'piece',1679147746937,'mBInventory',447,1679147736278,'mBCategory',15),(220000,'Isafuriya',11,'piece',1679147746992,'mBInventory',449,1679147736278,'mBCategory',15),(90000,'Ikarayi',6,'piece',1679147747032,'mBInventory',451,1679147736278,'mBCategory',15),(1200000,'Mixeur',1,'piece',1679147747071,'mBInventory',453,1679147736356,'mBCategory',19),(1000000,'Congelateur',2,'piece',1679147747113,'mBInventory',455,1679147736278,'mBCategory',15),(4700000,'Cuisiniere a gaz',1,'piece',1679147747154,'mBInventory',457,1679147736278,'mBCategory',15),(90000,'Balance',2,'piece',1679147747196,'mBInventory',459,1679147736278,'mBCategory',15),(75000,'Planches',3,'piece',1679147747235,'mBInventory',461,1679147736278,'mBCategory',15),(120000,'Pallette',6,'piece',1679147747281,'mBInventory',463,1679147736278,'mBCategory',15),(0,'Ipanu',6,'piece',1679147747324,'mBInventory',465,1679147736278,'mBCategory',15),(52000,'Bassin P',13,'piece',1679147747365,'mBInventory',467,1679147736278,'mBCategory',15),(0,'Ibirika',12,'piece',1679147747405,'mBInventory',469,1679147736278,'mBCategory',15),(168000,'Couteau de table',84,'piece',1679147747463,'mBInventory',471,1679147736278,'mBCategory',15),(76500,'Utuyiko dutoya',51,'piece',1679147747491,'mBInventory',473,1679147736278,'mBCategory',15),(380000,'Bolle',76,'piece',1679147747519,'mBInventory',475,1679147736278,'mBCategory',15),(0,'Table de cuisine',8,'piece',1679147747550,'mBInventory',477,1679147736278,'mBCategory',15),(0,'Cabelas',1,'piece',1679147747577,'mBInventory',479,1679147736278,'mBCategory',15),(12500,'Rouche',5,'piece',1679147747602,'mBInventory',481,1679147736278,'mBCategory',15),(0,'Machette de cuisine',3,'piece',1679147747629,'mBInventory',483,1679147736278,'mBCategory',15),(6500000,'Four akabenzi ',1,'piece',1679147747659,'mBInventory',485,1679147736278,'mBCategory',15),(600000,'Vantilateur',1,'piece',1679147747687,'mBInventory',487,1679147736278,'mBCategory',15),(0,'Carton amstel G',67,'caisse',1679147748133,'mBInventory',489,1679147736315,'mBCategory',17),(0,'Carton P amstel ',28,'caisse',1679147748170,'mBInventory',491,1679147736315,'mBCategory',17),(0,'Carton primus G',26,'caisse',1679147748196,'mBInventory',493,1679147736315,'mBCategory',17),(0,'Carton primus p',33,'caisse',1679147748223,'mBInventory',495,1679147736315,'mBCategory',17),(0,'Carton royale',41,'caisse',1679147748272,'mBInventory',497,1679147736315,'mBCategory',17),(0,'Carton skol',20,'caisse',1679147748302,'mBInventory',499,1679147736315,'mBCategory',17),(0,'Carton tusker',2,'caisse',1679147748336,'mBInventory',501,1679147736315,'mBCategory',17),(0,'Carton sminoff',2,'caisse',1679147748367,'mBInventory',503,1679147736315,'mBCategory',17),(0,'Bouteuille soma',12,'caisse',1679147748400,'mBInventory',505,1679147736315,'mBCategory',17),(0,'Bouteuille amstel G',6,'caisse',1679147748431,'mBInventory',507,1679147736315,'mBCategory',17),(0,'Bouteuille amstel P',5,'caisse',1679147748463,'mBInventory',509,1679147736315,'mBCategory',17),(0,'Bouteuille primus G',7,'caisse',1679147748492,'mBInventory',511,1679147736315,'mBCategory',17),(0,'Bouteuille primus P',13,'caisse',1679147748529,'mBInventory',513,1679147736315,'mBCategory',17),(0,'Bouteuille fanta',17,'caisse',1679147748564,'mBInventory',515,1679147736315,'mBCategory',17),(0,'Bouteuille viva',8,'caisse',1679147748596,'mBInventory',517,1679147736315,'mBCategory',17),(0,'Bouteuille royale',3,'caisse',1679147748625,'mBInventory',519,1679147736315,'mBCategory',17),(0,'Carton vide amstel G',7,'caisse',1679147748656,'mBInventory',521,1679147736315,'mBCategory',17),(0,'Carton vide amstel P',33,'caisse',1679147748688,'mBInventory',523,1679147736315,'mBCategory',17),(0,'Carton vide primus G',16,'caisse',1679147748720,'mBInventory',525,1679147736315,'mBCategory',17),(0,'Carton vide viva',13,'caisse',1679147748752,'mBInventory',527,1679147736315,'mBCategory',17),(6580000,'Chaises ',146,'piece',1679147748786,'mBInventory',529,1679147736315,'mBCategory',17),(3900000,'Tables',48,'piece',1679147748838,'mBInventory',531,1679147736315,'mBCategory',17),(6600000,'Chaises de comptoir',44,'piece',1679147748869,'mBInventory',533,1679147736315,'mBCategory',17),(2660000,'Tables de comptoir',10,'piece',1679147748899,'mBInventory',535,1679147736315,'mBCategory',17),(2400000,' fauteuils complet',6,'piece',1679147748929,'mBInventory',537,1679147736315,'mBCategory',17),(0,'Chaises plastiq',270,'piece',1679147748961,'mBInventory',539,1679147736315,'mBCategory',17),(0,'Tables plastiq',44,'piece',1679147748990,'mBInventory',541,1679147736315,'mBCategory',17),(4500000,'Imitaka ',30,'piece',1679147749020,'mBInventory',543,1679147736315,'mBCategory',17),(10000000,'Billard',2,'piece',1679147749051,'mBInventory',545,1679147736315,'mBCategory',17),(6000000,'Frigo',2,'piece',1679147749084,'mBInventory',547,1679147736315,'mBCategory',17),(0,'Frigo brarudi',1,'piece',1679147749116,'mBInventory',549,1679147736315,'mBCategory',17),(400000,'Ordinateur',1,'piece',1679147749147,'mBInventory',551,1679147736315,'mBCategory',17),(60000,'Parapluie',5,'piece',1679147749195,'mBInventory',553,1679147736315,'mBCategory',17),(10000,'Carculatrice',1,'piece',1679147749228,'mBInventory',555,1679147736315,'mBCategory',17),(0,'Poubelle',3,'piece',1679147749256,'mBInventory',557,1679147736315,'mBCategory',17),(0,'Congelateur',1,'piece',1679147749304,'mBInventory',559,1679147736315,'mBCategory',17),(0,'Verre simple',50,'piece',1679147749344,'mBInventory',561,1679147736315,'mBCategory',17),(0,'Congelateur brarudi',1,'piece',1679147749373,'mBInventory',563,1679147736315,'mBCategory',17),(0,'Plateaux',9,'piece',1679147749404,'mBInventory',565,1679147736315,'mBCategory',17),(0,'Coupe',30,'piece',1679147749436,'mBInventory',567,1679147736315,'mBCategory',17),(0,'Verre de vin',19,'piece',1679147749467,'mBInventory',569,1679147736315,'mBCategory',17),(0,'Verre brarudi',146,'piece',1679147749496,'mBInventory',571,1679147736315,'mBCategory',17),(200000,'Tissu',10,'piece',1679147750335,'mBInventory',573,1679147736356,'mBCategory',19),(450000,'Retro projecteur',1,'piece',1679147750377,'mBInventory',575,1679147736356,'mBCategory',19),(120000,'Allonge',8,'piece',1679147750411,'mBInventory',577,1679147735964,'mBCategory',5),(3700000,'sonolisation',1,'piece',1679147750536,'mBInventory',579,1679147736356,'mBCategory',19),(1600000,'Ventillateur',2,'piece',1679147750579,'mBInventory',581,1679147736356,'mBCategory',19),(1000000,'Radio simple',1,'piece',1679147750627,'mBInventory',583,1679147736356,'mBCategory',19),(25000000,'Camionnette',1,'piece',1679147750789,'mBInventory',585,1679147736083,'mBCategory',11),(20000000,'Groupe electrogene',1,'piece',1679147750821,'mBInventory',587,1679147736394,'mBCategory',21),(35000000,'Groupe electrogene 1',1,'piece',1679147750902,'mBInventory',589,1679147736083,'mBCategory',11),(25000000,'Groupe electrogene 2',1,'piece',1679147750926,'mBInventory',591,1679147736083,'mBCategory',11),(1054000,'Amballage de 25kg',1054,'piece',1679147750996,'mBInventory',593,1679147736677,'mBCategory',33),(566300,'Amballage de10kg',809,'piece',1679147751023,'mBInventory',595,1679147736677,'mBCategory',33),(1105650,'Amballage de 5kg',1701,'piece',1679147751055,'mBInventory',597,1679147736677,'mBCategory',33),(2701300,'Amballage de 1kg',3859,'piece',1679147751081,'mBInventory',599,1679147736677,'mBCategory',33),(372350,'Amballage de 500g',677,'piece',1679147751111,'mBInventory',601,1679147736677,'mBCategory',33),(1311000,'Maïs',874,'kg',1679147751269,'mBInventory',603,1679147736525,'mBCategory',25),(1768000,'Sorgho',1040,'kg',1679147751314,'mBInventory',605,1679147736525,'mBCategory',25),(20481001,'Soja',1024,'kg',1679147751359,'mBInventory',607,1679147736525,'mBCategory',25),(297000,'Blé',110,'kg',1679147751403,'mBInventory',609,1679147736525,'mBCategory',25),(60000000,'Ligne électrique',1,'piece',1679147751595,'mBInventory',611,1679147736711,'mBCategory',35),(50000000,'Groupe electrogene',1,'piece',1679147751630,'mBInventory',613,1679147736711,'mBCategory',35),(0,'Droyer',1,'piece',1679147751697,'mBInventory',615,1679147736779,'mBCategory',39),(5000000,'Grader',1,'piece',1679147751724,'mBInventory',617,1679147736779,'mBCategory',39),(4000000,'Machine Cynclone',1,'piece',1679147751759,'mBInventory',619,1679147736779,'mBCategory',39),(9000000,'Melangeur',1,'piece',1679147751789,'mBInventory',621,1679147736779,'mBCategory',39),(8550000,'Torrefacteur',1,'piece',1679147751821,'mBInventory',623,1679147736779,'mBCategory',39),(0,'Maϊs',1,'piece',1679147751855,'mBInventory',625,1679147736779,'mBCategory',39),(15000000,'Cynclone Inoxydable',2,'piece',1679147751887,'mBInventory',627,1679147736779,'mBCategory',39),(0,'Riz',1,'piece',1679147751915,'mBInventory',629,1679147736779,'mBCategory',39),(0,'Farine de maïs de 25kg',0,'piece',1679147752089,'mBInventory',631,1679147736564,'mBCategory',27),(0,'Farine de maïs de 10kg',0,'piece',1679147752115,'mBInventory',633,1679147736564,'mBCategory',27),(0,'Farine de maïs de 5kg',0,'piece',1679147752146,'mBInventory',635,1679147736564,'mBCategory',27),(0,'Farine de boullie de 1kg',0,'piece',1679147752177,'mBInventory',637,1679147736564,'mBCategory',27),(0,'Farine de boullie de 500g',0,'piece',1679147752208,'mBInventory',639,1679147736564,'mBCategory',27),(0,'Son de maïs qualité 1',0,'kg',1679147752317,'mBInventory',641,1679147736601,'mBCategory',29),(0,'Son de maïs qualité 2',0,'kg',1679147752344,'mBInventory',643,1679147736601,'mBCategory',29),(500000,'Machine à coudre',1,'piece',1679147752415,'mBInventory',645,1679147736812,'mBCategory',41),(600000,'Balance electronique',1,'piece',1679147752462,'mBInventory',647,1679147736812,'mBCategory',41),(1000000,'Balance zhusheng',1,'piece',1679147752513,'mBInventory',649,1679147736812,'mBCategory',41),(20000,'Allonge',1,'piece',1679147752557,'mBInventory',651,1679147736812,'mBCategory',41),(60000,'Table de bureau',1,'piece',1679147752603,'mBInventory',653,1679147736812,'mBCategory',41),(90000,'Uniforme',9,'piece',1679147752656,'mBInventory',655,1679147736812,'mBCategory',41),(90000,'Botune',6,'piece',1679147752680,'mBInventory',657,1679147736812,'mBCategory',41),(60000,'Marmite',1,'piece',1679147752711,'mBInventory',659,1679147736812,'mBCategory',41),(24000,'Beche',4,'piece',1679147752735,'mBInventory',661,1679147736812,'mBCategory',41),(0,'Mercedes benz 3236',1,'piece',1679147752912,'mBInventory',663,1679147736642,'mBCategory',31),(0,'Batiment',3,'piece',1679147752954,'mBInventory',665,1679147736746,'mBCategory',37);
/*!40000 ALTER TABLE `mbinventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbinventory_order`
--

DROP TABLE IF EXISTS `mbinventory_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbinventory_order` (
  `order_entry` int DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `inventory_date_created` bigint DEFAULT '0',
  `inventory_item_type` varchar(255) DEFAULT 'item',
  `inventory_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKpktkh97i68l38wjljxcdk544r` (`inventory_date_created`,`inventory_item_type`,`inventory_t_key`),
  CONSTRAINT `FKh9pxyqo96gbmrc5nvi65osclt` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `mborder_type` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKpktkh97i68l38wjljxcdk544r` FOREIGN KEY (`inventory_date_created`, `inventory_item_type`, `inventory_t_key`) REFERENCES `mbinventory` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbinventory_order`
--

LOCK TABLES `mbinventory_order` WRITE;
/*!40000 ALTER TABLE `mbinventory_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbinventory_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbinvoice`
--

DROP TABLE IF EXISTS `mbinvoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbinvoice` (
  `amount` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `invoice_number` varchar(255) DEFAULT NULL,
  `payment_mode` int DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `capital_entry_date_created` bigint DEFAULT '0',
  `capital_entry_item_type` varchar(255) DEFAULT 'item',
  `capital_entry_t_key` bigint DEFAULT '0',
  `client_date_created` bigint DEFAULT '0',
  `client_item_type` varchar(255) DEFAULT 'item',
  `client_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK2ow8k0pr09mrb2yc2h16nj1ok` (`capital_entry_date_created`,`capital_entry_item_type`,`capital_entry_t_key`),
  KEY `FKlo7n4c89d705jjq25hlw5m3po` (`client_date_created`,`client_item_type`,`client_t_key`),
  CONSTRAINT `FK1loku5evc57m0oxa936rkeg7d` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK2ow8k0pr09mrb2yc2h16nj1ok` FOREIGN KEY (`capital_entry_date_created`, `capital_entry_item_type`, `capital_entry_t_key`) REFERENCES `mbcapital_entry` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKlo7n4c89d705jjq25hlw5m3po` FOREIGN KEY (`client_date_created`, `client_item_type`, `client_t_key`) REFERENCES `mbclient` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbinvoice`
--

LOCK TABLES `mbinvoice` WRITE;
/*!40000 ALTER TABLE `mbinvoice` DISABLE KEYS */;
INSERT INTO `mbinvoice` VALUES (100000,'HEBERGEMENT:100000','IN-748',2,1679150764052,'mBInvoice',746,1679150764203,'mBCapitalEntry',749,1679147737637,'mBClient',83),(558500,'RESTO:275000\nBAR:283500\n','IN-753',2,1679150882869,'mBInvoice',751,1679150882986,'mBCapitalEntry',754,1679147737371,'mBClient',67),(350000,'RESTO:10000\nBAR:60000\nHEBERGEMENT:280000','IN-758',2,1679151000771,'mBInvoice',756,1679151000899,'mBCapitalEntry',759,1679147737305,'mBClient',63),(28000,'RESTO:28000','IN-763',2,1679151042448,'mBInvoice',761,1679151042553,'mBCapitalEntry',764,1679147737442,'mBClient',71),(1040000,'RESTO:962000\nBAR:78000\n','IN-768',2,1679151129812,'mBInvoice',766,1679151129909,'mBCapitalEntry',769,1679147737233,'mBClient',59),(1593000,'RESTO:1330000\nBAR:63000\nSALLE:200000','IN-773',2,1679151266186,'mBInvoice',771,1679151266279,'mBCapitalEntry',774,1679147736985,'mBClient',45),(645000,'RESTO:332000\nBAR:313000','IN-783',2,1679151361094,'mBInvoice',781,1679151361205,'mBCapitalEntry',784,1679147737512,'mBClient',75),(1729000,'RESTO:584000\n\nBAR:595000\n\nHEBERGEMENT:550000','IN-793',2,1679151624483,'mBInvoice',791,1679151624581,'mBCapitalEntry',794,1679147737479,'mBClient',73),(7601000,'RESTO:3812500\nBAR:757500\nHEBERGEMENT:2550000\nSALLE:480000','IN-798',2,1679151728990,'mBInvoice',796,1679151729096,'mBCapitalEntry',799,1679147736930,'mBClient',43),(745000,'RESTO:528000\nBAR:57000\nHEBERGEMENT:160000','IN-803',2,1679151838355,'mBInvoice',801,1679151838430,'mBCapitalEntry',804,1679147737131,'mBClient',53),(3678000,'RESTO:2242000\nBAR:986000\nSALLE:450000','IN-808',2,1679151926045,'mBInvoice',806,1679151926182,'mBCapitalEntry',809,1679147737194,'mBClient',57),(341000,'BAR:161000\nHEBERGEMENT:180000','IN-813',2,1679152049788,'mBInvoice',811,1679152049883,'mBCapitalEntry',814,1679147737057,'mBClient',49),(460000,'BAR:100000\nHEBERGEMENT:360000','IN-818',2,1679152108711,'mBInvoice',816,1679152108813,'mBCapitalEntry',819,1679147737270,'mBClient',61),(600000,'HEBERGEMENT:600000','IN-823',2,1679152151437,'mBInvoice',821,1679152151534,'mBCapitalEntry',824,1679147737577,'mBClient',79),(80000,'HEBERGRMENT:80000','IN-828',2,1679152197347,'mBInvoice',826,1679152197449,'mBCapitalEntry',829,1679147737547,'mBClient',77),(137500,'RESTO:100000\nBAR:37500','IN-833',2,1679152252914,'mBInvoice',831,1679152253012,'mBCapitalEntry',834,1679147737407,'mBClient',69),(60000,'HEBERGEMENT:60000','IN-838',2,1679152292972,'mBInvoice',836,1679152293033,'mBCapitalEntry',839,1679147737342,'mBClient',65),(195000,'HEBERGEMENT:195000','IN-843',2,1679152351966,'mBInvoice',841,1679152352064,'mBCapitalEntry',844,1679147737666,'mBClient',85),(508000,'RESTO:324000\nBAR:84000\nSALLE:100000','IN-848',2,1679152426977,'mBInvoice',846,1679152427096,'mBCapitalEntry',849,1679147737095,'mBClient',51),(10832000,'RESTO:8140000\nBAR:1692000\nSALLE:1000000','IN-853',2,1679152544046,'mBInvoice',851,1679152544161,'mBCapitalEntry',854,1679147737022,'mBClient',47),(1415000,'RESTO:1072500\nBAR:192500\nSALLE:150000','IN-863',2,1679152648839,'mBInvoice',861,1679152648936,'mBCapitalEntry',864,1679147737160,'mBClient',55),(60000,'HEBERGEMENT:60000','IN-868',2,1679152726806,'mBInvoice',866,1679152726901,'mBCapitalEntry',869,1679147737608,'mBClient',81),(55000,'BAR:25000\nHEBERGEMENT:30000','IN-889',2,1679153056579,'mBInvoice',887,1679153056625,'mBCapitalEntry',890,1679152987192,'mBClient',885),(55000,'BAR:55000','IN-894',2,1679153112347,'mBInvoice',892,1679153112454,'mBCapitalEntry',895,1679152810458,'mBClient',871),(354000,'RESTO:239000\nBAR:115000','IN-899',2,1679153193643,'mBInvoice',897,1679153193743,'mBCapitalEntry',900,1679152881000,'mBClient',878),(19193500,'RESTO:11838000\nBAR:2020500\nSALLE:1640000\nHEBERGEMENT:3695000','IN-904',2,1679153453632,'mBInvoice',902,1679153453724,'mBCapitalEntry',905,1679147736930,'mBClient',43),(2104000,'RESTO:1470000\nBART:294000\nHEBERGEMENT:100000\nSALLE:240000','IN-909',2,1679153570784,'mBInvoice',907,1679153570872,'mBCapitalEntry',910,1679147737637,'mBClient',83),(4074000,'RESTO:3034000\nBAR:240000\nSALLE:800000','IN-914',2,1679153736570,'mBInvoice',912,1679153736664,'mBCapitalEntry',915,1679147737233,'mBClient',59),(3945000,'RESTO:1885000\nBAR:1610000\nSALLE:450000','IN-921',2,1679153839190,'mBInvoice',919,1679153839280,'mBCapitalEntry',922,1679153780614,'mBClient',917),(1680000,'RESTO:1320000\nBAR:210000\nSALLE:150000','IN-928',2,1679153952153,'mBInvoice',926,1679153952240,'mBCapitalEntry',929,1679153882789,'mBClient',924),(410000,'RESTO:274000\nBAR:56000\nSALLE:80000','IN-933',2,1679154031874,'mBInvoice',931,1679154031982,'mBCapitalEntry',934,1679147737095,'mBClient',51),(1592500,'RESTO:1232500\nBAR:210000\nSALLE:150000','IN-940',2,1679154172691,'mBInvoice',938,1679154172799,'mBCapitalEntry',941,1679154077320,'mBClient',936),(1302500,'RESTO:990000\nBAR:112500\nSALLE:200000','IN-947',2,1679154284759,'mBInvoice',945,1679154284870,'mBCapitalEntry',948,1679154215342,'mBClient',943),(310000,'RESTO:80000\nHEBERGEMENT:23000','IN-952',2,1679154358650,'mBInvoice',950,1679154358743,'mBCapitalEntry',953,1679147737305,'mBClient',63),(80000,'HEBERGEMENT:80000','IN-957',2,1679154435578,'mBInvoice',955,1679154435721,'mBCapitalEntry',958,1679147737131,'mBClient',53),(473500,'BAR:173500\nHEBERGEMENT:300000','IN-969',2,1679154585030,'mBInvoice',967,1679154585119,'mBCapitalEntry',970,1679154513222,'mBClient',965),(740000,'HEBERGEMENT:740000','IN-974',2,1679154638320,'mBInvoice',972,1679154638412,'mBCapitalEntry',975,1679147737577,'mBClient',79),(40000,'HEBERGEMENT:40000','IN-979',2,1679154683848,'mBInvoice',977,1679154683938,'mBCapitalEntry',980,1679147737270,'mBClient',61),(51500,'RESTO:51500','IN-986',0,1679154747632,'mBInvoice',984,1679154747725,'mBCapitalEntry',987,1679154721973,'mBClient',982),(83000,'RESTO:70000\nBAR:13000','IN-991',2,1679154793149,'mBInvoice',989,1679154793250,'mBCapitalEntry',992,1679147737608,'mBClient',81),(392500,'RESTO:196000\nBAR:56500\nHEBERGEMENT:140000','IN-996',2,1679154997882,'mBInvoice',994,1679154997912,'mBCapitalEntry',997,1679147737479,'mBClient',73),(74000,'BAR:74000','IN-1003',2,1679155127390,'mBInvoice',1001,1679155127430,'mBCapitalEntry',1004,1679155047432,'mBClient',999),(906000,'RESTO:26500\nBAR:879500\n','IN-1010',2,1679155231140,'mBInvoice',1008,1679155231226,'mBCapitalEntry',1011,1679155182558,'mBClient',1006),(63500,'RESTO:63500','IN-1017',2,1679155325791,'mBInvoice',1015,1679155325876,'mBCapitalEntry',1018,1679155297685,'mBClient',1013),(27000,'BAR:27000','IN-1022',2,1679155366433,'mBInvoice',1020,1679155366527,'mBCapitalEntry',1023,1679152810458,'mBClient',871),(210000,'RESTO:103000\nBAR:107000','IN-1027',2,1679155445883,'mBInvoice',1025,1679155445969,'mBCapitalEntry',1028,1679152881000,'mBClient',878),(38500,'BAR:38500','IN-1034',2,1679155521346,'mBInvoice',1032,1679155521434,'mBCapitalEntry',1035,1679155485815,'mBClient',1030),(120000,'HEBERGEMENT:120000','IN-1039',2,1679157697891,'mBInvoice',1037,1679157697926,'mBCapitalEntry',1040,1679147737342,'mBClient',65);
/*!40000 ALTER TABLE `mbinvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mborder_type`
--

DROP TABLE IF EXISTS `mborder_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mborder_type` (
  `cost` bigint NOT NULL,
  `order_date` date DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `paid` bit(1) NOT NULL,
  `quantity` int NOT NULL,
  `unit` int NOT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `client_date_created` bigint DEFAULT '0',
  `client_item_type` varchar(255) DEFAULT 'item',
  `client_t_key` bigint DEFAULT '0',
  `invoice_date_created` bigint DEFAULT '0',
  `invoice_item_type` varchar(255) DEFAULT 'item',
  `invoice_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK9pjfia6o9gr1dxklhuec6f6sh` (`client_date_created`,`client_item_type`,`client_t_key`),
  KEY `FKjf1p9rnv8h8k9xu34sogvor0d` (`invoice_date_created`,`invoice_item_type`,`invoice_t_key`),
  CONSTRAINT `FK4jwhp08l91q8lwu7eyvwx5w8y` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK9pjfia6o9gr1dxklhuec6f6sh` FOREIGN KEY (`client_date_created`, `client_item_type`, `client_t_key`) REFERENCES `mbclient` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKjf1p9rnv8h8k9xu34sogvor0d` FOREIGN KEY (`invoice_date_created`, `invoice_item_type`, `invoice_t_key`) REFERENCES `mbinvoice` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mborder_type`
--

LOCK TABLES `mborder_type` WRITE;
/*!40000 ALTER TABLE `mborder_type` DISABLE KEYS */;
INSERT INTO `mborder_type` VALUES (120000,'2023-01-01','RO-718',_binary '\0',1,30,1679150541107,'mBRentOrder',716,1679148939134,'mBClient',685,NULL,NULL,NULL),(120000,'2023-02-01','RO-721',_binary '\0',1,30,1679150541201,'mBRentOrder',719,1679148939134,'mBClient',685,NULL,NULL,NULL),(70000,'2023-01-01','RO-724',_binary '\0',1,30,1679150541285,'mBRentOrder',722,1679148939079,'mBClient',681,NULL,NULL,NULL),(70000,'2023-02-01','RO-727',_binary '\0',1,30,1679150541388,'mBRentOrder',725,1679148939079,'mBClient',681,NULL,NULL,NULL),(120000,'2023-01-01','RO-730',_binary '\0',1,30,1679150541457,'mBRentOrder',728,1679148939050,'mBClient',679,NULL,NULL,NULL),(120000,'2023-02-01','RO-733',_binary '\0',1,30,1679150541524,'mBRentOrder',731,1679148939050,'mBClient',679,NULL,NULL,NULL),(250000,'2023-01-01','RO-736',_binary '\0',1,30,1679150541592,'mBRentOrder',734,1679148939019,'mBClient',677,NULL,NULL,NULL),(250000,'2023-02-01','RO-739',_binary '\0',1,30,1679150541653,'mBRentOrder',737,1679148939019,'mBClient',677,NULL,NULL,NULL),(120000,'2023-01-01','RO-742',_binary '\0',1,30,1679150541723,'mBRentOrder',740,1679148939164,'mBClient',687,NULL,NULL,NULL),(120000,'2023-02-01','RO-745',_binary '\0',1,30,1679150541785,'mBRentOrder',743,1679148939164,'mBClient',687,NULL,NULL,NULL);
/*!40000 ALTER TABLE `mborder_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbrent_contract`
--

DROP TABLE IF EXISTS `mbrent_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbrent_contract` (
  `contract_file_name` varchar(255) DEFAULT NULL,
  `cost_per_unit` bigint NOT NULL,
  `t_from` date DEFAULT NULL,
  `next_order_date` date DEFAULT NULL,
  `t_to` date DEFAULT NULL,
  `unit` int NOT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `client_date_created` bigint DEFAULT '0',
  `client_item_type` varchar(255) DEFAULT 'item',
  `client_t_key` bigint DEFAULT '0',
  `rent_property_date_created` bigint DEFAULT '0',
  `rent_property_item_type` varchar(255) DEFAULT 'item',
  `rent_property_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK7c6efslc57raiq7ytwr2de3ba` (`client_date_created`,`client_item_type`,`client_t_key`),
  KEY `FKwhlni7ky5gs5pgux8hacxo3g` (`rent_property_date_created`,`rent_property_item_type`,`rent_property_t_key`),
  CONSTRAINT `FK7c6efslc57raiq7ytwr2de3ba` FOREIGN KEY (`client_date_created`, `client_item_type`, `client_t_key`) REFERENCES `mbclient` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKisf150lnh1khtt54pj8rj2mjh` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKwhlni7ky5gs5pgux8hacxo3g` FOREIGN KEY (`rent_property_date_created`, `rent_property_item_type`, `rent_property_t_key`) REFERENCES `mbrent_property` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbrent_contract`
--

LOCK TABLES `mbrent_contract` WRITE;
/*!40000 ALTER TABLE `mbrent_contract` DISABLE KEYS */;
INSERT INTO `mbrent_contract` VALUES ('label.upload.contract',120000,'2023-01-01','2023-04-01','2023-12-31',30,1679149781560,'mBRentContract',701,1679148939134,'mBClient',685,1679147753095,'mBRentProperty',673),('label.upload.contract',70000,'2023-01-01','2023-04-01','2023-12-31',30,1679149928293,'mBRentContract',704,1679148939079,'mBClient',681,1679147753123,'mBRentProperty',675),('label.upload.contract',120000,'2023-01-01','2023-04-01','2023-12-31',30,1679150375137,'mBRentContract',707,1679148939050,'mBClient',679,1679147753067,'mBRentProperty',671),('label.upload.contract',250000,'2023-01-01','2023-04-01','2023-12-31',30,1679150437482,'mBRentContract',710,1679148939019,'mBClient',677,1679147753005,'mBRentProperty',667),('label.upload.contract',120000,'2023-01-01','2023-04-01','2023-12-31',30,1679150478822,'mBRentContract',713,1679148939164,'mBClient',687,1679149581844,'mBRentProperty',699);
/*!40000 ALTER TABLE `mbrent_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbrent_order`
--

DROP TABLE IF EXISTS `mbrent_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbrent_order` (
  `t_from` date DEFAULT NULL,
  `t_to` date DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `contract_date_created` bigint DEFAULT '0',
  `contract_item_type` varchar(255) DEFAULT 'item',
  `contract_t_key` bigint DEFAULT '0',
  `rent_property_date_created` bigint DEFAULT '0',
  `rent_property_item_type` varchar(255) DEFAULT 'item',
  `rent_property_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKmj7rdsyyynavjma51ihm2hflm` (`contract_date_created`,`contract_item_type`,`contract_t_key`),
  KEY `FK88uu1wu9moacfef7k01tdgfnd` (`rent_property_date_created`,`rent_property_item_type`,`rent_property_t_key`),
  CONSTRAINT `FK88uu1wu9moacfef7k01tdgfnd` FOREIGN KEY (`rent_property_date_created`, `rent_property_item_type`, `rent_property_t_key`) REFERENCES `mbrent_property` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKmj7rdsyyynavjma51ihm2hflm` FOREIGN KEY (`contract_date_created`, `contract_item_type`, `contract_t_key`) REFERENCES `mbrent_contract` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKs38qfqf02bx5h7dhgfjyw9fhb` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `mborder_type` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbrent_order`
--

LOCK TABLES `mbrent_order` WRITE;
/*!40000 ALTER TABLE `mbrent_order` DISABLE KEYS */;
INSERT INTO `mbrent_order` VALUES ('2023-01-01','2023-02-01',1679150541107,'mBRentOrder',716,1679149781560,'mBRentContract',701,1679147753095,'mBRentProperty',673),('2023-02-01','2023-03-01',1679150541201,'mBRentOrder',719,1679149781560,'mBRentContract',701,1679147753095,'mBRentProperty',673),('2023-01-01','2023-02-01',1679150541285,'mBRentOrder',722,1679149928293,'mBRentContract',704,1679147753123,'mBRentProperty',675),('2023-02-01','2023-03-01',1679150541388,'mBRentOrder',725,1679149928293,'mBRentContract',704,1679147753123,'mBRentProperty',675),('2023-01-01','2023-02-01',1679150541457,'mBRentOrder',728,1679150375137,'mBRentContract',707,1679147753067,'mBRentProperty',671),('2023-02-01','2023-03-01',1679150541524,'mBRentOrder',731,1679150375137,'mBRentContract',707,1679147753067,'mBRentProperty',671),('2023-01-01','2023-02-01',1679150541592,'mBRentOrder',734,1679150437482,'mBRentContract',710,1679147753005,'mBRentProperty',667),('2023-02-01','2023-03-01',1679150541653,'mBRentOrder',737,1679150437482,'mBRentContract',710,1679147753005,'mBRentProperty',667),('2023-01-01','2023-02-01',1679150541723,'mBRentOrder',740,1679150478822,'mBRentContract',713,1679149581844,'mBRentProperty',699),('2023-02-01','2023-03-01',1679150541785,'mBRentOrder',743,1679150478822,'mBRentContract',713,1679149581844,'mBRentProperty',699);
/*!40000 ALTER TABLE `mbrent_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbrent_property`
--

DROP TABLE IF EXISTS `mbrent_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbrent_property` (
  `address` varchar(255) DEFAULT NULL,
  `cost` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_income` bigint NOT NULL,
  `unit` int NOT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `current_contract_date_created` bigint DEFAULT '0',
  `current_contract_item_type` varchar(255) DEFAULT 'item',
  `current_contract_t_key` bigint DEFAULT '0',
  `facility_date_created` bigint DEFAULT '0',
  `facility_item_type` varchar(255) DEFAULT 'item',
  `facility_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKt4f16rwkxq7sbgpdv2m2av95y` (`current_contract_date_created`,`current_contract_item_type`,`current_contract_t_key`),
  KEY `FKabtaoqs5qw4uufkweb3pl4qmu` (`facility_date_created`,`facility_item_type`,`facility_t_key`),
  CONSTRAINT `FK71o8bygnrk2u12nb38q1yaem3` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKabtaoqs5qw4uufkweb3pl4qmu` FOREIGN KEY (`facility_date_created`, `facility_item_type`, `facility_t_key`) REFERENCES `mbfacility` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKt4f16rwkxq7sbgpdv2m2av95y` FOREIGN KEY (`current_contract_date_created`, `current_contract_item_type`, `current_contract_t_key`) REFERENCES `mbrent_contract` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbrent_property`
--

LOCK TABLES `mbrent_property` WRITE;
/*!40000 ALTER TABLE `mbrent_property` DISABLE KEYS */;
INSERT INTO `mbrent_property` VALUES ('Bujumbura',0,'B01',500000,30,1679147753005,'mBRentProperty',667,1679150437482,'mBRentContract',710,1679147737864,'mBFacility',87),('Bujumbura',0,'A01',0,30,1679147753042,'mBRentProperty',669,NULL,NULL,NULL,1679147737864,'mBFacility',87),('Muyinga',0,'B04',240000,30,1679147753067,'mBRentProperty',671,1679150375137,'mBRentContract',707,1679147737864,'mBFacility',87),('Muyinga',0,'B07',240000,30,1679147753095,'mBRentProperty',673,1679149781560,'mBRentContract',701,1679147737864,'mBFacility',87),('Muyinga',0,'A02',140000,30,1679147753123,'mBRentProperty',675,1679149928293,'mBRentContract',704,1679147737864,'mBFacility',87),('Muyinga',0,'B02',240000,30,1679149581844,'mBRentProperty',699,1679150478822,'mBRentContract',713,1679147737864,'mBFacility',87);
/*!40000 ALTER TABLE `mbrent_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `property_key` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK8m6s7bmncxpq59xf617694a1q` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_values`
--

DROP TABLE IF EXISTS `message_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_values` (
  `message_date_created` bigint NOT NULL DEFAULT '0',
  `message_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `message_t_key` bigint NOT NULL DEFAULT '0',
  `values_date_created` bigint NOT NULL DEFAULT '0',
  `values_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `values_t_key` bigint NOT NULL DEFAULT '0',
  UNIQUE KEY `UK_nfc07tp87h8va7gudde4mf0ua` (`values_date_created`,`values_item_type`,`values_t_key`),
  KEY `FKfa0tef9tve2br1lotvc6pq75a` (`message_date_created`,`message_item_type`,`message_t_key`),
  CONSTRAINT `FKfa0tef9tve2br1lotvc6pq75a` FOREIGN KEY (`message_date_created`, `message_item_type`, `message_t_key`) REFERENCES `message` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKmu83wn4df5r2ayxjj7mbqbjem` FOREIGN KEY (`values_date_created`, `values_item_type`, `values_t_key`) REFERENCES `language_value` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_values`
--

LOCK TABLES `message_values` WRITE;
/*!40000 ALTER TABLE `message_values` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modification_log`
--

DROP TABLE IF EXISTS `modification_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modification_log` (
  `modified_item` varchar(255) DEFAULT NULL,
  `previous_value_code` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `user_date_created` bigint DEFAULT '0',
  `user_item_type` varchar(255) DEFAULT 'item',
  `user_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FKkcdmr472x3n4kap1v3wov0gk4` (`user_date_created`,`user_item_type`,`user_t_key`),
  CONSTRAINT `FK951k3rgsns9eedc24p5bxfnwp` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKkcdmr472x3n4kap1v3wov0gk4` FOREIGN KEY (`user_date_created`, `user_item_type`, `user_t_key`) REFERENCES `principal` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modification_log`
--

LOCK TABLES `modification_log` WRITE;
/*!40000 ALTER TABLE `modification_log` DISABLE KEYS */;
INSERT INTO `modification_log` VALUES ('mBCategory',NULL,1679147735700,'modificationLog',2,NULL,NULL,NULL),('mBCategory',NULL,1679147735919,'modificationLog',4,NULL,NULL,NULL),('mBCategory',NULL,1679147735975,'modificationLog',6,NULL,NULL,NULL),('mBCategory',NULL,1679147736016,'modificationLog',8,NULL,NULL,NULL),('mBCategory',NULL,1679147736053,'modificationLog',10,NULL,NULL,NULL),('mBCategory',NULL,1679147736088,'modificationLog',12,NULL,NULL,NULL),('mBCategory',NULL,1679147736125,'modificationLog',14,NULL,NULL,NULL),('mBCategory',NULL,1679147736284,'modificationLog',16,NULL,NULL,NULL),('mBCategory',NULL,1679147736322,'modificationLog',18,NULL,NULL,NULL),('mBCategory',NULL,1679147736362,'modificationLog',20,NULL,NULL,NULL),('mBCategory',NULL,1679147736399,'modificationLog',22,NULL,NULL,NULL),('mBCategory',NULL,1679147736433,'modificationLog',24,NULL,NULL,NULL),('mBCategory',NULL,1679147736531,'modificationLog',26,NULL,NULL,NULL),('mBCategory',NULL,1679147736570,'modificationLog',28,NULL,NULL,NULL),('mBCategory',NULL,1679147736606,'modificationLog',30,NULL,NULL,NULL),('mBCategory',NULL,1679147736647,'modificationLog',32,NULL,NULL,NULL),('mBCategory',NULL,1679147736686,'modificationLog',34,NULL,NULL,NULL),('mBCategory',NULL,1679147736716,'modificationLog',36,NULL,NULL,NULL),('mBCategory',NULL,1679147736752,'modificationLog',38,NULL,NULL,NULL),('mBCategory',NULL,1679147736785,'modificationLog',40,NULL,NULL,NULL),('mBCategory',NULL,1679147736817,'modificationLog',42,NULL,NULL,NULL),('mBClient',NULL,1679147736936,'modificationLog',44,NULL,NULL,NULL),('mBClient',NULL,1679147736991,'modificationLog',46,NULL,NULL,NULL),('mBClient',NULL,1679147737027,'modificationLog',48,NULL,NULL,NULL),('mBClient',NULL,1679147737063,'modificationLog',50,NULL,NULL,NULL),('mBClient',NULL,1679147737100,'modificationLog',52,NULL,NULL,NULL),('mBClient',NULL,1679147737136,'modificationLog',54,NULL,NULL,NULL),('mBClient',NULL,1679147737166,'modificationLog',56,NULL,NULL,NULL),('mBClient',NULL,1679147737199,'modificationLog',58,NULL,NULL,NULL),('mBClient',NULL,1679147737239,'modificationLog',60,NULL,NULL,NULL),('mBClient',NULL,1679147737275,'modificationLog',62,NULL,NULL,NULL),('mBClient',NULL,1679147737311,'modificationLog',64,NULL,NULL,NULL),('mBClient',NULL,1679147737346,'modificationLog',66,NULL,NULL,NULL),('mBClient',NULL,1679147737376,'modificationLog',68,NULL,NULL,NULL),('mBClient',NULL,1679147737412,'modificationLog',70,NULL,NULL,NULL),('mBClient',NULL,1679147737447,'modificationLog',72,NULL,NULL,NULL),('mBClient',NULL,1679147737484,'modificationLog',74,NULL,NULL,NULL),('mBClient',NULL,1679147737517,'modificationLog',76,NULL,NULL,NULL),('mBClient',NULL,1679147737552,'modificationLog',78,NULL,NULL,NULL),('mBClient',NULL,1679147737582,'modificationLog',80,NULL,NULL,NULL),('mBClient',NULL,1679147737614,'modificationLog',82,NULL,NULL,NULL),('mBClient',NULL,1679147737642,'modificationLog',84,NULL,NULL,NULL),('mBClient',NULL,1679147737670,'modificationLog',86,NULL,NULL,NULL),('mBFacility',NULL,1679147737869,'modificationLog',88,NULL,NULL,NULL),('mBCapital',NULL,1679147737893,'modificationLog',90,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679147737926,'modificationLog',92,NULL,NULL,NULL),('mBFacility',NULL,1679147737989,'modificationLog',94,NULL,NULL,NULL),('mBCapital',NULL,1679147738003,'modificationLog',96,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679147738020,'modificationLog',98,NULL,NULL,NULL),('mBFacility',NULL,1679147738081,'modificationLog',100,NULL,NULL,NULL),('mBCapital',NULL,1679147738098,'modificationLog',102,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679147738113,'modificationLog',104,NULL,NULL,NULL),('mBInventory',NULL,1679147738181,'modificationLog',106,NULL,NULL,NULL),('mBInventory',NULL,1679147738237,'modificationLog',108,NULL,NULL,NULL),('mBInventory',NULL,1679147738266,'modificationLog',110,NULL,NULL,NULL),('mBInventory',NULL,1679147738299,'modificationLog',112,NULL,NULL,NULL),('mBInventory',NULL,1679147738334,'modificationLog',114,NULL,NULL,NULL),('mBInventory',NULL,1679147738368,'modificationLog',116,NULL,NULL,NULL),('mBInventory',NULL,1679147738399,'modificationLog',118,NULL,NULL,NULL),('mBInventory',NULL,1679147738428,'modificationLog',120,NULL,NULL,NULL),('mBInventory',NULL,1679147738455,'modificationLog',122,NULL,NULL,NULL),('mBInventory',NULL,1679147738513,'modificationLog',124,NULL,NULL,NULL),('mBInventory',NULL,1679147738544,'modificationLog',126,NULL,NULL,NULL),('mBInventory',NULL,1679147738577,'modificationLog',128,NULL,NULL,NULL),('mBInventory',NULL,1679147738611,'modificationLog',130,NULL,NULL,NULL),('mBInventory',NULL,1679147738641,'modificationLog',132,NULL,NULL,NULL),('mBInventory',NULL,1679147738677,'modificationLog',134,NULL,NULL,NULL),('mBInventory',NULL,1679147738713,'modificationLog',136,NULL,NULL,NULL),('mBInventory',NULL,1679147738742,'modificationLog',138,NULL,NULL,NULL),('mBInventory',NULL,1679147738768,'modificationLog',140,NULL,NULL,NULL),('mBInventory',NULL,1679147738803,'modificationLog',142,NULL,NULL,NULL),('mBInventory',NULL,1679147738833,'modificationLog',144,NULL,NULL,NULL),('mBInventory',NULL,1679147738864,'modificationLog',146,NULL,NULL,NULL),('mBInventory',NULL,1679147738896,'modificationLog',148,NULL,NULL,NULL),('mBInventory',NULL,1679147738929,'modificationLog',150,NULL,NULL,NULL),('mBInventory',NULL,1679147738964,'modificationLog',152,NULL,NULL,NULL),('mBInventory',NULL,1679147739001,'modificationLog',154,NULL,NULL,NULL),('mBInventory',NULL,1679147739036,'modificationLog',156,NULL,NULL,NULL),('mBInventory',NULL,1679147739070,'modificationLog',158,NULL,NULL,NULL),('mBInventory',NULL,1679147739105,'modificationLog',160,NULL,NULL,NULL),('mBInventory',NULL,1679147739142,'modificationLog',162,NULL,NULL,NULL),('mBInventory',NULL,1679147739173,'modificationLog',164,NULL,NULL,NULL),('mBInventory',NULL,1679147739201,'modificationLog',166,NULL,NULL,NULL),('mBInventory',NULL,1679147739230,'modificationLog',168,NULL,NULL,NULL),('mBInventory',NULL,1679147739261,'modificationLog',170,NULL,NULL,NULL),('mBInventory',NULL,1679147739287,'modificationLog',172,NULL,NULL,NULL),('mBInventory',NULL,1679147739318,'modificationLog',174,NULL,NULL,NULL),('mBInventory',NULL,1679147739346,'modificationLog',176,NULL,NULL,NULL),('mBInventory',NULL,1679147739377,'modificationLog',178,NULL,NULL,NULL),('mBInventory',NULL,1679147739401,'modificationLog',180,NULL,NULL,NULL),('mBInventory',NULL,1679147739429,'modificationLog',182,NULL,NULL,NULL),('mBInventory',NULL,1679147739460,'modificationLog',184,NULL,NULL,NULL),('mBInventory',NULL,1679147739493,'modificationLog',186,NULL,NULL,NULL),('mBInventory',NULL,1679147739529,'modificationLog',188,NULL,NULL,NULL),('mBInventory',NULL,1679147739564,'modificationLog',190,NULL,NULL,NULL),('mBInventory',NULL,1679147739597,'modificationLog',192,NULL,NULL,NULL),('mBInventory',NULL,1679147739635,'modificationLog',194,NULL,NULL,NULL),('mBInventory',NULL,1679147739666,'modificationLog',196,NULL,NULL,NULL),('mBInventory',NULL,1679147739700,'modificationLog',198,NULL,NULL,NULL),('mBInventory',NULL,1679147739728,'modificationLog',200,NULL,NULL,NULL),('mBInventory',NULL,1679147739751,'modificationLog',202,NULL,NULL,NULL),('mBInventory',NULL,1679147739776,'modificationLog',204,NULL,NULL,NULL),('mBInventory',NULL,1679147739806,'modificationLog',206,NULL,NULL,NULL),('mBInventory',NULL,1679147739831,'modificationLog',208,NULL,NULL,NULL),('mBInventory',NULL,1679147739855,'modificationLog',210,NULL,NULL,NULL),('mBInventory',NULL,1679147739883,'modificationLog',212,NULL,NULL,NULL),('mBInventory',NULL,1679147739913,'modificationLog',214,NULL,NULL,NULL),('mBInventory',NULL,1679147739945,'modificationLog',216,NULL,NULL,NULL),('mBInventory',NULL,1679147739975,'modificationLog',218,NULL,NULL,NULL),('mBInventory',NULL,1679147739999,'modificationLog',220,NULL,NULL,NULL),('mBInventory',NULL,1679147740024,'modificationLog',222,NULL,NULL,NULL),('mBInventory',NULL,1679147740049,'modificationLog',224,NULL,NULL,NULL),('mBInventory',NULL,1679147740077,'modificationLog',226,NULL,NULL,NULL),('mBInventory',NULL,1679147740109,'modificationLog',228,NULL,NULL,NULL),('mBInventory',NULL,1679147740142,'modificationLog',230,NULL,NULL,NULL),('mBInventory',NULL,1679147740169,'modificationLog',232,NULL,NULL,NULL),('mBInventory',NULL,1679147740199,'modificationLog',234,NULL,NULL,NULL),('mBInventory',NULL,1679147740224,'modificationLog',236,NULL,NULL,NULL),('mBInventory',NULL,1679147741253,'modificationLog',238,NULL,NULL,NULL),('mBInventory',NULL,1679147741299,'modificationLog',240,NULL,NULL,NULL),('mBInventory',NULL,1679147741333,'modificationLog',242,NULL,NULL,NULL),('mBInventory',NULL,1679147741380,'modificationLog',244,NULL,NULL,NULL),('mBInventory',NULL,1679147741431,'modificationLog',246,NULL,NULL,NULL),('mBInventory',NULL,1679147741477,'modificationLog',248,NULL,NULL,NULL),('mBInventory',NULL,1679147741529,'modificationLog',250,NULL,NULL,NULL),('mBInventory',NULL,1679147741570,'modificationLog',252,NULL,NULL,NULL),('mBInventory',NULL,1679147741615,'modificationLog',254,NULL,NULL,NULL),('mBInventory',NULL,1679147741658,'modificationLog',256,NULL,NULL,NULL),('mBInventory',NULL,1679147741731,'modificationLog',258,NULL,NULL,NULL),('mBInventory',NULL,1679147741774,'modificationLog',260,NULL,NULL,NULL),('mBInventory',NULL,1679147741817,'modificationLog',262,NULL,NULL,NULL),('mBInventory',NULL,1679147741858,'modificationLog',264,NULL,NULL,NULL),('mBInventory',NULL,1679147741897,'modificationLog',266,NULL,NULL,NULL),('mBInventory',NULL,1679147741941,'modificationLog',268,NULL,NULL,NULL),('mBInventory',NULL,1679147741993,'modificationLog',270,NULL,NULL,NULL),('mBInventory',NULL,1679147742023,'modificationLog',272,NULL,NULL,NULL),('mBInventory',NULL,1679147742055,'modificationLog',274,NULL,NULL,NULL),('mBInventory',NULL,1679147742080,'modificationLog',276,NULL,NULL,NULL),('mBInventory',NULL,1679147742112,'modificationLog',278,NULL,NULL,NULL),('mBInventory',NULL,1679147742136,'modificationLog',280,NULL,NULL,NULL),('mBInventory',NULL,1679147742158,'modificationLog',282,NULL,NULL,NULL),('mBInventory',NULL,1679147742201,'modificationLog',284,NULL,NULL,NULL),('mBInventory',NULL,1679147742251,'modificationLog',286,NULL,NULL,NULL),('mBInventory',NULL,1679147742306,'modificationLog',288,NULL,NULL,NULL),('mBInventory',NULL,1679147742369,'modificationLog',290,NULL,NULL,NULL),('mBInventory',NULL,1679147742415,'modificationLog',292,NULL,NULL,NULL),('mBInventory',NULL,1679147742445,'modificationLog',294,NULL,NULL,NULL),('mBInventory',NULL,1679147742494,'modificationLog',296,NULL,NULL,NULL),('mBInventory',NULL,1679147742544,'modificationLog',298,NULL,NULL,NULL),('mBInventory',NULL,1679147742594,'modificationLog',300,NULL,NULL,NULL),('mBInventory',NULL,1679147742648,'modificationLog',302,NULL,NULL,NULL),('mBInventory',NULL,1679147742707,'modificationLog',304,NULL,NULL,NULL),('mBInventory',NULL,1679147742757,'modificationLog',306,NULL,NULL,NULL),('mBInventory',NULL,1679147742810,'modificationLog',308,NULL,NULL,NULL),('mBInventory',NULL,1679147742860,'modificationLog',310,NULL,NULL,NULL),('mBInventory',NULL,1679147742932,'modificationLog',312,NULL,NULL,NULL),('mBInventory',NULL,1679147742981,'modificationLog',314,NULL,NULL,NULL),('mBInventory',NULL,1679147743655,'modificationLog',316,NULL,NULL,NULL),('mBInventory',NULL,1679147743683,'modificationLog',318,NULL,NULL,NULL),('mBInventory',NULL,1679147743710,'modificationLog',320,NULL,NULL,NULL),('mBInventory',NULL,1679147743737,'modificationLog',322,NULL,NULL,NULL),('mBInventory',NULL,1679147743765,'modificationLog',324,NULL,NULL,NULL),('mBInventory',NULL,1679147743798,'modificationLog',326,NULL,NULL,NULL),('mBInventory',NULL,1679147743835,'modificationLog',328,NULL,NULL,NULL),('mBInventory',NULL,1679147743865,'modificationLog',330,NULL,NULL,NULL),('mBInventory',NULL,1679147743898,'modificationLog',332,NULL,NULL,NULL),('mBInventory',NULL,1679147743930,'modificationLog',334,NULL,NULL,NULL),('mBInventory',NULL,1679147743962,'modificationLog',336,NULL,NULL,NULL),('mBInventory',NULL,1679147743996,'modificationLog',338,NULL,NULL,NULL),('mBInventory',NULL,1679147744028,'modificationLog',340,NULL,NULL,NULL),('mBInventory',NULL,1679147744057,'modificationLog',342,NULL,NULL,NULL),('mBInventory',NULL,1679147744092,'modificationLog',344,NULL,NULL,NULL),('mBInventory',NULL,1679147744122,'modificationLog',346,NULL,NULL,NULL),('mBInventory',NULL,1679147744156,'modificationLog',348,NULL,NULL,NULL),('mBInventory',NULL,1679147744187,'modificationLog',350,NULL,NULL,NULL),('mBInventory',NULL,1679147744217,'modificationLog',352,NULL,NULL,NULL),('mBInventory',NULL,1679147744249,'modificationLog',354,NULL,NULL,NULL),('mBInventory',NULL,1679147744287,'modificationLog',356,NULL,NULL,NULL),('mBInventory',NULL,1679147744317,'modificationLog',358,NULL,NULL,NULL),('mBInventory',NULL,1679147744346,'modificationLog',360,NULL,NULL,NULL),('mBInventory',NULL,1679147744379,'modificationLog',362,NULL,NULL,NULL),('mBInventory',NULL,1679147744424,'modificationLog',364,NULL,NULL,NULL),('mBInventory',NULL,1679147744453,'modificationLog',366,NULL,NULL,NULL),('mBInventory',NULL,1679147744517,'modificationLog',368,NULL,NULL,NULL),('mBInventory',NULL,1679147744547,'modificationLog',370,NULL,NULL,NULL),('mBInventory',NULL,1679147744578,'modificationLog',372,NULL,NULL,NULL),('mBInventory',NULL,1679147744607,'modificationLog',374,NULL,NULL,NULL),('mBInventory',NULL,1679147745079,'modificationLog',376,NULL,NULL,NULL),('mBInventory',NULL,1679147745112,'modificationLog',378,NULL,NULL,NULL),('mBInventory',NULL,1679147745147,'modificationLog',380,NULL,NULL,NULL),('mBInventory',NULL,1679147745182,'modificationLog',382,NULL,NULL,NULL),('mBInventory',NULL,1679147745214,'modificationLog',384,NULL,NULL,NULL),('mBInventory',NULL,1679147745256,'modificationLog',386,NULL,NULL,NULL),('mBInventory',NULL,1679147745286,'modificationLog',388,NULL,NULL,NULL),('mBInventory',NULL,1679147745318,'modificationLog',390,NULL,NULL,NULL),('mBInventory',NULL,1679147745352,'modificationLog',392,NULL,NULL,NULL),('mBInventory',NULL,1679147745403,'modificationLog',394,NULL,NULL,NULL),('mBInventory',NULL,1679147745436,'modificationLog',396,NULL,NULL,NULL),('mBInventory',NULL,1679147745465,'modificationLog',398,NULL,NULL,NULL),('mBInventory',NULL,1679147745495,'modificationLog',400,NULL,NULL,NULL),('mBInventory',NULL,1679147745524,'modificationLog',402,NULL,NULL,NULL),('mBInventory',NULL,1679147745555,'modificationLog',404,NULL,NULL,NULL),('mBInventory',NULL,1679147745583,'modificationLog',406,NULL,NULL,NULL),('mBInventory',NULL,1679147745607,'modificationLog',408,NULL,NULL,NULL),('mBInventory',NULL,1679147745651,'modificationLog',410,NULL,NULL,NULL),('mBInventory',NULL,1679147745680,'modificationLog',412,NULL,NULL,NULL),('mBInventory',NULL,1679147745725,'modificationLog',414,NULL,NULL,NULL),('mBInventory',NULL,1679147745753,'modificationLog',416,NULL,NULL,NULL),('mBInventory',NULL,1679147745781,'modificationLog',418,NULL,NULL,NULL),('mBInventory',NULL,1679147745810,'modificationLog',420,NULL,NULL,NULL),('mBInventory',NULL,1679147745843,'modificationLog',422,NULL,NULL,NULL),('mBInventory',NULL,1679147745867,'modificationLog',424,NULL,NULL,NULL),('mBInventory',NULL,1679147745892,'modificationLog',426,NULL,NULL,NULL),('mBInventory',NULL,1679147745924,'modificationLog',428,NULL,NULL,NULL),('mBInventory',NULL,1679147745949,'modificationLog',430,NULL,NULL,NULL),('mBInventory',NULL,1679147745973,'modificationLog',432,NULL,NULL,NULL),('mBInventory',NULL,1679147745998,'modificationLog',434,NULL,NULL,NULL),('mBInventory',NULL,1679147746034,'modificationLog',436,NULL,NULL,NULL),('mBInventory',NULL,1679147746062,'modificationLog',438,NULL,NULL,NULL),('mBInventory',NULL,1679147746085,'modificationLog',440,NULL,NULL,NULL),('mBInventory',NULL,1679147746818,'modificationLog',442,NULL,NULL,NULL),('mBInventory',NULL,1679147746863,'modificationLog',444,NULL,NULL,NULL),('mBInventory',NULL,1679147746903,'modificationLog',446,NULL,NULL,NULL),('mBInventory',NULL,1679147746943,'modificationLog',448,NULL,NULL,NULL),('mBInventory',NULL,1679147746998,'modificationLog',450,NULL,NULL,NULL),('mBInventory',NULL,1679147747039,'modificationLog',452,NULL,NULL,NULL),('mBInventory',NULL,1679147747079,'modificationLog',454,NULL,NULL,NULL),('mBInventory',NULL,1679147747120,'modificationLog',456,NULL,NULL,NULL),('mBInventory',NULL,1679147747161,'modificationLog',458,NULL,NULL,NULL),('mBInventory',NULL,1679147747202,'modificationLog',460,NULL,NULL,NULL),('mBInventory',NULL,1679147747242,'modificationLog',462,NULL,NULL,NULL),('mBInventory',NULL,1679147747288,'modificationLog',464,NULL,NULL,NULL),('mBInventory',NULL,1679147747331,'modificationLog',466,NULL,NULL,NULL),('mBInventory',NULL,1679147747372,'modificationLog',468,NULL,NULL,NULL),('mBInventory',NULL,1679147747411,'modificationLog',470,NULL,NULL,NULL),('mBInventory',NULL,1679147747468,'modificationLog',472,NULL,NULL,NULL),('mBInventory',NULL,1679147747497,'modificationLog',474,NULL,NULL,NULL),('mBInventory',NULL,1679147747523,'modificationLog',476,NULL,NULL,NULL),('mBInventory',NULL,1679147747556,'modificationLog',478,NULL,NULL,NULL),('mBInventory',NULL,1679147747581,'modificationLog',480,NULL,NULL,NULL),('mBInventory',NULL,1679147747607,'modificationLog',482,NULL,NULL,NULL),('mBInventory',NULL,1679147747633,'modificationLog',484,NULL,NULL,NULL),('mBInventory',NULL,1679147747664,'modificationLog',486,NULL,NULL,NULL),('mBInventory',NULL,1679147747692,'modificationLog',488,NULL,NULL,NULL),('mBInventory',NULL,1679147748139,'modificationLog',490,NULL,NULL,NULL),('mBInventory',NULL,1679147748175,'modificationLog',492,NULL,NULL,NULL),('mBInventory',NULL,1679147748201,'modificationLog',494,NULL,NULL,NULL),('mBInventory',NULL,1679147748229,'modificationLog',496,NULL,NULL,NULL),('mBInventory',NULL,1679147748277,'modificationLog',498,NULL,NULL,NULL),('mBInventory',NULL,1679147748307,'modificationLog',500,NULL,NULL,NULL),('mBInventory',NULL,1679147748342,'modificationLog',502,NULL,NULL,NULL),('mBInventory',NULL,1679147748373,'modificationLog',504,NULL,NULL,NULL),('mBInventory',NULL,1679147748406,'modificationLog',506,NULL,NULL,NULL),('mBInventory',NULL,1679147748438,'modificationLog',508,NULL,NULL,NULL),('mBInventory',NULL,1679147748469,'modificationLog',510,NULL,NULL,NULL),('mBInventory',NULL,1679147748499,'modificationLog',512,NULL,NULL,NULL),('mBInventory',NULL,1679147748535,'modificationLog',514,NULL,NULL,NULL),('mBInventory',NULL,1679147748571,'modificationLog',516,NULL,NULL,NULL),('mBInventory',NULL,1679147748601,'modificationLog',518,NULL,NULL,NULL),('mBInventory',NULL,1679147748630,'modificationLog',520,NULL,NULL,NULL),('mBInventory',NULL,1679147748663,'modificationLog',522,NULL,NULL,NULL),('mBInventory',NULL,1679147748694,'modificationLog',524,NULL,NULL,NULL),('mBInventory',NULL,1679147748726,'modificationLog',526,NULL,NULL,NULL),('mBInventory',NULL,1679147748759,'modificationLog',528,NULL,NULL,NULL),('mBInventory',NULL,1679147748791,'modificationLog',530,NULL,NULL,NULL),('mBInventory',NULL,1679147748843,'modificationLog',532,NULL,NULL,NULL),('mBInventory',NULL,1679147748875,'modificationLog',534,NULL,NULL,NULL),('mBInventory',NULL,1679147748904,'modificationLog',536,NULL,NULL,NULL),('mBInventory',NULL,1679147748935,'modificationLog',538,NULL,NULL,NULL),('mBInventory',NULL,1679147748967,'modificationLog',540,NULL,NULL,NULL),('mBInventory',NULL,1679147748996,'modificationLog',542,NULL,NULL,NULL),('mBInventory',NULL,1679147749027,'modificationLog',544,NULL,NULL,NULL),('mBInventory',NULL,1679147749057,'modificationLog',546,NULL,NULL,NULL),('mBInventory',NULL,1679147749090,'modificationLog',548,NULL,NULL,NULL),('mBInventory',NULL,1679147749122,'modificationLog',550,NULL,NULL,NULL),('mBInventory',NULL,1679147749152,'modificationLog',552,NULL,NULL,NULL),('mBInventory',NULL,1679147749200,'modificationLog',554,NULL,NULL,NULL),('mBInventory',NULL,1679147749233,'modificationLog',556,NULL,NULL,NULL),('mBInventory',NULL,1679147749261,'modificationLog',558,NULL,NULL,NULL),('mBInventory',NULL,1679147749310,'modificationLog',560,NULL,NULL,NULL),('mBInventory',NULL,1679147749349,'modificationLog',562,NULL,NULL,NULL),('mBInventory',NULL,1679147749379,'modificationLog',564,NULL,NULL,NULL),('mBInventory',NULL,1679147749411,'modificationLog',566,NULL,NULL,NULL),('mBInventory',NULL,1679147749442,'modificationLog',568,NULL,NULL,NULL),('mBInventory',NULL,1679147749472,'modificationLog',570,NULL,NULL,NULL),('mBInventory',NULL,1679147749502,'modificationLog',572,NULL,NULL,NULL),('mBInventory',NULL,1679147750341,'modificationLog',574,NULL,NULL,NULL),('mBInventory',NULL,1679147750384,'modificationLog',576,NULL,NULL,NULL),('mBInventory',NULL,1679147750417,'modificationLog',578,NULL,NULL,NULL),('mBInventory',NULL,1679147750541,'modificationLog',580,NULL,NULL,NULL),('mBInventory',NULL,1679147750585,'modificationLog',582,NULL,NULL,NULL),('mBInventory',NULL,1679147750633,'modificationLog',584,NULL,NULL,NULL),('mBInventory',NULL,1679147750793,'modificationLog',586,NULL,NULL,NULL),('mBInventory',NULL,1679147750827,'modificationLog',588,NULL,NULL,NULL),('mBInventory',NULL,1679147750906,'modificationLog',590,NULL,NULL,NULL),('mBInventory',NULL,1679147750932,'modificationLog',592,NULL,NULL,NULL),('mBInventory',NULL,1679147751003,'modificationLog',594,NULL,NULL,NULL),('mBInventory',NULL,1679147751028,'modificationLog',596,NULL,NULL,NULL),('mBInventory',NULL,1679147751059,'modificationLog',598,NULL,NULL,NULL),('mBInventory',NULL,1679147751086,'modificationLog',600,NULL,NULL,NULL),('mBInventory',NULL,1679147751116,'modificationLog',602,NULL,NULL,NULL),('mBInventory',NULL,1679147751276,'modificationLog',604,NULL,NULL,NULL),('mBInventory',NULL,1679147751321,'modificationLog',606,NULL,NULL,NULL),('mBInventory',NULL,1679147751366,'modificationLog',608,NULL,NULL,NULL),('mBInventory',NULL,1679147751411,'modificationLog',610,NULL,NULL,NULL),('mBInventory',NULL,1679147751601,'modificationLog',612,NULL,NULL,NULL),('mBInventory',NULL,1679147751636,'modificationLog',614,NULL,NULL,NULL),('mBInventory',NULL,1679147751702,'modificationLog',616,NULL,NULL,NULL),('mBInventory',NULL,1679147751729,'modificationLog',618,NULL,NULL,NULL),('mBInventory',NULL,1679147751763,'modificationLog',620,NULL,NULL,NULL),('mBInventory',NULL,1679147751792,'modificationLog',622,NULL,NULL,NULL),('mBInventory',NULL,1679147751825,'modificationLog',624,NULL,NULL,NULL),('mBInventory',NULL,1679147751860,'modificationLog',626,NULL,NULL,NULL),('mBInventory',NULL,1679147751892,'modificationLog',628,NULL,NULL,NULL),('mBInventory',NULL,1679147751920,'modificationLog',630,NULL,NULL,NULL),('mBInventory',NULL,1679147752094,'modificationLog',632,NULL,NULL,NULL),('mBInventory',NULL,1679147752118,'modificationLog',634,NULL,NULL,NULL),('mBInventory',NULL,1679147752151,'modificationLog',636,NULL,NULL,NULL),('mBInventory',NULL,1679147752181,'modificationLog',638,NULL,NULL,NULL),('mBInventory',NULL,1679147752211,'modificationLog',640,NULL,NULL,NULL),('mBInventory',NULL,1679147752321,'modificationLog',642,NULL,NULL,NULL),('mBInventory',NULL,1679147752349,'modificationLog',644,NULL,NULL,NULL),('mBInventory',NULL,1679147752422,'modificationLog',646,NULL,NULL,NULL),('mBInventory',NULL,1679147752468,'modificationLog',648,NULL,NULL,NULL),('mBInventory',NULL,1679147752519,'modificationLog',650,NULL,NULL,NULL),('mBInventory',NULL,1679147752563,'modificationLog',652,NULL,NULL,NULL),('mBInventory',NULL,1679147752610,'modificationLog',654,NULL,NULL,NULL),('mBInventory',NULL,1679147752661,'modificationLog',656,NULL,NULL,NULL),('mBInventory',NULL,1679147752685,'modificationLog',658,NULL,NULL,NULL),('mBInventory',NULL,1679147752716,'modificationLog',660,NULL,NULL,NULL),('mBInventory',NULL,1679147752741,'modificationLog',662,NULL,NULL,NULL),('mBInventory',NULL,1679147752916,'modificationLog',664,NULL,NULL,NULL),('mBInventory',NULL,1679147752958,'modificationLog',666,NULL,NULL,NULL),('mBRentProperty',NULL,1679147753010,'modificationLog',668,NULL,NULL,NULL),('mBRentProperty',NULL,1679147753047,'modificationLog',670,NULL,NULL,NULL),('mBRentProperty',NULL,1679147753073,'modificationLog',672,NULL,NULL,NULL),('mBRentProperty',NULL,1679147753100,'modificationLog',674,NULL,NULL,NULL),('mBRentProperty',NULL,1679147753127,'modificationLog',676,NULL,NULL,NULL),('mBClient',NULL,1679148939022,'modificationLog',678,NULL,NULL,NULL),('mBClient',NULL,1679148939053,'modificationLog',680,NULL,NULL,NULL),('mBClient',NULL,1679148939082,'modificationLog',682,NULL,NULL,NULL),('mBClient',NULL,1679148939111,'modificationLog',684,NULL,NULL,NULL),('mBClient',NULL,1679148939138,'modificationLog',686,NULL,NULL,NULL),('mBClient',NULL,1679148939166,'modificationLog',688,NULL,NULL,NULL),('mBClient',NULL,1679148939282,'modificationLog',690,NULL,NULL,NULL),('mBClient',NULL,1679148939306,'modificationLog',692,NULL,NULL,NULL),('mBClient',NULL,1679148939329,'modificationLog',694,NULL,NULL,NULL),('mBClient',NULL,1679148939353,'modificationLog',696,NULL,NULL,NULL),('mBClient',NULL,1679148939375,'modificationLog',698,NULL,NULL,NULL),('mBRentProperty',NULL,1679149581847,'modificationLog',700,NULL,NULL,NULL),('mBRentContract',NULL,1679149781564,'modificationLog',702,NULL,NULL,NULL),('mBRentContract',NULL,1679149928303,'modificationLog',705,NULL,NULL,NULL),('mBRentContract',NULL,1679150375149,'modificationLog',708,NULL,NULL,NULL),('mBRentContract',NULL,1679150437493,'modificationLog',711,NULL,NULL,NULL),('mBRentContract',NULL,1679150478827,'modificationLog',714,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541118,'modificationLog',717,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541212,'modificationLog',720,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541311,'modificationLog',723,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541399,'modificationLog',726,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541466,'modificationLog',729,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541533,'modificationLog',732,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541604,'modificationLog',735,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541662,'modificationLog',738,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541732,'modificationLog',741,NULL,NULL,NULL),('mBRentOrder',NULL,1679150541794,'modificationLog',744,NULL,NULL,NULL),('mBInvoice',NULL,1679150764066,'modificationLog',747,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679150764216,'modificationLog',750,NULL,NULL,NULL),('mBInvoice',NULL,1679150882884,'modificationLog',752,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679150883004,'modificationLog',755,NULL,NULL,NULL),('mBInvoice',NULL,1679151000786,'modificationLog',757,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151000916,'modificationLog',760,NULL,NULL,NULL),('mBInvoice',NULL,1679151042463,'modificationLog',762,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151042572,'modificationLog',765,NULL,NULL,NULL),('mBInvoice',NULL,1679151129824,'modificationLog',767,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151129920,'modificationLog',770,NULL,NULL,NULL),('mBInvoice',NULL,1679151266197,'modificationLog',772,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151266290,'modificationLog',775,NULL,NULL,NULL),('mBInvoice',NULL,1679151361107,'modificationLog',782,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151361216,'modificationLog',785,NULL,NULL,NULL),('mBInvoice',NULL,1679151624495,'modificationLog',792,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151624594,'modificationLog',795,NULL,NULL,NULL),('mBInvoice',NULL,1679151729001,'modificationLog',797,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151729109,'modificationLog',800,NULL,NULL,NULL),('mBInvoice',NULL,1679151838364,'modificationLog',802,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151838440,'modificationLog',805,NULL,NULL,NULL),('mBInvoice',NULL,1679151926061,'modificationLog',807,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679151926195,'modificationLog',810,NULL,NULL,NULL),('mBInvoice',NULL,1679152049800,'modificationLog',812,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152049894,'modificationLog',815,NULL,NULL,NULL),('mBInvoice',NULL,1679152108723,'modificationLog',817,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152108825,'modificationLog',820,NULL,NULL,NULL),('mBInvoice',NULL,1679152151449,'modificationLog',822,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152151544,'modificationLog',825,NULL,NULL,NULL),('mBInvoice',NULL,1679152197361,'modificationLog',827,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152197461,'modificationLog',830,NULL,NULL,NULL),('mBInvoice',NULL,1679152252924,'modificationLog',832,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152253023,'modificationLog',835,NULL,NULL,NULL),('mBInvoice',NULL,1679152292979,'modificationLog',837,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152293040,'modificationLog',840,NULL,NULL,NULL),('mBInvoice',NULL,1679152351977,'modificationLog',842,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152352078,'modificationLog',845,NULL,NULL,NULL),('mBInvoice',NULL,1679152426987,'modificationLog',847,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152427110,'modificationLog',850,NULL,NULL,NULL),('mBInvoice',NULL,1679152544056,'modificationLog',852,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152544172,'modificationLog',855,NULL,NULL,NULL),('mBInvoice',NULL,1679152648853,'modificationLog',862,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152648948,'modificationLog',865,NULL,NULL,NULL),('mBInvoice',NULL,1679152726818,'modificationLog',867,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679152726911,'modificationLog',870,NULL,NULL,NULL),('mBClient',NULL,1679152810471,'modificationLog',872,NULL,NULL,NULL),('mBClient',NULL,1679152881011,'modificationLog',879,NULL,NULL,NULL),('mBClient',NULL,1679152987203,'modificationLog',886,NULL,NULL,NULL),('mBInvoice',NULL,1679153056586,'modificationLog',888,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153056629,'modificationLog',891,NULL,NULL,NULL),('mBInvoice',NULL,1679153112357,'modificationLog',893,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153112466,'modificationLog',896,NULL,NULL,NULL),('mBInvoice',NULL,1679153193654,'modificationLog',898,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153193753,'modificationLog',901,NULL,NULL,NULL),('mBInvoice',NULL,1679153453644,'modificationLog',903,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153453736,'modificationLog',906,NULL,NULL,NULL),('mBInvoice',NULL,1679153570795,'modificationLog',908,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153570883,'modificationLog',911,NULL,NULL,NULL),('mBInvoice',NULL,1679153736580,'modificationLog',913,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153736676,'modificationLog',916,NULL,NULL,NULL),('mBClient',NULL,1679153780625,'modificationLog',918,NULL,NULL,NULL),('mBInvoice',NULL,1679153839200,'modificationLog',920,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153839293,'modificationLog',923,NULL,NULL,NULL),('mBClient',NULL,1679153882800,'modificationLog',925,NULL,NULL,NULL),('mBInvoice',NULL,1679153952162,'modificationLog',927,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679153952252,'modificationLog',930,NULL,NULL,NULL),('mBInvoice',NULL,1679154031885,'modificationLog',932,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154031992,'modificationLog',935,NULL,NULL,NULL),('mBClient',NULL,1679154077332,'modificationLog',937,NULL,NULL,NULL),('mBInvoice',NULL,1679154172703,'modificationLog',939,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154172811,'modificationLog',942,NULL,NULL,NULL),('mBClient',NULL,1679154215352,'modificationLog',944,NULL,NULL,NULL),('mBInvoice',NULL,1679154284770,'modificationLog',946,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154284881,'modificationLog',949,NULL,NULL,NULL),('mBInvoice',NULL,1679154358663,'modificationLog',951,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154358753,'modificationLog',954,NULL,NULL,NULL),('mBInvoice',NULL,1679154435588,'modificationLog',956,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154435730,'modificationLog',959,NULL,NULL,NULL),('mBClient',NULL,1679154513231,'modificationLog',966,NULL,NULL,NULL),('mBInvoice',NULL,1679154585039,'modificationLog',968,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154585131,'modificationLog',971,NULL,NULL,NULL),('mBInvoice',NULL,1679154638330,'modificationLog',973,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154638423,'modificationLog',976,NULL,NULL,NULL),('mBInvoice',NULL,1679154683858,'modificationLog',978,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154683950,'modificationLog',981,NULL,NULL,NULL),('mBClient',NULL,1679154721984,'modificationLog',983,NULL,NULL,NULL),('mBInvoice',NULL,1679154747643,'modificationLog',985,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154747737,'modificationLog',988,NULL,NULL,NULL),('mBInvoice',NULL,1679154793159,'modificationLog',990,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154793260,'modificationLog',993,NULL,NULL,NULL),('mBInvoice',NULL,1679154997888,'modificationLog',995,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679154997912,'modificationLog',998,NULL,NULL,NULL),('mBClient',NULL,1679155047435,'modificationLog',1000,NULL,NULL,NULL),('mBInvoice',NULL,1679155127396,'modificationLog',1002,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679155127433,'modificationLog',1005,NULL,NULL,NULL),('mBClient',NULL,1679155182567,'modificationLog',1007,NULL,NULL,NULL),('mBInvoice',NULL,1679155231152,'modificationLog',1009,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679155231237,'modificationLog',1012,NULL,NULL,NULL),('mBClient',NULL,1679155297699,'modificationLog',1014,NULL,NULL,NULL),('mBInvoice',NULL,1679155325801,'modificationLog',1016,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679155325887,'modificationLog',1019,NULL,NULL,NULL),('mBInvoice',NULL,1679155366443,'modificationLog',1021,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679155366540,'modificationLog',1024,NULL,NULL,NULL),('mBInvoice',NULL,1679155445894,'modificationLog',1026,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679155445980,'modificationLog',1029,NULL,NULL,NULL),('mBClient',NULL,1679155485825,'modificationLog',1031,NULL,NULL,NULL),('mBInvoice',NULL,1679155521357,'modificationLog',1033,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679155521447,'modificationLog',1036,NULL,NULL,NULL),('mBInvoice',NULL,1679157697892,'modificationLog',1038,NULL,NULL,NULL),('mBCapitalEntry',NULL,1679157697926,'modificationLog',1041,NULL,NULL,NULL);
/*!40000 ALTER TABLE `modification_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferred_companies2preferred_room_groups`
--

DROP TABLE IF EXISTS `preferred_companies2preferred_room_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferred_companies2preferred_room_groups` (
  `preferred_companies_date_created` bigint NOT NULL DEFAULT '0',
  `preferred_companies_t_key` varchar(255) NOT NULL DEFAULT 'item',
  `preferred_companies_item_type` bigint NOT NULL DEFAULT '0',
  `preferred_room_groups_date_created` bigint NOT NULL DEFAULT '0',
  `preferred_room_groups_t_key` varchar(255) NOT NULL DEFAULT 'item',
  `preferred_room_groups_item_type` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`preferred_companies_date_created`,`preferred_companies_t_key`,`preferred_companies_item_type`,`preferred_room_groups_date_created`,`preferred_room_groups_t_key`,`preferred_room_groups_item_type`),
  KEY `FK5woghqsd9wd2s5239ubvykjup` (`preferred_room_groups_date_created`,`preferred_room_groups_t_key`,`preferred_room_groups_item_type`),
  CONSTRAINT `FK5woghqsd9wd2s5239ubvykjup` FOREIGN KEY (`preferred_room_groups_date_created`, `preferred_room_groups_t_key`, `preferred_room_groups_item_type`) REFERENCES `room_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKm9fqbgw5ujy746ohmfflxu03p` FOREIGN KEY (`preferred_companies_date_created`, `preferred_companies_t_key`, `preferred_companies_item_type`) REFERENCES `company` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferred_companies2preferred_room_groups`
--

LOCK TABLES `preferred_companies2preferred_room_groups` WRITE;
/*!40000 ALTER TABLE `preferred_companies2preferred_room_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferred_companies2preferred_room_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_group`
--

DROP TABLE IF EXISTS `price_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price_group` (
  `price_value` double DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKtfhmqv6sckedd2csj9eqq4igd` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_group`
--

LOCK TABLES `price_group` WRITE;
/*!40000 ALTER TABLE `price_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `price_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `principal`
--

DROP TABLE IF EXISTS `principal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `principal` (
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK7qtjyyd9t0a0hwkdwtbdk75c` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `principal`
--

LOCK TABLES `principal` WRITE;
/*!40000 ALTER TABLE `principal` DISABLE KEYS */;
/*!40000 ALTER TABLE `principal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `principal2branch_groups`
--

DROP TABLE IF EXISTS `principal2branch_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `principal2branch_groups` (
  `principal_date_created` bigint NOT NULL DEFAULT '0',
  `principal_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `principal_t_key` bigint NOT NULL DEFAULT '0',
  `branch_groups_date_created` bigint NOT NULL DEFAULT '0',
  `branch_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `branch_groups_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`principal_date_created`,`principal_item_type`,`principal_t_key`,`branch_groups_date_created`,`branch_groups_item_type`,`branch_groups_t_key`),
  KEY `FKis49mq52b841n8557od9l0lcx` (`branch_groups_date_created`,`branch_groups_item_type`,`branch_groups_t_key`),
  CONSTRAINT `FK6it46ni6xfn6e9v8yjnpqd2l3` FOREIGN KEY (`principal_date_created`, `principal_item_type`, `principal_t_key`) REFERENCES `principal` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKis49mq52b841n8557od9l0lcx` FOREIGN KEY (`branch_groups_date_created`, `branch_groups_item_type`, `branch_groups_t_key`) REFERENCES `branch_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `principal2branch_groups`
--

LOCK TABLES `principal2branch_groups` WRITE;
/*!40000 ALTER TABLE `principal2branch_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `principal2branch_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `principal_modified`
--

DROP TABLE IF EXISTS `principal_modified`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `principal_modified` (
  `principal_date_created` bigint NOT NULL DEFAULT '0',
  `principal_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `principal_t_key` bigint NOT NULL DEFAULT '0',
  `modified_date_created` bigint NOT NULL DEFAULT '0',
  `modified_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `modified_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`principal_date_created`,`principal_item_type`,`principal_t_key`,`modified_date_created`,`modified_item_type`,`modified_t_key`),
  UNIQUE KEY `UK_sig2rji2kxnekrr2ij2xhwwo5` (`modified_date_created`,`modified_item_type`,`modified_t_key`),
  CONSTRAINT `FK110cywdfuddo2t3yuk2b8h4m0` FOREIGN KEY (`modified_date_created`, `modified_item_type`, `modified_t_key`) REFERENCES `modification_log` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK3twdtv6ijdv8wuvhke04cje8d` FOREIGN KEY (`principal_date_created`, `principal_item_type`, `principal_t_key`) REFERENCES `principal` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `principal_modified`
--

LOCK TABLES `principal_modified` WRITE;
/*!40000 ALTER TABLE `principal_modified` DISABLE KEYS */;
/*!40000 ALTER TABLE `principal_modified` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `check_in` varchar(255) DEFAULT NULL,
  `check_out` varchar(255) DEFAULT NULL,
  `reservation_date` datetime(6) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  `reserved_by_customer_date_created` bigint DEFAULT '0',
  `reserved_by_customer_item_type` varchar(255) DEFAULT 'item',
  `reserved_by_customer_t_key` bigint DEFAULT '0',
  `reserved_by_employee_date_created` bigint DEFAULT '0',
  `reserved_by_employee_item_type` varchar(255) DEFAULT 'item',
  `reserved_by_employee_t_key` bigint DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  KEY `FK7rqx5b29njha2sbjldht78l7f` (`reserved_by_customer_date_created`,`reserved_by_customer_item_type`,`reserved_by_customer_t_key`),
  KEY `FK8j4ioror8toctbo1y2lp4xphc` (`reserved_by_employee_date_created`,`reserved_by_employee_item_type`,`reserved_by_employee_t_key`),
  CONSTRAINT `FK7rqx5b29njha2sbjldht78l7f` FOREIGN KEY (`reserved_by_customer_date_created`, `reserved_by_customer_item_type`, `reserved_by_customer_t_key`) REFERENCES `customer` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK8j4ioror8toctbo1y2lp4xphc` FOREIGN KEY (`reserved_by_employee_date_created`, `reserved_by_employee_item_type`, `reserved_by_employee_t_key`) REFERENCES `employee` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKpdy0lnecxngxeng1yypxaj8wp` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations2groups`
--

DROP TABLE IF EXISTS `reservations2groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations2groups` (
  `reservations_date_created` bigint NOT NULL DEFAULT '0',
  `reservations_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `reservations_t_key` bigint NOT NULL DEFAULT '0',
  `groups_date_created` bigint NOT NULL DEFAULT '0',
  `groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `groups_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`reservations_date_created`,`reservations_item_type`,`reservations_t_key`,`groups_date_created`,`groups_item_type`,`groups_t_key`),
  KEY `FKmiyul0inhig2mihtnas326www` (`groups_date_created`,`groups_item_type`,`groups_t_key`),
  CONSTRAINT `FK4mlivp93ar4ibphg8nykfpgke` FOREIGN KEY (`reservations_date_created`, `reservations_item_type`, `reservations_t_key`) REFERENCES `reservation` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKmiyul0inhig2mihtnas326www` FOREIGN KEY (`groups_date_created`, `groups_item_type`, `groups_t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations2groups`
--

LOCK TABLES `reservations2groups` WRITE;
/*!40000 ALTER TABLE `reservations2groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations2groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_number` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKbt1qq58qoaxvwd8m76ydptou2` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_group`
--

DROP TABLE IF EXISTS `room_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_group` (
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKt0i3jde0bv72koqnlrd1qbu29` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_group`
--

LOCK TABLES `room_group` WRITE;
/*!40000 ALTER TABLE `room_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms2room_groups`
--

DROP TABLE IF EXISTS `rooms2room_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms2room_groups` (
  `rooms_date_created` bigint NOT NULL DEFAULT '0',
  `rooms_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `rooms_t_key` bigint NOT NULL DEFAULT '0',
  `tax_groups_date_created` bigint NOT NULL DEFAULT '0',
  `tax_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `tax_groups_t_key` bigint NOT NULL DEFAULT '0',
  `room_groups_date_created` bigint NOT NULL DEFAULT '0',
  `room_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `room_groups_t_key` bigint NOT NULL DEFAULT '0',
  `price_groups_date_created` bigint NOT NULL DEFAULT '0',
  `price_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `price_groups_t_key` bigint NOT NULL DEFAULT '0',
  `room_date_created` bigint NOT NULL DEFAULT '0',
  `room_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `room_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`rooms_date_created`,`rooms_item_type`,`rooms_t_key`,`price_groups_date_created`,`price_groups_item_type`,`price_groups_t_key`),
  KEY `FK274bnajg4vf4jpsvu88lejk9w` (`tax_groups_date_created`,`tax_groups_item_type`,`tax_groups_t_key`),
  KEY `FK2uid33xjymwfa6h622adhs88` (`room_groups_date_created`,`room_groups_item_type`,`room_groups_t_key`),
  KEY `FKquy5tlws1xn4tjrc40987lbgs` (`price_groups_date_created`,`price_groups_item_type`,`price_groups_t_key`),
  KEY `FK7a52niq2jj5i9exl1sgsjwr23` (`room_date_created`,`room_item_type`,`room_t_key`),
  CONSTRAINT `FK274bnajg4vf4jpsvu88lejk9w` FOREIGN KEY (`tax_groups_date_created`, `tax_groups_item_type`, `tax_groups_t_key`) REFERENCES `tax_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK2uid33xjymwfa6h622adhs88` FOREIGN KEY (`room_groups_date_created`, `room_groups_item_type`, `room_groups_t_key`) REFERENCES `room_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK7a52niq2jj5i9exl1sgsjwr23` FOREIGN KEY (`room_date_created`, `room_item_type`, `room_t_key`) REFERENCES `room_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK8v4p1t18d9op8xpgyosqsbyve` FOREIGN KEY (`rooms_date_created`, `rooms_item_type`, `rooms_t_key`) REFERENCES `room` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FKquy5tlws1xn4tjrc40987lbgs` FOREIGN KEY (`price_groups_date_created`, `price_groups_item_type`, `price_groups_t_key`) REFERENCES `price_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms2room_groups`
--

LOCK TABLES `rooms2room_groups` WRITE;
/*!40000 ALTER TABLE `rooms2room_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms2room_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_group` (
  `group_name` varchar(255) DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FK81ceq21bkcj15qh9guuv5ibx6` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `item` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax_group`
--

DROP TABLE IF EXISTS `tax_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax_group` (
  `tax_priority` int DEFAULT NULL,
  `tax_value` double DEFAULT NULL,
  `date_created` bigint NOT NULL DEFAULT '0',
  `item_type` varchar(255) NOT NULL DEFAULT 'item',
  `t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`date_created`,`item_type`,`t_key`),
  CONSTRAINT `FKsofd53hxfbioayud5jv602dd9` FOREIGN KEY (`date_created`, `item_type`, `t_key`) REFERENCES `t_group` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax_group`
--

LOCK TABLES `tax_group` WRITE;
/*!40000 ALTER TABLE `tax_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxed_companies2tax_groups`
--

DROP TABLE IF EXISTS `taxed_companies2tax_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taxed_companies2tax_groups` (
  `tax_groups_date_created` bigint NOT NULL DEFAULT '0',
  `tax_groups_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `tax_groups_t_key` bigint NOT NULL DEFAULT '0',
  `taxed_companies_date_created` bigint NOT NULL DEFAULT '0',
  `taxed_companies_item_type` varchar(255) NOT NULL DEFAULT 'item',
  `taxed_companies_t_key` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`tax_groups_date_created`,`tax_groups_item_type`,`tax_groups_t_key`,`taxed_companies_date_created`,`taxed_companies_item_type`,`taxed_companies_t_key`),
  KEY `FK236bapngnldtmi9ad3abcaa4y` (`taxed_companies_date_created`,`taxed_companies_item_type`,`taxed_companies_t_key`),
  CONSTRAINT `FK236bapngnldtmi9ad3abcaa4y` FOREIGN KEY (`taxed_companies_date_created`, `taxed_companies_item_type`, `taxed_companies_t_key`) REFERENCES `tax_group` (`date_created`, `item_type`, `t_key`),
  CONSTRAINT `FK4fam8n8gtr0cpwvs3vmpy68hu` FOREIGN KEY (`tax_groups_date_created`, `tax_groups_item_type`, `tax_groups_t_key`) REFERENCES `company` (`date_created`, `item_type`, `t_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxed_companies2tax_groups`
--

LOCK TABLES `taxed_companies2tax_groups` WRITE;
/*!40000 ALTER TABLE `taxed_companies2tax_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `taxed_companies2tax_groups` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-20 21:49:39
