package com.gitter.services.constants;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/***
 * Git API URLs
 * 
 * @author harisgx
 *
 */
public class GitFeedUrls {
	
    public static final String FEED_PROPS_FILE = "GitFeed.properties";
    public static final String FEED_URL_USER_PARAM = "{userName}";
    private static final Properties gitFeedProps = new Properties();
    private static final Logger logger = Logger.getLogger(GitFeedUrls.class.getCanonicalName());

	static {
		try {
			gitFeedProps.load(GitFeedUrls.class.getResourceAsStream(FEED_PROPS_FILE));
		} catch (IOException e) {
			logger.log(Level.SEVERE, "An error occurred while loading urls.", e);
		}
	}
	
	private static final String GIT_PUBLIC_FEED_URL  = gitFeedProps.getProperty("user.feed.url");
	
	private GitFeedUrls(){}
	
	public final static String getPublicFeedURL(String userName){
		StringBuilder sb =  new StringBuilder();
		sb.append(GIT_PUBLIC_FEED_URL);
		int stIndex = sb.indexOf(FEED_URL_USER_PARAM);
		int enIndex = stIndex + FEED_URL_USER_PARAM.length();
		sb.replace(stIndex, enIndex, userName);		
		logger.log(Level.INFO,"Feed URL:" + sb);		
		return sb.toString();
	}

}
