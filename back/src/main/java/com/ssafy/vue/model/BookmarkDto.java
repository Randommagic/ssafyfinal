package com.ssafy.vue.model;

public class BookmarkDto {
	private int id;
	private String aptCode;
	private String aptName;
	private String bookmarkedTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getBookmarkedTime() {
		return bookmarkedTime;
	}
	public void setBookmarkedTime(String bookmarkedTime) {
		this.bookmarkedTime = bookmarkedTime;
	}



}
