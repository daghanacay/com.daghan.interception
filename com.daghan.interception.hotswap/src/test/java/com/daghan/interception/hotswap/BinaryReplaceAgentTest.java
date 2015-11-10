package com.daghan.interception.hotswap;

import org.junit.Test;

import com.daghan.interception.domain.RandomClass;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class BinaryReplaceAgentTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		
		new AgentBuilder.Default().type(ElementMatchers.nameStartsWith("com.daghan"))
        .transform((builder, p2) -> builder
		.method(ElementMatchers.named("setValue")).intercept(MethodDelegation.to(DelegationClass.class)))
		.installOn(ByteBuddyAgent.install());

		RandomClass testClass = new RandomClass(); 
		
		testClass.setValue("1");
		testClass.setValue("2");
		testClass.setValue("3");
	}

}
