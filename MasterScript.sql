-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ATTDraft
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ATTDraft
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ATTDraft` DEFAULT CHARACTER SET utf8 ;
USE `ATTDraft` ;

-- -----------------------------------------------------
-- Table `ATTDraft`.`Volunteer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ATTDraft`.`Volunteer` (
  `VolunteerID` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `PhoneNumber` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Street` VARCHAR(45) NULL,
  `City` VARCHAR(45) NULL,
  `State` VARCHAR(45) NULL,
  `Zip` INT NULL,
  `IsAdmin` TINYINT(1) NOT NULL,
  `TimeStamp` DATETIME NOT NULL,
  PRIMARY KEY (`VolunteerID`),
  UNIQUE INDEX `VolunteerID_UNIQUE` (`VolunteerID` ASC),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ATTDraft`.`VolunteerEntry`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ATTDraft`.`VolunteerEntry` (
  `VolunteerEntryID` INT NOT NULL,
  `Date` DATETIME NOT NULL,
  `ActivityType` VARCHAR(45) NOT NULL,
  `Length` DOUBLE NOT NULL,
  `Notes` VARCHAR(45) NULL,
  `VolunteerID` INT NOT NULL,
  PRIMARY KEY (`VolunteerEntryID`),
  UNIQUE INDEX `VolunteerEntryID_UNIQUE` (`VolunteerEntryID` ASC),
  UNIQUE INDEX `Date_UNIQUE` (`Date` ASC),
  UNIQUE INDEX `ActivityType_UNIQUE` (`ActivityType` ASC),
  INDEX `fk_VolunteerEntry_Volunteer_idx` (`VolunteerID` ASC),
  CONSTRAINT `fk_VolunteerEntry_Volunteer`
    FOREIGN KEY (`VolunteerID`)
    REFERENCES `ATTDraft`.`Volunteer` (`VolunteerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ATTDraft`.`VolunteerCredentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ATTDraft`.`VolunteerCredentials` (
  `VCredentialsID` INT NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `VolunteerID` INT NOT NULL,
  PRIMARY KEY (`VCredentialsID`),
  UNIQUE INDEX `VCredentialsID_UNIQUE` (`VCredentialsID` ASC),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC),
  UNIQUE INDEX `Password_UNIQUE` (`Password` ASC),
  INDEX `fk_VolunteerCredentials_Volunteer1_idx` (`VolunteerID` ASC),
  CONSTRAINT `fk_VolunteerCredentials_Volunteer1`
    FOREIGN KEY (`VolunteerID`)
    REFERENCES `ATTDraft`.`Volunteer` (`VolunteerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
