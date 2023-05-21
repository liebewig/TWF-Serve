package com.twf.server.model;

public enum FoodType {
	KOREA("한식"),
	JAPAN("일식"),
	CHINA("중식"),
	AMERICA("양식"),
	ASIA("아시안식");

	private final String koreanName;

	FoodType(String koreanName) {
		this.koreanName = koreanName;
	}

	public String getKoreanName() {
		return koreanName;
	}
}
