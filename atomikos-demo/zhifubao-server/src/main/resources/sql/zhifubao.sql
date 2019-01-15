CREATE SCHEMA zhifubao DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

USE zhifubao;

CREATE TABLE `wallet` (
  `id` BIGINT(20) UNSIGNED NOT NULL COMMENT 'id',
  `user_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户id',
  `balance` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

USE zhifubao;
INSERT INTO `wallet` (`id`, `user_id`, `balance`) VALUES (1, 1, 100000000);