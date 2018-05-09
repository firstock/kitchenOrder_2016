package serverAct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import clientVO.MemberInfoC;


public class MemberManagingS {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@211.183.9.66:1521:xe";
	String serialNumber;
	MemberInfoC memberInfo = null;
	ArrayList<String> serial;
	
	/**ȸ�� �ø��� �ѹ��� �����Ҷ� �ߺ� üũ�� ���ֱ����� 
	 * �ø���ѹ� ��(DB�� ����Ǿ��ִ�)�� 
	 * �ҷ��� arrayList�� ����ִ� �޼ҵ�
	 * @return
	 */
	ArrayList<String> check() {	//��Ű�� �ٱ����� ����
		String SelelctSerialNumber = "Select codeNumber from memberInfo";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "st15", "st15");
			pstmt = con.prepareStatement(SelelctSerialNumber);
			rs = pstmt.executeQuery();
			 serial = new ArrayList<String>();
			while (rs.next()) {
				String db_serialNumber = rs.getString("codeNumber");
				serial.add(db_serialNumber);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {

			}
		}
		
		return serial;

	}

	/**
	 * Ŭ���̾�Ʈ���� ���� 
	 * @param MemberInfoC
	 * DB�� ����: ���� memberNumber, ���� codeNumber, ����,����,�̸�,��ȣ,����
	 * ���� codeNumber: SerialNumberGiveS() ȣ��
	 */
	public void insertIntoMember(MemberInfoC member) {//�������� memberInfo ��ü�� �Ű������� ����ؾ���
		String insert = "insert into memberInfo (memberNumber,codeNumber,age,gender,name,phone,birthday)"
						+ " values (memberNumber.nextval,?,?,?,?,?,?)";
		SerialNumberGiveS give = new SerialNumberGiveS();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "st15", "st15");
			pstmt = con.prepareStatement(insert);
			String serialN = give.SerialNumber();//�ø��� (����)�ѹ� ���� �޼ҵ� ȣ��
			this.serialNumber = serialN;
			 pstmt.setString(1,serialN);
			 pstmt.setInt(2,member.getAge());
			 pstmt.setString(3,member.getGender());
			 pstmt.setString(4,member.getName());
			 pstmt.setString(5,member.getPhone());
			 pstmt.setString(6,member.getBirth());
			 
				int cnt = pstmt.executeUpdate();
				System.err.println(cnt + "���� �߰��Ǿ����ϴ�");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}
	public String getSerial(){
		return this.serialNumber;
	}
	/** Select * from memberInfo */
	ArrayList<MemberInfoC> MemberInfo(){
		String SelelctMemberInfo = "Select * from memberInfo";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberInfoC> memberList = null;
		MemberInfoC memberInfo = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "st15", "st15");
			pstmt = con.prepareStatement(SelelctMemberInfo);
			rs = pstmt.executeQuery();
			memberList = new ArrayList<MemberInfoC>();			
			
			while (rs.next()) {				
			 memberInfo = new MemberInfoC("",null,"",0,"","","");
			
				memberInfo.setCodeNumber(rs.getString("codeNumber"));
				memberInfo.setAge(rs.getInt("age"));
				memberInfo.setGender(rs.getString("gender"));
				memberInfo.setName(rs.getString("name"));
				memberInfo.setPhone(rs.getString("phone"));
				memberInfo.setBirth(rs.getString("birthday"));
				
				memberList.add(memberInfo);				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {

			}
		}
		return memberList;
		
		
	
	}
	

	
}
