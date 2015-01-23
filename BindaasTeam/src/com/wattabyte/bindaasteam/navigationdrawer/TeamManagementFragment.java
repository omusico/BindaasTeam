package com.wattabyte.bindaasteam.navigationdrawer;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.teammanagement.Players;
import com.wattabyte.bindaasteam.teammanagement.TeamName;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class TeamManagementFragment extends Fragment {
	

	Button button;
	TextView tv;
	Intent i;
	BindaasUtil bu;
	ListView lv;
	static ArrayList<String> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.team_management_fragment, container , false);
		button = (Button) root.findViewById(R.id.button1);
		 button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					 i = new Intent(NavigationDrawerActivity.getInstance(),TeamName.class);
					startActivity(i);
					//finish();
				}
			});
	        
	        lv = (ListView) root.findViewById(R.id.listView1);
	        
	     
	      
	        ListAdapter adapter = new ArrayAdapter<String>(NavigationDrawerActivity.getInstance(),android.R.layout.simple_expandable_list_item_1,TeamName.tN);
		    lv.setAdapter(adapter);
		    
		    lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					i = new Intent(NavigationDrawerActivity.getInstance(), Players.class);
					startActivity(i);
					}
			});
		return root;
	}
}
