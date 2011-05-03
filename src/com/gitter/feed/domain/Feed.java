
package com.gitter.feed.domain;

import java.io.Serializable;
import java.util.List;


public class Feed implements Serializable {

	private static final long serialVersionUID = 1L;	
	private String title;	
	private String link;	
	private String author;	
	private String description;	
	private List<FeedEntry> entries;
	
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getLink() {
		return link;
	}
	
	
	public void setLink(String link) {
		this.link = link;
	}
	
	
	public String getAuthor() {
		return author;
	}
	

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<FeedEntry> getEntries() {
		return entries;
	}
	
	
	public void setEntries(List<FeedEntry> entries) {
		this.entries = entries;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
