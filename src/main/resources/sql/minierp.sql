-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mar. 02 déc. 2025 à 15:50
-- Version du serveur : 5.7.24
-- Version de PHP : 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `minierp`
--

-- --------------------------------------------------------

--
-- Structure de la table `comments`
--

CREATE TABLE `comments` (
                            `id` varchar(255) NOT NULL,
                            `taskId` varchar(255) NOT NULL,
                            `userId` varchar(255) NOT NULL,
                            `content` text NOT NULL,
                            `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            Foreign Key (taskId) REFERENCES tasks(id) ON DELETE CASCADE,
                            Foreign Key (userId) REFERENCES users(id) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `projects`
--

CREATE TABLE `projects` (
                            `id` varchar(255) NOT NULL,
                            `name` varchar(150) NOT NULL,
                            `description` text,
                            `managerId` varchar(255) NOT NULL,
                            `status` ENUM('TODO', 'IN PROGRESS', 'DONE') DEFAULT 'TODO',
                            `startDate` date DEFAULT NULL,
                            `endDate` date DEFAULT NULL,
                            Foreign Key (managerId) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tasks`
--

CREATE TABLE `tasks` (
                         `id` varchar(255) NOT NULL,
                         `title` varchar(150) NOT NULL,
                         `description` text,
                         `status` ENUM('TODO', 'IN PROGRESS', 'DONE') DEFAULT 'TODO',
                         `dueDate` date DEFAULT NULL,
                         `projectId` varchar(255) NOT NULL,
                         `assignedTo` varchar(255) NOT NULL,
                         Foreign Key (assignedTo) REFERENCES users(id) ON DELETE CASCADE,
                         Foreign Key (projectId) REFERENCES projects(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
                         `id` varchar(255) NOT NULL,
                         `surname` varchar(100) NOT NULL,
                         `name` varchar(100) NOT NULL,
                         `passwordHash` varchar(255) NOT NULL,
                         `role` ENUM('ADMIN','MANAGER','EMPLOYEE') NOT NULL,
                         `email` varchar(150) NOT NULL,
                         `dateOfBirth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comments`
--
ALTER TABLE `comments`
    ADD PRIMARY KEY (`id`);

--
-- Index pour la table `projects`
--
ALTER TABLE `projects`
    ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tasks`
--
ALTER TABLE `tasks`
    ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
