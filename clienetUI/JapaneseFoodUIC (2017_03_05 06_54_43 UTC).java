package clienetUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientAct.FoodManagementC;
import clientAct.ManagementC;
import clientVO.OrderInfoC;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

//"@@"표시는 합치고 수정해야함.
public class JapaneseFoodUIC implements ActionListener {

	private JFrame frame;
	//상단 가격표시
	private JPanel northPanel;
	private JLabel theCurrentAmountlbl;
	public String theCurrentAmount;
	private final String won = "원";
	private int theCurrentAmontInt = 0;
	//중간 메뉴표시
	private JPanel centerPanel;
	private JLabel foodNamelbl[] = new JLabel[6];
	private JTextField foodCountTF[] = new JTextField[6];
	private int foodCount[] = {0,0,0,0,0,0};
	private JButton foodPlusBT[] = new JButton[6];
	private JButton foodMinusBT[] = new JButton[6];
	private JLabel foodPricelbl[] = new JLabel[6];
	ArrayList<OrderInfoC> arrMenu = new ArrayList<>();
	OrderInfoC menuAll;
	//하단 표시
	private JPanel southPanel;
	private JButton finishOrderBT;
	private JButton beforeBT;
	ArrayList<OrderInfoC> arrOrderFood = new ArrayList<>();
	ArrayList<OrderInfoC> temperArrOrderFood = new ArrayList<>();	
	OrderInfoC orderFood;
	ManagementC manage;

	public JapaneseFoodUIC(ManagementC manage) {
		this.manage=manage;
		initialize();
	}
	
	private void initialize() {
		//@@arrMenu = 서버에서 받은 리스트 더해야함. 삭제용.
		arrMenu = manage.getJapaneseFood();
		frame = new JFrame("JapaneseFood");
		frame.setVisible(true);
		frame.setBounds(100, 100, 650, 600);//100~700
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//상단 현재 가격.
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		theCurrentAmount = "0";
		theCurrentAmountlbl = new JLabel(theCurrentAmount+" "+won);
		northPanel.add(theCurrentAmountlbl);
		
		//중앙 메뉴 보임.
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6, 5, 0, 0));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		for(int i=0;i<arrMenu.size();i++){
			foodNamelbl[i] = new JLabel(arrMenu.get(i).getFoodName(),JLabel.CENTER);
			foodCountTF[i] = new JTextField(String.valueOf(foodCount[i]));
			foodCountTF[i].setHorizontalAlignment(JTextField.CENTER);			
			foodCountTF[i].setEnabled(false);
			foodPlusBT[i] = new JButton("+");
			foodMinusBT[i] = new JButton("-");
			//foodPricelbl[i] = new JLabel(String.valueOf(arrMenu.get(i).getPrice()),JLabel.CENTER);
			int price = arrMenu.get(i).getFoodPrice();
			if(1000<=price){
				foodPricelbl[i] = new JLabel((String.valueOf(price/1000)+",000"+won),JLabel.CENTER);
			}else{
				foodPricelbl[i] = new JLabel(String.valueOf(price)+won,JLabel.CENTER);
			}
			centerPanel.add(foodNamelbl[i]);
			centerPanel.add(foodCountTF[i]);
			centerPanel.add(foodPlusBT[i]);
			centerPanel.add(foodMinusBT[i]);
			centerPanel.add(foodPricelbl[i]);	
			foodPlusBT[i].addActionListener(this);
			foodMinusBT[i].addActionListener(this);
		}
		
		//하단 완료 전달
		southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		finishOrderBT = new JButton("주문 완료");
		beforeBT = new JButton("<<이전 메뉴");
		southPanel.add(finishOrderBT);
		southPanel.add(beforeBT);
		finishOrderBT.addActionListener(this);
		beforeBT.addActionListener(this);	
	}
	
	public void actionPerformed(ActionEvent e){
		Object checkBT = e.getSource();
		for(int i=0;i<6;i++){
			if(checkBT==foodPlusBT[i]){
				foodCount[i] += 1;
				foodCountTF[i].setText(String.valueOf(foodCount[i]));
				int price = arrMenu.get(i).getFoodPrice();
				theCurrentAmontInt += price;
				if(1000<=theCurrentAmontInt){
					theCurrentAmount = String.valueOf(theCurrentAmontInt/1000+",000 ");
				}else{
					theCurrentAmount = String.valueOf(theCurrentAmontInt);
				}
				theCurrentAmountlbl.setText(theCurrentAmount+" "+won);
				foodPlusBascket(foodNamelbl[i]);
			}else if(checkBT==foodMinusBT[i]){
				if(foodCount[i]!=0){
					foodCount[i] -= 1;
					foodCountTF[i].setText(String.valueOf(foodCount[i]));
					int price = arrMenu.get(i).getFoodPrice();
					theCurrentAmontInt -= price;
					if(1000<=theCurrentAmontInt){
						theCurrentAmount = String.valueOf(theCurrentAmontInt/1000+",000 ");
					}else{
						theCurrentAmount = String.valueOf(theCurrentAmontInt);
					}
					theCurrentAmountlbl.setText(theCurrentAmount+" "+won);
					foodMinusBascket(foodNamelbl[i]);
				}else{
					JOptionPane.showMessageDialog(null, "수량이 0입니다.");
				}
			}
		}
		if(checkBT==finishOrderBT){
			//@@텝퍼어레이(1회 주문용) 보내기 sm.orderFood(temperArrOrderFood);
			for(int i=0;i<temperArrOrderFood.size();i++){
				arrOrderFood.add(temperArrOrderFood.get(i));
			}
			new OrderDoneUIC(temperArrOrderFood,manage);
			temperArrOrderFood.removeAll(temperArrOrderFood);
			frame.setVisible(false);
		}if(checkBT==beforeBT){
			new OrderMenuUIC(manage);
			frame.setVisible(false);
		}
		
	}

	private void foodPlusBascket(JLabel foodNamelbl) {
		String foodName = foodNamelbl.getText();
		int foodPrice = manage.getEachFoodPrice(foodName);
		orderFood = new OrderInfoC(foodName,1,foodPrice,new Date());
		temperArrOrderFood.add(orderFood);
	}
	
	private void foodMinusBascket(JLabel foodName) {
		for(int i=0;i<temperArrOrderFood.size();i++){
			if(foodName.getText().equals(temperArrOrderFood.get(i).getFoodName())){
				temperArrOrderFood.remove(i);
				break;
			}
		}
		
	}

	//@@서버에서 받는 리스트 대신 임시사용. 삭제용.
	private void temperForMenu() {
		OrderInfoC menu1 = new OrderInfoC("왕새우튀김",1,12000,new Date());
		arrMenu.add(menu1);
		OrderInfoC menu2 = new OrderInfoC("치즈돈까스",1,12000,new Date());
		arrMenu.add(menu2);
		OrderInfoC menu3 = new OrderInfoC("꼬치오뎅우동",1,10000,new Date());
		arrMenu.add(menu3);
		OrderInfoC menu4 = new OrderInfoC("참다랑어회",1,30000,new Date());
		arrMenu.add(menu4);
		OrderInfoC menu5 = new OrderInfoC("메로구이",1,25000,new Date());
		arrMenu.add(menu5);
		OrderInfoC menu6 = new OrderInfoC("특제오꼬노미야끼",1,10000,new Date());
		arrMenu.add(menu6);		
	}

}