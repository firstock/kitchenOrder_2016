package serverAct;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import clientVO.OrderInfoC;


public class MenuS {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@211.183.9.66:1521:xe";
	OrderInfoC menu = null;
	
	ArrayList<OrderInfoC> Menulist = new ArrayList<OrderInfoC>();
	
	public  ArrayList<OrderInfoC> selectMenu(){
		String Menu = "select * from foodmanaging";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,"st15","st15");
			pstmt = con.prepareStatement(Menu);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String db_foodName = rs.getString("FOODNAME");
				int db_price = rs.getInt("FOODPRICE");
				OrderInfoC menu = new OrderInfoC("",0,0,null);
				menu.setFoodName(db_foodName);
				menu.setFoodPrice(db_price);
				
				Menulist.add(menu);
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				pstmt.close();
				con.close();
			}catch(Exception igorne){
				
			}
		}
		return Menulist;
		
	}

}
