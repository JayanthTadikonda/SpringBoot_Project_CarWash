package com.jay.carwashwasher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOn {

    private String addOnName;
    private double amount;
}
