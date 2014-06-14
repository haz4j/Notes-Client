/**
 * 
 */
package com.dev.client.data.impl;

import java.util.ArrayList;

import com.dev.client.data.model.DataCommunicationModel;
import com.dev.client.data.model.DataSourceModel;
import com.dev.node.Note;
import com.dev.node.NoteModel;

/**
 * @author hazard
 * 
 */
public class DataCommunication implements DataCommunicationModel {

	private DataSourceModel dataSource;
	private ArrayList<NoteModel> notesArraylist;
	// private JPanel panel;

	{
		notesArraylist = new ArrayList<NoteModel>();
	}

	public void setDataSourceModel(DataSourceModel dataSource) {
		this.dataSource = dataSource;
	}

	public DataCommunication() {
	}

	public ArrayList<NoteModel> getNotesArraylist() {
		syncronize();
		return notesArraylist;
	}

	public void syncronize() {
		ArrayList<NoteModel> storedNotesArraylist = dataSource.getStoredNodes();
		int storedNotesArraylistSize = storedNotesArraylist.size();
		int notesArraylistSize = notesArraylist.size();
		int cycles = Integer.max(storedNotesArraylistSize, notesArraylistSize);
		int numberOfChanges = 0;

		for (int i = 0; i < cycles; i++) {
			if (notesArraylistSize <= i) {
				Note note = new Note(storedNotesArraylist.get(i));
				storedNotesArraylist.get(i).setChanged(false);
				note.setChanged(false);
				notesArraylist.add(note);
				numberOfChanges++;
			}

			if (storedNotesArraylistSize <= i) {
				Note note = new Note(notesArraylist.get(i));
				note.setChanged(false);
				notesArraylist.get(i).setChanged(false);
				storedNotesArraylist.add(note);
				numberOfChanges++;
			}

			if (notesArraylist.get(i).isChanged()) {
				numberOfChanges++;
			}

			// if (notesArraylist.get(i).isChanged()) {
			// NoteModel note = notesArraylist.get(i);
			// note.setChanged(false);
			// storedNotesArraylist.get(i).setChanged(note.isChanged());
			// storedNotesArraylist.get(i).setDeleted(note.isDeleted());
			// storedNotesArraylist.get(i).setId(note.getId());
			// storedNotesArraylist.get(i).setText(note.getText());
			// numberOfChanges++;
			// }

		}

		if (numberOfChanges > 0) {
			dataSource.pushNodes(notesArraylist);
		}
	}
}
