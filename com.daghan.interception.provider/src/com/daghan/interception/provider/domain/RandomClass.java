package com.daghan.interception.provider.domain;

import com.daghan.interception.provider.core.InterceptMethod;

public class RandomClass {
	private String value;

	@InterceptMethod
	public void setValue(String value) {
		this.value = value;
		System.out.println("called original setValue(String value) method");
	}
	
	@InterceptMethod
	public void calculateValue(String value, int val2) {
		this.value = value;
		System.out.println("called the original calculateValue(String value, int val2) method");
	}
}
