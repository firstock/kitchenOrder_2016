package serverUI;
// 테이블 누른후 데려온 쿼리문을 JLabel로 출력할 것

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HallTableMemShowS {
	public HallTableMemShowS(int tableNo,int age, String gender,String name){
		JFrame frame= new JFrame(tableNo+"번 테이블");//창 이름
		
		frame.setPreferredSize(new Dimension(300,500));
		frame.setLocation(500,400);
		Container contentPane= frame.getContentPane();
		BoxLayout layout= new BoxLayout(contentPane, BoxLayout.Y_AXIS);//p.747
		
		//box 위,아래 : 라벨(회원정보), 테이블(음식 이름/가격)
		
		//회원정보
		String memDB= age+"/  "+gender+"/  "+name;
		JLabel label= new JLabel(memDB);
		
		//테이블 상에서 추가버튼 안 넣을거면 model필요 없을 듯
		//테이블: 섭씨 > 복붙 or 예제 참조_p.759
		String colNames[]= {"음식이름","가격"};
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
