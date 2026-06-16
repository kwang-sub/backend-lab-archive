package org.example.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiAllraMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAllraMarketApplication.class, args);
    }

}
