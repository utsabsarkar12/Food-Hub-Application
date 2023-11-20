package com.example.food_hub_application.repository;

import com.example.food_hub_application.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
