package com.jay.carwashadmin.repository;

import com.jay.carwashadmin.model.WashPack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<WashPack, Integer> {


}
