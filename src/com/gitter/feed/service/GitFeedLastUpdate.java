package com.gitter.feed.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/****
 * Manages the last update of the feed
 * 
 * @author harisgx
 *
 */
public class GitFeedLastUpdate {
	
	private long lastupdatedtime;
	private static final String KEY_LAST_UPDATE = "lastupdatedtime";
	private static final String propFileName = "feed_status.properties";
	private static final Properties gitLastUpdateProps = new Properties();
	private static final Logger logger = Logger.getLogger(GitFeedLastUpdate.class.getCanonicalName());
	
	private String user;
	
	public GitFeedLastUpdate(String user){
		this.user = user;
	}
	 
	static {
		InputStream inputStream = null;
		try {
			inputStream = GitFeedLastUpdate.class.getClassLoader().getResourceAsStream(propFileName);
			gitLastUpdateProps.load(inputStream);			
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
	

	/***
	 * 
	 * @return the last update time in milliseconds
	 */
	public long get(){	
		if(lastupdatedtime == 0){
			String val = (String)gitLastUpdateProps.get(this.user + "." +KEY_LAST_UPDATE);
			if(val != null && val.length() > 0){
				lastupdatedtime = Long.valueOf(val);
			}
		}
		
		return lastupdatedtime;
	}
	
	/****
	 * Sets the value of last update time
	 * 
	 * @param value
	 */
	public  void set(long value){		
		lastupdatedtime = value;
	}

	/***
	 * Persists the last update
	 */
	public void save(){
		FileOutputStream fo =  null;
		try {
			fo = new FileOutputStream(GitFeedLastUpdate.class.getClassLoader().getResource(propFileName).getPath());
			System.out.println(lastupdatedtime);
			gitLastUpdateProps.put(this.user + "." + KEY_LAST_UPDATE, String.valueOf(lastupdatedtime));	
			gitLastUpdateProps.store(fo, "");
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
