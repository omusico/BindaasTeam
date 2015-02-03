package com.wattabyte.bindaasteam.navigationdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.groupmanagement.GroupManagementActivity;

public class GroupManagementFragment extends Fragment {
	
	Button add;
	Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.groups_fragment, container , false);
		add = (Button) root.findViewById(R.id.add_groups);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					intent = new Intent(NavigationDrawerActivity.getInstance(), GroupManagementActivity.class);

					startActivity(intent);

				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		
		return root;
	}

}
