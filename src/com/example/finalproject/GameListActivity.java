package com.example.finalproject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
//import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

public class GameListActivity extends Activity {

	ListView gameList;
	ProgressDialog pdialog;
	int selectedItem,pos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
		// Show the Up button in the action bar.
		setupActionBar();
		pdialog = new ProgressDialog(this);
		pdialog.setCancelable(false);
		pdialog.setMessage("Loading Games");
		gameList = (ListView) findViewById(R.id.listView_games);
		selectedItem = getIntent().getIntExtra("item", 0);
		switch(selectedItem){
		case 0: 
			String url = getIntent().getStringExtra("url");
			RetrieveGamesTask retrieveGames = new RetrieveGamesTask(this, gameList, url);
			retrieveGames.execute();
			break;
		case 1:
			RetrieveTopGamesTask retrieveTopGames = new RetrieveTopGamesTask(this, gameList, "http://www.giantbomb.com/api/games/?api_key=d94db8d291ea91078d127a5fcecebcdfd601a5d0&format=json&field_list=name,image,original_game_rating,platforms,api_detail_url,number_of_user_reviews&sort=number_of_user_reviews:desc&limit=15");
			retrieveTopGames.execute();
			break;
		case 2:
			RetrieveTopGamesTask retrieveRecentGames = new RetrieveTopGamesTask(this, gameList, "http://www.giantbomb.com/api/games/?api_key=d94db8d291ea91078d127a5fcecebcdfd601a5d0&format=json&field_list=name,image,original_game_rating,platforms,api_detail_url,number_of_user_reviews&sort=original_release_date:desc&limit=15");
			retrieveRecentGames.execute();
			break;
		case 3:
			RetrieveTopGamesTask retrieveUpcomingGames = new RetrieveTopGamesTask(this, gameList, "http://www.giantbomb.com/api/games/?api_key=d94db8d291ea91078d127a5fcecebcdfd601a5d0&format=json&field_list=name,image,original_game_rating,platforms,api_detail_url,number_of_user_reviews&filter=expected_release_month:12&limit=15");
			retrieveUpcomingGames.execute();
			break;
		
		default:
			break;
		}
		
		gameList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				pos = position;
				GameObject game = (GameObject) gameList.getItemAtPosition(position);
				Intent gameActivity = new Intent(GameListActivity.this,GameActivity.class);
				gameActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				gameActivity.putExtra("url", game.getDetailUrl());
				startActivity(gameActivity);
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
