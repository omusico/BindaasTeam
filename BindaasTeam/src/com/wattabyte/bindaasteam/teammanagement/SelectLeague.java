package com.wattabyte.bindaasteam.teammanagement;

import com.wattabyte.bindaasteam.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectLeague extends ActionBarActivity {
	
	TextView tv;

	ArrayAdapter<String> adapter;
	 String[] leagueArray = {"World Cup 2015", "Indian Premier League",
			 "Celebrity Cricket League", "Champions League"};
	 static String teamName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_league);
		
		tv = (TextView) findViewById(R.id.textView1);
		
		Intent i = getIntent();
		teamName = i.getStringExtra("text");
		Log.i("MSG", teamName);
		tv.setText(teamName);
		
		adapter = new ArrayAdapter<String>(this, 
			      R.layout.league_list, leagueArray);
			      
			      ListView listView = (ListView) findViewById(R.id.league_list);
			      listView.setAdapter(adapter);
			      
			      listView.setOnItemClickListener( new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						
						Intent in = new Intent(SelectLeague.this,SelectPlayers.class);
						in.putExtra("league", adapter.getItem(position).toString());
						in.putExtra("name", teamName);
						startActivity(in);
						
					}
				});


			    	 

		
	}

}
