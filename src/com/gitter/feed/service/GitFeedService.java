package com.gitter.feed.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gitter.feed.domain.Feed;
import com.gitter.feed.domain.FeedEntry;
import com.gitter.feed.util.FeedParser;
import com.gitter.feed.util.HttpUtil;
import com.gitter.services.constants.GitFeedUrls;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/***
 * Handles fetching user feed from git user
 * 
 * @author haris
 *
 */
public class GitFeedService {
	
	private static final Logger logger = Logger.getLogger(GitFeedService.class.getCanonicalName());
	
	public GitFeedService(){
		
	}
	
	/****
	 * Returns Feed of the user
	 * 
	 * @param user
	 *            - git user name
	 * @return {@link Feed} instance - can return null if none
	 */
	public Feed getFeed(String user) {
		
		Feed feed = null;
		//fetch the feed from the url
		InputStream data = HttpUtil.getData(GitFeedUrls.getPublicFeedURL(user));
		if(data != null){
			try{
				//response data will be a json for the git feed
				JsonParser parser = new JsonParser();
				JsonElement responseElmnt = parser.parse(new InputStreamReader(data));//UTF ?				
				JsonObject responseObj = responseElmnt.getAsJsonObject();		
				JsonElement responseDataElmnt = responseObj.get("responseData");
				JsonElement feedElmnt = responseDataElmnt.getAsJsonObject().get("feed");
				GitFeedLastUpdate lastfeedupdate = new GitFeedLastUpdate(user);
				if(feedElmnt != null){
					feed = new FeedParser().getFeed(feedElmnt);
					List<FeedEntry> entries = feed.getEntries();
					List<FeedEntry> newEntries = new ArrayList<FeedEntry>();
					long lastUpdateTime = lastfeedupdate.get();
					System.out.println("My last update time :" + lastUpdateTime);
					if(entries != null){
						for(FeedEntry entry : entries){
							//stored date
							Date pubDate = entry.getPublishedDate();
							
							//feed to contain only those are latest ones
							if(pubDate != null && pubDate.getTime() > lastUpdateTime){
								lastfeedupdate.set(pubDate.getTime());
								newEntries.add(entry);
							}					
						}
						feed.setEntries(newEntries);
						if(feed.getEntries().size() == 0){
							logger.info("No feed data");
						}
					}
					//store date
					lastfeedupdate.save();
				}
				
			}catch(Exception e){
				logger.log(Level.SEVERE, "Feed could not be loaded");		
			}
			
		}else{
			logger.info("No feed data");
		}
		
		
		return feed;
	}

}
