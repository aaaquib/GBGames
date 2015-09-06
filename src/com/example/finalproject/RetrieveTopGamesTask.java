package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

public class RetrieveTopGamesTask extends AsyncTask<Void, Void, ArrayList<GameObject>>{

	Activity activity;
	ListView listview;
	CustomAdapter adapter;
	String requestUrl;
	ProgressDialog dialog;
	static Integer numOfResults;
	public RetrieveTopGamesTask (Activity a,ListView list, String url){
		activity = a;
		listview = list;
		requestUrl = url;
		dialog = new ProgressDialog(activity);
		dialog.setCancelable(false);
		dialog.setMessage("Loading Games");
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		dialog.show();
		super.onPreExecute();
	}
	@Override
	protected ArrayList<GameObject> doInBackground(Void... arg0) {

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
			return ParserUtil.JSONTopGameListParser.parseGameList(sb.toString());
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
	protected void onPostExecute(ArrayList<GameObject> result) {
		// TODO Auto-generated method stub
		if(result != null){
			adapter = new CustomAdapter(activity, result);
			listview.setAdapter(adapter);
			//Toast.makeText(activity, "Found " + numOfResults.toString() + " Games", Toast.LENGTH_LONG).show();
		}
		//else
		
		if(result == null)
			Toast.makeText(activity, "result is null", Toast.LENGTH_SHORT).show();
		dialog.cancel();
		super.onPostExecute(result);
	}
}
