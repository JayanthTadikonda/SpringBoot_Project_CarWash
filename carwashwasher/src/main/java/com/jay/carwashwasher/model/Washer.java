package com.jay.carwashwasher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "washer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Washer {

    @Id
    private ObjectId _id;
    private String name;
    private String password;
    private List<String> address;
    private List<RatingReview> ratingReviewList;
    private String role;

}
