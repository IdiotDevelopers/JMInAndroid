package com.idiotDevelopers.Parsing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import java.io.*;
import android.os.*;
import org.jsoup.select.*;
import android.content.*;
import com.idiotDevelopers.sqlite.*;
import com.idiotDevelopers.jminandroid.*;
import android.util.*;
import android.app.*;
import android.database.*;

public class Notiparsing
{
	String[] notiList = new String[3];
	String url;
	Document doc;
	Handler handler;
	String classname;
	DatabaseHandler db;
	Context context;
	Element notiEle;
	public void open(Context context, String Classname,Handler handler)
	{
		this.context = context;
		this.handler = handler;
		classname = Classname;
		db = new DatabaseHandler(context);
		try
		{
			if(classname == "SchoolnotiActivity"){
				url="http://m.jeongmyung.hs.kr/board.list?mcode=1110&no_gongji=";
			}
			else{
				url="http://m.jeongmyung.hs.kr/board.list?mcode=1112&no_gongji=y";
			}
			process();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private void process() throws IOException
	{
		//상태 Progress 띄우기 위해서 사용함!
		final Handler mHandler = new Handler();
		new Thread()
		{

			@Override
			public void run()
			{
				try
				{
					doc=Jsoup.connect(url).get();
					
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				Elements noti = doc.select("li");
				if(classname == "SchoolnotiActivity" ){
					for(int i=8;i<23;i++){
						notiEle = noti.get(i);
						notiList[0] = notiEle.select("div.left").get(0).toString().replaceAll("&nbsp;","");
						notiList[1] = notiEle.select("div.title").get(0).toString();
						notiList[2] = notiEle.select("div.title > a").get(0).toString();					
						db.updateNoti(new Noti(i,notiList[1], notiList[0], notiList[2]));
						Log.d("parsing...",notiList[1]+notiList[0]+notiList[2]);
					}
				}
				else{
					for(int i=0;i<15;i++){
						notiEle = noti.get(i+8);
						notiList[0] = notiEle.select("div.left").get(0).text().toString().replaceAll("&nbsp;","");
						notiList[1] = notiEle.select("div.title").get(0).text().toString();
						notiList[2] = notiEle.select("div.title > a").get(0).text("href").toString();
						if(db.getNewslatterCount() == i){
							db.addNewslatter(new Newslatter(i,notiList[1], notiList[0], notiList[2]));
						}
						else if(db.getNewslatter(i).getTitle() != notiList[1]){
							db.updateNewslatter(new Newslatter(i,notiList[1], notiList[0], notiList[2]));
						}
						Log.d("parsing...",i+db.getNewslatter(i).getTitle()+db.getNewslatter(i).getDesc()+db.getNewslatter(i).getLink());
					}
				}	
				db.closeDB();

				mHandler.post(new Runnable(){
					public void run()
					{
						//업데이트 완료를 핸들러로 보내줌
						handler.sendEmptyMessage(0);
					}
				});
			}

		}.start();
	}
}
