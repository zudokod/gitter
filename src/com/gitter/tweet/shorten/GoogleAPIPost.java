package com.gitter.tweet.shorten;

import java.io.ByteArrayInputStream;
import java.net.URL;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;

/***
 * Wrapper for API post message
 * 
 * @author harisgx
 *
 */
public class GoogleAPIPost {
	
	private static final String apiURL = "https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyBbPLh1qHC6N1uKGCDMoS7Fm4Pxb87oUu8";
	private static final HttpPost httppost = new HttpPost(apiURL);
	private StringBuilder jsonStr = new StringBuilder("{\"longUrl\":");
	
	private GoogleAPIPost(URL longURL){
		jsonStr.append("\"");
		jsonStr.append(longURL.toString());
		jsonStr.append("\"}");	
	}
	
	public static GoogleAPIPost build(URL longURL){		
		return new GoogleAPIPost(longURL);
	}
	
	public HttpPost getHttpPost(){		
		InputStreamEntity reqEntity = new InputStreamEntity(new ByteArrayInputStream(jsonStr.toString().getBytes()), -1);
		reqEntity.setContentType("application/json");
		httppost.setEntity(reqEntity);
		return httppost;
	}

}
