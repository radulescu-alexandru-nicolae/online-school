package com.example.onlineshopbackend.repository;

import com.example.onlineshopbackend.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository underTest;

    @AfterEach
    void tearDown(){underTest.deleteAll();}


    @Test
    void itSouldCheckWhenCustomerEmailExist(){
        String email="asd@gmail.com";
        Customer customer=new Customer(email,"asd","Craiova");
        underTest.save(customer);
        boolean expect=underTest.selectExistingEmail(email);

        assertThat(expect).isTrue();
    }
    @Test
    void itSouldntCheckWhenCustomerEmailExist(){
        String email="asd@gmail.com";
        Customer customer=new Customer(email,"asd","Craiova");

        boolean expect=underTest.selectExistingEmail(email);

        assertThat(expect).isFalse();
    }

    @Test
    void itSouldSelectCustomerByEmail(){
        String email="asd@gmail.com";
        Customer customer=new Customer(email,"asd","Craiova");
        underTest.save(customer);


        Customer aux=underTest.findCustomerByEmail(customer.getEmail()).get();
       assertThat(aux.equals(customer));
    }
}