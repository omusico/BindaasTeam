package com.wattabyte.bindaasteam.friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.R;

public class ResponseActivity extends ActionBarActivity{
	
	private static final String REQUEST_USER_ID = "RequestUserId";
	private static final String REQUEST_MESSAGE = "RequestMessage";
	private static final String REQUEST_NAME = "RequestName";
	public static final String REQUESTNAME = "requestName";
	public static final String REQUESTMESSAGE = "requestMessage";
	public static final String REQUESTNAMEID = "requestNameId";
	
	ArrayList<HashMap<String, String>> requestList;
	ListView requestListView ;
    ListAdapter adapter ;
    
    public static  ResponseActivity instance;
    
    public static ResponseActivity getInstance(){
    	return instance;
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		
		setContentView(R.layout.activity_response);
		ParseUser pUser = ParseUser.getCurrentUser();
		String userName = pUser.getUsername();
		requestListView =(ListView) findViewById(android.R.id.list);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Request");
		query.whereEqualTo("PlayerName", userName);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> request, ParseException e) {
		        if (e == null) {
		        	
		        	requestList = new ArrayList<HashMap<String,String>>();
		        	HashMap<String, String> map ; 
		        	for (ParseObject parseObject : request) {
		        		if(e==null){
						
							map =  new HashMap<String, String>();
							map.put(REQUESTNAME, parseObject.getString(REQUEST_NAME));
							Log.d("score", "Retrieved " +parseObject.getString(REQUEST_NAME) );
							map.put(REQUESTMESSAGE,parseObject.getString(REQUEST_MESSAGE));
							Log.d("score", "Retrieved " +parseObject.getString(REQUEST_MESSAGE));
							map.put(REQUESTNAMEID, parseObject.getString(REQUEST_USER_ID));	
							Log.d("score", "Retrieved " +parseObject.getString(REQUEST_USER_ID));
							requestList.add(map);
		        		}else {
							Log.d("MSG", "Error: " + e.getMessage());
						}
		        		adapter = new SimpleAdapter(ResponseActivity.this,
								requestList, R.layout.request_item, new String[] {REQUESTNAME, REQUESTMESSAGE },new int[] {R.id.requestName,R.id.requestMessage});
						requestListView.setAdapter(adapter);
						Log.d("Vinay", "After set AdapterView");
						requestListView.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Log.d("Vinay", "Inside On item click listener AdapterView");
								HashMap<String, String> item = requestList.get(position);
								Intent intent = new Intent(ResponseActivity.this, ReplyActivity.class);
								intent.putExtra(REQUESTNAME, item.get(REQUESTNAME));
								Log.d("Vinay", item.get(REQUESTNAME));
								intent.putExtra(REQUESTMESSAGE, item.get(REQUESTMESSAGE));
								Log.d("Vinay", item.get(REQUESTMESSAGE));
								intent.putExtra(REQUESTNAMEID, item.get(REQUESTNAMEID));
								Log.d("Vinay", item.get(REQUESTNAMEID));
								ResponseActivity.getInstance().startActivity(intent);
								Log.d("Vinay", "Inside On item click listener AdapterView");
								
							}
						});
						
						
					}
		        	
		        	 // create the grid item mapping
		        	
		        	
		        	
		            Log.d("score", "Retrieved " + requestList );
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		        
		    
		       
		    }

			
		});
		
		ParseAnalytics.trackAppOpened(getIntent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.response, menu);
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
