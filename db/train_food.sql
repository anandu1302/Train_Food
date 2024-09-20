-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2024 at 12:11 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `train_food`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart_tbl`
--

CREATE TABLE IF NOT EXISTS `cart_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  `food_id` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `cart_tbl`
--

INSERT INTO `cart_tbl` (`id`, `user_id`, `food_id`, `quantity`) VALUES
(1, '1', '3', '3'),
(2, '1', '2', '6'),
(5, '1', '1', '1'),
(6, '8', '2', '2'),
(7, '8', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `food_tbl`
--

CREATE TABLE IF NOT EXISTS `food_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `restaurant_id` varchar(100) NOT NULL,
  `itemName` varchar(100) NOT NULL,
  `image` tinytext NOT NULL,
  `price` varchar(100) NOT NULL,
  `food_type` varchar(100) NOT NULL,
  `itemDesc` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `food_tbl`
--

INSERT INTO `food_tbl` (`id`, `restaurant_id`, `itemName`, `image`, `price`, `food_type`, `itemDesc`) VALUES
(1, '1', 'Masala Dosa', 'dosa.jpg', '106', 'veg', 'A famous south Indian dish'),
(2, '2', 'Chicken Biriyani', 'biriyani.jpg', '130', 'non-veg', 'Serves 1 | Our slow steamed chicken biriyani is cooked with superfine rice with aromatic quality spices and masala powders. Fresh meat are slow cooked in flavorful thick rich broth mixed with ginger, garlic, bombay onion, green chilly and other biriyani ingredients. We serve dates pickle and south indian raita with our biriyani.'),
(3, '4', 'Beef Biriyani', 'beefbiriyani.jpg', '165', 'non-veg', 'Biryani is a mixed rice dish most popular in South Asia and was thought to have originated from ancient Iran. It is made with rice, some type of meat  and spices. '),
(4, '3', 'al faham mandhi', '2024-03-05-04-01-11Designs (7).png', '300', 'non-veg', 'dgv');

-- --------------------------------------------------------

--
-- Table structure for table `orders_tbl`
--

CREATE TABLE IF NOT EXISTS `orders_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `train_details` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `train_no` varchar(100) NOT NULL,
  `coach_position` varchar(100) NOT NULL,
  `seat_no` varchar(100) NOT NULL,
  `payment_status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `orders_tbl`
--

INSERT INTO `orders_tbl` (`id`, `uid`, `train_details`, `name`, `contact`, `train_no`, `coach_position`, `seat_no`, `payment_status`) VALUES
(1, '1', 'kerala Express', 'anandu', '8547841563', '16256', '12', '8547841563', ''),
(2, '1', 'eranad', 'basi', '3690852147', '16256', '14', '3690852147', ''),
(3, '1', 'Memu', 'manu', '2356890855', '12356', 't', '2356890855', 'paid'),
(5, '8', 'kerala express', 'gokul', '9946087498', '23425', 'ac', '9946087498', 'paid'),
(6, '8', 'kerala express', 'gokulm', '9946087498', '12524', 'addf', '9946087498', 'paid');

-- --------------------------------------------------------

--
-- Table structure for table `payment_tbl`
--

CREATE TABLE IF NOT EXISTS `payment_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `amount` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  `card_name` varchar(100) NOT NULL,
  `card_number` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `payment_tbl`
--

INSERT INTO `payment_tbl` (`id`, `user_id`, `order_id`, `amount`, `date`, `card_name`, `card_number`) VALUES
(1, '1', '3', '1286', '05-03-2024', 'anandu ', '1234567890885258'),
(2, '8', '4', '236', '05-03-2024', 'ubyv', '1234567890855258'),
(3, '8', '4', '236', '05-03-2024', 'ubyv', '1234567890855258'),
(4, '8', '5', '236', '05-03-2024', 'gokul', '1235456882868588'),
(5, '8', '6', '366', '05-03-2024', 'gokul', '1234567890122558');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant_tbl`
--

CREATE TABLE IF NOT EXISTS `restaurant_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `latitude` varchar(200) NOT NULL,
  `longitude` varchar(200) NOT NULL,
  `rating` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `restaurant_tbl`
--

INSERT INTO `restaurant_tbl` (`id`, `name`, `place`, `image`, `latitude`, `longitude`, `rating`) VALUES
(1, 'Brindhavan Restaurant', 'Palarivattom', 'brindhavan.jpg', '10.006634', '76.3058215', '4.5'),
(2, 'Biriyani Hut', 'Elamakkara', 'hut.jpg', '10.0091194', '76.2873552', '3.8'),
(3, 'Majlis Restaurant', 'North Paravur', 'majlis.jpg', '10.1469324', '76.2157145', '3.9'),
(4, 'Paradise Hotel', 'Palarivattom', 'paradise.jpg', '10.002262', '76.2680978', '4.3');

-- --------------------------------------------------------

--
-- Table structure for table `user_tbl`
--

CREATE TABLE IF NOT EXISTS `user_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number` varchar(100) NOT NULL,
  `userimage` tinytext NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `user_tbl`
--

INSERT INTO `user_tbl` (`id`, `name`, `number`, `userimage`, `email`, `password`) VALUES
(1, 'Anandu', '8943409211', 'image1.jpg', 'anandu@gmail.com', '555'),
(2, 'Kalliyani', '7736473857', 'image2.jpg', 'kalliyani@gmail.com', 'qwee'),
(8, 'M Gokul', '9946087498', 'image8.jpg', 'gokulmani972@gmail.com', '1111');

-- --------------------------------------------------------

--
-- Table structure for table `vendor_tbl`
--

CREATE TABLE IF NOT EXISTS `vendor_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `number` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `vendor_tbl`
--

INSERT INTO `vendor_tbl` (`id`, `name`, `number`, `email`, `password`) VALUES
(1, 'Amal', '7896541230', 'amal@gmail.com', '123');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
