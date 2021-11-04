-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1:3306
-- Timp de generare: nov. 04, 2021 la 05:15 PM
-- Versiune server: 5.7.31
-- Versiune PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `drools_master_db`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract` (
  `id` bigint(20) NOT NULL,
  `contract_type` varchar(255) DEFAULT NULL,
  `date_start` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9os0rerwj7q4finn5ufs1s9u5` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `contract`
--

INSERT INTO `contract` (`id`, `contract_type`, `date_start`, `end_date`, `person_id`) VALUES
(1, 'SMALL', '2021-10-12 13:56:50.000000', '2021-11-02 13:56:50.000000', 1),
(2, 'LONG', '2021-10-12 13:17:10.000000', '2021-12-24 13:17:10.000000', 2),
(3, 'MEDIUM', '2021-10-14 13:17:36.000000', '2021-12-24 13:17:36.000000', 3),
(4, 'MEDIUM', '2021-10-12 13:18:55.000000', '2021-11-02 13:18:55.000000', 1);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `discount`
--

DROP TABLE IF EXISTS `discount`;
CREATE TABLE IF NOT EXISTS `discount` (
  `id` bigint(20) NOT NULL,
  `percentage` double DEFAULT NULL,
  `insurance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5vlludrkdp0rd0d9u5923fxdr` (`insurance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `discount`
--

INSERT INTO `discount` (`id`, `percentage`, `insurance_id`) VALUES
(1, 0.25, 2),
(2, 0, 7),
(3, 0, 7),
(4, 0.45, 6),
(5, 0, 5),
(6, 0, 7);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `insurance`
--

DROP TABLE IF EXISTS `insurance`;
CREATE TABLE IF NOT EXISTS `insurance` (
  `id` bigint(20) NOT NULL,
  `active_payment` bit(1) NOT NULL,
  `insurance_type` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `sum_insured` double DEFAULT NULL,
  `contract_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5loom9payruew6b3xet28vysp` (`contract_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `insurance`
--

INSERT INTO `insurance` (`id`, `active_payment`, `insurance_type`, `number`, `status`, `sum_insured`, `contract_id`) VALUES
(1, b'1', 'LIFE', '1116', b'0', 0, 1),
(2, b'1', 'VEHICLE', '1115', b'0', 0, 1),
(3, b'1', 'LIFE', '0114', b'0', 0, 2),
(4, b'1', 'VEHICLE', '1114', b'0', 0, 2),
(5, b'0', 'PROPERTY', '1113', b'0', 0, 1),
(6, b'1', 'PROPERTY', '1118', b'0', 0, 3),
(7, b'0', 'LIFE', '1119', b'0', 0, 3),
(8, b'0', 'VEHICLE', '0119', b'0', 0, 4);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `insurance_products`
--

DROP TABLE IF EXISTS `insurance_products`;
CREATE TABLE IF NOT EXISTS `insurance_products` (
  `id` bigint(20) NOT NULL,
  `sum_insured` double DEFAULT NULL,
  `insurance_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8un7ej4fuw6gxebnv4ihlcoss` (`insurance_id`),
  KEY `FKi7hwi81um0mu6urfluwaojk53` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `insurance_products`
--

INSERT INTO `insurance_products` (`id`, `sum_insured`, `insurance_id`, `product_id`) VALUES
(1, 65, 1, 3),
(2, 25, 2, 1),
(3, 65, 3, 3),
(4, 0, 4, 1),
(5, 0, 3, 3),
(6, 0, 4, 1),
(7, 0, 5, 2),
(8, 0, 6, 2),
(9, 0, 7, 3),
(10, 0, 3, 3),
(11, 0, 2, 1),
(12, 0, 5, 2),
(13, 0, 8, 1);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `id` bigint(20) NOT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `paid_sum` double DEFAULT NULL,
  `insurance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlp1dkis8sf0e4fp5mxbmnwjo9` (`insurance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `payment_notification`
--

DROP TABLE IF EXISTS `payment_notification`;
CREATE TABLE IF NOT EXISTS `payment_notification` (
  `id` bigint(20) NOT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `insurance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8mamj0xmvprph49y6g427rnn2` (`insurance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `payment_notification`
--

INSERT INTO `payment_notification` (`id`, `due_date`, `insurance_id`) VALUES
(1, '2021-10-05 19:41:11.000000', 1),
(2, '2021-10-12 19:41:22.000000', 2),
(3, '2021-10-05 19:41:29.000000', 3);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `person`
--

INSERT INTO `person` (`id`, `holder_name`) VALUES
(1, 'Holder_name_1'),
(2, 'Holder_name_2'),
(3, 'Holder_name_3');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `starting_sum` double DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `product`
--

INSERT INTO `product` (`id`, `product_name`, `starting_sum`, `product_type`) VALUES
(1, 'Vehicle Insurance Product_name_n', 15, 'VEHICLE'),
(2, 'Property Insurance Product_name_n', 25, 'HOUSE'),
(3, 'Life Insurance Product_name_n', 45, 'LIFE');

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `FK9os0rerwj7q4finn5ufs1s9u5` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`);

--
-- Constrângeri pentru tabele `discount`
--
ALTER TABLE `discount`
  ADD CONSTRAINT `FK5vlludrkdp0rd0d9u5923fxdr` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`id`);

--
-- Constrângeri pentru tabele `insurance`
--
ALTER TABLE `insurance`
  ADD CONSTRAINT `FK5loom9payruew6b3xet28vysp` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`);

--
-- Constrângeri pentru tabele `insurance_products`
--
ALTER TABLE `insurance_products`
  ADD CONSTRAINT `FK8un7ej4fuw6gxebnv4ihlcoss` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`id`),
  ADD CONSTRAINT `FKi7hwi81um0mu6urfluwaojk53` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constrângeri pentru tabele `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FKlp1dkis8sf0e4fp5mxbmnwjo9` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`id`);

--
-- Constrângeri pentru tabele `payment_notification`
--
ALTER TABLE `payment_notification`
  ADD CONSTRAINT `FK8mamj0xmvprph49y6g427rnn2` FOREIGN KEY (`insurance_id`) REFERENCES `insurance` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
