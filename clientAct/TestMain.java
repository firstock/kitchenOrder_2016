package clientAct;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import clientVO.MessageInfoC;
import command.Command;
import serverAct.ServerThreads;

public class testMain {
	ServerSocket server;
	ServerSocket kitchenserver;
	ObjectOutputStream oos;
	OutputStream out;
	InputStream in;
	ObjectInputStream ois;	
	
	private static final int port = 9500;
	public void run(){
		Command	returncom = new Command();
//		String sendM = messageInfo.getContent();
		MessageInfoC Minfo = new MessageInfoC(1,"보냈습니다.",new Date());	
		returncom.setObj(Minfo);				//보내는사람 (테이블,메세지,현재시간)
		returncom.setTableNo(18);		//받을 table
//		System.out.println("Message_Check : 6,i="+i);
		returncom.setCommand(70);
//		returncom
		try{
			writeCommand(returncom, new Socket("211.183.9.81",9500));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void writeCommand(Command returncom, Socket socket){
		try {
			System.out.println("com"+returncom);
			System.out.println(socket);
		
			System.out.println(returncom.getCommand());
			System.out.println(returncom.getTableNo());
			System.out.println(returncom.getObj());
			oos =  new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(returncom);
			oos.flush();
			System.out.println("writeCommand!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try{
				socket.close();
				oos.close();
				
			}catch(Exception e){
				
			}
			
		}
	}
	public static void main(String[] args) {
		new testMain().run();
	}

}
