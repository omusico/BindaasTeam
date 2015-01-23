package com.wattabyte.bindaasteam;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.util.Names;

public class BindaasApplication extends Application {
	
	private final static String YOUR_APPLICATION_ID = "5Gnl5yYb2Q2TGqL8j0X8V1OurhCngQ8wO7BPRo4E";
	private final static  String YOUR_CLIENT_KEY = "wibeIiRiFwiHoHPe1ayjcCC9KTqatzz73fCcyySc";
	  
	 @Override
	  public void onCreate() {
	    super.onCreate();
	    ParseObject.registerSubclass(Names.class);
	    Parse.enableLocalDatastore(getApplicationContext());
	    // Initialize Crash Reporting.
	    ParseCrashReporting.enable(this);

	    // Add your initialization code here
	    Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);


	    ParseUser.enableAutomaticUser();
	    ParseACL defaultACL = new ParseACL();
	      
	    // If you would like all objects to be private by default, remove this line.
	    defaultACL.setPublicReadAccess(true);
	    
	    ParseACL.setDefaultACL(defaultACL, true);
	  }
}

