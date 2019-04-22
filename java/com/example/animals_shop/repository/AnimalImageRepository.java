package com.example.animals_shop.repository;

import com.example.animals_shop.model.AnimalImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalImageRepository extends JpaRepository<AnimalImage, Integer> {
}
