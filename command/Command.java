package command;

import java.io.Serializable;
import java.util.ArrayList;

import clientVO.OrderInfoC;

public class Command implements Serializable {
	int command;
	int tableNo;
	ArrayList<OrderInfoC> foodOrder;
	String serialNumber;
	Object obj;	
	static final long serialVersionUID = 10000;
	
	public static final int Join_Check = 10;
	public static final int LogIn_Check = 15;
	public static final int Food_Check = 20;
	public static final int Message_Check = 30;
	public static final int Call_Check = 35;
	
	public static final int Command_OK = 40;
	public static final int Command_Fail = 50;
	
	public static final int Receive_Staff = 60;
	public static final int Receive_Sms = 70;	
	public static final int Receive_Food = 80;
	
	public Command(){}
	public Command(int tableNo, int command){
		this.tableNo=tableNo;
		this.command=command;
	}
	
	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public ArrayList<OrderInfoC> getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(ArrayList<OrderInfoC> foodOrder) {
		this.foodOrder = foodOrder;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public void setSerial(String serial){
		this.serialNumber = serial;
	}
	
	@Override
	public String toString() {
		return "Command [command=" + command + ", obj=" + obj + "]";
	}

}
