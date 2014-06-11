package com.dev.client.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.dev.node.NoteModel;

public class DataExchangeClient {

	private final int PORTNUMBER = 2010;
	private final String HOSTNAME = "localhost";
	private ArrayList<NoteModel> listOfNodes;

	public DataExchangeClient() {
	}

	public ArrayList<NoteModel> getNodes() {

		try (Socket socket = new Socket(HOSTNAME, PORTNUMBER);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
			oos.writeObject("get object");
			Thread.sleep(2000);

			listOfNodes = (ArrayList<NoteModel>) ois.readObject();

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + HOSTNAME);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + HOSTNAME);
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfNodes;
	}

	public void pushNodes(ArrayList<NoteModel> storedNodes) {
		try (Socket socket = new Socket(HOSTNAME, PORTNUMBER);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
			oos.writeObject("push object");
			oos.writeObject(storedNodes);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + HOSTNAME);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + HOSTNAME);
			System.exit(1);
		}
	}
}
