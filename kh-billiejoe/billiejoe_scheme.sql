--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 회원 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE member;

CREATE TABLE member (
	member_no	NUMBER		NOT NULL,
	member_email	VARCHAR2(50)		NOT NULL,
	member_pw	VARCHAR2(200)		NOT NULL,
	member_name	VARCHAR2(30)		NOT NULL,
	member_phone	VARCHAR2(20)		NOT NULL,
	member_pic	VARCHAR2(200)	DEFAULT 'https://via.placeholder.com/300.png?text=No%20Image'	NOT NULL,
	reg_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL,
	member_status	CHAR(1)	DEFAULT 'Y'	CHECK(member_status IN('Y','N')) NOT NULL,
	member_is_admin	CHAR(1)	DEFAULT 'N'	CHECK(member_is_admin IN('Y','N')) NOT NULL
);

COMMENT ON COLUMN member.reg_date IS '회원 가입 일시';
COMMENT ON COLUMN member.member_status IS '회원 상태';
COMMENT ON COLUMN member.member_is_admin IS '회원 관리자 여부';

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 탈퇴 회원 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE unreg_member;

CREATE TABLE unreg_member (
	member_no	NUMBER		NOT NULL,
	member_email	VARCHAR2(50)		NOT NULL,
	member_pw	VARCHAR2(200)	DEFAULT 'secession_pw'	NOT NULL,
	member_name	VARCHAR2(30)		NOT NULL,
	member_phone	VARCHAR2(20) DEFAULT '000-0000-0000'		NOT NULL,
	member_pic	VARCHAR2(200)	DEFAULT '/img/....png'	NOT NULL,
	reg_date	TIMESTAMP		NOT NULL,
	member_is_admin	CHAR(1)	DEFAULT 'N'	CHECK(member_is_admin IN('Y','N')) NOT NULL,
	unreg_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL
);


COMMENT ON COLUMN unreg_member.reg_date IS '회원 가입 일시';
COMMENT ON COLUMN unreg_member.member_is_admin IS '회원 관리자 여부';
COMMENT ON COLUMN unreg_member.unreg_date IS '회원 탈퇴 일시';

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 장소 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE place;

CREATE TABLE place (
	place_no	NUMBER		NOT NULL,
	place_name	VARCHAR2(100)		NOT NULL,
	place_summary	VARCHAR2(200)		NOT NULL,
	place_addr	VARCHAR2(200)		NOT NULL,
	place_content	CLOB		NOT NULL,
	place_charge	NUMBER		NOT NULL,
	place_is_available	CHAR(1)	DEFAULT 'Y'	CHECK(place_is_available IN('Y','N')) NOT NULL,
	place_status	CHAR(1)	DEFAULT 'Y'	CHECK(place_status IN('Y','N')) NOT NULL,
	place_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL,
	member_no	NUMBER		NOT NULL
);

COMMENT ON COLUMN place.place_summary IS '장소 짧은 설명';
COMMENT ON COLUMN place.place_charge IS '장소 대여 가격';
COMMENT ON COLUMN place.place_is_available IS '장소 예약 가능 여부';
COMMENT ON COLUMN place.place_status IS '장소 상태';
COMMENT ON COLUMN place.member_no IS '장소 작성 회원';

--------------------------------------------------------------------------------
-- 장소 이용 가능 시간 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE place_available;

CREATE TABLE place_available (
	available_no	NUMBER		NOT NULL,
	place_no	NUMBER		NOT NULL,
	available_start	NUMBER		NOT NULL,
	available_end	NUMBER		NOT NULL
);

COMMENT ON COLUMN place_available.available_start IS '이용 가능 시작시간';
COMMENT ON COLUMN place_available.available_end IS '이용 가능 종료시간';


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 예약 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE reservation CASCADE CONSTRAINTS;

CREATE TABLE reservation (
	reserve_no	NUMBER		NOT NULL,
	reserve_use_date VARCHAR2(30) NOT NULL,
	reserve_use_start	NUMBER		NOT NULL,
	reserve_use_end	NUMBER		NOT NULL,
	reserve_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL,
	place_no	NUMBER		NOT NULL,
	member_no	NUMBER		NOT NULL,
	state_no	NUMBER		NOT NULL
);

COMMENT ON COLUMN reservation.reserve_use_date IS '이용 예정일';
COMMENT ON COLUMN reservation.reserve_use_start IS '이용 시작 시간';
COMMENT ON COLUMN reservation.reserve_use_end IS '이용 종료 시간';
COMMENT ON COLUMN reservation.reserve_date IS '예약 일시';
COMMENT ON COLUMN reservation.place_no IS '예약한 장소';
COMMENT ON COLUMN reservation.member_no IS '예약한 회원';
COMMENT ON COLUMN reservation.state_no IS '예약 상태';

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 결제 테이블 생성 SQL
--------------------------------------------------------------------------------

DROP TABLE payment;

CREATE TABLE payment (
	pay_no	NUMBER		NOT NULL,
	reserve_no	NUMBER		NOT NULL,
	pay_amount	NUMBER		NOT NULL,
	pay_method	VARCHAR2(30)		NOT NULL,
	pay_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL,
	member_no	NUMBER		NOT NULL,
	merchant_uid	VARCHAR2(30)		NOT NULL
);

COMMENT ON COLUMN payment.pay_amount IS '결제 금액';
COMMENT ON COLUMN payment.pay_method IS '결제 수단';
COMMENT ON COLUMN payment.merchant_uid IS '주문 번호(import 용)';

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 채팅방 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE chat_room;

CREATE TABLE chat_room (
	chat_room_no	NUMBER		NOT NULL,
	place_no	NUMBER		NOT NULL,
	member_no	NUMBER		NOT NULL
);

COMMENT ON COLUMN chat_room.chat_room_no IS '채팅방 번호';
COMMENT ON COLUMN chat_room.place_no IS '장소 작성자';
COMMENT ON COLUMN chat_room.member_no IS '채팅문의 누른 사람';

--------------------------------------------------------------------------------
-- 채팅 메시지 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE chat_msg;

CREATE TABLE chat_msg (
	chat_msg_no	NUMBER		NOT NULL,
	chat_msg_content	VARCHAR2(500)		NOT NULL,
	chat_msg_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL,
	member_no	NUMBER		NOT NULL,
	chatroom_no	NUMBER		NOT NULL
);

COMMENT ON COLUMN chat_msg.chat_msg_no IS '메시지 번호';
COMMENT ON COLUMN chat_msg.chat_msg_content IS '메시지 내용';
COMMENT ON COLUMN chat_msg.chat_msg_date IS '메세지 작성 시간';
COMMENT ON COLUMN chat_msg.member_no IS '메시지 작성 회원 번호';
COMMENT ON COLUMN chat_msg.chatroom_no IS '보내진 채팅방 번호';

--------------------------------------------------------------------------------
-- 채팅방 참여 회원 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE chat_user;

CREATE TABLE chat_user (
	member_no	NUMBER		NOT NULL,
	chat_room_no	NUMBER		NOT NULL,
	chat_join_status	CHAR(1)	DEFAULT 'Y'	CHECK(chat_join_status IN('Y','N')) NOT NULL
);

COMMENT ON COLUMN chat_user.member_no IS '참가한 회원 번호';
COMMENT ON COLUMN chat_user.chat_room_no IS '참가중인 채팅방 번호';
COMMENT ON COLUMN chat_user.chat_join_status IS '회원 참가 상태';


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 리뷰 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE review;

CREATE TABLE review (
	review_no	NUMBER		NOT NULL,
	review_content	CLOB		NOT NULL,
	review_rate	NUMBER		NOT NULL,
	review_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL,
	place_no	NUMBER		NOT NULL,
	member_no	NUMBER		NOT NULL,
	review_status	CHAR(1)	DEFAULT 'Y'	CHECK(review_status IN('Y','N')) NOT NULL
);

COMMENT ON COLUMN review.place_no IS '후기가 남겨진 장소';
COMMENT ON COLUMN review.member_no IS '후기 작성 회원';
COMMENT ON COLUMN review.review_status IS '후기 상태';


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 찜 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE likes;

CREATE TABLE likes (
	member_no	NUMBER		NOT NULL,
	place_no	NUMBER		NOT NULL,
	liked_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL
);

COMMENT ON COLUMN likes.member_no IS '찜한 회원';
COMMENT ON COLUMN likes.place_no IS '찜한 장소';
COMMENT ON COLUMN likes.liked_date IS '찜한 날짜';

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 신고 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE report;

CREATE TABLE report (
	report_no	NUMBER		NOT NULL,
	report_content	VARCHAR2(300)		NOT NULL,
	member_no	NUMBER		NOT NULL,
	review_no	NUMBER		NOT NULL,
	report_date	TIMESTAMP	DEFAULT SYSTIMESTAMP	NOT NULL
);


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 첨부파일 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE attachment;

CREATE TABLE attachment (
	file_no	NUMBER		NOT NULL,
	file_path	VARCHAR2(500)		NOT NULL,
	file_name	VARCHAR2(500)		NOT NULL,
	file_level	NUMBER		NOT NULL,
	place_no	NUMBER		NOT NULL
);


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 장소-태그 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE place_tag;

CREATE TABLE place_tag (
	place_no	NUMBER		NOT NULL,
	tag_no	NUMBER		NOT NULL
);

COMMENT ON COLUMN place_tag.place_no IS '태그 달린 장소';

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 태그 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE tag;

CREATE TABLE tag (
	tag_no	NUMBER		NOT NULL,
	tag_name	VARCHAR2(50)		NOT NULL
);

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 예약 상태 테이블 생성 SQL
--------------------------------------------------------------------------------
DROP TABLE reservation_state;

CREATE TABLE reservation_state (
	state_no	NUMBER		NOT NULL,
	state_name	VARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN reservation_state.state_name IS '예약 상태 이름';


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 이하 PK 조건 SQL
--------------------------------------------------------------------------------
ALTER TABLE member ADD CONSTRAINT PK_MEMBER PRIMARY KEY (
	member_no
);

ALTER TABLE place ADD CONSTRAINT PK_PLACE PRIMARY KEY (
	place_no
);

ALTER TABLE reservation ADD CONSTRAINT PK_RESERVATION PRIMARY KEY (
	reserve_no
);

ALTER TABLE unreg_member ADD CONSTRAINT PK_UNREG_MEMBER PRIMARY KEY (
	member_no
);

ALTER TABLE place_available ADD CONSTRAINT PK_PLACE_AVAILABLE PRIMARY KEY (
	available_no,
	place_no
);

ALTER TABLE payment ADD CONSTRAINT PK_PAYMENT PRIMARY KEY (
	pay_no,
	reserve_no
);

ALTER TABLE chat_room ADD CONSTRAINT PK_CHAT_ROOM PRIMARY KEY (
	chat_room_no
);

ALTER TABLE chat_msg ADD CONSTRAINT PK_CHAT_MSG PRIMARY KEY (
	chat_msg_no
);

ALTER TABLE chat_user ADD CONSTRAINT PK_CHAT_USER PRIMARY KEY (
	member_no,
	chat_room_no
);

ALTER TABLE review ADD CONSTRAINT PK_REVIEW PRIMARY KEY (
	review_no
);

ALTER TABLE likes ADD CONSTRAINT PK_LIKE PRIMARY KEY (
	member_no,
	place_no
);

ALTER TABLE report ADD CONSTRAINT PK_REPORT PRIMARY KEY (
	report_no
);

ALTER TABLE attachment ADD CONSTRAINT PK_ATTACHMENT PRIMARY KEY (
	file_no
);

ALTER TABLE tag ADD CONSTRAINT PK_TAG PRIMARY KEY (
	tag_no
);

ALTER TABLE tag ADD CONSTRAINT UK_TAG UNIQUE (
    tag_name
);

ALTER TABLE reservation_state ADD CONSTRAINT PK_RESERVATION_STATE PRIMARY KEY (
	state_no
);


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 이하 FK 조건 SQL
--------------------------------------------------------------------------------
ALTER TABLE place_available ADD CONSTRAINT FK_place_TO_place_available_1 FOREIGN KEY (
	place_no
)
REFERENCES place (
	place_no
);

ALTER TABLE payment ADD CONSTRAINT FK_reservation_TO_payment_1 FOREIGN KEY (
	reserve_no
)
REFERENCES reservation (
	reserve_no
);

ALTER TABLE chat_user ADD CONSTRAINT FK_member_TO_chat_user_1 FOREIGN KEY (
	member_no
)
REFERENCES member (
	member_no
);

ALTER TABLE chat_user ADD CONSTRAINT FK_chat_room_TO_chat_user_1 FOREIGN KEY (
	chat_room_no
)
REFERENCES chat_room (
	chat_room_no
);

ALTER TABLE likes ADD CONSTRAINT FK_member_TO_like_1 FOREIGN KEY (
	member_no
)
REFERENCES member (
	member_no
);

ALTER TABLE likes ADD CONSTRAINT FK_place_TO_like_1 FOREIGN KEY (
	place_no
)
REFERENCES place (
	place_no
);


--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- 시퀀스 설정
--------------------------------------------------------------------------------
-- 회원 번호용
CREATE SEQUENCE seq_user;
DROP SEQUENCE seq_user;

-- 장소용
CREATE SEQUENCE seq_place;
DROP SEQUENCE seq_place;

-- 태그용
CREATE SEQUENCE seq_tag;
DROP SEQUENCE seq_tag;

-- 파일용
CREATE SEQUENCE seq_file;
DROP SEQUENCE seq_file;

-- 결제용
CREATE SEQUENCE seq_pay;
DROP SEQUENCE seq_pay;

-- 예약 가능 시간용 
CREATE SEQUENCE seq_available;
DROP SEQUENCE seq_available;

-- 예약용
CREATE SEQUENCE seq_reserve;
DROP SEQUENCE seq_reserve;

-- 채팅 메시지용
CREATE SEQUENCE seq_chat_msg;
DROP SEQUENCE seq_chat_msg;

-- 후기용
CREATE SEQUENCE seq_review;
DROP SEQUENCE seq_review;

-- 신고용
CREATE SEQUENCE seq_report;
DROP SEQUENCE seq_report;

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- VIEW 설정
--------------------------------------------------------------------------------

--place조회용 view
CREATE OR REPLACE FORCE VIEW "BILLIEJOE"."PLACE_VIEW" ("PLACE_NO", "PLACE_NAME", "PLACE_SUMMARY", "PLACE_ADDR", "PLACE_CONTENT", "PLACE_CHARGE", "MEMBER_NO", "PLACE_DATE", "LIKE_COUNT", "PLACE_STATUS", "PLACE_IS_AVAILABLE") AS 
  SELECT PLACE_NO, PLACE_NAME, PLACE_SUMMARY, PLACE_ADDR, PLACE_CONTENT, PLACE_CHARGE, MEMBER_NO, PLACE_DATE, LIKE_COUNT,PLACE_STATUS,PLACE_IS_AVAILABLE
FROM PLACE
LEFT JOIN (SELECT COUNT(*) LIKE_COUNT ,PLACE_NO
        FROM LIKES
        GROUP BY PLACE_NO) USING (PLACE_NO)
JOIN MEMBER USING(MEMBER_NO);

--Tag조회용 view
CREATE OR REPLACE FORCE VIEW PLACE_TAG_VIEW ("PLACE_NO", "TAG_NO", "TAG_NAME") AS 
  SELECT PLACE_NO, TAG_NO, TAG_NAME
                    FROM PLACE_TAG      
                    JOIN TAG USING (TAG_NO);

-- place 목록 조회용 view
CREATE OR REPLACE VIEW place_list AS
    SELECT place_no, place_name, place_summary, place_addr, place_charge, place_is_available, place_status, member_no,
        file_name, 
        like_count,
        review_count
    FROM place
    JOIN member USING(member_no)
    JOIN (SELECT place_no, file_name FROM attachment WHERE file_level = 0) USING(place_no)
    LEFT JOIN (SELECT place_no, COUNT(*) AS review_count FROM review GROUP BY place_no) USING(place_no)
    LEFT JOIN (SELECT place_no, COUNT(*) AS like_count FROM likes GROUP BY place_no) USING(place_no);
    
-- 장소에 대한 후기 평점 및 개수를 구하는 뷰 생성
CREATE OR REPLACE VIEW REVIEW_ADD AS
        SELECT  PLACE_NO, 
                    ROUND( AVG(REVIEW_RATE), 0 ) AS REVIEW_AVG, 
                    COUNT(*) AS REVIEW_COUNT 
            FROM REVIEW 
            JOIN PLACE USING(PLACE_NO)
            WHERE REVIEW_STATUS='Y'
            GROUP BY PLACE_NO;
;