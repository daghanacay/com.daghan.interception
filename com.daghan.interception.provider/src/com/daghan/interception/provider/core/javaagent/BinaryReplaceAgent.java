package com.daghan.interception.provider.core.javaagent;

import static net.bytebuddy.matcher.ElementMatchers.declaresMethod;
import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;

import java.lang.instrument.Instrumentation;

import com.daghan.interception.provider.core.DelegationClass;
import com.daghan.interception.provider.core.InterceptMethod;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;

public class BinaryReplaceAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("Starting the Java Agent");
		 new
		 AgentBuilder.Default().rebase(declaresMethod(isAnnotatedWith(InterceptMethod.class)))
		 .transform((builder, p2) ->
		 builder.method(isAnnotatedWith(InterceptMethod.class))
		 .intercept(MethodDelegation.to(DelegationClass.class)))
		 .installOn(inst);

		System.out.println("Finished the Java Agent");
	}

}
