package com.twf.server.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.twf.server.model.entity.RestaurantEntity;
import com.twf.server.model.entity.RestaurantMenuEntity;
import com.twf.server.model.entity.RestaurantOperationEntity;

import lombok.Builder;

@Builder
public record Restaurant(
	Long id,
	String title,
	String address,
	FoodType foodType,
	String phone,
	Integer price,
	Set<RestaurantMenu> menus,
	Set<RestaurantOperation> operations
) {
	public static Restaurant toDto(RestaurantEntity entity) {
		return Restaurant.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.address(entity.getAddress())
			.foodType(entity.getFoodType())
			.phone(entity.getPhone())
			.price(entity.getPrice())
			.build();
	}

	public static Restaurant toDto(RestaurantEntity entity, Set<RestaurantMenuEntity> menuEntities,
		Set<RestaurantOperationEntity> operationEntities) {
		return Restaurant.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.address(entity.getAddress())
			.foodType(entity.getFoodType())
			.phone(entity.getPhone())
			.price(entity.getPrice())
			.menus(menuEntities.stream().map(RestaurantMenu::toDto).collect(Collectors.toUnmodifiableSet()))
			.operations(
				operationEntities.stream().map(RestaurantOperation::toDto).collect(Collectors.toUnmodifiableSet()))
			.build();
	}
}
