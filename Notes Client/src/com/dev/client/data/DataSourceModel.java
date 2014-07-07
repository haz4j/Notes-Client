package com.dev.client.data;

import java.util.ArrayList;

import com.dev.note.NoteModel;

/**
 * The Interface DataSourceModel. Manages all communications with data sources.
 */
public interface DataSourceModel {

	/**
	 * Gets the stored notes from data sources
	 *
	 * @return the stored notes
	 */
	ArrayList<NoteModel> getStoredNotes();

	/**
	 * Push notes to data sources
	 *
	 * @param notesArraylist
	 *            the notes arraylist
	 */
	public void pushNotes(ArrayList<NoteModel> notesArraylist);
}
