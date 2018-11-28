-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-11-2018 a las 16:33:55
-- Versión del servidor: 5.7.19
-- Versión de PHP: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `anakin`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

DROP TABLE IF EXISTS `actividad`;
CREATE TABLE IF NOT EXISTS `actividad` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `id_orientacion` int(11) NOT NULL,
  `Nombre` varchar(32) NOT NULL,
  `Plazas` int(11) NOT NULL,
  `Monitor` varchar(32) NOT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Nombre` (`Nombre`),
  KEY `id_orientacion` (`id_orientacion`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`Codigo`, `id_orientacion`, `Nombre`, `Plazas`, `Monitor`) VALUES
(1, 1, 'Petanca', 10, 'Yoda'),
(2, 1, 'Meditacion', 5, 'Windu'),
(8, 1, 'Bingo', 20, 'C3PO'),
(9, 2, 'www', 0, '11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orientacion`
--

DROP TABLE IF EXISTS `orientacion`;
CREATE TABLE IF NOT EXISTS `orientacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(16) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_orientacion` (`nombre`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `orientacion`
--

INSERT INTO `orientacion` (`id`, `nombre`) VALUES
(1, 'Jedi'),
(2, 'Sith');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`id_orientacion`) REFERENCES `orientacion` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
