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
 * 클라이언트에서 전송된Command 를 이용해서 무슨 객체인지 알아내고 수행해주는것
 *
 */
public class CommandCheckS {
	
	MemberManagingS managing = new MemberManagingS();
	ServerThreads server = new ServerThreads();
	MenuS menu2 = new MenuS();
	GregorianCalendar calendar = new GregorianCalendar();
	SimpleDateFormat dateFormat = new SimpleDateFormat("aa hh시 mm분");
	ArrayList<OrderInfoC> menu = menu2.selectMenu();
	int tableN;
	String serialNo;
	
	public CommandCheckS(){}
	
	/** 
	 * @param Command, Socket
	 * 서버가 보낸 socket 속 Command VO 안에 있는
	 * command 값이 무엇이냐에 따라 join,LogIn,Food주문,Message인지 판단하고
	 * 그에 맞는 적절한 처리
	 */
	public void Check(Command com, Socket socket) {
		Command defaultCom = new Command();
		System.out.println("CommandCheckS클래스 Check메소드로 넘어옴");
		// Join_Check = 10;
		// LogIn_Check = 15;
		// Food_Check = 20;
		// Message_Check = 30;
		//
		// Command_OK = 40;
		// Command_Fail = 50;
		System.out.println("CommandCheckS클래스 Check메소드로 넘어옴");		//test
		System.out.println("com:"+com);

		if (com.getCommand() == Command.Join_Check) {
			tableN = com.getTableNo();
			managing.insertIntoMember((MemberInfoC) com.getObj());
			System.out.println("추가 되었습니다");
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
			
			System.out.println("로그인할거야");
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
				} else if (basic.getCodeNumber().length()>0) {//비회원
					System.out.println("게스트입장시작");
					tableN = com.getTableNo();
					Command	returncom = new Command();
					returncom.setFoodOrder(menu);
					returncom.setCommand(40);
					returncom.setTableNo(tableN);
					server.writeCommand(returncom, socket);
					System.out.println("게스트 와이트 완료");
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
			System.out.println("직원호출테이블넘버"+tableN);
			
			System.out.println("직원호출시작");
			Command returncom = new Command();
			returncom.setCommand(40);

			server.writeCommand(returncom, socket);		
			
			
		}else if(com.getCommand()== Command.Food_Check){
			
			
		}else if(com.getCommand()==Command.Message_Check){
			System.out.println("Message_Check : 1");
			tableN = com.getTableNo();		//보내는 사람 테이블 number
			Date time = (Date) (calendar.getTime());	//현재 시간
			System.out.println("Message_Check : 2");
			
			MessageInfoC messageInfo =(MessageInfoC) com.getObj();	//받은 메세지
			System.out.println("Message_Check : 3");
			ArrayList<ServerThreads> aList = ServerSocketS.getsList();	//cla 소켓리스트
			System.out.println("Message_Check : 4");
			for (int i = 0; i < aList.size(); i++) {
				ServerThreads st = aList.get(i);
				int revTableNo = messageInfo.getToTable();
				System.out.println("Message_Check : 4 ,i="+i);
				if(st.getCom().getTableNo() == revTableNo){	//받을 table number
					System.out.println("Message_Check : 5,i="+i);
					Command	returncom = new Command();
					String sendM = messageInfo.getContent();
					MessageInfoC Minfo = new MessageInfoC(tableN,sendM,time);			
					returncom.setObj(Minfo);				//보내는사람 (테이블,메세지,현재시간)
					returncom.setTableNo(revTableNo);		//받을 table
					System.out.println("Message_Check : 6,i="+i);
					returncom.setCommand(70);
					System.out.println("Message_Check [ServerThreads]: "+st.toString());
					System.out.println("Message_Check [returncom]:"+returncom.toString());
					Socket s;
					try{
						System.out.println(revTableNo+"번 받아라 야! 나는 "+Minfo.getToTable());
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
						System.out.println(revTableNo+"번 OK");
						Socket s = new Socket(st.getSocket().getInetAddress(), 9500);
						server.writeCommand(returncom, s);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}else if(com.getCommand()==Command.Call_Check){		//직원호출
			tableN = com.getTableNo();
			System.out.println("직원호출테이블넘버"+tableN);
			
			System.out.println("직원호출시작");
			Command returncom = new Command();
			returncom.setCommand(40);
			server.writeCommand(returncom, socket);
			
			new CallStaff(tableN);
		}

	}
}