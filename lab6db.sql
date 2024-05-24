-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lab6db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab6db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab6db` DEFAULT CHARACTER SET utf8 ;
USE `lab6db` ;

-- -----------------------------------------------------
-- Table `lab6db`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab6db`.`Rol` (
  `idRol` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab6db`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab6db`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `estado` TINYINT NOT NULL,
  `idRol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE INDEX `idUsuarios_UNIQUE` (`idUsuarios` ASC) VISIBLE,
  INDEX `fk_Usuarios_Rol_idx` (`idRol` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_Rol`
    FOREIGN KEY (`idRol`)
    REFERENCES `lab6db`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab6db`.`Mesas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab6db`.`Mesas` (
  `idMesas` INT NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `capacidad` INT NOT NULL,
  `Ubicación` VARCHAR(45) NOT NULL,
  `disponibilidad` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idMesas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab6db`.`Reservas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab6db`.`Reservas` (
  `idReservas` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  `idMesa` INT NOT NULL,
  `fechaInicio` DATETIME NOT NULL,
  `fechaFin` DATETIME NOT NULL,
  PRIMARY KEY (`idReservas`),
  INDEX `fk_Reservas_Usuarios1_idx` (`idUsuario` ASC) VISIBLE,
  INDEX `fk_Reservas_Mesas1_idx` (`idMesa` ASC) VISIBLE,
  CONSTRAINT `fk_Reservas_Usuarios1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `lab6db`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservas_Mesas1`
    FOREIGN KEY (`idMesa`)
    REFERENCES `lab6db`.`Mesas` (`idMesas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
