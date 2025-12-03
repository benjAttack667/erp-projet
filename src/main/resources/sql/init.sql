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
(2, 3, 'Je vais commencer cette tâche demain');