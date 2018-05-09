package serverAct;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

import command.Command;

public class KithchenThread {
	ObjectOutputStream oos;
	OutputStream out;
	InputStream in;
	ObjectInputStream ois;	
	Command com;
	
	
	public KithchenThread(Socket kitchenSocket){
		 readeKithcen(kitchenSocket);
		
		
	}
	
		public void readeKithcen(Socket socket){
		
		
	}
	
	public void writeKitchn(){
		
		

}

}