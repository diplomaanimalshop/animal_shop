package com.example.animals_shop.repository;

import com.example.animals_shop.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Integer> {

    List<Feed> findAllByUserId (int id);

    void deleteById(int id);
}
