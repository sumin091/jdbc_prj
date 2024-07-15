package kr.co.sist.statement.vo;

import java.sql.Date;

public class EmployeeVO {
	private int EMPNO;
	private String ENAME,JOB;
	private double SAL;
	private Date HIREDATE;
	private String HIREDATE2;

	
	public EmployeeVO(int eMPNO, String eNAME, String jOB, double sAL, Date hIREDATE) {
		super();
		EMPNO = eMPNO;
		ENAME = eNAME;
		JOB = jOB;
		SAL = sAL;
		HIREDATE = hIREDATE;
	}
	
	
	public EmployeeVO(int eMPNO, String eNAME, String jOB, double sAL, Date hIREDATE,String hIREDATE2) {
		super();
		EMPNO = eMPNO;
		ENAME = eNAME;
		JOB = jOB;
		SAL = sAL;
		HIREDATE=  hIREDATE;
		HIREDATE2 = hIREDATE2;
	}


	public EmployeeVO() {
		super();
	}
	public int getEMPNO() {
		return EMPNO;
	}
	public void setEMPNO(int eMPNO) {
		EMPNO = eMPNO;
	}
	
	public String getENAME() {
		return ENAME;
	}
	
	public void setENAME(String eNAME) {
		ENAME = eNAME;
	}
	
	public String getJOB() {
		return JOB;
	}
	
	public void setJOB(String jOB) {
		JOB = jOB;
	}
	
	public double getSAL() {
		return SAL;
	}
	
	public void setSAL(double sAL) {
		SAL = sAL;
	}
	
	public Date getHIREDATE() {
		return HIREDATE;
	}
	
	public void setHIREDATE(Date hIREDATE) {
		HIREDATE = hIREDATE;
	}


	/**
	 * @return the hIREDATE2
	 */
	public String getHIREDATE2() {
		return HIREDATE2;
	}


	/**
	 * @param hIREDATE2 the hIREDATE2 to set
	 */
	public void setHIREDATE2(String hIREDATE2) {
		HIREDATE2 = hIREDATE2;
	}


	@Override
	public String toString() {
		return "EmployeeVO [EMPNO=" + EMPNO + ", ENAME=" + ENAME + ", JOB=" + JOB + ", SAL=" + SAL + ", HIREDATE="
				+ HIREDATE + ", HIREDATE2=" + HIREDATE2 + "]";
	}
	
	
	
	
}
