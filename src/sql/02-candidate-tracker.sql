CREATE DATABASE  IF NOT EXISTS `hb_candidate_tracker`;
USE `hb_candidate_tracker`;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;

CREATE TABLE `candidate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

