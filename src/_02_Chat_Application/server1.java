package _02_Chat_Application;

import java.net.*;
import java.io.*;

public class server1 extends Thread {

	ServerSocket serversocket;
	private static int port;

	public server1(int port) throws IOException {
		this.port = port;

	}

	public void start() {

		Boolean b = true;

		while (b == true) {
			System.out.println("creating serversocket...");
			try {
				serversocket = new ServerSocket(port, 100);

				System.out.println("Server waiting for a client to connect...");
				Socket socket = serversocket.accept();

				System.out.println("The client has connected!");

				DataInputStream dis = new DataInputStream(socket.getInputStream());
				String message = dis.readUTF();
				System.out.println(message);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("message");
				socket.close();

			} catch (SocketTimeoutException s) {
				System.out.println("ERROR! SocketTimeoutException");
				b = false;
				s.printStackTrace();
			} catch (IOException e) {
				System.out.println("ERROR! IOExeption");
				b = false;
				e.printStackTrace();
			}

		}
	}

	public static String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public static int getPort() {
		return port;
	}

	public void sendClick() {

	}

	public static void main(String[] args) {

		Thread t = new Thread(() -> {
			try {
				server1 sg = new server1(8080);
				sg.start();
			} catch (IOException e) {
				System.out.println("ERROR!");
				e.printStackTrace();
			}
		});
		t.start();
	}
}
