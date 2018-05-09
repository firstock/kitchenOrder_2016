package serverAct;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;

import command.Command;

public class ServerSocketS {
	ServerSocket server;
	ServerSocket kitchenserver;
	Socket socket;
	Socket kitchensocket;
	private ServerThreads serverThreads;
	private static final int port = 9500;
	private static final int kithchenPort = 9600;
	private static ArrayList<ServerThreads> sList;

	public static ArrayList<ServerThreads> getsList() {
		return sList;
	}

	public void SeverSocket() {
		sList = new ArrayList<ServerThreads>();
		// this.sList = sList;
		try {
			// if (server == null)
//			System.out.println("개점준비중");
			server = new ServerSocket(port);
//			System.out.println("키친포트 만듬");
//			kitchenserver = new ServerSocket(kithchenPort);
//			System.out.println("키친소켓 만듬");
//			kitchensocket = kitchenserver.accept();
//			System.out.println("주방준비완료");
			while (true) {
				System.out.println("대기중...");
				socket = server.accept();
				
				boolean f = true; // 리스트에 소켓이 없을경우 true, 있을경우 false
				for (int i = 0; i < sList.size(); i++) {
					ServerThreads serverThreads = sList.get(i);
					if (serverThreads.getSocket().getInetAddress().equals(socket.getInetAddress())) {
						f = false;
					}
				}
				if (f) {
					serverThreads = new ServerThreads(socket);
					serverThreads.start();
					sList.add(serverThreads);
				} else {
					serverThreads = new ServerThreads(socket);
					serverThreads.start();
				}

				System.out.println("hello client");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}