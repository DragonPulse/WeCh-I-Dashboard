package com.demo.wechatint.wechatintegration.entity;

import java.util.ArrayList;
import java.util.List;

public class News {

	private List<Article> articles = new ArrayList<>();
	
	void addArticle( Article anArticle ) {
		articles.add(anArticle);
	}
	
	void clearArticleList( ) {
		articles.clear();
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
