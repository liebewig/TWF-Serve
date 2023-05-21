package com.twf.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twf.server.model.entity.RestaurantMenuEntity;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenuEntity, Long> {
}
