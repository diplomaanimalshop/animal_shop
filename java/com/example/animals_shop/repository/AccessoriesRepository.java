package com.example.animals_shop.repository;

import com.example.animals_shop.model.Accessories;
import com.example.animals_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessoriesRepository extends JpaRepository<Accessories, Integer> {

    List<Accessories> findAllByUserId(int id);
    void deleteById(int id);
}
