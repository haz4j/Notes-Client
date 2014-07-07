package com.dev.client.data;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev.client.sockets.DataExchangeClient;
import com.dev.note.NoteModel;

/**
 * The Class DataSource.
 */
@Component
public class DataSource implements DataSourceModel {

	/** The stored notes. */
	private ArrayList<NoteModel> storedNotes;

	/** The data exchange client. */
	@Autowired
	private DataExchangeClient dataExchangeClient;

	/**
	 * Instantiates a new data source.
	 */
	public DataSource() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dev.client.data.DataSourceModel#getStoredNotes()
	 */
	public ArrayList<NoteModel> getStoredNotes() {
		storedNotes = dataExchangeClient.getNotes();
		return storedNotes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dev.client.data.DataSourceModel#pushNotes(java.util.ArrayList)
	 */
	public void pushNotes(ArrayList<NoteModel> notesArraylist) {
		dataExchangeClient = new DataExchangeClient();
		dataExchangeClient.pushNotes(notesArraylist);
	}
}
