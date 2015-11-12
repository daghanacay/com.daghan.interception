package com.daghan.interception.provider;

import java.util.List;

import com.daghan.interception.api.ProxyFactory;
import com.daghan.interception.provider.domain.SingleInterface;
import com.daghan.interception.provider.domain.TestClassWithSingleInterface;

import junit.framework.TestCase;
import net.bytebuddy.ByteBuddy;

public class ProxyFactoryImplTest extends TestCase {

	public void testProxyMethodNoInterface() {
		ProxyFactory unitUnderTest = new ProxyFactoryImpl();

		SingleInterface proxiedClass = unitUnderTest.newInstance(SingleInterface.class,
				new TestClassWithSingleInterface());

		proxiedClass.setAttribute(5);
		proxiedClass.setAttribute(3);
		proxiedClass.setMultiAttribute(1, "hello");

		List<String> callHistory = unitUnderTest.returnCallHistory(proxiedClass);
		assertEquals(3, callHistory.size());
		
	}

}
