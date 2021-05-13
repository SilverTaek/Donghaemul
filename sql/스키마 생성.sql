DROP TABLE IF EXISTS `ssafy`.`favorite`;
DROP TABLE IF EXISTS `ssafy`.`ingredient_avg`;
DROP TABLE IF EXISTS `ssafy`.`ingredient_info`;
DROP TABLE IF EXISTS `ssafy`.`shopping_api`;
DROP TABLE IF EXISTS `ssafy`.`watch`;
DROP TABLE IF EXISTS `ssafy`.`member`;
DROP TABLE IF EXISTS `ssafy`.`ingredient`;

# create tables
CREATE TABLE `member` (
	`member_id`	INT	NOT NULL AUTO_INCREMENT,
	`member_email`	varchar(128)	NULL,
	`member_password`	char(255)	NULL,
	`member_name`	varchar(10)	NULL,
	`member_gender`	char(1)	NULL,
	`member_birth`	DATE	NULL,
	`member_area`	varchar(10)	NULL,
    PRIMARY KEY (`member_id`),
	UNIQUE KEY `member_id_UNIQUE` (`member_id`),
	UNIQUE KEY `member_email_UNIQUE` (`member_email`)
);

CREATE TABLE `ingredient` (
	`ingredient_id`	INT	NOT NULL AUTO_INCREMENT,
	`ingredient_name`	varchar(20)	NULL,
	`ingredient_name_code`	varchar(10)	NULL,
	`ingredient_detail_name`	varchar(20)	NULL,
	`ingredient_detail_name_code`	varchar(10)	NULL,
	`ingredient_category`	varchar(10)	NULL,
    PRIMARY KEY (`ingredient_id`),
	UNIQUE KEY `ingredient_id_UNIQUE` (`ingredient_id`)
);

CREATE TABLE `ingredient_info` (
	`ingredient_info_id`	INT	NOT NULL AUTO_INCREMENT,
	`ingredient_id`	INT	NOT NULL,
	`ingredient_info_date`	DATE	NULL,
	`ingredient_info_price`	INT	NULL,
	`ingredient_info_area`	varchar(10)	NULL,
	`ingredient_info_unit`	varchar(10)	NULL,
    PRIMARY KEY (`ingredient_info_id`)
);

CREATE TABLE `favorite` (
	`member_id`	INT	NOT NULL,
	`ingredient_id`	INT	NOT NULL,
    PRIMARY KEY (`member_id`, `ingredient_id`)
);

CREATE TABLE `watch` (
	`watch_id`	INT	NOT NULL AUTO_INCREMENT,
	`member_id`	INT	NOT NULL,
	`ingredient_id`	INT	NOT NULL,
	`watch_date` date DEFAULT current_timestamp(),
    PRIMARY KEY (`watch_id`)
);

CREATE TABLE `shopping_api` (
	`shopping_api_id`	INT	NOT NULL AUTO_INCREMENT,
	`ingredient_id`	INT	NOT NULL,
	`shopping_api_title`	varchar(128)	NULL,
	`shopping_api_price`	INT	NULL,
	`shopping_api_store`	varchar(20)	NULL,
	`shopping_api_date` date DEFAULT current_timestamp(),
	`shopping_api_link`	varchar(255)	NULL,
    PRIMARY KEY (`shopping_api_id`)
);

CREATE TABLE `ingredient_avg` (
	`ingredient_avg_id`	INT	NOT NULL AUTO_INCREMENT,
	`ingredient_id`	INT	NOT NULL,
	`ingredient_avg_date`	DATE	NULL,
	`ingredient_avg_previous_price` int(11) DEFAULT NULL,
	`ingredient_avg_price`	INT	NULL,
	`ingredient_avg_predict_price`	INT	NULL,
    PRIMARY KEY (`ingredient_avg_id`)
);

# create constraints
ALTER TABLE `ingredient_info` ADD CONSTRAINT `FK_ingredient_TO_ingredient_info_1` FOREIGN KEY (
	`ingredient_id`
)
REFERENCES `ingredient` (
	`ingredient_id`
);

ALTER TABLE `favorite` ADD CONSTRAINT `FK_member_TO_favorite_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `favorite` ADD CONSTRAINT `FK_ingredient_TO_favorite_1` FOREIGN KEY (
	`ingredient_id`
)
REFERENCES `ingredient` (
	`ingredient_id`
);

ALTER TABLE `watch` ADD CONSTRAINT `FK_member_TO_watch_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `watch` ADD CONSTRAINT `FK_ingredient_TO_watch_1` FOREIGN KEY (
	`ingredient_id`
)
REFERENCES `ingredient` (
	`ingredient_id`
);

ALTER TABLE `shopping_api` ADD CONSTRAINT `FK_ingredient_TO_shopping_api_1` FOREIGN KEY (
	`ingredient_id`
)
REFERENCES `ingredient` (
	`ingredient_id`
);

ALTER TABLE `ingredient_avg` ADD CONSTRAINT `FK_ingredient_TO_ingredient_avg_1` FOREIGN KEY (
	`ingredient_id`
)
REFERENCES `ingredient` (
	`ingredient_id`
);

# truncate All tables
set FOREIGN_KEY_CHECKS = 0;
truncate member;
truncate ingredient;
truncate ingredient_info;
truncate favorite;
truncate watch;
truncate shopping_api;
truncate ingredient_avg;
set FOREIGN_KEY_CHECKS = 1;
