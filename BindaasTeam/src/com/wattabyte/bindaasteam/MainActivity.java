package com.wattabyte.bindaasteam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.wattabyte.bindaasteam.navigationdrawer.NavigationDrawerActivity;

public class MainActivity extends ActionBarActivity {

	public static MainActivity instance;
	EditText userName, password;
	Button login, signup;
	String name, pwd;

	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		userName = (EditText) findViewById(R.id.userName);
		password = (EditText) findViewById(R.id.password);

		login = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				name = userName.getText().toString();
				pwd = password.getText().toString();
				if (name != null && !name.equals("") && pwd != null
						&& !pwd.equals("")) {

					ParseUser.logInInBackground(name, pwd, new LogInCallback() {
						public void done(ParseUser user, ParseException e) {
							if (user != null) {
								Intent in = new Intent(MainActivity.this,
										NavigationDrawerActivity.class);
								startActivity(in);
							} else {
								// Signup failed. Look at the ParseException to
								// see what happened.

								AlertDialog.Builder alert = new AlertDialog.Builder(
										context);
								alert.setMessage("Validate your Email, by clicking on the link set to you");
								alert.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {
											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {

											}
										});
								AlertDialog alertDialog = alert.create();
								alertDialog.show();
							}
						}
					});
				} else {
					Toast.makeText(MainActivity.this, "Fill the fields",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SignUp.class);
				startActivity(i);

			}
		});

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

	

}
