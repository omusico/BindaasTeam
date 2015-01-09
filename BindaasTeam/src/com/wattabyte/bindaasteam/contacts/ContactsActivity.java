package com.wattabyte.bindaasteam.contacts;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.facebook.Session;
import com.wattabyte.bindaasteam.MainActivity;
import com.wattabyte.bindaasteam.R;



public class ContactsActivity extends ActionBarActivity implements OnItemClickListener{
	
	private ArrayList<ContactUtil> listContacts;
    private ListView lvContacts;
    ConatctsAdapter adapterContacts;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact); 
    listContacts = new ContactFetcher(this).fetchAll();
    lvContacts = (ListView) findViewById(android.R.id.list);
     adapterContacts = new ConatctsAdapter(this, listContacts);
    lvContacts.setAdapter(adapterContacts);
    lvContacts.setOnItemClickListener(this);
}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.fblogout, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_logout) {
		// find the active session which can only be facebook in my app
	    Session session = Session.getActiveSession();
	    // run the closeAndClearTokenInformation which does the following
	    // DOCS : Closes the local in-memory Session object and clears any persistent 
	    // cache related to the Session.
	    session.closeAndClearTokenInformation();
	    // return the user to the login screen
	    startActivity(new Intent(getApplicationContext(), MainActivity.class));
	    // make sure the user can not access the page after he/she is logged out
	    // clear the activity stack

		return true;
	}
    return super.onOptionsItemSelected(item);
}


@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	
	
	
}




}
