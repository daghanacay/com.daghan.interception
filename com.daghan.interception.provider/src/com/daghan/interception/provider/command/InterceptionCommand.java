package com.daghan.interception.provider.command;

import java.io.Serializable;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.osgi.service.component.annotations.Component;

import com.daghan.interception.provider.core.DelegationClass;
import com.daghan.interception.provider.core.LogClass;
import com.daghan.interception.provider.domain.AnotherRandomClass;
import com.daghan.interception.provider.domain.RandomClass;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import osgi.enroute.debug.api.Debug;

@Component(immediate = true, property = { Debug.COMMAND_SCOPE + "=res",
		Debug.COMMAND_FUNCTION + "=interceptAgentInstrumentation", Debug.COMMAND_FUNCTION + "=interceptBinaryRebase",
		Debug.COMMAND_FUNCTION + "=printAllLog" })
public class InterceptionCommand implements Serializable {

	RandomClass testCommon = new RandomClass();

	/**
	 * runs teh tests intercepted by the agent
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void interceptAgentInstrumentation() throws InstantiationException, IllegalAccessException {
		// LogClass.clearLogs();

		RandomClass test = new RandomClass();
		test.setValue("1");
		test.calculateValue("string1", 1);

		testCommon.setValue("1");
		testCommon.calculateValue("string1", 1);

		System.out.println(LogClass.getLogs(test));
		System.out.println(LogClass.getLogs(testCommon));
	}

	/**
	 * Runs methods intercepted by ByteBuddy created instances
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void interceptBinaryRebase() throws InstantiationException, IllegalAccessException {
		// LogClass.clearLogs();

		AnotherRandomClass test = new ByteBuddy().subclass(AnotherRandomClass.class)
				.method(ElementMatchers.named("setValue")).intercept(MethodDelegation.to(DelegationClass.class)).make()
				.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded().newInstance();

		test.setValue("2");
		test.doSomething();

		System.out.println(LogClass.getLogs(test));
	}

	/**
	 * Prints all the logs.
	 */
	public void printAllLog() {
		System.out.println(LogClass.getAllLogs());
	}

}
