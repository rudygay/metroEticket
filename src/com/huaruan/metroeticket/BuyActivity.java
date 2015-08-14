package com.huaruan.metroeticket;

import com.huaruan.other.Ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BuyActivity extends Activity {
	public ImageView weixinch,zhifubaoch;
	boolean is_weixin = false,is_zhifubao = true;
	public Ticket ticket;
	private TextView buy_txt,ticket_price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy);
		weixinch = (ImageView)findViewById(R.id.weixincheck);
		zhifubaoch = (ImageView)findViewById(R.id.zhifubaocheck);
		buy_txt = (TextView) findViewById(R.id.buy_txt);
		ticket_price = (TextView)findViewById(R.id.ticket_price);
		Intent intent = getIntent();
		ticket = (Ticket) intent.getExtras().get("ticket");
		buy_txt.setText(ticket.str+"µ½"+ticket.end);
		ticket_price.setText("RMB "+ticket.jiage+"Ôª");
		Log.v("str", ticket.str);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	public void buy_back(View v){
		this.finish();
	}
	public void erweima(View v){
		Intent intent = new Intent(this, ErweimaActivity.class);
		startActivity(intent);
	}
	public void zhifubao(View v){
		zhifubaoch.setImageResource(R.drawable.rad_pressed);
		weixinch.setImageResource(R.drawable.rad_normal);
		is_weixin = false;
		is_zhifubao = true;
	}
	public void weixin(View v){
		zhifubaoch.setImageResource(R.drawable.rad_normal);
		weixinch.setImageResource(R.drawable.rad_pressed);
		is_weixin = true;
		is_zhifubao = false;
	}
	public void zhifu(View v){
		if(is_zhifubao){
			Intent intent = new Intent(this, ZhifubaoDialog.class);
			intent.putExtra("ticket", ticket);
			startActivity(intent);
		}
		else{
			Intent intent = new Intent(this, WeixinDialog.class);
			intent.putExtra("ticket", ticket);
			startActivity(intent);
		}
	}
}
