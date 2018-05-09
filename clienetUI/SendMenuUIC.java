package clienetUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clientAct.ManagementC;

public class SendMenuUIC implements ActionListener  {
	ManagementC manage;
	
	private JFrame frame;
	private JPanel northPanel;
	private JLabel welcomelbl;
	private JLabel theCurrentAmountlbl;
	public String theCurrentAmount;
	private final String won = "원";
	private int theCurrentAmountInt;
	
	// 중앙 페널
	private JPanel centerPanel;
	private JButton tableBtn[] = new JButton[18];
	private int sendTableNo;
	private boolean sendCheck = true;
	
	// 하단 페널
	private JPanel southPanel;
	private JLabel explainlbl;
	private JButton beforeBT;

	public SendMenuUIC(ManagementC manage) {
		this.manage = manage;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("주문 보내기");
		frame.setVisible(true);
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		welcomelbl = new JLabel("[♡오늘도 화이팅하는 당신♡]");
		northPanel.add(welcomelbl);
		theCurrentAmountInt = 0;
		theCurrentAmount = String.valueOf(theCurrentAmountInt);
		theCurrentAmountlbl = new JLabel(theCurrentAmount+" "+won);
		northPanel.add(theCurrentAmountlbl);

		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		GridLayout grid = new GridLayout(3, 6, 15, 15);
		centerPanel.setLayout(grid);
		for (int i = 0; i < 18; i++) {
			tableBtn[i] = new JButton(String.valueOf(i + 1));
			centerPanel.add(tableBtn[i]);
			tableBtn[i].addActionListener(this);
		}

		southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		explainlbl = new JLabel("<상대방에게 보내는 주문음식의 금액은 손님의 금액에 청구됩니다.>");
		southPanel.add(explainlbl);
		
		beforeBT = new JButton("<<이전 메뉴");
		southPanel.add(beforeBT);
		beforeBT.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object choice = e.getSource();
		for (int i = 0; i < 18; i++) {
			if (choice == tableBtn[i]) {
				int result = JOptionPane.showOptionDialog(null, ((i+1)+"의 테이블에 주문하시겠습니까? 확인클리시 취소불가."), "사랑의 음식 전달 서비스", JOptionPane.OK_CANCEL_OPTION, 2, null, null, null);
				if(result==JOptionPane.OK_OPTION){
					new OrderMenuUIC((i+1),false);
					frame.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "사랑의 힘이 모자릅니다.");
				}
			}
		}
		if(choice==beforeBT){
			frame.setVisible(false);
			new MainMenuUIC(manage);
		}
	}

}

