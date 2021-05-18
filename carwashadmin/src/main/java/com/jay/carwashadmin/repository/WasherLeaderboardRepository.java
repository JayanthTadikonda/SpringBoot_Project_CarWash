package com.jay.carwashadmin.repository;

import com.jay.carwashadmin.model.WasherLeaderboard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WasherLeaderboardRepository extends MongoRepository<WasherLeaderboard, Integer> {

}
