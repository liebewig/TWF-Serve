package com.twf.server.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twf.server.dto.RestaurantResponse;
import com.twf.server.dto.RestaurantSaveRequest;
import com.twf.server.model.DayOfWeek;
import com.twf.server.model.FoodType;
import com.twf.server.model.Restaurant;
import com.twf.server.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {
	private final RestaurantService restaurantService;

	@PostMapping()
	public RestaurantResponse save(@RequestBody RestaurantSaveRequest request) {
		Restaurant save = restaurantService.save(request);
		return RestaurantResponse.from(save);
	}

	@GetMapping()
	public List<RestaurantResponse> findByDayOfWeekAndPriceAndFoodType(
		@RequestParam(required = false) List<DayOfWeek> dayOfWeeks,
		@RequestParam(required = false) Integer minPrice,
		@RequestParam(required = false) Integer maxPrice,
		@RequestParam(required = false) List<FoodType> foodTypes
	) {
		List<Restaurant> find = restaurantService.findByDayOfWeekAndPriceAndFoodType(dayOfWeeks, minPrice, maxPrice,
			foodTypes);
		return find.stream().map(RestaurantResponse::from).toList();
	}
}
