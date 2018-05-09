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
		mainF = new JFrame("����ȭ��");
		mainC = mainF.getContentPane();
		mainC.setLayout(new GridLayout(4,1,0,10));
		orderMenuBT = new JButton("�ֹ��ϱ�");		
		sendSmsBT = new JButton("�޽��� ������");
		sendMenuBT = new JButton("�޴� ������");
		callingWaterBT = new JButton("���� ȣ���ϱ�");		
		
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
		
		//��Ʈ����
		orderMenuBT.setFont(new Font("���� ���", Font.BOLD, 24));
		sendSmsBT.setFont(new Font("���� ���", Font.BOLD, 24));
		sendMenuBT.setFont(new Font("���� ���", Font.BOLD, 24));
		callingWaterBT.setFont(new Font("���� ���", Font.BOLD, 24));
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
				JOptionPane.showMessageDialog(null, "��ø� ��ٷ��ּ���");
			} else {
				System.out.println("����ȣ�����");
			}
		} else {}		
	}
}
