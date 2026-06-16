package org.example.ex02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StoreTest {

    @Test
    void purchase_succeeds_when_enough_inventory() {
        Store store = new Store();
        store.addInventory(Product.SHAMPOO, 10);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertThat(success).isTrue();
        assertThat(store.getInventory(Product.SHAMPOO)).isEqualTo(5);
    }

    @Test
    void purchase_fails_when_not_enough_inventory() {
        Store store = new Store();
        store.addInventory(Product.SHAMPOO, 10);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 15);
        assertThat(success).isFalse();
        assertThat(store.getInventory(Product.SHAMPOO)).isEqualTo(10);
    }
}