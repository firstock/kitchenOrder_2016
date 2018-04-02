package serverAct;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import command.Command;

public class ServerThreads extends Thread{
	ObjectOutputStream oos;
	OutputStream out;
	InputStream in;
	ObjectInputStream ois;	
	private Command com;
	private Socket socket;
	
	public ServerThreads(){}
	public ServerThreads(Socket socket){
		System.out.println(1);
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		super.run();
		System.out.println(2);
		readCommand(this.socket);
		System.out.println(com.toString());
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
				//socket.close();
			}catch(Exception e){
				
			}
			
		}
	}
	
	public Command getCom() {
		return com;
	}
	public void setCom(Command com) {
		this.com = com;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public void readCommand(Socket socket){
		CommandCheckS instance = null;
		try {
			if(socket == null){ System.out.println("소켓 null");}
			in = socket.getInputStream();
			ois= new ObjectInputStream(in);
			System.out.println("읽을래");
			com = (Command) ois.readObject();
			System.out.println("읽었다");
			instance = new CommandCheckS();
			System.out.println(com.getCommand());
			System.out.println(com.getTableNo());
			System.out.println(com.getObj());
			System.out.println("readCommand!!");
			instance.Check(com,socket);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try{
			//	socket.close();				
			}catch(Exception e){
				
			}
		}
	}
	@Override
	public String toString() {
		return "ServerThreads [oos=" + oos + ", out=" + out + ", in=" + in + ", ois=" + ois + ", com=" + com
				+ ", socket=" + socket + "]";
	}
	
	
}
