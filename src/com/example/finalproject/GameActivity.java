package com.example.finalproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.VideoView;
import android.support.v4.app.NavUtils;

public class GameActivity extends Activity {

	String url;
	String reviewsUrl;
	VideoView vv;
	ScrollView sv;
	//TextView link;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		// Show the Up button in the action bar.
		setupActionBar();
		
		url = getIntent().getExtras().getString("url");
		GameDetails getGameDetails = new GameDetails(this, url);
		getGameDetails.execute();
		
		sv = (ScrollView) findViewById(R.id.scrollView);
	
		
		//sv.seto
		
		//link = (TextView) findViewById(R.id.textView7);
		
		//link.setClickable(true);
		//link.setMovementMethod(LinkMovementMethod.getInstance());
		//link.setLinkTextColor(Color.BLUE);
		
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
		getMenuInflater().inflate(R.menu.game, menu);
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
