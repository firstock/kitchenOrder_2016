package clientAct;

import java.awt.Container; 
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;

import clientVO.MessageInfoC;
import command.Command;

public class ReadThread extends Thread{
	Socket soc;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Command com;
	private ServerSocket server;
	private JFrame jf;
	private JTextArea ta;
	private final int port=9500;
	final String SERVER_IP= "211.183.9.84";
	private static ReadThread readThread;
	
	public static ReadThread getInstance() {
		if(readThread == null){
			return new ReadThread();
		}else {
			return readThread;
		}
	}
	public ReadThread(){}
	@Override
	public void run() {
		super.run();
		loop();
	}
	private void loop(){
		try{
			System.out.println("1:");
			server = new ServerSocket(port);
			System.out.println("2:");
			while(true){
				soc = server.accept();
				System.out.println("3:");
				com = readCommand(soc);
				System.out.println("4:");
				if(com != null && com.getObj() != null){
					System.out.println("ReadThread.loop() 1 : "+com.toString());
					MessageInfoC mc = (MessageInfoC)com.getObj();
					System.out.println("ReadThread.loop() 2 : "+mc.toString());
					
					//command 분류 (해당 번호로 이동)
					if(com.getCommand() == com.Receive_Sms){
						System.out.println("ReadThread.loop() 3 : "+mc.toString());
						setMessage(mc);		//메세지 받아오기 메세지창 자동열림
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public void setMessage(MessageInfoC mc){
		if(jf == null){
			System.out.println(1);
			jf = new JFrame("Message");
			jf.setPreferredSize(new Dimension(400, 400));
			ta = new JTextArea();
			ta.setPreferredSize(new Dimension(400, 400));
			System.out.println(2);
			Container ct = jf.getContentPane();
			ct.add(ta);
			System.out.println("2-1");
			jf.pack();
			System.out.println("2-2");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println("2-3");
			jf.setVisible(true);
			System.out.println(3);
		}
		String aTextTemp = ta.getText();
		System.out.println(4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		System.out.println(5);
		String msg = mc.getToTable()+"번 T : "+mc.getContent()+" ["+sdf.format(mc.getSentTime())+"]";	//메세지 구조를 만들어준다.
		System.out.println(6);
		ta.setText(aTextTemp+"\n"+msg);
		System.out.println(7);
	}
	
	
	
	
//	public JTextArea getTa() { 
//		return ta;
//	}
//	public void setTa(MessageInfoC mc) {
//		String aTextTemp = ta.getText();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
//		String msg = mc.getToTable()+"번 T : "+mc.getContent()+" ["+sdf.format(mc.getSentTime())+"]";	//메세지 구조를 만들어준다.
//		ta.setText(aTextTemp+"\n"+msg);
//	}
	//	public void createSocket(){
//		if(soc == null){
//			try {
//				System.out.println("CREATE NEW SOCKET!");
//				soc= new Socket(SERVER_IP,port);
//				System.out.println("CREATED NEW SOCKET!");
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
	public Command readCommand(Socket soc) {
		try {
			ois= new ObjectInputStream(soc.getInputStream());
			com = (Command) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return com;
	}
	
}
