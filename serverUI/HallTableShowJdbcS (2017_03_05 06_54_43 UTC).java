
package serverUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class HallTableShowJdbcS{
	static int tableNo_Hall;
	static String codeNo_Hall;
	/** �� �� ���̺��̳Ŀ� ���� �ش� ���̺� ��ȣ�� ������ Ŭ�󿡰� �޾Ƽ� DB�κ��� ��� */
	public HallTableShowJdbcS(int tableNo) {
		final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String URL = "jdbc:oracle:thin:@211.183.9.66:1521:xe";
		//DB������ st15��
		final String ID= "st15";
		final String PW= "st15";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final String sqlSelectMember = "select * from memberInfo "
									+ " where trim(codeNumber)=?"; // [����] ?�κ��� string

		String codeNumber;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, ID, PW);

			pstmt = con.prepareStatement(sqlSelectMember);

			//TableCodeS VO���� tableNo�̶� CodeNo �޾ƿ���
//			codeNumber= "000005";
			codeNumber= "ASF4DS";
			pstmt.setString(1, codeNumber);	//test�� codeNumber
			rs = pstmt.executeQuery();

			System.out.println(tableNo + "�� ���̺�");
			while (rs.next()) {
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				String name = rs.getString("name");

				new HallTableMemShowS(tableNo, age, gender,name);//���� �� �ѱ��: ����(�޴�&����)list
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
