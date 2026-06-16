package org.example.ex02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreWithMockTest {

    @Mock
    Store store;

    @Test
    void purchase_succeeds_when_enough_inventory() {
        when(store.hasEnoughInventory(Product.SHAMPOO, 5)).thenReturn(true);
        when(store.getInventory(Product.SHAMPOO)).thenReturn(5);

        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 5);

        assertThat(success).isTrue();
        assertThat(store.getInventory(Product.SHAMPOO)).isEqualTo(5);

        verify(store, times(1)).hasEnoughInventory(Product.SHAMPOO, 5);
        verify(store, times(1)).getInventory(Product.SHAMPOO);
    }

    @Test
    void purchase_fails_when_not_enough_inventory() {
        when(store.hasEnoughInventory(Product.SHAMPOO, 15)).thenReturn(false);
        when(store.getInventory(Product.SHAMPOO)).thenReturn(10);

        store.addInventory(Product.SHAMPOO, 10);
        Customer customer = new Customer();

        boolean success = customer.purchase(store, Product.SHAMPOO, 15);
        assertThat(success).isFalse();
        assertThat(store.getInventory(Product.SHAMPOO)).isEqualTo(10);

        verify(store, times(1)).hasEnoughInventory(Product.SHAMPOO, 15);
        verify(store, times(1)).getInventory(Product.SHAMPOO);
    }
}