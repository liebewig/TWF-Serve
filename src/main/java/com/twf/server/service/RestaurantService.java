package com.twf.server.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twf.server.dto.RestaurantSaveRequest;
import com.twf.server.model.DayOfWeek;
import com.twf.server.model.FoodType;
import com.twf.server.model.Restaurant;
import com.twf.server.model.RestaurantMenu;
import com.twf.server.model.entity.RestaurantEntity;
import com.twf.server.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class RestaurantService {
	private final RestaurantRepository restaurantRepository;

	public Restaurant save(RestaurantSaveRequest request) {
		RestaurantEntity restaurant = RestaurantEntity.builder()
			.title(request.title())
			.price(request.price())
			.phone(request.phone())
			.address(request.address())
			.foodType(request.foodType())
			.build();
		restaurant.addAllOperatingDays(request.operatingDays());
		restaurant.addAllMenus(request.menus().stream().map(RestaurantMenu::from).toList());
		RestaurantEntity save = restaurantRepository.save(restaurant);
		return Restaurant.toDto(save, save.getMenus(), save.getOperatingDays());
	}

	public List<Restaurant> findByDayOfWeekAndPriceAndFoodType(List<DayOfWeek> daysOfWeek,
		Integer minPrice,
		Integer maxPrice,
		List<FoodType> foodTypes) {
		List<RestaurantEntity> restaurantEntities = restaurantRepository.findByDayOfWeekAndPriceAndFoodType(
			daysOfWeek, minPrice, maxPrice, foodTypes);
		return restaurantEntities.stream()
			.map(entity -> Restaurant.toDto(entity, entity.getMenus(), entity.getOperatingDays()))
			.toList();
	}
}
