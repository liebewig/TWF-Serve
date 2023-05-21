package com.twf.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twf.server.model.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>, RestaurantQuerydslRepository {
}
