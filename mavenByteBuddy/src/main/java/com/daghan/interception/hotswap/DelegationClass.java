package com.daghan.interception.hotswap;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;

public class DelegationClass {
	@RuntimeType
	public static Object allCalls(@SuperCall Callable<?> callable, @Origin Method zuperMethod,
			@AllArguments Object[] args, @This Object obj) throws Exception {
		LogClass.addLog(obj, "Intercepting call " + zuperMethod.getName() + " with values " + Arrays.toString(args));
		return callable.call();
	}
}
