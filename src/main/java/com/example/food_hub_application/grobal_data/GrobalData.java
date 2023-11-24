package com.example.food_hub_application.grobal_data;

import com.example.food_hub_application.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GrobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
