package com.daghan.interception.provider;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import com.daghan.interception.api.ProxyFactory;
import com.daghan.interception.provider.core.DelegationClass;
import com.daghan.interception.provider.core.LogClass;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;

@Component(property={"factory=bytebuddy"})
public class ProxyFactoryByteBuddy implements ProxyFactory {

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
