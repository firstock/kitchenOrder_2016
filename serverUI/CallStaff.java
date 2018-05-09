package serverUI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CallStaff implements ActionListener{
	int tableNo;
	private JFrame frame;
	private JPanel contentPane;
	private JLabel callMessage;
	private Container container;
	private JButton check;
	int rand1,rand2;
	
	public CallStaff(int tableNo) {
		rand1= (int)(System.currentTimeMillis() % 50);
		rand2= (int)(System.currentTimeMillis() % 50);
		
		frame= new JFrame("호출");
		contentPane = new JPanel();
//		check = new JButton("버튼 확인");
		container = frame.getContentPane();
		frame.setBounds(400+rand1, 500+rand2, 200, 50);
		
		callMessage= new JLabel();
		callMessage.setText(tableNo+"번 테이블");
		contentPane.add(callMessage);
		container.add(contentPane);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		check.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comName= e.getActionCommand();
		if(comName.equals(check)){
			// 됨?
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
}
