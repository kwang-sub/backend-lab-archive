package ch04.tags.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnit5AssertJListTest {

    private List<String> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();
        values.add("Oliver");
        values.add("jack");
        values.add("Harry");
    }

    @Test
    @DisplayName("AssertJ를 이용한 테스트")
    public void testListWithAssertJ() {
        assertThat(values).size().isEqualTo(3);
        assertThat(List.of("Oliver", "jack", "Harry")).containsAll(values);
        assertThat(values).containsExactly("Oliver", "jack", "Harry");
        assertThat(values).contains("jack", "Oliver", "Harry");
    }
}
