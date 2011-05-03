package com.gitter.tweet.service;

import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gitter.feed.domain.Feed;
import com.gitter.feed.domain.FeedEntry;
import com.gitter.feed.service.GitFeedService;
import com.gitter.tweet.domain.GitMessage;
import com.gitter.tweet.shorten.URLShortenerService;

/***
 * Publish the feed supplied
 *
 * @author harisgx
 *
 */
public class GitterService {
	
	private static final Logger logger = Logger.getLogger(GitterService.class.getCanonicalName());
	private String user;
	
	public GitterService(String user){
		this.user = user;
	}
	
	public void execute(){
		GitFeedService gf = new GitFeedService();
		publish(gf.getFeed(user));
	}
	
	/***
	 * Publishes the feed
	 * 
	 * @param feed
	 */
	private void publish(Feed feed){
		
		if(feed != null){
			
			List<FeedEntry> entries = feed.getEntries();
			URLShortenerService urServ = new URLShortenerService();
			TwitterService twit = new TwitterService();
		
			for(FeedEntry entry : entries){
				//shorten the url to suffice the twitter
				URL url = urServ.shorten(entry.getLink());
				GitMessage gm = new GitMessage();
				gm.setShortLink(url.toString());
				gm.setText(entry.getTitle());
				twit.updateStatus(gm);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					logger.log(Level.WARNING, "InterruptedException while tweeting.Ignore.");
				}
			}
			
			urServ.shutDown();
		}else{
			logger.info("There is no feed");
		}
		
	}
	

}
