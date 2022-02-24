package com.example.onlineshopbackend.service;

import com.example.onlineshopbackend.exception.BadRequest;
import com.example.onlineshopbackend.exception.Customer.NotFoundCustomer;
import com.example.onlineshopbackend.model.Customer;
import com.example.onlineshopbackend.model.Orders;
import com.example.onlineshopbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void addCustomer(Customer customer){
        boolean aux=this.customerRepository.selectExistingEmail(customer.getEmail());

        if(!aux){
            customerRepository.save(customer);
        }else{
            throw new BadRequest("Email: "+customer.getEmail()+" is already taken");
        }

    }

    public void deleteCustomer(Long id){
        if(!customerRepository.findById(id).isEmpty()){
            customerRepository.deleteById(id);
        }else{
            throw new NotFoundCustomer("Customer with id "+ id+ " does not exist");
        }
    }
    public void updateCustomer(Long id,Customer newCustomer){

        Optional<Customer> aux=this.customerRepository.findById(id);
        if(!aux.isEmpty()){
            Customer oldCustomer=aux.get();
            oldCustomer.setAddres(newCustomer.getAddres());
            oldCustomer.setEmail(newCustomer.getEmail());
            oldCustomer.setPassword(newCustomer.getPassword());
        }else{
            throw new NotFoundCustomer("Customer with id "+ id+ " does not exist");
        }
    }

    public Customer getCustomerById(Long id){
        Optional<Customer> aux=this.customerRepository.findById(id);

        if(!aux.isEmpty()){
            return aux.get();
        }else{
            throw new NotFoundCustomer("Customer with id "+id+" does not exist");
        }
    }

    public List<Orders> allOrders(Long customerId){
        Optional<Customer> aux=this.customerRepository.findById(customerId);

        if(!aux.isEmpty()){
            return aux.get().getOrdersList();
        }else{
            throw new NotFoundCustomer("Customer with id: "+customerId+" does not exist");
        }
    }
    public void addOrders(Long customerId,Orders orders){
        Optional<Customer> optionalCustomer=this.customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new NotFoundCustomer("Customer with id: "+customerId+" does not exist");
        }else{
            optionalCustomer.get().addOrder(orders);
            this.customerRepository.save(optionalCustomer.get());
        }
    }



}
