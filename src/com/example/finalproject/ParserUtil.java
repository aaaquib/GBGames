package com.example.finalproject;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParserUtil {
	
	
	static public class DetailGameJSONParser{
		static GameObject parseGameList(String in) throws JSONException {
			GameObject game = new GameObject();
			
			JSONObject root = new JSONObject(in);
			
			JSONObject gameObject = root.getJSONObject("results");
			
			game.setTitle(gameObject.getString("name"));
			game.setId(gameObject.getInt("id"));
			if(!gameObject.isNull("original_game_rating"))
				game.setRating((gameObject.getJSONArray("original_game_rating")).getJSONObject(0).getString("name"));
			if(!gameObject.isNull("image"))
				game.setPosterUrl((gameObject.getJSONObject("image")).getString("thumb_url"));
			if(!gameObject.isNull("image"))
				game.setThumbUrl((gameObject.getJSONObject("image")).getString("thumb_url"));
			if(!gameObject.isNull("platforms")){
				JSONArray platformArray = gameObject.getJSONArray("platforms");
				ArrayList<String> platforms = new ArrayList<String>();
				for(int j=0; j<platformArray.length(); j++){
					platforms.add(platformArray.getJSONObject(j).getString("abbreviation"));
				}
				game.setPlatforms(platforms);
			}
			if(!gameObject.isNull("original_release_date"))
				game.setRelease_date(gameObject.getString("original_release_date"));
			if(!gameObject.isNull("number_of_user_reviews"))
				game.setNumOfReviews(gameObject.getString("number_of_user_reviews"));
			if(!gameObject.isNull("site_detail_url"))
				game.setSiteUrl(gameObject.getString("site_detail_url"));
			if(!gameObject.isNull("deck"))
				game.setDescription(gameObject.getString("deck"));
			if(!gameObject.getJSONArray("videos").isNull(0))
				game.setVideoUrl(gameObject.getJSONArray("videos").getJSONObject(0).getString("api_detail_url"));
			if(gameObject.has("reviews")){
				if(!gameObject.getJSONArray("reviews").isNull(0)){
					game.setReviewUrl(gameObject.getJSONArray("reviews").getJSONObject(0).getString("api_detail_url"));
				}
			}
			
			return game;
		}
	}
	
	static public class JSONGameListParser{
		static ArrayList<GameObject> parseGameList(String in) throws JSONException{
			ArrayList<GameObject> gamesList = new ArrayList<GameObject>();
			
			JSONObject root = new JSONObject(in);
			RetrieveGamesTask.numOfResults = root.getInt("number_of_total_results");
			
			JSONArray gamesArray = root.getJSONArray("results");
			
			for(int i=0; i<gamesArray.length(); i++){
				JSONObject gameObject = gamesArray.getJSONObject(i);
				GameObject game = new GameObject();
				
				game.setTitle(gameObject.getString("name"));
				if(!gameObject.isNull("original_game_rating"))
					game.setRating((gameObject.getJSONArray("original_game_rating")).getJSONObject(0).getString("name"));
				if(!gameObject.isNull("image"))
					game.setThumbUrl((gameObject.getJSONObject("image")).getString("thumb_url"));
				if(!gameObject.isNull("platforms")){
					JSONArray platformArray = gameObject.getJSONArray("platforms");
					ArrayList<String> platforms = new ArrayList<String>();
					for(int j=0; j<platformArray.length(); j++){
						platforms.add(platformArray.getJSONObject(j).getString("abbreviation"));
					}
					game.setPlatforms(platforms);
				}
				if(!gameObject.isNull("api_detail_url"))
					game.setDetailUrl(gameObject.getString("api_detail_url"));
				gamesList.add(game);
			}
			
			
			return gamesList;
		}
	}
	
	static public class TopGameJSONParser{
		static GameObject parseGameList(String in) throws JSONException {
			GameObject game = new GameObject();
			
			JSONObject root = new JSONObject(in);
			
			JSONObject gameObject = root.getJSONObject("results");
			
			game.setTitle(gameObject.getString("name"));
			
			if(!gameObject.isNull("image"))
				game.setThumbUrl((gameObject.getJSONObject("image")).getString("thumb_url"));
			return game;
		}
	}
			
	static public class JSONTopGameListParser{
		static ArrayList<GameObject> parseGameList(String in) throws JSONException{
			ArrayList<GameObject> gamesList = new ArrayList<GameObject>();
			
			JSONObject root = new JSONObject(in);
			RetrieveGamesTask.numOfResults = root.getInt("number_of_total_results");
			
			JSONArray gamesArray = root.getJSONArray("results");
			
			for(int i=0; i<gamesArray.length(); i++){
				JSONObject gameObject = gamesArray.getJSONObject(i);
				GameObject game = new GameObject();
				
				game.setTitle(gameObject.getString("name"));
				if(!gameObject.isNull("original_game_rating"))
					game.setRating((gameObject.getJSONArray("original_game_rating")).getJSONObject(0).getString("name"));
				if(!gameObject.isNull("image"))
					game.setThumbUrl((gameObject.getJSONObject("image")).getString("thumb_url"));
				
				if(!gameObject.isNull("platforms")){
						JSONArray platformArray = gameObject.getJSONArray("platforms");
						ArrayList<String> platforms = new ArrayList<String>();
						for(int j=0; j<platformArray.length(); j++){
							platforms.add(platformArray.getJSONObject(j).getString("abbreviation"));
						}
					game.setPlatforms(platforms);
				}
					
				if(!gameObject.isNull("api_detail_url"))
					game.setDetailUrl(gameObject.getString("api_detail_url"));
				game.setNumOfReviews(gameObject.getString("number_of_user_reviews"));
				
				gamesList.add(game);
			}
			
			return gamesList;
		}
	}
	
	static public class JSONVideoParser{
		static String parseGameVideo(String in) throws JSONException {
			
			JSONObject root = new JSONObject(in);
			
			JSONObject result = root.getJSONObject("results");
			
			return result.getString("low_url");
			
		}
	}
	
	static public class JSONReviewParser{
		static ReviewObject parseGameReview(String in) throws JSONException {
			
			JSONObject root = new JSONObject(in);
			
			JSONObject result = root.getJSONObject("results");
			ReviewObject review = new ReviewObject();
			
			if(result.has("deck") && !result.isNull("deck"))
				review.setDeck(result.getString("deck"));
			if(result.has("description") && !result.isNull("description"))
				review.setDescription(result.getString("description"));
			if(result.has("publish_date") && !result.isNull("publish_date"))
				review.setPublish_date(result.getString("publish_date"));
			if(result.has("reviewer") && !result.isNull("reviewer"))
				review.setReviewer(result.getString("reviewer"));
			if(result.has("score") && !result.isNull("score"))
				review.setScore(result.getInt("score"));
			if(result.has("game") && !result.isNull("game"))
			review.setGame(result.getJSONObject("game").getString("name"));
			return review;
			
		}
	}
}
