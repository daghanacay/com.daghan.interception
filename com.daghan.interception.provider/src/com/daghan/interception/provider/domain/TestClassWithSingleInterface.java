package com.daghan.interception.provider.domain;

public class TestClassWithSingleInterface implements SingleInterface {
	int intAttribute;
	String strAttribute;

	public void setAttribute(Integer intAttribute) {
		this.intAttribute = intAttribute;
	}

	public void setMultiAttribute(Integer intAttribute, String strAttribute) {
		this.intAttribute = intAttribute;
		this.strAttribute = strAttribute;
	}
}
