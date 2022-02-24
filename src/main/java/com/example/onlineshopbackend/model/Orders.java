package com.example.onlineshopbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
@Data

public class Orders {
    @Id
    @SequenceGenerator(
            name="orders_sequance",
            sequenceName = "orders_sequance",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequance"
    )
    @Column(name="id")
    private Long id;



    @NotNull(message = "Order is necessary")
    @Column(
            name="ammount"
    )
    private int ammount;


//    @NotBlank(message="order_addres is necessary")
    @Column(
            name="order_address"
    )
    private String orderAddres;


    @Column(
            name="order_date"
    )
//    @NotNull(message = "orderDate is necessary")
    private LocalDate orderDate;

    @NotNull(message = "order_status is necessary")
    @Column(
            name="order_status"
    )
    private boolean orderStatus;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(
            name="customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="customer_fk")
    )
    @JsonBackReference
    private Customer customer;


    @OneToMany(mappedBy = "orders",fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetailList=new ArrayList<>();

    public Orders(int ammount, String orderAddres, LocalDate orderDate, boolean orderStatus, Customer customer) {
        this.ammount = ammount;
        this.orderAddres = orderAddres;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }


}
