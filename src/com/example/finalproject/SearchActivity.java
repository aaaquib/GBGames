package com.example.finalproject;

import android.os.Bundle;
import android.app.Activity;
//import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class SearchActivity extends Activity {

	Intent gameListActivity;
	String searchURL = "http://www.giantbomb.com/api/search?api_key=" + MainActivity.apiKey + "&format=json";
	TextView tv;
	Button button;
	//ProgressDialog pd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//pd = new ProgressDialog(this);
		tv= (TextView) findViewById(R.id.search_Text);
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String text = tv.getText().toString();
				searchURL = searchURL + "&query=\"" + text + "\"&resources=game,video&field_list=name,original_game_rating,image,platforms,api_detail_url";
				
				gameListActivity = new Intent(getApplicationContext(),GameListActivity.class);
				gameListActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				gameListActivity.putExtra("item", 0);
				gameListActivity.putExtra("url", searchURL);
				startActivity(gameListActivity);
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
		getMenuInflater().inflate(R.menu.search, menu);
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
