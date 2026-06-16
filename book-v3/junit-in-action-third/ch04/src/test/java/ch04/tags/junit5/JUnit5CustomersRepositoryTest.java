package ch04.tags.junit5;

import ch04.tags.Customer;
import ch04.tags.CustomersRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("repository")
public class JUnit5CustomersRepositoryTest {

    private String CUSTOMER_NAME = "John Doe";
    private CustomersRepository repository = new CustomersRepository();

    @Test
    public void testNonExistence() {
        boolean exists = repository.contains(CUSTOMER_NAME);
        assertThat(exists).isFalse();
    }

    @Test
    public void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));
        assertThat(repository.contains(CUSTOMER_NAME)).isTrue();
    }
}
