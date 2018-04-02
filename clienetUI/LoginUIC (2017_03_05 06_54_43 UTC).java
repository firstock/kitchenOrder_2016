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
	private static ManagementC loginInfo;	//로그인 정보 저장
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
		loginF = new JFrame("로그인");
		loginC = loginF.getContentPane();
		loginF.setLayout(null);
		
		//제목Panel
		titleP = new JPanel();
		titleP.setBackground(Color.ORANGE);
		titleP.setLocation(10,50);
		titleP.setSize(610, 40);
		loginC.add(titleP);
		
		titleL = new JLabel("'짝'에 오신걸 환영합니다.");
		titleP.add(titleL);
		
		//코드번호입력Panel
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

		//버튼Panel
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
		
		//폰트설정
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		codeL.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		writeCodeT.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		signInBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		guestBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
	}
		
	public void actionPerformed(ActionEvent e) {
		manage = new ManagementC(tableNo);
		//System.out.println("management생성");
		Object checkBtn = e.getSource();
		if(checkBtn==loginBtn) {	//회원인 경우 입력받은 code를 전송
			String code = writeCodeT.getText();
			BasicInfoC BasicInfo = new BasicInfoC(code, null);
			
			if(manage.logIn(BasicInfo, tableNo)) {	//로그인성공
				loginF.setVisible(false);
				new MainMenuUIC(manage);
				loginInfo = manage;	//로그인 정보 저장 static
			} else {	//로그인실패
				JFrame failLogin = new JFrame("Message");
				JOptionPane.showMessageDialog(failLogin, "회원이 아닙니다.");
			}
		} else if(checkBtn==signInBtn) {	//회원가입버튼
			loginF.setVisible(false);
			System.out.println("signupbtn");
			new SignUpUIC(manage,tableNo);
		} else if(checkBtn==guestBtn){	//비회원인 경우 테이블번호를 code로 전송
			String codeNum = String.valueOf(tableNo);
			BasicInfoC BasicInfo = new BasicInfoC(codeNum, null);
			if(manage.logIn(BasicInfo, tableNo)) {	//로그인성공
				loginF.setVisible(false);
				new MainMenuUIC(manage);
			} else {	//로그인실패
				}
			
		
			
		} else {}		
	}	
}
