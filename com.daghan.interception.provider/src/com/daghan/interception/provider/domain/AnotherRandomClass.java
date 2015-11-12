package com.daghan.interception.provider.domain;

public class AnotherRandomClass {
	private String value;

	public void setValue(String value) {
		this.value = value;
		System.out.println("called the original method");
	}

	public void doSomething() {
		System.out.println("called the original method");
	}
}
