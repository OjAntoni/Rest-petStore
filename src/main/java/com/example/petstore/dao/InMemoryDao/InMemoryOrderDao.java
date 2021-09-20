package com.example.petstore.dao.InMemoryDao;

import com.example.petstore.dao.OrderDao;
import com.example.petstore.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryOrderDao implements OrderDao {
    private static final List<Order> orders = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong index = new AtomicLong();

    @Override
    public Order save(Order order) {
        long id = index.incrementAndGet();
        order.setId(id);
        orders.add(order);
        return order;
    }

    @Override
    public Order findById(long id) {
        for (Order order : orders) {
            if(order.getId()==id){
                return order;
            }
        }
        return null;
    }

    @Override
    public void delete(long id) {
        orders.removeIf(order -> order.getId()==id);
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }
}
