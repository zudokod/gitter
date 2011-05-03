package com.gitter.tweet.shorten;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.gitter.feed.service.GitFeedService;

/***
 * 
 * Shortens urls - uses Google API
 * 
 * @author harisgx
 *
 */
public class URLShortenerService {
	
	private static final Logger logger = Logger.getLogger(GitFeedService.class.getCanonicalName());
	
	private HttpClient httpclient;
	
	public URLShortenerService(){
		httpclient = new DefaultHttpClient();
	}
	
	/***
	 * 
	 * @param url
	 * @return
	 */	
	public synchronized URL  shorten(String url){
		URL sURL = null;
		try {
			sURL = shorten(new URL(url));
		} catch (MalformedURLException e) {
			logger.log(Level.WARNING,"Url is not valid " + url);
		}
		return sURL ;
	}
	
	/***
	 * 
	 * @param url
	 * @return
	 */
	public synchronized URL  shorten(URL url){
		
		URL shortenedURL = null;		
		HttpResponse response = null;
		try {
			response = httpclient.execute(GoogleAPIPost.build(url).getHttpPost());
			HttpEntity resEntity = response.getEntity();
			
			if (resEntity != null && response.getStatusLine().getStatusCode() == 200) {
				shortenedURL = new URL(GoogleAPIResponse.build(EntityUtils.toString(resEntity)).getId());
			}else{
				logger.log(Level.WARNING,"Fails to shorten url " + url);
			}
			
		} catch (ClientProtocolException e) {
			logger.log(Level.WARNING,"ClientProtocolException while shortening url " + url);
		} catch (IOException e) {
			logger.log(Level.WARNING,"IOException while shortening url " + url);
		}catch (Exception e) {
			logger.log(Level.WARNING,"Exception while shortening url " + url);
		}
       
		return shortenedURL;
	}
	
	public synchronized void shutDown(){
		httpclient.getConnectionManager().shutdown();
	}

}
