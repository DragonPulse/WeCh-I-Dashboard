package com.demo.wechatint.wechatintegration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
	@JsonProperty
	private String title;
	@JsonProperty
	private String description;
	@JsonProperty
	private String url;
	@JsonProperty
	private String picurl;


	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public String getPicurl() {
		return picurl;
	}

}
