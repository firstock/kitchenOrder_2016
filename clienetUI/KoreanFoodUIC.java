package clienetUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clientAct.FoodManagementC;
import clientAct.ManagementC;
import clientVO.OrderInfoC;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

//"@@"표시는 합치고 수정해야함.
public class KoreanFoodUIC implements ActionListener {

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
	ArrayList<OrderInfoC> temperArrOrderFood = new ArrayList<>();	
	OrderInfoC orderFood;
	ManagementC manage;

	public KoreanFoodUIC(ManagementC manage) {
		this.manage = manage;
		initialize();
	}
	
	private void initialize() {
		//@@ manage.setKoreanFood(); 테스트용
		arrMenu = manage.getKoreanFood();
		frame = new JFrame("KoreanFood");
		frame.setVisible(true);//문제 발생시 위치 선정 중요############################
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
		System.out.println(arrMenu.size());
		for(int i=0;i<arrMenu.size();i++){
			System.out.println(arrMenu.get(i));
		}
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
			//주문음식 저장하고 완료시 저장.
			manage.saveOrder(temperArrOrderFood);
			temperArrOrderFood = manage.getOrderFood();
			new OrderDoneUIC(temperArrOrderFood,manage);
			temperArrOrderFood.removeAll(temperArrOrderFood);
			frame.setVisible(false);
		}if(checkBT==beforeBT){
			manage.saveOrder(temperArrOrderFood);
			temperArrOrderFood.removeAll(temperArrOrderFood);
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
}