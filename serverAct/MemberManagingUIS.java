package serverAct;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import clientVO.MemberInfoC;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MemberManagingUIS extends JFrame implements ActionListener {
	MemberManagingS test = new MemberManagingS();
	JButton button1;
	JButton button2;
	JFrame frame = new JFrame("MemberManaing");
	Container contentPane = frame.getContentPane();
	String colNames[] = { "ȸ����ȣ", "�̸�", "����", "����", "�ڵ�����ȣ", "ȸ���ø���ѹ�", "�������" };
	JTable table = new JTable(getDataToString(), colNames);
	public MemberManagingUIS() {
		showJTable();
	}
	

	private void showJTable() {
//		JFrame frame = new JFrame("MemberManaing");
		frame.setPreferredSize(new Dimension(720, 600));
		frame.setLocation(300, 250);
//		Container contentPane = frame.getContentPane();
		//String colNames[] = { "ȸ����ȣ", "�̸�", "����", "����", "�ڵ�����ȣ", "ȸ���ø���ѹ�", "�������" };
		JTable table = new JTable(getDataToString(), colNames);
		table.setPreferredScrollableViewportSize(new Dimension(700, 600));
		contentPane.add(new JScrollPane(table), BorderLayout.WEST);
		contentPane.setSize(900, 600);
		JPanel panel = new JPanel();
		JTextField text1 = new JTextField(3);

		button1 = new JButton("����");
		button1.addActionListener(this);
	
		panel.add(button1);
		
		contentPane.add(panel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
		new MemberManagingUIS();
		frame.setVisible(false); // ���ŵ� �����͸� ȭ�鿡 �ٽ� �ε�
			System.out.println("button1");			
	

		
	}


	private String[][] getDataToString() {//db�� �ִ� ȸ������ �ҷ�����
		String colNames[] = { "ȸ����ȣ", "�̸�", "����", "����", "�ڵ�����ȣ", "ȸ���ø���ѹ�", "�������" };
		ArrayList<MemberInfoC> memberInfo = test.MemberInfo();
		String data[][] = new String[memberInfo.size()][colNames.length];
		for (int i = 0; i < memberInfo.size(); i++) {
			data[i][0] = ""+(i+1);
			data[i][1] = memberInfo.get(i).getName();
			data[i][2] = memberInfo.get(i).getGender();
			data[i][3] = "" + memberInfo.get(i).getAge();
			data[i][4] = memberInfo.get(i).getPhone();
			data[i][5] = memberInfo.get(i).getCodeNumber();
			data[i][6] = memberInfo.get(i).getBirth();
		}
		return data;
	}

}
