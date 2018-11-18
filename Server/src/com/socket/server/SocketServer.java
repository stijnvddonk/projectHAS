package com.socket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import gui.MainScreen;


public class SocketServer {

	protected MainScreen ms;
    private ServerSocket server;
    private int port = 9876;
    
    public SocketServer(MainScreen _ms) {
		this.ms = _ms;
		try {
	        //create the socket server object
	        server = new ServerSocket(port);
	        InetAddress host = InetAddress.getLocalHost();
	        //keep listens indefinitely until receives 'exit' call or program terminates
	        String temp = "Server had started " + host + ":" + port;
	        this.ms.addFeedback(temp);
	        while(true){
	            System.out.println("Waiting for client request \n" + host + ":" + port + "\n");
	            //creating socket and waiting for client connection
	            Socket socket = server.accept();
	            //read from socket to ObjectInputStream object
	            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	            
	            //convert ObjectInputStream object to String
	            String message = (String) ois.readObject();
	            System.out.println("Message Received: " + message);
	            //create ObjectOutputStream object
	            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	            //write object to Socket
	            oos.writeObject("Hi Client "+message);
	            //close resources
	            ois.close();
	            oos.close();
	            socket.close();
	            //terminate the server if client sends exit request
	//            if(message.equalsIgnoreCase("exit")) break;
	        }
		} catch (IOException IOE) {
			System.out.print("IOException:\n" + IOE.getStackTrace());
		} catch (ClassNotFoundException CNFE) {
			System.out.print("ClassNotFoundException:\n" + CNFE.getStackTrace());
		}
			//		} catch (Exception e) {
//			System.out.print("Exception:\n" + e.getMessage() + "\n" + e.getStackTrace());
//		}
//        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
//        server.close();
    }
    
//    public void closeConnection()
//    {
//    	try {
//			server.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    }
}