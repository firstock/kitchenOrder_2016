package kitchenUI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.print.DocFlavor.INPUT_STREAM;

public class ServerExample1 {

	public static void main(String[] args) {
		
		LinkedList<OrderInfoC> menuList = new LinkedList<OrderInfoC>();	
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM��dd�� aahh��mm��");
		String str = dateFormat.format(date);
		ServerSocket serverSocket =null;
		Socket socket=null;
		try{
			serverSocket =new ServerSocket(9600);
			System.out.println("waiting...");
			socket = serverSocket.accept();
			System.out.println("�����");
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("objectoutputstream ����");
			OrderInfoC orderInfo = new OrderInfoC("���",1,1,str, null);
			menuList.add(orderInfo);
			System.out.println("orderinfo������");
			out.writeObject(menuList);
			
			System.out.println("������Ʈ ����");
			out.flush();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				
				socket.close();
				serverSocket.close();
			} catch(Exception e) {}
		}

	}

}
