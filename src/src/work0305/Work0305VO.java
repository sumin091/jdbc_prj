package work0305;

import java.sql.Date;

public class Work0305VO {
	public Number NUM;
	public String NAME;	
	public String IMAGE;
	public Number AGE;
	public Date INPUT_DATE;

	public Work0305VO() {
		
	}

	public Work0305VO(Number nUM, String nAME, String iMAGE, Number aGE, Date iNPUT_DATE) {
		super();
		NUM = nUM;
		NAME = nAME;
		IMAGE = iMAGE;
		AGE = aGE;
		INPUT_DATE = iNPUT_DATE;
	}

	/**
	 * @return the nUM
	 */
	public Number getNUM() {
		return NUM;
	}

	/**
	 * @param nUM the nUM to set
	 */
	public void setNUM(Number nUM) {
		NUM = nUM;
	}

	/**
	 * @return the nAME
	 */
	public String getNAME() {
		return NAME;
	}

	/**
	 * @param nAME the nAME to set
	 */
	public void setNAME(String nAME) {
		NAME = nAME;
	}

	/**
	 * @return the iMAGE
	 */
	public String getIMAGE() {
		return IMAGE;
	}

	/**
	 * @param iMAGE the iMAGE to set
	 */
	public void setIMAGE(String iMAGE) {
		IMAGE = iMAGE;
	}

	/**
	 * @return the aGE
	 */
	public Number getAGE() {
		return AGE;
	}

	/**
	 * @param aGE the aGE to set
	 */
	public void setAGE(Number aGE) {
		AGE = aGE;
	}

	/**
	 * @return the iNPUT_DATE
	 */
	public Date getINPUT_DATE() {
		return INPUT_DATE;
	}

	/**
	 * @param iNPUT_DATE the iNPUT_DATE to set
	 */
	public void setINPUT_DATE(Date iNPUT_DATE) {
		INPUT_DATE = iNPUT_DATE;
	}

	@Override
	public String toString() {
		return "Work0305VO [NUM=" + NUM + ", NAME=" + NAME + ", IMAGE=" + IMAGE + ", AGE=" + AGE + ", INPUT_DATE="
				+ INPUT_DATE + "]";
	}
	
	
}
