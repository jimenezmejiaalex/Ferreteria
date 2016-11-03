CREATE DATABASE  IF NOT EXISTS `Ferreteria` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Ferreteria`;
-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: Ferreteria
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `idCliente` varchar(45) NOT NULL,
  `nombreCliente` varchar(45) NOT NULL,
  `telefonoCliente` varchar(45) NOT NULL,
  `emailCliente` varchar(45) NOT NULL,
  `descuentoCliente` decimal(10,0) NOT NULL,
  PRIMARY KEY (`idCliente`,`nombreCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`),
  UNIQUE KEY `nombreCliente_UNIQUE` (`nombreCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Empleado`
--

DROP TABLE IF EXISTS `Empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Empleado` (
  `idEmpleado` varchar(45) NOT NULL,
  `nombreEmpleado` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `rollEmpleado` varchar(45) NOT NULL,
  PRIMARY KEY (`idEmpleado`,`nombreEmpleado`),
  UNIQUE KEY `idEmpleado_UNIQUE` (`idEmpleado`),
  UNIQUE KEY `nombreEmpleado_UNIQUE` (`nombreEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empleado`
--

LOCK TABLES `Empleado` WRITE;
/*!40000 ALTER TABLE `Empleado` DISABLE KEYS */;
INSERT INTO `Empleado` VALUES ('113045','Herrera Angulo','JesÃºs','Despachador'),('114977','Lopez Sandi','Riccardo','Administrador'),('121212','Walter Monterrosa','11111','Cajero'),('1222','elmer','1234','Administrador'),('1231','Salmon','123','Administrador'),('123123','Hernan Forlan Gomez','34566y','Despachador'),('12313233','Walter esteban Yoyo Worlber','ww123','Administrador'),('12321','qdede','1234','Cajero'),('135649','Granizo CalderÃ³n','Nashyra','Bodeguero'),('187919','VÃ¡squez Villalobos','Jeferson','Bodeguero'),('20023411','Alvaro Palomo Coto','123123123123','Cajero'),('207430562','Katherine Valverde Solis','123123','Administrador'),('223877','Quesada Jimenez','Luis','Bodeguero'),('23345','Hernan Torta Chilena','1234rt','Bodeguero'),('27332','Solano Alvarez','Felipe','Bodeguero'),('28213','GÃ³mez Quesada','Juan','Bodeguero'),('402250096','Alexander Jimenez Mejia','1234450','Cajero'),('4139169','Miguel Jimenez Araya','123123','Bodeguero'),('445ttyy7','Roy Mayer Hamilton','00000','Cajero'),('596799','Zamora Salas','Brandon','Bodeguero'),('626116','Ramirez Aguilar','Mario','Bodeguero'),('633596','Gonzalez Segura','JuliÃ¡n','Bodeguero'),('690477','Sandoval Fernandez','Raul','Estornudo'),('691748','Bove Saenz','Jean','Vendedor'),('72123','Hidalgo Hernandez','Allan','Administrador'),('725976','Navarro Chaves','Jose','Bodeguero'),('735323','Quesada Campos','Jose','Despachador'),('744680','Solano Paniagua','Diego','Bodeguero'),('780566','JimÃ©nez Palma','Francisco','Bodeguero'),('781867','JimÃ©nez Montoya','David','Vendedor'),('787178','Mora Mora','Jose','Vendedor'),('849632','Lasso Sanchez','Manuel','Vendedor'),('893774','GonzÃ¡lez Ugalde','Elizabeth','Despachador'),('fsdasf','asdfasdfas','123','Cajero'),('ï»¿80770','Barrantes Binns','Francini','Administrador'),('qwe','wq','123','Administrador'),('sdgdfsag','adgadf','123','Administrador'),('we','wqewq','123','Bodeguero');
/*!40000 ALTER TABLE `Empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Factura`
--

DROP TABLE IF EXISTS `Factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Factura` (
  `numeroFactura` varchar(45) NOT NULL,
  `fechaFactura` date NOT NULL,
  `horaFactura` varchar(45) NOT NULL,
  `nombreClient` varchar(45) NOT NULL,
  `nombreVendedor` varchar(45) NOT NULL,
  `idEmpleado` varchar(45) NOT NULL,
  `idCliente` varchar(45) NOT NULL,
  PRIMARY KEY (`numeroFactura`),
  KEY `factura-cliente_idx` (`nombreClient`),
  KEY `factura-empleado_idx` (`nombreVendedor`),
  KEY `factura-idCliente_idx` (`idCliente`),
  KEY `factura-idEmpleado_idx` (`idEmpleado`),
  CONSTRAINT `factura-nClient` FOREIGN KEY (`nombreClient`) REFERENCES `Cliente` (`nombreCliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `factura-nEmpleado` FOREIGN KEY (`nombreVendedor`) REFERENCES `Empleado` (`nombreEmpleado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Factura`
--

LOCK TABLES `Factura` WRITE;
/*!40000 ALTER TABLE `Factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `Factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductoCatalogo`
--

DROP TABLE IF EXISTS `ProductoCatalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductoCatalogo` (
  `idProducto` varchar(45) NOT NULL,
  `descripProducto` varchar(45) NOT NULL,
  `unidadMedida` varchar(45) NOT NULL,
  `precioUnitario` decimal(20,0) NOT NULL,
  PRIMARY KEY (`idProducto`,`descripProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductoCatalogo`
--

LOCK TABLES `ProductoCatalogo` WRITE;
/*!40000 ALTER TABLE `ProductoCatalogo` DISABLE KEYS */;
INSERT INTO `ProductoCatalogo` VALUES ('3456','Piedrilla','Metros cuadrados',10500);
/*!40000 ALTER TABLE `ProductoCatalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductoInventario`
--

DROP TABLE IF EXISTS `ProductoInventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductoInventario` (
  `idProducto` varchar(45) NOT NULL,
  `decripcionProducto` varchar(45) NOT NULL,
  `unidadMedida` varchar(45) NOT NULL,
  `precioUnitario` varchar(45) NOT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductoInventario`
--

LOCK TABLES `ProductoInventario` WRITE;
/*!40000 ALTER TABLE `ProductoInventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProductoInventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-03 16:58:00
