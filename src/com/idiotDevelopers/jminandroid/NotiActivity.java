package com.idiotDevelopers.jminandroid;

import android.os.Bundle;


import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost;

public class NotiActivity extends TabActivity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		TabHost tabHost=getTabHost();
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("공지사항")
				.setContent(new Intent(this, SchoolnotiActivity.class)));
		
		tabHost.addTab(tabHost.newTabSpec("tab2")
				.setIndicator("가정통신문")
				.setContent(new Intent(this, SchoolnewslatterActivity.class)));
		
		tabHost.setCurrentTab(0);
	}
	

}