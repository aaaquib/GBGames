package com.example.finalproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	static final String apiKey = "d94db8d291ea91078d127a5fcecebcdfd601a5d0";
	
	String[] main_menu = { "Search for a Game", "Most popular games", "Recently Released games", "Upcoming Games"};
	private ListView main_ListView;
	 
	private ArrayAdapter<String> arrayAdapter;
	Intent searchActivity;
	Intent gameListActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setTitle("Giant Bomb");
		
		main_ListView = (ListView) findViewById(R.id.listView_main);
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, main_menu);
		
		main_ListView.setAdapter(arrayAdapter);
		
		main_ListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				
				switch((int)id){

					case 0: 
						searchActivity = new Intent(getApplicationContext(),SearchActivity.class);
						searchActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						//searchActivity.putExtra("item", 0);
						startActivity(searchActivity);
						break;
					case 1:
						gameListActivity = new Intent(getApplicationContext(),GameListActivity.class);
						gameListActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						gameListActivity.putExtra("item", 1);
						//gameListActivity.putExtra("url", searchURL);
						startActivity(gameListActivity);
						break;
					case 2:
						gameListActivity = new Intent(getApplicationContext(),GameListActivity.class);
						gameListActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						gameListActivity.putExtra("item", 2);
						//gameListActivity.putExtra("url", searchURL);
						startActivity(gameListActivity);						
						break;
					case 3:
						gameListActivity = new Intent(getApplicationContext(),GameListActivity.class);
						gameListActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						gameListActivity.putExtra("item", 3);
						//gameListActivity.putExtra("url", searchURL);
						startActivity(gameListActivity);
						break;
					case 4:
						break;
					case 5:
						break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
