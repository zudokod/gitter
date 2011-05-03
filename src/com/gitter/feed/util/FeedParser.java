package com.gitter.feed.util;

import com.gitter.feed.domain.Feed;
import com.google.gson.JsonElement;

/***
 * parse the feed from github
 * 
 * @author harisgx
 *
 */
public class FeedParser extends JsonParser {
	
	public FeedParser(){
		
	}
	
	public Feed getFeed(JsonElement feedElmnt){
		return super.gson.fromJson(feedElmnt, Feed.class);
	}
	

}
