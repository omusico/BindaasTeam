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
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class ProfileFragment extends Fragment {

	TextView fbUserName;
	EditText bindaasName , mailId , location;
	String mail=null , loc=null;
	ProfilePictureView profilePictureView;
	Button bindaasButton , bindaasButton2 , done;	
	ParseUser pu;
	ParseObject po;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(R.layout.profile_fragment, container , false);
		profilePictureView = (ProfilePictureView) root.findViewById(R.id.selection_profile_pic);
		profilePictureView.setCropped(true);
		fbUserName = (TextView) root.findViewById(R.id.fbUserName);
		bindaasName = (EditText) root.findViewById(R.id.bindaasName);
		bindaasButton = (Button) root.findViewById(R.id.bindaasButton);
		bindaasButton2 = (Button) root.findViewById(R.id.bindaasButton2);
		mailId = (EditText) root.findViewById(R.id.mailId);
		location = (EditText) root.findViewById(R.id.location);


		done = (Button) root.findViewById(R.id.done);


		profilePictureView.setProfileId(BindaasUtil.getFbId());

		fbUserName.setText(BindaasUtil.getFbName());

		bindaasButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BindaasUtil.setPlayerName(bindaasName.getText().toString());
				bindaasButton2.setVisibility(View.VISIBLE);
				bindaasButton.setVisibility(View.INVISIBLE);
				done.setVisibility(View.VISIBLE);
			}

		});

		bindaasButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast.makeText(NavigationDrawerActivity.getInstance(), BindaasUtil.getPlayerName(),
						Toast.LENGTH_SHORT).show();
				bindaasButton.setVisibility(View.VISIBLE);
				bindaasButton2.setVisibility(View.INVISIBLE);

			}
		});

		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mail = mailId.getText().toString();
				loc = location.getText().toString();
				pu = new ParseUser();
				po = new ParseObject("PlayerProfile");
				try {
					
						pu.setUsername(BindaasUtil.getFbName());
						pu.put("PlayerName", BindaasUtil.getPlayerName());

						if(mail != null && !mail.equals("")){
							pu.setEmail(mail);
						}
						if(loc != null && !loc.equals("")){
							pu.put("PlayerLocation", loc);
						}
						pu.saveInBackground();
					
				} catch (NullPointerException e) {
					Toast.makeText(NavigationDrawerActivity.getInstance(), "No Net Connectivity", Toast.LENGTH_LONG).show();

				}
				done.setVisibility(View.INVISIBLE);

			}
		});

		return root;
	}


}
