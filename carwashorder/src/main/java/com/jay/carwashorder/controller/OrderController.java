package com.jay.carwashorder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jay.carwashorder.model.Order;
import com.jay.carwashorder.repository.OrderRepository;
import com.jay.carwashorder.service.OrderServiceImpl;
import com.jay.carwashorder.common.TransactionRequest;
import com.jay.carwashorder.common.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order) throws JsonProcessingException {
        return orderServiceImpl.payAfterWash(order);
    }

    @PostMapping("/book-wash")
    public TransactionResponse placeOrder(@RequestBody TransactionRequest request) {
        return orderServiceImpl.saveOrder(request);
    }

    @GetMapping("/get-orders/{name}")
    public ResponseEntity<List<Order>> getOrdersByName(@PathVariable String name) {
        return new ResponseEntity<>(orderServiceImpl.getOrderListByName(name), HttpStatus.OK);
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orderList = orderRepository.findAll();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/washer-orders/{name}")
    public List<Order> getOrdersByWasherName(@PathVariable String name) {
        return orderServiceImpl.getWasherOrderListByName(name);
    }

    @PostMapping("/update-status")
    public Order updatePaymentStatus(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @RequestMapping("/test-order")
    public String testOrder() {
        return "Order service running";
    }
}
