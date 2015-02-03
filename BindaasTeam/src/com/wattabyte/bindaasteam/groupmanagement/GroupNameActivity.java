package com.wattabyte.bindaasteam.groupmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wattabyte.bindaasteam.R;

public class GroupNameActivity extends ActionBarActivity {
	
	public static final String GROUP_NAME = "groupName";
	
	EditText groupName;
	Button next;
	String groupNameText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_name);
		
		groupName  = (EditText) findViewById(R.id.groupName);
		
		next = (Button) findViewById(R.id.next);
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				groupNameText = groupName.getText().toString();
				
				if(groupNameText != null && !groupNameText.equals("")){
					
					Intent i = new Intent(GroupNameActivity.this, LeagueSelectActivity.class);
					i.putExtra(GROUP_NAME, groupNameText);
					startActivity(i);
					
				}else {
					Toast.makeText(GroupNameActivity.this, 
							"Please enter your Group Name", Toast.LENGTH_SHORT).show();
				}
				
				
				
			}
		});
		
		
	}

}
