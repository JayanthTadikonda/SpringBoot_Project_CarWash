package com.jay.carwashwasher.service;

import com.jay.carwashwasher.model.Order;
import com.jay.carwashwasher.model.RatingReview;
import com.jay.carwashwasher.model.Washer;
import com.jay.carwashwasher.model.WasherLeaderboard;

import java.util.List;

public interface WasherService {

    public String washRequestFromCustomer();

    public String receiveNotification() throws Exception;

    public void sendNotification(String notification);

    public Washer findByName(String name);

    public String washerChoice(Boolean option);

    public RatingReview takeRating(RatingReview ratingReview);

    public List<Order> washerOrders(String name);

    public List<WasherLeaderboard> washerLeaderboard();
}
