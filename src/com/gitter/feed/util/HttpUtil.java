package com.gitter.feed.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/****
 * 
 * @author harisgx
 *
 */
public class HttpUtil {
	
	private HttpUtil(){	}
	
	private static final Logger logger = Logger.getLogger(HttpUtil.class.getCanonicalName());
	
	/****
	 * Returns data from the url given
	 * 
	 * @param urlStr
	 *            - url
	 * @return response data - can return null if none
	 */
	public static InputStream getData(String urlStr){
		URL url = null;
		
		try{
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();			
			
			if(conn.getResponseCode() == 200){
				return conn.getInputStream();
			}else{
				throw new Exception();//TODO
			}
			
		}catch(Exception e){
			logger.log(Level.SEVERE, "Failed to connect url");
		}
		
		
		return null;
		
	}

}
