CREATE DATABASE IF NOT EXISTS minierp;
USE minierp;

-- Users
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE,
                       password_hash VARCHAR(255),
                       role VARCHAR(20)
);

-- Projects
CREATE TABLE projects (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100),
                          description TEXT,
                          manager_id INT
);

-- Tasks
CREATE TABLE tasks (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100),
                       description TEXT,
                       status VARCHAR(20),
                       project_id INT,
                       assigned_to INT
);
