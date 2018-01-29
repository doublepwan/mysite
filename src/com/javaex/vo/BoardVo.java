package com.javaex.vo;

public class BoardVo {

	private String no;
	private String title;
	private String content;
	private String date;
	private int hit;
	private String name;
	private String userNo;
	
	public BoardVo() {
	}


	public BoardVo(String no, String title, String content, String date, int hit, String name, String userNo) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.date = date;
		this.hit = hit;
		this.name = name;
		this.userNo = userNo;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserNo() {
		return userNo;
	}


	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", date=" + date + ", hit=" + hit
				+ ", name=" + name + ", userNo=" + userNo + "]";
	}


	
	
}
