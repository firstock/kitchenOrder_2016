
package serverUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class HallTableShowJdbcS{
	static int tableNo_Hall;
	static String codeNo_Hall;
	/** 몇 번 테이블이냐에 따라 해당 테이블 번호의 정보를 클라에게 받아서 DB로부터 출력 */
	public HallTableShowJdbcS(int tableNo) {
		final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String URL = "jdbc:oracle:thin:@211.183.9.66:1521:xe";
		//DB접속은 st15로
		final String ID= "st15";
		final String PW= "st15";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final String sqlSelectMember = "select * from memberInfo "
									+ " where trim(codeNumber)=?"; // [유의] ?부분이 string

		String codeNumber;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, ID, PW);

			pstmt = con.prepareStatement(sqlSelectMember);

			//TableCodeS VO에서 tableNo이랑 CodeNo 받아오기
//			codeNumber= "000005";
			codeNumber= "ASF4DS";
			pstmt.setString(1, codeNumber);	//test용 codeNumber
			rs = pstmt.executeQuery();

			System.out.println(tableNo + "번 테이블");
			while (rs.next()) {
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String name = rs.getString("name");

				new HallTableMemShowS(tableNo, age, gender,name);//인자 더 넘길것: 음식(메뉴&가격)list
				//test: System.out.println("HallTableShowJdbcS line49.java");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
