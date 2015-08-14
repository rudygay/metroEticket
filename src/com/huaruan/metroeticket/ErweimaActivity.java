package com.huaruan.metroeticket;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.huaruan.other.ErweimaAdapter;
import com.huaruan.other.MyDatabaseHelper;
import com.huaruan.other.Ticket;

public class ErweimaActivity extends Activity {
	public ListView erweimaListview;
	public ErweimaAdapter erweimaAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.erweima);
		erweimaListview = (ListView) findViewById(R.id.erweimaList);
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this, "e_t.db3", 1);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		Cursor c = db.query("e_ticket",null,null,null,null,null,null);
		while(c.moveToNext()){
			String st = c.getString(c.getColumnIndex("str"));
		    String en = c.getString(c.getColumnIndex("end"));
		    int id = c.getInt(c.getColumnIndex("_id"));
		    Ticket ticket = new Ticket(st, en,"0",id);
		    tickets.add(ticket);
		}
		erweimaAdapter = new ErweimaAdapter(tickets, this);
		erweimaListview.setAdapter(erweimaAdapter);		
	}
	public void er_back(View v){
		this.finish();
	}
	
	 
}
