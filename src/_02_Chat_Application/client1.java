package _02_Chat_Application;

import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.*;

public class client1 {
	
private int port;
private String ip;
private String passwordEnter;
private ChatApp app;

DataOutputStream dos;
DataInputStream dis;

public client1(String ip, int port, String passwordEnter, ChatApp app) {
	this.ip = ip;
	this.port = port;
	this.passwordEnter = passwordEnter;
	this.app = app;
}

   public void start() {

    try {
    	Socket s = new Socket(ip, port);
    	dos = new DataOutputStream(s.getOutputStream());
    	dos.writeUTF("message");
    	dis = new DataInputStream(s.getInputStream());
    	dos.writeUTF(passwordEnter + "Servers Connected!");
    	
   // 	if() {
			app.setVisible(true);
			System.out.println("Password Is Correct");
			
		//	}
		//	else {
				//JOptionPane.showMessageDialog(null, "Connection terminated... Incorrect Passcode");
			//}
    	while (s.isConnected()) {
			try {
				//JOptionPane.showMessageDialog(null, dis.readUTF());
				
				

	String clientData = dis.readUTF();
				
			
					app.addMessageToWindow(true, clientData);

				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	String message = dis.readUTF();
    	System.out.println(message);
    	s.close();
    	
    } catch(IOException e) {
    	JOptionPane.showMessageDialog(null, "Connection Lost");
    	System.out.println("ERROR");
    	e.printStackTrace();
    }


   }
   
   public void sendClick() {
	   try {
			if (dos != null) {
				dos.writeUTF(passwordEnter + app.textField.getText());
				app.addMessageToWindow(false, app.textField.getText());
				dos.flush();
				app.textField.setText("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
