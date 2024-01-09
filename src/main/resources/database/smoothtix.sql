-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        11.2.2-MariaDB - mariadb.org binary distribution
-- 伺服器作業系統:                      Win64
-- HeidiSQL 版本:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 傾印 smoothtix 的資料庫結構
CREATE DATABASE IF NOT EXISTS `smoothtix` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `smoothtix`;

-- 傾印  資料表 smoothtix.2collection 結構
CREATE TABLE IF NOT EXISTS `2collection` (
  `collection` int(11) NOT NULL AUTO_INCREMENT,
  `cdescription` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`collection`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  smoothtix.2collection 的資料：~2 rows (近似值)
INSERT INTO `2collection` (`collection`, `cdescription`) VALUES
	(1, '超商取票'),
	(2, '現場取票'),
	(3, '電子票券');

-- 傾印  資料表 smoothtix.2dateandlocation 結構
CREATE TABLE IF NOT EXISTS `2dateandlocation` (
  `dateandlocation` int(11) NOT NULL AUTO_INCREMENT,
  `ddescription` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`dateandlocation`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  smoothtix.2dateandlocation 的資料：~6 rows (近似值)
INSERT INTO `2dateandlocation` (`dateandlocation`, `ddescription`) VALUES
	(1, '1/13台北兩廳院'),
	(2, '1/13台中歌劇院'),
	(3, '1/13高雄衛武營'),
	(4, '1/20台北兩廳院'),
	(5, '1/20台中歌劇院'),
	(6, '1/20高雄衛武營');

-- 傾印  資料表 smoothtix.2payment 結構
CREATE TABLE IF NOT EXISTS `2payment` (
  `payment` int(11) NOT NULL AUTO_INCREMENT,
  `pdescription` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`payment`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  smoothtix.2payment 的資料：~3 rows (近似值)
INSERT INTO `2payment` (`payment`, `pdescription`) VALUES
	(1, '信用卡'),
	(2, '超商付款'),
	(3, '轉帳');

-- 傾印  資料表 smoothtix.2tickettype 結構
CREATE TABLE IF NOT EXISTS `2tickettype` (
  `tickettype` int(11) NOT NULL AUTO_INCREMENT,
  `tdecription` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tickettype`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  smoothtix.2tickettype 的資料：~3 rows (近似值)
INSERT INTO `2tickettype` (`tickettype`, `tdecription`) VALUES
	(1, '敬老票'),
	(2, '學生票'),
	(3, '全票');

-- 傾印  資料表 smoothtix.ticketinfo 結構
CREATE TABLE IF NOT EXISTS `ticketinfo` (
  `infoid` int(11) NOT NULL AUTO_INCREMENT,
  `dateandlocation` int(5) NOT NULL,
  `seat` varchar(10) NOT NULL,
  `tickettype` int(5) NOT NULL,
  `payment` int(5) NOT NULL,
  `collection` int(5) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`infoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  smoothtix.ticketinfo 的資料：~0 rows (近似值)

-- 傾印  資料表 smoothtix.user 結構
CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL DEFAULT '0',
  `password` varchar(20) NOT NULL DEFAULT '0',
  `mobile` varchar(15) DEFAULT NULL,
  `e-mail` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  smoothtix.user 的資料：~0 rows (近似值)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
