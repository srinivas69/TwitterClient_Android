package com.seenu.twitterclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainActivity extends ActionBarActivity {

	private String CONSUMER_KEY;
	private String CONSUMER_SECRET;

	SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CONSUMER_KEY = getResources().getString(R.string.consumer_key);
		CONSUMER_SECRET = getResources().getString(R.string.consumer_secret);

		pref = getPreferences(0);
		SharedPreferences.Editor edit = pref.edit();
		edit.putString("CONSUMER_KEY", CONSUMER_KEY);
		edit.putString("CONSUMER_SECRET", CONSUMER_SECRET);
		edit.commit();
		Fragment login = new LoginFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content_frame, login);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.addToBackStack(null);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
