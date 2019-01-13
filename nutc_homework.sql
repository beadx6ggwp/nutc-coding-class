-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主機: 127.0.0.1
-- 產生時間： 2019 年 01 月 13 日 09:36
-- 伺服器版本: 10.1.37-MariaDB
-- PHP 版本： 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `nutc_homework`
--

-- --------------------------------------------------------

--
-- 資料表結構 `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `post_comment` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `post`
--

INSERT INTO `post` (`post_id`, `post_time`, `post_name`, `post_comment`) VALUES
(30, '2018-12-14 17:13:41', '2', '5'),
(31, '2018-12-16 13:42:49', 'e1234', 'awwe'),
(32, '2018-12-16 13:42:59', '3', '6'),
(35, '2018-12-16 13:43:05', '4', '5'),
(36, '2018-12-28 12:13:29', 'qwe', '12331');

-- --------------------------------------------------------

--
-- 資料表結構 `upload_image`
--

CREATE TABLE `upload_image` (
  `id` int(11) NOT NULL,
  `img` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `class` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `upload_image`
--

INSERT INTO `upload_image` (`id`, `img`, `name`, `class`, `time`) VALUES
(7, '5GOnIbIOTE0U4fK6lbMO.jpg', 'qw', 'test2', '2018-12-28 11:20:59'),
(8, '9yQF3YU.jpg', 'qwerty', 'test1', '2018-12-28 11:47:15'),
(9, '5a86035759e3b62415f67bca2bd51fca.GIF', 'qw', 'test1', '2018-12-28 12:04:50');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`);

--
-- 資料表索引 `upload_image`
--
ALTER TABLE `upload_image`
  ADD PRIMARY KEY (`id`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- 使用資料表 AUTO_INCREMENT `upload_image`
--
ALTER TABLE `upload_image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
