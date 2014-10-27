package com.idiotDevelopers.jminandroid;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.fima.cardsui.views.*;
import com.fima.cardsui.objects.*;
import com.idiotDevelopers.card.BasicCard;
import com.idiotDevelopers.card.TitleCard;

public class MainActivity extends Activity {
	CardUI Main;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Main = (CardUI)findViewById(R.id.mainCard);
		TitleCard noti = new TitleCard("공지사항","","#cd5c5c");
		noti.setOnClickListener(new OnClickListener(){
			@Override
	        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,NotiActivity.class);
            startActivity(intent);
			}
		});
		Main.addCard(noti);
		TitleCard eat = new TitleCard("급식","","#000000");
		eat.setOnClickListener(new OnClickListener(){
			@Override
	        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,EatActivity.class);
            startActivity(intent);
			}
		});
		Main.addCard(eat);
		TitleCard Schedule = new TitleCard("시간표","","#5f9ea0");
		eat.setOnClickListener(new OnClickListener(){
			@Override
	        public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,ScheduleActivity.class);
	            startActivity(intent);
			}
		});
		Main.addCard(Schedule);
		TitleCard set = new TitleCard("설정","","#bc8f8f");
		set.setOnClickListener(new OnClickListener(){
			@Override
	        public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,SetActivity.class);
	            startActivity(intent);
			}
		});
		Main.addCard(set);
		TitleCard info = new TitleCard("정보","","#00cedd");
		info.setOnClickListener(new OnClickListener(){
			@Override
	        public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,InfoActivity.class);
	            startActivity(intent);
			}
		});
		Main.addCard(info);
	    Main.refresh();
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
