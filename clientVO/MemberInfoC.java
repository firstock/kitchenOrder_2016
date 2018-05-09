package clientVO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MemberInfoC extends BasicInfoC implements Serializable{
	String name;
	int age;
	String gender;
	String phone;
	String birth;
	public MemberInfoC(String codeNumber, MCardInfoC mci, String name, 
			int age, String gender, String phone, String birth) {
		super(codeNumber, mci);
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String toString() {
		return "codeNumber:" + codeNumber + "MCardInfoC : " + mci + 
				"name:" + name + ", age:" + age + ", gender:" + gender 
				+ ", phone:" + phone + ", birth:" + birth;
	}
}
