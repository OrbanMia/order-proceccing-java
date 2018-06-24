-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Gép: localhost
-- Létrehozás ideje: 2018. Jún 24. 20:15
-- Kiszolgáló verziója: 10.1.26-MariaDB
-- PHP verzió: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `JavaProba`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `order_item`
--

CREATE TABLE `order_item` (
  `OrderItemId` int(11) NOT NULL,
  `OrderId` int(11) NOT NULL,
  `SalePrice` double(10,2) NOT NULL,
  `ShippingPrice` double(10,2) NOT NULL,
  `TotalItemPrice` double(10,2) NOT NULL,
  `SKU` text COLLATE utf8_bin NOT NULL,
  `Status` enum('OUT_OF_STOCK','IN_STOCK','','') COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `order_java`
--

CREATE TABLE `order_java` (
  `OrderId` int(11) NOT NULL,
  `BuyerName` varchar(255) COLLATE utf8_bin NOT NULL,
  `BuyerEmail` varchar(255) COLLATE utf8_bin NOT NULL,
  `OrderDate` date NOT NULL,
  `OrderTotalValue` double(10,2) NOT NULL,
  `Address_` varchar(255) COLLATE utf8_bin NOT NULL,
  `Postcode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`OrderItemId`),
  ADD UNIQUE KEY `OrderId_2` (`OrderId`),
  ADD KEY `OrderId` (`OrderId`);

--
-- A tábla indexei `order_java`
--
ALTER TABLE `order_java`
  ADD PRIMARY KEY (`OrderId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
