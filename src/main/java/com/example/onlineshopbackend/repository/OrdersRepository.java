package com.example.onlineshopbackend.repository;

import com.example.onlineshopbackend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface OrdersRepository extends JpaRepository<Orders,Long> {


}
