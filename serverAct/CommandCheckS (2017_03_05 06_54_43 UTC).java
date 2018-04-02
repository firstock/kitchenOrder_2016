package serverAct;

import java.net.Socket;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;


import clientVO.BasicInfoC;
import clientVO.MemberInfoC;
import clientVO.MessageInfoC;
import serverUI.CallStaff;
import clientVO.OrderInfoC;
import command.Command;
//import 

/**
 * Ŭ���̾�Ʈ���� ���۵�Command �� �̿��ؼ� ���� ��ü���� �˾Ƴ��� �������ִ°�
 *
 */
public class CommandCheckS {
	
	MemberManagingS managing = new MemberManagingS();
	ServerThreads server = new ServerThreads();
	MenuS menu2 = new MenuS();
	GregorianCalendar calendar = new GregorianCalendar();
	SimpleDateFormat dateFormat = new SimpleDateFormat("aa hh�� mm��");
	ArrayList<OrderInfoC> menu = menu2.selectMenu();
	int tableN;
	String serialNo;
	
	public CommandCheckS(){}
	
	/** 
	 * @param Command, Socket
	 * ������ ���� socket �� Command VO �ȿ� �ִ�
	 * command ���� �����̳Ŀ� ���� join,LogIn,Food�ֹ�,Message���� �Ǵ��ϰ�
	 * �׿� �´� ������ ó��
	 */
	public void Check(Command com, Socket socket) {
		Command defaultCom = new Command();
		System.out.println("CommandCheckSŬ���� Check�޼ҵ�� �Ѿ��");
		// Join_Check = 10;
		// LogIn_Check = 15;
		// Food_Check = 20;
		// Message_Check = 30;
		//
		// Command_OK = 40;
		// Command_Fail = 50;
		System.out.println("CommandCheckSŬ���� Check�޼ҵ�� �Ѿ��");		//test
		System.out.println("com:"+com);

		if (com.getCommand() == Command.Join_Check) {
			tableN = com.getTableNo();
			managing.insertIntoMember((MemberInfoC) com.getObj());
			System.out.println("�߰� �Ǿ����ϴ�");
			Command returnCom = new Command();
			returnCom.setCommand(40);
			returnCom.setTableNo(tableN);
			returnCom.setObj(managing.getSerial());
			System.out.println(returnCom.getCommand());
			System.out.println(returnCom.getTableNo());
			System.out.println(returnCom.getObj());
			System.out.println("Check!");
			server.writeCommand(returnCom, socket);

		} else if (com.getCommand() == Command.LogIn_Check) {
			
			System.out.println("�α����Ұž�");
			System.out.println(com);
			tableN = com.getTableNo();			
			BasicInfoC basic = (BasicInfoC)com.getObj();			
			System.out.println("basic "+basic);
			if(basic.getCodeNumber().length()>5){
			for (int i = 0; i < managing.check().size(); i++) {
				String check = managing.check().get(i);
				if (basic.getCodeNumber().equals(check)) {
					Command	returncom = new Command();
					returncom.setCommand(40);
					returncom.setFoodOrder(menu);
					returncom.setTableNo(tableN);
				defaultCom = returncom;
					server.writeCommand(returncom, socket);
					return ;
				}
			}
				} else if (basic.getCodeNumber().length()>0) {//��ȸ��
					System.out.println("�Խ�Ʈ�������");
					tableN = com.getTableNo();
					Command	returncom = new Command();
					returncom.setFoodOrder(menu);
					returncom.setCommand(40);
					returncom.setTableNo(tableN);
					server.writeCommand(returncom, socket);
					System.out.println("�Խ�Ʈ ����Ʈ �Ϸ�");
					return;
				} 
			Command	returncom = new Command();
			returncom.setCommand(50);
			System.out.println("Failed!");
			server.writeCommand(returncom, socket);							
			
			
		}else if(com.getCommand()== Command.Food_Check){
			
		}else if(com.getCommand()==Command.Message_Check){
			tableN = com.getTableNo();
			 Date time = (Date) (calendar.getTime());
			MessageInfoC Minfo = new MessageInfoC(0,"" ,time);	
			
			ArrayList<ServerThreads> aList = ServerSocketS.getsList();
			MessageInfoC messageInfo =(MessageInfoC) com.getObj();
			int receiveTableN = messageInfo.getToTable();
			String sendM = messageInfo.getContent();
			
			Command	returncom = new Command();
			
			for (int i = 0; i < aList.size(); i++) {
				ServerThreads st = aList.get(i);
				
				//if()
			}
			
		}else if(com.getCommand()==Command.Call_Check){
			tableN = com.getTableNo();
			System.out.println("����ȣ�����̺�ѹ�"+tableN);
			
			System.out.println("����ȣ�����");
			Command returncom = new Command();
			returncom.setCommand(40);

			server.writeCommand(returncom, socket);		
			
			
		}else if(com.getCommand()== Command.Food_Check){
			
			
		}else if(com.getCommand()==Command.Message_Check){
			System.out.println("Message_Check : 1");
			tableN = com.getTableNo();		//������ ��� ���̺� number
			Date time = (Date) (calendar.getTime());	//���� �ð�
			System.out.println("Message_Check : 2");
			
			MessageInfoC messageInfo =(MessageInfoC) com.getObj();	//���� �޼���
			System.out.println("Message_Check : 3");
			ArrayList<ServerThreads> aList = ServerSocketS.getsList();	//cla ���ϸ���Ʈ
			System.out.println("Message_Check : 4");
			for (int i = 0; i < aList.size(); i++) {
				ServerThreads st = aList.get(i);
				int revTableNo = messageInfo.getToTable();
				System.out.println("Message_Check : 4 ,i="+i);
				if(st.getCom().getTableNo() == revTableNo){	//���� table number
					System.out.println("Message_Check : 5,i="+i);
					Command	returncom = new Command();
					String sendM = messageInfo.getContent();
					MessageInfoC Minfo = new MessageInfoC(tableN,sendM,time);			
					returncom.setObj(Minfo);				//�����»�� (���̺�,�޼���,����ð�)
					returncom.setTableNo(revTableNo);		//���� table
					System.out.println("Message_Check : 6,i="+i);
					returncom.setCommand(70);
					System.out.println("Message_Check [ServerThreads]: "+st.toString());
					System.out.println("Message_Check [returncom]:"+returncom.toString());
					Socket s;
					try{
						System.out.println(revTableNo+"�� �޾ƶ� ��! ���� "+Minfo.getToTable());
						s = new Socket(st.getSocket().getInetAddress(), 9500);
						server.writeCommand(returncom, s);
					}catch(Exception e){
						e.printStackTrace();
					}
//					break;
				}else if(st.getCom().getTableNo() == tableN){
					Command	returncom = new Command();
					returncom.setCommand(40);
					try{
						System.out.println(revTableNo+"�� OK");
						Socket s = new Socket(st.getSocket().getInetAddress(), 9500);
						server.writeCommand(returncom, s);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}else if(com.getCommand()==Command.Call_Check){		//����ȣ��
			tableN = com.getTableNo();
			System.out.println("����ȣ�����̺�ѹ�"+tableN);
			
			System.out.println("����ȣ�����");
			Command returncom = new Command();
			returncom.setCommand(40);
			server.writeCommand(returncom, socket);
			
			new CallStaff(tableN);
		}

	}
}