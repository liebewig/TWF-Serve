package com.twf.server.repository;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.twf.server.model.DayOfWeek;
import com.twf.server.model.FoodType;
import com.twf.server.model.entity.QRestaurantEntity;
import com.twf.server.model.entity.QRestaurantMenuEntity;
import com.twf.server.model.entity.QRestaurantOperationEntity;
import com.twf.server.model.entity.RestaurantEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantQuerydslRepositoryImpl implements RestaurantQuerydslRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<RestaurantEntity> findByDayOfWeekAndPriceAndFoodType(List<DayOfWeek> daysOfWeek, Integer minPrice,
		Integer maxPrice,
		List<FoodType> foodTypes) {
		QRestaurantEntity restaurant = QRestaurantEntity.restaurantEntity;
		QRestaurantMenuEntity menu = QRestaurantMenuEntity.restaurantMenuEntity;
		QRestaurantOperationEntity operation = QRestaurantOperationEntity.restaurantOperationEntity;

		BooleanExpression priceCondition = null;
		if (!ObjectUtils.isEmpty(minPrice) && !ObjectUtils.isEmpty(maxPrice)) {
			priceCondition = restaurant.price.between(minPrice, maxPrice);
		} else if (!ObjectUtils.isEmpty(minPrice)) {
			priceCondition = restaurant.price.goe(minPrice);
		} else if (!ObjectUtils.isEmpty(maxPrice)) {
			priceCondition = restaurant.price.loe(maxPrice);
		}

		JPAQuery<RestaurantEntity> query = queryFactory.selectDistinct(restaurant)
			.from(restaurant);
		if (!ObjectUtils.isEmpty(daysOfWeek)) {
			query.where(
				restaurant.id.in(
					JPAExpressions.select(operation.restaurant.id).from(operation)
						.where(operation.dayOfWeek.in(daysOfWeek))
				)
			);
		}
		if (!ObjectUtils.isEmpty(foodTypes)) {
			query.where(restaurant.foodType.in(foodTypes));
		}
		if (priceCondition != null) {
			query.where(priceCondition);
		}
		return query.distinct().fetch();
	}
}
