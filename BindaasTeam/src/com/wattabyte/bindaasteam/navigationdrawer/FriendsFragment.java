package com.wattabyte.bindaasteam.navigationdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.wattabyte.bindaasteam.R;
import com.wattabyte.bindaasteam.contacts.ContactsActivity;


public class FriendsFragment extends Fragment {

	Button add;
	Intent intent;

	public static final String MSG = "FriendsFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.friends_fragment, container , false);
		add = (Button) root.findViewById(R.id.add_friends);

		Log.d(MSG, "Before setOnclick Listener");

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {

					intent = new Intent(NavigationDrawerActivity.getInstance(), ContactsActivity.class);

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
