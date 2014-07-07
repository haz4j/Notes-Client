/**
 * 
 */
package com.dev.client.data;

import java.util.ArrayList;

import com.dev.note.NoteModel;

/**
 * The Interface DataCommunicationModel. It contains all logic of
 * synchronization between client and server sides.
 *
 * @author hazard
 */
public interface DataCommunicationModel {

	/**
	 * Syncronize.
	 */
	public void syncronize();

	/**
	 * Gets the notes arraylist.
	 *
	 * @return the notes arraylist
	 */
	public ArrayList<NoteModel> getNotesArraylist();
}
