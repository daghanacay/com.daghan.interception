package com.daghan.interception.provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.daghan.interception.api.ProxyFactory;

/**
 * 
 */
@Component(property={"factory=javaproxy"})
public class ProxyFactoryImpl implements ProxyFactory {

	@Override
	public <T> T newInstance(Class<T> interfaceClass, Object obj) {
		DynamicProxy<T> proxyInstance = new DynamicProxy<>((T) obj);
		return interfaceClass.cast(
				Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[] { interfaceClass }, proxyInstance));
	}

	@Override
	public List<String> returnCallHistory(Object obj) {
		List<String> retunVal;
		try {
			retunVal = ((DynamicProxy) Proxy.getInvocationHandler(obj)).callHistory;
		} catch (IllegalArgumentException e) {
			retunVal = Arrays.asList(new String[] { "This object is not created by ProxyFactory" });
		}

		return retunVal;
	}

	/**
	 * Intercepts the method calls before it delegates them to the actual
	 * implementation
	 * 
	 * @author daghan
	 *
	 * @param <T>
	 */
	private class DynamicProxy<T> implements java.lang.reflect.InvocationHandler {
		private Object obj;
		private final List<String> callHistory = new ArrayList<>();
		private StringBuffer buffer;

		/**
		 * Constructor
		 * 
		 * @param obj
		 */
		public DynamicProxy(T obj) {
			this.obj = obj;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result;

			try {
				result = method.invoke(obj, args);
				int indexer = 0;
				buffer = new StringBuffer();
				for (Class paramType : method.getParameterTypes()) {
					buffer.append(paramType.cast(args[indexer]).toString()).append(",");
					indexer++;
				}
				callHistory.add(String.format("Call is made to %s with argument value %s", method.getName(),
						buffer.toString()));
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			} catch (Exception e) {
				throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
			}
			return result;
		}

	}

}
