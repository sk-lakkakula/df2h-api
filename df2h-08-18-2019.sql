# df2h Latest Schema to add Order Details, Changes for Item -08-18-2019
# Table Creation Sequence 
# 1. administrator
# 2. supplier
# 3. farmer
# 4. consumer
# 5. category
# 1. survey -removed
# 6. user_details
# 7. order_details
# 8. item
# 9. order_item


DROP SCHEMA IF EXISTS `df2h_10_19`;

CREATE SCHEMA `df2h_10_19`;

use `df2h_10_19`;

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `contact_no` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `user_details_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_name_unique` (`first_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4008 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `contact_no` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `user_details_id` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `registration_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `supplier_name_unique` (`first_name`),
  KEY `FK_USER_ID_IDX` (`user_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3034 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `farmer`;
CREATE TABLE `farmer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `contact_no` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `registration_status` varchar(255) DEFAULT NULL,
  `user_details_id` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `farmer_name_unique` (`first_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4023 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `contact_no` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `registration_status` varchar(255) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `consumer_name_unique` (`first_name`),
  KEY `FK_SUPPLIER_IDX` (`supplier_id`),
  CONSTRAINT `FK_SUPPLIER` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=4010 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_role` varchar(255) DEFAULT NULL,
  `consumer_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `administrator_id` int(11) DEFAULT NULL,
  `farmer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`user_name`),
  KEY `FK_CONSUMER_ID_IDX` (`consumer_id`),
  KEY `FK_SUPLR_ID_IDX` (`supplier_id`),
  KEY `FK_ADMIN_ID_IDX` (`administrator_id`),
  KEY `FK_FARMR_ID_IDX` (`farmer_id`),
  CONSTRAINT `FK_ADMIN_ID` FOREIGN KEY (`administrator_id`) REFERENCES `administrator` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CONSUMER_ID` FOREIGN KEY (`consumer_id`) REFERENCES `consumer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_FARMR_ID` FOREIGN KEY (`farmer_id`) REFERENCES `farmer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USERSUPPLIER_ID` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1043 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_cost` varchar(255) DEFAULT NULL,
  `consumer_id` int(11) DEFAULT NULL,
  `payment_type` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `delivery_date date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORDR_CONSUMER_ID` (`consumer_id`),
  #KEY `FK_ORDR_ITEM_ID` (`item_id`),
  CONSTRAINT `FK_ORDR_CONSUMER_ID` FOREIGN KEY (`consumer_id`) REFERENCES `consumer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
#  CONSTRAINT `FK_ORDR_ITEM_ID` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `unit_cost` varchar(255) DEFAULT NULL,
  `unit_of_sale` varchar(255) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `stock_remaining` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `quantity_ordered` int(11) ,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_name_uniq` (`name`),
  KEY `FK_ITEM_CATEGORY_ID_INDX` (`category_id`),
  CONSTRAINT `FK_ITEM_CATEGORY_IDD` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  )ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `unit_cost` varchar(255) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `total_cost` Float(3,2) ,
  `quantity_ordered` int(5) ,
  PRIMARY KEY (`id`),
  KEY `FK_ORDER_ITEM_ID_IDX` (`order_id`),
  CONSTRAINT `FK_ORDER_ITEM_ID` FOREIGN KEY (`order_id`) REFERENCES `order_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  )ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

