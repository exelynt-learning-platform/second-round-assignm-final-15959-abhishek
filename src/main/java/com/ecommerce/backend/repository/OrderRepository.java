package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.OrderEntity;
import com.ecommerce.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUser(User user);
}
