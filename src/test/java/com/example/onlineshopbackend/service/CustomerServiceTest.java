package com.example.onlineshopbackend.service;

import com.example.onlineshopbackend.exception.BadRequest;
import com.example.onlineshopbackend.exception.Customer.NotFoundCustomer;
import com.example.onlineshopbackend.model.Customer;
import com.example.onlineshopbackend.model.Orders;
import com.example.onlineshopbackend.repository.CustomerRepository;
import com.example.onlineshopbackend.repository.OrdersRepository;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;


    private CustomerService underTest;

    @Autowired
    private OrdersRepository ordersRepository;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest=new CustomerService(this.customerRepository);

    }

    @Test
    void itSouldSaveCustomer(){
        Faker faker=new Faker();


        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());

        //given
        given(this.customerRepository.selectExistingEmail(customer.getEmail())).willReturn(false);

        underTest.addCustomer(customer);
        //then
        then(customerRepository).should().save(customerArgumentCaptor.capture());

        Customer cutomerCature=customerArgumentCaptor.getValue();

        assertThat(cutomerCature).isEqualTo(customer);


    }

    @Test
    void itSouldntSaveCustomer(){
        Faker faker=new Faker();

        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());

        //given
        given(this.customerRepository.selectExistingEmail(customer.getEmail())).willReturn(true);

        assertThatThrownBy(()->underTest.addCustomer(customer)).isInstanceOf(BadRequest.class).hasMessageContaining("Email: "+customer.getEmail()+" is already taken");
    }


    @Test
    void itSouldDeleteCustomer(){
        Faker faker=new Faker();


        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
    customer.setId(1L);
        //given
        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));

        underTest.deleteCustomer(1L);
        //then
        then(customerRepository).should().deleteById(1L);
    }

    @Test
    void itSouldntDeletCustomer(){
        Faker faker=new Faker();


        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        customer.setId(1L);
        //given
        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.deleteCustomer(customer.getId())).isInstanceOf(NotFoundCustomer.class).hasMessageContaining("Customer with id "+customer.getId()+" does not exist");
    }

    @Test
    void itSouldUpdateCustomer(){
        Faker faker=new Faker();


        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        Customer newCustomer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
customer.setId(1L);
newCustomer.setId(1L);



        //given
        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));

        this.underTest.updateCustomer(customer.getId(),newCustomer);

        assertThat(newCustomer).isEqualTo(customerRepository.findById(customer.getId()).get());

    }


    @Test
    void itSouldntUpdateCustomer(){
        Faker faker=new Faker();
        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        Customer newCustomer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        customer.setId(1L);
        newCustomer.setId(1L);

        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.empty());

        assertThatThrownBy(()->underTest.updateCustomer(customer.getId(),newCustomer)).isInstanceOf(NotFoundCustomer.class)
                .hasMessageContaining("Customer with id "+customer.getId()+" does not exist");
    }


    @Test
    void getCustomerById(){
        Faker faker=new Faker();
        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        customer.setId(1L);
        //given

        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));

        underTest.addCustomer(customer);
        //then
        then(customerRepository).should().save(customerArgumentCaptor.capture());

        Customer cutomerCature=customerArgumentCaptor.getValue();

        assertThat(cutomerCature).isEqualTo(customer);
    }

    @Test
    void itSouldntGetCustomerById(){
        Faker faker=new Faker();
        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        customer.setId(1L);
        //given
        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.empty());
        assertThatThrownBy(()->underTest.getCustomerById(customer.getId())).isInstanceOf(NotFoundCustomer.class)
                .hasMessageContaining("Customer with id "+customer.getId()+" does not exist");

    }

    @Test
    void itSouldAddOrder(){
        Faker faker=new Faker();
        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        customer.setId(1L);
        underTest.addCustomer(customer);
        Orders orders=new Orders(faker.number().randomDigit(),faker.address().fullAddress(), LocalDate.now(),true,customer);




        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));

        underTest.addOrders(customer.getId(),orders);


        List<Orders> aux=this.customerRepository.findById(1L).get().getOrdersList();


        assertThat(orders).isEqualTo(aux.get(0));
    }



    @Test
    void itSouldDeleteOrder(){
        Faker faker=new Faker();
        Customer customer=new Customer(faker.internet().emailAddress(),faker.internet().password(),faker.address().fullAddress());
        customer.setId(1L);
        underTest.addCustomer(customer);
        Orders orders=new Orders(faker.number().randomDigit(),faker.address().fullAddress(), LocalDate.now(),true,customer);




        given(this.customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));


    }


}