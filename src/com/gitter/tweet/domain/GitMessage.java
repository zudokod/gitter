package com.gitter.tweet.domain;


/***
 * 
 * Holds the message from git to twitter
 * 
 * @author harisgx
 *
 */
public class GitMessage {
	
	public static final String TAG = "#github ";
	public static final String DOTS = "... ";
	private String origLink;
	private String shortLink;
	private String title;
	public String getOrigLink() {
		return origLink;
	}
	public void setOrigLink(String origLink) {
		this.origLink = origLink;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public String getText() {
		return title;
	}
	public void setText(String title) {
		this.title = title;
	}
	
	public String getMessage(){
		return normalize();
	}
	
	private String normalize(){
		StringBuilder builder = new StringBuilder(TAG);
		builder.append(title.substring(0, getEndIndex())).append(DOTS).append(shortLink);
		return builder.toString();
	}
	
	private int getEndIndex(){
		int remaining = 140 - TAG.length() + shortLink.length() + DOTS.length();
		if(title.length() > remaining){
			return remaining -2;
		}else{
			return title.length() - DOTS.length();
		}
	}
}
