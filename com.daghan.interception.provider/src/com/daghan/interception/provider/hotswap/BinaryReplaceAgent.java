package com.daghan.interception.provider.hotswap;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class BinaryReplaceAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("Starting the Java Agent");
		new AgentBuilder.Default().rebase(ElementMatchers.nameStartsWith("com.daghan.interception.provider.hotswap.RandomClass")).transform((builder, p2) -> builder
				.method(ElementMatchers.named("setValue")).intercept(MethodDelegation.to(DelegationClass.class)))
				.installOn(inst);
		System.out.println("Finished the Java Agent");
		
	}

}