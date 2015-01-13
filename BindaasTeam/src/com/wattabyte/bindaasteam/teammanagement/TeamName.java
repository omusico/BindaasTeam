package com.wattabyte.bindaasteam.teammanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wattabyte.bindaasteam.R;

public class TeamName extends ActionBarActivity {
	
	EditText editText;
	Button button;
	
	private static final String LOG = "MSG";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_name);
		
		editText = (EditText) findViewById(R.id.editText1);
		
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	     		Intent i = new Intent(TeamName.this,SelectLeague.class);
	     		i.putExtra("text", editText.getText().toString());
	     		Log.i(LOG, "Starting Activity");
				startActivity(i);
			}
		});
	}

}
