package ch04.lifecycle;

import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnit4SUTTest {

    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

    @BeforeClass
    public static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

    @AfterClass
    public static void tearDownClass() {
        resourceForAllTests.close();
    }

    @Before
    public void setUp() {
        systemUnderTest = new SUT("테스트 대상 시스템");
    }

    @After
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
    @Ignore
    public void myThirdTest() {
        assertThat(2).isEqualTo(1);
    }
}