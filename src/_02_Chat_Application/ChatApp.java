package _02_Chat_Application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import _00_Click_Chat.networking.Server;

public class ChatApp extends JFrame implements KeyListener {
	JTextArea textField = new JTextArea();

	server1 server;
	client1 client;
	String password;
	String passwordEnter;
	static final int port = 8081;
	Boolean isServer = true;

	public static void main(String[] args) {
		new ChatApp();
		
	}

	public ChatApp() {

		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat App",
				JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			isServer = true;
			try {
				server = new server1(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setTitle("SERVER");
			password = JOptionPane.showInputDialog("Please create a server passcode");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + Server.getIPAddress() + "\nPort: " + server.getPort());
			textField.addKeyListener(this);
			System.out.println(password);

			add(textField);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();

		} else {
			isServer = false;
			setTitle("CLIENT");
			String ipStr = server.getIPAddress();

			System.out.println(ipStr);

			passwordEnter = JOptionPane.showInputDialog("Please enter server password...");
			client = new client1(ipStr, port, passwordEnter, this);
			textField.addKeyListener(this);
			client.start();
			System.out.println(passwordEnter);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (isServer == false) {
				client.sendClick();
			} else if (isServer) {
				server.sendClick();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
