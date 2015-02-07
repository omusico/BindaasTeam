package com.wattabyte.bindaasteam.groupmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class GroupFinalActivity extends ActionBarActivity {
	
	public static final String GROUP_NAME = "GroupName";
	public static final String LEAGUE_NAME = "LeagueName";
	public static final String PLAYER_NAME = "PlayerName";
	public static final String TEAM_MEMBER = "Name";
	public static final String POINTS = "Points";
	public static final String TEAM_CREATOR = "TeamCreator";
	ListView resultListView;
	ListAdapter adapter;
	ArrayList<HashMap<String, String>> resultList;
	
	static String teamName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_final);
		Intent i = getIntent();
		final String groupName  = i.getStringExtra(GROUP_NAME);
		Log.d("MSG", groupName);
		final String leagueName = i.getStringExtra(LEAGUE_NAME);
		Log.d("MSG", leagueName);
		resultListView = (ListView) findViewById(R.id.listView);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery(""+groupName);
		query.whereExists(PLAYER_NAME);
		query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> groups, ParseException e) {
				if(e == null){
					Toast.makeText(GroupFinalActivity.this, "Groups Retrieved "+groups.size(), 
							Toast.LENGTH_SHORT).show();
				
					
				for (ParseObject parseObject : groups) {
					
					Log.d("MSG", parseObject.getString(PLAYER_NAME));
					
					
					ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Team"+parseObject.getString(PLAYER_NAME));
					
					query2.whereEqualTo(LEAGUE_NAME,leagueName);
					query2.findInBackground(new FindCallback<ParseObject>() {
						
						@Override
						public void done(List<ParseObject> leagues, ParseException e2) {
							if (e2 == null) {
								Toast.makeText(GroupFinalActivity.this, "Groups Retrieved "+leagues.size(), 
										Toast.LENGTH_SHORT).show();
								
								
								for (ParseObject parseObject2 : leagues) {
									SharedPreferences sharedPreferences = getSharedPreferences("final", Context.MODE_PRIVATE);
									SharedPreferences.Editor editor = sharedPreferences.edit();
									editor.putString(TEAM_CREATOR, parseObject2.getString(TEAM_CREATOR));
									editor.putString(GROUP_NAME, parseObject2.getString(GROUP_NAME));
									editor.putString(POINTS, parseObject2.getString(POINTS));
									editor.commit();
								}
							} else {
								Log.d("MSG", "Error: " + e2.getMessage());
							}
							
						}
					});
					SharedPreferences sharedPreferences = getSharedPreferences("final", Context.MODE_PRIVATE);
					String memberName = sharedPreferences.getString(TEAM_CREATOR, "");
					Log.d("MSG", memberName);
					String teamName = sharedPreferences.getString(GROUP_NAME,"");
					Log.d("MSG", teamName);
					String points = sharedPreferences.getString(POINTS, "");
					Log.d("MSG", points);
					
				}
				}
				else{
					Log.d("MSG", "Error: " + e.getMessage());
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_final, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	
	
	
	}
