-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.26 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for climate_control
CREATE DATABASE IF NOT EXISTS `climate_control` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `climate_control`;


-- Dumping structure for table climate_control.climate_data
CREATE TABLE IF NOT EXISTS `climate_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) COLLATE utf8_bin DEFAULT '0',
  `temperature` float DEFAULT '0',
  `humidity` float DEFAULT '0',
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `controller_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table climate_control.climate_data: ~5 rows (approximately)
DELETE FROM `climate_data`;
/*!40000 ALTER TABLE `climate_data` DISABLE KEYS */;
INSERT INTO `climate_data` (`id`, `status`, `temperature`, `humidity`, `date`, `controller_number`) VALUES
	(1, 'OK', 28, 12, '2015-01-22 00:00:00', 1),
	(2, 'OK', 50, 22, '2015-02-28 16:32:07', 1),
	(3, 'OK', 29, 30, '2015-11-28 16:32:27', 1),
	(4, 'OK', 25, 33, '2015-12-28 16:32:39', 1),
	(5, 'Ok', 11, 22, '2015-12-28 17:26:10', 1);
/*!40000 ALTER TABLE `climate_data` ENABLE KEYS */;


-- Dumping structure for table climate_control.controller_configuration
CREATE TABLE IF NOT EXISTS `controller_configuration` (
  `controller_number` int(11) NOT NULL,
  `max_temperature` float NOT NULL,
  `min_temperature` float NOT NULL,
  `max_humidity` float NOT NULL,
  `min_humidity` float NOT NULL,
  PRIMARY KEY (`controller_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table climate_control.controller_configuration: ~1 rows (approximately)
DELETE FROM `controller_configuration`;
/*!40000 ALTER TABLE `controller_configuration` DISABLE KEYS */;
INSERT INTO `controller_configuration` (`controller_number`, `max_temperature`, `min_temperature`, `max_humidity`, `min_humidity`) VALUES
	(1, 40, 11, 50, 12);
/*!40000 ALTER TABLE `controller_configuration` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
