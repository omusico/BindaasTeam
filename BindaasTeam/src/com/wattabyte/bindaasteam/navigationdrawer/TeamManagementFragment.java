package com.wattabyte.bindaasteam.navigationdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.MainActivity;
import com.wattabyte.bindaasteam.teammanagement.TeamName;

public class TeamManagementFragment extends Fragment {
	

	Button button;
	TextView tv;
	Intent i;
	String teamName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.team_management_fragment, container , false);
		button = (Button) root.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				i = new Intent(MainActivity.getInstance(),TeamName.class);
				MainActivity.getInstance().startActivity(i);
			}
		});
		return root;
	}
}
