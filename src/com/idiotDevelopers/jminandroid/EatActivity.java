package com.idiotDevelopers.jminandroid;

import android.os.Bundle;


import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.support.v4.app.NavUtils;

public class EatActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		TabHost tabHost=getTabHost();
		tabHost.addTab(tabHost.newTabSpec("tab1")
					   .setIndicator("LUNCH")
					   .setContent(new Intent(this, LunchActivity.class)));

		tabHost.addTab(tabHost.newTabSpec("tab2")
					   .setIndicator("DINNER")
					   .setContent(new Intent(this, DinnerActivity.class)));

		tabHost.setCurrentTab(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		getMenuInflater().inflate(R.menu.refresh, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch(item.getItemId()){
			case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
