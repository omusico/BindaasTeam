package com.wattabyte.bindaasteam.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.widget.ProfilePictureView;
import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class ProfileFragment extends Fragment {
	
	TextView fbUserName;
	ProfilePictureView profilePictureView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View root = inflater.inflate(R.layout.profile_fragment, container , false);
		profilePictureView = (ProfilePictureView) root.findViewById(R.id.selection_profile_pic);
		
		fbUserName = (TextView) root.findViewById(R.id.fbUserName);
		 profilePictureView.setProfileId(BindaasUtil.getFbId());
		
		fbUserName.setText(BindaasUtil.getFbName());
		
		return root;
	}

}
