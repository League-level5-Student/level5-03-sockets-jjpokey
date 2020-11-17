package _02_Chat_Application;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import _00_Click_Chat.networking.Server;





/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame implements KeyListener{
JTextArea textField = new JTextArea();
	
	server1 server;
	client1 client;
	String password;
	String passwordEnter;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat App", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			try {
				server = new server1(8081);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setTitle("SERVER");
			password = JOptionPane.showInputDialog("Please create a server passcode");
			JOptionPane.showMessageDialog(null, "Server started at: " + Server.getIPAddress() + "\nPort: " + server.getPort());
			textField.addKeyListener(this);
			System.out.println(password);
			
			add(textField);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = server.getIPAddress();
			int prtStr = server.getPort();
			passwordEnter = JOptionPane.showInputDialog("Please enter server password...");
			client = new client1(ipStr, prtStr, passwordEnter, this);
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

		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}
}
