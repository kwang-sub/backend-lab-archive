package org.example.ladder.constant

interface ErrorCode {
    val code: String
    val message: String
}

enum class ServiceErrorCode(
    override val code: String,
    override val message: String
): ErrorCode {
    ENTITY_NOT_FOUND("E10001", "Entity not found: %s"),

    INVALID_VALUE("C10001", "올바르지 않은 값입니다."),

    ALREADY_PLAYER("P10001", "이미 플레이어가 존재합니다."),

    MISMATCHED_PLAYER_AND_REWARD_COUNT("P10001", "플레이어 수와 보상 수가 일치하지 않습니다."),
    ALREADY_EXIST_LADDER("L10001", "이미 사다리가 존재합니다."),
}

enum class DomainErrorCode(
    override val code: String,
    override val message: String
): ErrorCode {
    INVALID_PLAYER_NAME("D10001",  "올바르지 않은 이름입니다."),
    INVALID_LINE_MINIMUM_POINT_COUNT("D10002",  "최소 1개의 점이 필요합니다."),
    NOT_SAME_SIZE_LINES("D10003",  "라인의 크기가 다릅니다."),
    INVALID_TARGET_PLAYER("D10004",  "대상 플레이어가 아닙니다."),
}