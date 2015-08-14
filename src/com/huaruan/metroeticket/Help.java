package com.huaruan.metroeticket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Help extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
	}
	public void back(View v){
		this.finish();
	}
}
