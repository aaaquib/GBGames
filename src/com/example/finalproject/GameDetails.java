package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameDetails extends AsyncTask<Void, Void, GameObject>{

	Activity activity;
	String requestUrl;
	ProgressDialog dialog;
	public GameDetails(Activity a, String url){
		activity = a;
		requestUrl = url;
		dialog = new ProgressDialog(activity);
		dialog.setCancelable(false);
		dialog.setMessage("Loading Game Info");
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		dialog.show();
		super.onPreExecute();
	}
	@Override
	protected GameObject doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		URL url;
		
		try {
			url = new URL(requestUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int statusCode = con.getResponseCode();
			if(statusCode == HttpURLConnection.HTTP_OK){
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = reader.readLine();
				while(line!=null){
					sb.append(line);
					line = reader.readLine();
				}
			return ParserUtil.DetailGameJSONParser.parseGameList(sb.toString());
				//Log.d("demo", sb.toString());
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
}

@Override
protected void onPostExecute(GameObject game) {
	// TODO Auto-generated method stub
	if(game != null){

		TextView title = (TextView) activity.findViewById(R.id.textView2);
		TextView description = (TextView) activity.findViewById(R.id.textView1);
		TextView rating = (TextView) activity.findViewById(R.id.textView3);
		TextView platforms = (TextView) activity.findViewById(R.id.textView4);
		TextView release_date = (TextView) activity.findViewById(R.id.textView5);
		TextView reviews = (TextView) activity.findViewById(R.id.textView6);
		final TextView link = (TextView) activity.findViewById(R.id.textView8);
		ImageView iv = (ImageView) activity.findViewById(R.id.imageView1);
		Button button = (Button) activity.findViewById(R.id.button1);
		Button rbutton = (Button) activity.findViewById(R.id.button2);
		//VideoView vv = (VideoView) activity.findViewById(R.id.videoView1);
		//vv.setBackgroundResource(android.R.color.transparent);
		
		title.setText(game.getTitle());
		description.setText(game.getDescription());
		rating.setText("Game Rating: " + game.getRating());
		platforms.setText("Platforms: " + game.getPlatforms().toString());
		release_date.setText("Release Date: " + game.getRelease_date());
		reviews.setText("User Reviews: " + game.getNumOfReviews());
		link.setText(game.getSiteUrl());
		link.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				String url = link.getText().toString();
				Intent webActivity = new Intent(activity,WebActivity.class);
				webActivity.putExtra("url", url);
				activity.startActivity(webActivity);
			}
		});
		
		ImageLoaderTask loader = new ImageLoaderTask(iv, game.getPosterUrl());
		loader.execute();
		
		if(game.getVideoUrl() != null){
			//VideoLoader vloader = new VideoLoader(activity, vv, game.getVideoUrl());
			//vloader.execute();
			final String videoUrl = game.getVideoUrl();
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent trailerActivity = new Intent(activity,TrailerActivity.class);
					trailerActivity.putExtra("url", videoUrl);
					activity.startActivity(trailerActivity);
				}
			});
		}
		if(game.getReviewUrl()!=null)
		{
			final String reviewUrl = game.getReviewUrl();
			rbutton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent reviewActivity = new Intent(activity,ReviewActivity.class);
					reviewActivity.putExtra("url", reviewUrl);
					activity.startActivity(reviewActivity);
				}
			});
		}
		
//		Toast.makeText(activity, "Found " + numOfResults.toString() + " Games", Toast.LENGTH_LONG).show();
	}
	//else
	
	if(game == null)
		Toast.makeText(activity, "result is null", Toast.LENGTH_SHORT).show();
	dialog.cancel();
	super.onPostExecute(game);
	}
}
