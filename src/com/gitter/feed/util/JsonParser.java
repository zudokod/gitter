package com.gitter.feed.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/***
 * Parse the json
 * 
 * @author harisgx
 *
 */
public class JsonParser {
	
	public static final String pubDateFormat = "EEE, dd MMM yyyy HH:mm:ss zzz";	
	protected  Gson gson;
	
	public JsonParser(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat(pubDateFormat);
		gson = gsonBuilder.create();
	}
	

}
