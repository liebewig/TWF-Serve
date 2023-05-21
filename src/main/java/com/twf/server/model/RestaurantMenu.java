package com.twf.server.model;

import com.twf.server.dto.RestaurantSaveRequest;
import com.twf.server.model.entity.RestaurantMenuEntity;

import lombok.Builder;

@Builder
public record RestaurantMenu(
	Long id,
	String title,
	Integer price,
	boolean isRepresent
) {
	public static RestaurantMenu toDto(RestaurantMenuEntity entity) {
		return RestaurantMenu.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.price(entity.getPrice())
			.isRepresent(entity.isRepresent())
			.build();
	}

	public static RestaurantMenu from(RestaurantSaveRequest.MenuRequest request) {
		return RestaurantMenu.builder()
			.title(request.title())
			.price(request.price())
			.isRepresent(request.isRepresent())
			.build();
	}
}
