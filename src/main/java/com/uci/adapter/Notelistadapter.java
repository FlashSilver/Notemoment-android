package com.uci.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uci.bean.NoteBean;
import com.uci.notshare.R;
import com.uci.tool.RoundImageView;
import com.uci.tool.RoundedImageView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Notelistadapter extends BaseAdapter {
	private Context context;
	private List<NoteBean> notes;	
	public Notelistadapter(Context context, List<NoteBean> notes) {
		super();
		this.context = context;
		this.notes = notes;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notes.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return notes.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		RelativeLayout relaytivelayout;
		if(convertView==null){
			relaytivelayout=(RelativeLayout) View.inflate(context,R.layout.item, null);			
		}
		else{
			relaytivelayout=(RelativeLayout) convertView;
		}
		NoteBean notebean=(NoteBean) getItem(arg0);
		TextView username=(TextView) relaytivelayout.findViewById(R.id.username);
		TextView noteclass=(TextView) relaytivelayout.findViewById(R.id.classname);
		TextView time=(TextView) relaytivelayout.findViewById(R.id.time);
		
		RoundImageView avatar=(RoundImageView) relaytivelayout.findViewById(R.id.avatar);
		ImageView note=(ImageView) relaytivelayout.findViewById(R.id.notepic);
		username.setText(notebean.getUsername());
		noteclass.setText(notebean.getNoteclass());
		time.setText(notebean.getPuttime());
		ImageLoader.getInstance().displayImage(notebean.getAvatar(), avatar);
		ImageLoader.getInstance().displayImage(notebean.getNotepic(), note);
		return relaytivelayout;
	}

	public List<NoteBean> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteBean> notes) {
		this.notes = notes;
	}

}
