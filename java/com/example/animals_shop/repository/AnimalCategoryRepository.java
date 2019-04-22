package com.example.animals_shop.repository;

import com.example.animals_shop.model.AnimalCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalCategoryRepository extends JpaRepository<AnimalCategory, Integer> {


}
