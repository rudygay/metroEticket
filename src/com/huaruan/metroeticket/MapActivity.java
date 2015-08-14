package com.huaruan.metroeticket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huaruan.other.Search;
import com.huaruan.other.Ticket;

public class MapActivity extends Activity{
	private WebView webView;
	private TextView textView;
	private String str,end,jiage;
	private RelativeLayout show_lay;
	private boolean is_str = false,is_end = false;
	private Search search;
	
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled", "JavascriptInterface" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webview);
		textView=(TextView)findViewById(R.id.leadTxt);
		show_lay = (RelativeLayout) findViewById(R.id.show_lay);
		search = new Search();
		//֧��js�ļ�
		webView.getSettings().setJavaScriptEnabled(true);
		//֧������
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
      //�������������
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //���طŴ���С����
        webView.getSettings().setDisplayZoomControls(false);
        //���ع�����
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        //���ر����ļ�
        webView.loadUrl("file:///android_asset/index.html");
        webView.setInitialScale(50);
        
    	webView.setWebChromeClient(new WebChromeClient() {
    		public void onConsoleMessage(String message, int lineNumber,
    				String sourceID) {
    			String panduan[] = message.split(" ");
    			if(panduan[0].equals("1")){
    				Log.v("jjklml","�������");
    				str = panduan[1];
    				show_lay.setVisibility(View.VISIBLE);
    				is_str = true;
    				if(!is_end){
    					textView.setText("�������յ�");
    				}
    				else{
    					String resultStr = search.doLineSearch(str, end);
    					String showshow[] = resultStr.split("@");
    					jiage = showshow[1];
    					textView.setText(showshow[0]+"��"+"Ʊ��"+showshow[1]+"Ԫ");
    				}
    			}
    			if(panduan[0].equals("0")){
    				Log.v("jjklml","�����յ�");
    				end = panduan[1];
    				show_lay.setVisibility(View.VISIBLE);
    				is_end = true;
    				if(!is_str){
    					textView.setText("���������");
    				}
    				else{
    					String resultStr = search.doLineSearch(str, end);
    					String showshow[] = resultStr.split("@");
    					jiage = showshow[1];
    					textView.setText(showshow[0]+"��"+"Ʊ��"+showshow[1]+"Ԫ");   					
    				}
    			}
    			if(panduan[0].equals("3")){
    				Log.v("njk","���");
    				show_lay.setVisibility(View.INVISIBLE);
    				is_end = false;
					is_end = false;
    			}
    		}
    		@Override
    		public boolean onJsAlert(WebView view, String url, String message,
    				JsResult result) {
    			// TODO Auto-generated method stub
    			return super.onJsAlert(view, url, message, result);  
    		}
    	});
	}
	
	class MyWebViewClient extends WebViewClient{ 
		@Override 
		public boolean shouldOverrideUrlLoading(WebView view,String url_){ 
			view.loadUrl(url_); 
			return true;
		}
	}
	public void erweima(View v){
		Intent intent = new Intent(this, ErweimaActivity.class);
		startActivity(intent);
	}
	public void more(View v){
		Intent intent = new Intent(this, MoreActivity.class);
		startActivity(intent);
	}
	public void buy(View v){
		if(is_end && is_str){
		Intent intent = new Intent(this, BuyActivity.class);
		Ticket ticket = new Ticket(str, end,jiage,0);
		intent.putExtra("ticket", ticket);
		startActivity(intent);}
	}
}
