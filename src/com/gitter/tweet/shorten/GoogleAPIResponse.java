package com.gitter.tweet.shorten;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/***
 * Wrapper for API response message
 * 
 * @author harisgx
 * 
 * 
 */
public class GoogleAPIResponse {
	
	private static final JSONParser parser = new JSONParser();
	
	private String kind;
	private String id;
	private String longUrl;
	
	private GoogleAPIResponse(){
		
	}
	
	public static GoogleAPIResponse build(String responseStr){
		JSONObject jsonObj = null;
		GoogleAPIResponse respObject = null;
		try {
			jsonObj = (JSONObject) parser.parse(responseStr);
			respObject = new GoogleAPIResponse();
			respObject.id = (String)jsonObj.get("id");
			respObject.kind = (String)jsonObj.get("kind");
			respObject.longUrl = (String)jsonObj.get("longUrl");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return respObject;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

}
