package com.twf.server.repository;

import java.util.List;

import com.twf.server.model.DayOfWeek;
import com.twf.server.model.FoodType;
import com.twf.server.model.entity.RestaurantEntity;

public interface RestaurantQuerydslRepository {
	List<RestaurantEntity> findByDayOfWeekAndPriceAndFoodType(List<DayOfWeek> daysOfWeek, Integer minPrice,
		Integer maxPrice,
		List<FoodType> foodTypes);
}
