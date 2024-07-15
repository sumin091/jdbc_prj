package test_jdbc;

public class TestVO {

	private String sa_name, sa_category;
int age, standing;
	
	private TestVO() {
		
	}

	public TestVO(String sa_name, String sa_category, int age, int standing) {
		super();
		this.sa_name = sa_name;
		this.sa_category = sa_category;
		this.age = age;
		this.standing = standing;
	}

	/**
	 * @return the sa_name
	 */
	public String getSa_name() {
		return sa_name;
	}

	/**
	 * @param sa_name the sa_name to set
	 */
	public void setSa_name(String sa_name) {
		this.sa_name = sa_name;
	}

	/**
	 * @return the sa_category
	 */
	public String getSa_category() {
		return sa_category;
	}

	/**
	 * @param sa_category the sa_category to set
	 */
	public void setSa_category(String sa_category) {
		this.sa_category = sa_category;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the standing
	 */
	public int getStanding() {
		return standing;
	}

	/**
	 * @param standing the standing to set
	 */
	public void setStanding(int standing) {
		this.standing = standing;
	}
	
	
}
