package com.daghan.interception.hotswap;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;

import java.util.List;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;

public class ProxyFactoryByteBuddy {

	public <T> T newInstance(Class<T> interfaceClass, Object obj) {
		try {
			return new ByteBuddy().subclass(interfaceClass).method(nameContains("set"))
					.intercept(MethodDelegation.to(DelegationClass.class)).make()
					.load(interfaceClass.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded()
					.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> returnCallHistory(Object obj) {
		return LogClass.getLogs(obj);
	}

}
