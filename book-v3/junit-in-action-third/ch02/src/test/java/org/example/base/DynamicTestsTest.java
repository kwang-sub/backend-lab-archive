package org.example.base;

import org.example.predicate.PositiveNumberPredicate;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicTestsTest {
    private PositiveNumberPredicate predicate = new PositiveNumberPredicate();

    @BeforeAll
    static void setUpClass() {
        System.out.println("@BeforeAll method");
    }

    @AfterAll
    static void tearDownClass() {
        System.out.println("@AfterAll method");
    }

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach method");
    }

    @TestFactory
    Iterator<DynamicTest> positiveNumberPredicateTestCases() {
        return Arrays.asList(
                DynamicTest.dynamicTest("negative number", () -> Assertions.assertFalse(predicate.check(-1))),
                DynamicTest.dynamicTest("zero", () -> Assertions.assertFalse(predicate.check(0))),
                DynamicTest.dynamicTest("positive number", () -> Assertions.assertTrue(predicate.check(1)))
        ).iterator();
    }
}
