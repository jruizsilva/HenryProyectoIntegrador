CREATE TABLE `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_category`))

 CREATE TABLE `expense` (
   `id_expense` int NOT NULL,
   `id_category` int NOT NULL,
   `amount` double NOT NULL,
   `date` date DEFAULT NULL,
   PRIMARY KEY (`id_expense`),
   KEY `fk_id_category_expense_idx` (`id_category`),
   CONSTRAINT `fk_id_category_expense` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
 )
