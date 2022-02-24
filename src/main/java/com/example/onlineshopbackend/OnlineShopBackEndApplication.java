package com.example.onlineshopbackend;
import com.example.onlineshopbackend.service.CustomerService;
import com.github.javafaker.Faker;

import com.example.onlineshopbackend.model.Customer;
import com.example.onlineshopbackend.model.Orders;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class OnlineShopBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopBackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService){
        return  args -> {
            Faker faker=new Faker();
            Customer customer=customerService.getCustomerById(1L);

            Orders orders=new Orders(faker.number().randomDigit(),faker.address().fullAddress(), LocalDate.now(),true,customer);
//            System.out.println(orders);
//    customerService.addOrders(customer.getId(),orders);

            System.out.println(customer.getOrdersList());


        };
    }

}
