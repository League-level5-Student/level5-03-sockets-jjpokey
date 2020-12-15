package _02_Chat_Application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import _00_Click_Chat.networking.Server;

public class ChatApp extends JFrame implements KeyListener {
	JTextArea textField = new JTextArea();
	JLabel mSent = new JLabel();
	JPanel panel = new JPanel();
	JTextPane area = new JTextPane();
	JScrollPane scroll = new JScrollPane(area, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
		
		setPreferredSize(new Dimension(450, 300));
		panel.add(textField);
		panel.add(mSent);
		mSent.setPreferredSize(new Dimension(400, 260));
		textField.setPreferredSize(new Dimension(400, 40));
		mSent.setText("test");
		mSent.setVisible(true);
		scroll.setPreferredSize(new Dimension(450, 250));
		textField.setBorder(BorderFactory.createLineBorder(Color.PINK));
		panel.add(scroll);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(panel);
		
		if (response == JOptionPane.YES_OPTION) {
			isServer = true;
			try {
				server = new server1(port, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setTitle("SERVER");
			password = JOptionPane.showInputDialog("Please create a server passcode");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + Server.getIPAddress() + "\nPort: " + server.getPort());
			textField.addKeyListener(this);
			System.out.println(password);

			server.start();

		} else {
			isServer = false;
			setTitle("CLIENT");
			String ipStr = server.getIPAddress();
			setVisible(false);
			
			
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
