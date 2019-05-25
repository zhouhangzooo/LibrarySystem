/*
SQLyog Ultimate v8.32 
MySQL - 8.0.15 : Database - test
*********************************************************************
*/
/* 使用说明 */
/* 
	将以下代码放到SQLyog工具进行执行，将创建数据库和数据表
 * */
CREATE DATABASE `library_system`;
USE `library_system`;

CREATE TABLE `student` (
  `s_id` varchar(20) NOT NULL,
  `name` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int,
  `profession` varchar(20),
  `grade` varchar(20),
  `sex` char(4),
  `integrity` int DEFAULT 1,
  `cumulative` int DEFAULT 0,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `manager` (
  `id` varchar(20) NOT NULL,
  `name` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int,
  `phone` char(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book_sort` (
  `sort_id` int NOT NULL AUTO_INCREMENT,
  `sort_name` varchar(20) NOT NULL,
  PRIMARY KEY (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `ISBN` varchar(20) NOT NULL,
  `book_name` varchar(50) NOT NULL,
  `book_author` varchar(20) NOT NULL,
  `book_pub` varchar(50),
  `sort_id` int NOT NULL,
  `book_record` datetime,
  `book_price` DECIMAL NOT NULL,
  `book_borrow` int DEFAULT 0,
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (sort_id) REFERENCES book_sort(sort_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `borrow` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(20) NOT NULL,
  `s_id` VARCHAR(20) NOT NULL,
  `price` DECIMAL NOT NULL,
  `book_name` VARCHAR(50) NOT NULL,
  `borrow_date` DATETIME NOT NULL,
  `expect_return_date` DATETIME NOT NULL,
  `return_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (s_id) REFERENCES student(s_id),
  FOREIGN KEY (ISBN) REFERENCES book(ISBN)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
