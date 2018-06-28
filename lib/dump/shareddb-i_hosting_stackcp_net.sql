CREATE DATABASE  IF NOT EXISTS `itsonedb-3337937e` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `itsonedb-3337937e`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: itsonedb-3337937e
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `date` date NOT NULL,
  `rating` char(3) NOT NULL,
  `site` varchar(35) NOT NULL,
  `developer` varchar(35) NOT NULL,
  `cover` varchar(255) NOT NULL DEFAULT 'blank.jpg',
  `background` varchar(255) NOT NULL DEFAULT 'blank.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'FORTNITE','The Storm came without warning and wiped out 98 percent of the world\'s population in a flash. Poof. Adios. Sayonara. Then came the monsters, wave after wave, night after night. Destroying everything in their path. But it\'s not all doom and gloom. In an abandoned missile silo, we\'ve found one of our first weapons against the Storm you. We\'re looking for a few good commanders like you to help make a difference, push back the storm and protect those among us who are unable to protect themselves. Explore the world. Rescue survivors. Make hundreds of guns, swords, and things that go boom. Make impregnable forts. Tastefully decorate with sniper perches, poison gas traps, and jump pads. Take back the world. You know, the usual. And be sure to invite your friends. Welcome to Fortnite.','2017-07-21','T','http://fortnitegame.com/','Epic Games','23417548_171924346728877_8405782474705076224_n.jpg','31958922_1924299657647349_503464218285047808_o.jpg'),(2,'PLAYERUNKNOWN\'S BATTLEGROUNDS','PLAYERUNKNOWN\'S BATTLEGROUNDS is a last-man-standing shooter being developed with community feedback. Players must fight to locate weapons and supplies in a massive 8x8 km island to be the lone survivor. This is BATTLE ROYALE.','2017-12-20','T','https://playbattlegrounds.com/','Bluehole, Inc., PUBG Corporation','22812527_442500159480299_8011550141024567296_n.jpg','25550499_1960326894291225_4557629636267055425_n.jpg'),(3,' GRAND THEFT AUTO V','Los Santos: a sprawling sun-soaked metropolis full of self-help gurus, starlets and fading celebrities, once the envy of the Western world, now struggling to stay alive in a time of economic uncertainty and cheap reality TV. Amidst the turmoil, three very unique criminals plot their own chances of survival and success: Franklin, a street hustler looking for tangible opportunities and serious money; Michael, a professional ex-con whose retirement is less rosy than he figured it would be; and Trevor, a violent dude driven by the opportunity for a cheap high and his next big score. With options at a premium, the crew risks it all in a myriad of daring and dangerous heists that could set them up for life.','2015-04-14','M','https://www.rockstargames.com/V/','Rockstar North','22810705_140251603396086_3943215813829328896_n.jpg','29468670_1903895452968638_6743682253071099301_n.png'),(4,'OVERWATCH','Overwatch is a highly stylized team-based shooter set on earth in the near future. Every match is an intense multiplayer showdown pitting a diverse cast of soldiers, mercenaries, scientists, adventurers, and oddities against each other in an epic, globe-spanning conflict.','2016-04-23','T','https://playoverwatch.com/pt-br/','Blizzard Entertainment','21624420_273804319792303_3831570411007508480_n.jpg','34073786_1051355628355578_7186438587901018112_n.png'),(5,' DRAGON BALL FIGHTERZ','After the success of the Xenoverse series, its time to introduce a new classic 2D DRAGON BALL fighting game for this generations consoles. DRAGON BALL FighterZ is born from what makes the DRAGON BALL series so loved and famous: endless spectacular fights with its allpowerful fighters. Partnering with Arc System Works, DRAGON BALL FighterZ maximizes high end Anime graphics and brings easy to learn but difficult to master fighting gameplay to audiences worldwide.','2018-01-26','T','bandainamcoent.com/dragon-fighterz','Arc System Works','26813876_1549324141789788_7053036611629481984_n.jpg','19250855_254138355066170_2616191251556797857_o.jpg'),(6,' HEARTHSTONE','Hearthstone is a free-to-play digital strategy card game that anyone can enjoy. Players choose one of nine epic Warcraft heroes to play as, and then take turns playing cards from their customizable decks to cast potent spells, use heroic weapons or abilities, or summon powerful characters to crush their opponent.','2011-03-11','T','www.heroesofthestorm.com/','Blizzard Entertainment','23803368_145253312864792_1362189362288132096_n.jpg','29133119_1792582214117872_1521522302393516032_n.png'),(7,' STREET FIGHTER V','Birdie and Charlie Nash make their return to the Street Fighter universe, where they join classic characters like Ryu, Chun-Li, Cammy, and M. Bison. Many more new and returning characters will be added to the diverse roster, offering a wide variety of fighting styles for players to choose from. ','2016-02-16','T','https://streetfighter.com/','Capcom','23065593_1166667516799148_625721196805095424_n.jpg','34054277_10156514898282147_4605832617953067008_o.jpg');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `game_list`
--

DROP TABLE IF EXISTS `game_list`;
/*!50001 DROP VIEW IF EXISTS `game_list`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `game_list` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `description`,
 1 AS `date`,
 1 AS `rating`,
 1 AS `site`,
 1 AS `developer`,
 1 AS `cover`,
 1 AS `background`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `game_user`
--

DROP TABLE IF EXISTS `game_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_user` (
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `review` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`game_id`),
  KEY `fk_game_user` (`game_id`),
  CONSTRAINT `fk_game_user` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `fk_user_game` FOREIGN KEY (`user_id`) REFERENCES user_system (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_user`
--

LOCK TABLES `game_user` WRITE;
/*!40000 ALTER TABLE `game_user` DISABLE KEYS */;
INSERT INTO `game_user` VALUES (1,1,6),(1,3,9),(1,4,7),(3,1,NULL),(3,3,10),(3,4,NULL);
/*!40000 ALTER TABLE `game_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER game_user_insert BEFORE INSERT ON game_user
  FOR EACH ROW
  BEGIN
    if exists (select * from game_user_deleted where user_id=NEW.user_id AND game_id=NEW.game_id) THEN
    SET NEW.review = (select review from game_user_deleted where user_id=NEW.user_id AND game_id=NEW.game_id LIMIT 1);
END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER game_user_delete BEFORE DELETE ON game_user
  FOR EACH ROW
  BEGIN
    if exists (select * from game_user_deleted where user_id=OLD.user_id AND game_id=OLD.game_id) THEN
    UPDATE game_user_deleted SET review=OLD.review where user_id=OLD.user_id AND game_id=OLD.game_id;
ELSE 
    insert into game_user_deleted values (OLD.user_id,OLD.game_id,OLD.review);
END IF;
  END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `game_user_deleted`
--

DROP TABLE IF EXISTS `game_user_deleted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_user_deleted` (
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `review` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`game_id`),
  KEY `fk_game_user` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_user_deleted`
--

LOCK TABLES `game_user_deleted` WRITE;
/*!40000 ALTER TABLE `game_user_deleted` DISABLE KEYS */;
INSERT INTO `game_user_deleted` VALUES (1,1,6),(1,3,9);
/*!40000 ALTER TABLE `game_user_deleted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `game_user_list`
--

DROP TABLE IF EXISTS `game_user_list`;
/*!50001 DROP VIEW IF EXISTS `game_user_list`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `game_user_list` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `description`,
 1 AS `date`,
 1 AS `rating`,
 1 AS `site`,
 1 AS `developer`,
 1 AS `cover`,
 1 AS `background`,
 1 AS `user_id`,
 1 AS `game_id`,
 1 AS `review`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO user_system VALUES (1,'admin','admin@admin.com','$2a$10$YxTd2y/uIDXsqZWThKuV5.etklBtqS8W3xa.lBM0rWU/onfBcJKXu'),(3,'pedro','pedro@gmail.com','$2a$10$O.ppmIUCKT5h4n92VPmSHeYL3gEERTdsUm51ratT31U9X1piNsxeC'),(4,'test','test@test.com','$2a$10$Za/KzVm65n.uOyaqtqurp.qq1bPwKlghKmn8pznjxF95BXXQM.fda'),(5,'testar','testar@email.com','$2a$10$I7LJMa.Qa6Emc0DepQTVvO5ytnugsqfi9/yZjhs/V01pw8ajlS4uC');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'itsonedb-3337937e'
--

--
-- Dumping routines for database 'itsonedb-3337937e'
--

--
-- Final view structure for view `game_list`
--

/*!50001 DROP VIEW IF EXISTS `game_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `game_list` AS select `game`.`id` AS `id`,`game`.`name` AS `name`,`game`.`description` AS `description`,`game`.`date` AS `date`,`game`.`rating` AS `rating`,`game`.`site` AS `site`,`game`.`developer` AS `developer`,`game`.`cover` AS `cover`,`game`.`background` AS `background` from `game` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `game_user_list`
--

/*!50001 DROP VIEW IF EXISTS `game_user_list`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `game_user_list` AS select `game`.`id` AS `id`,`game`.`name` AS `name`,`game`.`description` AS `description`,`game`.`date` AS `date`,`game`.`rating` AS `rating`,`game`.`site` AS `site`,`game`.`developer` AS `developer`,`game`.`cover` AS `cover`,`game`.`background` AS `background`,`game_user`.`user_id` AS `user_id`,`game_user`.`game_id` AS `game_id`,`game_user`.`review` AS `review` from (`game` join `game_user` on((`game`.`id` = `game_user`.`game_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-08 15:44:35
