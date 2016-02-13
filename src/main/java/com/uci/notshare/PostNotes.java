package com.uci.notshare;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;
import org.json.JSONArray;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uci.tool.HttpUtil;
import com.uci.tool.PreferenceUtils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PostNotes extends Activity{
	private Button uploadpic;
	private Button sure;
	private ImageView note;
	private EditText notecom;
	private static final int INTENT_ACTION_PICTURE = 0;
	private File file;
	private String postcom;
	private String puttime;
	RequestParams requstpost=new RequestParams();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postnotes);
		sure=(Button) findViewById(R.id.sure);
		note=(ImageView) findViewById(R.id.note);
		notecom=(EditText) findViewById(R.id.notecom);
		uploadpic=(Button) findViewById(R.id.upload);
//		requstpost.put("username", PreferenceUtils.getPrefString(getApplicationContext(), "username", ""));
		requstpost.put("userid", PreferenceUtils.getPrefString(getApplicationContext(), "userid", ""));
//		requstpost.put("avatar", PreferenceUtils.getPrefString(getApplicationContext(), "avatar", ""));
		Toast.makeText(getApplicationContext(), PreferenceUtils.getPrefString(getApplicationContext(), "username", "")+PreferenceUtils.getPrefString(getApplicationContext(), "userid", ""), 1).show();
		
		uploadpic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getPicture();
			}
		});
		
		sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 if(notecom.getText().toString()!=""){
					 puttime=System.currentTimeMillis()+"";
					 Toast.makeText(getApplicationContext(), puttime, 1).show();
					 postcom=notecom.getText().toString();
					 requstpost.put("noteclass", postcom);
					 requstpost.put("puttime", puttime);
					 HttpUtil.post("http://"+data.url+":9999/postnotes/",requstpost, new JsonHttpResponseHandler(){

							@Override
							public void onSuccess(int statusCode, Header[] headers,
									JSONArray response) {
								// TODO Auto-generated method stub
								super.onSuccess(statusCode, headers, response);
								Toast.makeText(getApplicationContext(), "yessss", 1).show();
								PostNotes.this.finish();
							}

							@Override
							public void onFailure(int statusCode, Header[] headers,
									String responseBody, Throwable e) {
								// TODO Auto-generated method stub
								super.onFailure(statusCode, headers, responseBody, e);
								
								Toast.makeText(getApplicationContext(), statusCode+"", 1).show();
								Log.v("hhaaaaaa", statusCode+"");
							}
				        });
				 }else{
					 Toast.makeText(getApplicationContext(), "Please enter classname", 1).show();
				 }
				 
				}
			});
		
			}
		
	
	
	private void getPicture() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, INTENT_ACTION_PICTURE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		int a = 0;
		if (requestCode == INTENT_ACTION_PICTURE
				&& resultCode == Activity.RESULT_OK && null != data) {
			Cursor c = getContentResolver().query(data.getData(), null, null,
					null, null);
			c.moveToNext();
			Uri uri=data.getData();
			
			String path = c.getString(c
					.getColumnIndex(MediaStore.MediaColumns.DATA));
			
			c.close();
			System.out.println("onActivityResult == " + path);
			// Toast.makeText(getApplicationContext(), path, 1).show();
			// photobean.setPicturepath(path);
			Log.v("sssssssssssss", path);
			note.setImageURI(uri);
			file=new File(path);
			try {
				requstpost.put("notepic", file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
