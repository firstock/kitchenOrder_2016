package clienetUI;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clientAct.ManagementC;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderMenuUIC implements ActionListener {
	ManagementC manage;
	
	private JFrame frame;
	//상단 패널
	private JPanel northPanel ;
	private JLabel theCurrentAmountlbl;
	public String theCurrentAmount;
	private final String won = "원";
	private int theCurrentAmontInt = 0;
	
	//중간 패널
	private JPanel centerPanel ;
	private JButton koreanFoodBtn;
	private JButton japaneseFoodBtn;
	private JButton weternFoodBtn;
	private JButton drinksBtn;
	
	//하단 패널
	private JPanel southPanel ;
	private JButton previousBtn;
	private int sendTableNo;
	private boolean sendCehck;
	
	public OrderMenuUIC(ManagementC manage) {
		this.manage = manage;
		initialize();
	}
	public OrderMenuUIC(int sendTableNo,boolean sendCheck) {
		this.sendTableNo = sendTableNo;
		this.sendCehck = sendCheck;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("주문하기");
		frame.setVisible(true);
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		theCurrentAmountlbl = new JLabel("0 "+won);
		northPanel.add(theCurrentAmountlbl);
	
		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(4, 1, 15, 15));
		koreanFoodBtn = new JButton("한국 음식");
		japaneseFoodBtn = new JButton("일본 음식");
		weternFoodBtn = new JButton("서양 음식");
		drinksBtn = new JButton("마실 것");
		centerPanel.add(koreanFoodBtn);
		centerPanel.add(japaneseFoodBtn);
		centerPanel.add(weternFoodBtn);
		centerPanel.add(drinksBtn);
		koreanFoodBtn.addActionListener(this);
		japaneseFoodBtn.addActionListener(this);
		weternFoodBtn.addActionListener(this);
		drinksBtn.addActionListener(this);
		
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		previousBtn = new JButton("<<이전");
		southPanel.add(previousBtn);
		previousBtn.addActionListener(this);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		Object choice = e.getSource();
		if(choice==koreanFoodBtn){
			new KoreanFoodUIC(manage);
			frame.setVisible(false);
		}else if(choice==japaneseFoodBtn){
			new JapaneseFoodUIC(manage);
			frame.setVisible(false);
		}else if(choice==weternFoodBtn){
			new WesternFoodUIC(manage);
			frame.setVisible(false);
		}else if(choice==drinksBtn){
			new DrinksUIC(manage);
			frame.setVisible(false);
		}else if(choice==previousBtn){
			frame.setVisible(false);
			new MainMenuUIC(manage);
		} 
		
	}

}
