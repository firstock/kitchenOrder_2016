package kitchenUI;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Kitchen {

	public static void main(String[] args) {
			Socket socket= null;
			LinkedList<OrderInfoC> list =new LinkedList<OrderInfoC>();
			
			try {
				socket=new Socket("localhost",9600);
				InputStream in =socket.getInputStream();
				OutputStream out =socket.getOutputStream();
				ObjectInputStream objIn = new ObjectInputStream(in);
				OrderInfoC getObj = (OrderInfoC) objIn.readObject();
				list.add(getObj);	
				
				Iterator<OrderInfoC> iterator =list.iterator();
				while (iterator.hasNext()) {
					OrderInfoC str =iterator.next();
					System.out.println(str);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}

