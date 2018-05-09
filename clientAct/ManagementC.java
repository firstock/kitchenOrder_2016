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
	
	//회원가입
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
	
	//로그인
	public boolean logIn(BasicInfoC bInfo,int tableNo) {
		boolean check = false;
		Command com = new Command(tableNo,15);
		com.setObj(bInfo);
		System.out.println("코드넘버:" + com.getObj());
		cs.writeCommand(com);
		System.out.println("111111");
		com = cs.readCommand();
		System.out.println("manage:소켓닫기전");
		//@@Socket닫기 ---->ManagementC에 각각 메소드마다 모두 실행해야함!!!		
		cs.closeSocket();
		//@@!!!!
		System.out.println("manage:소켓닷기후");
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
	
	//메시지보내기
	public boolean sendMessage(MessageInfoC smsInfo, int fromTable) {
		boolean check = false;
		Command com = new Command(fromTable, 30);
		com.setObj(smsInfo);
		cs.writeCommand(com);
		System.out.println("sendMessage:client가 메시지객체를 보냈습니다.");
		//@@필요없음
//		com = cs.readCommand();		//주석처리했습니다. 봐주세요
		System.out.println("before: "+com);
		cs.closeSocket();
		if(com.getCommand()==Command.Command_OK) {
			check = true;
			System.out.println("메시지 전송이 완료되었습니다.");
		}		
		return check;		
	}
	
	//직원호출하기
	public boolean callStaff(int tableNo) {		
		System.out.println("manage:callStaff메소드실행"); //@테스트용
		boolean check = false;
		System.out.println("tableNO:"+tableNo);
		Command com = new Command(tableNo, 35);
		System.out.println("callStaff:writeCommand해라");
		com.setObj(null);
		System.out.println("setObj:null완료");
		cs.writeCommand(com);
		System.out.println("callStaff:client가 직원호출"); //@테스트용
		com = cs.readCommand();
		cs.closeSocket();
		System.out.println("before: " + com);
		if(com.getCommand()==Command.Command_OK) {
			check = true;
			System.out.println("직원호출이 완료되었다."); //@테스트용
		}
		return check;
	}
	
	//테이블번호 
	public int getTableNo() {
		return tableNo;
	}
	
	
	//음식 관련 메소드=========================================================

	// 주문 음식 저장
	public void saveOrder(ArrayList<OrderInfoC> saveFood) {
		for (int i = 0; i < saveFood.size(); i++) {
			orderFood.add(saveFood.get(i));
		}
	}

	// 주문 완료 음식 리스트
	public ArrayList<OrderInfoC> getOrderFood() {
		return orderFood;
	}
	
	public void sendOrderFood(ArrayList<OrderInfoC> arrOrder) {
		orderFood = arrOrder;
		Command com = new Command(tableNo,20);
		com.setFoodOrder(orderFood);
		cs.writeCommand(com);
		System.out.println("음식 주문 서버에 보냄.");
	}
	
	//음식 클래스에서 음식이름 받으면 금액 나오는 메소드
	public int getEachFoodPrice(String foodName){
		int foodPrice=0;
		for(int i=0;i<foodOrder.size();i++){
			if(foodName.equalsIgnoreCase(foodOrder.get(i).getFoodName())){
				foodPrice = foodOrder.get(i).getFoodPrice();
			}
		}
		return foodPrice;
	}
	
	//"음식 전체 뿌려주기"
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
		/*테스트용
		System.out.println("koreanGet");
		OrderInfoC menu1 = new OrderInfoC("두부김치",1,10000,new Date());
		OrderInfoC menu2 = new OrderInfoC("얼큰꽃게오뎅탕",1,12000,new Date());
		OrderInfoC menu3 = new OrderInfoC("묵은지보쌈",1,15000,new Date());
		OrderInfoC menu4 = new OrderInfoC("대왕오징어해물파전",1,10000,new Date());
		OrderInfoC menu5 = new OrderInfoC("국물닭볶음탕",1,15000,new Date());
		OrderInfoC menu6 = new OrderInfoC("신선로",1,25000,new Date());
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
	//"음식 전체 뿌리기 완료"
	
}
