package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoLoader extends AsyncTask<Void, Void, String>{
	
	VideoView videoView;
	String requestUrl;
	Activity activity;
	
	public VideoLoader(Activity a, VideoView vv, String url){
		videoView = vv;
		this.requestUrl = url;
		activity = a;
	}
	
	@Override
	protected String doInBackground(Void... params) {
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
			return ParserUtil.JSONVideoParser.parseGameVideo(sb.toString());
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
	protected void onPostExecute(String result) {

		videoView.setVideoPath(result);
		MediaController mcontrol = new MediaController(activity);
		mcontrol.setAnchorView(videoView);
		videoView.setMediaController(mcontrol);
		videoView.start();
		
		super.onPostExecute(result);
	}
	
	

}
