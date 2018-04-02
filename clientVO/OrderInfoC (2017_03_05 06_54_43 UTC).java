package clientVO;

import java.io.Serializable;
import java.util.*;
public class OrderInfoC implements Serializable{
	String foodName;
	int foodCount;
	int foodPrice;
	Date orderTime;
	public OrderInfoC (String foodName, int foodCount, int foodPrice, Date orderTime) {
		this.foodName = foodName;
		this.foodCount = foodCount;
		this.foodPrice = foodPrice;
		this.orderTime = orderTime;
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
	public int getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}	
	@Override
	public String toString() {
		return "OrderInfoC [foodName=" + foodName + ", foodCount=" + foodCount + ", foodPrice=" + foodPrice
				+ ", orderTime=" + orderTime + "]";
	}
	
	
}
