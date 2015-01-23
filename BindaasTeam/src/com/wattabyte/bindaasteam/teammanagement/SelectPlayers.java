package com.wattabyte.bindaasteam.teammanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.navigationdrawer.NavigationDrawerActivity;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class SelectPlayers extends ListActivity {

	static String teamName;
	static String name;

	public static String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String league;
	TextView tv, tv1, playerno, golds;
	Button bt;
	ListView lv;
	TextView goldCount;
	View v;
	LayoutInflater inflater;
	static final String NAME = "Name";
	static final String ROLE = "Role";
	static final String GOLD = "Gold";
	int gCount = 110;
	ArrayList<HashMap<String, Object>> menuItems;
	ArrayList<String> list = new ArrayList<String>();
	ListAdapter adapter;
	static int count = 0;

	// ArrayAdapter<String> adapter;
	BindaasUtil bu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_player);

		tv = (TextView) findViewById(R.id.pName);
		tv1 = (TextView) findViewById(R.id.pGold);
		golds = (TextView) findViewById(R.id.pRole);
		bt = (Button) findViewById(R.id.button1);
		bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (count < 11) {
					int i = 11 - count;
					Toast.makeText(SelectPlayers.this,
							"Select " + i + " more players", Toast.LENGTH_SHORT)
							.show();
				} else {
					 Intent in = new Intent(SelectPlayers.this,
					 NavigationDrawerActivity.class);
					 startActivity(in);
				}
			}
		});

		Intent i = getIntent();
		teamName = i.getStringExtra("name");
		league = i.getStringExtra("league");
		tv.setText(teamName);
		tv1.setText(league);
		setName(teamName);
		String parseClass = league;

		ParseQuery<ParseObject> query = ParseQuery.getQuery("League");
		query.whereExists("Name");
		Log.i("MSG", "Before parsing");
		query.findInBackground(new FindCallback<ParseObject>() {
			@SuppressWarnings("static-access")
			@Override
			public void done(List<ParseObject> playersList, ParseException e) {
				Log.i("MSG", "inside done");
				Log.i("MSG", playersList.size() + "");
				if (e == null) {
					Log.d("MSG", "Retrieved " + playersList.size() + " players");
					Toast.makeText(SelectPlayers.this,
							"Size of a player : " + playersList.size(),
							Toast.LENGTH_LONG).show();
					
					String playerNam = null;

					menuItems = new ArrayList<HashMap<String, Object>>();
					HashMap<String, Object> map;
					for (ParseObject ob : playersList) {
						if (e == null) {

							Log.i("MSG", "mapping");
							map = new HashMap<String, Object>();
							map.put(NAME, ob.getString(NAME));
							Log.i("MSG", ob.getString(NAME));
							map.put(ROLE, ob.getString(ROLE));
							Log.i("MSG", ob.getString(ROLE));
							map.put(GOLD, ob.getString(GOLD));
							Log.i("MSG", ob.getString(GOLD));
							menuItems.add(map);
						} else {
							Log.d("MSG", "Error: " + e.getMessage());
						}
						adapter = new SimpleAdapter(SelectPlayers.this,
								menuItems, R.layout.player_list, new String[] {
										NAME, ROLE, GOLD }, new int[] {
										R.id.name, R.id.role, R.id.gold });
						setListAdapter(adapter);

						lv = getListView();
						
						// lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
						lv.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {

								String name = ((TextView) view
										.findViewById(R.id.name)).getText()
										.toString();
								String gold = ((TextView) view
										.findViewById(R.id.gold)).getText()
										.toString();

								int i = Integer.parseInt(gold);
								if (count <= 11 && (gCount - i) > 0) {
									count++;

									gCount = gCount - i;

									if (gCount > 0) {
										Log.i("MSG", name + "  " + gold
												+ "        " + count);
										Log.i("MSG", "Remaining gold is  "
												+ gCount);
										golds.setText(String.valueOf(gCount));
										list.add(name);
										setList(list);
									} else {
										Log.i("MSG", "Sorry you have " + gCount
												+ "golds");

									}
								}
								if (count > 11) {
									Log.i("MSG", "Maximum team size is 11");

									Log.i("MSG", list.toString());
								}

							}

						});
					}

				}
			}
		});

	}

	public static ArrayList<String> listPlay;

	public static void setList(ArrayList<String> list) {
		listPlay = list;

	}

	public static ArrayList<String> getList() {
		return listPlay;

	}
}