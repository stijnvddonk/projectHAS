package com.socket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import gui.MainScreen;


public class SocketServer extends Thread {

	protected MainScreen ms;
    private ServerSocket server;
    private int port = 9876;
    private InetAddress host;
    
    public SocketServer(MainScreen _ms) {
		this.ms = _ms;
    }
    
    public void run() {
    	Thread.currentThread().setName("Server");
		try {
	        //create the socket server object
	        server = new ServerSocket(port);
	        host = InetAddress.getLocalHost();
	        //keep listens indefinitely until receives 'exit' call or program terminates
	        while(true){
	        	ms.addFeedback("Waiting for client request \n" + host + ":" + port);
//	            System.out.println("Waiting for client request \n" + host + ":" + port + "\n");
	            //creating socket and waiting for client connection
	            Socket socket = server.accept();
	            //read from socket to ObjectInputStream object
	            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	            
	            //convert ObjectInputStream object to String
	            String message = (String) ois.readObject();
//	            System.out.println("Message Received: " + message);
	            ms.addFeedback("Message Received: " + message);
	            //create ObjectOutputStream object
	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            //write object to Socket
	            oos.writeObject("Hi Client " + message);
	            
	            //close resources
	            ois.close();
	            oos.close();
	            socket.close();
	            
	            //terminate the server if client sends exit request
	//            if(message.equalsIgnoreCase("exit")) break;
	        }
		} catch (IOException IOE) {
			System.out.print("IOException:\n" + IOE.getStackTrace());
		} catch (Exception e) {
			System.out.print("Exception:\n" + e.getMessage() + "\n" + e.getStackTrace());
		}
    }
   
    
    public void closeConnection()
    {
    	try {
    		Thread.currentThread().interrupt();
			server.close();
		}  catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}