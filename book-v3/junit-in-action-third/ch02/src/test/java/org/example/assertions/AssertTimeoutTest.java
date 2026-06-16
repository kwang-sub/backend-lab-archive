package org.example.assertions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AssertTimeoutTest {
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @DisplayName("작업을 마칠 떄까지 기다리는 assertTimeout 메서드")
    void testTimeout() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeout(Duration.ofMillis(500), () -> systemUnderTest.run(200));
    }

    @Test
    @DisplayName("시간이 지나면 작업을 중지시키는 assertTimeoutPreemptively 메서드")
    void testTimeoutPreemptively() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeoutPreemptively(Duration.ofMillis(500), () -> systemUnderTest.run(500));
    }
}