package com.daghan.interception.hotswap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogClass {
	private static Map<Object, List<String>> logMethods = new ConcurrentHashMap<>();

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
	
	public static List<String> getLogs(Object obj){
		return logMethods.get(obj);
	}

}
