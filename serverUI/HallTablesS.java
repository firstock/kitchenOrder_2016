package serverUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;

//JFrame 상속하면 Container 생성 안하고 getContentPane()으로 컴포넌트 추가 가능
public class HallTablesS
{
	final int TABLE_EA= 18;
		
	public HallTablesS(){
		JFrame frame= new JFrame("Hall");
		frame.setLocation(400,400);
		frame.setPreferredSize(new Dimension(400,300));
		Container contentPane= frame.getContentPane();
		contentPane.setLayout(new GridLayout(3,6));
		JButton btnNewButton[]= new JButton[TABLE_EA];
		ActionListener listener[]= new HallTableButtonListenerS[TABLE_EA];
		
		for(int i=0; i<TABLE_EA; i++){
			btnNewButton[i]= new JButton((i+1)+"");
			contentPane.add(btnNewButton[i]);
			
			listener[i]= new HallTableButtonListenerS(i+1);	//넘길때 +1
			btnNewButton[i].addActionListener(listener[i]);
			
		}
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
