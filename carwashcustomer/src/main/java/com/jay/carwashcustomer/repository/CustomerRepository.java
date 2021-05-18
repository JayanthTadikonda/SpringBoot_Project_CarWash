package com.jay.carwashcustomer.repository;

import com.jay.carwashcustomer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {

    public List<Customer> findAll();
    public Customer findByName(String name);
    public Customer findById(int id);

}
