package com.uci.bean;

public class NoteBean {
private String userid;
private String username;
private String avatar;
private String notepic;
private String noteclass;
private String puttime;
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAvatar() {
	return avatar;
}
public void setAvatar(String avatar) {
	this.avatar = avatar;
}
public String getNotepic() {
	return notepic;
}
public void setNotepic(String notepic) {
	this.notepic = notepic;
}
public String getNoteclass() {
	return noteclass;
}
public void setNoteclass(String noteclass) {
	this.noteclass = noteclass;
}
public String getPuttime() {
	return puttime;
}
public void setPuttime(String puttime) {
	this.puttime = puttime;
}
public NoteBean(String userid, String username, String avatar, String notepic,
		String noteclass, String puttime) {
	super();
	this.userid = userid;
	this.username = username;
	this.avatar = avatar;
	this.notepic = notepic;
	this.noteclass = noteclass;
	this.puttime = puttime;
}
public NoteBean() {
	super();
}


}
