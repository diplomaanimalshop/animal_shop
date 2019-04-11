package com.example.animals_shop.repository;

import com.example.animals_shop.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed,Integer> {
}
