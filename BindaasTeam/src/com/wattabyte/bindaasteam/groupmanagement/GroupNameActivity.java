package com.wattabyte.bindaasteam.groupmanagement;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.wattabyte.bindaasteam.R;

public class GroupNameActivity extends ActionBarActivity {

	public static final String GROUP_NAME = "groupName";
	public static final String BINDAAS_TEAM_NAME = "BindaasTeamNames";
	public static final String BINDAAS_NAMES = "BindaasNames";
	EditText groupName;
	Button next;
	String groupNameText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_name);

		groupName = (EditText) findViewById(R.id.groupName);
		
		next = (Button) findViewById(R.id.next);

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				groupNameText = groupName.getText().toString();
				if (groupNameText != null
						&& !groupNameText.equals("")) {

					ParseQuery<ParseObject> query = ParseQuery.getQuery(BINDAAS_TEAM_NAME);
					query.whereExists(BINDAAS_NAMES);
					query.findInBackground(new FindCallback<ParseObject>() {
						
						@Override
						public void done(List<ParseObject> bindaasNames, ParseException e) {
							if (e == null) {
								groupName = (EditText) GroupNameActivity.this.findViewById(R.id.groupName);
								String groupNameTexts = groupName.getText().toString();
								boolean flag = false;
								for (ParseObject parseObject : bindaasNames) {
									if(parseObject.getString(BINDAAS_NAMES).equals(groupNameTexts)){
										flag = false;
									}
									else{
										flag = true;
									}
								}
								if (flag) {
								ParseObject	object = new ParseObject(BINDAAS_TEAM_NAME);
									object.saveInBackground();
									object.put(BINDAAS_NAMES, groupNameText);
									Intent i = new Intent(GroupNameActivity.this,
											LeagueSelectActivity.class);
									i.putExtra(GROUP_NAME, groupNameText);
									startActivity(i);
									
								} else {
									Toast.makeText(GroupNameActivity.this,
											"Change The GroupName", Toast.LENGTH_SHORT).show();
								}
							}
							
						}
					});

				} else {
					Toast.makeText(GroupNameActivity.this,
							"Please enter your Group Name",
							Toast.LENGTH_SHORT).show();
				}

			}
		});


	}

}
