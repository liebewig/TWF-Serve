package com.twf.server.dto;

import java.util.List;
import java.util.Set;

import com.twf.server.model.DayOfWeek;
import com.twf.server.model.FoodType;

public record RestaurantSaveRequest(
	String title,
	String address,
	String phone,
	List<DayOfWeek> operatingDays,
	FoodType foodType,
	Integer price,
	List<MenuRequest> menus
) {
	public record MenuRequest(
		String title,
		int price,
		boolean isRepresent
	) {
	}
}
