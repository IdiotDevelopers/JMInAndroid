package com.idiotDevelopers.Parsing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import java.io.*;
import android.os.*;
import org.jsoup.select.*;
import android.content.Context;
import com.idiotDevelopers.sqlite.*;
import com.idiotDevelopers.jminandroid.*;

public class Notiparsing
{
	String[] notiList = new String[3];
	String url;
	Document doc;
	Handler handler;
	DatabaseHandler db;
	String classname;
	public void update(String classname, Context context){
		db = new DatabaseHandler(context);
		this.classname = classname;
		handler = new Handler();
		new Thread(runnable).start();
	}
	Runnable runnable = new Runnable(){
		public void run(){
			try {
				if(classname == "SchoolnotiActivity"){
					url="http://m.jeongmyung.hs.kr/board.list?mcode=1110&no_gongji=";
				}
				else{
					url="http://m.jeongmyung.hs.kr/board.list?mcode=1112&no_gongji=y";
				}
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			handler.post(new Runnable(){
				public void run(){
					Elements noti = doc.select("div.info");
					if(classname == "SchoolnotiActivity" ){
						for(int i=0;i<15;i++){
							notiList[0] = noti.get(i).select("div.left").get(0).toString().replaceAll("&nbsp;","");
							System.out.println(notiList[0]);
							notiList[1] = noti.get(i).select("div.title").get(0).toString();
							System.out.println(notiList[1]);
							notiList[2] = noti.get(i).select("div.title > a href").get(0).toString();
							System.out.println(notiList[2]);
							db.updateNoti(new Noti(i,notiList[1], notiList[0], notiList[2]));
						}
					}
					else{
						for(int i=0;i<15;i++){
							notiList[0] = noti.get(i).select("div.left").get(0).toString().replaceAll("&nbsp;","");
							System.out.println(notiList[0]);
							notiList[1] = noti.get(i).select("div.title").get(0).toString();
							System.out.println(notiList[1]);
							notiList[2] = noti.get(i).select("div.title > a href").get(0).toString();
							System.out.println(notiList[2]);
							db.updateNewslatter(new Newslatter(i,notiList[1], notiList[0], notiList[2]));
						}
					}
					
				}
			});
			db.close();
		}
	};
}
