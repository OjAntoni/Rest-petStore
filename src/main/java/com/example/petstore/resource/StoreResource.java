package com.example.petstore.resource;

import com.example.petstore.entity.Order;
import com.example.petstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreResource {
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        if(order==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Order savedOrder = orderService.save(order);
            return ResponseEntity.status(HttpStatus.OK).body(savedOrder);
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable long orderId){
        Optional<Order> order = orderService.find(orderId);
        if(orderId<=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            if(order.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(order.get());
            }
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable long orderId){
        if(orderId<=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(orderService.find(orderId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        orderService.delete(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //todo ???
    // The server cannot or will not process the request due to something that is perceived to be a
    // client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).
    @GetMapping("/inventory")
    public ResponseEntity<Map<String,Long>> getStatusMap(){
        return ResponseEntity.ok(orderService.getHashMapOfStatus());
    }
}
