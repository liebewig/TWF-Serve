package com.twf.server.model.entity;

import com.twf.server.common.AuditingFields;
import com.twf.server.model.DayOfWeek;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant_operation", indexes = {
	@Index(columnList = "dayOfWeek"),
})
@Entity
public class RestaurantOperationEntity extends AuditingFields {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id", nullable = false)
	@ToString.Exclude
	private RestaurantEntity restaurant;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private DayOfWeek dayOfWeek;

	public RestaurantOperationEntity(RestaurantEntity restaurant, DayOfWeek dayOfWeek) {
		this.restaurant = restaurant;
		this.dayOfWeek = dayOfWeek;
	}
}
