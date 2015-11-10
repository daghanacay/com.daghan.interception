package com.daghan.interception.provider.hotswap;

public class RandomClass {
	private String value;

	public void setValue(String value) {
		this.value = value;
		System.out.println("called the original method");
	}
}
