package com.daghan.interception.hotswap;

import org.junit.Test;

import com.daghan.interception.domain.RandomClass;

public class ProxyFactoryByteBuddyTest {

	@Test
	public void testProxyWithBytebuddy() {

		ProxyFactoryByteBuddy unitUnderTest = new ProxyFactoryByteBuddy();
		
		
		RandomClass testInstance = unitUnderTest.newInstance(RandomClass.class, null);
		RandomClass testInstance2 = unitUnderTest.newInstance(RandomClass.class, null);

		testInstance.setValue("hello1");
		testInstance.setValue("hello2");
		testInstance.setValue("hello3");

		testInstance2.calculateValue("hello2", 2);
		testInstance2.calculateValue("hello3", 3);
		testInstance2.calculateValue("hello4", 4);

		System.out.println("Results for test testClass : " + LogClass.getLogs(testInstance));
		System.out.println("Results for test testClass2 : " + LogClass.getLogs(testInstance2));
	}

}
