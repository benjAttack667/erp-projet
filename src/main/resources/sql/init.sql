-- Création de la base de données CREATE DATABASE IF NOT EXISTS minierp_poo;
USE minierp_poo;
-- ====================== -- TABLE : users -- ====================== CREATE TABLE users (
    id VARCHAR PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','MANAGER','EMPLOYEE') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
-- ====================== -- TABLE : projects -- ====================== CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    manager_id INT NOT NULL,
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (manager_id) REFERENCES users(id)
);
-- ====================== -- TABLE : tasks -- ====================== CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    priority ENUM('LOW','MEDIUM','HIGH') DEFAULT 'MEDIUM',
    status ENUM('TODO','IN_PROGRESS','DONE') DEFAULT 'TODO',
    due_date DATE,
    project_id INT NOT NULL,
    assigned_to INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_to) REFERENCES users(id) ON DELETE CASCADE
);
-- ====================== -- TABLE : comments (BONUS) -- ====================== CREATE TABLE comments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task_id INT NOT NULL,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

                                                         -- Utilisateurs INSERT INTO users(username, password_hash, role)VALUES
('admin', 'adminhash', 'ADMIN'),
('manager1', 'managerhash', 'MANAGER'),
('employee1', 'employeehash', 'EMPLOYEE');
-- Projets INSERT INTO projects(name, description, manager_id, start_date, end_date)VALUES
('Projet POO', 'Mini ERP JavaFX', 2, '2025-12-01', '2026-01-15');
-- Tâches INSERT INTO tasks(title, description, priority, status, due_date, project_id, assigned_to)VALUES('Créer la structure MVC', 'Définir Models, DAO, Services', 'HIGH', 'IN_PROGRESS', '2025-12-05', 1, 3),
('Coder l’authentification', 'Login / Session / Rôles', 'MEDIUM', 'TODO', '2025-12-07', 1, 3);
-- Commentaires INSERT INTO comments(task_id, user_id, content)VALUES(1, 2, 'Vérifie la cohérence des classes DAO'),
(2, 3, 'Je vais commencer cette tâche demain')


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