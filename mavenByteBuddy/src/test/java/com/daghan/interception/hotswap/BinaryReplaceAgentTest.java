package com.daghan.interception.hotswap;

import static net.bytebuddy.matcher.ElementMatchers.declaresMethod;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

import org.junit.Test;

import com.daghan.interception.domain.RandomClass;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;

public class BinaryReplaceAgentTest {

	@Test
	public void testInterceptionAtRuntimeUsingByteBuddyAgent() {

		new AgentBuilder.Default().type(declaresMethod(isAnnotatedWith(InterceptMethod.class)))
				.transform((builder, p2) -> builder.method(isAnnotatedWith(InterceptMethod.class))
						.intercept(MethodDelegation.to(DelegationClass.class)))
				.installOn(ByteBuddyAgent.install());

		RandomClass testClass = new RandomClass();
		RandomClass testClass2 = new RandomClass();

		testClass.setValue("1");
		testClass.setValue("2");
		testClass.setValue("3");

		testClass2.calculateValue("2", 2);
		testClass2.calculateValue("3", 3);
		testClass2.calculateValue("4", 4);

		System.out.println("Results for test testClass : " + LogClass.getLogs(testClass));
		System.out.println("Results for test testClass2 : " + LogClass.getLogs(testClass2));
	}

	// NOTE: Only when you run "mvn test". Also expect that the method is
	// intercepted twice at "mvn test" due to Custom agent and ByteBuddyAgent
	@Test
	public void testInterceptionUsingCustomJavaAgent() {

		RandomClass testClass = new RandomClass();
		RandomClass testClass2 = new RandomClass();

		testClass.setValue("hello1");
		testClass.setValue("hello2");
		testClass.setValue("hello3");

		testClass2.calculateValue("hello2", 2);
		testClass2.calculateValue("hello3", 3);
		testClass2.calculateValue("hello4", 4);

		System.out.println("Results for test testClass : " + LogClass.getLogs(testClass));
		System.out.println("Results for test testClass2 : " + LogClass.getLogs(testClass2));
	}

	

}
