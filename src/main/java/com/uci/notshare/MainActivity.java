package com.uci.notshare;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.alibaba.fastjson.JSON;
import com.facebook.appevents.AppEventsLogger;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.uci.adapter.Notelistadapter;
import com.uci.bean.NoteBean;
import com.uci.tool.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView notelist;
	private List<NoteBean> notecontent=new ArrayList<NoteBean>();
	private Notelistadapter noteadapter;
	private Button bt;
	private Button post;
	private String address;
	private InetAddress ia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        notelist=(ListView) findViewById(R.id.notelist);
        bt=(Button) findViewById(R.id.button1);
        post=(Button) findViewById(R.id.button2);
		Toast.makeText(getApplicationContext(),address,Toast.LENGTH_LONG).show();

		post.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,PostNotes.class);
				startActivity(intent);
			}
		});
        bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Login.class);
				startActivity(intent);
			}
		});


       HttpUtil.get("http://"+ data.url+":9999/qurey/",new JsonHttpResponseHandler(){
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				String responseBody) {
			super.onSuccess(statusCode, headers, responseBody);
			Toast.makeText(getApplicationContext(), responseBody, Toast.LENGTH_LONG).show();
			notecontent=JSON.parseArray(responseBody, NoteBean.class);
			noteadapter=new Notelistadapter(getApplicationContext(), notecontent);
			notelist.setAdapter(noteadapter);
			Log.v("ss", responseBody);
		}
       }); 
    }

//	@Override
//	protected void onResume() {
//	super.onResume();
//
//	// Logs 'install' and 'app activate' App Events.
//	AppEventsLogger.activateApp(this);
//}
//
//	@Override
//	protected void onPause() {
//		super.onPause();
//
//		// Logs 'app deactivate' App Event.
//		AppEventsLogger.deactivateApp(this);
//	}
}
