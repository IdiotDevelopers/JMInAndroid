package com.idiotDevelopers.jminandroid;

import android.app.Activity;
import android.app.ActionBar;
import android.os.*;
import com.idiotDevelopers.Parsing.Notiparsing;
import android.widget.*;
import android.content.*;
import java.util.*;
import com.idiotDevelopers.sqlite.*;
import android.widget.AdapterView.OnItemClickListener;
import android.view.*;
import android.util.*;
import android.net.*;

public class SchoolnewslatterActivity extends Activity implements
		OnItemClickListener {

	String[][] noti = new String[3][15];
	NotiList notilist;
	Notiparsing notiparsing = new Notiparsing();
	ListView listview;
	DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newslatter);
		listview = (ListView) findViewById(R.id.list);
		notilist = new NotiList(this, noti[1], noti[0]);
		ListUpdate();
		while (db.getNewslatterCount() != 15) {
		}
		listview.setAdapter(notilist);
	}

	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int position, long p4) {
		// TODO: Implement this method
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(noti[2][position]));
		startActivity(intent);

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_refresh:
	        	ListUpdate();
	        	notilist = new NotiList(this, noti[1], noti[0]);
	        	listview.setAdapter(notilist);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	private void ListUpdate() {
		DatabaseHandler db = new DatabaseHandler(this);
		notiparsing.open(this, "SchoolNewslatterActivity");
		while (db.getNewslatterCount() != 15) {
		}
		for (int i = 0; i < 15; i++) {
			Newslatter newslatter = db.getNewslatter(i);
			noti[1][i] = newslatter.getTitle().toString();
			noti[0][i] = newslatter.getDesc().toString();
			noti[2][i] = newslatter.getLink().toString();
			Log.d(i + "", "");
		}
		notilist.notifyDataSetChanged();
		db.closeDB();
	}
}
