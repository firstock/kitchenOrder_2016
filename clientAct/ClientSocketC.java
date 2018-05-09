package clientAct;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import command.Command;

public class ClientSocketC {
	Socket soc;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Command com;
	private static final int port=9500;
	final String SERVER_IP= "211.183.9.84";
	Thread t1;
	public ClientSocketC() {}
	
	public void createSocket(){
		//if(soc == null){
			try {
				System.out.println("CREATE NEW SOCKET!");
				soc= new Socket(SERVER_IP,port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	//}
	
	public void writeCommand(Command com)  {
		createSocket();
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(com);
			oos.flush();
			System.out.println("write Complete~");
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				System.out.println("OOS작업 종료! SOC:" + soc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Command readCommand() {
		try {
			System.out.println("OIS작업 시작! SOC:" + soc);
			ois= new ObjectInputStream(soc.getInputStream());
			com = (Command) ois.readObject();
			System.out.println(com.getTableNo());
			System.out.println(com.getObj());
			System.out.println(com.getCommand());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return com;
	}
	
	public void closeSocket(){
		try {
			//soc.close();
//			oos.close();
//			ois.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
