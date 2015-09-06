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
import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

public class ReviewLoader extends AsyncTask<Void, Void, ReviewObject> {
	
	Activity activity;
	String requestUrl;
	ProgressDialog pd;
	public ReviewLoader(Activity a, String url){
		activity = a;
		requestUrl = url;
		pd = new ProgressDialog(activity);
		pd.setCancelable(false);
		pd.setMessage("Loading Review");
	}
	

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		pd.show();
		super.onPreExecute();
	}
	
	@Override
	protected ReviewObject doInBackground(Void... params) {
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
			return ParserUtil.JSONReviewParser.parseGameReview(sb.toString());
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
	protected void onPostExecute(ReviewObject result) {
		// TODO Auto-generated method stub
		TextView tview=(TextView) activity.findViewById(R.id.titleView);
		TextView rview=(TextView) activity.findViewById(R.id.revView);
		TextView sview=(TextView) activity.findViewById(R.id.scoreView);
		TextView pview=(TextView) activity.findViewById(R.id.publish);
		TextView sumview=(TextView) activity.findViewById(R.id.sumView);
		TextView descview=(TextView) activity.findViewById(R.id.descView);
		
		tview.setText(result.getGame());
		rview.setText("Review By: " + result.getReviewer());
		sview.setText("Score: " + result.getScore() + "/5");
		pview.setText("Published on: " + result.getPublish_date());
		sumview.setText("Summary: " + result.getDeck());
		descview.setText(Html.fromHtml("\t" + result.getDescription()));
		
		
		pd.cancel();
		super.onPostExecute(result);
	}


}
