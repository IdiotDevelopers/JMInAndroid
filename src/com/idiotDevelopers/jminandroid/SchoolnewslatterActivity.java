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


public class SchoolnewslatterActivity extends Activity implements OnItemClickListener
{

	String[][] noti= new String[3][15];
	NotiList notilist;
	Notiparsing notiparsing = new Notiparsing();
	ListView listview;
	DatabaseHandler db = new DatabaseHandler(this);
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newslatter);
		listview = (ListView)findViewById(R.id.list);	
		notilist = new NotiList(this, noti[1],noti[0]);
		ListUpdate();
		while(db.getNewslatterCount()!=15){}
		listview.setAdapter(notilist);
	}
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int position, long p4)
	{
		// TODO: Implement this method
		ListUpdate();
		while(db.getNewslatterCount()!=15){}
		switch(position){
			case 0:
				final String link0 = db.getNewslatter(0).getLink();
				Intent intent0 = new Intent(Intent.ACTION_VIEW);
				intent0.setData(Uri.parse(link0));
				startActivity(intent0);
				break;
			case 1:
				final String link1 = db.getNewslatter(1).getLink();
				Intent intent1 = new Intent(Intent.ACTION_VIEW);
				intent1.setData(Uri.parse(link1));
				startActivity(intent1);
				break;
			case 2:
				final String link2 = db.getNewslatter(2).getLink();
				Intent intent2 = new Intent(Intent.ACTION_VIEW);
				intent2.setData(Uri.parse(link2));
				startActivity(intent2);
				break;
			case 3:
				final String link3 = db.getNewslatter(3).getLink();
				Intent intent3 = new Intent(Intent.ACTION_VIEW);
				intent3.setData(Uri.parse(link3));
				startActivity(intent3);
				break;
		}
	}
	private void ListUpdate(){
		DatabaseHandler db = new DatabaseHandler(this);
		notiparsing.open(this,"SchoolNewslatterActivity");
		while(db.getNewslatterCount()!=15){}
		for(int i=0;i<15;i++){
			Newslatter newslatter = db.getNewslatter(i);
			noti[1][i] = newslatter.getTitle().toString();
			noti[0][i] = newslatter.getDesc().toString();
			noti[2][i] = newslatter.getLink().toString();
			Log.d(i+"","");
		}
		notilist.notifyDataSetChanged();
		db.closeDB();
	}
}
