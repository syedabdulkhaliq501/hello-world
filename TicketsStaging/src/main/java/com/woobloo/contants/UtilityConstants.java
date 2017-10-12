package com.woobloo.contants;

import java.util.HashMap;
import java.util.Map;

public class UtilityConstants {
public static Map<String,String> changeToStatus= new HashMap<String, String>();


public static  HashMap<String, String> responseHeaders = new HashMap<String, String>();




static {
	
	changeToStatus.put("N", "WIP");
	changeToStatus.put("WIP", "COM");
	
	responseHeaders.put("Content-Type", "applicatio/json");

	responseHeaders.put("Access-Control-Allow-Origin", "*");
	
}
}
