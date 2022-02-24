package com.example.onlineshopbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name="customer_sequance",
            sequenceName = "customer_sequance",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator="customer_sequance"
    )
    @Column(name="id")
    private long id;

    @Email
    @NotBlank(message = "Email is necessary")
    @Column(name = "email")
    private String email;


    @NotBlank(message = "Password is necessary")
    private String password;



    @NotBlank(message = "Addres is necessary")
    private String addres;

@OneToMany(cascade ={CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
@JoinColumn(name="orders_id")
@JsonBackReference

    private List<Orders> ordersList =new ArrayList<>();

    public Customer(String email, String password, String addres) {
        this.email = email;
        this.password = password;
        this.addres = addres;
    }
//add order
//update order
//delete order

    @Transactional

    public void addOrder(Orders order){
      ordersList.add(order);

    }

    public void deleteOrder(Orders order){
        if(ordersList.contains(order)){
            this.ordersList.remove(order);
        }else{
            System.out.println("Nu s-a gasit order");
        }
    }





}
