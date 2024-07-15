package day0305;

import java.util.Date;

public class TestClobVO {
	private int NUM;
	private String TITLE,CONTENT,WRITER;
	private Date INPUT_DATE;
	
	public TestClobVO(int nUM, String tITLE, String cONTENT, String wRITER, Date iNPUT_DATE) {
		super();
		NUM = nUM;
		TITLE = tITLE;
		CONTENT = cONTENT;
		WRITER = wRITER;
		INPUT_DATE = iNPUT_DATE;
	}
	
	
	public TestClobVO() {
	
	}


	public int getNUM() {
		return NUM;
	}
	
	public void setNUM(int nUM) {
		NUM = nUM;
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
	
	public String getWRITER() {
		return WRITER;
	}
	
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	
	public Date getINPUT_DATE() {
		return INPUT_DATE;
	}
	
	public void setINPUT_DATE(Date iNPUT_DATE) {
		INPUT_DATE = iNPUT_DATE;
	}
	@Override
	public String toString() {
		return "TestClobVO [NUM=" + NUM + ", TITLE=" + TITLE + ", CONTENT=" + CONTENT + ", WRITER=" + WRITER + "]";
	}
	
	

}
