package web.dto;

import java.util.Date;

public class BoardFile {
//	FILENO
//	BOARDNO
//	ORIGIN_NAME
//	STORED_NAME
//	FILESIZE
//	DATE
	
// 자바는 SQL과 달리 대소문자 구분을 하고, 단어사이를 _로 구분하는 것을 싫어하기 때문에
// DTO로 옮기면서 변수명이 달라진다
	
	private int fileno;
	private int boardno;
	private String originName;
	private String storedName;
	private int filesize;
	private Date date;
	
	public BoardFile() {}

	public BoardFile(int fileno, int boardno, String originName, String storedName, int filesize, Date date) {
		super();
		this.fileno = fileno;
		this.boardno = boardno;
		this.originName = originName;
		this.storedName = storedName;
		this.filesize = filesize;
		this.date = date;
	}

	@Override
	public String toString() {
		return "BoardFile [fileno=" + fileno + ", boardno=" + boardno + ", originName=" + originName + ", storedName="
				+ storedName + ", filesize=" + filesize + ", date=" + date + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
