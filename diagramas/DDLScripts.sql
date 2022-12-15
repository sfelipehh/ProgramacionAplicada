-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema db_spring_example
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db_spring_example` ;

-- -----------------------------------------------------
-- Schema db_spring_example
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_spring_example` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_spring_example` ;

-- -----------------------------------------------------
-- Table `db_spring_example`.`sede`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`sede` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`sede` (
  `id` BIGINT NOT NULL,
  `direccion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`cuadrilla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`cuadrilla` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`cuadrilla` (
  `id` BIGINT NOT NULL,
  `cantidad_empleados` BIGINT NULL DEFAULT NULL,
  `cupo_asignado` BIGINT NULL DEFAULT NULL,
  `cupo_restante` BIGINT NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `sede_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKbhv68wqk4k1gd4aj8h2qe71k7` (`sede_id` ASC) VISIBLE,
  CONSTRAINT `FKbhv68wqk4k1gd4aj8h2qe71k7`
    FOREIGN KEY (`sede_id`)
    REFERENCES `db_spring_example`.`sede` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`empleado` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`empleado` (
  `id` BIGINT NOT NULL,
  `dni` BIGINT NULL DEFAULT NULL,
  `apellidos` VARCHAR(255) NULL DEFAULT NULL,
  `celular` VARCHAR(255) NULL DEFAULT NULL,
  `cupo_asignado` BIGINT NULL DEFAULT NULL,
  `cupo_restante` BIGINT NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `fecha_nacimiento` VARCHAR(255) NULL DEFAULT NULL,
  `nombres` VARCHAR(255) NULL DEFAULT NULL,
  `cuadrilla_id` BIGINT NULL DEFAULT NULL,
  `sede_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK5hvjp1ihk6m5otvejmue2tnq9` (`cuadrilla_id` ASC) VISIBLE,
  INDEX `FKm44ircqfmj89obg7y41cpxp26` (`sede_id` ASC) VISIBLE,
  CONSTRAINT `FK5hvjp1ihk6m5otvejmue2tnq9`
    FOREIGN KEY (`cuadrilla_id`)
    REFERENCES `db_spring_example`.`cuadrilla` (`id`),
  CONSTRAINT `FKm44ircqfmj89obg7y41cpxp26`
    FOREIGN KEY (`sede_id`)
    REFERENCES `db_spring_example`.`sede` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`administrador_sede`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`administrador_sede` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`administrador_sede` (
  `id` BIGINT NOT NULL,
  `empleado_id` BIGINT NULL DEFAULT NULL,
  `sede_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKa04s0n1agdhfw236jw8gxo4or` (`empleado_id` ASC) VISIBLE,
  INDEX `FKhr7req9g0x58h4k6whin6f5uy` (`sede_id` ASC) VISIBLE,
  CONSTRAINT `FKa04s0n1agdhfw236jw8gxo4or`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `db_spring_example`.`empleado` (`id`),
  CONSTRAINT `FKhr7req9g0x58h4k6whin6f5uy`
    FOREIGN KEY (`sede_id`)
    REFERENCES `db_spring_example`.`sede` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`administrador_sede_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`administrador_sede_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`administrador_sede_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`cuadrilla_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`cuadrilla_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`cuadrilla_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`empleado_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`empleado_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`empleado_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`localidad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`localidad` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`localidad` (
  `id` BIGINT NOT NULL,
  `calle_fin` VARCHAR(255) NULL DEFAULT NULL,
  `calle_inicio` VARCHAR(255) NULL DEFAULT NULL,
  `carrera_fin` VARCHAR(255) NULL DEFAULT NULL,
  `carrera_inicio` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `cuadrilla_id` BIGINT NULL DEFAULT NULL,
  `sede_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKfhlmkgixep14dk6bodc1gmolp` (`cuadrilla_id` ASC) VISIBLE,
  INDEX `FKi4xlw1kjeiu65kmt5nbjjea` (`sede_id` ASC) VISIBLE,
  CONSTRAINT `FKfhlmkgixep14dk6bodc1gmolp`
    FOREIGN KEY (`cuadrilla_id`)
    REFERENCES `db_spring_example`.`cuadrilla` (`id`),
  CONSTRAINT `FKi4xlw1kjeiu65kmt5nbjjea`
    FOREIGN KEY (`sede_id`)
    REFERENCES `db_spring_example`.`sede` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`evento_de_gasto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`evento_de_gasto` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`evento_de_gasto` (
  `id` BIGINT NOT NULL,
  `aprobado` BIT(1) NOT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `fecha` VARCHAR(255) NULL DEFAULT NULL,
  `hora` VARCHAR(255) NULL DEFAULT NULL,
  `valor` BIGINT NULL DEFAULT NULL,
  `cuadrilla_id` BIGINT NULL DEFAULT NULL,
  `empleado_id` BIGINT NULL DEFAULT NULL,
  `localidad_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK43rdyr118tburx4fgrepd1uak` (`cuadrilla_id` ASC) VISIBLE,
  INDEX `FKta9goh8w5yb5h4vsl3557bun3` (`empleado_id` ASC) VISIBLE,
  INDEX `FK3lsacs6w181jwlfkk8qsboutu` (`localidad_id` ASC) VISIBLE,
  CONSTRAINT `FK3lsacs6w181jwlfkk8qsboutu`
    FOREIGN KEY (`localidad_id`)
    REFERENCES `db_spring_example`.`localidad` (`id`),
  CONSTRAINT `FK43rdyr118tburx4fgrepd1uak`
    FOREIGN KEY (`cuadrilla_id`)
    REFERENCES `db_spring_example`.`cuadrilla` (`id`),
  CONSTRAINT `FKta9goh8w5yb5h4vsl3557bun3`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `db_spring_example`.`empleado` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`evento_de_gasto_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`evento_de_gasto_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`evento_de_gasto_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`localidad_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`localidad_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`localidad_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`perfil` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`perfil` (
  `id` BIGINT NOT NULL,
  `consulta` BIT(1) NULL DEFAULT NULL,
  `registro` BIT(1) NULL DEFAULT NULL,
  `revision` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`perfil_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`perfil_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`perfil_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`sede_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`sede_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`sede_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`supervisor_cuadrilla`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`supervisor_cuadrilla` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`supervisor_cuadrilla` (
  `id` BIGINT NOT NULL,
  `cuadrilla_id` BIGINT NULL DEFAULT NULL,
  `empleado_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK45k8mc80y5tvtki5qu2k6qaf5` (`cuadrilla_id` ASC) VISIBLE,
  INDEX `FK40m96lxa59blgj06tl9hpvn8e` (`empleado_id` ASC) VISIBLE,
  CONSTRAINT `FK40m96lxa59blgj06tl9hpvn8e`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `db_spring_example`.`empleado` (`id`),
  CONSTRAINT `FK45k8mc80y5tvtki5qu2k6qaf5`
    FOREIGN KEY (`cuadrilla_id`)
    REFERENCES `db_spring_example`.`cuadrilla` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`supervisor_cuadrilla_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`supervisor_cuadrilla_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`supervisor_cuadrilla_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`usuario` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`usuario` (
  `id` BIGINT NOT NULL,
  `nombre_usuario` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `empleado_id` BIGINT NULL DEFAULT NULL,
  `perfil_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKjn24nnj8w0mmt2eq54ol6xrq2` (`empleado_id` ASC) VISIBLE,
  INDEX `FK9po12ytp6krwvwht1kmd0qgxf` (`perfil_id` ASC) VISIBLE,
  CONSTRAINT `FK9po12ytp6krwvwht1kmd0qgxf`
    FOREIGN KEY (`perfil_id`)
    REFERENCES `db_spring_example`.`perfil` (`id`),
  CONSTRAINT `FKjn24nnj8w0mmt2eq54ol6xrq2`
    FOREIGN KEY (`empleado_id`)
    REFERENCES `db_spring_example`.`empleado` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_spring_example`.`usuario_seq`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_spring_example`.`usuario_seq` ;

CREATE TABLE IF NOT EXISTS `db_spring_example`.`usuario_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
