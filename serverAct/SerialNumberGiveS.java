package serverAct;

import java.util.Random;

public class SerialNumberGiveS {
	// ȸ�� �ø��� �ѹ� ���� ����
	MemberManagingS Mbm = new MemberManagingS();// ����Ŵ�¡ ��ü ����

	/**
	 * ���ڿ� �ø��� �ѹ� ��ȯ(����)
	 * @return
	 */
	String SerialNumber() { 
		Random random = new Random();
		StringBuffer SerialN = new StringBuffer();// ���ۿ� ��´�
		boolean boo = true;
		do {
			SerialN = new StringBuffer();
			for (int i = 0; i < 7; i++) {
			
				if (random.nextBoolean()) {// ���ƴϸ� ������ ����
					SerialN.append((char) (random.nextInt(26) + 65));// ���� �߻�
																		// ���ڿ�
				} else {
					SerialN.append(random.nextInt(10));// ���� �߻�
				}

			}

			if (Mbm.check() != null) {// �ߺ�ó��
				for (int i = 0; i < Mbm.check().size(); i++) {// ��� �ִ�
					// System.out.println("��� �ڵ�ѹ� ����"+Mbm.check().size()); //
					// serial�ѹ���
					String check = Mbm.check().get(i);// ��� �ִ� �ø��� �ѹ��� ���
					// System.out.println("����ѹ�"+SerialN);
					// System.out.println("���ҳѹ�"+check);
					if (SerialN.toString().equals(check)) {// ������� �ø��� �Ѱ� ��
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