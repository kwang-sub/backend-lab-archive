package org.example.market.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConstant {


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Product {
        public static final Integer MINIMUM_ORDER_QUANTITY = 1;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Jwt {
        public static final long ACCESS_TOKEN_EXPIRATION_MS = 60 * 60 * 1000;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Exception {
        public static final String FIELD_ERROR_KEY = "fieldErrors";

        public static final String EXCEPTION_PARAM_NAME = "사용자 이름";
    }

}
