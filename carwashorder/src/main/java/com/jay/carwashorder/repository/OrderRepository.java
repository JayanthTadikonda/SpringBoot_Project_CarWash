package com.jay.carwashorder.repository;

import com.jay.carwashorder.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {

    default void deleteByOrderId(Integer integer) {

    }
}
