package com.example.demo.model;

import java.util.Date;




import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {

	@NotBlank(message = "이름(닉네임)은 필수 입력 값입니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]{1,10}$", message = "이름은 영문과 숫자로 1~10자 이내로 입력해주세요.")
	private String NAME;


	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.(com|net|org|edu|gov|mil|co\\.kr|or\\.kr|go\\.kr|ac\\.kr)$", message = "올바른 이메일 형식이 아닙니다.")
	private String EMAIL;
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[#?!@$%^&*-])(?=\\S+$).{8,15}$", message = "비밀번호는 특수문자,숫자,문자를 포함한 최소 8~15자리만 가능합니다.")
	private String PASSWORD;
	@NotBlank(message = "전화번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^(?:(010-\\d{4})|(01[1|6|7|8|9]-\\d{3,4}))-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다. 하이폰을 포함한 010-xxxx-xxxx로 입력바랍니다.")
	private String TEL;
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	private String ADDR;
	
	private String ADDR_DETAIL;
	
	@NotBlank(message = "우편번호는 필수 입력 값입니다.")
	private String STREETNUM;

	private String PS;

	private Date REG_DATE;

	private Date MODIFY_DATE;

	private Boolean emailduplication;
	
	private int MEMBERID;

	public int getMEMBERID() {
		return MEMBERID;
	}

	public void setMEMBERID(int mEMBERID) {
		MEMBERID = mEMBERID;
	}

	public Boolean getEmailduplication() {
		return emailduplication;
	}

	public void setEmailduplication(Boolean emailduplication) {
		this.emailduplication = emailduplication;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getTEL() {
		return TEL;
	}

	public void setTEL(String tEL) {
		TEL = tEL;
	}

	public String getADDR() {
		return ADDR;
	}

	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}

	public String getADDR_DETAIL() {
		return ADDR_DETAIL;
	}

	public void setADDR_DETAIL(String aDDR_DETAIL) {
		ADDR_DETAIL = aDDR_DETAIL;
	}

	public String getSTREETNUM() {
		return STREETNUM;
	}

	public void setSTREETNUM(String sTREETNUM) {
		STREETNUM = sTREETNUM;
	}

	public String getPS() {
		return PS;
	}

	public void setPS(String pS) {
		PS = pS;
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
	public MemberDto() {}
	
	public MemberDto(String nAME, String eMAIL, String pASSWORD, String tEL, String aDDR, String aDDR_DETAIL,
			String sTREETNUM, String pS, Date rEG_DATE, Date mODIFY_DATE) {
		super();
		
		NAME = nAME;
		EMAIL = eMAIL;
		PASSWORD = pASSWORD;
		TEL = tEL;
		ADDR = aDDR;
		ADDR_DETAIL = aDDR_DETAIL;
		STREETNUM = sTREETNUM;
		PS = pS;
		
		this.REG_DATE = new Date(System.currentTimeMillis());
		MODIFY_DATE = mODIFY_DATE;
	}

	
	
	
	public MemberDto(String nAME, String eMAIL, String pASSWORD, String tEL, String aDDR, String aDDR_DETAIL,
			String sTREETNUM, String pS, Date rEG_DATE, Date mODIFY_DATE, int mEMBERID) {
		super();
		MEMBERID = mEMBERID;
		NAME = nAME;
		EMAIL = eMAIL;
		PASSWORD = pASSWORD;
		TEL = tEL;
		ADDR = aDDR;
		ADDR_DETAIL = aDDR_DETAIL;
		STREETNUM = sTREETNUM;
		PS = pS;
		
		this.REG_DATE = new Date(System.currentTimeMillis());
		MODIFY_DATE = mODIFY_DATE;
	}

	@Override
	public String toString() {
		return "MemberDto [NAME=" + NAME + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", TEL=" + TEL + ", ADDR="
				+ ADDR + ", ADDR_DETAIL=" + ADDR_DETAIL + ", STREETNUM=" + STREETNUM + ", PS=" + PS + ", REG_DATE="
				+ REG_DATE + ", MODIFY_DATE=" + MODIFY_DATE + ", emailduplication=" + emailduplication + ", MEMBERID="
				+ MEMBERID + "]";
	}

}
