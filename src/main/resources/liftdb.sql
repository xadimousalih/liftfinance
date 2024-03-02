-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Oct 17, 2023 at 10:02 AM
-- Server version: 5.7.43
-- PHP Version: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `liftdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `reference` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`id`, `description`, `name`, `reference`) VALUES
(1, 'VENTES MENSUELLES GLOBALE', 'VENTES MENSUELLES', 'REF1'),
(2, 'TOTAL ENCAISSEMENTS', 'TOTAL ENCAISSEMENTS', 'REF2'),
(3, 'ACHATS 2', 'ACHATS', 'REF3'),
(4, 'PAIEMENTS  FOURNISSEURS EFFECTUES', 'PAIEMENTS  FOURNISSEURS EFFECTUES', 'REF4'),
(5, 'CHARGES DE FONCTIONNEMENT', 'CHARGES DE FONCTIONNEMENT', 'REF5'),
(6, 'CHARGES DE PERSONNEL', 'CHARGES DE PERSONNEL', 'REF6'),
(7, 'INVESTISSEMENTS', 'INVESTISSEMENTS', 'REF7'),
(8, 'CHARGES FINANCIERES', 'CHARGES FINANCIERES', 'REF8'),
(9, 'CHARGES FISCALES', 'CHARGES FISCALES', 'REF9'),
(10, 'TRESORERIE', 'TRESORERIE', 'REF10'),
(11, 'TVA', 'TVA', 'REF11'),
(12, 'SITUATION A LA FIN DU MOIS', 'SITUATION A LA FIN DU MOIS', 'REF12');

-- --------------------------------------------------------

--
-- Table structure for table `ecriture`
--

CREATE TABLE `ecriture` (
  `id` bigint(20) NOT NULL,
  `created` date DEFAULT NULL,
  `disabled` date DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `mois` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `total_annee` double DEFAULT NULL,
  `total_global` double DEFAULT NULL,
  `total_mensuel` double DEFAULT NULL,
  `categorie_id` bigint(20) DEFAULT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `entreprise`
--

CREATE TABLE `entreprise` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `agedudirigent` int(11) NOT NULL,
  `chiffre_affaire` double NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `disabled` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ninea` varchar(255) DEFAULT NULL,
  `nom_localisation` varchar(255) DEFAULT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `secteur_activite_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entreprise`
--

INSERT INTO `entreprise` (`id`, `adresse`, `agedudirigent`, `chiffre_affaire`, `code`, `created`, `disabled`, `email`, `latitude`, `longitude`, `name`, `ninea`, `nom_localisation`, `pays`, `region`, `telephone`, `secteur_activite_id`) VALUES
(1, 'Rue marchand X Autoroute', 0, 10000000, 'TAB28', '2004-12-04', NULL, 'contact@tb28.com', 0, 0, 'TAB28 ARCHITECTURES LOGICIELLES', NULL, NULL, NULL, 'Dakar', NULL, NULL),
(2, 'Rue marchand X Autoroute', 0, 0, 'CABD', '2020-04-04', NULL, 'contact@cabd.com', 0, 0, 'Cheikh Ahmadou Bamba DIOP', NULL, NULL, 'Afghanistan', 'Dakar', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` bigint(20) NOT NULL,
  `created` date DEFAULT NULL,
  `disabled` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `entreprise_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `created`, `disabled`, `email`, `enabled`, `first_name`, `last_name`, `login`, `password`, `telephone`, `entreprise_id`) VALUES
(1, '2019-05-01', NULL, 'ceo@lift.com', b'1', 'Issa', 'GUEYE', 'pmanager', '$2a$10$aK81fm.BSg13nxvjEKcEfeD/3cCphRLVCHiQgyhvX1tHYLkH309RC', '776514240', 1),
(2, '2019-05-01', NULL, 'cto@lift.com', b'1', 'Khadim', 'DIOP', 'tmanager', '$2a$10$aK81fm.BSg13nxvjEKcEfeD/3cCphRLVCHiQgyhvX1tHYLkH309RC', '774238962', 2);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(1, 'PRODUCT MANAGER', 'ROLE_PRODUCT_MANAGER'),
(2, 'MANAGER', 'ROLE_MANAGER');

-- --------------------------------------------------------

--
-- Table structure for table `secteur_activite`
--

CREATE TABLE `secteur_activite` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type_entreprise_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `secteur_activite`
--

INSERT INTO `secteur_activite` (`id`, `code`, `name`, `type_entreprise_id`) VALUES
(1, 'PHAR', 'PHARMACIE', 3),
(2, 'TA', 'TRANSFERT ARGENT', 2);

-- --------------------------------------------------------

--
-- Table structure for table `type_entreprise`
--

CREATE TABLE `type_entreprise` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_entreprise`
--

INSERT INTO `type_entreprise` (`id`, `code`, `description`, `name`) VALUES
(2, 'TE1', 'ENTREPRISES DE SERVICES', 'SERVICES'),
(3, 'TE2', 'ENTREPRISES COMMERCIALES', 'COMMERCIALES'),
(4, 'TE3', 'AGRICULTURE ET ÉLEVAGE', 'AGRICULTURE ET ÉLEVAGE'),
(5, 'TE4', 'ARCHITECTURE ET ÉTUDE', 'ARCHITECTURE ET ÉTUDE'),
(6, 'TE5', 'ARTISANAT ART', 'ARTISANAT ART'),
(7, 'TE6', 'AUDIOVISUEL ET SPECTACLE', 'AUDIOVISUEL ET SPECTACLE'),
(8, 'TE7', 'AUTOMOBILE', 'AUTOMOBILE'),
(9, 'TE8', 'BÂTIMENT ET TRAVAUX PUBLICS (BTP)', 'BÂTIMENT ET TRAVAUX PUBLICS (BTP)'),
(10, 'TE9', 'COMMERCE ET DISTRIBUTION', 'COMMERCE ET DISTRIBUTION'),
(11, 'TE10', 'COMMUNICATION ET MARKETING', 'COMMUNICATION ET MARKETING'),
(12, 'TE11', 'COMPTABILITÉ GESTION ET RESSOURCES HUMAINES', 'COMPTABILITÉ GESTION ET RESSOURCES HUMAINES'),
(13, 'TE12', 'CULTURE ET PATRIMOINE', 'CULTURE ET PATRIMOINE'),
(14, 'TE13', 'DÉFENSE ET SÉCURITÉ', 'DÉFENSE ET SÉCURITÉ'),
(15, 'TE14', 'DROIT ET JUSTICE', 'DROIT ET JUSTICE'),
(16, 'TE15', 'ENSEIGNEMENT ET FORMATION', 'ENSEIGNEMENT ET FORMATION'),
(17, 'TE16', 'ENVIRONNEMENT', 'ENVIRONNEMENT'),
(18, 'TE17', 'FINANCE BANQUE ET ASSURANCE', 'FINANCE BANQUE ET ASSURANCE'),
(19, 'TE18', 'HÔTELLERIE ET RESTAURATION', 'HÔTELLERIE ET RESTAURATION'),
(20, 'TE19', 'IMMOBILIER', 'IMMOBILIER'),
(21, 'TE20', 'INDUSTRIE - ALIMENTAIRE', 'INDUSTRIE - ALIMENTAIRE'),
(22, 'TE21', 'INDUSTRIE - BOIS', 'INDUSTRIE - BOIS'),
(23, 'TE22', 'INDUSTRIE - CHIMIE', 'INDUSTRIE - CHIMIE'),
(24, 'TE23', 'INDUSTRIE - ÉLECTRONIQUE', 'INDUSTRIE - ÉLECTRONIQUE'),
(25, 'TE24', 'INDUSTRIE - IMPRIMERIE', 'INDUSTRIE - IMPRIMERIE'),
(26, 'TE25', 'INDUSTRIE - MÉTALLURGIE', 'INDUSTRIE - MÉTALLURGIE'),
(27, 'TE26', 'INDUSTRIE - TEXTILE', 'INDUSTRIE - TEXTILE'),
(28, 'TE27', 'INDUSTRIES', 'INDUSTRIES'),
(29, 'TE28', 'INFORMATIQUE, INTERNET ET TÉLÉCOMMUNICATION', 'INFORMATIQUE, INTERNET ET TÉLÉCOMMUNICATION'),
(30, 'TE29', 'JOURNALISME ET ÉDITION', 'JOURNALISME ET ÉDITION'),
(31, 'TE30', 'LOGISTIQUE ET TRANSPORT', 'LOGISTIQUE ET TRANSPORT'),
(32, 'TE31', 'MAINTENANCE ET ENTRETIEN', 'MAINTENANCE ET ENTRETIEN'),
(33, 'TE32', 'MODE', 'MODE'),
(34, 'TE33', 'RECHERCHE', 'RECHERCHE'),
(35, 'TE34', 'SANTÉ', 'SANTÉ'),
(36, 'TE35', 'SERVICE À LA COLLECTIVITÉ ET SERVICE PUBLIC', 'SERVICE À LA COLLECTIVITÉ ET SERVICE PUBLIC'),
(37, 'TE36', 'SERVICE À LA PERSONNE', 'SERVICE À LA PERSONNE'),
(38, 'TE37', 'SOCIAL', 'SOCIAL'),
(39, 'TE38', 'SPORT ET ANIMATION', 'SPORT ET ANIMATION'),
(40, 'TE39', 'TOURISME', 'TOURISME');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_g0q7wt2eac72pdh1knn9upk6b` (`name`),
  ADD UNIQUE KEY `UK_qp7hsc2t6c44awoxiewuietp2` (`reference`);

--
-- Indexes for table `ecriture`
--
ALTER TABLE `ecriture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbyf7atr4xaaywo54jdlwxnfn7` (`categorie_id`),
  ADD KEY `FK60esewe2m3msk4wb3805npn1y` (`entreprise_id`);

--
-- Indexes for table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46h9dunf9egoxgdgshke4vmrh` (`secteur_activite_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg8itkda3sh0j8iw8tw03okg0p` (`entreprise_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `secteur_activite`
--
ALTER TABLE `secteur_activite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1vtpt82y4lklan5audel3ajp3` (`type_entreprise_id`);

--
-- Indexes for table `type_entreprise`
--
ALTER TABLE `type_entreprise`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `ecriture`
--
ALTER TABLE `ecriture`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `secteur_activite`
--
ALTER TABLE `secteur_activite`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `type_entreprise`
--
ALTER TABLE `type_entreprise`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
