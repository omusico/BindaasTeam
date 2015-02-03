package com.wattabyte.bindaasteam.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.R;

public class ReplyActivity extends ActionBarActivity {
	
	public static final String REQUESTNAME = "requestName";
	public static final String REQUESTMESSAGE = "requestMessage";
	public static final String REQUESTNAMEID = "requestNameId";
	
	TextView reqName, reqMessage;
	Button accept , request;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply);
		Log.d("Vinay", "Inside Reply Activity");
		
		Intent i = getIntent();
		Log.d("Vinay", "Inside getIntent");
		final String requestName = i.getStringExtra(REQUESTNAME);
		String requestMessage = i.getStringExtra(REQUESTMESSAGE);
		final String requestNameId = i.getStringExtra(REQUESTNAMEID);
		Log.d("Vinay", "After getting strings");
		reqName = (TextView) findViewById(R.id.requestName);
		reqMessage = (TextView) findViewById(R.id.requestMessage);
		accept = (Button) findViewById(R.id.accept);
		Log.d("Vinay", "After gsetting");
		
		reqName.setText(requestName);
		reqMessage.setText(requestMessage);
		
		ParseUser pUser = ParseUser.getCurrentUser();
		final String rUserName = pUser.getUsername();
		final String rUserId = pUser.getObjectId();
		
		accept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ParseObject freindAccept = new ParseObject(""+rUserName);
				freindAccept.put("FriendName", ""+requestName);
				freindAccept.put("FriendId", ""+requestNameId);
				freindAccept.saveInBackground();
				ParseObject freindRequest = new ParseObject(""+requestName);
				freindRequest.put("FriendName", ""+rUserName);
				freindRequest.put("FriendId", ""+rUserId);
				freindRequest.saveInBackground();
			}
		});
		ParseAnalytics.trackAppOpened(getIntent());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reply, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
