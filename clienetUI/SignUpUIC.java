package clienetUI;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import clientAct.ClientSocketC;
import clientAct.ManagementC;
import clientVO.BasicInfoC;
import clientVO.MemberInfoC;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.Component;

public class SignUpUIC implements ActionListener{
	//��������	
	ManagementC manage;
	
	//GUI
	JFrame signUpF;
	Container signUpC;
	private JPanel panel1, panel2, panel3;
	private JLabel nameL, ageL, genderL, phoneL, birthL;
	private JTextField nameT, ageT, phoneT, birthT;
	private JButton okBtn;
	private ButtonGroup genderG;
	private JRadioButton female, male;
	String name, age, gender, phone, birth;	int ageInt;
	int tableNo =0;
	
	SignUpUIC(ManagementC manage,int i) {
		this.manage=manage;
		this.tableNo = i;
		System.out.println();
		makeSignUp();
	}
	
	void makeSignUp () {
		signUpF = new JFrame("ȸ������");
		signUpC = signUpF.getContentPane();
		signUpC.setLayout(new BoxLayout(signUpC, BoxLayout.Y_AXIS));
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5,2,0,10));
		signUpC.add(panel1);
		
		//�̸�, ����
		nameL = new JLabel("�̸�", SwingConstants.CENTER);
		ageL = new JLabel("���� (���ڷ� �Է�)", SwingConstants.CENTER);
		nameT = new JTextField();		
		ageT = new JTextField();
		panel1.add(nameL);
		panel1.add(nameT);
		panel1.add(ageL);
		panel1.add(ageT);		
		
		//����
		genderL = new JLabel("����", SwingConstants.CENTER);
		panel2 = new JPanel();
		
		genderG = new ButtonGroup();
		female = new JRadioButton("����");
		female.setActionCommand("f");
		female.setSelected(true);
		male = new JRadioButton("����");
		male.setActionCommand("m");
		
		panel1.add(genderL);
		panel2.add(female);
		panel2.add(male);
		genderG.add(female);
		genderG.add(male);
		panel1.add(panel2);
		
		//�ڵ���,����
		phoneL = new JLabel("�ڵ��� ex)01012345678",
				SwingConstants.CENTER);
		birthL = new JLabel("������� ex)20160125",
				SwingConstants.CENTER);
		phoneT = new JTextField();		
		birthT = new JTextField();
		panel1.add(phoneL);
		panel1.add(phoneT);
		panel1.add(birthL);
		panel1.add(birthT);
		
		//�Ϸ�
		panel3 = new JPanel();
		okBtn = new JButton("�Ϸ�");	
				
		okBtn.addActionListener(this);
		panel3.add(okBtn);
		signUpC.add(panel3);

		signUpF.setBounds(100, 100, 650, 600);
		signUpF.setPreferredSize(new Dimension(650,650));
		signUpF.pack();
		signUpF.setVisible(true);
		signUpF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��Ʈ����
		nameL.setFont(new Font("���� ���", Font.BOLD, 24));
		nameT.setFont(new Font("���� ���", Font.BOLD, 24));
		ageL.setFont(new Font("���� ���", Font.BOLD, 24));
		ageT.setFont(new Font("���� ���", Font.BOLD, 24));
		genderL.setFont(new Font("���� ���", Font.BOLD, 24));
		female.setFont(new Font("���� ���", Font.BOLD, 24));
		male.setFont(new Font("���� ���", Font.BOLD, 24));
		phoneL.setFont(new Font("���� ���", Font.BOLD, 20));
		phoneT.setFont(new Font("���� ���", Font.BOLD, 24));
		birthL.setFont(new Font("���� ���", Font.BOLD, 20));
		birthT.setFont(new Font("���� ���", Font.BOLD, 24));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//JTextField���� �Է°�get
		name = nameT.getText();
		age = ageT.getText();
		gender = genderG.getSelection().getActionCommand();
		phone = phoneT.getText();
		birth = birthT.getText();
		
		//��ĭ�˻�
		if(name.length()!=0 && age.length()!=0 && gender.length()!=0 &&
				phone.length()!=0 && birth.length()!=0) {	//
			//��ĭ�� ���°��, server�� memberInfo������
			ageInt = Integer.parseInt(age);
			MemberInfoC mInfo = new MemberInfoC("0", null, name, ageInt, gender, phone, birth);
			String fromServer = manage.join(mInfo, tableNo);
			System.out.println("fromServer:" + fromServer);
			
			//�ٷ� �α����ϱ�
			BasicInfoC BasicInfo = new BasicInfoC(fromServer, null);
			if(manage.logIn(BasicInfo, tableNo)) {	//�α��μ���
				signUpF.setVisible(false);
				new MainMenuUIC(manage);
			} else {	//�α��ν���
				JOptionPane.showMessageDialog(null, "ȸ���� �ƴմϴ�.");			
			}			
		} else {	//��ĭ�� �ִ� ���
			JFrame alertF = new JFrame("Message");
			JOptionPane.showMessageDialog(alertF, "�ʼ� ������ ��� �Է��ϼ���.");
		}	
	}	
}
