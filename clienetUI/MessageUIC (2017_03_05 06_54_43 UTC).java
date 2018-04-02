package clienetUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clientAct.ClientSocketC;
import clientAct.ManagementC;
import clientAct.ReadThread;
import clientVO.MessageInfoC;

public class MessageUIC implements ActionListener {
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
	private String message;
	
	// 하단 페널
	private JPanel southPanel;
	private JLabel explainlbl;
	private JButton beforeBT;

	public MessageUIC(ManagementC manage) {
		this.manage = manage;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("메세지/주문 보내기");
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
		explainlbl = new JLabel("<메세지는 천원의 비용이 발생되며, 상대방의 금액에서 천원이 차감됩니다.>");
		southPanel.add(explainlbl);
		
		beforeBT = new JButton("<<이전 메뉴");
		southPanel.add(beforeBT);
		beforeBT.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Date date = new Date();
//		String name = e.getActionCommand();
		Object choice = e.getSource();
//		if(name.equals("")){
//			
//		}
		for (int i = 0; i < 18; i++) {
			if (choice == tableBtn[i]) {
				
				message = JOptionPane.showInputDialog((i+1)+"의 테이블에 메세지를 보내겠습니까? 내용을 입력해주세요");
//				System.out.println(message+"1");
				if((message!=null)&&(0<message.length())){
//					System.out.println(message+"2");
					theCurrentAmountInt += 1000;
					theCurrentAmount = theCurrentAmountInt+"";
					theCurrentAmountlbl.setText(theCurrentAmount+" "+won);
					
					
					//@@메니지먼트에 보냄. 메시지보냄.
					MessageInfoC smsInfo = new MessageInfoC(i+1, message, date);
//					System.out.println(smsInfo.getToTable() + 
//							smsInfo.getContent() + smsInfo.getSentTime());
//					System.out.println("MessageUIC@fromTable :" + manage.getTableNo());
					manage.sendMessage(smsInfo, manage.getTableNo());
//					ReadThread.getInstance().setMessage(smsInfo);	//이거 본인 메세지
					System.out.println("메세지를 보냈습니다."+smsInfo.toString());
					JOptionPane.showMessageDialog(null, ((i+1)+"의 테이블에 메세지를 보냈습니다."));
				}
			}
		}
		if(choice==beforeBT){
			frame.setVisible(false);
			new MainMenuUIC(manage);
		}
	}

}
