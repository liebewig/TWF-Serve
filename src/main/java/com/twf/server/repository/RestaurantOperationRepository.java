package com.twf.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twf.server.model.entity.RestaurantOperationEntity;

public interface RestaurantOperationRepository extends JpaRepository<RestaurantOperationEntity, Long> {
}
