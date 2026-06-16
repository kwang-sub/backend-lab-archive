-- liquibase formatted sql
-- changeset kwang:init.sql

-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.10.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

-- 테이블 leesan.tbl_std_contents 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_contents`;
CREATE TABLE IF NOT EXISTS `tbl_std_contents`
(
    `id`                   bigint(20)   NOT NULL AUTO_INCREMENT,
    `contents_create_date` datetime(6)  NOT NULL COMMENT '컨텐츠생성일시',
    `contents_name`        varchar(50)  NOT NULL COMMENT '컨텐츠명',
    `contents_og_name`     varchar(100) NOT NULL COMMENT '컨텐츠원본명',
    `contents_path`        varchar(100) NOT NULL COMMENT '컨텐츠경로',
    `contents_size`        int(11)      NOT NULL COMMENT '컨텐츠사이즈',
    `contents_type`        varchar(100) NOT NULL COMMENT '컨텐츠유형',
    `is_deleted`           bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`           varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`         datetime(6)  NOT NULL COMMENT '등록일시',
    `last_modified_by`     varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date`   datetime(6)  NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='컨텐츠';
-- 테이블 leesan.tbl_std_admin 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_admin`;
CREATE TABLE IF NOT EXISTS `tbl_std_admin`
(
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
    `admin_login_id`     varchar(100) NOT NULL COMMENT '어드민로그인아이디',
    `admin_password`     varchar(255) NOT NULL COMMENT '어드민비밀번호',
    `admin_user_name`    varchar(50)  NOT NULL COMMENT '어드민명',
    `admin_phone_num`    varchar(30)  NOT NULL COMMENT '어드민연락처',
    `admin_email`        varchar(100) NOT NULL COMMENT '어드민이메일',
    `is_activated`       bit(1)       NOT NULL DEFAULT b'1' COMMENT '활성화여부',
    `is_deleted`         bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`         varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`       datetime(6)  NOT NULL COMMENT '등록일시',
    `last_modified_by`   varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date` datetime(6)  NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='어드민계정';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_authority 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_authority`;
CREATE TABLE IF NOT EXISTS `tbl_std_authority`
(
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
    `authority_code`     varchar(25)  NOT NULL COMMENT '권한코드',
    `authority_name`     varchar(100) NOT NULL COMMENT '권한명',
    `authority_desc`     varchar(255) DEFAULT NULL COMMENT '권한설명',
    `is_activated`       bit(1)       NOT NULL COMMENT '활성화여부',
    `is_deleted`         bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`         varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`       datetime(6)  NOT NULL COMMENT '등록일시',
    `last_modified_by`   varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date` datetime(6)  NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='권한';


-- 테이블 leesan.tbl_std_admin_authority 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_admin_authority`;
CREATE TABLE IF NOT EXISTS `tbl_std_admin_authority`
(
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
    `admin_id`           bigint(20)   NOT NULL COMMENT '어드민ID',
    `is_deleted`         bit(1)       NOT NULL DEFAULT b'0' COMMENT '삭제여부',
    `created_by`         varchar(100) NOT NULL DEFAULT user() COMMENT '등록자ID',
    `created_date`       datetime(6)  NOT NULL DEFAULT current_timestamp() COMMENT '등록일시',
    `last_modified_by`   varchar(100) NOT NULL DEFAULT user() COMMENT '수정자ID',
    `last_modified_date` datetime(6)  NOT NULL DEFAULT current_timestamp() COMMENT '수정일시',
    `authority_code_id`  bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_tbl_std_admin_authority__authority_code_id` (`authority_code_id`),
    KEY `tbl_std_admin_authority_tbl_std_admin_id_fk` (`admin_id`),
    CONSTRAINT `fk_tbl_std_admin_authority__authority_code_id` FOREIGN KEY (`authority_code_id`) REFERENCES `tbl_std_authority` (`id`),
    CONSTRAINT `tbl_std_admin_authority_tbl_std_admin_id_fk` FOREIGN KEY (`admin_id`) REFERENCES `tbl_std_admin` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='어드민권한관계';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_admin_password 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_admin_password`;
CREATE TABLE IF NOT EXISTS `tbl_std_admin_password`
(
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
    `before_password`    varchar(255) NOT NULL COMMENT '이전비밀번호',
    `after_password`     varchar(255) NOT NULL COMMENT '변경비밀번호',
    `is_deleted`         bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`         varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`       datetime(6)  NOT NULL COMMENT '등록일시',
    `last_modified_by`   varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date` datetime(6)  NOT NULL COMMENT '수정일시',
    `admin_id`           bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_tbl_std_admin_password__admin_id` (`admin_id`),
    CONSTRAINT `fk_tbl_std_admin_password__admin_id` FOREIGN KEY (`admin_id`) REFERENCES `tbl_std_admin` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='어드민계정비밀번호변경이력';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_board 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_board`;
CREATE TABLE IF NOT EXISTS `tbl_std_board`
(
    `id`                 bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '인덱스번호',
    `admin_id`           bigint(20)   NOT NULL COMMENT '관리자인덱스ID',
    `board_title`        varchar(50)  NOT NULL COMMENT '게시판제목',
    `board_contents`     longtext              DEFAULT NULL COMMENT '게시판내용',
    `board_type_code`    varchar(50)  NOT NULL COMMENT '게시판유형',
    `view_cnt`           int(11)      NOT NULL DEFAULT 0 COMMENT '조회수',
    `major_news`         bit(1)       NOT NULL COMMENT '주요소식여부',
    `is_deleted`         bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`         varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`       datetime     NOT NULL COMMENT '등록일시',
    `last_modified_by`   varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date` datetime     NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`),
    KEY `admin_id` (`admin_id`),
    CONSTRAINT `tbl_std_board_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `tbl_std_admin` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_board_contents_rel 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_board_contents_rel`;
CREATE TABLE IF NOT EXISTS `tbl_std_board_contents_rel`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '인덱스번호',
    `board_id`    bigint(20) NOT NULL COMMENT '게시글인덱스ID',
    `contents_id` bigint(20) NOT NULL COMMENT '컨텐츠인덱스ID',
    PRIMARY KEY (`id`),
    KEY `board_id` (`board_id`),
    KEY `contents_id` (`contents_id`),
    CONSTRAINT `tbl_std_board_contents_rel_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `tbl_std_board` (`id`),
    CONSTRAINT `tbl_std_board_contents_rel_ibfk_2` FOREIGN KEY (`contents_id`) REFERENCES `tbl_std_contents` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_board_headline 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_board_headline`;
CREATE TABLE IF NOT EXISTS `tbl_std_board_headline`
(
    `id`                 int(11)      NOT NULL AUTO_INCREMENT COMMENT '인덱스ID',
    `contents_id`        bigint(20)   DEFAULT NULL COMMENT '컨텐츠인덱스ID',
    `title`              varchar(25)  NOT NULL COMMENT '제목',
    `content`            varchar(255) NOT NULL COMMENT '내용',
    `qna_type_code`      varchar(25)  NOT NULL COMMENT '게시글유형코드',
    `link`               varchar(500) DEFAULT NULL COMMENT '참조링크',
    `is_public`          bit(1)       NOT NULL COMMENT '노출여부',
    `is_deleted`         bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`         varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`       datetime     NOT NULL COMMENT '등록일시',
    `last_modified_by`   varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date` datetime     NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`),
    KEY `fk_std_board_headline_content` (`contents_id`),
    CONSTRAINT `fk_std_board_headline_content` FOREIGN KEY (`contents_id`) REFERENCES `tbl_std_contents` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='게시글 헤드라인 관리';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_common_group_code 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_common_group_code`;
CREATE TABLE IF NOT EXISTS `tbl_std_common_group_code`
(
    `id`                     bigint(20)   NOT NULL AUTO_INCREMENT,
    `common_group_code`      varchar(25)  NOT NULL COMMENT '공통그룹코드',
    `common_group_code_name` varchar(50)  NOT NULL COMMENT '공통그룹코드명',
    `common_group_code_desc` varchar(255) DEFAULT NULL COMMENT '공통그룹코드설명',
    `sort_num`               int(11)      NOT NULL COMMENT '정렬순서',
    `is_activated`           bit(1)       NOT NULL COMMENT '활성화여부',
    `is_deleted`             bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`             varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`           datetime(6)  NOT NULL COMMENT '등록일시',
    `last_modified_by`       varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date`     datetime(6)  NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='공통그룹코드';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 leesan.tbl_std_common_code 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_common_code`;
CREATE TABLE IF NOT EXISTS `tbl_std_common_code`
(
    `id`                   bigint(20)   NOT NULL AUTO_INCREMENT,
    `common_code`          varchar(25)  NOT NULL COMMENT '공통코드',
    `common_code_name`     varchar(50)  NOT NULL COMMENT '공통코드명',
    `common_code_desc`     varchar(255) DEFAULT NULL COMMENT '공통코드설명',
    `sort_num`             int(11)      NOT NULL COMMENT '정렬순서',
    `is_activated`         bit(1)       NOT NULL COMMENT '활성화여부',
    `is_deleted`           bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`           varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`         datetime(6)  NOT NULL COMMENT '등록일시',
    `last_modified_by`     varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date`   datetime(6)  NOT NULL COMMENT '수정일시',
    `common_group_code_id` bigint(20)   NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_tbl_std_common_code__common_group_code_id` (`common_group_code_id`),
    CONSTRAINT `fk_tbl_std_common_code__common_group_code_id` FOREIGN KEY (`common_group_code_id`) REFERENCES `tbl_std_common_group_code` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='공통코드';

-- 내보낼 데이터가 선택되어 있지 않습니다.




-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_message 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_message`;
CREATE TABLE IF NOT EXISTS `tbl_std_message`
(
    `id`                 bigint(20)    NOT NULL AUTO_INCREMENT,
    `message_type_code`  varchar(25)   NOT NULL COMMENT '메시지유형코드',
    `message_code`       varchar(25)   NOT NULL COMMENT '메시지코드',
    `message_content`    varchar(1000) NOT NULL COMMENT '메시지내용',
    `sort_order`         int(11)       NOT NULL COMMENT '정렬순서',
    `is_activated`       bit(1)        NOT NULL COMMENT '활성화여부',
    `is_deleted`         bit(1)        NOT NULL COMMENT '삭제여부',
    `created_by`         varchar(100)  NOT NULL COMMENT '등록자ID',
    `created_date`       datetime(6)   NOT NULL COMMENT '등록일시',
    `last_modified_by`   varchar(100)  NOT NULL COMMENT '수정자ID',
    `last_modified_date` datetime(6)   NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='공통메시지';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 leesan.tbl_std_qna 구조 내보내기
DROP TABLE IF EXISTS `tbl_std_qna`;
CREATE TABLE IF NOT EXISTS `tbl_std_qna`
(
    `id`                  int(11)      NOT NULL AUTO_INCREMENT COMMENT '인덱스번호',
    `title`               varchar(50)  NOT NULL COMMENT '제목',
    `content`             longtext     NOT NULL COMMENT '내용',
    `qna_type_code`       varchar(25)  NOT NULL COMMENT '문의분야',
    `writer_company_name` varchar(50)  NOT NULL COMMENT '작성자업체명',
    `writer_dept_name`    varchar(50)  NOT NULL COMMENT '작성자부서명',
    `writer_name`         varchar(50)  NOT NULL COMMENT '작성자이름',
    `writer_tel_no`       varchar(50)  NOT NULL COMMENT '작성자연락처',
    `writer_email`        varchar(100) NOT NULL COMMENT '작성자이메일',
    `admin_memo`          longtext DEFAULT NULL COMMENT '어드민메모',
    `is_deleted`          bit(1)       NOT NULL COMMENT '삭제여부',
    `created_by`          varchar(100) NOT NULL COMMENT '등록자ID',
    `created_date`        datetime     NOT NULL COMMENT '등록일시',
    `last_modified_by`    varchar(100) NOT NULL COMMENT '수정자ID',
    `last_modified_date`  datetime     NOT NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE = IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE = IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS = IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES = IFNULL(@OLD_SQL_NOTES, 1) */;

