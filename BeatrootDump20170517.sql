-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: beatroot
-- ------------------------------------------------------
-- Server version	5.6.34-log

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `staff_id` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `display_name` varchar(45) NOT NULL,
  `user_description` mediumtext NOT NULL,
  `personal_picture_path` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_address` varchar(45) NOT NULL,
  `physical_address` varchar(45) NOT NULL,
  `city_of_residence` varchar(45) NOT NULL,
  `postal_code` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `start_date` date NOT NULL,
  `wage` float NOT NULL,
  `contract_hours` float NOT NULL,
  `gender_gender_id` int(11) NOT NULL,
  `playlist_link` varchar(20) NOT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `fk_administrator_gender_idx` (`gender_gender_id`),
  KEY `fk_administrator_user_link1_idx` (`playlist_link`),
  CONSTRAINT `fk_administrator_gender` FOREIGN KEY (`gender_gender_id`) REFERENCES `gender` (`gender_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_administrator_user_link1` FOREIGN KEY (`playlist_link`) REFERENCES `user_link` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('beatroot','beatroot','1234','rCoolBeatz','','','Hercule','Poirot','1873-06-21','rCoolBeatz@beatroot.com','56B Whitehaven Mansions','London','EC1A 9PS','United Kingdom','','2017-04-01',500000,40,1,'beatroot');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(45) NOT NULL,
  `album_cover_path` varchar(1024) NOT NULL,
  `year_of_publication` year(4) NOT NULL,
  `administrator_staff_id` varchar(20) NOT NULL,
  PRIMARY KEY (`album_id`),
  KEY `fk_album_administrator1_idx` (`administrator_staff_id`),
  CONSTRAINT `fk_album_administrator1` FOREIGN KEY (`administrator_staff_id`) REFERENCES `administrator` (`staff_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'No Redemption','http://www.webshare.hkr.se/FECO0002/Combichrist%20-%20No%20Redemption.jpg',2013,'beatroot'),(2,'Life','http://www.webshare.hkr.se/FECO0002/Dope%20-%20Life.jpg',2001,'beatroot'),(3,'No Regrets','http://www.webshare.hkr.se/FECO0002/Dope%20-%20No%20Regrets.jpg',2015,'beatroot'),(4,'Halcyon Days','http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Halcyon%20Days.png',2013,'beatroot'),(5,'Misteri','http://www.webshare.hkr.se/FECO0002/Fratello%20Metallo%20-%20Misteri.jpg',2008,'beatroot'),(6,'Goa','http://www.webshare.hkr.se/FECO0002/Goa.jpg',1998,'beatroot'),(7,'American IV: The Man Comes Around','http://www.webshare.hkr.se/FECO0002/Johnny%20Cash.jpg',2002,'beatroot'),(8,'Nashville','http://www.webshare.hkr.se/FECO0002/Keith%20Carradine%20-%20Im%20Easy.jpg',1975,'beatroot'),(9,'Greatest Hitz','http://www.webshare.hkr.se/FECO0002/Limpbizkit%20-%20Greatest%20Hitz.jpg',2005,'beatroot'),(10,'LIVING THINGS','http://www.webshare.hkr.se/FECO0002/Linkin%20Park%20-%20Living%20Things.jpg',2012,'beatroot'),(11,'Buiikikaesu','http://www.webshare.hkr.se/FECO0002/Maximum%20the%20Hormone%20-%20Buiikikaesu.jpg',2007,'beatroot'),(12,'PSY 6? (Six Rules), Part 1','http://www.webshare.hkr.se/FECO0002/PSY%20-%20Gangnam%20Style.jpg',2012,'beatroot'),(13,'Absolute Greatest','http://www.webshare.hkr.se/FECO0002/Queen%20-%20Absolute%20Greatest.jpg',2009,'beatroot'),(14,'Rage Against The Machine','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20the%20Machine%20-%20Rage%20Against%20the%20Machine.jpg',1992,'beatroot'),(15,'Tarkan','http://www.webshare.hkr.se/FECO0002/Tarkan%20-%20Tarkan.jpg',1999,'beatroot'),(16,'TranceDance Neelix remix','http://www.webshare.hkr.se/FECO0002/Techno-%20Neelix.jpg',2014,'beatroot');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `album_has_music_track`
--

DROP TABLE IF EXISTS `album_has_music_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album_has_music_track` (
  `album_album_id` int(11) NOT NULL,
  `music_track_track_id` int(11) NOT NULL,
  PRIMARY KEY (`album_album_id`,`music_track_track_id`),
  KEY `fk_album_has_music_track_music_track1_idx` (`music_track_track_id`),
  KEY `fk_album_has_music_track_album1_idx` (`album_album_id`),
  CONSTRAINT `fk_album_has_music_track_album1` FOREIGN KEY (`album_album_id`) REFERENCES `album` (`album_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_album_has_music_track_music_track1` FOREIGN KEY (`music_track_track_id`) REFERENCES `music_track` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album_has_music_track`
--

LOCK TABLES `album_has_music_track` WRITE;
/*!40000 ALTER TABLE `album_has_music_track` DISABLE KEYS */;
INSERT INTO `album_has_music_track` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(2,10),(2,11),(2,12),(2,13),(3,14),(3,15),(3,16),(3,17),(3,18),(4,19),(4,20),(5,21),(5,22),(5,23),(6,24),(6,25),(7,26),(8,27),(9,28),(9,29),(9,30),(9,31),(10,32),(10,33),(10,34),(11,35),(11,36),(11,37),(11,38),(12,39),(12,40),(13,41),(13,42),(13,43),(13,44),(13,45),(13,46),(13,47),(13,48),(13,49),(13,50),(13,51),(13,52),(13,53),(14,54),(14,55),(14,56),(14,57),(14,58),(14,59),(14,60),(14,61),(14,62),(14,63),(15,64),(15,65),(15,66),(16,67),(16,68),(16,69);
/*!40000 ALTER TABLE `album_has_music_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `message` mediumtext NOT NULL,
  `music_track_track_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_music_track1_idx` (`music_track_track_id`),
  CONSTRAINT `fk_comment_music_track1` FOREIGN KEY (`music_track_track_id`) REFERENCES `music_track` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Test from setFirstSong',19),(2,'Test from clickOnImageView',1),(4,'Test from lstTracks.',38);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contact_name` varchar(20) NOT NULL,
  PRIMARY KEY (`contact_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES ('temp');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_request` (
  `friend_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(250) NOT NULL,
  `premium_user_user_name` varchar(20) NOT NULL,
  `contact_contact_name` varchar(20) NOT NULL,
  PRIMARY KEY (`friend_request_id`),
  KEY `fk_friend_request_premium_user1_idx` (`premium_user_user_name`),
  KEY `fk_friend_request_contact1_idx` (`contact_contact_name`),
  CONSTRAINT `fk_friend_request_contact1` FOREIGN KEY (`contact_contact_name`) REFERENCES `contact` (`contact_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_request_premium_user1` FOREIGN KEY (`premium_user_user_name`) REFERENCES `premium_user` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `gender_id` int(11) NOT NULL,
  `gender` varchar(45) NOT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (0,'MALE'),(1,'FEMALE'),(2,'Not specified');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `genre_id` int(11) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `music_track_id` int(11) NOT NULL,
  PRIMARY KEY (`genre_id`),
  KEY `fk_genre_music_track1_idx` (`music_track_id`),
  CONSTRAINT `fk_genre_music_track1` FOREIGN KEY (`music_track_id`) REFERENCES `music_track` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'POP',19),(2,'INDUSTRIAL_METAL',1),(3,'HEAVY_METAL',22),(4,'DEATH_METAL',2),(5,'ALTERNATIVE_METAL',3),(6,'JAZZ',0),(7,'REGGAE',0),(8,'HIP_HOP',31),(9,'TECHNO',24),(10,'DISCO',20),(11,'HOUSE',0),(12,'BLUES',0),(13,'RAP',0),(14,'PUNK_ROCK',0),(15,'RYTHM_AND_BLUES',0),(16,'FOLK',0),(17,'DUBSTEP',0),(18,'ELECTRO',0),(19,'FUNK',0),(20,'COUNTRY',0),(21,'MELODY',0),(22,'ORCHESTRA',0),(23,'CLASSICAL',0),(24,'INSTRUMENTAL',0),(25,'DRUM_AND_BASS',0),(26,'TRANCE',0),(27,'GOSPEL',0),(28,'OPERA',0),(29,'POP_ROCK',0),(30,'BLUEGRASS',0),(31,'PROGRESSIVE_ROCK',0),(32,'FOLK_ROCK',0),(33,'SOUL',0),(34,'HARDCORE_PUNK',0),(35,'SKA',0),(36,'INDIE_POP',0),(37,'TRAP',0),(38,'WORLD',0),(39,'REGGAETON',0),(40,'BLUES_ROCK',0);
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_artist`
--

DROP TABLE IF EXISTS `music_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music_artist` (
  `artist_id` int(11) NOT NULL AUTO_INCREMENT,
  `stage_name` varchar(128) NOT NULL,
  `administrator_staff_id` varchar(20) NOT NULL,
  `rating_id` int(11) NOT NULL,
  `year_of_foundation` year(4) NOT NULL,
  `artist_description` mediumtext NOT NULL,
  PRIMARY KEY (`artist_id`),
  KEY `fk_music_artist_administrator1_idx` (`administrator_staff_id`),
  KEY `fk_music_artist_rating1_idx` (`rating_id`),
  CONSTRAINT `fk_music_artist_administrator1` FOREIGN KEY (`administrator_staff_id`) REFERENCES `administrator` (`staff_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_music_artist_rating1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`rating_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_artist`
--

LOCK TABLES `music_artist` WRITE;
/*!40000 ALTER TABLE `music_artist` DISABLE KEYS */;
INSERT INTO `music_artist` VALUES (1,'Combichrist','beatroot',500,2003,'Combichrist is an American aggrotech band formed in 2003 by Norwegian Andy LaPlegua, who also founded the bands Icon of Coil, Panzer AG, Scandinavian Cock and Scandy. Combichrist is based in Atlanta, Georgia. The band consist of only LaPlegua in the studio with Joe Letz, Nick Rossi, Eric13, and Brent Ashley for live performances. Releases by the band have been very successful in terms of sales and charted in top positions for dance and alternative charts worldwide.[1]'),(2,'Dope','beatroot',501,1997,'Dope is an American metal band from New York City, New York formed in 1997.'),(3,'Ellie Goulding','beatroot',502,2009,'Elena Jane \"Ellie\" Goulding (/ˈɡoʊldɪŋ/ gohl-ding; born 30 December 1986) is an English singer, songwriter and record producer. Her career began when she met record producers Starsmith and Frankmusik, and she was later spotted by Jamie Lillywhite, who later became her manager and A&R. After signing to Polydor Records in July 2009, Goulding released her debut extended play, An Introduction to Ellie Goulding, later that year'),(4,'Fratello Metallo','beatroot',503,1990,'Father Cesare Bonizzi, O.F.M. Cap. (born 15 March 1946), also known as Frate Cesare and Fratello Metallo (\"Brother Metal\") - which was also the name of his band, is a Capuchin friar, who was known as a heavy metal singer.'),(5,'Goya','beatroot',504,2016,'Jeff Owens - guitar, vocals'),(6,'Johnny Cash','beatroot',505,1954,'Johnny Cash (born J. R. Cash; February 26, 1932 – September 12, 2003) was an American singer-songwriter, guitarist, actor, and author'),(7,'Keith Carradine','beatroot',506,1971,'Keith Ian Carradine (born August 8, 1949) is an American actor, singer and songwriter who has had success on stage, film and television.'),(8,'Limp Bizkit','beatroot',507,1994,'Limp Bizkit is an American rap rock band from Jacksonville, Florida, formed in 1994. Their lineup consists of Fred Durst (lead vocals), Sam Rivers (bass guitar, backing vocals), John Otto (drums, percussions), and Wes Borland (guitars, backing vocals).'),(9,'Linkin Park','beatroot',508,1996,'Linkin Park is an American rock band from Agoura Hills, California. Formed in 1996, the band rose to international fame with their debut album Hybrid Theory (2000), which was certified diamond by the RIAA in 2005 and multi-platinum in several other countries'),(10,'Maximum The Hormone','beatroot',509,1998,'Maximum the Hormone Makishimamu Za Horumon?) is a Japanese Nu metal band from Hachiōji, Tokyo.'),(11,'PSY','beatroot',510,1999,'Park Jae-sang (Hangul: 박재상; Hanja: 朴載相; born December 31, 1977), known professionally as Psy, stylized PSY, is a South Korean singer, rapper, songwriter, and record producer. Psy is known domestically for his humorous videos and stage performances, and internationally for his hit single \"Gangnam Style\". The song\'s refrain was entered into The Yale Book of Quotations as one of the most famous quotations of 2012.[2]'),(12,'Queen','beatroot',511,1970,'Queen are a British rock band that formed in London in 1970. Their classic line-up was Freddie Mercury (lead vocals, piano), Brian May (lead guitar, vocals), Roger Taylor (drums, vocals), and John Deacon (bass guitar). Queen\'s earliest works were influenced by progressive rock, hard rock and heavy metal, but the band gradually ventured into more conventional and radio-friendly works by incorporating further styles, such as arena rock and pop rock, into their music.'),(13,'Rage Against The Machine','beatroot',512,1991,'Rage Against the Machine (also known as RATM or simply Rage) is an American rock band from Los Angeles, California. Formed in 1991, the group consists of rapper and vocalist Zack de la Rocha, bassist and backing vocalist Tim Commerford, guitarist Tom Morello, and drummer Brad Wilk. And Felicity saw them live at Big Day Out in Australia in 2008'),(14,'Tarkan','beatroot',513,1991,'One of few European singers who has managed to span chart success over three continents without singing in English, he is also noted for his live stage performances. Tarkan\'s effect on Turkey has been compared by the Washington Post to that of Elvis Presley in the US around 1957 and Atlantic Records co-founder Ahmet Ertegün described him as one of the best live performers he had ever seen.[1] He has also been listed by Rhapsody as a key artist in the history of European pop music, with his signature song \"Şımarık\" as a keystone track that moved the genre forward.'),(15,'Neelix','beatroot',514,1998,'Neelix (burgerlich Henrik Twardzik,  11. September 1975 in Hamburg) ist ein deutscher Musikproduzent, Liveact und DJ.');
/*!40000 ALTER TABLE `music_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_track`
--

DROP TABLE IF EXISTS `music_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music_track` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT,
  `track_name` varchar(100) NOT NULL,
  `track_length` time NOT NULL,
  `track_url` varchar(512) NOT NULL,
  `administrator_staff_id` varchar(20) NOT NULL,
  `rating_id` int(11) NOT NULL,
  `year_of_publication` year(4) NOT NULL,
  `music_artist_artist_id` int(11) NOT NULL,
  PRIMARY KEY (`track_id`),
  KEY `fk_music_track_administrator1_idx` (`administrator_staff_id`),
  KEY `fk_music_track_rating1_idx` (`rating_id`),
  KEY `fk_music_track_music_artist1_idx` (`music_artist_artist_id`),
  CONSTRAINT `fk_music_track_administrator1` FOREIGN KEY (`administrator_staff_id`) REFERENCES `administrator` (`staff_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_music_track_music_artist1` FOREIGN KEY (`music_artist_artist_id`) REFERENCES `music_artist` (`artist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_music_track_rating1` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`rating_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_track`
--

LOCK TABLES `music_track` WRITE;
/*!40000 ALTER TABLE `music_track` DISABLE KEYS */;
INSERT INTO `music_track` VALUES (1,'Zombie Fistfight','00:02:00','http://www.webshare.hkr.se/FECO0002/02%20-%20Combichrist%20%20-%20Zombie%20Fistfight.mp3','beatroot',1,2013,1),(2,'Feed The Fire','00:03:59','http://www.webshare.hkr.se/FECO0002/03%20-%20Combichrist%20-%20Feed%20The%20Fire.mp3','beatroot',2,2013,1),(3,'Clouds Of War','00:04:03','http://www.webshare.hkr.se/FECO0002/05%20-%20Combichrist%20-%20Clouds%20of%20War.mp3','beatroot',3,2013,1),(4,'Empty','00:04:31','http://www.webshare.hkr.se/FECO0002/07%20-%20Combichrist%20-%20Empty.mp3','beatroot',4,2013,1),(5,'I Know What I Am Doing (Planet Treason)','00:02:43','http://www.webshare.hkr.se/FECO0002/08%20-%20Combichrist%20-%20I%20Know%20What%20I%20Am%20Doing%20(Planet%20Treason).mp3','beatroot',5,2013,1),(6,'No Redemption','00:02:33','http://www.webshare.hkr.se/FECO0002/09%20-%20Combichrist%20-%20No%20Redemption.mp3','beatroot',6,2013,1),(7,'Falling Apart','00:02:45','http://www.webshare.hkr.se/FECO0002/10%20-%20Combichrist%20-%20Falling%20Apart.mp3','beatroot',7,2013,1),(8,'Gotta Go','00:02:59','http://www.webshare.hkr.se/FECO0002/11%20-%20Combichrist%20-%20Gotta%20Go.mp3','beatroot',8,2013,1),(9,'How Old Is Your Soul?','00:03:32','http://www.webshare.hkr.se/FECO0002/12%20-%20Combichrist%20-%20How%20Old%20Is%20Your%20Soul.mp3','beatroot',9,2013,1),(10,'Now Or Never','00:03:28','http://www.webshare.hkr.se/FECO0002/02%20-%20Dope%20-%20Now%20Or%20Never.mp3','beatroot',10,2001,2),(11,'Nothing (Why)','00:04:01','http://www.webshare.hkr.se/FECO0002/03%20-%20Dope%20-%20Nothing%20(Why).mp3','beatroot',11,2001,2),(12,'Die MF Die','00:03:07','http://www.webshare.hkr.se/FECO0002/06%20-%20Dope%20-%20Die%20Motherfucker%20Die.mp3','beatroot',12,2001,2),(13,'March Of Hope','00:07:30','http://www.webshare.hkr.se/FECO0002/13%20-%20Dope%20-%20March%20Of%20Hope.mp3','beatroot',13,2001,2),(14,'6-6-Sick','00:02:48','http://www.webshare.hkr.se/FECO0002/02%206-6-sick.mp3','beatroot',14,2001,2),(15,'Addiction','00:02:43','http://www.webshare.hkr.se/FECO0002/03%20Addiction.mp3','beatroot',15,2001,2),(16,'No Regrets','00:03:30','http://www.webshare.hkr.se/FECO0002/04%20No%20Regrets.mp3','beatroot',16,2001,2),(17,'Best For Me','00:03:21','http://www.webshare.hkr.se/FECO0002/10%20Best%20for%20Me.mp3','beatroot',17,2001,2),(18,'Rebell Yell','00:04:04','http://www.webshare.hkr.se/FECO0002/13%20Rebel%20Yell.mp3','beatroot',18,2001,2),(19,'Burn','00:03:54','http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Burn.mp3','beatroot',19,2015,3),(20,'Burn (Codeko Dubstep Remix)','00:03:58','http://www.webshare.hkr.se/FECO0002/Ellie%20Goulding%20-%20Burn%20(Codeko%20Dubstep%20Remix).mp3','beatroot',20,2015,3),(21,'Vita','00:04:33','http://www.webshare.hkr.se/FECO0002/04%20-%20Fratello%20Metallo%20-%20Vita.mp3','beatroot',21,2013,4),(22,'Bacco','00:02:56','http://www.webshare.hkr.se/FECO0002/06%20-%20Fratello%20Metallo%20-%20Bacco.mp3','beatroot',22,2013,4),(23,'Maria Maiestatis','00:02:59','http://www.webshare.hkr.se/FECO0002/10%20-%20Fratello%20Metallo%20-%20Maria%20Maiestatis.mp3','beatroot',23,2013,4),(24,'Technosong 7 Usn','00:07:32','http://www.webshare.hkr.se/FECO0002/Tecnosong%20%20GoYA%207%20Usn.mp3','beatroot',24,2008,5),(25,'Technosong 8 Usn','00:05:09','http://www.webshare.hkr.se/FECO0002/Tecnosong%20%20GoYA%208%20Usn.mp3','beatroot',25,2008,5),(26,'Hurt','00:04:02','http://www.webshare.hkr.se/FECO0002/Johnny%20Cash%20-%20Hurt.mp3','beatroot',26,1998,6),(27,'I\'m Easy','00:03:00','http://www.webshare.hkr.se/FECO0002/Keith%20Carradine%20%20-%20I\'m%20Easy.mp3','beatroot',27,1975,7),(28,'Nookie','00:04:27','http://www.webshare.hkr.se/FECO0002/03.Nookie.mp3','beatroot',28,2005,8),(29,'Break Stuff','00:02:48','http://www.webshare.hkr.se/FECO0002/04.Break%20Stuff.mp3','beatroot',29,2005,8),(30,'My Generation','00:03:43','http://www.webshare.hkr.se/FECO0002/08.My%20Generation.mp3','beatroot',30,2005,8),(31,'Rollin\' (Air Raid Vehicle)','00:03:37','http://www.webshare.hkr.se/FECO0002/09.Rollin\'%20(Air%20Raid%20Vehicle).mp3','beatroot',31,2005,8),(32,'I\'LL BE GONE','00:03:31','http://www.webshare.hkr.se/FECO0002/05%20-%20I\'LL%20BE%20GONE.mp3','beatroot',32,2012,9),(33,'CASTLE OF GLASS','00:03:25','http://www.webshare.hkr.se/FECO0002/06%20-%20CASTLE%20OF%20GLASS.mp3','beatroot',33,2012,9),(34,'ROADS UNTRAVELED','00:03:50','http://www.webshare.hkr.se/FECO0002/08%20-%20ROADS%20UNTRAVELED.mp3','beatroot',34,2012,9),(35,'Buiikikaesu','00:03:59','http://www.webshare.hkr.se/FECO0002/Maximum%20the%20Hormone%20-%20Buiikikaesu.mp3','beatroot',35,2007,10),(36,'Koi No Mega Lover','00:05:29','http://www.webshare.hkr.se/FECO0002/Maximum%20the%20Hormone%20-%20Koi%20No%20Mega%20Lover.mp3','beatroot',36,2007,10),(37,'What\'s up, people?!','00:04:10','http://www.webshare.hkr.se/FECO0002/Maximum%20the%20Hormone%20-%20What\'s%20up,people!.mp3','beatroot',37,2007,10),(38,'Zetsubou Billy','00:03:44','http://www.webshare.hkr.se/FECO0002/Maximum%20The%20Hormone%20-%20Zetsubou%20Billy.mp3','beatroot',38,2007,10),(39,'Gangnam Style','00:04:12','http://www.webshare.hkr.se/FECO0002/PSY%20-%20Gangnam%20Style.mp3','beatroot',39,2007,11),(40,'Gangnam Style (T. I. remix)','00:03:00','http://www.webshare.hkr.se/FECO0002/PSY%20-%20Gangnam%20Style%20(Tim%20Ismag%20Trollmix).mp3','beatroot',40,2007,11),(41,'We Will Rock You','00:02:01','http://www.webshare.hkr.se/FECO0002/01%20We%20Will%20Rock%20You.mp3','beatroot',41,2005,12),(42,'We Are The Champions','00:02:58','http://www.webshare.hkr.se/FECO0002/02%20We%20Are%20The%20Champions.mp3','beatroot',42,2005,12),(43,'Radio Ga Ga','00:05:40','http://www.webshare.hkr.se/FECO0002/03%20Radio%20Ga%20Ga.mp3','beatroot',43,2005,12),(44,'Another One Bites The Dust','00:03:31','http://www.webshare.hkr.se/FECO0002/04%20Another%20One%20Bites%20The%20Dust.mp3','beatroot',44,2005,12),(45,'Crazy Little Thing Called Love','00:02:41','http://www.webshare.hkr.se/FECO0002/06%20Crazy%20Little%20Thing%20Called%20Love.mp3','beatroot',45,2005,12),(46,'A Kind Of Magic','00:04:20','http://www.webshare.hkr.se/FECO0002/07%20A%20Kind%20Of%20Magic.mp3','beatroot',46,2005,12),(47,'Under Pressure','00:03:58','http://www.webshare.hkr.se/FECO0002/08%20Under%20Pressure.mp3','beatroot',47,2005,12),(48,'One Vision','00:04:00','http://www.webshare.hkr.se/FECO0002/09%20One%20Vision.mp3','beatroot',48,2005,12),(49,'Don\'t Stop Me Now','00:03:26','http://www.webshare.hkr.se/FECO0002/11%20Don\'t%20Stop%20Me%20Now.mp3','beatroot',49,2005,12),(50,'Seven Seas Of Rhye','00:02:44','http://www.webshare.hkr.se/FECO0002/15%20Seven%20Seas%20Of%20Rhye.mp3','beatroot',50,2005,12),(51,'I Want To Break Free','00:04:14','http://www.webshare.hkr.se/FECO0002/18%20I%20Want%20To%20Break%20Free.mp3','beatroot',51,2005,12),(52,'The Show Must Go On','00:04:27','http://www.webshare.hkr.se/FECO0002/19%20The%20Show%20Must%20Go%20On.mp3','beatroot',52,2005,12),(53,'Bohemian Rhapsody','00:05:48','http://www.webshare.hkr.se/FECO0002/20%20Bohemian%20Rhapsody.mp3','beatroot',53,2005,12),(54,'Bombtrack','00:04:04','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2001%20Bombtrack.mp3','beatroot',54,1992,13),(55,'Killing in the Name','00:05:14','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2002%20Killing%20in%20the%20Name.mp3','beatroot',55,1992,13),(56,'Take the Power Back','00:05:37','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2003%20Take%20the%20Power%20Back.mp3','beatroot',56,1992,13),(57,'Settle for Nothing','00:04:48','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2004%20Settle%20for%20Nothing.mp3','beatroot',57,1992,13),(58,'Bullet in the Head','00:05:09','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2005%20Bullet%20in%20the%20Head.mp3','beatroot',58,1992,13),(59,'Know Your Enemy','00:04:55','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2006%20Know%20Your%20Enemy.mp3','beatroot',59,1992,13),(60,'Wake Up','00:06:04','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2007%20Wake%20Up.mp3','beatroot',60,1992,13),(61,'Fistful of Steel','00:05:31','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2008%20Fistful%20of%20Steel.mp3','beatroot',61,1992,13),(62,'Township Rebelion','00:05:24','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2009%20Township%20Rebelion.mp3','beatroot',62,1992,13),(63,'Freedom','00:06:06','http://www.webshare.hkr.se/FECO0002/Rage%20Against%20The%20Machine%2010%20Freedom.mp3','beatroot',63,1992,13),(64,'Hepsi Sinin Mi','00:03:54','http://www.webshare.hkr.se/FECO0002/Tarkan%20-%20Hepsi%20Senin%20Mi.mp3','beatroot',64,1999,14),(65,'Simarik','00:04:00','http://www.webshare.hkr.se/FECO0002/Tarkan%20-%20Simarik.mp3','beatroot',65,1999,14),(66,'Kiss kiss','00:03:24','http://www.webshare.hkr.se/FECO0002/Holly%20Valance%20-%20Kiss%20Kiss.mp3','beatroot',66,1999,14),(67,'Shadow of a smile','00:07:38','http://www.webshare.hkr.se/FECO0002/TranceDance%20-%20Klopfgeister%20-%20Shadow%20of%20a%20smile%20(Neelix%20Remix).mp3','beatroot',67,2014,15),(68,'Dirty Buddy','00:07:27','http://www.webshare.hkr.se/FECO0002/TranceDance%20-%20Meller%20-%20Dirty%20Buddy%20(Neelix%20RMX).mp3','beatroot',68,2014,15),(69,'Voices','00:08:10','http://www.webshare.hkr.se/FECO0002/TranceDance%20-%20Official%20-%20Neelix%20-%20Voices.mp3','beatroot',69,2014,15);
/*!40000 ALTER TABLE `music_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `number_of_entries` int(10) unsigned zerofill NOT NULL,
  `total_duration` time NOT NULL,
  `privacy_level_privacy_id` int(11) NOT NULL,
  `owner` varchar(20) NOT NULL,
  PRIMARY KEY (`playlist_id`),
  KEY `fk_playlist_privacy_level1_idx` (`privacy_level_privacy_id`),
  KEY `fk_playlist_user_link1_idx` (`owner`),
  CONSTRAINT `fk_playlist_privacy_level1` FOREIGN KEY (`privacy_level_privacy_id`) REFERENCES `privacy_level` (`privacy_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_playlist_user_link1` FOREIGN KEY (`owner`) REFERENCES `user_link` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'Metal',0000000002,'00:00:00',1,'Misstery'),(2,'Dance',0000000004,'00:00:00',2,'Misstery'),(10,'Country',0000000001,'00:00:00',0,'Misstery');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_has_music_track`
--

DROP TABLE IF EXISTS `playlist_has_music_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_has_music_track` (
  `playlist_playlist_id` int(11) NOT NULL,
  `music_track_track_id` int(11) NOT NULL,
  PRIMARY KEY (`playlist_playlist_id`,`music_track_track_id`),
  KEY `fk_playlist_has_music_track_music_track1_idx` (`music_track_track_id`),
  KEY `fk_playlist_has_music_track_playlist1_idx` (`playlist_playlist_id`),
  CONSTRAINT `fk_playlist_has_music_track_music_track1` FOREIGN KEY (`music_track_track_id`) REFERENCES `music_track` (`track_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_playlist_has_music_track_playlist1` FOREIGN KEY (`playlist_playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_has_music_track`
--

LOCK TABLES `playlist_has_music_track` WRITE;
/*!40000 ALTER TABLE `playlist_has_music_track` DISABLE KEYS */;
INSERT INTO `playlist_has_music_track` VALUES (1,1),(1,2),(2,19),(10,27),(2,35),(2,40),(2,69);
/*!40000 ALTER TABLE `playlist_has_music_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premium_user`
--

DROP TABLE IF EXISTS `premium_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `premium_user` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `display_name` varchar(45) NOT NULL,
  `user_description` mediumtext NOT NULL,
  `personal_picture_path` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_address` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `physical_address` varchar(45) DEFAULT NULL,
  `city_of_residence` varchar(45) NOT NULL,
  `postal_code` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `bank_card_number` char(16) NOT NULL,
  `expiration_date` date NOT NULL,
  `card_type` varchar(45) NOT NULL,
  `billing_account_owner_name` varchar(45) NOT NULL,
  `billing_address` varchar(45) DEFAULT NULL,
  `billing_city` varchar(45) NOT NULL,
  `billing_postal_code` varchar(45) NOT NULL,
  `billing_country` varchar(45) NOT NULL,
  `billing_phone_number` varchar(45) NOT NULL,
  `gender_gender_id` int(11) NOT NULL,
  `playlist_link` varchar(20) NOT NULL,
  PRIMARY KEY (`user_name`),
  KEY `fk_premium_user_gender1_idx` (`gender_gender_id`),
  KEY `fk_premium_user_user_link1_idx` (`playlist_link`),
  CONSTRAINT `fk_premium_user_gender1` FOREIGN KEY (`gender_gender_id`) REFERENCES `gender` (`gender_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_premium_user_user_link1` FOREIGN KEY (`playlist_link`) REFERENCES `user_link` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premium_user`
--

LOCK TABLES `premium_user` WRITE;
/*!40000 ALTER TABLE `premium_user` DISABLE KEYS */;
INSERT INTO `premium_user` VALUES ('Misstery','12345','MissTery','I am a nice little old lady','I am a nice little old lady','Jane','Marple','1880-05-08','misstery@gmail.com','070 111 3333','382 Mount Dandenong Road','London','EC1A 9DB','United_Kingdom','5555777733331111','2019-05-08','Visa','Jane Marple','382 Mount Dandenong Road','London','EC1A 9DB','United_Kingdom','070 333 1111',2,'Misstery'),('temp','temporary','temp','','','tempie','temples','2017-01-01','temp@gmail.com','',NULL,'malmö','21014','Sverige','111133335555','2025-05-01','Mastercard','tempie',NULL,'malmö','21014','Sverige','0701113333',1,'temp');
/*!40000 ALTER TABLE `premium_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premium_user_has_contact`
--

DROP TABLE IF EXISTS `premium_user_has_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `premium_user_has_contact` (
  `premium_user_user_name` varchar(20) NOT NULL,
  `contact_contact_name` varchar(20) NOT NULL,
  PRIMARY KEY (`premium_user_user_name`,`contact_contact_name`),
  KEY `fk_premium_user_has_contact_contact1_idx` (`contact_contact_name`),
  KEY `fk_premium_user_has_contact_premium_user1_idx` (`premium_user_user_name`),
  CONSTRAINT `fk_premium_user_has_contact_contact1` FOREIGN KEY (`contact_contact_name`) REFERENCES `contact` (`contact_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_premium_user_has_contact_premium_user1` FOREIGN KEY (`premium_user_user_name`) REFERENCES `premium_user` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premium_user_has_contact`
--

LOCK TABLES `premium_user_has_contact` WRITE;
/*!40000 ALTER TABLE `premium_user_has_contact` DISABLE KEYS */;
INSERT INTO `premium_user_has_contact` VALUES ('Misstery','temp');
/*!40000 ALTER TABLE `premium_user_has_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privacy_level`
--

DROP TABLE IF EXISTS `privacy_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privacy_level` (
  `privacy_id` int(11) NOT NULL,
  `privacy_level` varchar(45) NOT NULL,
  PRIMARY KEY (`privacy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privacy_level`
--

LOCK TABLES `privacy_level` WRITE;
/*!40000 ALTER TABLE `privacy_level` DISABLE KEYS */;
INSERT INTO `privacy_level` VALUES (0,'PRIVATE'),(1,'PUBLIC'),(2,'CONTACT');
/*!40000 ALTER TABLE `privacy_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `sum_from_all_voters` float NOT NULL,
  `final_rating` float NOT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,0,0),(2,0,0),(3,0,0),(4,0,0),(5,0,0),(6,0,0),(7,0,0),(8,0,0),(9,0,0),(10,0,0),(11,0,0),(12,0,0),(13,0,0),(14,6,4),(15,0,0),(16,0,0),(17,0,0),(18,0,0),(19,33,3),(20,0,0),(21,0,0),(22,0,0),(23,0,0),(24,0,0),(25,0,0),(26,0,0),(27,0,0),(28,0,0),(29,0,0),(30,0,0),(31,0,0),(32,0,0),(33,0,0),(34,0,0),(35,0,0),(36,0,0),(37,0,0),(38,0,0),(39,0,0),(40,0,0),(41,0,0),(42,0,0),(43,0,0),(44,0,0),(45,0,0),(46,0,0),(47,0,0),(48,0,0),(49,0,0),(50,0,0),(51,0,0),(52,0,0),(53,0,0),(54,0,0),(55,0,0),(56,0,0),(57,0,0),(58,0,0),(59,0,0),(60,0,0),(61,0,0),(62,0,0),(63,0,0),(64,0,0),(65,0,0),(66,0,0),(67,0,0),(68,0,0),(69,1,5);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trial_user`
--

DROP TABLE IF EXISTS `trial_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trial_user` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `display_name` varchar(45) NOT NULL,
  `user_description` mediumtext NOT NULL,
  `personal_picture_path` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_address` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `physical_address` varchar(45) NOT NULL,
  `city_of_residence` varchar(45) NOT NULL,
  `postal_code` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `free_trial_end_date` date NOT NULL,
  `gender_gender_id` int(11) NOT NULL,
  `playlist_link` varchar(20) NOT NULL,
  PRIMARY KEY (`user_name`),
  KEY `fk_trial_user_gender1_idx` (`gender_gender_id`),
  KEY `fk_trial_user_user_link1_idx` (`playlist_link`),
  CONSTRAINT `fk_trial_user_gender1` FOREIGN KEY (`gender_gender_id`) REFERENCES `gender` (`gender_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trial_user_user_link1` FOREIGN KEY (`playlist_link`) REFERENCES `user_link` (`user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trial_user`
--

LOCK TABLES `trial_user` WRITE;
/*!40000 ALTER TABLE `trial_user` DISABLE KEYS */;
INSERT INTO `trial_user` VALUES ('nero','123456','DeadNero','','','Nero','Wolfe','1934-05-30','nerowolfe@gmail.com','','454 W. 35th Street','New York','10001','UNITED_STATES_OF_AMERICA','2017-06-08',1,'nero');
/*!40000 ALTER TABLE `trial_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_link`
--

DROP TABLE IF EXISTS `user_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_link` (
  `user` varchar(20) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_link`
--

LOCK TABLES `user_link` WRITE;
/*!40000 ALTER TABLE `user_link` DISABLE KEYS */;
INSERT INTO `user_link` VALUES ('beatroot'),('Misstery'),('nero'),('temp');
/*!40000 ALTER TABLE `user_link` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-17 19:53:16
