package clientVO;
import java.io.Serializable;
import java.util.*;

public class MCardInfoC implements Serializable{
	int price;
	Date[] time;
	ArrayList<MessageInfoC> mList;
	ArrayList<OrderInfoC> oList;
	MCardInfoC (int price, Date[] time, ArrayList<MessageInfoC> mList,
			ArrayList<OrderInfoC> oList) {
		this.price = price;
		this.time = time;
		this.mList = mList;
		this.oList = oList;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date[] getTime() {
		return time;
	}
	public void setTime(Date[] time) {
		this.time = time;
	}
	public ArrayList<MessageInfoC> getmList() {
		return mList;
	}
	public void setmList(ArrayList<MessageInfoC> mList) {
		this.mList = mList;
	}
	public ArrayList<OrderInfoC> getoList() {
		return oList;
	}
	public void setoList(ArrayList<OrderInfoC> oList) {
		this.oList = oList;
	}
	public String toString() {
		return "MCardInfoC=[price:" + price + ", Date=" + time + 
				", mList=" + mList + ", oList=" + oList;
	}
 	
}
