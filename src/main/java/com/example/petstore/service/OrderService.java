package com.example.petstore.service;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.constants.OrderStatus;
import com.example.petstore.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Optional<Order> find(long id){
        return orderRepository.findById(id);
    }

    public void delete(long id){
        orderRepository.deleteById(id);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Map<String,Long> getHashMapOfStatus(){
        List<Order> all = orderRepository.findAll();
        long placedIndex = 0;
        long approvedIndex = 0;
        long deliveredIndex = 0;
        for (Order order : all) {
            String status = order.getStatus();
            switch (status) {
                case OrderStatus.PLACED:
                    placedIndex++;
                    break;
                case OrderStatus.APPROVED:
                    approvedIndex++;
                    break;
                case OrderStatus.DELIVERED:
                    deliveredIndex++;
                    break;
            }
        }
        Map<String,Long> statusCount = new HashMap<>();
        statusCount.put(OrderStatus.PLACED, placedIndex);
        statusCount.put(OrderStatus.APPROVED, approvedIndex);
        statusCount.put(OrderStatus.DELIVERED, deliveredIndex);
        return statusCount;
    }
}
