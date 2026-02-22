CREATE TABLE IF NOT EXISTS `spring_security`.`user` (
                                                        `username` VARCHAR(45) UNIQUE,
                                                        `password` TEXT NULL,
                                                        PRIMARY KEY (`username`));
CREATE TABLE IF NOT EXISTS `spring_security`.`otp` (
                                                       `username` VARCHAR(45) UNIQUE ,
                                                       `code` VARCHAR(45) NULL,
                                                       PRIMARY KEY (`username`));