CREATE TABLE `categories` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_category`)
);


CREATE TABLE `expenses` (
  `id_expense` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `date` date NOT NULL,
  `id_category` int NOT NULL,
  PRIMARY KEY (`id_expense`)
);

ALTER TABLE `expenses`
ADD CONSTRAINT `id_category`
  FOREIGN KEY (`id_category`)
  REFERENCES `categories` (`id_category`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
