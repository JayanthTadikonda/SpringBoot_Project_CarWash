package com.jay.carwashorder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jay.carwashorder.common.TransactionRequest;
import com.jay.carwashorder.common.TransactionResponse;
import com.jay.carwashorder.model.Order;

import java.util.List;

public interface OrderService {

    public TransactionResponse saveOrder(TransactionRequest request);

    public Order payAfterWash(Order order) throws JsonProcessingException;

    public Order cancelOrder(int id);

    public List<Order> getOrderListByName(String name);

    public List<Order> getWasherOrderListByName(String name);

}
