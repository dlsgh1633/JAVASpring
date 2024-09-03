package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class BoardDto {
	
	private int BOARDID;
	
	private int MEMBERID;
	
	@NotBlank(message="게시판 제목은 필수 입력 값입니다.")
	private String TITLE;

	
	@NotBlank(message="게시판 내용은 필수 입력 값입니다.")
	private String CONTENT;
	

	private Date REG_DATE;
	

	private Date MODIFY_DATE;

	private int BOARD_DELETE;

	private String MEMBER_NAME;
	
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	private List<MultipartFile> files = new ArrayList<>();

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}

	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}

	public int getBOARDID() {
		return BOARDID;
	}

	public void setBOARDID(int bOARDID) {
		BOARDID = bOARDID;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public int getMEMBERID() {
		return MEMBERID;
	}

	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}

	public Date getREG_DATE() {
		
		return REG_DATE;
	}

	public void setREG_DATE(Date rEG_DATE) {
		REG_DATE = rEG_DATE; 
	}

	public Date getMODIFY_DATE() {
		return MODIFY_DATE;
	}

	public void setMODIFY_DATE(Date mODIFY_DATE) {
		MODIFY_DATE = mODIFY_DATE;
	}

	public int getBOARD_DELETE() {
		return BOARD_DELETE;
	}

	public void setBOARD_DELETE(int bOARD_DELETE) {
		BOARD_DELETE = bOARD_DELETE;
	}
	
	public BoardDto() {
       
    }
	
	
	public BoardDto(int bOARDID, int mEMBERID, @NotBlank String tITLE, @NotBlank String cONTENT, Date rEG_DATE,
			Date mODIFY_DATE, int bOARD_DELETE, String mEMBER_NAME) {
		super();
		BOARDID = bOARDID;
		MEMBERID = mEMBERID;
		TITLE = tITLE;
		CONTENT = cONTENT;
		REG_DATE = rEG_DATE;
		MODIFY_DATE = mODIFY_DATE;
		BOARD_DELETE = bOARD_DELETE;
		MEMBER_NAME = mEMBER_NAME;
	}


	@Override
	public String toString() {
		return "BoardDto [BOARDID=" + BOARDID + ", TITLE=" + TITLE + ", CONTENT=" + CONTENT + ", MEMBERID=" + MEMBERID
				+ ", REG_DATE=" + REG_DATE + ", MODIFY_DATE=" + MODIFY_DATE + ", BOARD_DELETE=" + BOARD_DELETE + "]";
	}
	
}
