package com.daghan.interception.command;

import java.io.Serializable;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import com.daghan.interception.api.ProxyFactory;
import com.daghan.interception.provider.domain.RandomClass;
import com.daghan.interception.provider.domain.SingleInterface;
import com.daghan.interception.provider.domain.TestClassWithSingleInterface;

import osgi.enroute.debug.api.Debug;

@Component(immediate = true, property = { Debug.COMMAND_SCOPE + "=app", Debug.COMMAND_FUNCTION + "=intercept",
		Debug.COMMAND_FUNCTION + "=factory" })
public class InterceptApp implements Serializable {
	// bind to all the ProxyFactory implementations
	@Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE)
	List<ProxyFactory> proxyFactories;

	// bind to ProxyFactory implementations specifically
	@Reference(target = "(factory=bytebuddy)")
	ProxyFactory byteBuddyFactory;
	@Reference(target = "(factory=javaproxy)")
	ProxyFactory javaProxyFactory;

	/**
	 * Runs intercepted methods intercepted by the
	 * com.daghan.interception.provider bundle. use res:printAllLog command to
	 * see the interception log after running this command
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void intercept() throws InstantiationException, IllegalAccessException {
		// LogClass.clearLogs();
		RandomClass test = new RandomClass();
		test.setValue("TestFromApplication");
		test.calculateValue("TestFromApplication", 1);
	}

	/**
	 * creates instances through injected Proxy factories and prints the
	 * intercepted logs
	 */
	public void factory() {
		System.out.println(proxyFactories.size());
		System.out.println("Using byteBuddyFactory");
		TestClassWithSingleInterface newInstance = byteBuddyFactory.newInstance(TestClassWithSingleInterface.class,
				null);
		newInstance.setAttribute(5);
		newInstance.setAttribute(3);
		newInstance.setMultiAttribute(1, "hello");

		System.out.println(byteBuddyFactory.returnCallHistory(newInstance));

		System.out.println("Using javaProxyFactory");
		SingleInterface anotherInstance = javaProxyFactory.newInstance(SingleInterface.class,
				new TestClassWithSingleInterface());
		anotherInstance.setAttribute(5);
		anotherInstance.setAttribute(3);
		anotherInstance.setMultiAttribute(1, "hello");

		System.out.println(javaProxyFactory.returnCallHistory(anotherInstance));
	}

}
