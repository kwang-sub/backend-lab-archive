package org.example.ex01;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SampleTest {

    @Test
    void test() {
        boolean result = Sample.isStringLong("abc");
        assertThat(result).isFalse();
    }
}