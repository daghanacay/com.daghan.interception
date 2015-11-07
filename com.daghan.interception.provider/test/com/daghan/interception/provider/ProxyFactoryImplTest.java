package com.daghan.interception.provider;

import java.util.List;

import com.daghan.interception.api.ProxyFactory;

import junit.framework.TestCase;

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

	public interface SingleInterface {
		public void setAttribute(Integer intAttribute);

		public void setMultiAttribute(Integer intAttribute, String strAttribute);
	}

	/**
	 * represents a geenric no interface class
	 */
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

}
