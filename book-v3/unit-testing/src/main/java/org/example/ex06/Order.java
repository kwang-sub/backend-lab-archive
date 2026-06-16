package org.example.ex06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Order {
    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}