package com.huaruan.metroeticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moremore);
	}
	public void more_back(View v){
		this.finish();
	}
	public void dataUpdate(View v){
		Toast.makeText(this, "数据已是最新",Toast.LENGTH_LONG).show();
	}
	public void appUpdate(View v){
		Toast.makeText(this, "已更新到版本v1.0.1",Toast.LENGTH_LONG).show();
	}
	public void help(View v){
		startActivity(new Intent(this, Help.class));
	}
	public void aboutUs(View v){
		startActivity(new Intent(this, About.class));
	}
}
