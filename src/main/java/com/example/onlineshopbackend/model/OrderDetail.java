package com.example.onlineshopbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name="orderDetail")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @SequenceGenerator(
            name="orderDetail_sequance",
            sequenceName = "orderDetail_sequance",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderDetail_sequance"
    )
    @Column(name="id")
    private Long id;


    @NotBlank(message = "price is necessary")
    @Column(
            name="price"
    )
    private double price;

    @NotBlank(message = "sku is necessary")
    @Column(
            name="sku")
    private String sku;


    @NotBlank(message = "quantity is necessary")
    @Column(
            name="quantity"
    )
    private int quantity;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="product_fk")
    )
    @JsonBackReference
    private Product product;


    @ManyToOne(
            cascade = CascadeType.ALL,fetch = FetchType.EAGER
    )
    @JoinColumn(name="ordersDetail_id",
    referencedColumnName = "id",
            foreignKey = @ForeignKey(name="order_fk")
    )
    @JsonBackReference
    private Orders orders;




}
