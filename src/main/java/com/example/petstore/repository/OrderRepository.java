package com.example.petstore.repository;

import com.example.petstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
