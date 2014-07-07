/**
 * 
 */
package com.dev.client.data;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev.note.Note;
import com.dev.note.NoteModel;

/**
 * The Class DataCommunication. It contains all logic of synchronization between
 * client and server sides.
 */
@Component
public class DataCommunication implements DataCommunicationModel {

	/** The data source. */
	@Autowired
	private DataSourceModel dataSource;

	/** The notes arraylist. Here we store all clients notes */
	private ArrayList<NoteModel> notesArraylist;

	{
		notesArraylist = new ArrayList<NoteModel>();
	}

	/**
	 * Instantiates a new data communication.
	 */
	public DataCommunication() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dev.client.data.DataCommunicationModel#getNotesArraylist()
	 */
	public ArrayList<NoteModel> getNotesArraylist() {
		syncronize();
		return notesArraylist;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Synchronize notes between server and client. The resulted arraylist of
	 * nodes submits to server to be stored on DB.
	 * 
	 * @see com.dev.client.data.DataCommunicationModel#syncronize()
	 */
	public void syncronize() {
		ArrayList<NoteModel> storedNotesArraylist = dataSource.getStoredNotes();
		int storedNotesArraylistSize = storedNotesArraylist.size();
		int notesArraylistSize = notesArraylist.size();
		int cycles = Math.max(storedNotesArraylistSize, notesArraylistSize);
		int numberOfChanges = 0;

		for (int i = 0; i < cycles; i++) {
			if (notesArraylistSize <= i) {
				Note note = new Note(storedNotesArraylist.get(i));
				notesArraylist.add(note);
			}

			if (storedNotesArraylistSize <= i) {
				numberOfChanges++;
			}

			if ((notesArraylistSize > i) && (notesArraylist.get(i).isChanged())) {
				numberOfChanges++;
			}

			if ((storedNotesArraylistSize > i) && (storedNotesArraylist.get(i).isChanged())) {
				NoteModel note = storedNotesArraylist.get(i);
				notesArraylist.set(i, note);
				numberOfChanges++;
			}
		}

		if (numberOfChanges > 0) {
			dataSource.pushNotes(notesArraylist);
			setUnchanged(notesArraylist);
			clearDeleted(notesArraylist);
		}
	}

	/**
	 * Clear deleted. After synchronization deletes all notes from arraylist
	 * that have been earlier marked as "deleted"
	 *
	 * @param notesArraylist
	 *            the notes arraylist
	 */
	private void clearDeleted(ArrayList<NoteModel> notesArraylist) {
		for (int i = 0; i < notesArraylist.size(); i++) {

			if (notesArraylist.get(i) != null && notesArraylist.get(i).isDeleted()) {
				notesArraylist.remove(i);
			}
		}
	}

	/**
	 * Sets the unchanged. After synchronization marks all notes as "!changed"
	 *
	 * @param notesArraylist
	 *            the new unchanged
	 */
	private void setUnchanged(ArrayList<NoteModel> notesArraylist) {
		for (int i = 0; i < notesArraylist.size(); i++) {

			if (notesArraylist.get(i) != null) {
				notesArraylist.get(i).setChanged(false);
			}
		}
	}
}