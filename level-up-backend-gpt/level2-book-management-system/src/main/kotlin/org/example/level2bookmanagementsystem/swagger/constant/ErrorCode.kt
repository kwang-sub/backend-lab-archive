package org.example.level2bookmanagementsystem.swagger.constant

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpCode: HttpStatus,
    val code: String,
    val message: String,
) {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "T10000", "인증이 실패한 경우"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "T10001", "권한이 없는 경우"),
    AUTH_TOKEN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "T10002", "인증 토큰 오류가 발생한 경우"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "T10003", "인증 토큰이 만료된 경우"),

    // 인증 세분화 코드 (401)
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "T10010", "유효하지 않은 인증 토큰입니다"),
    EXPIRED_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "T10011", "만료된 인증 토큰입니다"),
    NOT_EXISTS_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "T10012", "인증 토큰이 존재하지 않습니다"),
    NOT_FOUND_AUTH_REFRESH(HttpStatus.UNAUTHORIZED, "T10013", "리프레시 토큰을 찾을 수 없습니다"),
    INVALID_AUTH_REFRESH(HttpStatus.UNAUTHORIZED, "T10014", "리프레시 토큰이 일치하지 않습니다"),
    NOT_FOUND_AUTH_USER(HttpStatus.UNAUTHORIZED, "T10015", "인증 대상 사용자를 찾을 수 없습니다"),

    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "L10001", "도서 재고가 부족한 경우"),
    ALREADY_LOANED_BOOK(HttpStatus.BAD_REQUEST, "L10002", "이미 대출한 도서인 경우"),
    EXCEEDING_NUMBER_OF_LOANS(HttpStatus.BAD_REQUEST, "L10003", "대출 한도를 초과한 경우"),
    EXISTING_OVERDUE_BOOK_LOAN(HttpStatus.BAD_REQUEST, "L10004", "연체된 도서 대출이 존재하는 경우"),
    EXISTING_LOAN_RESTRICTION(HttpStatus.BAD_REQUEST, "L10005", "도서 대출이 제한된 경우"),

    DUPLICATE_REQUEST(HttpStatus.BAD_REQUEST, "D10001", "중복된 사용자 요청인 경우"),
    NOT_FOUND_ENTITY(HttpStatus.BAD_REQUEST, "E10001", "Entity(%s)를 찾을 수 없는 경우"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "A10001", "서버 오류가 발생한 경우"),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "A10001", "올바르지 않은 요청 값인 경우");


    fun getMessage(vararg args: Any): String = if (args.isNotEmpty()) String.format(message, *args) else message
}