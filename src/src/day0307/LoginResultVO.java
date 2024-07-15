package day0307;

import java.sql.Date;

public class LoginResultVO {
	private String name;
	private Date input_date;

	public LoginResultVO() {
	}

	public LoginResultVO(String name, Date input_date) {
		super();
		this.name = name;
		this.input_date = input_date;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the input_date
	 */
	public Date getInput_date() {
		return input_date;
	}

	/**
	 * @param input_date the input_date to set
	 */
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}

	@Override
	public String toString() {
		return "LoginResultVO [name=" + name + ", input_date=" + input_date + "]";
	}
	
	
}
