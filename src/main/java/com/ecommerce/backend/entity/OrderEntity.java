//package com.ecommerce.backend.entity;
//
//import com.ecommerce.backend.entity.enums.OrderStatus;
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "orders")
//public class OrderEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(optional = false)
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderItem> items = new ArrayList<>();
//
//    @Column(nullable = false, precision = 12, scale = 2)
//    private BigDecimal totalPrice;
//
//    @Column(nullable = false)
//    private String shippingAddress;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private OrderStatus status = OrderStatus.PENDING;
//
//    private String paymentReference;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<OrderItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<OrderItem> items) {
//        this.items = items;
//    }
//
//    public BigDecimal getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public String getShippingAddress() {
//        return shippingAddress;
//    }
//
//    public void setShippingAddress(String shippingAddress) {
//        this.shippingAddress = shippingAddress;
//    }
//
//    public OrderStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(OrderStatus status) {
//        this.status = status;
//    }
//
//    public String getPaymentReference() {
//        return paymentReference;
//    }
//
//    public void setPaymentReference(String paymentReference) {
//        this.paymentReference = paymentReference;
//    }
//}

package com.ecommerce.backend.entity;

import com.ecommerce.backend.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JsonIgnore   // 🔥 prevent loop
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    private String paymentReference;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public String getPaymentReference() { return paymentReference; }
    public void setPaymentReference(String paymentReference) { this.paymentReference = paymentReference; }
}
