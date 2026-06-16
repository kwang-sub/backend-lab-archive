package com.example.ch06;

import com.example.ch06.model.*;
import com.example.ch06.repository.ItemRepository;
import com.example.ch06.repository.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@SpringBootTest
@Transactional
public class MappingValuesSpringDataJPATest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {
        Address homeAddress = Address.create("Flowers Street", "12345", "Boston", "London");
        User user = User.builder()
                .name("username")
                .homeAddress(homeAddress)
                .billingAddress(homeAddress)
                .build();
        userRepository.save(user);

        Item item = Item.builder()
                .name("Some Item")
                .metricWeight(2)
                .description("Some description")
                .buyNowPrice(
                        MonetaryAmount.builder()
                                .value(BigDecimal.valueOf(1.1))
                                .currency(Currency.getInstance("USD"))
                                .build()
                )
                .build();
        itemRepository.save(item);

        List<User> users = (List<User>) userRepository.findAll();
        List<Item> items = (List<Item>) itemRepository.findByMetricWeight(2.0);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(users).hasSize(1);
        softAssertions.assertThat(users.get(0).getName()).isEqualTo("username");
        softAssertions.assertThat(users.get(0).getHomeAddress().getStreet()).isEqualTo("Flowers Street");
        softAssertions.assertThat(users.get(0).getHomeAddress().getCity().getName()).isEqualTo("Boston");
        softAssertions.assertThat(users.get(0).getHomeAddress().getCity().getZipcode().getClass()).isEqualTo(GermanZipcode.class);


        softAssertions.assertThat(items).hasSize(1);
        softAssertions.assertThat(items.get(0).getName()).isEqualTo("Some Item");
        softAssertions.assertThat(items.get(0).getBuyNowPrice().toString()).isEqualTo("1.1 USD");

        softAssertions.assertAll();
    }
}
