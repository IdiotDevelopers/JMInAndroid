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


public class SchoolnewslatterActivity extends Activity implements OnItemClickListener
{

	String[][] noti= new String[3][15];
	NotiList notilist;
	Notiparsing notiparsing = new Notiparsing();
	ListView listview;
	private final Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			ListUpdate();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newslatter);
		listview = (ListView)findViewById(R.id.list);
		notiparsing.open(this,"SchoolNewslatterActivity",handler);
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
	private void ListUpdate(){
		for(int i=0;i<15;i++){
			DatabaseHandler db = new DatabaseHandler(this);
			Newslatter newslatter = db.getNewslatter(i);
			noti[1][i] = newslatter.getTitle().toString();
			noti[0][i] = newslatter.getDesc().toString();
			noti[2][i] = newslatter.getLink().toString();
			Log.d(i+"","");
		}
		notilist.notifyDataSetChanged();
	}
}
