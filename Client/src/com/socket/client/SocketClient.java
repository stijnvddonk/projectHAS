package com.socket.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	
	private String mode = "server"; // There are two modes: server / local
	private String ipAddr;

	public void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
		
		if (mode.equals("local")) {
	        /*
	         * get the local host IP address, if server is running on some other IP, you need to use that
	         * This only works when you are using the server and client on the same PC.
	         */
	        InetAddress host = InetAddress.getLocalHost();
	        ipAddr = host.getHostName();
		} else if (mode.equals("server")) {
			// Hard code IP-Address to connect to the server
			ipAddr = "192.168.1.106";
		}
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        for(int i=0; i<5;i++){
            //establish socket connection to server
            socket = new Socket(ipAddr, 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i==4)oos.writeObject("exit");
            else oos.writeObject(ipAddr + ", hi server " + i);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}