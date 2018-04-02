package serverAct;

import java.util.Random;

public class SerialNumberGiveS {
	// 회원 시리얼 넘버 난수 생성
	MemberManagingS Mbm = new MemberManagingS();// 멤버매니징 객체 생성

	/**
	 * 문자열 시리얼 넘버 반환(난수)
	 * @return
	 */
	String SerialNumber() { 
		Random random = new Random();
		StringBuffer SerialN = new StringBuffer();// 버퍼에 담는다
		boolean boo = true;
		do {
			SerialN = new StringBuffer();
			for (int i = 0; i < 7; i++) {
			
				if (random.nextBoolean()) {// 참아니면 거짓을 리턴
					SerialN.append((char) (random.nextInt(26) + 65));// 난수 발생
																		// 문자열
				} else {
					SerialN.append(random.nextInt(10));// 숫자 발생
				}

			}

			if (Mbm.check() != null) {// 중복처리
				for (int i = 0; i < Mbm.check().size(); i++) {// 디비에 있는
					// System.out.println("디비 코드넘버 수량"+Mbm.check().size()); //
					// serial넘버들
					String check = Mbm.check().get(i);// 디비에 있는 시리얼 넘버를 담고
					// System.out.println("만든넘버"+SerialN);
					// System.out.println("비교할넘버"+check);
					if (SerialN.toString().equals(check)) {// 만들어진 시리얼 넘과 비교
						boo = true;
						break;
					} else
						boo = false;

				}
			}

		} while (boo);
		return SerialN.toString();
	}
}