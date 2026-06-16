package org.example.market.factory;

import org.example.market.dto.request.OrderCartReqDto;
import org.example.market.dto.request.OrderReqDto;
import org.example.market.dto.response.PaymentResDto;
import org.example.market.entity.Cart;
import org.example.market.entity.Customer;
import org.example.market.entity.Order;
import org.example.market.entity.Product;
import org.example.market.enums.OrderStatus;
import org.example.market.enums.TransactionStatus;
import org.example.market.repository.CartRepository;
import org.example.market.repository.CustomerRepository;
import org.example.market.repository.OrderRepository;
import org.example.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@TestComponent
public class ObjectFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Customer createCustomer(final String name, final String password) {
        Customer customer = Customer.create(name, passwordEncoder.encode(password));
        customerRepository.save(customer);

        return customer;
    }

    public List<Product> createProducts(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> this.createProduct(
                        "상품" + i,
                        null,
                        BigDecimal.valueOf(1000).multiply(BigDecimal.valueOf(i)),
                        i + 1))
                .toList();
    }

    public Product createProduct(final String name, final String description, BigDecimal price, Integer stock) {
        Product product = Product.create(name, description, price, stock);
        productRepository.saveAndFlush(product);

        return product;
    }

    public Cart createCart(Customer customer, Product product, int quantity) {
        Cart cart = Cart.create(customer, product, quantity);
        cartRepository.save(cart);

        return cart;
    }

    public List<Cart> createCarts(Customer customer, List<Product> products, int quantity) {
        return products.stream()
                .map(it -> this.createCart(customer, it, quantity))
                .toList();
    }

    public OrderReqDto createOrderReqDto(List<Cart> carts) {
        Set<OrderCartReqDto> orderCarts = carts.stream().map(this::createOrderCartReqDto)
                .collect(Collectors.toSet());

        BigDecimal totalAmount = orderCarts.stream().map(cart -> cart.price().multiply(BigDecimal.valueOf(cart.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        return new OrderReqDto(totalAmount, orderCarts);
    }

    public OrderCartReqDto createOrderCartReqDto(Cart cart) {
        return new OrderCartReqDto(
                cart.getId(),
                cart.getProduct().getId(),
                cart.getQuantity(),
                cart.getProduct().getPrice()
        );
    }

    public PaymentResDto createPaymentResDto(TransactionStatus status) {
        return switch (status) {
            case SUCCESS ->
                    new PaymentResDto(TransactionStatus.SUCCESS, "txn_1233456", "Payment processed successfully");
            case FAILED -> new PaymentResDto(TransactionStatus.FAILED, null, "Payment processed failed");
        };
    }

    public Order createOrder(Customer customer, BigDecimal amount) {
        Order order = Order.create(customer, amount);
        orderRepository.save(order);

        return order;
    }

    public List<Order> createCompleteOrders(Customer customer, int size) {
        List<Order> completeOrders = IntStream.range(0, size)
                .mapToObj(it -> {
                    Order order = createOrder(customer, BigDecimal.valueOf(1000));
                    order.changeOrderStatus(OrderStatus.PAYMENT_COMPLETE);
                    return order;
                })
                .toList();
        orderRepository.saveAll(completeOrders);

        return completeOrders;
    }
}
