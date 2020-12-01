package _02_Chat_Application;

import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.*;

public class client1 {
	
private int port;
private String ip;
private String password;
private ChatApp app;

DataOutputStream dos;
DataInputStream dis;

public client1(String ip, int port, String password, ChatApp app) {
	this.ip = ip;
	this.port = port;
	this.password = password;
	this.app = app;
}

   public void start() {

    try {
    	Socket s = new Socket(ip, port);
    	dos = new DataOutputStream(s.getOutputStream());
    	dos.writeUTF("message");
    	dis = new DataInputStream(s.getInputStream());
    	
    	
    	if(app.passwordEnter.equals(password)) {
			app.add(app.textField);
			app.setVisible(true);
			app.setSize(400, 300);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println("Password Is Correct");
			
			}
			else {
				JOptionPane.showMessageDialog(null, "Connection terminated... Incorrect Passcode");
			}
    	while (s.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, dis.readUTF());
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	String message = dis.readUTF();
    	System.out.println(message);
    	s.close();
    	
    } catch(IOException e) {
    	System.out.println("ERROR");
    	e.printStackTrace();
    }


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
}
