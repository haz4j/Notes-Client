package com.dev.client.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.dev.note.NoteModel;

/**
 * The Class DataExchangeClient.
 */
@Component
public class DataExchangeClient {

	/** The portnumber. */
	private final int PORTNUMBER = 2010;

	/** The hostname. */
	private final String HOSTNAME = "localhost";

	/** The list of notes. */
	private ArrayList<NoteModel> listOfNotes;

	/**
	 * Instantiates a new data exchange client.
	 */
	public DataExchangeClient() {
	}

	/**
	 * Gets the notes. Format is very simple: if we need to send object from
	 * client we send "push object" from client. Or "get object" otherwise
	 *
	 * @return the notes
	 */
	public ArrayList<NoteModel> getNotes() {

		try (Socket socket = new Socket(HOSTNAME, PORTNUMBER);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
			oos.writeObject("get object");
			Thread.sleep(2000);

			listOfNotes = (ArrayList<NoteModel>) ois.readObject();

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + HOSTNAME);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + HOSTNAME);
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return listOfNotes;
	}

	/**
	 * Push notes. Format is very simple: if we need to send object from client
	 * we send "push object" from client. Or "get object" otherwise
	 *
	 * @param storedNotes
	 *            the stored notes
	 */
	public void pushNotes(ArrayList<NoteModel> storedNotes) {
		try (Socket socket = new Socket(HOSTNAME, PORTNUMBER);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
			oos.writeObject("push object");
			oos.writeObject(storedNotes);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + HOSTNAME);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + HOSTNAME);
			System.exit(1);
		}
	}
}
