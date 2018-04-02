package clienetUI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientAct.ClientSocketC;
import clientAct.ManagementC;
import clientAct.ReadThread;
import clientVO.BasicInfoC;
public class LoginUIC extends JFrame  implements ActionListener{
	ManagementC manage;
	private static ManagementC loginInfo;	//�α��� ���� ����
	private JFrame loginF;
	private Container loginC;
	private JPanel titleP, codeP, guestP;
	private JLabel codeL, titleL;
	private JButton loginBtn, signInBtn, guestBtn;
	private JTextField writeCodeT;
	int tableNo;
	public boolean loginCheck(){
		return (loginInfo != null);
	}
	public static ManagementC getLoginInfo() {
		return loginInfo;
	}



	public LoginUIC(int i){
		this.tableNo = i;
		login();
	}	
	public int getTableNo() {
		return tableNo;
	}
	void login() {
		loginF = new JFrame("�α���");
		loginC = loginF.getContentPane();
		loginF.setLayout(null);
		
		//����Panel
		titleP = new JPanel();
		titleP.setBackground(Color.ORANGE);
		titleP.setLocation(10,50);
		titleP.setSize(610, 40);
		loginC.add(titleP);
		
		titleL = new JLabel("'¦'�� ���Ű� ȯ���մϴ�.");
		titleP.add(titleL);
		
		//�ڵ��ȣ�Է�Panel
		codeP = new JPanel();
		codeP.setLocation(10,120);
		codeP.setSize(610, 60);
		loginC.add(codeP);
				
		codeL = new JLabel("CODE:");
		
		codeP.add(codeL);
			
		writeCodeT = new JTextField(13);
		codeP.add(writeCodeT);
				
		loginBtn = new JButton("ENTER");
		codeP.add(loginBtn);
		loginBtn.addActionListener(this);		

		//��ưPanel
		guestP = new JPanel();
		guestP.setLayout(new GridLayout(2, 1, 0, 50));
		guestP.setLocation(130, 220);
		guestP.setSize(370,150);
		loginC.add(guestP);		
		
		signInBtn = new JButton("Sign In");
		guestP.add(signInBtn);
		signInBtn.addActionListener(this);
		
		guestBtn = new JButton("I'm GUEST");		
		guestP.add(guestBtn);
		guestBtn.addActionListener(this);
		
		loginF.setBounds(100, 100, 650, 600);
		loginF.setPreferredSize(new Dimension(650,650));		
		loginF.pack();
		loginF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginF.setVisible(true);
		
		//��Ʈ����
		titleL.setFont(new Font("���� ���", Font.BOLD, 22));
		codeL.setFont(new Font("���� ���", Font.BOLD, 19));
		writeCodeT.setFont(new Font("���� ���", Font.BOLD, 19));
		loginBtn.setFont(new Font("���� ���", Font.BOLD, 15));
		signInBtn.setFont(new Font("���� ���", Font.BOLD, 15));
		guestBtn.setFont(new Font("���� ���", Font.BOLD, 15));
		
	}
		
	public void actionPerformed(ActionEvent e) {
		manage = new ManagementC(tableNo);
		//System.out.println("management����");
		Object checkBtn = e.getSource();
		if(checkBtn==loginBtn) {	//ȸ���� ��� �Է¹��� code�� ����
			String code = writeCodeT.getText();
			BasicInfoC BasicInfo = new BasicInfoC(code, null);
			
			if(manage.logIn(BasicInfo, tableNo)) {	//�α��μ���
				loginF.setVisible(false);
				new MainMenuUIC(manage);
				loginInfo = manage;	//�α��� ���� ���� static
			} else {	//�α��ν���
				JFrame failLogin = new JFrame("Message");
				JOptionPane.showMessageDialog(failLogin, "ȸ���� �ƴմϴ�.");
			}
		} else if(checkBtn==signInBtn) {	//ȸ�����Թ�ư
			loginF.setVisible(false);
			System.out.println("signupbtn");
			new SignUpUIC(manage,tableNo);
		} else if(checkBtn==guestBtn){	//��ȸ���� ��� ���̺��ȣ�� code�� ����
			String codeNum = String.valueOf(tableNo);
			BasicInfoC BasicInfo = new BasicInfoC(codeNum, null);
			if(manage.logIn(BasicInfo, tableNo)) {	//�α��μ���
				loginF.setVisible(false);
				new MainMenuUIC(manage);
			} else {	//�α��ν���
				}
			
		
			
		} else {}		
	}	
}
