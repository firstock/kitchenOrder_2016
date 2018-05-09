package clienetUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientAct.FoodManagementC;
import clientAct.ManagementC;
import clientVO.OrderInfoC;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

//"@@"ǥ�ô� ��ġ�� �����ؾ���.
public class JapaneseFoodUIC implements ActionListener {

	private JFrame frame;
	//��� ����ǥ��
	private JPanel northPanel;
	private JLabel theCurrentAmountlbl;
	public String theCurrentAmount;
	private final String won = "��";
	private int theCurrentAmontInt = 0;
	//�߰� �޴�ǥ��
	private JPanel centerPanel;
	private JLabel foodNamelbl[] = new JLabel[6];
	private JTextField foodCountTF[] = new JTextField[6];
	private int foodCount[] = {0,0,0,0,0,0};
	private JButton foodPlusBT[] = new JButton[6];
	private JButton foodMinusBT[] = new JButton[6];
	private JLabel foodPricelbl[] = new JLabel[6];
	ArrayList<OrderInfoC> arrMenu = new ArrayList<>();
	OrderInfoC menuAll;
	//�ϴ� ǥ��
	private JPanel southPanel;
	private JButton finishOrderBT;
	private JButton beforeBT;
	ArrayList<OrderInfoC> arrOrderFood = new ArrayList<>();
	ArrayList<OrderInfoC> temperArrOrderFood = new ArrayList<>();	
	OrderInfoC orderFood;
	ManagementC manage;

	public JapaneseFoodUIC(ManagementC manage) {
		this.manage=manage;
		initialize();
	}
	
	private void initialize() {
		//@@arrMenu = �������� ���� ����Ʈ ���ؾ���. ������.
		arrMenu = manage.getJapaneseFood();
		frame = new JFrame("JapaneseFood");
		frame.setVisible(true);
		frame.setBounds(100, 100, 650, 600);//100~700
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//��� ���� ����.
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		theCurrentAmount = "0";
		theCurrentAmountlbl = new JLabel(theCurrentAmount+" "+won);
		northPanel.add(theCurrentAmountlbl);
		
		//�߾� �޴� ����.
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6, 5, 0, 0));
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		for(int i=0;i<arrMenu.size();i++){
			foodNamelbl[i] = new JLabel(arrMenu.get(i).getFoodName(),JLabel.CENTER);
			foodCountTF[i] = new JTextField(String.valueOf(foodCount[i]));
			foodCountTF[i].setHorizontalAlignment(JTextField.CENTER);			
			foodCountTF[i].setEnabled(false);
			foodPlusBT[i] = new JButton("+");
			foodMinusBT[i] = new JButton("-");
			//foodPricelbl[i] = new JLabel(String.valueOf(arrMenu.get(i).getPrice()),JLabel.CENTER);
			int price = arrMenu.get(i).getFoodPrice();
			if(1000<=price){
				foodPricelbl[i] = new JLabel((String.valueOf(price/1000)+",000"+won),JLabel.CENTER);
			}else{
				foodPricelbl[i] = new JLabel(String.valueOf(price)+won,JLabel.CENTER);
			}
			centerPanel.add(foodNamelbl[i]);
			centerPanel.add(foodCountTF[i]);
			centerPanel.add(foodPlusBT[i]);
			centerPanel.add(foodMinusBT[i]);
			centerPanel.add(foodPricelbl[i]);	
			foodPlusBT[i].addActionListener(this);
			foodMinusBT[i].addActionListener(this);
		}
		
		//�ϴ� �Ϸ� ����
		southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		finishOrderBT = new JButton("�ֹ� �Ϸ�");
		beforeBT = new JButton("<<���� �޴�");
		southPanel.add(finishOrderBT);
		southPanel.add(beforeBT);
		finishOrderBT.addActionListener(this);
		beforeBT.addActionListener(this);	
	}
	
	public void actionPerformed(ActionEvent e){
		Object checkBT = e.getSource();
		for(int i=0;i<6;i++){
			if(checkBT==foodPlusBT[i]){
				foodCount[i] += 1;
				foodCountTF[i].setText(String.valueOf(foodCount[i]));
				int price = arrMenu.get(i).getFoodPrice();
				theCurrentAmontInt += price;
				if(1000<=theCurrentAmontInt){
					theCurrentAmount = String.valueOf(theCurrentAmontInt/1000+",000 ");
				}else{
					theCurrentAmount = String.valueOf(theCurrentAmontInt);
				}
				theCurrentAmountlbl.setText(theCurrentAmount+" "+won);
				foodPlusBascket(foodNamelbl[i]);
			}else if(checkBT==foodMinusBT[i]){
				if(foodCount[i]!=0){
					foodCount[i] -= 1;
					foodCountTF[i].setText(String.valueOf(foodCount[i]));
					int price = arrMenu.get(i).getFoodPrice();
					theCurrentAmontInt -= price;
					if(1000<=theCurrentAmontInt){
						theCurrentAmount = String.valueOf(theCurrentAmontInt/1000+",000 ");
					}else{
						theCurrentAmount = String.valueOf(theCurrentAmontInt);
					}
					theCurrentAmountlbl.setText(theCurrentAmount+" "+won);
					foodMinusBascket(foodNamelbl[i]);
				}else{
					JOptionPane.showMessageDialog(null, "������ 0�Դϴ�.");
				}
			}
		}
		if(checkBT==finishOrderBT){
			//@@���۾��(1ȸ �ֹ���) ������ sm.orderFood(temperArrOrderFood);
			for(int i=0;i<temperArrOrderFood.size();i++){
				arrOrderFood.add(temperArrOrderFood.get(i));
			}
			new OrderDoneUIC(temperArrOrderFood,manage);
			temperArrOrderFood.removeAll(temperArrOrderFood);
			frame.setVisible(false);
		}if(checkBT==beforeBT){
			new OrderMenuUIC(manage);
			frame.setVisible(false);
		}
		
	}

	private void foodPlusBascket(JLabel foodNamelbl) {
		String foodName = foodNamelbl.getText();
		int foodPrice = manage.getEachFoodPrice(foodName);
		orderFood = new OrderInfoC(foodName,1,foodPrice,new Date());
		temperArrOrderFood.add(orderFood);
	}
	
	private void foodMinusBascket(JLabel foodName) {
		for(int i=0;i<temperArrOrderFood.size();i++){
			if(foodName.getText().equals(temperArrOrderFood.get(i).getFoodName())){
				temperArrOrderFood.remove(i);
				break;
			}
		}
		
	}

	//@@�������� �޴� ����Ʈ ��� �ӽû��. ������.
	private void temperForMenu() {
		OrderInfoC menu1 = new OrderInfoC("�ջ���Ƣ��",1,12000,new Date());
		arrMenu.add(menu1);
		OrderInfoC menu2 = new OrderInfoC("ġ��",1,12000,new Date());
		arrMenu.add(menu2);
		OrderInfoC menu3 = new OrderInfoC("��ġ�����쵿",1,10000,new Date());
		arrMenu.add(menu3);
		OrderInfoC menu4 = new OrderInfoC("���ٶ���ȸ",1,30000,new Date());
		arrMenu.add(menu4);
		OrderInfoC menu5 = new OrderInfoC("�޷α���",1,25000,new Date());
		arrMenu.add(menu5);
		OrderInfoC menu6 = new OrderInfoC("Ư��������̾߳�",1,10000,new Date());
		arrMenu.add(menu6);		
	}

}