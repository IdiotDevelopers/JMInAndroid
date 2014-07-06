package com.idiotDevelopers.sqlite;
import com.idiotDevelopers.jminandroid.*;

public class Noti {

	//private variables
	int _id;
	String _title;
	String _desc;
	String _link;

	// Empty constructor
	public Noti(){

	}
	// constructor
	public Noti(int id, String title, String desc, String link){
		this._id = id;
		this._title = title;
		this._desc = desc;
		this._link = link;
	}

	// constructor
	public Noti(String title, String desc, String link){
		this._title = title;
		this._desc = desc;
		this._link = link;
	}
	// getting ID
	public int getID(){
		return this._id;
	}

	// setting id
	public void setID(int id){
		this._id = id;
	}

	// getting title
	public String getTitle(){
		return this._title;
	}

	// setting title
	public void setTitle(String title){
		this._title = title;
	}

	// getting author
	public String getDesc(){
		return this._desc;
	}

	// setting author
	public void setDesc(String desc){
		this._desc = desc;
	}

	// getting link
	public String getLink(){
		return this._link;
	}

	// setting author
	public void setLink(String link){
		this._link = link;
	}
}
