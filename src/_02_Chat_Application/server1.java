package _02_Chat_Application;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class server1 extends Thread {

	ServerSocket serversocket;
	private static int port;
	private ChatApp app;
	Socket socket;
	
	DataInputStream dis;
	DataOutputStream dos;
	

	public server1(int port) throws IOException {
		this.port = port;
		this.app = app;

	}

	public void start() {

			System.out.println("creating serversocket...");
			try {
				System.out.println(port);
				serversocket = new ServerSocket(port, 100);

				System.out.println("Server waiting for a client to connect...");
				socket = serversocket.accept();

				System.out.println("The client has connected!");

				dis = new DataInputStream(socket.getInputStream());
				String message = dis.readUTF();
				System.out.println(message);
				dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF("message");
				

			} catch (SocketTimeoutException s) {
				System.out.println("ERROR! SocketTimeoutException");
				s.printStackTrace();
			} catch (IOException e) {
				System.out.println("ERROR! IOExeption");
				e.printStackTrace();
			}

		
		
		while (socket.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, dis.readUTF());
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Connection Lost");
				System.exit(0);
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

		  try {
				if (dos != null) {
					dos.writeUTF(app.textField.getText());
					dos.flush();
					app.textField.setText("");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
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
