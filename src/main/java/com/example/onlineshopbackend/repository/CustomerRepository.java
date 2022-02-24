package com.example.onlineshopbackend.repository;

import com.example.onlineshopbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly=true)
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query( " SELECT CASE WHEN COUNT(c) >0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.email=?1")
    Boolean selectExistingEmail(String email);


    @Query("SELECT c FROM Customer c WHERE  c.email=?1")
    Optional<Customer> findCustomerByEmail(String email);




}
