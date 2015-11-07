package com.daghan.interception.api;

import java.util.List;

/**
 * Defines interface for the proxy factory creates a proxy for intercepting any
 * object that is created by this factory
 * 
 */
public interface ProxyFactory {

	/**
	 * Returns a proxy object as the same type of the object
	 */
	public <T> T newInstance(Class<T> interfaceClass, Object obj);

	/**
	 * if the object is created by this factory it will return the history of
	 * all the method calls with the call parameters
	 * 
	 * @param obj the object created by this factory
	 * @return
	 */
	public List<String> returnCallHistory(Object obj);

}
