package com.twf.server.model.entity;

import com.twf.server.model.RestaurantMenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant_menu", indexes = {
	@Index(columnList = "title"),
})
@Entity
public class RestaurantMenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private Integer price;

	@Column
	private boolean isRepresent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id", nullable = false)
	@ToString.Exclude
	private RestaurantEntity restaurant;

	@Builder
	public RestaurantMenuEntity(String title, Integer price, boolean isRepresent, RestaurantEntity restaurant) {
		this.title = title;
		this.price = price;
		this.isRepresent = isRepresent;
		this.restaurant = restaurant;
	}

	public static RestaurantMenuEntity from(RestaurantMenu dto) {
		return RestaurantMenuEntity.builder()
			.title(dto.title())
			.price(dto.price())
			.isRepresent(dto.isRepresent())
			.build();
	}

	public static RestaurantMenuEntity from(RestaurantMenu dto, RestaurantEntity restaurant) {
		return RestaurantMenuEntity.builder()
			.title(dto.title())
			.price(dto.price())
			.isRepresent(dto.isRepresent())
			.restaurant(restaurant)
			.build();
	}
}
