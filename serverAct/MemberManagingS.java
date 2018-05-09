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
	
	/**회원 시리얼 넘버를 생성할때 중복 체크를 해주기위한 
	 * 시리얼넘버 값(DB에 저장되어있는)을 
	 * 불러서 arrayList에 담아주는 메소드
	 * @return
	 */
	ArrayList<String> check() {	//패키지 바깥에선 ㄴㄴ
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
	 * 클라이언트에서 받음 
	 * @param MemberInfoC
	 * DB에 삽입: 순차 memberNumber, 랜덤 codeNumber, 나이,성별,이름,번호,생일
	 * 랜덤 codeNumber: SerialNumberGiveS() 호출
	 */
	public void insertIntoMember(MemberInfoC member) {//소켓으로 memberInfo 객체를 매개변수로 사용해야함
		String insert = "insert into memberInfo (memberNumber,codeNumber,age,gender,name,phone,birthday)"
						+ " values (memberNumber.nextval,?,?,?,?,?,?)";
		SerialNumberGiveS give = new SerialNumberGiveS();
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "st15", "st15");
			pstmt = con.prepareStatement(insert);
			String serialN = give.SerialNumber();//시리얼 (난수)넘버 생성 메소드 호출
			this.serialNumber = serialN;
			 pstmt.setString(1,serialN);
			 pstmt.setInt(2,member.getAge());
			 pstmt.setString(3,member.getGender());
			 pstmt.setString(4,member.getName());
			 pstmt.setString(5,member.getPhone());
			 pstmt.setString(6,member.getBirth());
			 
				int cnt = pstmt.executeUpdate();
				System.err.println(cnt + "건이 추가되었습니다");

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
