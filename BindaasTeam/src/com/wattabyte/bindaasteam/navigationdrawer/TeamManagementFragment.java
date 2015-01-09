package com.wattabyte.bindaasteam.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wattabyte.bindaasteam.R;

public class TeamManagementFragment extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.team_management_fragment, container , false);
		return root;
	}

}
