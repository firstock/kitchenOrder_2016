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
//			System.out.println("�����غ���");
			server = new ServerSocket(port);
//			System.out.println("Űģ��Ʈ ����");
//			kitchenserver = new ServerSocket(kithchenPort);
//			System.out.println("Űģ���� ����");
//			kitchensocket = kitchenserver.accept();
//			System.out.println("�ֹ��غ�Ϸ�");
			while (true) {
				System.out.println("�����...");
				socket = server.accept();
				
				boolean f = true; // ����Ʈ�� ������ ������� true, ������� false
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