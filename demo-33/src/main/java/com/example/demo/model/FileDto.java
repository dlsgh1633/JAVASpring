package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileDto {
	
	
	private String ORG_FILE_NAME;
	
	
	private String FILE_NAME;
	
	private Date REG_DATE;
	
	private Date MODIFY_DATE;
	
	private int BOARDID;
	
	private Long FILE_SIZE;
	
	private List<String> removeFileIds = new ArrayList<>(); 
	
	public List<String> getRemoveFileIds() {
		return removeFileIds;
	}
	public void setRemoveFileIds(List<String> removeFileIds) {
		this.removeFileIds = removeFileIds;
	}
	public Long getFILE_SIZE() {
		return FILE_SIZE;
	}
	public void setFILE_SIZE(Long fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}
	
	public String getORG_FILE_NAME() {
		return ORG_FILE_NAME;
	}
	public void setORG_FILE_NAME(String oRG_FILE_NAME) {
		ORG_FILE_NAME = oRG_FILE_NAME;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
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
	public int getBOARDID() {
		return BOARDID;
	}
	public FileDto() {}
	
	
	public FileDto(String oRG_FILE_NAME, String fILE_NAME, Long fILE_SIZE) {
		super();
		ORG_FILE_NAME = oRG_FILE_NAME;
		FILE_NAME = fILE_NAME;
		FILE_SIZE = fILE_SIZE;
	}

	public void setBOARDID(int bOARDID) {
		BOARDID = bOARDID;
	}
	//  toString을 쓰는 이유 -> 객체를 문자열로 변환해서 로깅 및 디버깅을 쉽게 하기 위해서.
	@Override
	public String toString() {
		return "FileDto [ORG_FILE_NAME=" + ORG_FILE_NAME + ", FILE_NAME=" + FILE_NAME + ", REG_DATE=" + REG_DATE
				+ ", MODIFY_DATE=" + MODIFY_DATE + ", BOARDID=" + BOARDID + "]";
	}
	
	
	
}
