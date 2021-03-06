package com.example.animals_shop.repository;

import com.example.animals_shop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
    List <Animal> findAllByCategory_Id(int id);
}
