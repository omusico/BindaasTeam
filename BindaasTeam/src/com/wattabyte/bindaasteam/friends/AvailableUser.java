package com.wattabyte.bindaasteam.friends;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.R;

public class AvailableUser extends ActionBarActivity {
	
	public static final String PLAYERNAME = "playerName";
	public static final String PLAYERID = "playerId";
	
	ParseUser user;
	String userName;
	ArrayList<HashMap<String, String>> playerList;
	ListView playerListView ;
	ListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available_user);
		
		playerListView = (ListView) findViewById(android.R.id.list);
		user = ParseUser.getCurrentUser();
		userName = user.getUsername();
		
		ParseQuery<ParseUser> query = ParseQuery.getUserQuery();
		
		query.findInBackground(new FindCallback<ParseUser>() {
		    

			@Override
			public void done(List<ParseUser> objects,
					ParseException e) {
				if(e== null){
						playerList =  new ArrayList<HashMap<String,String>>();
						HashMap<String, String> map;
					for (ParseUser parseUser : objects) {
						if(!userName.equals(parseUser.getUsername())){
							 map = new HashMap<String , String>();
								map.put(PLAYERNAME, parseUser.getUsername());
								map.put(PLAYERID, parseUser.getObjectId());
								playerList.add(map);
							
						}
						
					}
					
					adapter = new SimpleAdapter(AvailableUser.this,
							playerList, R.layout.player_item, new String[] {
									PLAYERNAME }, new int[] {
									R.id.playerName });
					playerListView.setAdapter(adapter);
					
					playerListView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(
								AdapterView<?> parent, View view,
								int position, long id) {
							HashMap<String, String> item = playerList.get(position);
							Intent intent = new Intent(AvailableUser.this, RequestActivity.class);
							intent.putExtra(PLAYERNAME, item.get(PLAYERNAME));
							Log.d("Vinay", item.get(PLAYERNAME));
							intent.putExtra(PLAYERID, item.get(PLAYERID));
							Log.d("Vinay", item.get(PLAYERID));
							startActivity(intent);
							
						}
					});
				}
			}
		});
		
		
		
		
		
	}

}
