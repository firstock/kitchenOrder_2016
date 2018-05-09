package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import clientVO.MCardInfoC;
import clientVO.MemberInfoC;
import command.Command;

public class Client2 {

	public static void main(String[] args) {
		Socket soc = null;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		Command com;
		
		
		try {
			
			soc= new Socket("211.183.9.84",9500);
			System.out.println(22222);
			OutputStream out = soc.getOutputStream();
			oos= new ObjectOutputStream(out);
			System.out.println("만들게");
			VO2 vo = new VO2("test",10,"사람","남자");			
			oos.writeObject(vo);
			oos.flush();
			System.out.println(111);
			System.out.println("보냇어");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try{
				soc.close();
			}catch(Exception e){
				
			}
		}
	}
	
	
	}


