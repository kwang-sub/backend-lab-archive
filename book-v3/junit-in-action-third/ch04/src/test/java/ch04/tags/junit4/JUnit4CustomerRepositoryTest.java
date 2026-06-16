package ch04.tags.junit4;

import ch04.tags.Customer;
import ch04.tags.CustomersRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.*;

@Category({IndividualTests.class, RepositoryTests.class})
public class JUnit4CustomerRepositoryTest {
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
