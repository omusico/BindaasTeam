package com.wattabyte.bindaasteam.groupmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
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
import com.wattabyte.bindaasteam.R;

public class GroupFinalActivity extends ActionBarActivity {
	
	public static final String GROUP_NAME = "GroupName";
	public static final String LEAGUE_NAME = "LeagueName";
	public static final String PLAYER_NAME = "PlayerName";
	
	ListView resultListView;
	ListAdapter adapter;
	ArrayList<HashMap<String, String>> resultList;
	
	
	static String teamName;
	public GroupFinalActivity() {
		// TODO Auto-generated constructor stub
	}
	

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
					resultList = new ArrayList<HashMap<String, String>>();
					HashMap<String, String> map;
				for (ParseObject parseObject : groups) {
					map = new  HashMap<String , String>();
					map.put(PLAYER_NAME, parseObject.getString(PLAYER_NAME));
					Log.d("MSG", parseObject.getString(PLAYER_NAME));
					
					ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Team"+parseObject.getString(PLAYER_NAME));
					
					query2.whereEqualTo(LEAGUE_NAME,leagueName);
					query2.findInBackground(new FindCallback<ParseObject>() {
						
						@Override
						public void done(List<ParseObject> leagues, ParseException e2) {
							if (e2 == null) {
								Toast.makeText(GroupFinalActivity.this, "Groups Retrieved "+leagues.size(), 
										Toast.LENGTH_SHORT).show();
								
								HashMap<String, String>	map2;
								for (ParseObject parseObject2 : leagues) {
								map2 = new  HashMap<String , String>();
								map2.put(GROUP_NAME, parseObject2.getString(GROUP_NAME));
								Log.d(GROUP_NAME, map2.toString());
								
								}
							} else {
								Log.d("MSG", "Error: " + e2.getMessage());
							}
							
						}
					});
					
					resultList.add(map);
					Log.d("MSG", resultList.toString());
					adapter = new SimpleAdapter(GroupFinalActivity.this, resultList, R.layout.group_item, new String[]{PLAYER_NAME},
							new int[]{R.id.groupName });
					resultListView.setAdapter(adapter);
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
