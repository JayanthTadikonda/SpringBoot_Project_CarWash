package com.jay.carwashpayment.repository;

import com.jay.carwashpayment.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Integer> {

    @Override
    List<Payment> findAll();
}
