package com.example.ch03.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemTest {

    @Mock
    private Validator validator;

    @Test
    void test() {
        when(validator.validate(any())).thenReturn(Set.of());
        Set<ConstraintViolation<Item>> validate = validator.validate(new Item());
        Assertions.assertThat(validate).isEmpty();
    }

    @Test
    void validationTest() {
        Item item = Item.builder()
                .name("Some Item")
                .auctionEnd(new Date())
                .build();

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        ConstraintViolation<Item> violation = violations.iterator().next();
        String failedPropertyName = violation.getPropertyPath().iterator().next().getName();

        // SoftAssertions 사용
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(violations.size()).isEqualTo(1);
        softly.assertThat(failedPropertyName).isEqualTo("auctionEnd");
        if (Locale.getDefault().getLanguage().equals("ko")) {
            softly.assertThat(violation.getMessage()).isEqualTo("미래 날짜여야 합니다");
        }
        // 검증 실행
        softly.assertAll();

    }
}