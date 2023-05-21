package com.twf.server.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.twf.server.model.FoodType;
import com.twf.server.model.Restaurant;
import com.twf.server.model.RestaurantMenu;
import com.twf.server.model.RestaurantOperation;

import lombok.Builder;

@Builder
public record RestaurantResponse(
	Long id,
	String title,
	String address,
	String foodType,
	String phone,
	Integer price,
	Set<MenuResponse> menus,
	Set<OperationResponse> operations
) {
	public static RestaurantResponse from(Restaurant restaurant) {
		return RestaurantResponse.builder()
			.id(restaurant.id())
			.title(restaurant.title())
			.address(restaurant.address())
			.foodType(restaurant.foodType().getKoreanName())
			.phone(restaurant.phone())
			.price(restaurant.price())
			.menus(restaurant.menus().stream().map(MenuResponse::from).collect(Collectors.toUnmodifiableSet()))
			.operations(
				restaurant.operations().stream().map(OperationResponse::from).collect(Collectors.toUnmodifiableSet()))
			.build();
	}

	public record MenuResponse(
		Long id,
		String title,
		int price,
		boolean isRepresent
	) {
		public static MenuResponse from(RestaurantMenu dto) {
			return new MenuResponse(dto.id(), dto.title(), dto.price(), dto.isRepresent());
		}
	}

	public record OperationResponse(
		String operationDay
	) {
		public static OperationResponse from(RestaurantOperation dto) {
			return new OperationResponse(dto.dayOfWeek().getKoreanName());
		}
	}
}
