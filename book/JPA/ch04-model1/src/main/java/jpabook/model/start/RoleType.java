package jpabook.model.start;

public enum RoleType {
    ADMIN("관리자"), USER("일반 사용자");

    private String detail;

    RoleType(String detail) {
        this.detail = detail;
    }
}
