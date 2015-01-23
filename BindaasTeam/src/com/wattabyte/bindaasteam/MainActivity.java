package com.wattabyte.bindaasteam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AppEventsLogger;


public class MainActivity extends ActionBarActivity{

	
	public static MainActivity instance;

	public static MainActivity getInstance(){
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		Log.d(BindaasApplication.VINAY, "Main activity on create");
		setContentView(R.layout.first_main_activity);
		Log.d(BindaasApplication.VINAY, "content view setting");
		Fragment fragment = new MainFragment();
		Log.d(BindaasApplication.VINAY, "Setting New fragment");
		FragmentManager fragmentManager = getSupportFragmentManager();
		Log.d(BindaasApplication.VINAY, "Set fragment Manager");
		fragmentManager.beginTransaction().replace(R.id.mainContent, fragment).commit();
		Log.d(BindaasApplication.VINAY, "replace fragment");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	protected void onResume() {
		super.onResume();

		// Logs 'install' and 'app activate' App Events.
		AppEventsLogger.activateApp(this);
	}

	@Override
	protected void onPause() {
		super.onPause();

		// Logs 'app deactivate' App Event.
		AppEventsLogger.deactivateApp(this);
	}




}
