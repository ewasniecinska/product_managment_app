-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: product-3
-- Source Schemata: product
-- Created: Wed Apr 05 15:05:59 2017
-- Workbench Version: 6.3.8
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema product-3
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `product-3` ;
CREATE SCHEMA IF NOT EXISTS `product-3` ;

-- ----------------------------------------------------------------------------
-- Table product-3.invoices
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `product-3`.`invoices` (
  `ID_invoice` INT(11) NOT NULL,
  `name_invoice` VARCHAR(45) NULL DEFAULT NULL,
  `lastname_invoice` VARCHAR(45) NULL DEFAULT NULL,
  `city_invoice` VARCHAR(45) NULL DEFAULT NULL,
  `postcode_invoice` VARCHAR(45) NULL DEFAULT NULL,
  `street_invoice` VARCHAR(45) NULL DEFAULT NULL,
  `streetnumber_invoice` INT(11) NULL DEFAULT NULL,
  `date_invoice` DATE NULL DEFAULT NULL,
  `ID_order` INT(11) NOT NULL,
  `finalprice_invoice` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_invoice`),
  INDEX `fk_order_idx` (`ID_order` ASC),
  CONSTRAINT `fk_order`
    FOREIGN KEY (`ID_order`)
    REFERENCES `product-3`.`orderproducts` (`ID_orderproducts`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table product-3.orderproducts
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `product-3`.`orderproducts` (
  `ID_orderproducts` INT(11) NOT NULL,
  `ID_order` INT(11) NULL DEFAULT NULL,
  `ID_product` INT(11) NOT NULL,
  `quantity_order` INT(11) NULL DEFAULT NULL,
  `product_amount` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_orderproducts`),
  INDEX `fk_product_idx` (`ID_product` ASC),
  INDEX `fk_order_idx` (`ID_order` ASC),
  CONSTRAINT `fk_neworder`
    FOREIGN KEY (`ID_order`)
    REFERENCES `product-3`.`orders` (`ID_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product`
    FOREIGN KEY (`ID_product`)
    REFERENCES `product-3`.`products` (`ID_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table product-3.orders
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `product-3`.`orders` (
  `ID_order` INT(11) NOT NULL,
  `order_date` DATE NULL DEFAULT NULL,
  `name_order` VARCHAR(45) NULL DEFAULT NULL,
  `lastname_order` VARCHAR(45) NULL DEFAULT NULL,
  `shippeddate_order` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`ID_order`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table product-3.products
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `product-3`.`products` (
  `ID_product` INT(11) NOT NULL,
  `name_product` VARCHAR(45) NULL DEFAULT NULL,
  `quanitity_product` VARCHAR(45) NULL DEFAULT NULL,
  `unit_price` INT(11) NULL DEFAULT NULL,
  `ID_warehouse` INT(11) NOT NULL,
  PRIMARY KEY (`ID_product`),
  UNIQUE INDEX `name_product_UNIQUE` (`name_product` ASC),
  INDEX `fk_warehouse_idx` (`ID_warehouse` ASC),
  CONSTRAINT `fk_warehouse`
    FOREIGN KEY (`ID_warehouse`)
    REFERENCES `product-3`.`warehouses` (`ID_warehouse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table product-3.warehouses
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `product-3`.`warehouses` (
  `ID_warehouse` INT(11) NOT NULL,
  `name_warehouse` VARCHAR(45) NULL DEFAULT NULL,
  `city_warehouse` VARCHAR(45) NULL DEFAULT NULL,
  `postcode_warehouse` VARCHAR(6) NULL DEFAULT NULL,
  `street_warehouse` VARCHAR(45) NULL DEFAULT NULL,
  `code_warehouse` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_warehouse`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS = 1;
