package com.huaruan.metroeticket;

import com.huaruan.other.MyDatabaseHelper;
import com.huaruan.other.Ticket;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WeixinDialog extends Activity {
	public EditText mima;
	public Ticket ticket;
	private MyDatabaseHelper dbHelper;
	private TextView jiage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weixin_dialog);
		mima = (EditText)findViewById(R.id.weixinpass);
		jiage = (TextView)findViewById(R.id.jiage);
		Intent intent = getIntent();
		ticket = (Ticket) intent.getExtras().get("ticket");
		jiage.setText("£¤"+ticket.jiage);
		mima.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_ENTER){
					if(mima.getText().toString().equals("123")){
						Toast.makeText(WeixinDialog.this, "Ö§¸¶³É¹¦", Toast.LENGTH_LONG).show();
						mima.setText("");
						dbHelper = new MyDatabaseHelper(WeixinDialog.this, "e_t.db3", 1);
						SQLiteDatabase db = dbHelper.getReadableDatabase();
						db.execSQL("insert into e_ticket values(null,?,?)"
								, new String[]{ticket.str,ticket.end});
						Intent intent = new Intent(WeixinDialog.this, ErweimaActivity.class);
						startActivity(intent);
						WeixinDialog.this.finish();
					}
					else{
						if(mima.getText().toString().length()>0){
						Toast.makeText(WeixinDialog.this, "ÃÜÂë´íÎó", Toast.LENGTH_LONG).show();
						mima.setText("");}
					}
					return true;
					}
				return false;
			}
		});
		
	}
	
}
