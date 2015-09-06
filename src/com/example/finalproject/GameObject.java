package com.example.finalproject;

import java.util.ArrayList;

public class GameObject {
	String title,rating;
	ArrayList<String> platforms;
	String thumbUrl, posterUrl, detailUrl, siteUrl,videoUrl,reviewUrl;
	String release_date, numOfReviews, description;
	Integer score;
	Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public ArrayList<String> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(ArrayList<String> platforms) {
		this.platforms = platforms;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public String getPosterUrl() {
		return posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl + "?api_key=" + MainActivity.apiKey + "&format=json";
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getNumOfReviews() {
		return numOfReviews;
	}
	public void setNumOfReviews(String numOfReviews) {
		this.numOfReviews = numOfReviews;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl + "?api_key=" + MainActivity.apiKey + "&format=json";
	}
	public String getReviewUrl() {
		return reviewUrl;
	}
	public void setReviewUrl(String reviewUrl) {
		this.reviewUrl = reviewUrl + "?api_key=" + MainActivity.apiKey + "&format=json";
	}
	
}
