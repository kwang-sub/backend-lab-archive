package org.example.ex06;

import org.example.ex05.Controller;
import org.example.ex05.IDatabase;
import org.example.ex05.IEmailGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Mock
    private IEmailGateway gateway;

    @Mock
    private IDatabase database;

    @Test
    void discount_of_two_products() {
        // given
        Product product1 = new Product("Hand wash");
        Product product2 = new Product("Shampoo");
        PriceEngine sut = new PriceEngine();

        // when
        double discount = sut.calculateDiscount(product1, product2);

        // then
        assertThat(discount).isEqualTo(0.02);
    }

    @Test
    void adding_a_product_to_an_order() {
        // given
        Product product = new Product("Hand wash");
        Order order = new Order();

        // when
        order.addProduct(product);

        // then
        assertThat(order.getProducts()).hasSize(1);
        assertThat(order.getProducts()).containsExactly(product);
    }

    void sending_a_greetings_email() {
        // given
        Controller controller = new Controller(gateway, database);

        // when
        controller.greetUser("user@email.com");
        // then
        Mockito.verify(gateway, Mockito.times(1))
                .sendGreetingsEmail("user@email.com");
    }
}