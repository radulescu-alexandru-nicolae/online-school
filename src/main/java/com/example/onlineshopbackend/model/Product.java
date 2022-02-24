package com.example.onlineshopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {

    @Id
    @SequenceGenerator(
            name="product_sequance",
            sequenceName = "product_sequance",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequance"
    )
    @Column(
            name="id"
    )
    private long id;


    @NotBlank(message = "name is necessary")
    @Column(
            name="name"
    )
    private String name;

    @NotNull(message = "price is necessary")
    @Column(name="price")
    private double price;

    @NotNull(message = "stock is necessary")
    @Column(name="stock")
    private int stock;

    @NotNull(message = "category is necessary")
    @Column(name="category")
    private String category;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="orderDetail_id")
    private List<OrderDetail> list=new ArrayList<>();

}
