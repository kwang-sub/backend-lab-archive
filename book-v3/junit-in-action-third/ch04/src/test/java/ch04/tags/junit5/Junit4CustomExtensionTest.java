package ch04.tags.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CustomExtension.class)
public class Junit4CustomExtensionTest {
    @Test
    void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
