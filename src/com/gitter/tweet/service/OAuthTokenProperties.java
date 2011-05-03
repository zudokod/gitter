package com.gitter.tweet.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Properties handler for OAuth
 * 
 * @author harisgx
 * 
 */
public class OAuthTokenProperties {
	
	private static String acessToken;
	private static String acessSecret;
	private static final String KEY_ACCESS_TOKEN = "oauth.accessToken";
	private static final String KEY_ACCESS_SECRET = "oauth.accessTokenSecret";
	private static final String propFileName = "twitter4j.properties";
	private static final Properties twitterProps = new Properties();
	private static final Logger logger = Logger.getLogger(OAuthTokenProperties.class.getCanonicalName());
	 
	static {
		InputStream inputStream = null;
		try {
			inputStream = OAuthTokenProperties.class.getClassLoader().getResourceAsStream(propFileName);
			twitterProps.load(inputStream);	
			acessToken = (String)twitterProps.get(KEY_ACCESS_TOKEN);
			acessSecret = (String)twitterProps.get(KEY_ACCESS_SECRET);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "An error occurred while loading last update.", e);
		}finally{
			try {
			inputStream.close();
			} catch (Exception e) {
				//
			}
		}
	}
	
	private OAuthTokenProperties(){}
	
	public static String getacessToken(){		
		return acessToken;
	}
	
	public static String getAcessSecret(){		
		return acessSecret;
	}
	
	
	public static void save(String p_acessToken, String p_acessSecret){
		FileOutputStream fo =  null;
		try {
			fo = new FileOutputStream(OAuthTokenProperties.class.getClassLoader().getResource(propFileName).getPath());
			twitterProps.put(KEY_ACCESS_TOKEN, p_acessToken);	
			twitterProps.put(KEY_ACCESS_SECRET, p_acessSecret);	
			twitterProps.store(fo, "");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "An error occurred while saving last update.", e);
		}finally{
			try {
			fo.close();
			} catch (Exception e) {
				logger.log(Level.SEVERE, "An error occurred while closing last update.", e);
			}
		}
	}

}
