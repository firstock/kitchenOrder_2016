package clientAct;

import java.util.ArrayList;

import clientVO.OrderInfoC;

public class FoodManagementC {
	private ArrayList<OrderInfoC> foodOrder;
	public ArrayList<OrderInfoC> koreanFood;
	public ArrayList<OrderInfoC> japaneseFood;
	public ArrayList<OrderInfoC> westernFood;
	public ArrayList<OrderInfoC> drinks;

	public FoodManagementC(ArrayList<OrderInfoC> foodOrder) {
		this.foodOrder = foodOrder;
	}

	public ArrayList<OrderInfoC> getKoreanFood() {
		return koreanFood;
	}

	public void setKoreanFood(ArrayList<OrderInfoC> foodOrder) {
		koreanFood = new ArrayList<OrderInfoC>();
		for(int i=0;i<6;i++){
			koreanFood.add(foodOrder.get(i));
		}
	}

	public ArrayList<OrderInfoC> getJapaneseFood() {
		return japaneseFood;
	}

	public void setJapaneseFood(ArrayList<OrderInfoC> foodOrder) {
		japaneseFood = new ArrayList<OrderInfoC>();
		for(int i=6;i<11;i++){
			japaneseFood.add(foodOrder.get(i));
		}
	}

	public ArrayList<OrderInfoC> getWesternFood() {
		return westernFood;
	}

	public void setWesternFood(ArrayList<OrderInfoC> foodOrder) {
		westernFood = new ArrayList<OrderInfoC>();
		for(int i=11;i<15;i++){
			westernFood.add(foodOrder.get(i));
		}
	}

	public ArrayList<OrderInfoC> getDrinks() {
		return drinks;
	}

	public void setDrinks(ArrayList<OrderInfoC> foodOrder) {
		drinks = new ArrayList<OrderInfoC>();
		for(int i=15;i<foodOrder.size();i++){
			drinks.add(foodOrder.get(i));
		}
	}

	public int getEachFoodPrice(String foodName){
		int foodPrice=0;
		for(int i=0;i<foodOrder.size();i++){
			if(foodName.equalsIgnoreCase(foodOrder.get(i).getFoodName())){
				foodPrice = foodOrder.get(i).getFoodPrice();
			}
		}
		return foodPrice;
	}
	
}
