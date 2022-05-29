-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecommercetesteyoube
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(80) NULL,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `senha` VARCHAR(256) NOT NULL,
  `salt` VARCHAR(45) NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_expiracao` DATETIME NULL,
  `data_desativacao` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_categoria_1_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `ecommercetesteyoube`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `descricao` VARCHAR(150) NOT NULL,
  `detalhes` TEXT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  `data_desativacao` DATETIME NULL,
  `categoria_id` INT NOT NULL,
  `marca_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_categoria_idx` (`categoria_id` ASC) VISIBLE,
  INDEX `fk_produto_marca_idx` (`marca_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `ecommercetesteyoube`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_marca`
    FOREIGN KEY (`marca_id`)
    REFERENCES `ecommercetesteyoube`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(80) NOT NULL,
  `sexo` VARCHAR(9) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `rg` VARCHAR(14) NOT NULL,
  `orgao_expedidor` VARCHAR(10) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `senha` VARCHAR(256) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `data_cadastro` DATETIME NOT NULL,
  `data_desativacao` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(60) NOT NULL,
  `logradouro` VARCHAR(150) NOT NULL,
  `numero` VARCHAR(20) NOT NULL,
  `complemento` VARCHAR(45) NULL,
  `bairro` VARCHAR(80) NOT NULL,
  `cidade` VARCHAR(80) NOT NULL,
  `estado` VARCHAR(2) NOT NULL,
  `cep` VARCHAR(10) NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_cliente_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ecommercetesteyoube`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`venda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NOT NULL,
  `endereco_entrega_id` INT NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  `data_cancelamento` DATETIME NULL,
  `descricao_cancelamento` VARCHAR(120) NULL,
  `descricao_interna` VARCHAR(150) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_venda_cliente_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_venda_endereco_idx` (`endereco_entrega_id` ASC) VISIBLE,
  CONSTRAINT `fk_venda_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `ecommercetesteyoube`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_endereco`
    FOREIGN KEY (`endereco_entrega_id`)
    REFERENCES `ecommercetesteyoube`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`item_venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`item_venda` (
  `produto_id` INT NOT NULL,
  `venda_id` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`produto_id`, `venda_id`),
  INDEX `fk_item_venda_2_idx` (`venda_id` ASC) VISIBLE,
  CONSTRAINT `fk_item_venda_1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommercetesteyoube`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_venda_2`
    FOREIGN KEY (`venda_id`)
    REFERENCES `ecommercetesteyoube`.`venda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`movimento _caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`movimento _caixa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(100) NOT NULL,
  `tipo_movimento` VARCHAR(15) NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `data_movimento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`imagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(45) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`pagamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `url` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`permissao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(120) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`usuario_permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`usuario_permissao` (
  `usuario_id` INT NOT NULL,
  `permissao_id` INT NOT NULL,
  PRIMARY KEY (`usuario_id`, `permissao_id`),
  INDEX `fk_usuario_permissao_2_idx` (`permissao_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_permissao_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `ecommercetesteyoube`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_permissao_2`
    FOREIGN KEY (`permissao_id`)
    REFERENCES `ecommercetesteyoube`.`permissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`produto_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`produto_tag` (
  `produto_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`produto_id`, `tag_id`),
  INDEX `fk_produto_tag_2_idx` (`tag_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_tag_1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommercetesteyoube`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_tag_2`
    FOREIGN KEY (`tag_id`)
    REFERENCES `ecommercetesteyoube`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommercetesteyoube`.`produto_imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommercetesteyoube`.`produto_imagem` (
  `produto_id` INT NOT NULL,
  `imagem_id` INT NOT NULL,
  PRIMARY KEY (`produto_id`, `imagem_id`),
  INDEX `fk_produto_imagem_1_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_imagem_1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommercetesteyoube`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_imagem_2`
    FOREIGN KEY (`imagem_id`)
    REFERENCES `ecommercetesteyoube`.`imagem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
