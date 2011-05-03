
package com.gitter.feed.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class FeedEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;	
	private String link;	
	private String author;
	private Date publishedDate;
	private String content;
	private List<String> categories;
	
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
	
	
	public Date getPublishedDate() {
		return publishedDate;
	}
	
	
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	public String getContent() {
		return content;
	}
	
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<String> getCategories() {
		return categories;
	}
	
	
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return " {author=" + author + ", categories=" + categories
				+ ", content=" + content
				+ ", link=" + link + ", publishedDate=" + publishedDate
				+ ", title=" + title + "}";
	}
}
