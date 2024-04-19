CREATE TABLE IF NOT EXISTS `quiz` (
    `id` varchar(255) NOT NULL,
    `user_id` varchar(255) NOT NULL,
    `quiz_title` varchar(255) DEFAULT NULL,

    `created_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `deleted_at` datetime(6)  DEFAULT NULL,

    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `question` (
    `id` varchar(255) NOT NULL,
    `quiz_id` varchar(255) NOT NULL,
    `question_title` varchar(255) NOT NULL,
    `question_type` varchar(255) NOT NULL,

    `created_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `deleted_at` datetime(6)  DEFAULT NULL,

    PRIMARY KEY (`id`),
    INDEX `idx_quiz_id` (`quiz_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `answer` (
    `id` varchar(255) NOT NULL,
    `question_id` varchar(255) NOT NULL,
    `answer_title` varchar(255) NOT NULL,
    `answer_correct` boolean NOT NULL,

    `created_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `deleted_at` datetime(6)  DEFAULT NULL,

    PRIMARY KEY (`id`),
    INDEX `idx_question_id` (`question_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
    `id` varchar(255) NOT NULL,
    `user_name` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,

    `created_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` datetime(6)  DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    `deleted_at` datetime(6)  DEFAULT NULL,

    PRIMARY KEY (`id`),

    INDEX `idx_user_name` (`user_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
