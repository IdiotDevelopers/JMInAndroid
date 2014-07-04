package com.idiotDevelopers.card;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import com.idiotDevelopers.jminandroid.R;

public class TitleCard extends Card{
	public TitleCard(String title, String desc, String color){
		super(title,desc,color,"#FFFFFF",false, true);
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_title, null);
		((TextView) view.findViewById(R.id.title)).setText(title);
		((TextView) view.findViewById(R.id.desc)).setText(desc);
		((ImageView) view.findViewById(R.id.stripe)).setBackgroundColor(Color
				.parseColor(color));
		return view;
	}

	@Override
	public boolean convert(View convertCardView) {
		// TODO Auto-generated method stub
		return true;
	}
}