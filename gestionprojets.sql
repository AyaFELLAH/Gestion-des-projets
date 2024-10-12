-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 15, 2023 at 05:37 PM
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
-- Database: `gestionprojets`
--

create database gestionprojets;
use gestionprojets;
-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `cin` varchar(100) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `prenom` varchar(250) NOT NULL,
  `telephone` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`cin`, `nom`, `prenom`, `telephone`) VALUES
('de56498', 'Irikh ', 'Zanis', '6546513'),
('sx564', 'Morad', 'Kasmi', '065487465');

-- --------------------------------------------------------

--
-- Table structure for table `developpeur_technologie`
--

CREATE TABLE `developpeur_technologie` (
  `idDeveloppeur` int(11) NOT NULL,
  `idTechnologie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `developpeur_technologie`
--

INSERT INTO `developpeur_technologie` (`idDeveloppeur`, `idTechnologie`) VALUES
(7, 4),
(10, 4),
(8, 5),
(11, 5),
(10, 6),
(8, 7),
(7, 8),
(11, 8),
(8, 9),
(8, 10);

-- --------------------------------------------------------

--
-- Table structure for table `methodologie`
--

CREATE TABLE `methodologie` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `methodologie`
--

INSERT INTO `methodologie` (`id`, `nom`) VALUES
(1, 'Agile'),
(2, 'XP');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `dateCreation` date DEFAULT current_timestamp(),
  `message` varchar(300) NOT NULL,
  `idEmetteur` int(11) NOT NULL,
  `idRecepteur` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`id`, `dateCreation`, `message`, `idEmetteur`, `idRecepteur`, `status`) VALUES
(18, '2023-12-15', 'Vous avez affecté au projet ~ Project Alpha ~, vous avez 50 à partir de 2023-12-30', 6, 1, 0),
(19, '2023-12-15', 'Vous avez affecté au projet ~ Projet Beta ~, vous avez 20 à partir de 2023-12-17', 6, 1, 0),
(20, '2023-12-15', 'Vous avez affecté au projet ~ Projet Gamma ~, vous avez 35 à partir de 2023-12-29', 6, 1, 0),
(21, '2023-12-15', 'Vous avez affecté au projet ~ Projet Gamma 2033 ~, et vous avez une réunion le 2023-12-30', 1, 8, 1),
(22, '2023-12-15', 'Vous avez affecté au projet ~ Projet Gamma 2033 ~, et vous avez une réunion le 2023-12-30', 1, 11, 0);

-- --------------------------------------------------------

--
-- Table structure for table `projet`
--

CREATE TABLE `projet` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `nombreJours` int(100) NOT NULL,
  `dateDemarrage` date NOT NULL,
  `dateLivraison` date NOT NULL,
  `cin` varchar(100) NOT NULL,
  `idUser` int(11) NOT NULL,
  `methodologie` varchar(250) DEFAULT NULL,
  `dateReunion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `projet`
--

INSERT INTO `projet` (`id`, `name`, `description`, `nombreJours`, `dateDemarrage`, `dateLivraison`, `cin`, `idUser`, `methodologie`, `dateReunion`) VALUES
(31, 'Project Alpha', 'developpement d une application mobile de gestion des taches', 50, '2023-12-30', '2024-04-22', 'sx564', 1, NULL, NULL),
(33, 'Projet Gamma 2033', 'conception d un site web e-commerce pour une boutique de vetements', 20, '2023-12-13', '2024-02-10', 'de56498', 1, 'Agile', '2023-12-14');

-- --------------------------------------------------------

--
-- Table structure for table `projet_developpeur`
--

CREATE TABLE `projet_developpeur` (
  `idDeveloppeur` int(11) NOT NULL,
  `idProjet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `projet_developpeur`
--

INSERT INTO `projet_developpeur` (`idDeveloppeur`, `idProjet`) VALUES
(8, 33),
(11, 33);

-- --------------------------------------------------------

--
-- Table structure for table `projet_technologie`
--

CREATE TABLE `projet_technologie` (
  `idProjet` int(11) NOT NULL,
  `idTechnologie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `projet_technologie`
--

INSERT INTO `projet_technologie` (`idProjet`, `idTechnologie`) VALUES
(33, 5),
(33, 7);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL,
  `duree` int(11) NOT NULL,
  `idProjet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `nom`, `description`, `duree`, `idProjet`) VALUES
(4, 'front-end', 'creation des fichier html css and js, pour concevoir les intefaces clients ', 4, 33);

-- --------------------------------------------------------

--
-- Table structure for table `tache`
--

CREATE TABLE `tache` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL,
  `pourcentage` int(110) NOT NULL DEFAULT 0,
  `idService` int(11) NOT NULL,
  `idDeveloppeur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tache`
--

INSERT INTO `tache` (`id`, `nom`, `description`, `pourcentage`, `idService`, `idDeveloppeur`) VALUES
(5, 'html files', 'creations de html files', 5, 4, 8),
(6, 'js file', 'rendre les pages un peu reactifs', 37, 4, 11);

-- --------------------------------------------------------

--
-- Table structure for table `technologie`
--

CREATE TABLE `technologie` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `technologie`
--

INSERT INTO `technologie` (`id`, `nom`, `description`) VALUES
(4, '.Net', 'description de .net'),
(5, 'Php', 'description de php'),
(6, 'Ajax', 'description de ajax'),
(7, 'JEE', 'description de jee'),
(8, 'C++', 'description de c++'),
(9, 'Spring Boot', 'la gestion complete de backend'),
(10, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `prenom` varchar(200) NOT NULL,
  `login` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `login`, `password`, `role`) VALUES
(1, 'kharmichi', 'mohammed', 'mohammedkharmichi@gmail.com', '@123456', 'chefProject'),
(6, 'Fellah', 'Aya', 'ayafellah@gmail.com', '@123456', 'director'),
(7, 'Adb El Samad', 'Chahbi', 'abdchahbi@gmail.com', '@123456', 'devlopper'),
(8, 'Sanae', 'Karimi', 'sanaekahil@gmail.com', '@123456', 'devlopper'),
(9, 'Mahdi', 'Kharmichi', 'mahdi@gmail.com', '@123456', 'chefProject'),
(10, 'Hamid', 'Tofil', 'hamid@gmail.com', '@123456', 'devlopper'),
(11, 'Fatiha', 'Banani', 'fatiha@gmail.com', '@123456', 'devlopper');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`cin`);

--
-- Indexes for table `developpeur_technologie`
--
ALTER TABLE `developpeur_technologie`
  ADD PRIMARY KEY (`idTechnologie`,`idDeveloppeur`);

--
-- Indexes for table `methodologie`
--
ALTER TABLE `methodologie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `projet_client_pk` (`cin`),
  ADD KEY `projet_chef` (`idUser`);

--
-- Indexes for table `projet_developpeur`
--
ALTER TABLE `projet_developpeur`
  ADD PRIMARY KEY (`idProjet`,`idDeveloppeur`);

--
-- Indexes for table `projet_technologie`
--
ALTER TABLE `projet_technologie`
  ADD PRIMARY KEY (`idProjet`,`idTechnologie`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tache`
--
ALTER TABLE `tache`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `technologie`
--
ALTER TABLE `technologie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`,`prenom`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `methodologie`
--
ALTER TABLE `methodologie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `projet`
--
ALTER TABLE `projet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tache`
--
ALTER TABLE `tache`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `technologie`
--
ALTER TABLE `technologie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `projet`
--
ALTER TABLE `projet`
  ADD CONSTRAINT `projet_chef` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
