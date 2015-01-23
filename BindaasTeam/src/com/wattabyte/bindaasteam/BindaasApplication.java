package com.wattabyte.bindaasteam;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class BindaasApplication extends Application {
	public static final String VINAY = "msg";
	private final static String YOUR_APPLICATION_ID = "AagOi8uLMB5wMZyK9xglRf7C1ftseZ9SZZX2ha7q";
	private final static  String YOUR_CLIENT_KEY = "N4y3KV8VuFAFzva2PPO42IWgHXGhHnAEOb5rWdPL";
	  
	 @Override
	  public void onCreate() {
	    super.onCreate();
	    
	    Log.d(VINAY, "on create Bindaas Application");
	  
	    // Initialize Crash Reporting.
	    ParseCrashReporting.enable(this);
	    Log.d(VINAY, "on crash reporting enable");

	    // Add your initialization code here
	    Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
	    Log.d(VINAY , "On parse initialiaze");

	    ParseUser.enableAutomaticUser();
	    ParseACL defaultACL = new ParseACL();
	     Log.d(VINAY, "ParcelACL"); 
	    // If you would like all objects to be private by default, remove this line.
	    defaultACL.setPublicReadAccess(true);
	    
	    ParseACL.setDefaultACL(defaultACL, true);
	  }
}

