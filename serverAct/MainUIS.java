package serverAct;

import javax.swing.JFrame;

import command.Command;
import serverAct.MemberManagingUIS;
import serverAct.ServerSocketS;
import serverUI.HallTablesS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainUIS extends JFrame {

	public static void main(String[] args) {
		new MainUIS();	//디폴트 생성자
		ServerSocketS server = new ServerSocketS();
		JFrame frame = new JFrame("짝");
		frame.setPreferredSize(new Dimension(300, 200));
		frame.setLocation(800, 400);
		Container contentPane = frame.getContentPane();
		GridLayout layout = new GridLayout(2, 1);
		contentPane.setLayout(layout);

		//회원 명단
		JButton btnNewButton = new JButton("MemberManaing");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MemberManagingUIS();

			}
		});

		btnNewButton.setFont(new Font("Aharoni", Font.BOLD, 17));
		contentPane.add(btnNewButton);

		//테이블 현황+계산
		JButton btnFoodmanaing = new JButton("Table");
		btnFoodmanaing.setFont(new Font("Aharoni", Font.BOLD, 17));
		btnFoodmanaing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HallTablesS();
			}
		});
		contentPane.add(btnFoodmanaing);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		server.SeverSocket();
		
	}

}
