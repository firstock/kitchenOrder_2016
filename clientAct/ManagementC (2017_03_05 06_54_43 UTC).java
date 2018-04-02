package clientAct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import clientAct.ClientSocketC;
import command.Command;
import clientVO.BasicInfoC;
import clientVO.MemberInfoC;
import clientVO.MessageInfoC;
import clientVO.OrderInfoC;

public class ManagementC {
	ClientSocketC cs = new ClientSocketC();
	
	int tableNo;
	private ArrayList<OrderInfoC> foodOrder;
	public ArrayList<OrderInfoC> koreanFood;
	public ArrayList<OrderInfoC> japaneseFood;
	public ArrayList<OrderInfoC> westernFood;
	public ArrayList<OrderInfoC> drinks;
	public ArrayList<OrderInfoC> orderFood = new ArrayList<>();

	public ManagementC(int tableNo) {
		this.tableNo = tableNo;
	}
	
	//ȸ������
	public String join(MemberInfoC mInfo,int tableNo) {
		String fromServer = null;
		Command com = new Command(tableNo,10);
		com.setObj(mInfo);
		cs.writeCommand(com);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		com = cs.readCommand();
		System.out.println("before: "+com);
		cs.closeSocket();
		if(com.getCommand()==Command.Command_OK){
			fromServer = (String) com.getObj();
			System.out.println("codeNumber:"+fromServer);
		}
		return fromServer;
	}
	
	//�α���
	public boolean logIn(BasicInfoC bInfo,int tableNo) {
		boolean check = false;
		Command com = new Command(tableNo,15);
		com.setObj(bInfo);
		System.out.println("�ڵ�ѹ�:" + com.getObj());
		cs.writeCommand(com);
		System.out.println("111111");
		com = cs.readCommand();
		System.out.println("manage:���ϴݱ���");
		//@@Socket�ݱ� ---->ManagementC�� ���� �޼ҵ帶�� ��� �����ؾ���!!!		
		cs.closeSocket();
		//@@!!!!
		System.out.println("manage:���ϴ����");
		System.out.println("before: "+com);
		if(com.getCommand()==Command.Command_OK){
			check=true;
		}
		foodOrder = new ArrayList<>();
		foodOrder = com.getFoodOrder();
		spreadFood(foodOrder);		
		ReadThread rt = ReadThread.getInstance();
		rt.start();
		return check;
	}
	
	//�޽���������
	public boolean sendMessage(MessageInfoC smsInfo, int fromTable) {
		boolean check = false;
		Command com = new Command(fromTable, 30);
		com.setObj(smsInfo);
		cs.writeCommand(com);
		System.out.println("sendMessage:client�� �޽�����ü�� ���½��ϴ�.");
		//@@�ʿ����
//		com = cs.readCommand();		//�ּ�ó���߽��ϴ�. ���ּ���
		System.out.println("before: "+com);
		cs.closeSocket();
		if(com.getCommand()==Command.Command_OK) {
			check = true;
			System.out.println("�޽��� ������ �Ϸ�Ǿ����ϴ�.");
		}		
		return check;		
	}
	
	//����ȣ���ϱ�
	public boolean callStaff(int tableNo) {		
		System.out.println("manage:callStaff�޼ҵ����"); //@�׽�Ʈ��
		boolean check = false;
		System.out.println("tableNO:"+tableNo);
		Command com = new Command(tableNo, 35);
		System.out.println("callStaff:writeCommand�ض�");
		com.setObj(null);
		System.out.println("setObj:null�Ϸ�");
		cs.writeCommand(com);
		System.out.println("callStaff:client�� ����ȣ��"); //@�׽�Ʈ��
		com = cs.readCommand();
		cs.closeSocket();
		System.out.println("before: " + com);
		if(com.getCommand()==Command.Command_OK) {
			check = true;
			System.out.println("����ȣ���� �Ϸ�Ǿ���."); //@�׽�Ʈ��
		}
		return check;
	}
	
	//���̺��ȣ 
	public int getTableNo() {
		return tableNo;
	}
	
	
	//���� ���� �޼ҵ�=========================================================

	// �ֹ� ���� ����
	public void saveOrder(ArrayList<OrderInfoC> saveFood) {
		for (int i = 0; i < saveFood.size(); i++) {
			orderFood.add(saveFood.get(i));
		}
	}

	// �ֹ� �Ϸ� ���� ����Ʈ
	public ArrayList<OrderInfoC> getOrderFood() {
		return orderFood;
	}
	
	public void sendOrderFood(ArrayList<OrderInfoC> arrOrder) {
		orderFood = arrOrder;
		Command com = new Command(tableNo,20);
		com.setFoodOrder(orderFood);
		cs.writeCommand(com);
		System.out.println("���� �ֹ� ������ ����.");
	}
	
	//���� Ŭ�������� �����̸� ������ �ݾ� ������ �޼ҵ�
	public int getEachFoodPrice(String foodName){
		int foodPrice=0;
		for(int i=0;i<foodOrder.size();i++){
			if(foodName.equalsIgnoreCase(foodOrder.get(i).getFoodName())){
				foodPrice = foodOrder.get(i).getFoodPrice();
			}
		}
		return foodPrice;
	}
	
	//"���� ��ü �ѷ��ֱ�"
	public void spreadFood(ArrayList<OrderInfoC> foodOrder){
		setKoreanFood(foodOrder);
		setJapaneseFood(foodOrder);
		setWesternFood(foodOrder);
		setDrinks(foodOrder);
	}
	public void setKoreanFood(ArrayList<OrderInfoC> foodOrder) {
		koreanFood = new ArrayList<OrderInfoC>();
		for(int i=0;i<6;i++){
			koreanFood.add(foodOrder.get(i));
		}
		/*�׽�Ʈ��
		System.out.println("koreanGet");
		OrderInfoC menu1 = new OrderInfoC("�κα�ġ",1,10000,new Date());
		OrderInfoC menu2 = new OrderInfoC("��ū�ɰԿ�����",1,12000,new Date());
		OrderInfoC menu3 = new OrderInfoC("����������",1,15000,new Date());
		OrderInfoC menu4 = new OrderInfoC("��տ�¡���ع�����",1,10000,new Date());
		OrderInfoC menu5 = new OrderInfoC("�����ߺ�����",1,15000,new Date());
		OrderInfoC menu6 = new OrderInfoC("�ż���",1,25000,new Date());
		koreanFood.add(menu1);
		koreanFood.add(menu2);
		koreanFood.add(menu3);
		koreanFood.add(menu4);
		koreanFood.add(menu5);
		koreanFood.add(menu6);*/
	}
	public ArrayList<OrderInfoC> getKoreanFood() {
		return koreanFood;
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
	//"���� ��ü �Ѹ��� �Ϸ�"
	
}
