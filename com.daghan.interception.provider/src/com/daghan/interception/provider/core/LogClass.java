package com.daghan.interception.provider.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogClass {
	private static Map<Object, List<String>> logMethods = new ConcurrentHashMap<>();

	/**
	 * Stores a log for a given object
	 * 
	 * @param obj
	 * @param log
	 */
	public static void addLog(Object obj, String log) {
		List<String> logList;
		if ((logList = logMethods.get(obj)) != null) {
			logList.add(log);
		} else {
			logList = new ArrayList<>();
			logList.add(log);
			logMethods.put(obj, logList);
		}
	}

	/**
	 * Returns all the log for this object
	 * 
	 * @param obj
	 * @return
	 */
	public static List<String> getLogs(Object obj) {
		return logMethods.get(obj);
	}

	/**
	 * Clears the logs
	 */
	public static void clearLogs() {
		logMethods.clear();
	}

	/**
	 * Returns all the logs as a single string
	 * 
	 * @return
	 */
	public static String getAllLogs() {
		StringBuilder builder = new StringBuilder();
		for (List<String> logStr : logMethods.values()) {
			builder.append(logStr.toString()).append(System.lineSeparator());
		}
		return builder.toString();
	}

}
