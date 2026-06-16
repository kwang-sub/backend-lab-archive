package ch04.lifecycle;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnit5SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

    @BeforeAll
    public static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

    @AfterAll
    public static void tearDownClass() {
        resourceForAllTests.close();
    }

    @BeforeEach
    public void setUp() {
        systemUnderTest = new SUT("테스트 대상 시스템");
    }

    @AfterEach
    public void tearDown() {
        systemUnderTest.close();
    }

    @Test
    public void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        assertThat(canReceiveRegularWork).isTrue();
    }

    @Test
    public void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        assertThat(canReceiveAdditionalWork).isFalse();
    }

    @Test
    @Disabled
    public void myThirdTest() {
        assertThat(2).isEqualTo(1);
    }
}
