package com.daghan.interception.provider.command;

import java.io.Serializable;

import org.osgi.service.component.annotations.Component;

import com.daghan.interception.provider.hotswap.DelegationClass;
import com.daghan.interception.provider.hotswap.RandomClass;
import com.daghan.interception.provider.hotswap.AnotherRandomClass;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import osgi.enroute.debug.api.Debug;

@Component(immediate = true, property = { Debug.COMMAND_SCOPE + "=res", Debug.COMMAND_FUNCTION + "=interceptAgentInstrumentation", Debug.COMMAND_FUNCTION + "=interceptBinaryRebase"  })
public class InterceptionCommand implements Serializable {

	public void interceptAgentInstrumentation() throws InstantiationException, IllegalAccessException {
		RandomClass test = new RandomClass();
		test.setValue("1");
		test.setValue("2");
		test.setValue("3");
	}
	
	public void interceptBinaryRebase() throws InstantiationException, IllegalAccessException {
		AnotherRandomClass test = new ByteBuddy().subclass(AnotherRandomClass.class).method(ElementMatchers.named("setValue"))
		.intercept(MethodDelegation.to(DelegationClass.class)).make()
		.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded().newInstance();
		
		test.setValue("1");
		test.setValue("2");
		test.setValue("3");
	}

}
