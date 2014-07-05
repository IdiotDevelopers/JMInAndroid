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
	public TitleCard(String titlePlay, String desc, String color){
		super(titlePlay,desc,color,"#000000",false, true);
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_title, null);
		((TextView) view.findViewById(R.id.titlecardtitle)).setText(titlePlay);
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
