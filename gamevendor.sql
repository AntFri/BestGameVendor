-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 23. Feb 2023 um 20:32
-- Server-Version: 10.4.27-MariaDB
-- PHP-Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `gamevendor`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `cellingwebsite`
--

CREATE TABLE `cellingwebsite` (
  `idWebsite` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `link` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `cellingwebsite`
--

INSERT INTO Sellingwebsite (`idWebsite`, `nombre`, `link`) VALUES
(1, 'MMOGA', 'https://www.mmoga.es/?gclid=CjwKCAiA85efBhBbEiwAD7oLQJbJmTO1ZtVTDFDRNmJPZwL6aAa2AZDuifAm0WMheHsao1hzi1nVMxoC4-kQAvD_BwE'),
(2, 'Instant Gaming', 'https://www.instant-gaming.com/de/?igr=gamer-cb871fb&gclid=CjwKCAiA85efBhBbEiwAD7oLQDmWP9mDnmk4X8cdQw0CTlX1UYXQfmtBAZv0Q3lXgi5xfYyvjwwPNxoCzOQQAvD_BwE'),
(3, 'Steam', 'https://store.steampowered.com/'),
(4, 'G2A', 'https://www.g2a.com/de/?adid=GA-ES_PB_NGAM_SN_BRAND-English&id=35&gclid=CjwKCAiA85efBhBbEiwAD7oLQE0ehb-EVXMfJavKVN1CGSUtnyucYmSukglCOqvHs0Pxe8v7poSPQhoCWVAQAvD_BwE&gclsrc=aw.ds'),
(5, 'Kinguin', 'https://www.kinguin.net/?gclid=CjwKCAiA85efBhBbEiwAD7oLQEdNUTq00jerrzG4EfwuAAPEF4Ef-Vys06BjHp_EcLaItrVKnyslcxoCrCMQAvD_BwE');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `genre`
--

CREATE TABLE `genre` (
  `idGenre` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `genre`
--

INSERT INTO `genre` (`idGenre`, `nombre`, `Description`) VALUES
(1, 'Horror', 'Horrar games that can scare people'),
(2, 'Strategie', 'Games with strategies, city planing'),
(3, 'Simulation Racing', 'Racing games that are very realistic'),
(4, 'Arcade Racing', 'Racing games that are more casual/arcade like'),
(5, 'Shooter', 'Games in where people shoot with a weapon'),
(6, 'Multyplayer', 'Games where people play togueter');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lista_favoritos`
--

CREATE TABLE `lista_favoritos` (
  `idCarrito_favoritos` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  `productos_idProductos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `lista_favoritos`
--

INSERT INTO `lista_favoritos` (`idCarrito_favoritos`, `nombre`, `usuario_idUsuario`, `productos_idProductos`) VALUES
(1, 'mifav', 7, 2),
(3, 'mifav', 7, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lista_rebajasproductos`
--

CREATE TABLE `lista_rebajasproductos` (
  `idListaRebajas` int(11) NOT NULL,
  `Fecha_Cambio` date DEFAULT NULL,
  `PrecioRebajas` float DEFAULT NULL,
  `cellingwebsite_idWebsite` int(11) NOT NULL,
  `productos_idProductos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `lista_rebajasproductos`
--

INSERT INTO `lista_rebajasproductos` (`idListaRebajas`, `Fecha_Cambio`, `PrecioRebajas`, `cellingwebsite_idWebsite`, `productos_idProductos`) VALUES
(1, '2020-02-20', 31.99, 2, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `plataformas`
--

CREATE TABLE `plataformas` (
  `idPlataformas` int(11) NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `FechaSalida` datetime DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `plataformas`
--

INSERT INTO `plataformas` (`idPlataformas`, `Nombre`, `FechaSalida`, `Descripcion`) VALUES
(1, 'Nintendo Switch', '2017-01-01 20:10:04', 'Portable Game console'),
(2, 'PlayStation 5', '2020-01-01 20:10:04', 'Sony fix console'),
(3, 'Xbox Series X/S', '2020-01-01 20:13:11', 'Microsoft Fix console'),
(4, 'PC-Steam', '2003-09-12 20:13:11', 'PC platform from Valve'),
(5, 'PC-Origin_EA', '2011-06-03 20:15:18', 'PC platform from Elotronics Arts'),
(6, 'Ubisoft Connect', '2009-11-17 20:15:18', 'PC paltform from Ubisoft');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `plataformas_has_productos`
--

CREATE TABLE `plataformas_has_productos` (
  `fk_Plataformas` int(11) NOT NULL,
  `fk_Productos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `productos`
--

CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `edadMinima` int(2) NOT NULL,
  `Photo_Producto` varchar(200) DEFAULT NULL,
  `Descripcion` varchar(400) DEFAULT NULL,
  `FechaSalida` datetime DEFAULT NULL,
  `precioSalida` float DEFAULT NULL,
  `publisher_idPublisher` int(11) NOT NULL,
  `genre_idGenre` int(11) NOT NULL,
  `region_idRegion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `productos`
--

INSERT INTO `productos` (`idProductos`, `nombre`, `edadMinima`, `Photo_Producto`, `Descripcion`, `FechaSalida`, `precioSalida`, `publisher_idPublisher`, `genre_idGenre`, `region_idRegion`) VALUES
(1, 'Daying light 2: Stay Human', 18, '/img/Dying_Light_2.jpg', 'Dying Light 2 es un videojuego de mundo abierto en primera persona que se sitúa en un mundo postapocalíptico en el que se ha sido víctima de una epidemia zombi. Al igual que la primera entrega, el jugador cuenta con varias habilidades del parkour, pudiendo escalar salientes, deslizarse, saltar desde cornisas y correr por la pared para poder desplazarse más rápidamente.', '2022-02-04 20:32:37', 59.99, 4, 1, 1),
(2, 'Dying light ', 18, '/img/Dying_Light_cover.jpg', 'Dying Light es un juego con un amplio y peligroso mundo abierto ubicado en la ciudad ficticia de Harran. Durante el día los jugadores podrán explorar un amplio ambiente urbano y expansivo contaminado por un insidioso virus, también podrán colocar las trampas y a su vez podrán buscar suministros en el día tanto como en la noche, salvar a los supervivientes encontrados.', '2015-01-27 20:32:37', 59.99, 4, 1, 1),
(3, 'New World', 16, '/img/new_world.jpeg', 'New World es un videojuego de rol multijugador masivo en línea desarrollado por Amazon Game Studios. Ambientado a mediados del siglo XVII, los jugadores tendrán la tarea de colonizar tierras modeladas a imagen y semejanza de la América Británica oriental', '2021-09-28 20:39:56', 59.99, 4, 6, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `publisher`
--

CREATE TABLE `publisher` (
  `idPublisher` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `fechaInauguracion` datetime DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Origen_Contry` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `publisher`
--

INSERT INTO `publisher` (`idPublisher`, `nombre`, `fechaInauguracion`, `Descripcion`, `Origen_Contry`) VALUES
(1, '11 bit studios', '2010-01-01 19:57:59', 'video game developer, marketer, and distribut', 'Warsaw, Poland'),
(2, '2K Games', '2005-01-01 19:57:59', 'video game marketer and distributor subsidia', 'Novato, California, United States'),
(3, 'Activision', '1979-01-01 20:05:41', 'video game developer acquired Neversoft in 19', 'Santa Monica, California, United States  '),
(4, 'Amazon Games', '2012-01-01 20:05:41', 'software publishing arm of Amazon', 'Seattle, Washington, United States');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `region`
--

CREATE TABLE `region` (
  `idRegion` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `desciption` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `region`
--

INSERT INTO `region` (`idRegion`, `nombre`, `desciption`) VALUES
(1, 'EU', 'Europen Union'),
(2, 'USA', 'United States of America');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `review`
--

CREATE TABLE `review` (
  `idReview` int(11) NOT NULL,
  `Review_text` varchar(500) DEFAULT NULL,
  `Review_rating` float DEFAULT NULL,
  `fk_User` int(11) NOT NULL,
  `fk_Products` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `region_idRegion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Daten für Tabelle `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `Nombre`, `Apellido`, `Email`, `FechaNacimiento`, `Password`, `region_idRegion`) VALUES
(2, 'pepe', 'rorento', 'pepe@torento.com', '2013-01-01', 'pepe', 2),
(6, 'ab', 'abc', 'ab@gmail.com', '2023-01-30', '$2a$10$ZX5a1gEFC2EMJImebhbsh.EaKdhi0M0nkTOjyRYaC.VZqeDTQBFwG', 1),
(7, 'dani', 'dani', 'dani@dani.com', '2023-01-31', '$2a$10$YVxN1JV6SKPy.AOXTWupuOaSJwXS3uDvxyXHIF.9k5E3J/l/hhSwy', 1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `cellingwebsite`
--
ALTER TABLE Sellingwebsite
  ADD PRIMARY KEY (`idWebsite`);

--
-- Indizes für die Tabelle `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`idGenre`);

--
-- Indizes für die Tabelle `lista_favoritos`
--
ALTER TABLE `lista_favoritos`
  ADD PRIMARY KEY (`idCarrito_favoritos`),
  ADD KEY `fk_Carrito_favoritos_Usuario1_idx` (`usuario_idUsuario`),
  ADD KEY `fk_Carrito_favoritos_Productos(videojuagos)1_idx` (`productos_idProductos`);

--
-- Indizes für die Tabelle `lista_rebajasproductos`
--
ALTER TABLE `lista_rebajasproductos`
  ADD PRIMARY KEY (`idListaRebajas`),
  ADD KEY `fk_Lista_preciosBajos_celling_Website1_idx` (`cellingwebsite_idWebsite`),
  ADD KEY `fk_Lista_preciosBajos_Productos(videojuagos)1_idx` (`productos_idProductos`);

--
-- Indizes für die Tabelle `plataformas`
--
ALTER TABLE `plataformas`
  ADD PRIMARY KEY (`idPlataformas`);

--
-- Indizes für die Tabelle `plataformas_has_productos`
--
ALTER TABLE `plataformas_has_productos`
  ADD PRIMARY KEY (`fk_Plataformas`,`fk_Productos`),
  ADD KEY `fk_Plataformas_has_Productos(videojuagos)_Productos(videoju_idx` (`fk_Productos`),
  ADD KEY `fk_Plataformas_has_Productos(videojuagos)_Plataformas1_idx` (`fk_Plataformas`);

--
-- Indizes für die Tabelle `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProductos`),
  ADD KEY `fk_Productos(videojuagos)_Publisher(Desenvolupador)1_idx` (`publisher_idPublisher`),
  ADD KEY `fk_Productos(videojuagos)_Genero(genre)1_idx` (`genre_idGenre`),
  ADD KEY `fk_Productos_Region1_idx` (`region_idRegion`);

--
-- Indizes für die Tabelle `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`idPublisher`);

--
-- Indizes für die Tabelle `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`idRegion`);

--
-- Indizes für die Tabelle `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`idReview`),
  ADD KEY `fk_Review_Usuario1_idx` (`fk_User`),
  ADD KEY `fk_Review_Productos1_idx` (`fk_Products`);

--
-- Indizes für die Tabelle `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `fk_Usuario_Region1_idx` (`region_idRegion`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `cellingwebsite`
--
ALTER TABLE Sellingwebsite
  MODIFY `idWebsite` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `genre`
--
ALTER TABLE `genre`
  MODIFY `idGenre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `lista_favoritos`
--
ALTER TABLE `lista_favoritos`
  MODIFY `idCarrito_favoritos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `lista_rebajasproductos`
--
ALTER TABLE `lista_rebajasproductos`
  MODIFY `idListaRebajas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `plataformas`
--
ALTER TABLE `plataformas`
  MODIFY `idPlataformas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `productos`
--
ALTER TABLE `productos`
  MODIFY `idProductos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `publisher`
--
ALTER TABLE `publisher`
  MODIFY `idPublisher` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `region`
--
ALTER TABLE `region`
  MODIFY `idRegion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `review`
--
ALTER TABLE `review`
  MODIFY `idReview` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `lista_favoritos`
--
ALTER TABLE `lista_favoritos`
  ADD CONSTRAINT `fk_Carrito_favoritos_Productos(videojuagos)1` FOREIGN KEY (`productos_idProductos`) REFERENCES `productos` (`idProductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Carrito_favoritos_Usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `lista_rebajasproductos`
--
ALTER TABLE `lista_rebajasproductos`
  ADD CONSTRAINT `fk_Lista_preciosBajos_Productos(videojuagos)1` FOREIGN KEY (`productos_idProductos`) REFERENCES `productos` (`idProductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Lista_preciosBajos_celling_Website1` FOREIGN KEY (`cellingwebsite_idWebsite`) REFERENCES Sellingwebsite (`idWebsite`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `plataformas_has_productos`
--
ALTER TABLE `plataformas_has_productos`
  ADD CONSTRAINT `fk_Plataformas_has_Productos(videojuagos)_Plataformas1` FOREIGN KEY (`fk_Plataformas`) REFERENCES `plataformas` (`idPlataformas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Plataformas_has_Productos(videojuagos)_Productos(videojuag1` FOREIGN KEY (`fk_Productos`) REFERENCES `productos` (`idProductos`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `fk_Productos(videojuagos)_Genero(genre)1` FOREIGN KEY (`genre_idGenre`) REFERENCES `genre` (`idGenre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Productos(videojuagos)_Publisher(Desenvolupador)1` FOREIGN KEY (`publisher_idPublisher`) REFERENCES `publisher` (`idPublisher`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Productos_Region1` FOREIGN KEY (`region_idRegion`) REFERENCES `region` (`idRegion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_Review_Productos1` FOREIGN KEY (`fk_Products`) REFERENCES `productos` (`idProductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Review_Usuario1` FOREIGN KEY (`fk_User`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_Usuario_Region1` FOREIGN KEY (`region_idRegion`) REFERENCES `region` (`idRegion`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
