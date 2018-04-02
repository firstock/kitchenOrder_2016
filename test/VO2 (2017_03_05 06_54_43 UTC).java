package test;

import java.io.Serializable;

public class VO2 extends VO implements Serializable{
	String test;

	public VO2(String name, int age, String gender, String test) {
		super(name, age, gender);
		this.test = test;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}
