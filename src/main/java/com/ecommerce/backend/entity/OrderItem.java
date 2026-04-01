//package com.ecommerce.backend.entity;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//
//@Entity
//public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(optional = false)
//    private OrderEntity order;
//
//    @ManyToOne(optional = false)
//    private Product product;
//
//    @Column(nullable = false)
//    private Integer quantity;
//
//    @Column(nullable = false, precision = 12, scale = 2)
//    private BigDecimal priceAtPurchase;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public OrderEntity getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderEntity order) {
//        this.order = order;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public BigDecimal getPriceAtPurchase() {
//        return priceAtPurchase;
//    }
//
//    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
//        this.priceAtPurchase = priceAtPurchase;
//    }
//}


package com.ecommerce.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JsonIgnore   // 🔥 prevent loop
    private OrderEntity order;

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal priceAtPurchase;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrderEntity getOrder() { return order; }
    public void setOrder(OrderEntity order) { this.order = order; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPriceAtPurchase() { return priceAtPurchase; }
    public void setPriceAtPurchase(BigDecimal priceAtPurchase) { this.priceAtPurchase = priceAtPurchase; }
}