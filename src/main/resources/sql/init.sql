-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 17, 2025 at 01:15 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `minierp`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` varchar(255) NOT NULL,
  `taskId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` varchar(255) NOT NULL,
  `name` varchar(150) NOT NULL,
  `description` text DEFAULT NULL,
  `managerId` varchar(255) NOT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `name`, `description`, `managerId`, `startDate`, `endDate`) VALUES
('6088f525-4a73-4898-96a0-a794bfa2e5cd', 'aaaa', 'k,n', '4b0e6117-bef6-49ef-9f75-ae1a3da1c52a', '2025-12-08', NULL),
('9a747097-0781-4de0-9570-896abe3e218a', 'benji', 'bete', 'a11a46b1-081b-4895-a2b0-1d9cbf204337', '2025-12-06', NULL),
('f04259a5-b5b8-4228-8a34-41d5b1da16e5', 'adil', '1234', 'a11a46b1-081b-4895-a2b0-1d9cbf204337', '2025-12-08', NULL),
('ff005ccf-1359-411a-bc5d-51b11da9cf69', 'nabil', 'fou', 'a11a46b1-081b-4895-a2b0-1d9cbf204337', '2025-12-07', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` varchar(255) NOT NULL,
  `title` varchar(150) NOT NULL,
  `description` text DEFAULT NULL,
  `status` enum('TODO','IN PROGRESS','DONE') DEFAULT 'TODO',
  `dueDate` date DEFAULT NULL,
  `projectId` varchar(255) NOT NULL,
  `assignedTo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`id`, `title`, `description`, `status`, `dueDate`, `projectId`, `assignedTo`) VALUES
('1314757a-6c0f-4d70-9d9f-60e591f1c109', 'cvbn,', 'c vbn,', 'IN PROGRESS', '2025-11-30', '6088f525-4a73-4898-96a0-a794bfa2e5cd', '4b0e6117-bef6-49ef-9f75-ae1a3da1c52a'),
('28bcd6de-3b85-4280-afd5-8777d7eef980', 'xcvbn,', 'xdcfgv', 'TODO', '2025-11-30', '9a747097-0781-4de0-9570-896abe3e218a', 'a11a46b1-081b-4895-a2b0-1d9cbf204337'),
('2a259480-8d23-4249-be1d-28cac572fc31', 'zsdfgh', 'aqsdf', 'DONE', '2025-11-30', '6088f525-4a73-4898-96a0-a794bfa2e5cd', '4b0e6117-bef6-49ef-9f75-ae1a3da1c52a'),
('2acf7be3-7d4e-4e9b-8f85-8a94b64da242', 'derghjgfd', 'zerfgh', 'TODO', '2025-11-30', '9a747097-0781-4de0-9570-896abe3e218a', 'a11a46b1-081b-4895-a2b0-1d9cbf204337'),
('45e509b1-b28b-4278-a624-271d4e8c18ee', 'cfvgbhnjcv', 'cv bn', 'TODO', '2025-12-08', '9a747097-0781-4de0-9570-896abe3e218a', 'a11a46b1-081b-4895-a2b0-1d9cbf204337'),
('8c7866cf-74d1-4f92-9e04-5bf818244670', 'detfrgthbyj', 'gvhbnj', 'IN PROGRESS', '2025-11-30', 'ff005ccf-1359-411a-bc5d-51b11da9cf69', 'a11a46b1-081b-4895-a2b0-1d9cbf204337'),
('c210c24b-fa79-490d-b056-da92f10307f9', 'zdfrtgyh', 'azedgrty', 'IN PROGRESS', '2025-12-08', '6088f525-4a73-4898-96a0-a794bfa2e5cd', '4b0e6117-bef6-49ef-9f75-ae1a3da1c52a'),
('cdfa9587-648d-4166-8faa-35096fedf43b', 'adsfb', 'qsdfbgvn', 'TODO', '2025-11-30', 'f04259a5-b5b8-4228-8a34-41d5b1da16e5', 'a11a46b1-081b-4895-a2b0-1d9cbf204337'),
('d108674a-c51c-4ed6-9c72-527842b7c6b3', 'benji', 'erftghyujk', 'DONE', '2025-11-30', '6088f525-4a73-4898-96a0-a794bfa2e5cd', '4b0e6117-bef6-49ef-9f75-ae1a3da1c52a'),
('e3565f7d-9521-4017-8fd0-4f82540f01df', 'Benji', 'dcgfgbhj', 'TODO', '2025-11-30', '6088f525-4a73-4898-96a0-a794bfa2e5cd', '4b0e6117-bef6-49ef-9f75-ae1a3da1c52a');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `role` enum('ADMIN','MANAGER','EMPLOYEE') NOT NULL,
  `email` varchar(150) NOT NULL,
  `dateOfBirth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `surname`, `name`, `password_hash`, `role`, `email`, `dateOfBirth`) VALUES
('074a8ca0-2355-4475-ad00-8df35b3a2c22', 'kkkk', 'rue', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'EMPLOYEE', 'rue@gmail.com', NULL),
('4b0e6117-bef6-49ef-9f75-ae1a3da1c52a', 'adil', 'KHALIL', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'EMPLOYEE', 'adil@gmail.com', NULL),
('a11a46b1-081b-4895-a2b0-1d9cbf204337', 'nikiema', 'benji', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'ADMIN', 'nikiema@gmail.com', NULL),
('cd2ef832-956e-491e-bc83-305f7897b6b3', 'nabil', 'mouchili', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'MANAGER', 'nabil@gmail.com', NULL),
('U1', 'Admin', 'System', 'adminhash', 'MANAGER', 'admin@mail.com', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `taskId` (`taskId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `managerId` (`managerId`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `assignedTo` (`assignedTo`),
  ADD KEY `projectId` (`projectId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`taskId`) REFERENCES `tasks` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`managerId`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`assignedTo`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`projectId`) REFERENCES `projects` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
