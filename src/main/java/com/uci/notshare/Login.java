package com.uci.notshare;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import org.apache.http.Header;
import org.json.JSONArray;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uci.tool.HttpUtil;
import com.uci.tool.PreferenceUtils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

public class Login extends Activity {
	private EditText name;
	private Button upload;
	private Button signin;
	private String username;
	private String userid;
	private ImageView avatar;
	File file;
	RequestParams requstlogin=new RequestParams();
	private LoginButton facebook;
	private static final int INTENT_ACTION_PICTURE = 0;
	CallbackManager callbackManager;
	private ProfileTracker mProfileTracker;
	private AccessTokenTracker mTokenTracker;

	@SuppressLint("WrongViewCast")

//	private FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
//		@Override
//		public void onSuccess(LoginResult loginResult) {
//			AccessToken accessToken = loginResult.getAccessToken();
//			Profile profile = Profile.getCurrentProfile();
//
//		}
//		@Override
//		public void onCancel() {
//			Log.d("VIVZ", "onCancel");
//		}
//
//		@Override
//		public void onError(FacebookException e) {
//			Log.d("VIVZ", "onError " + e);
//		}
//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		FacebookSdk.sdkInitialize(this.getApplicationContext());
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		callbackManager = CallbackManager.Factory.create();
		upload=(Button) findViewById(R.id.upload);
		name=(EditText) findViewById(R.id.username);
		avatar=(ImageView) findViewById(R.id.avatar);
		signin=(Button) findViewById(R.id.signin);
		facebook= (LoginButton) findViewById(R.id.facebook);
//		AccessToken.getCurrentAccessToken().getPermissions();
//		facebook.setReadPermissions(Arrays.asList("user_status"));
//		facebook.registerCallback(callbackManager, mFacebookCallback);
//
//		mTokenTracker.startTracking();
//		mProfileTracker.startTracking();
//		facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//			@Override
//			public void onSuccess(LoginResult loginResult) {
//				// App code
//				Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_LONG).show();
//				AccessToken accessToken=loginResult.getAccessToken();
//				Profile profile=Profile.getCurrentProfile();
//				if (profile!=null){
//					Toast.makeText(getApplicationContext(),profile.toString(),Toast.LENGTH_LONG).show();
//					Log.d("aaaaaa",profile.getFirstName());
//				}
//
//
//			}
//
//			public void onCancel() {
//				// App code
//				Toast.makeText(getApplicationContext(),"cancel",Toast.LENGTH_LONG).show();
//
//			}
//
//			@Override
//			public void onError(FacebookException exception) {
//				// App code
//				Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
//			}
//		});

		upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getPicture();
			}
		});
		signin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				userid = System.currentTimeMillis() + "";
				PreferenceUtils.setPrefString(getApplicationContext(), "userid", userid);
				username = name.getText().toString();
				PreferenceUtils.setPrefString(getApplicationContext(), "username", username);
				requstlogin.put("userid", userid);
				requstlogin.put("username", username);

				if (name.getText().toString() != "") {
					HttpUtil.post("http://"+data.url+":9999/send/", requstlogin, new JsonHttpResponseHandler() {

						@Override
						public void onSuccess(int statusCode, Header[] headers,
											  JSONArray response) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, headers, response);
							Toast.makeText(getApplicationContext(), "yessss", 1).show();
							Login.this.finish();
						}

						@Override
						public void onFailure(int statusCode, Header[] headers,
											  String responseBody, Throwable e) {
							// TODO Auto-generated method stub
							super.onFailure(statusCode, headers, responseBody, e);

							Toast.makeText(getApplicationContext(), statusCode + "", 1).show();
							Log.v("hhaaaaaa", statusCode + "");
						}
					});
				} else {
					Toast.makeText(getApplicationContext(), "Please enter a name", 1).show();
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
		super.onActivityResult(requestCode, resultCode, data);
//		callbackManager.onActivityResult(requestCode, resultCode, data);

//		Toast.makeText(getApplicationContext(),data.toString(),Toast.LENGTH_LONG).show();

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
			avatar.setImageURI(uri);
			file=new File(path);
			try {
				requstlogin.put("avatar", file);
				PreferenceUtils.setPrefString(getApplicationContext(), "avatar", path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
