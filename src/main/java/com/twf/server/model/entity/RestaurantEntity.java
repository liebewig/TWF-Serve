package com.twf.server.model.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.twf.server.common.AuditingFields;
import com.twf.server.model.DayOfWeek;
import com.twf.server.model.FoodType;
import com.twf.server.model.RestaurantMenu;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant", indexes = {
	@Index(columnList = "title"),
	@Index(columnList = "foodType"),
})
@Entity
public class RestaurantEntity extends AuditingFields {

	@OneToMany(mappedBy = "restaurant", cascade = {CascadeType.ALL})
	@ToString.Exclude
	private final Set<RestaurantOperationEntity> operatingDays = new HashSet<>();
	@OneToMany(mappedBy = "restaurant", cascade = {CascadeType.ALL})
	@ToString.Exclude
	private final Set<RestaurantMenuEntity> menus = new HashSet<>();
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String title;
	@Column
	private String address;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FoodType foodType;
	@Column
	private String phone;
	@Column
	private Integer price;

	@Builder
	public RestaurantEntity(String title, String address, FoodType foodType, String phone, Integer price) {
		this.title = title;
		this.address = address;
		this.foodType = foodType;
		this.phone = phone;
		this.price = price;
	}

	public void addOperatingDays(DayOfWeek dayOfWeek) {

		this.getOperatingDays().add(new RestaurantOperationEntity(this, dayOfWeek));
	}

	public void addAllOperatingDays(Collection<DayOfWeek> dayOfWeeks) {
		Set<RestaurantOperationEntity> entities = dayOfWeeks.stream()
			.map(dayOfWeek -> new RestaurantOperationEntity(this, dayOfWeek))
			.collect(Collectors.toSet());
		this.getOperatingDays().addAll(entities);
	}

	public void addMenu(RestaurantMenu menu) {
		RestaurantMenuEntity menuEntity = RestaurantMenuEntity.from(menu, this);
		this.getMenus().add(menuEntity);
	}

	public void addAllMenus(Collection<RestaurantMenu> menus) {
		Set<RestaurantMenuEntity> entities = menus.stream()
			.map(menu -> RestaurantMenuEntity.from(menu, this))
			.collect(Collectors.toSet());
		this.getMenus().addAll(entities);
	}
}
