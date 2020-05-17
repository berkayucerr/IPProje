-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.4.12-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- database için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `database` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `database`;

-- tablo yapısı dökülüyor database.category
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.category: ~9 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`, `category_name`) VALUES
	(11, 'ACTİON'),
	(13, 'FUNNY'),
	(14, 'SIMULATOR'),
	(42, 'asdd'),
	(48, '123'),
	(49, '1'),
	(50, ''),
	(51, ''),
	(52, '');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.comments
CREATE TABLE IF NOT EXISTS `comments` (
  `comment_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `comment` varchar(50) CHARACTER SET utf8 NOT NULL,
  `score_id` int(11) unsigned NOT NULL,
  `game_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `score_fk` (`score_id`),
  KEY `game_fk` (`game_id`),
  CONSTRAINT `game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_fk` FOREIGN KEY (`score_id`) REFERENCES `score` (`score_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.comments: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` (`comment_id`, `comment`, `score_id`, `game_id`) VALUES
	(63, '123', 12, 68),
	(64, 'fewds2', 12, 68),
	(65, '123', 16, 68);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.dosya
CREATE TABLE IF NOT EXISTS `dosya` (
  `dosya_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dosya_ismi` varchar(100) CHARACTER SET utf8 NOT NULL,
  `dosya_konumu` varchar(350) CHARACTER SET utf8 NOT NULL,
  `dosya_tipi` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`dosya_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.dosya: ~3 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dosya` DISABLE KEYS */;
INSERT INTO `dosya` (`dosya_id`, `dosya_ismi`, `dosya_konumu`, `dosya_tipi`) VALUES
	(25, 'IMG_20200221_143624.jpg', 'C:\\Users\\berkay\\Desktop\\GamecommentersIP\\web\\dosyalar', 'image/jpeg'),
	(27, 'IMG_20200221_143636.jpg', 'C:\\Users\\berkay\\Documents\\NetBeansProjects\\IPGameCommenters\\web\\dosyalar', 'image/jpeg'),
	(28, 'IMG_20200221_143513.jpg', 'C:\\Users\\berkay\\Documents\\NetBeansProjects\\IPGameCommenters\\web\\dosyalar', 'image/jpeg');
/*!40000 ALTER TABLE `dosya` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.game
CREATE TABLE IF NOT EXISTS `game` (
  `game_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `baslik` varchar(50) CHARACTER SET utf8 NOT NULL,
  `aciklama` text CHARACTER SET utf8 NOT NULL,
  `vizyon_tarihi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `category_id` int(11) unsigned NOT NULL,
  `producer_id` int(11) unsigned NOT NULL,
  `dosya_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`game_id`),
  KEY `producer_fk` (`producer_id`),
  KEY `category_fk` (`category_id`),
  KEY `dosya_fk` (`dosya_id`),
  CONSTRAINT `category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dosya_fk` FOREIGN KEY (`dosya_id`) REFERENCES `dosya` (`dosya_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `producer_fk` FOREIGN KEY (`producer_id`) REFERENCES `producer` (`producer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.game: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` (`game_id`, `baslik`, `aciklama`, `vizyon_tarihi`, `category_id`, `producer_id`, `dosya_id`) VALUES
	(63, 'Emirhan', 'Kalaycı', '2000', 14, 11, 27),
	(68, 'dsa', 'dsa', 'dsa', 13, 7, 27);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.game_platform
CREATE TABLE IF NOT EXISTS `game_platform` (
  `game_id` int(11) unsigned NOT NULL,
  `platform_id` int(11) unsigned NOT NULL,
  KEY `game_platform_game_id` (`game_id`),
  KEY `game_platform_platform_id` (`platform_id`),
  CONSTRAINT `game_platform_game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `game_platform_platform_id` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.game_platform: ~11 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `game_platform` DISABLE KEYS */;
INSERT INTO `game_platform` (`game_id`, `platform_id`) VALUES
	(63, 35),
	(63, 36),
	(63, 37),
	(63, 40),
	(68, 35),
	(68, 36),
	(68, 37),
	(68, 38),
	(68, 39),
	(68, 40),
	(68, 54);
/*!40000 ALTER TABLE `game_platform` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.platform
CREATE TABLE IF NOT EXISTS `platform` (
  `platform_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `platform_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`platform_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.platform: ~9 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `platform` DISABLE KEYS */;
INSERT INTO `platform` (`platform_id`, `platform_name`) VALUES
	(35, 'XBOX'),
	(36, 'PLAYSTATİON'),
	(37, 'PC'),
	(38, 'ANDROİD'),
	(39, 'İOS'),
	(40, 'X'),
	(54, '1'),
	(68, 'asd12s'),
	(69, '');
/*!40000 ALTER TABLE `platform` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.producer
CREATE TABLE IF NOT EXISTS `producer` (
  `producer_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `producer_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`producer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.producer: ~13 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` (`producer_id`, `producer_name`) VALUES
	(7, 'Sony'),
	(8, 'RİOT GAMES'),
	(9, 'EA GAMES'),
	(10, 'BUGİSOFT'),
	(11, 'ROCKSTAR GAMES RC'),
	(18, 'PEAK GAMES'),
	(25, 'g'),
	(27, ''),
	(28, ''),
	(29, '1'),
	(30, ''),
	(31, '13'),
	(32, '123333');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.score
CREATE TABLE IF NOT EXISTS `score` (
  `score_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `game_score` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.score: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` (`score_id`, `game_score`) VALUES
	(12, 1),
	(13, 2),
	(14, 3),
	(15, 4),
	(16, 5),
	(17, 6),
	(18, 7),
	(19, 8),
	(20, 9),
	(21, 10);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;

-- tablo yapısı dökülüyor database.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `soyad` varchar(50) CHARACTER SET utf8 NOT NULL,
  `mail` varchar(50) CHARACTER SET utf8 NOT NULL,
  `admin` int(1) NOT NULL DEFAULT 0,
  `sifre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- database.user: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `ad`, `soyad`, `mail`, `admin`, `sifre`) VALUES
	(112, 'berkay', 'ucer', 'berkayucer@gmail.com', 1, '123456789'),
	(132, 'Toprak', 'Akdoğan', 'tierra@gmail.com', 1, '123456789'),
	(135, 'Emirhan', 'Kalaycı', 'ryuujix@gmail.com', 0, '123456789'),
	(139, 'berk', 'ay', 'berkay@gmail.com', 1, '123456789');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
