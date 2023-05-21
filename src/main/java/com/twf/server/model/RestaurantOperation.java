package com.twf.server.model;

import com.twf.server.model.entity.RestaurantOperationEntity;

import lombok.Builder;

@Builder
public record RestaurantOperation(
	Long id,
	DayOfWeek dayOfWeek
) {
	public static RestaurantOperation toDto(RestaurantOperationEntity entity) {
		return RestaurantOperation.builder()
			.id(entity.getId())
			.dayOfWeek(entity.getDayOfWeek())
			.build();
	}
}
