package clientVO;

import java.io.Serializable;

public class BasicInfoC implements Serializable{
	String codeNumber;
	MCardInfoC mci;
	public BasicInfoC(String codeNumber, MCardInfoC mci) {
		this.codeNumber = codeNumber;
		this.mci = mci;
	}
	public  String getCodeNumber() {
		return codeNumber;
	}
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	public MCardInfoC getMci() {
		return mci;
	}
	public void setMci(MCardInfoC mci) {
		this.mci = mci;
	}
	public String toString() {
		return "BasicInfo=[codeNumber:" + codeNumber + ", mci=" + mci;
	}
}
