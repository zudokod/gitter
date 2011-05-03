package com.gitter.tweet.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gitter.tweet.domain.GitMessage;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


//TODO: removing dependency on twitter4j

/***
 * Handles publishing the message to twitter
 *  
 * @author harisgx
 *
 */
public class TwitterService {
	
	private static final Logger logger = Logger.getLogger(TwitterService.class.getCanonicalName());
	private  Twitter twitter;
	
	public TwitterService(){
		init();
	}
	
	private void init(){
		 
		this.twitter = new TwitterFactory().getInstance();
		 
		 try {
			 AccessToken accessToken = null;
			 //fetch from the properties
			if(OAuthTokenProperties.getAcessSecret() != null && !OAuthTokenProperties.getAcessSecret().isEmpty()
					&& OAuthTokenProperties.getacessToken() !=  null &&  !OAuthTokenProperties.getacessToken().isEmpty()){
				
				logger.info("Access token is already available.");
				accessToken = new AccessToken(OAuthTokenProperties.getacessToken(), OAuthTokenProperties.getAcessSecret());
				
			}else{
				//for the first time
				RequestToken requestToken = twitter.getOAuthRequestToken();
				requestToken.getToken();
				requestToken.getTokenSecret();
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				while (null == accessToken) {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("A PIN will be provided by Twitter. Type the PIN here and hit enter :");
                    String pin = br.readLine();
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                        OAuthTokenProperties.save(accessToken.getToken(), accessToken.getTokenSecret()) ;
                        
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
			}
			
			twitter.setOAuthAccessToken(accessToken);
			
		} catch (TwitterException te) {
			te.getStatusCode();
			te.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Update the status. Publishes the  feed message here.
	 * @param message
	 */
	public void updateStatus(GitMessage message){
		 Status status;
		try {
			System.out.println("Publishing "+ message.getMessage());
			status = this.twitter.updateStatus(message.getMessage());
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			logger.info(e.getMessage());
		}catch(Exception e){
			logger.log(Level.WARNING,"Exception occured while publishing status");
		}
        
	}

}
