package com.wattabyte.bindaasteam.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.ProfilePictureView;
import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class ProfileFragment extends Fragment {

	TextView fbUserName;
	EditText bindaasName;
	ProfilePictureView profilePictureView;
	Button bindaasButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.profile_fragment, container , false);
		profilePictureView = (ProfilePictureView) root.findViewById(R.id.selection_profile_pic);
		profilePictureView.setCropped(true);
		fbUserName = (TextView) root.findViewById(R.id.fbUserName);
		bindaasName = (EditText) root.findViewById(R.id.bindaasName);
		bindaasButton = (Button) root.findViewById(R.id.bindaasButton);
		
		
		profilePictureView.setProfileId(BindaasUtil.getFbId());

		fbUserName.setText(BindaasUtil.getFbName());
		
		bindaasButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BindaasUtil.setPlayerName(bindaasName.getText().toString());
				Toast.makeText(NavigationDrawerActivity.getInstance(), BindaasUtil.getPlayerName(),Toast.LENGTH_SHORT).show();
				
			}
		});

		return root;
	}

}
