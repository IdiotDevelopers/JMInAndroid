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


public class SchoolnewslatterActivity extends Activity implements OnItemClickListener
{

	String[][] noti;
	NotiList notilist;
	Notiparsing notiparsing = new Notiparsing();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newslatter);
		ListView listview = (ListView)findViewById(R.id.list);
		notiparsing.update("SchoolNewslatterActivity",this);
		DatabaseHandler db = new DatabaseHandler(this);
		for(int i=0;i<15;i++){
			Newslatter newslatter = db.getNewslatter(i);
			noti[1][i] = newslatter.getTitle().toString();
			noti[0][i] = newslatter.getDesc().toString();
			noti[2][i] = newslatter.getLink().toString();
		}
		notilist = new NotiList(this, noti[1],noti[0]);
		listview.setAdapter(notilist);
	}
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int position, long p4)
	{
		// TODO: Implement this method
		switch(position){
			case 0:
				break;
		}
	}
}
