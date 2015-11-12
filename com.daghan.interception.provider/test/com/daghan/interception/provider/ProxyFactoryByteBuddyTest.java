package com.daghan.interception.provider;

import com.daghan.interception.provider.core.LogClass;
import com.daghan.interception.provider.domain.TestClassWithSingleInterface;

import junit.framework.TestCase;

public class ProxyFactoryByteBuddyTest extends TestCase{

	public void testProxyWithBytebuddy() {

		ProxyFactoryByteBuddy unitUnderTest = new ProxyFactoryByteBuddy();
		
		
		TestClassWithSingleInterface testInstance = unitUnderTest.newInstance(TestClassWithSingleInterface.class, null);
		TestClassWithSingleInterface testInstance2 = unitUnderTest.newInstance(TestClassWithSingleInterface.class, null);

		testInstance.setAttribute(5);
		testInstance.setAttribute(3);
		testInstance2.setMultiAttribute(1, "hello");

		System.out.println("Results for test testClass : " + LogClass.getLogs(testInstance));
		System.out.println("Results for test testClass2 : " + LogClass.getLogs(testInstance2));
	}

}
