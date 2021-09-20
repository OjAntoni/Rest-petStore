package com.example.petstore.dao;

import com.example.petstore.entity.Order;

import java.util.List;

public interface OrderDao {
    Order save(Order order);
    Order findById(long id);
    void delete(long id);
    List<Order> getAll();
}
