package serverUI;
// ���̺� ������ ������ �������� JLabel�� ����� ��

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HallTableMemShowS {
	public HallTableMemShowS(int tableNo,int age, String gender,String name){
		JFrame frame= new JFrame(tableNo+"�� ���̺�");//â �̸�
		
		frame.setPreferredSize(new Dimension(300,500));
		frame.setLocation(500,400);
		Container contentPane= frame.getContentPane();
		BoxLayout layout= new BoxLayout(contentPane, BoxLayout.Y_AXIS);//p.747
		
		//box ��,�Ʒ� : ��(ȸ������), ���̺�(���� �̸�/����)
		
		//ȸ������
		String memDB= age+"/  "+gender+"/  "+name;
		JLabel label= new JLabel(memDB);
		
		//���̺� �󿡼� �߰���ư �� �����Ÿ� model�ʿ� ���� ��
		//���̺�: ���� > ���� or ���� ����_p.759
		String colNames[]= {"�����̸�","����"};
		//String foodOrderDB= foodName+"\t"+foodPrice;
		DefaultTableModel model= new DefaultTableModel(colNames,0);
		JTable table= new JTable(model);
		
		contentPane.setLayout(layout);
		contentPane.add(label);
		contentPane.add(table);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
