package com.huaruan.other;

import java.io.Serializable;

public class Ticket implements Serializable{
	public String str,end,jiage;
	public int id;

	public Ticket(String str,String end,String jiage,int id) {
		this.str = str;
		this.end = end;
		this.id = id;
		this.jiage = jiage;
	}
}
