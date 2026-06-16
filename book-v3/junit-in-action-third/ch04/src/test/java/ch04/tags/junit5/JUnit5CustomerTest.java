package ch04.tags.junit5;

import ch04.tags.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("individual")
public class JUnit5CustomerTest {
    private String CUSTOMER_NAME = "John Doe";

    @Test
    void testCustomer() {
        Customer customer = new Customer(CUSTOMER_NAME);
        assertThat(customer.getName()).isEqualTo("John Doe");
    }
}
