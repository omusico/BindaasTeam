package com.wattabyte.bindaasteam.teammanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wattabyte.bindaasteam.R;

public class SelectPlayers extends ListActivity {
	
	String teamName;
	String league;
	TextView tv, tv1;
	Button bt;
	ListView lv;
	static String NAME = "Name";
	static String ROLE = "Role";
	static String GOLD = "Gold";
//	ArrayAdapter<String> adapter;
	//TeamUtil tu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.select_player);
			
			tv = (TextView) findViewById(R.id.textView1);
			tv1 = (TextView) findViewById(R.id.textView2);
			Intent i = getIntent();
			teamName = i.getStringExtra("name");
			league = i.getStringExtra("league");
			tv.setText(teamName);
			tv1.setText(league);
			//tu.setTeamName(teamName);
			
			ParseQuery<ParseObject> query = ParseQuery.getQuery("League");
			query.whereExists("Name");	
			Log.i("MSG","Before parsing");
			query.findInBackground(new FindCallback<ParseObject>() {
				@Override
				public void done(List<ParseObject> playersList, ParseException e) {
					Log.i("MSG","inside done");
					Log.i("MSG",playersList.size()+"");
					if (e == null) {
						Log.d("MSG", "Retrieved " + playersList.size() + " players");
						Toast.makeText(SelectPlayers.this, "Size of a player : "+ playersList.size(), Toast.LENGTH_LONG).show();
//						List<String> player = new ArrayList<String>();
//						String playerNam = null;
//						String playerRole = null; 
//						String playerGold = null;
//						String playerCountry = null;
						//TextView tv = (TextView) findViewById(R.id.label);
						ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
						HashMap<String, String> map;
					for( ParseObject ob : playersList)
					{
						if(e == null)
						{
//							playerNam = ob.getString("Name");
//							playerRole = ob.getString("Role");
//							playerGold = ob.getString("Gold");
//							playerCountry = ob.getString("Country");
//							Log.i("MSG", playerNam);
//							Log.i("MSG", playerRole);
//							Log.i("MSG", playerGold);
//							Log.i("MSG", playerCountry);
//							player.add(playerNam);
							//player.add(playerRole);
							Log.i("MSG", "mapping");
							map = new HashMap<String, String>();
							map.put(NAME, ob.getString(NAME));
							Log.i("MSG", ob.getString(NAME));
							map.put(ROLE, ob.getString(ROLE));
							Log.i("MSG", ob.getString(ROLE));
							map.put(GOLD, ob.getString(GOLD));
							Log.i("MSG", ob.getString(GOLD));
							menuItems.add(map);
						}
						else {
			            Log.d("MSG", "Error: " + e.getMessage());
						 		}
						ListAdapter adapter = new SimpleAdapter(SelectPlayers.this, menuItems, R.layout.player_list, new String[]
								{NAME,ROLE,GOLD}, new int[] {R.id.name , R.id.gold, R.id.role});
						setListAdapter(adapter);
					}
//					ListView lv = (ListView) findViewById(R.id.league_list);
//					ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectPlayers.this, android.R.layout.simple_list_item_1, player);
//							    lv.setAdapter(adapter);
					
				}
			}
	});

}
}