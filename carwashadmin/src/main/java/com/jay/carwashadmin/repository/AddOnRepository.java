package com.jay.carwashadmin.repository;

import com.jay.carwashadmin.model.AddOn;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddOnRepository extends MongoRepository<AddOn, Integer> {

}
