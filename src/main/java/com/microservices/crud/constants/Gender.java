package com.microservices.crud.constants;

public enum Gender {
	MALE("M"), FEMALE("F");

	private final String gender;

	Gender(String gender) {
		this.gender = gender;
	}
}
