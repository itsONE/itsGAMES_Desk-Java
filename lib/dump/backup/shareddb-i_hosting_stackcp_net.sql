-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: shareddb-i.hosting.stackcp.net
-- Generation Time: Jun 07, 2018 at 07:13 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `itsonedb-3337937e`
--
CREATE DATABASE IF NOT EXISTS `itsonedb-3337937e` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `itsonedb-3337937e`;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `date` date NOT NULL,
  `rating` char(3) NOT NULL,
  `site` varchar(35) NOT NULL,
  `developer` varchar(35) NOT NULL,
  `cover` varchar(255) NOT NULL DEFAULT 'blank.jpg',
  `background` varchar(255) NOT NULL DEFAULT 'blank.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id`, `name`, `description`, `date`, `rating`, `site`, `developer`, `cover`, `background`) VALUES
(1, 'FORTNITE', 'The Storm came without warning and wiped out 98 percent of the world\'s population in a flash. Poof. Adios. Sayonara. Then came the monsters, wave after wave, night after night. Destroying everything in their path. But it\'s not all doom and gloom. In an abandoned missile silo, we\'ve found one of our first weapons against the Storm you. We\'re looking for a few good commanders like you to help make a difference, push back the storm and protect those among us who are unable to protect themselves. Explore the world. Rescue survivors. Make hundreds of guns, swords, and things that go boom. Make impregnable forts. Tastefully decorate with sniper perches, poison gas traps, and jump pads. Take back the world. You know, the usual. And be sure to invite your friends. Welcome to Fortnite.', '2017-07-21', 'T', 'http://fortnitegame.com/', 'Epic Games', '23417548_171924346728877_8405782474705076224_n.jpg', '31958922_1924299657647349_503464218285047808_o.jpg'),
(2, 'PLAYERUNKNOWN\'S BATTLEGROUNDS', 'PLAYERUNKNOWN\'S BATTLEGROUNDS is a last-man-standing shooter being developed with community feedback. Players must fight to locate weapons and supplies in a massive 8x8 km island to be the lone survivor. This is BATTLE ROYALE.', '2017-12-20', 'T', 'https://playbattlegrounds.com/', 'Bluehole, Inc., PUBG Corporation', '22812527_442500159480299_8011550141024567296_n.jpg', '25550499_1960326894291225_4557629636267055425_n.jpg'),
(3, ' GRAND THEFT AUTO V', 'Los Santos: a sprawling sun-soaked metropolis full of self-help gurus, starlets and fading celebrities, once the envy of the Western world, now struggling to stay alive in a time of economic uncertainty and cheap reality TV. Amidst the turmoil, three very unique criminals plot their own chances of survival and success: Franklin, a street hustler looking for tangible opportunities and serious money; Michael, a professional ex-con whose retirement is less rosy than he figured it would be; and Trevor, a violent dude driven by the opportunity for a cheap high and his next big score. With options at a premium, the crew risks it all in a myriad of daring and dangerous heists that could set them up for life.', '2015-04-14', 'M', 'https://www.rockstargames.com/V/', 'Rockstar North', '22810705_140251603396086_3943215813829328896_n.jpg', '29468670_1903895452968638_6743682253071099301_n.png'),
(4, 'OVERWATCH', 'Overwatch is a highly stylized team-based shooter set on earth in the near future. Every match is an intense multiplayer showdown pitting a diverse cast of soldiers, mercenaries, scientists, adventurers, and oddities against each other in an epic, globe-spanning conflict.', '2016-04-23', 'T', 'https://playoverwatch.com/pt-br/', 'Blizzard Entertainment', '21624420_273804319792303_3831570411007508480_n.jpg', '34073786_1051355628355578_7186438587901018112_n.png'),
(5, ' DRAGON BALL FIGHTERZ', 'After the success of the Xenoverse series, its time to introduce a new classic 2D DRAGON BALL fighting game for this generations consoles. DRAGON BALL FighterZ is born from what makes the DRAGON BALL series so loved and famous: endless spectacular fights with its allpowerful fighters. Partnering with Arc System Works, DRAGON BALL FighterZ maximizes high end Anime graphics and brings easy to learn but difficult to master fighting gameplay to audiences worldwide.', '2018-01-26', 'T', 'bandainamcoent.com/dragon-fighterz', 'Arc System Works', '26813876_1549324141789788_7053036611629481984_n.jpg', '19250855_254138355066170_2616191251556797857_o.jpg'),
(6, ' HEARTHSTONE', 'Hearthstone is a free-to-play digital strategy card game that anyone can enjoy. Players choose one of nine epic Warcraft heroes to play as, and then take turns playing cards from their customizable decks to cast potent spells, use heroic weapons or abilities, or summon powerful characters to crush their opponent.', '2011-03-11', 'T', 'www.heroesofthestorm.com/', 'Blizzard Entertainment', '23803368_145253312864792_1362189362288132096_n.jpg', '29133119_1792582214117872_1521522302393516032_n.png'),
(7, ' STREET FIGHTER V', 'Birdie and Charlie Nash make their return to the Street Fighter universe, where they join classic characters like Ryu, Chun-Li, Cammy, and M. Bison. Many more new and returning characters will be added to the diverse roster, offering a wide variety of fighting styles for players to choose from. ', '2016-02-16', 'T', 'https://streetfighter.com/', 'Capcom', '23065593_1166667516799148_625721196805095424_n.jpg', '34054277_10156514898282147_4605832617953067008_o.jpg');

-- --------------------------------------------------------

--
-- Stand-in structure for view `game_list`
-- (See below for the actual view)
--
CREATE TABLE `game_list` (
`id` int(11)
,`name` varchar(50)
,`description` text
,`date` date
,`rating` char(3)
,`site` varchar(35)
,`developer` varchar(35)
,`cover` varchar(255)
,`background` varchar(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `game_user`
--

CREATE TABLE `game_user` (
  `user_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `review` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game_user`
--

INSERT INTO `game_user` (`user_id`, `game_id`, `review`) VALUES
(1, 1, 8),
(1, 3, 7),
(1, 4, 7),
(3, 1, NULL),
(3, 3, 10),
(3, 4, NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `game_user_list`
-- (See below for the actual view)
--
CREATE TABLE `game_user_list` (
`id` int(11)
,`name` varchar(50)
,`description` text
,`date` date
,`rating` char(3)
,`site` varchar(35)
,`developer` varchar(35)
,`cover` varchar(255)
,`background` varchar(255)
,`user_id` int(11)
,`game_id` int(11)
,`review` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO user_system (`id`, `username`, `email`, `password`) VALUES
(1, 'admin', 'admin@admin.com', '$2a$10$YxTd2y/uIDXsqZWThKuV5.etklBtqS8W3xa.lBM0rWU/onfBcJKXu'),
(3, 'pedro', 'pedro@gmail.com', '$2a$10$O.ppmIUCKT5h4n92VPmSHeYL3gEERTdsUm51ratT31U9X1piNsxeC'),
(4, 'test', 'test@test.com', '$2a$10$Za/KzVm65n.uOyaqtqurp.qq1bPwKlghKmn8pznjxF95BXXQM.fda'),
(5, 'testar', 'testar@email.com', '$2a$10$I7LJMa.Qa6Emc0DepQTVvO5ytnugsqfi9/yZjhs/V01pw8ajlS4uC');

-- --------------------------------------------------------

--
-- Structure for view `game_list`
--
DROP TABLE IF EXISTS `game_list`;

CREATE ALGORITHM=UNDEFINED DEFINER=`user-onedb`@`%` SQL SECURITY DEFINER VIEW `game_list`  AS  select `game`.`id` AS `id`,`game`.`name` AS `name`,`game`.`description` AS `description`,`game`.`date` AS `date`,`game`.`rating` AS `rating`,`game`.`site` AS `site`,`game`.`developer` AS `developer`,`game`.`cover` AS `cover`,`game`.`background` AS `background` from `game` ;

-- --------------------------------------------------------

--
-- Structure for view `game_user_list`
--
DROP TABLE IF EXISTS `game_user_list`;

CREATE ALGORITHM=UNDEFINED DEFINER=`user-onedb`@`%` SQL SECURITY DEFINER VIEW `game_user_list`  AS  select `game`.`id` AS `id`,`game`.`name` AS `name`,`game`.`description` AS `description`,`game`.`date` AS `date`,`game`.`rating` AS `rating`,`game`.`site` AS `site`,`game`.`developer` AS `developer`,`game`.`cover` AS `cover`,`game`.`background` AS `background`,`game_user`.`user_id` AS `user_id`,`game_user`.`game_id` AS `game_id`,`game_user`.`review` AS `review` from (`game` join `game_user` on((`game`.`id` = `game_user`.`game_id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `game_user`
--
ALTER TABLE `game_user`
  ADD PRIMARY KEY (`user_id`,`game_id`),
  ADD KEY `fk_game_user` (`game_id`);

--
-- Indexes for table `user`
--
ALTER TABLE user_system
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE user_system
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `game_user`
--
ALTER TABLE `game_user`
  ADD CONSTRAINT `fk_game_user` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  ADD CONSTRAINT `fk_user_game` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
