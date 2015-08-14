package com.huaruan.metroeticket;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huaruan.other.MyDatabaseHelper;
import com.huaruan.other.Ticket;

public class ZhifubaoDialog extends Activity {
	public Button cancle,sure;
	public EditText mima;
	public Ticket ticket;
	private MyDatabaseHelper dbHelper;
	private TextView jiage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhifubao_dialog);
		cancle = (Button) findViewById(R.id.cancel);
		sure = (Button) findViewById(R.id.sure);
		mima = (EditText)findViewById(R.id.mima);
		jiage = (TextView)findViewById(R.id.jiage);
		Intent intent = getIntent();
		ticket = (Ticket) intent.getExtras().get("ticket");
		jiage.setText(ticket.jiage+"元");
	}
	public void cancle(View v){
		this.finish();
	}
	public void sure(View v){
		if(mima.getText().toString().equals("123")){
			Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
			mima.setText("");
			dbHelper = new MyDatabaseHelper(this, "e_t.db3", 1);
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			db.execSQL("insert into e_ticket values(null,?,?)"
					, new String[]{ticket.str,ticket.end});
			Intent intent = new Intent(this, ErweimaActivity.class);
			startActivity(intent);
			this.finish();
		}
		else{
			Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
			mima.setText("");
		}
	}
}
