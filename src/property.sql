-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2017 at 09:29 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `property`
--

-- --------------------------------------------------------

--
-- Table structure for table `apartments`
--

CREATE TABLE IF NOT EXISTS `apartments` (
  `apartment_no` int(11) NOT NULL,
  `no.room` int(11) NOT NULL,
  `no.hall` int(11) NOT NULL,
  `no.toilet` int(11) NOT NULL,
  `no.kitchen` int(11) NOT NULL,
  `amount` varchar(10) NOT NULL,
  `rent_state` varchar(10) NOT NULL,
  `insurance` varchar(20) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `apartments`
--

INSERT INTO `apartments` (`apartment_no`, `no.room`, `no.hall`, `no.toilet`, `no.kitchen`, `amount`, `rent_state`, `insurance`, `price`) VALUES
(0, 1, 0, 1, 1, '', '', '', 0),
(1, 3, 1, 2, 1, '500', 'rented', '500', 0),
(2, 2, 1, 1, 1, '500', 'rented', '500', 0),
(3, 2, 1, 1, 1, '1500', 'rented', '500', 0),
(4, 2, 1, 1, 1, '1800', 'free', '500', 1300),
(5, 4, 2, 2, 2, '950', 'rented', 'no insurance', 0),
(6, 2, 2, 1, 1, '450', 'free', '500', 0),
(7, 2, 1, 1, 1, '2000', 'free', '500', 1500),
(8, 2, 1, 1, 1, '1700', 'free', '500', 1200);

-- --------------------------------------------------------

--
-- Table structure for table `leases`
--

CREATE TABLE IF NOT EXISTS `leases` (
`lease_no` int(11) NOT NULL,
  `receive` varchar(50) NOT NULL,
  `lodger_phone` int(11) NOT NULL,
  `amount` varchar(60) NOT NULL,
  `money` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  `paid` varchar(100) NOT NULL,
  `apartment_no` int(11) NOT NULL,
  `duration_from` date NOT NULL,
  `duration_until` date NOT NULL,
  `lodger_sign` varchar(50) NOT NULL,
  `renter_sign` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `office_name` varchar(60) NOT NULL,
  `office_tel` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leases`
--

INSERT INTO `leases` (`lease_no`, `receive`, `lodger_phone`, `amount`, `money`, `payment_date`, `paid`, `apartment_no`, `duration_from`, `duration_until`, `lodger_sign`, `renter_sign`, `address`, `office_name`, `office_tel`) VALUES
(0, 'a', 0, 'a', 0, '2017-05-01', 'a', 0, '2017-05-01', '2017-05-01', 'a', 'a', 'a', 'a', 0),
(1, 'bakr', 0, 'four hundred', 4000, '2014-09-05', 'apartment ', 0, '2014-09-08', '2014-10-09', 'flffljfhl', 'fyhjxhkm', 'mansouriah', 'Hawsawi Office', 12558474),
(2, 'hassan', 53669258, 'seven hundred', 700, '2017-08-09', 'paid for hiring an apartment ', 2, '2017-08-10', '2017-08-20', 'hasaan', 'hamam', 'faris nb', 'null', 503551505),
(4, 'Loay', 547710025, '1500 one thousend and five hundred', 1500, '2014-07-09', 'for renew the lease', 2, '2014-07-10', '2014-08-30', 'hkhlhl', 'hzdgjzd', 'al hijrah', 'hammam', 79454),
(5, 'bassam', 0, '500', 0, '2017-05-10', '', 1, '2017-05-10', '2017-05-19', '', '', 'haramain str', 'al-jazzirah', 12584720),
(6, 'hussam', 0, '500 five hundred', 0, '2017-04-07', '', 2, '2017-04-08', '2017-05-09', '', '', 'Al-Nakheel King Saud str', 'Al-Kamal ', 536924780);

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `username` varchar(50) NOT NULL,
  `pass` varchar(10) NOT NULL,
  `type` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`username`, `pass`, `type`) VALUES
('fatah', '12345', 'admin'),
('salem', '01234', 'emp'),
('Admin', 'pass', 'admin'),
('ali', '123', 'emp');

-- --------------------------------------------------------

--
-- Table structure for table `office`
--

CREATE TABLE IF NOT EXISTS `office` (
`office_num` int(11) NOT NULL,
  `office_name` varchar(50) NOT NULL,
  `office_phone` int(11) NOT NULL,
  `office_tel` int(11) NOT NULL,
  `office_address` varchar(100) NOT NULL,
  `office_city` varchar(50) NOT NULL,
  `office_owner` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `office`
--

INSERT INTO `office` (`office_num`, `office_name`, `office_phone`, `office_tel`, `office_address`, `office_city`, `office_owner`) VALUES
(1, 'Hawsawi office', 59668552, 5224147, 'janadraih prince majid str', 'riyadh', 'hassan'),
(10, 'Ladan realEstate', 503551505, 8265, 'Mansour nb Asfan st', 'jeddah', 'Mohammed Ladan'),
(12, 'Al-Kamal l', 536924780, 52481, 'Al-Nakheel King Saud str', 'Al-Khubar', 'Hussain');

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE IF NOT EXISTS `rent` (
  `rent_id` int(11) NOT NULL,
  `property_num` int(11) NOT NULL,
  `floor_no` int(11) NOT NULL,
  `apartment_no` int(11) NOT NULL,
  `lease_no` int(11) NOT NULL,
  `rent_state` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`rent_id`, `property_num`, `floor_no`, `apartment_no`, `lease_no`, `rent_state`) VALUES
(0, 0, 0, 0, 0, 'a'),
(1, 1001, 1, 1, 1, 'rented'),
(2, 1001, 1, 1, 2, 'rented');

-- --------------------------------------------------------

--
-- Table structure for table `residential_units`
--

CREATE TABLE IF NOT EXISTS `residential_units` (
`property_num` int(11) NOT NULL,
  `property_name` varchar(50) NOT NULL,
  `property_type` varchar(60) NOT NULL,
  `property_address` varchar(100) NOT NULL,
  `property_city` varchar(50) NOT NULL,
  `property_zip` int(5) NOT NULL,
  `no.floor` int(11) NOT NULL,
  `no.apartment` int(11) NOT NULL,
  `no.room` int(11) NOT NULL,
  `no.hall` int(11) NOT NULL,
  `no.toilet` int(11) NOT NULL,
  `no.kitchen` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residential_units`
--

INSERT INTO `residential_units` (`property_num`, `property_name`, `property_type`, `property_address`, `property_city`, `property_zip`, `no.floor`, `no.apartment`, `no.room`, `no.hall`, `no.toilet`, `no.kitchen`) VALUES
(0, 'a', 'a', 'a', 'a', 12, 0, 0, 0, 0, 0, 0),
(1001, 'Al- Nojom', 'hotel', 'Al- nozhah sahat-Islam', 'Makkah', 1400, 3, 6, 18, 6, 24, 12),
(1003, 'Al-Haramain', 'hotel', 'Al-Maosouriah Al-Mansour st', 'Makkah', 1400, 0, 0, 0, 0, 0, 0),
(1004, 'karim', 'building', 'hazmiah al-shomukh str', 'arar', 5244, 2, 4, 8, 4, 8, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(11) NOT NULL,
  `firstname` varchar(60) NOT NULL,
  `lastname` varchar(60) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(90) NOT NULL,
  `job` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `city` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `zip` varchar(9) NOT NULL,
  `na_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20556149 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstname`, `lastname`, `phone`, `email`, `job`, `gender`, `city`, `address`, `zip`, `na_id`) VALUES
(20147441, 'hassan', 'Al- Wael', 59664811, 'wal@mail.state', 'emp', 'male', 'riyyadh', 'al jazairi ', '36652', 10822144),
(20556144, 'ahmed', 'al-qarni', 582269523, 'qarni@mail.state', 'emp', 'male', 'baha', 'waleed bin ali', '25471', 107225415),
(20556146, 'khalid', 'qahtani', 59665411, 'qaht@mail.state', 'admin', 'male', 'abha', 'al-nasser', '5822', 1065885247),
(20556147, 'hilal', 'barnawi', 535582103, 'hilal.falah@gmail.state', 'admin', 'male', 'Makkah', '60th st', '5822', 105336933),
(20556148, 'hassan', 'salem', 58944142, 'hasan@gmail.state', 'Deputy', 'male', 'ahsaa', 'al-noor str', '85471', 108224402);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apartments`
--
ALTER TABLE `apartments`
 ADD PRIMARY KEY (`apartment_no`);

--
-- Indexes for table `leases`
--
ALTER TABLE `leases`
 ADD PRIMARY KEY (`lease_no`);

--
-- Indexes for table `office`
--
ALTER TABLE `office`
 ADD PRIMARY KEY (`office_num`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
 ADD KEY `property_num` (`property_num`), ADD KEY `apartment_no` (`apartment_no`), ADD KEY `lease_no` (`lease_no`);

--
-- Indexes for table `residential_units`
--
ALTER TABLE `residential_units`
 ADD PRIMARY KEY (`property_num`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `leases`
--
ALTER TABLE `leases`
MODIFY `lease_no` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `office`
--
ALTER TABLE `office`
MODIFY `office_num` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `residential_units`
--
ALTER TABLE `residential_units`
MODIFY `property_num` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1005;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20556149;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `rent`
--
ALTER TABLE `rent`
ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`property_num`) REFERENCES `residential_units` (`property_num`),
ADD CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`apartment_no`) REFERENCES `apartments` (`apartment_no`),
ADD CONSTRAINT `rent_ibfk_3` FOREIGN KEY (`lease_no`) REFERENCES `leases` (`lease_no`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
