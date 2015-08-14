package com.huaruan.other;

import java.util.ArrayList;

import com.huaruan.metroeticket.R;
import com.huaruan.metroeticket.ShowActivity;
import com.huaruan.metroeticket.StartActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ErweimaAdapter extends BaseAdapter {
	public ArrayList<Ticket> tickets;
	public Context ctx;
	public ErweimaAdapter(ArrayList<Ticket> tickets,Context ctx) {
		this.tickets = tickets;
		this.ctx = ctx;
	}
	//MyDatabaseHelper databaseHelper = new MyDatabaseHelper(ctx,"e_t.db3", 1);
	//SQLiteDatabase db = databaseHelper.getReadableDatabase();

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tickets.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return tickets.get(position);
	}

	@Override
	public long getItemId(int position) {				
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Ticket ticket = tickets.get(position);
		final int fposition = position;
		final MyDatabaseHelper databaseHelper = new MyDatabaseHelper(ctx,"e_t.db3", 1);
		final SQLiteDatabase db = databaseHelper.getReadableDatabase();
		convertView = LayoutInflater.from(ctx).inflate(R.layout.erweima_item,null);
		TextView strTxt = (TextView)convertView.findViewById(R.id.str_id);
		TextView endTxt = (TextView)convertView.findViewById(R.id.end_id);
		strTxt.setText(ticket.str);
		endTxt.setText(ticket.end);
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ctx, ShowActivity.class); 
				intent.putExtra("ticket", ticket);
				ctx.startActivity(intent);
			}
		});
		convertView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				new AlertDialog.Builder(ctx)
				.setMessage("确定删除吗？")
				.setNegativeButton("取消", null)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					    db.execSQL("delete from e_ticket where _id="+ticket.id);
						tickets.remove(fposition);
						notifyDataSetChanged();
					}
				}).show();	
				return false;
			}
		});
		return convertView;
	}

}
