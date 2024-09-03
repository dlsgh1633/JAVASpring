package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;


public class CommentDto {
	private int COMMENTID;
	private int BOARDID;
	private int MEMBERID;
	@NotBlank(message = "댓글의 내용은 필수 입력 값입니다.")
	private String CONTENT;
	private Date REG_DATE;
	private Date MODIFY_DATE;
	private int PARENTSID;
	private int COMMENT_DELETE;
	private int COUNT;
	private String NAME;
	private int LEVEL;
	private boolean isWriter;
	private boolean UserNullcheck;

	public boolean isUserNullcheck() {
		return UserNullcheck;
	}
	public void setUserNullcheck(boolean userNullcheck) {
		UserNullcheck = userNullcheck;
	}
	public boolean isWriter() {
		return isWriter;
	}
	public void setWriter(boolean isWriter) {
		this.isWriter = isWriter;
	}
	public int getLEVEL() {
		return LEVEL;
	}
	public void setLEVEL(int lEVEL) {
		LEVEL = lEVEL;
	}

	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public int getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(int cOUNT) {
		COUNT = cOUNT;
	}
	public int getCOMMENTID() {
		return COMMENTID;
	}
	public void setCOMMENTID(int cOMMENTID) {
		COMMENTID = cOMMENTID;
	}
	public int getBOARDID() {
		return BOARDID;
	}
	public void setBOARDID(int bOARDID) {
		BOARDID = bOARDID;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
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
	public int getPARENTSID() {
		return PARENTSID;
	}
	public void setPARENTSID(int pARENTSID) {
		PARENTSID = pARENTSID;
	}
	public int getCOMMENT_DELETE() {
		return COMMENT_DELETE;
	}
	public void setCOMMENT_DELETE(int cOMMENT_DELETE) {
		COMMENT_DELETE = cOMMENT_DELETE;
	}
	public int getMEMBERID() {
		return MEMBERID;
	}
	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}
	@Override
	public String toString() {
		return "CommentDto [COMMENTID=" + COMMENTID + ", BOARDID=" + BOARDID + ", MEMBERID=" + MEMBERID + ", CONTENT="
				+ CONTENT + ", REG_DATE=" + REG_DATE + ", MODIFY_DATE=" + MODIFY_DATE + ", PARENTSID=" + PARENTSID
				+ ", COMMENT_DELETE=" + COMMENT_DELETE + ", COUNT=" + COUNT + "]";
	}
	
}
