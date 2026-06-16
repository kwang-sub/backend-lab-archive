package org.example.market.domain;

import org.example.market.common.ApplicationConstant;
import org.example.market.entity.Product;
import org.example.market.factory.ObjectFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Import(ObjectFactory.class)
public class ProductTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectFactory objectFactory;

    @Test
    @WithMockUser
    void 상품_목록_조회할_수_있다() throws Exception {
        // given
        List<Product> products = objectFactory.createProducts(30);
        Product maxIdProduct = products.stream()
                .max(Comparator.comparing(Product::getId))
                .orElse(null);

        // when
        mvc.perform(get("/api/v1/products")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,desc")
                )

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(10))
                .andExpect(jsonPath("$.content[0].id").value(maxIdProduct.getId()))
                .andExpect(jsonPath("$.totalElements").value(30));
    }

    @Test
    @WithMockUser
    void 수량이_0개이하인_상품은_목록조회_되면_안된다() throws Exception {
        // given
        objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), 0);

        // when
        mvc.perform(get("/api/v1/products"))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(0))
                .andExpect(jsonPath("$.totalElements").value(0));
    }

    @Test
    @WithMockUser
    void 최소_수량_상품은_목록조회된다() throws Exception {
        // given
        objectFactory.createProduct("상품", null, BigDecimal.valueOf(1000), ApplicationConstant.Product.MINIMUM_ORDER_QUANTITY);

        // when
        mvc.perform(get("/api/v1/products"))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }
}
