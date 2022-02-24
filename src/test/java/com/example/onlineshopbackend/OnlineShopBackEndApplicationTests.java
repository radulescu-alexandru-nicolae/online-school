package com.example.onlineshopbackend;

import com.example.onlineshopbackend.model.Customer;
import com.example.onlineshopbackend.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
class OnlineShopBackEndApplicationTests {

    @Test
    void contextLoads() {
    }

}
