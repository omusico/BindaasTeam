package com.wattabyte.bindaasteam;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.wattabyte.bindaasteam.navigationdrawer.NavigationDrawerActivity;
import com.wattabyte.bindaasteam.util.BindaasUtil;

public class MainFragment extends Fragment {

	private static final String TAG = "MainFragment";
	public static final String UN = "User";
	
	private UiLifecycleHelper uiHelper;
	
		private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call( Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container ,false);

		
		LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
		authButton.setFragment(this);
		authButton.setReadPermissions(Arrays.asList("public_profile"));
		return view;
	}


	private void onSessionStateChange(final Session session, SessionState state, Exception exception) {
		
		if (state.isOpened() ) {
			
			if (session != null && session.isOpened()) {
		        // Get the user's data.
		        makeMeRequest(session);
		    }

			Intent intent = new Intent(MainActivity.getInstance() , NavigationDrawerActivity.class);
			startActivity(intent);
			MainActivity.getInstance().finish();
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();

		// For scenarios where the main activity is launched and user
		// session is not null, the session state change notification
		// may not be triggered. Trigger it if it's open/closed.
		Session session = Session.getActiveSession();
		if (session != null &&
				(session.isOpened() || session.isClosed()) ) {
			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}
	
	private void makeMeRequest(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
	    Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            // If the response is successful
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                   	        BindaasUtil.setFbName(user.getName());
	                   	        BindaasUtil.setFbId(user.getId());
	                   	    
	                }
	            }
	            if (response.getError() != null) {
	            	 FacebookRequestError error = response.getError();
	                 if (error != null) {
	                      Log.d(UN , error.getErrorMessage()); 
	                 }
	            }
	        }

			

			
	    });
	    request.executeAsync();
	} 
	
	
	

}
