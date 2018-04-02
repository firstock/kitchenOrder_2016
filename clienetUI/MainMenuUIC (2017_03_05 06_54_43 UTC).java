package clienetUI;

import javax.swing.*;

import clientAct.ManagementC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuUIC implements ActionListener {
	ManagementC manage;
	private JFrame mainF;
	private Container mainC;
	private JButton orderMenuBT, sendSmsBT, sendMenuBT, callingWaterBT;
	MainMenuUIC (ManagementC manage) {
		this.manage = manage;
		makeMainUI();	
	}
	void makeMainUI() {
		mainF = new JFrame("메인화면");
		mainC = mainF.getContentPane();
		mainC.setLayout(new GridLayout(4,1,0,10));
		orderMenuBT = new JButton("주문하기");		
		sendSmsBT = new JButton("메시지 보내기");
		sendMenuBT = new JButton("메뉴 보내기");
		callingWaterBT = new JButton("직원 호출하기");		
		
		mainC.add(orderMenuBT);
		mainC.add(sendSmsBT);
		mainC.add(sendMenuBT);
		mainC.add(callingWaterBT);
		
		orderMenuBT.addActionListener(this);
		sendSmsBT.addActionListener(this);
		sendMenuBT.addActionListener(this);
		callingWaterBT.addActionListener(this);
		
		mainF.setBounds(100, 100, 650, 600);
		mainF.setPreferredSize(new Dimension(650,650));
		mainF.pack();
		mainF.setVisible(true);
		mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//폰트설정
		orderMenuBT.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		sendSmsBT.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		sendMenuBT.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		callingWaterBT.setFont(new Font("맑은 고딕", Font.BOLD, 24));
	}
	/*public static void main(String[] args) {
		new MainMenuUIC();
	}*/
	
	public void actionPerformed(ActionEvent e) {
		Object checkBT = e.getSource();
		if(checkBT == orderMenuBT) {
			mainF.setVisible(false);
			new OrderMenuUIC(manage);			
		} else if(checkBT == sendSmsBT) {
			mainF.setVisible(false);
			new MessageUIC(manage);
		} else if(checkBT == sendMenuBT) {
			mainF.setVisible(false);
			new SendMenuUIC(manage);
		} else if(checkBT == callingWaterBT) {
			if(manage.callStaff(manage.getTableNo())) {
				JOptionPane.showMessageDialog(null, "잠시만 기다려주세요");
			} else {
				System.out.println("직원호출실패");
			}
		} else {}		
	}
}
