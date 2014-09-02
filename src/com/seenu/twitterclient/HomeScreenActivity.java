package com.seenu.twitterclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

public class HomeScreenActivity extends ActionBarActivity {

	private Activity activity;
	private ListView lv;
	private Twitter twitter;
	private SharedPreferences pref;

	private String CONSUMER_KEY;
	private String CONSUMER_SECRET;
	private String ACCESS_TOKEN;
	private String ACCESS_TOKEN_SECRET;
	final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";

	// Accesss Token
	private AccessToken accessToken;

	final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/home_timeline.json";

	private TwitterHomeTimelineAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen_activity);
		activity = this;

		lv = (ListView) findViewById(R.id.listView1);
		pref = getSharedPreferences(MainActivity.PREF_NAME, 0);

		CONSUMER_KEY = pref.getString("CONSUMER_KEY", "");
		CONSUMER_SECRET = pref.getString("CONSUMER_SECRET", "");
		ACCESS_TOKEN = pref.getString("ACCESS_TOKEN", "");
		ACCESS_TOKEN_SECRET = pref.getString("ACCESS_TOKEN_SECRET", "");

		TwitterFactory factory = new TwitterFactory();

		// get access_token fetching the data from sharedprefernces
		accessToken = loadAccessToken();
		twitter = factory.getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		twitter.setOAuthAccessToken(accessToken);

		userHomeTimeline();
	}

	// function for getting the access token
	private AccessToken loadAccessToken() {
		// TODO Auto-generated method stub
		String token = ACCESS_TOKEN;
		String tokenSecret = ACCESS_TOKEN_SECRET;

		return new AccessToken(token, tokenSecret);
	}

	private void userHomeTimeline() {
		// TODO Auto-generated method stub
		new UserHomeTimeline().execute();
	}

	private class UserHomeTimeline extends
			AsyncTask<Void, Void, List<twitter4j.Status>> {

		// String result = null;

		@Override
		protected List<twitter4j.Status> doInBackground(Void... params) {
			// TODO Auto-generated method stub

			List<twitter4j.Status> statuses = null;
			try {
				Paging pag = new Paging(1, 50);
				statuses = twitter.getHomeTimeline(pag);

				System.out.println(statuses.size());

			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return statuses;
		}

		@Override
		protected void onPostExecute(List<twitter4j.Status> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			adapter = new TwitterHomeTimelineAdapter(getApplicationContext(),
					result);
			lv.setAdapter(adapter);
		}

	}

	public String convertStreamToString(InputStream is) {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
