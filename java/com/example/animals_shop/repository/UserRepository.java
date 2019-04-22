package com.example.animals_shop.repository;

import com.example.animals_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    void deleteById(int id);

    User findByEmail(String email);
}
