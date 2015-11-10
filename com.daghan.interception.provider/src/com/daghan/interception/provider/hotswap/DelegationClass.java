package com.daghan.interception.provider.hotswap;

import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class DelegationClass {
	public static String setValue(@SuperCall Callable<String> zuperMethod, @Argument(0) String argument)
			throws Exception {
		System.out.println("Intercepting call called with argument " + argument);
		try {
			return zuperMethod.call();
		} finally {
			System.out.println("Finished intercepting");
		}
	}
}
