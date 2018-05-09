package kitchenUI;

import java.io.Serializable;
import java.util.*;
public class OrderInfoC implements Serializable{
	
	String foodName; // ���� �̸�	
	int foodCount; // ���� ����
	int tableNo; // ���̺� ��ȣ
	String orderTime; // �ֹ� �ð�
	String completeTime; // �Ϸ� �ð�
	public OrderInfoC(String foodName, int foodCount, int tableNo, String orderTime,
			String completeTime) {
		
		this.foodName = foodName;
		this.foodCount = foodCount;
		this.tableNo = tableNo;
		this.orderTime = orderTime;
		this.completeTime = completeTime;
	}

	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	@Override
	public String toString() {
		return "OrderInfoC [foodName=" + foodName + ", foodCount=" + foodCount + ", tableNo=" + tableNo + ", orderTime="
				+ orderTime + ", completeTime=" + completeTime + "]";
	}

	
}