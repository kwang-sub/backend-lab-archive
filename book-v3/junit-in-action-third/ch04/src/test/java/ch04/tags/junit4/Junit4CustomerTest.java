package ch04.tags.junit4;

import ch04.tags.Customer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.*;

public class Junit4CustomerTest {
    private String CUSTOMER_NAME = "John Smith";

    @Category(IndividualTests.class)
    @Test
    public void testCustomer() {
        Customer customer = new Customer(CUSTOMER_NAME);
        assertThat(customer.getName()).isEqualTo(CUSTOMER_NAME);
    }
}