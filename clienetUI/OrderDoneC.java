package clienetUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clientAct.ManagementC;
import clientVO.OrderInfoC;

public class OrderDoneC implements ActionListener {
	ManagementC manage;
	private JFrame frame;
	
	//���
	private JPanel northPanel;
	private JLabel theCurrentAmountlbl;
	private int theCurrentAmontInt = 0;
	public String theCurrentAmount = String.valueOf(theCurrentAmontInt);
	private final String won = "��";
	
	//�߰�
	private JPanel centerPanel;
	private DefaultTableModel model;
	private String[] columName = {"�����̸�","����","����","�ֹ��ð�"};
	private JTable showMenuTable;
	private JScrollPane scrollPane;
	
	//�ϴ�
	private JPanel southPanel;
	private JButton selectDeleteBtn;
	private JButton cancelBtn;
	private JButton checkBtn;
	ArrayList<OrderInfoC> arrOrder;	
	//Ȯ��

	public OrderDoneC(ArrayList<OrderInfoC> arrOrder, ManagementC manage) {
		this.arrOrder = arrOrder;
		this.manage = manage;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("�ֹ��Ϸ�");
		frame.setBounds(100, 100, 650, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		JLabel theCurrentAmountlbl = new JLabel(theCurrentAmount+won);
		northPanel.add(theCurrentAmountlbl);

		//middle
		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		model = new DefaultTableModel(columName, 0);
		showMenuTable = new JTable(model);
		scrollPane = new JScrollPane(showMenuTable);
		centerPanel.add(scrollPane);
		showJTable();
		
		//bottom
		southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		selectDeleteBtn = new JButton("���û���");
		southPanel.add(selectDeleteBtn);
		cancelBtn = new JButton("��ü���");
		southPanel.add(cancelBtn);
		checkBtn = new JButton("�ֹ�");
		southPanel.add(checkBtn);
		
		selectDeleteBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		checkBtn.addActionListener(this);

	}
	
	private void showJTable() {
		for(int i=0;i<arrOrder.size();i++){
			Object[] data = new Object[4];
			data[0] = arrOrder.get(i).getFoodName();
			data[1] = arrOrder.get(i).getFoodCount();
			data[2] = arrOrder.get(i).getFoodPrice();
			data[3] = arrOrder.get(i).getOrderTime();
			model.addRow(data);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object choice = e.getSource();
		if(choice==selectDeleteBtn){
			if(showMenuTable.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "���õ� �ֹ��� �����ϴ�.");
			}else{
				model.removeRow(showMenuTable.getSelectedRow());
			}
		}else if(choice==cancelBtn){
			frame.setVisible(false);
			new OrderMenuUIC(manage);
			JOptionPane.showMessageDialog(null, "��� �ֹ��� ��ҵǾ����ϴ�.");
		}else if(choice==checkBtn){
			//@@�ֹ� �޴����� ������.
			frame.setVisible(false);
			new OrderMenuUIC(manage);
			JOptionPane.showMessageDialog(null, "�ֹ��� �Ϸ�Ǿ����ϴ�.");
		}
		
	}

}
