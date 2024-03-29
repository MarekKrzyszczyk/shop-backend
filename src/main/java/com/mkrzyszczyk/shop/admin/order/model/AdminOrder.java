package com.mkrzyszczyk.shop.admin.order.model;

import com.mkrzyszczyk.shop.common.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class AdminOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime placementDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<AdminOrderRow> orderRows;
    private BigDecimal grossValue;
    private String firstname;
    private String lastname;
    private String street;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    @OneToOne
    private AdminPayment payment;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<AdminOrderLog> orderLogs;
}
