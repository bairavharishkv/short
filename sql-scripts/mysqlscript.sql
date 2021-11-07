

CREATE DATABASE  IF NOT EXISTS `url_tracker`;
USE `url_tracker`;

DROP TABLE IF EXISTS `url`;

CREATE TABLE `url` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `long_url` text DEFAULT NULL,
  `short_urlkey` varchar(255) DEFAULT NULL,
  `creation_date` varchar(255) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `statistics`;

CREATE TABLE `statistics` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `visit_time` varchar(255) DEFAULT NULL,
   `ip_address` varchar(255) DEFAULT NULL,
   `url_id` bigint(15) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_URL_idx` (`url_id`),
  
  CONSTRAINT `FK_URL` 
  FOREIGN KEY (`url_id`) 
  REFERENCES `url` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into `url_tracker`.url
(id, long_url, short_urlkey, creation_date)
 values(1,"dummy url", "a", "0:0:0");
 
 insert into `url_tracker`.statistics
(id, visit_time, ip_address, url_id)
 values(1,"0:0:0", "0.0.0.0", 1);

