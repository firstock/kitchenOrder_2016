package clienetUI;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstUIC implements ActionListener {

	private JFrame frame;
	private JPanel northPanel;
	private JLabel welcomelbl;
	
	//�߾� ���
	private JPanel centerPanel;
	private JButton tableBtn[]=new JButton[18];
	
	//�ϴ� ���
	private JPanel southPanel;
	private JLabel explainlbl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstUIC window = new FirstUIC();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstUIC() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("���̺� ����");
		frame.setBounds(100, 100, 650, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		welcomelbl = new JLabel("[¦]");
		northPanel.add(welcomelbl);
		
		centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		GridLayout grid = new GridLayout(3,6,15,15);
		centerPanel.setLayout(grid);
		for(int i=0;i<18;i++){
			tableBtn[i] = new JButton(String.valueOf(i+1));
			centerPanel.add(tableBtn[i]);
			tableBtn[i].addActionListener(this);
		}
		
		southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		explainlbl = new JLabel("<====�ڸ��� Ŭ�����ּ���====>");
		southPanel.add(explainlbl);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object choice = e.getSource();
		for(int i=0;i<18;i++){
			if(choice==tableBtn[i]){
				new LoginUIC(i+1); 
				frame.dispose();
			}
		}
	}

}
