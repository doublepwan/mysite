package com.javaex.vo;

public class BoardVo {
	//조회수 등록일 추가해야됨
	//users 테이블이랑 조인할것도 고려
	private int no;
	private String title;
	private String content;
	
	public BoardVo() {
	}

	public BoardVo(int no, String title, String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
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
	
}
