package com.wattabyte.bindaasteam.navigationdrawer;


import com.wattabyte.bindaasteam.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotificationFragment extends Fragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.notification_fragment, container , false);
		return root;
	}

}
